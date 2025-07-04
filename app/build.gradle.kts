import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.text.SimpleDateFormat
import java.util.Date

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "top.sacz.flavors"
    compileSdk = 35

    defaultConfig {
        applicationId = "top.sacz.flavors"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    applicationVariants.all {
        val buildTypeName = buildType.name
        val timestamp = SimpleDateFormat("yyyy-MM-dd_HH.mm").format(Date())
        //转换BaseVariantOutputImpl
        outputs.forEach { output ->
            output as BaseVariantOutputImpl
            output.outputFileName = "Flavors_v${defaultConfig.versionName}_${flavorName}_${buildTypeName}_$timestamp.apk"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }


    // 创建产品风味维度(渠道类型) 我这里设置为 环境和渠道
    flavorDimensions ("env", "channel")

    // 创建产品风味(渠道)
    productFlavors {
        // 环境
        create("envProd") {
            //维度归类
            dimension = "env"
            //添加环境标识到BuildConfig类中
            buildConfigField("String", "ENV", "\"prod\"")
        }
        create("envTest") {
            dimension = "env"
            //添加环境标识到BuildConfig类中
            buildConfigField("String", "ENV", "\"test\"")
        }

        // 渠道
        create("official") {
            dimension = "channel"
            applicationId = "top.sacz.flavors"
            //设置AndroidManifest的属性
            manifestPlaceholders.putAll(mapOf("CHANNEL_VALUE" to "official"))
        }
        create("vivo") {
            dimension = "channel"
            applicationId = "top.sacz.flavors.vivo"
            manifestPlaceholders.putAll(mapOf("CHANNEL_VALUE" to "vivo"))
        }
        create("oppo") {
            dimension = "channel"
            applicationId = "top.sacz.flavors.oppo"
            manifestPlaceholders.putAll(mapOf("CHANNEL_VALUE" to "open"))
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    debugImplementation(libs.androidx.ui.tooling)
}