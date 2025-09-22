{
  description = "Nix flake for kotling-graalvm-playground";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    nixpkgs-stable.url = "github:NixOS/nixpkgs/nixos-25.05";
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
          graalvmPackages.graalvm-ce
          just
          tokei
        ];

        shellHook = ''
          # health checks for Nix flake inputs
          nix run "github:DeterminateSystems/flake-checker"
        '';
      };
    }
  );
}
