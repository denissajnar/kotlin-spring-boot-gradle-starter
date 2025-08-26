# Kotlin Spring Boot Gradle Starter

[![Kotlin](https://img.shields.io/badge/kotlin-2.2.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Spring Boot](https://img.shields.io/badge/spring--boot-4.0.0--SNAPSHOT-brightgreen.svg?logo=springboot)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/java-24-blue.svg?logo=openjdk)](https://openjdk.org/)
[![Gradle](https://img.shields.io/badge/gradle-wrapper-blue.svg?logo=gradle)](https://gradle.org/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

A multi-module Kotlin Spring Boot starter template with build conventions, code quality tools, and container support
configured but ready for your implementation.

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Prerequisites](#-prerequisites)
- [Quick Start](#-quick-start)
- [Project Structure](#-project-structure)
- [Development](#-development)
- [Testing](#-testing)
- [Code Quality](#-code-quality)
- [Container Support](#-container-support)
- [CI/CD](#-cicd)
- [Documentation](#-documentation)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

- **🏗️ Multi-Module Architecture** - Clean separation of concerns with api, app, common, domain, and persistence modules
- **🔧 Convention Plugins** - Reusable build logic for consistent configuration across modules
- **✅ Code Quality Tools** - Pre-configured KtLint, Spotless, and Kover for formatting and coverage
- **🔒 Security Scanning** - OWASP dependency vulnerability detection configured
- **📦 Container Support** - Paketo buildpacks with JVM and GraalVM native image support
- **🌱 Spring Boot Starter Dependencies** - Web, WebFlux, Security, Actuator, and JPA starters included
- **📖 Documentation** - Dokka configured for API documentation generation
- **🔄 Dependency Management** - Automated dependency updates tracking with ben-manes plugin

## 🛠 Technology Stack

| Technology        | Version        | Description                  |
|-------------------|----------------|------------------------------|
| Kotlin            | 2.2.0+         | Primary programming language |
| Spring Boot       | 4.0.0-SNAPSHOT | Application framework        |
| Java              | 24+            | Runtime environment          |
| Gradle            | 9.0+           | Build automation             |
| Paketo Buildpacks | Latest         | Container image building     |
| GraalVM           | Latest         | Native image compilation     |

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **JDK 24** or higher (Bellsoft Liberica recommended)
  ```bash
  java -version
  ```

- **Docker** (for container builds)
  ```bash
  docker --version
  ```

- **Gradle 9.0+** (or use the wrapper)
  ```bash
  ./gradlew --version
  ```

## 🚀 Quick Start

### 1. Clone the repository

```bash
git clone https://github.com/denissajnar/kotlin-spring-boot-gradle-starter.git
cd kotlin-spring-boot-gradle-starter
```

### 2. Build the project

```bash
./gradlew build
```

### 3. Run the application

```bash
./gradlew :app:bootRun
```

The application will start and be available at:

- Application: http://localhost:8080
- Actuator endpoints: http://localhost:8080/actuator
- Health check: http://localhost:8080/actuator/health

Note: This is a minimal starter application. You'll need to implement your business logic and endpoints.

### 4. Build container image

```bash
# JVM-based image
./gradlew :app:bootBuildImage

# Native image
./gradlew :app:bootBuildImage -Pnative
```

## 📁 Project Structure

```
.
├── build-logic/            # Convention plugins (composite build)
│   └── src/main/kotlin/    # Build conventions
├── api/                    # API interfaces and DTOs
├── app/                    # Spring Boot application
├── common/                 # Shared utilities and constants
├── domain/                 # Business logic and domain models
├── persistence/            # Data access layer
└── gradle/                 # Gradle wrapper and configuration
```

### Module Dependencies

```mermaid
graph TD
    app[app] --> api
    app --> domain
    app --> persistence
    app --> common
    domain --> api
    domain --> common
    persistence --> domain
    persistence --> common
    api --> common
```

## 💻 Development

### Running locally

```bash
# Run the application
./gradlew :app:bootRun

# With specific profile
./gradlew :app:bootRun --args='--spring.profiles.active=local'
```

### Code formatting

```bash
# Check formatting
./gradlew spotlessCheck

# Apply formatting
./gradlew spotlessApply

# KtLint format
./gradlew ktlintFormat
```

## 🧪 Testing

### Unit tests

```bash
./gradlew test
```

### Test coverage

```bash
# Run tests with coverage
./gradlew koverVerify

# Generate HTML report
./gradlew koverHtmlReport
# Open: build/reports/kover/html/index.html
```

## ✅ Code Quality

### Quality checks

Run individual quality checks:

```bash
# Code style checks
./gradlew ktlintCheck

# Formatting validation
./gradlew spotlessCheck

# Test coverage verification
./gradlew koverVerify

# Unit tests
./gradlew test
```

### Security scanning

```bash
# Check for vulnerable dependencies
./gradlew dependencyCheckAnalyze

# Check for outdated dependencies
./gradlew showOutdatedDependencies
```

### Generate quality reports

```bash
./gradlew generateQualityReports
```

Reports will be available in:

- Coverage: `build/reports/kover/html/index.html`
- Dependencies: `build/reports/dependency-updates/dependency-updates-report.html`
- Security: `build/reports/dependency-check/dependency-check-report.html`

## 🐳 Container Support

### Building images

```bash
# JVM-based container (optimized with Paketo buildpacks)
./gradlew :app:bootBuildImage

# Native container (GraalVM)
./gradlew :app:bootBuildImage -Pnative

# Custom registry
./gradlew :app:bootBuildImage \
  --imageName=registry.example.com/myapp:latest
```

### Running containers

```bash
# Run JVM container
docker run -p 8080:8080 com.template/app:0.0.1-SNAPSHOT

# Run with environment variables
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SERVER_PORT=8080 \
  com.template/app:0.0.1-SNAPSHOT
```

## 🔄 CI/CD

This starter template doesn't include pre-configured CI/CD workflows. You can set up your own CI/CD pipeline using:

### Recommended CI/CD Tools

- **GitHub Actions** - For GitHub repositories
- **GitLab CI** - For GitLab repositories
- **Jenkins** - For enterprise environments
- **CircleCI** - Alternative CI/CD platform

### Suggested CI/CD Pipeline

Your pipeline should include:

- Code quality checks: `./gradlew spotlessCheck ktlintCheck`
- Tests: `./gradlew test`
- Coverage verification: `./gradlew koverVerify`
- Security scanning: `./gradlew dependencyCheckAnalyze`
- Container image building: `./gradlew :app:bootBuildImage`

### Environment Variables

For production deployments, consider these environment variables:

| Variable                 | Description                    | Usage                   |
|--------------------------|--------------------------------|-------------------------|
| `NVD_API_KEY`            | OWASP dependency check API key | Optional (faster scans) |
| `SPRING_PROFILES_ACTIVE` | Spring Boot profiles           | Runtime configuration   |

## 📖 Documentation

### API Documentation

Generate API documentation:

```bash
# Multi-module documentation
./gradlew dokkaHtmlMultiModule

# Module-specific
./gradlew :api:dokkaHtml
```

View documentation:

- Open `build/dokka/index.html`

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

### Development setup

1. Fork the repository
2. Create a feature branch
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. Make your changes
4. Run quality checks
   ```bash
   ./gradlew ktlintCheck spotlessCheck koverVerify test
   ```
5. Commit your changes
   ```bash
   git commit -m 'Add amazing feature'
   ```
6. Push to your fork
   ```bash
   git push origin feature/amazing-feature
   ```
7. Open a Pull Request

### Code style

This project uses:

- [KtLint](https://pinterest.github.io/ktlint/) for Kotlin linting
- [Spotless](https://github.com/diffplug/spotless) for code formatting
- 4 spaces for indentation
- 120 character line limit

## 📊 Getting Started Tips

### Build Commands

| Task                     | Command                                  |
|--------------------------|------------------------------------------|
| Clean build              | `./gradlew clean build`                  |
| Run application          | `./gradlew :app:bootRun`                 |
| Run tests                | `./gradlew test`                         |
| Code formatting          | `./gradlew spotlessApply`                |
| Security scan            | `./gradlew dependencyCheckAnalyze`       |
| Container build (JVM)    | `./gradlew :app:bootBuildImage`          |
| Container build (Native) | `./gradlew :app:bootBuildImage -Pnative` |

### Next Steps

1. Implement your business logic in the `domain` module
2. Add REST controllers in the `api` module
3. Configure data access in the `persistence` module
4. Add shared utilities in the `common` module
5. Set up your CI/CD pipeline
6. Configure production environment settings

## 🔗 Resources

- [Kotlin Documentation](https://kotlinlang.org/docs)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/)
- [Gradle User Manual](https://docs.gradle.org/current/userguide/)
- [Paketo Buildpacks](https://paketo.io/)
- [GraalVM Native Image](https://www.graalvm.org/native-image/)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- JetBrains for Kotlin and IntelliJ IDEA
- Gradle team for the build tool
- Paketo buildpacks community
- All contributors and maintainers

---

<div align="center">
  <sub>Built with ❤️ using Kotlin and Spring Boot</sub>
</div>