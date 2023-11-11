# Smart_Immo

[![Build](https://github.com/FFN-Team/Smart_Immo/actions/workflows/github-ci.yml/badge.svg?branch=main)](https://github.com/FFN-Team/Smart_Immo/actions/workflows/github-ci.yml)
[![License](https://img.shields.io/github/license/FFN-Team/Smart_Immo.svg?style=flat-square)](LICENSE) ![GitHub tag (with filter)](https://img.shields.io/github/v/tag/FFN-Team/Smart_Immo) [![Release](https://img.shields.io/github/release/FFN-Team/Smart_Immo.svg?style=flat-square)](smartimmo/pom.xml) [![(Pre-)Release](https://img.shields.io/github/release/FFN-Team/Smart_Immo/all.svg?label=(pre-)release&style=flat-square)](smartimmo/pom.xml)

[![Test Coverage](https://api.codeclimate.com/v1/badges/a0cb328c06615e126de9/test_coverage)](https://codeclimate.com/repos/6526cea126b84700f171f099/test_coverage)[![Maintainability](https://api.codeclimate.com/v1/badges/a0cb328c06615e126de9/maintainability)](https://codeclimate.com/repos/6526cea126b84700f171f099/maintainability)

# To do before setting up your development environment

- install [Java 21](https://jdk.java.net/java-se-ri/21)
- install [Docker](https://docs.docker.com/engine/install/)
- install [Docker Compose](https://docs.docker.com/engine/install/) 
- install your favorite IDE (IntelliJ, Eclipse, Visual Source Code, ...)

# How to setup your local development environment

## Setting up Docker

- run [docker-compose.yml](docker-compose.yml)

## Setting up TechnImmo
- run `mvn clean install`
- run `mvn flyway:migrate` to migrate data

# Run the application in local 

- use your IDE to create a run configuration from [SmartimmoApplication.java](smartimmo/src/main/java/com/gangdestrois/smartimmo/SmartimmoApplication.java)
- use profile `log-txt` to prevent from logging in json

# Testing 

### Unit tests

- run `mvn test`

### All tests 

- run `mvn clean verify`
