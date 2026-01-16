plugins {
    kotlin("jvm")
    id("org.graalvm.buildtools.native")
}

repositories {
    mavenCentral()
}

tasks.wrapper {
    gradleVersion = "9.3.0"
    distributionSha256Sum = "046f36af261f2c6ed09eef06bf25b93d1f20d5220991bb8a3f08fd5fb6f6629a"
    distributionType = Wrapper.DistributionType.ALL
}

dependencies {
    implementation(Kotlin.stdlib)
}

graalvmNative {
    toolchainDetection = true

    binaries {
        named("main") {
            imageName = "hello"
            mainClass = "MainKt"

            buildArgs.apply {
                // disable native toolchain checking (macOS only?)
                addAll("-H:+UnlockExperimentalVMOptions", "-H:-CheckToolchain")
                // require types to be fully defined at image build-time
                add("--link-at-build-time")
                // enable more CPU features for improved performance
                add("-march=native")
                // all optimisations for best performance
                add("-O3")
                // build stand-alone image or report failure
                add("--no-fallback")
                // heap settings at build time
                add("-R:MinHeapSize=128k")
                add("-R:MaxHeapSize=1m")
                add("-R:MaxNewSize=512k")
                // no need for garbage collection
                add("--gc=epsilon")
                // no need for safepoints
                add("-H:-GenLoopSafepoints")
            }

            quickBuild = false
            richOutput = false
            verbose = false

            javaLauncher = javaToolchains.launcherFor {
                languageVersion = JavaLanguageVersion.of(23)
                vendor = JvmVendorSpec.GRAAL_VM
            }
        }
    }
}
