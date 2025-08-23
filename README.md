# Spring Boot Kotlin Multi-Module Template

🚀 A clean, production-ready template for building Spring Boot applications with Kotlin using Gradle's multi-module architecture.

## 🎯 Purpose

This template provides a pragmatic starting point for Spring Boot projects that need clean architecture without over-engineering. It demonstrates module separation, dependency management, and best practices while remaining simple enough to understand and extend.

Perfect for:
- New microservices or APIs
- Modular monoliths
- Teams learning multi-module architecture
- Projects that will grow over time

## ✨ Features

- **Multi-module Gradle setup** with clean separation of concerns
- **Spring Boot 4.x** with Kotlin and Coroutines support
- **Gradle Kotlin DSL** with version catalogs
- **Detekt** for static code analysis and formatting
- **Dokka** for Kotlin API documentation
- **Testing setup** with JUnit 5, MockK, and Spring Boot Test
- **Docker support** with layered JARs
- **GitHub Actions** CI/CD pipeline template
- **Convention plugins** for consistent module configuration

## 📦 Module Structure

- **`common`** - Shared utilities, extensions, and base classes
- **`domain`** - Core business entities, services, and interfaces  
- **`api`** - REST controllers, DTOs, and API documentation
- **`persistence`** - JPA entities, repositories, and database config
- **`app`** - Main Spring Boot application and configuration

### Module Dependencies
```
app → api → domain ← persistence
         ↓           ↓
       common ← ─ ─ ─
```

## 🚀 Quick Start

### Prerequisites
- JDK 24 or higher
- Gradle 9+ (or use the wrapper)

### Clone and Run
```bash
# Clone the template
git clone https://github.com/denissajnar/kotlin-spring-boot-gradle-starter/.git
cd kotlin-spring-boot-gradle-starter/

# Build the project
./gradlew build

# Run tests
./gradlew test

# Run the application
./gradlew :app:bootRun

# Generate documentation
./gradlew dokkaHtmlMultiModule

# Run Detekt analysis
./gradlew detekt
```

### Create Your Project
1. Click "Use this template" on GitHub
2. Rename packages from `com.template` to your domain
3. Update `settings.gradle.kts` with your project name
4. Modify `gradle.properties` with your versions
5. Start building your features!

## 🏗️ Architecture Principles

- **Dependency Rule**: Dependencies flow inward toward the domain
- **Module Isolation**: Each module has a single responsibility
- **Framework Independence**: Domain module has no Spring dependencies
- **Testability**: Each module can be tested independently

## 📈 Scaling the Architecture

As your project grows, you can evolve the structure:

```markdown
Starting Template          →    Growing Project           →    Enterprise Scale
─────────────────               ─────────────────              ─────────────────
common                          platform-core                   platform-core
domain                          domain-model                    platform-commons
api                             domain-service                  platform-test
persistence                     api-rest                        domain-model
app                             api-dto                         domain-port
                                infrastructure-persistence      domain-service
                                infrastructure-client           application-api
                                app                             application-usecase
                                                                infrastructure-*
                                                                bootstrap-app
```

The template provides a solid foundation that can evolve with your needs without starting with unnecessary complexity.

## 🛠️ Configuration

### Detekt Setup
Configuration is in `config/detekt/detekt.yml`. Run analysis:
```bash
./gradlew detekt                    # Analyze
./gradlew detektFormat              # Auto-format
```

### Dokka Documentation
Generate API documentation:
```bash
./gradlew dokkaHtml                 # Single module
./gradlew dokkaHtmlMultiModule      # All modules
```
Documentation is generated in `build/dokka/`

### Version Management
Dependencies are managed via `gradle/libs.versions.toml`:
```toml
[versions]
spring-boot = "4.0.0-SNAPSHOT"
kotlin = "2.2.0"
detekt = "1.23.8"
dokka = "2.0.0"

[libraries]
spring-boot-starter-web = { module = "org.springframework.boot:spring-boot-starter-web" }
```

## 📝 Project Structure Example

```
├── gradle/
│   ├── libs.versions.toml         # Version catalog
│   └── wrapper/                   # Gradle wrapper
├── build-logic/                       # Convention plugins
│   └── src/main/kotlin/
│       ├── buildlogic.kotlin-application-conventions.gradle.kts
│       └── buildlogic.kotlin-common-conventions.gradle.kts
│       └── buildlogic.kotlin-library-conventions.gradle.kts
├── config/
│   └── detekt/
│       └── detekt.yml             # Detekt configuration
├── common/
│   └── src/main/kotlin/...       # Shared code
├── domain/
│   └── src/main/kotlin/...       # Business logic
├── api/
│   └── src/main/kotlin/...       # REST endpoints
├── persistence/
│   └── src/main/kotlin/...       # Database layer
├── app/
│   └── src/main/kotlin/...       # Main application
├── build.gradle.kts               # Root build file
└── settings.gradle.kts            # Project settings
```

## 🧪 Testing Strategy

Each module includes appropriate test types:
- **`domain`** - Unit tests with no Spring context
- **`api`** - `@WebMvcTest` for controllers
- **`persistence`** - `@DataJpaTest` for repositories
- **`app`** - `@SpringBootTest` for integration tests
- **`common`** - Pure unit tests

## 🐳 Docker Support

Build and run with Docker:
```bash
# Build image using Spring Boot's buildpacks
./gradlew :app:bootBuildImage

# Or use the included Dockerfile
docker build -t my-app .
docker run -p 8080:8080 my-app
```

## 📚 Technology Stack

- **Kotlin** 2.2.0
- **Spring Boot** 4.0.0-SNAPSHOT
- **Gradle** 9 with Kotlin DSL
- **Detekt** for code analysis
- **Dokka** for documentation
- **JUnit 5** & **MockK** for testing
- **H2** for development database
- **PostgreSQL** for production (configurable)

## 🤝 Contributing

This is a template repository. Feel free to:
1. Use it as a starting point for your projects
2. Submit issues for bugs or suggestions
3. Create PRs for improvements
4. Fork and customize for your needs

## 📄 License

MIT License - Use this template however you want!

## 🔗 Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Kotlin Documentation](https://kotlinlang.org/docs)
- [Gradle Documentation](https://docs.gradle.org)
- [Detekt Rules](https://detekt.dev/docs/rules/ruleset)
- [Dokka Documentation](https://kotlinlang.org/docs/dokka-introduction.html)

---

**Created with ❤️ for the Kotlin/Spring Boot community**
