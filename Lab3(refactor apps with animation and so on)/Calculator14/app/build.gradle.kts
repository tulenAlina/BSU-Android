plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.calculator14"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.calculator14"
        minSdk = 21
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // JUnit для модульных тестов
    testImplementation(libs.junit)

    // AndroidX JUnit и Espresso для UI-тестов
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Дополнительные компоненты для Espresso
    androidTestImplementation(libs.androidx.espresso.contrib) // Для работы с RecyclerView, DatePicker и др.
    androidTestImplementation(libs.androidx.espresso.intents) // Для тестирования интентов
    androidTestImplementation(libs.androidx.espresso.idling.resource) // Для тестов с асинхронными операциями

    // Правила и Runner для AndroidX тестов
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)

    // Mockito для создания моков в JUnit-тестах
    testImplementation("org.mockito:mockito-core:5.11.0")
    androidTestImplementation("org.mockito:mockito-android:5.11.0")

    // Core Testing (например, для ViewModel и LiveData)
    testImplementation(libs.androidx.arch.core.testing)
}
