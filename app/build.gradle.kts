import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.module2"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.example.module2"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures { compose = true }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    // те же ui тесты из androidTest гоняем локально через robolectric
    sourceSets.getByName("test").java.srcDir("src/androidTest/java")
}
kotlin { compilerOptions { jvmTarget = JvmTarget.JVM_17 } }

dependencies {
    val bom = platform("androidx.compose:compose-bom:2025.06.01")
    implementation(bom)
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    androidTestImplementation(bom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")

    testImplementation(bom)
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.15.1")
    testImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("androidx.test.ext:junit:1.2.1")
}
