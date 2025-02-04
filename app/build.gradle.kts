plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.atenea.unaltodosalau.crudsqlite"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.atenea.unaltodosalau.crudsqlite"
        minSdk = 31
        //noinspection ExpiredTargetSdkVersion
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.room:room-runtime:2.4.3")
    annotationProcessor("androidx.room:room-compiler:2.4.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.3")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}