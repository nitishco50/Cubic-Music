plugins {
    kotlin("jvm")
}

sourceSets.all {
    java.srcDir("src/$name/kotlin")
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.serialization.json)
}
