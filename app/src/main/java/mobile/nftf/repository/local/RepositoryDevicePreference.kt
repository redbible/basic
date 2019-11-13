package mobile.nftf.repository.local

class RepositoryDevicePreference: RepositoryCached() {

    override fun setRawValue(key: LocalKey, value: Any?) {
        //Usage set Data String to Local Device Preference
    }

    override fun getRawValue(key: LocalKey): String? {
        //Usage get and return Data String, json or any thing
        return ""
    }
}