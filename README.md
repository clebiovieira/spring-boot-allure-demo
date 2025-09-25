# Spring Boot 3 with Allure Reporting Demo

This project demonstrates how to use Allure for test reporting in a Spring Boot 3 application with Java 21.

## Project Overview

This is a simple RESTful API for product management with the following features:

- Spring Boot 3.2.3 with Java 21
- RESTful API endpoints for CRUD operations on products
- Unit and integration tests with JUnit 5
- Allure reporting for detailed test reports

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Allure command-line tool (for viewing reports)

## Installing Allure Command Line Tool

### macOS (using Homebrew)
```bash
brew install allure
```

### Windows (using Scoop)
```bash
scoop install allure
```

### Linux
```bash
sudo apt-add-repository ppa:qameta/allure
sudo apt-get update
sudo apt-get install allure
```

## Running the Application

```bash
mvn spring-boot:run
```

The application will start on port 8080.

## API Endpoints

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get a product by ID
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update a product
- `DELETE /api/products/{id}` - Delete a product

## Running Tests

To run the tests and generate Allure report data:

```bash
mvn clean test
```

## Generating and Viewing Allure Reports

### Option 1: Using Maven Plugin

Generate the report:

```bash
mvn allure:report
```

The report will be generated in `target/site/allure-maven-plugin` directory. Open `index.html` in a browser to view it.

### Option 2: Using Allure Command Line

Generate and open the report:

```bash
allure serve target/allure-results
```

This command will generate the report and open it in your default web browser.

## 🚀 GitHub Actions Integration

Este projeto está configurado com GitHub Actions para executar testes automaticamente e gerar relatórios Allure.

### Workflows Disponíveis

1. **CI with Allure Reports** (`ci.yml`): Executa testes e gera artefatos
2. **Deploy Allure Reports by Branch** (`branch-pages.yml`): Deploy de relatórios por branch

### 📊 Visualização de Relatórios por Branch

Os relatórios Allure são automaticamente publicados no GitHub Pages organizados por branch:

- **Página Principal**: https://clebiovieira.github.io/spring-boot-allure-demo/
- **Branch Main**: https://clebiovieira.github.io/spring-boot-allure-demo/main/
- **Branch Develop**: https://clebiovieira.github.io/spring-boot-allure-demo/develop/
- **Feature Branches**: https://clebiovieira.github.io/spring-boot-allure-demo/feature-nome/

### 🔧 Configuração do GitHub Actions

Para habilitar os relatórios automáticos:

1. Vá para **Settings** → **Pages** no seu repositório
2. Em **Source**, selecione **GitHub Actions**
3. Os workflows serão executados automaticamente nos pushes

### ⚡ Funcionalidades dos Workflows

- **Cache do Maven**: Acelera builds subsequentes
- **Upload de Artefatos**: Relatórios disponíveis por 30 dias
- **Deploy Condicional**: Apenas branches específicas são publicadas
- **Estrutura Organizada**: Cada branch tem seu próprio diretório
- **Página de Índice**: Lista todos os relatórios disponíveis

## Allure Features Demonstrated

This project demonstrates the following Allure features:

1. **Basic Annotations**
   - `@Epic`, `@Feature`, `@Story` - For organizing tests hierarchically
   - `@DisplayName`, `@Description` - For providing human-readable test descriptions
   - `@Severity` - For indicating test importance

2. **Advanced Features**
   - Custom steps using `@Step` annotation
   - Attachments for test data and results
   - Parameters for test inputs
   - Links to issues and test management systems
   - Custom test lifecycle listener

3. **REST API Testing**
   - Integration with REST Assured for API testing
   - HTTP request and response logging

## Project Structure

- `src/main/java/com/example/demo` - Application source code
  - `controller` - REST controllers
  - `model` - Data models
  - `service` - Business logic

- `src/test/java/com/example/demo` - Test source code
  - `controller` - Controller tests
  - `service` - Service tests
  - `util` - Test utilities for Allure reporting
  - `AllureFeatureDemoTest.java` - Demonstration of Allure features

## Customizing Allure Reports

You can customize the Allure reports by modifying the `allure-maven` plugin configuration in the `pom.xml` file.

## Additional Resources

- [Allure Framework Documentation](https://docs.qameta.io/allure/)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)