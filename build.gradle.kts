plugins {
    kotlin("jvm")
    id("org.graalvm.buildtools.native")
}

repositories {
    mavenCentral()
}

tasks.wrapper {
    gradleVersion = "8.8-rc-2"
    distributionSha256Sum = "8cc380c27207423e8d0b2feeedca5f52c269aa52f797f5b5ea7cb5d101713329"
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
                // prepare for the next GraalVM release
                add("--strict-image-heap")
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
                languageVersion = JavaLanguageVersion.of(21)
                vendor = JvmVendorSpec.GRAAL_VM
            }
        }
    }
}
