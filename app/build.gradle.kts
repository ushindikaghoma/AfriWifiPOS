plugins {
    id("com.android.application")
}

android {
    namespace = "com.soft.afri_wifi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.soft.afri_wifi"
        minSdk = 24
        targetSdk = 34
        versionCode = 6
        versionName = "1.4"
        multiDexEnabled = true


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

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation ("com.squareup.retrofit2:retrofit:2.7.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.7.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.4.0")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.7.3")
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.google.android.play:app-update:2.1.0")
//    implementation ("com.google.android.play:core:1.10.3")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}