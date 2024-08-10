set fallback := true

_targets:
  @just --list --unsorted --list-heading $'Available targets:\n' --list-prefix "  "

# updates the top-level flake lock file
@update-flake:
  nix flake update --commit-lock-file --commit-lockfile-summary "update Nix flake inputs"

# upgrades dependencies across all Gradle projects
@versions:
  ./gradlew refreshVersionsMigrate --mode=VersionsPropertiesOnly && ./gradlew refreshVersions

# compiles programs as native images placed under build/native/nativeCompile/
@images:
  ./gradlew nativeCompile

# prints names and version numbers of the shared libraries for a given program
@libs program:
  file build/native/nativeCompile/{{program}}
  otool -L build/native/nativeCompile/{{program}}

# prints names and version numbers of the shared libraries for a given program
@run program:
  build/native/nativeCompile/{{program}}
