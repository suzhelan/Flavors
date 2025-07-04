package top.sacz.flavors.config

import top.sacz.flavors.BuildConfig

object AppConfig {
    lateinit var baseUrl : String

    /**
     * 对接口初始化 分env维度初始化
     */
    fun initApiUrl() {
        when(BuildConfig.ENV) {
            "test" -> {
                this.baseUrl = "testUrl"
            }
            "prod" -> {
                this.baseUrl = "proUrl"
            }
        }
    }
}