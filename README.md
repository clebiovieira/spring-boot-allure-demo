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

## GitHub Actions Integration

This project includes GitHub Actions workflows for automated testing and Allure report generation:

### 🚀 CI Workflow (`.github/workflows/ci.yml`)

Automatically runs on push/PR to main branch:
- Executes all tests
- Generates Allure reports
- Uploads artifacts (results and reports)
- Deploys to GitHub Pages (main branch only)

### 📊 Viewing Allure Reports in GitHub Actions

#### Option 1: GitHub Pages (Recommended)
1. Enable GitHub Pages in repository settings
2. Push to main branch
3. Access reports at: `https://[username].github.io/[repository]/allure-report`

#### Option 2: Download Artifacts
1. Go to Actions tab in your repository
2. Click on a workflow run
3. Download "allure-report" artifact
4. Extract and open `index.html` in browser

#### Option 3: Using GitHub CLI (Advanced)
```bash
# Install GitHub CLI
brew install gh

# Authenticate
gh auth login

# Use the provided script
./scripts/download-allure-report.sh [owner] [repo] [run-id]

# Example:
./scripts/download-allure-report.sh myuser spring-boot-allure-demo 1234567890
```

### 📋 GitHub Actions Setup Steps

1. **Enable GitHub Pages**:
   - Go to Settings → Pages
   - Source: GitHub Actions
   - No additional configuration needed

2. **Push your code**:
   ```bash
   git add .
   git commit -m "Add Allure reporting with GitHub Actions"
   git push origin main
   ```

3. **View Results**:
   - Check Actions tab for workflow status
   - Access reports via GitHub Pages URL
   - Download artifacts if needed

### 🔧 Workflow Features

- **Caching**: Maven dependencies cached for faster builds
- **Artifacts**: Both raw results and generated reports saved
- **Retention**: Artifacts kept for 30 days
- **Conditional Deploy**: Only deploys to Pages from main branch
- **Always Upload**: Artifacts uploaded even if tests fail

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