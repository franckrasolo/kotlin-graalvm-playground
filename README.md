# Kotlin/JVM & GraalVM playground

[![Nix flake](https://img.shields.io/badge/Built_with-macOS%2015.4.1-43329C.svg?style=flat&logo=nixos&logoColor=white&label=Flake%20used%20on&labelColor=5277C3&suffix=macOS%2015.4.1)](https://nix.dev/concepts/flakes)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.21-blue.svg?logo=kotlin&style=flat&labelColor=30373d)](https://kotlinlang.org)
[![GraalVM](https://img.shields.io/badge/GraalVM%20CE-23.0.2-blue.svg?style=flat&labelColor=30373d)](https://www.graalvm.org/latest/docs/)
[![Gradle](https://img.shields.io/badge/Gradle-8.14.1-blue.svg?style=flat&labelColor=30373d)](https://docs.gradle.org/8.14.1/release-notes.html)
[![Apache License 2.0](https://img.shields.io/badge/License-MIT-blue.svg?style=flat&labelColor=30373d)](https://github.com/franckrasolo/kotlin-graalvm-playground/blob/trunk/LICENSE)

This playground includes a Nix flake providing the
[GraalVM Native Image](https://www.graalvm.org/jdk21/reference-manual/native-image/)
toolchain to produce Kotlin/JVM executables.

These executables are currently built using the official
[Gradle plugin](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
from GraalVM.

In a future iteration, we'll attempt to build them with these Bazel rules:

- [bazelbuild/rules_kotlin](https://github.com/bazelbuild/rules_kotlin)
- [sgammon/rules_graalvm](https://github.com/sgammon/rules_graalvm)
