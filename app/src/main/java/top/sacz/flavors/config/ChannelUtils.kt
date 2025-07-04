package top.sacz.flavors.config

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

object ChannelUtils {
    fun getChannelName(context: Context): String {
        try {
            val packageManager: PackageManager? = context.packageManager
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                val applicationInfo: ApplicationInfo = packageManager.getApplicationInfo(
                    context.packageName,
                    PackageManager.GET_META_DATA
                )
                if (applicationInfo.metaData != null) {
                    return applicationInfo.metaData.getString("CHANNEL") ?: "unknown"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "unknown"
    }
}