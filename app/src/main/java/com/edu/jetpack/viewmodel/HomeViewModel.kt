package com.edu.jetpack.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.jetpack.data.ConfigsData
import com.edu.jetpack.model.Config
import com.edu.jetpack.parser.dto.entities.ProfileItem
import com.edu.jetpack.parser.fmt.VlessFmt
import com.edu.jetpack.parser.fmt.VmessFmt
import com.edu.jetpack.parser.handler.MmkvManager
import com.edu.jetpack.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeViewModel(
    private val context: Context
): ViewModel() {
    companion object {
        private const val TAG = "HomeViewModel"
    }
    private val _configs = MutableStateFlow(ConfigsData.toMutableList())
    val configs: StateFlow<List<Config>> = _configs.asStateFlow()

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    private var _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val _connectedConfigGuid = MutableStateFlow<String?>(null)
    val connectedConfigGuid: StateFlow<String?> = _connectedConfigGuid.asStateFlow()

    var currentConfigGuid: String? = null

    init {
        loadConfigsFromMmkv()
    }

    fun toggleConnection() {
        _isConnected.value = !_isConnected.value
        if(_isConnected.value)
            _toastMessage.value = "VPN Connected"
        else
            _toastMessage.value = "VPN Connected"
    }

    fun setConnected(status: Boolean) {
        _isConnected.value = status
    }

    fun connectToConfig(guid: String) {
        if (_isConnected.value && _connectedConfigGuid.value == guid){

        } else {
            _isConnected.value = true
            _connectedConfigGuid.value = guid
            currentConfigGuid = guid
            _toastMessage.value = "Connect To ${getConfigRemark(guid)?.profile?.remarks}"
        }
    }

    fun setGuidBasedOnClickedConfig(guid: String) {
        _connectedConfigGuid.value = guid
    }

    private fun getConfigRemark(guid: String): Config? {
        return _configs.value.find { it.guid == guid }
    }

    private fun isConfigExist(content: String): Boolean{
        val serverList = MmkvManager.decodeAllServerList()
        for (guid in serverList) {
            val raw = MmkvManager.decodeServerRaw(guid)
            if (raw == null) continue
            if (raw.trim() == content.trim()) return true
        }

        return false
    }

    private fun loadConfigsFromMmkv(){
        val serverList = MmkvManager.decodeAllServerList()
        val configsList = mutableListOf<Config>()
        for (guid in serverList) {
            Log.i(TAG, "Guid: $guid")

            val profile = MmkvManager.decodeServerConfig(guid)
            if (profile == null) continue

            var raw = MmkvManager.decodeServerRaw(guid)
            Log.i(TAG, "Raw: $raw")

            if (raw == null) raw = ""
            var name: String? = profile.remarks
            if (name == null || name.isEmpty()) name = profile.serviceName
            if (name == null || name.isEmpty()) name = "Imported Config"

            val config = Config(
                guid = guid,
                name = name ?: "Imported Config",
                type = profile.configType?.name ?: "Unknown", // اصلاح شده
                address = "${profile.server ?: ""}:${profile.serverPort ?: ""}",
                network = profile.network ?: "tcp",
                sni = profile.sni,
                fp = profile.fingerPrint,
                details = profile.security ?: "none",
                content = raw,
                profile = profile,
            )

            configsList.add(config)
        }
        _configs.value = configsList
    }

    private fun stopVpnService() {
        setConnected(false);
        try {
            Log.i(TAG, "VPN Service stopped")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to stop VPN service", e)
        }
    }

    fun removeConfig(guid: String) {
        Log.i(TAG, "*********************************************")
        Log.i(TAG, "removeConfig Function Is Called, Guid: $guid")
        viewModelScope.launch {
            if (guid.isNotEmpty()){
                MmkvManager.removeServer(guid)
                currentConfigGuid = null

                stopVpnService()
                _toastMessage.value = "VPN Disconnected"

                val newList = _configs.value.filter { it.guid != guid }
                _configs.value = newList as MutableList<Config>
                _toastMessage.value = "Config Is Deleted"
            }
        }
    }

    fun downloadFreeConfig() {}

    fun importConfigFromUri(uri: Uri){
        viewModelScope.launch {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val reader = BufferedReader(InputStreamReader(inputStream))

                val stringBuilder = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null)
                    stringBuilder.append(line).append("\n")
                reader.close()
                inputStream?.close()

                val content = stringBuilder.toString()
                Log.i(TAG, "Config Imported Successfully")

                importConfigFromFileSystem(content)
            } catch (e: Exception) {
                Log.e(TAG, "Import File Failed", e)
                _toastMessage.value = "Import Failed"
            }
        }
    }

    private fun importConfigFromFileSystem(content: String) {
        try {
            val fixedLink = Utils.sanitizeVlessLink(content)
            var profile: ProfileItem? = null

            if (fixedLink != null && fixedLink.startsWith("vless://"))
                profile = VlessFmt.parse(fixedLink)
            else if (fixedLink != null && fixedLink.startsWith("vmess://"))
                profile = VmessFmt.parse(fixedLink)

            if (profile == null) {
                _toastMessage.value = "Invalid Config Format"
                return
            }

            var name = profile.serviceName
            if (name == null || name.isEmpty()) name = "Imported Config"

            if(isConfigExist(content)) {
                _toastMessage.value = "Config Already Exist"
                return
            }

            val guid = MmkvManager.encodeServerConfig("", profile)
            MmkvManager.encodeServerRaw(guid, content)
            MmkvManager.setSelectServer(guid)

            val config = Config(
                guid = guid,
                name = name ?: "Imported Config",
                type = profile.configType?.name ?: "Unknown",
                address = "${profile.server ?: ""}:${profile.serverPort ?: ""}",
                network = profile.network ?: "tcp",
                sni = profile.sni,
                fp = profile.fingerPrint,
                details = profile.security ?: "none",
                content = content,
                profile = profile
            )

            val currentList = _configs.value.toMutableList()
            currentList.add(config)
            _configs.value = currentList

            _toastMessage.value = "Config Is Imported"
        } catch (e: Exception) {
            Log.i(TAG, "Import Config From FileSystem Failed", e)
            _toastMessage.value = "Import Failed"
        }
    }

    fun clearToast(){
        _toastMessage.value = null
    }
}









































