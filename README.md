# Kotlin/JVM & GraalVM playground

[![Nix flake](https://img.shields.io/badge/Built_with-macOS%2014.5-43329C.svg?style=flat&logo=nixos&logoColor=white&label=Flake%20used%20on&labelColor=5277C3&suffix=macOS%2014.5)](https://nix.dev/concepts/flakes)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.0-blue.svg?logo=kotlin&style=flat&labelColor=30373d)](https://kotlinlang.org)
[![GraalVM](https://img.shields.io/badge/GraalVM%20CE-21.0.1-blue.svg?style=flat&labelColor=30373d)](https://www.graalvm.org/jdk21/docs/)
[![Gradle](https://img.shields.io/badge/Gradle-8.8_RC_1-blue.svg?style=flat&labelColor=30373d)](https://gradle.org/release-candidate/)
[![Apache License 2.0](https://img.shields.io/badge/License-MIT-blue.svg?style=flat&labelColor=30373d)](https://github.com/franckrasolo/kotlin-graalvm-playground/blob/trunk/LICENSE)

This playgound includes a Nix flake providing the
[GraalVM Native Image](https://www.graalvm.org/jdk21/reference-manual/native-image/)
toolchain to produce Kotlin/JVM executables.

These executables are currently built using the official
[Gradle plugin](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
from GraalVM.

In a future iteration, we'll attempt to build them with these Bazel rules:
- [bazelbuild/rules_kotlin](https://github.com/bazelbuild/rules_kotlin)
- [sgammon/rules_graalvm](https://github.com/sgammon/rules_graalvm)
