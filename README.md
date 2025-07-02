# ddd-banking-example

## Build and Test

This repository contains several versions of the same project. Just change into the version you want to work and and build with Maven.

On Windows:

```powershell
cd "1 Hands-on Legacy Code"
./mvnw.cmd package
```

On Unix/Linux/MacOS:

```sh
cd "1 Hands-on Legacy Code"
./mvnw package
```

## Containerized Version

The easiest way is to use open the project in Visual Studio Code inside a Devcontainer. For that you need Docker installed.

### Install Docker Desktop

On Windows:

```powershell
winget install -e --id Docker.DockerDesktop
```

On MacOS:

```sh
brew install --cask docker
```

## On Your Own System

You need a JDK version 21.

### Install prerequisites

On Windows:

```powershell
winget install -e --id EclipseAdoptium.Temurin.21.JDK
```

On MacOS:

```sh
brew install temurin@21
```
