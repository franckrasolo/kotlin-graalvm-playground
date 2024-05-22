{
  description = "Nix flake for kotling-graalvm-playground";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";
    nixpkgs-stable.url = "github:NixOS/nixpkgs/nixos-23.11";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, nixpkgs-stable, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system: let
      pkgs = nixpkgs.legacyPackages.${system};
      graalvm-21 = nixpkgs-stable.legacyPackages.${system}.graalvm-ce;
    in with pkgs; {
      devShells.default = mkShell {
        packages = [
          direnv
          graalvm-21
          just
        ];

        # Workaround for a GraalVM issue where the builder no longer has access to
        # environment variables since 21.0.0
        #
        # https://github.com/oracle/graal/pull/6095
        # https://github.com/oracle/graal/issues/7502
        env.NATIVE_IMAGE_DEPRECATED_BUILDER_SANITATION = "true";

        shellHook = ''
          # health checks for Nix flake inputs
          nix run "github:DeterminateSystems/flake-checker"
        '';
      };
    }
  );
}
