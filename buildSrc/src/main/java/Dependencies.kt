object Dependencies {

    object Compose {
        private const val composeBomVersion = "2023.06.01"
        const val composeVersion = "1.5.1"

        const val bom = "androidx.compose:compose-bom:$composeBomVersion"
        const val ui = "androidx.compose.ui:ui"
        const val uiGraphics = "androidx.compose.ui:ui-graphics"
        const val material3 = "androidx.compose.material3:material3"

        const val navigation = "androidx.navigation:navigation-compose:2.5.3"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"

        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val tooling = "androidx.compose.ui:ui-tooling"
        const val manifest = "androidx.compose.ui:ui-test-manifest"
    }

    object Lifecycle {
        const val core = "androidx.core:core-ktx:1.10.1"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
        const val activityCompose = "androidx.activity:activity-compose:1.7.2"
    }

    object Testing {
        const val junit = "junit:junit:4.13.2"
        const val androidJunit = "androidx.test.ext:junit:1.1.5"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4"
    }

    object Hilt {
        private const val hiltVersion = "2.47"

        const val daggerHiltProject = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val hiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
    }

    object Accompanist {
        private const val accompanistVersion = "0.31.2-alpha"

        const val animationNavigation =
            "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion"

        const val systemUiController =
            "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
    }

    object Paging {
        private const val pagingVersion = "3.1.1"

        const val common = "androidx.paging:paging-common-ktx:$pagingVersion"
        const val compose = "androidx.paging:paging-compose:1.0.0-alpha17"
    }

    object Room {
        private const val roomVersion = "2.5.2"

        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomPaging = "androidx.room:room-paging:$roomVersion"
    }

    object Landscapist {
        private const val landscapistVersion = "2.1.0"

        const val bom = "com.github.skydoves:landscapist-bom:$landscapistVersion"
        const val coil = "com.github.skydoves:landscapist-coil"
    }

    object Gson {
        const val gson = "com.google.code.gson:gson:2.10.1"
    }
}