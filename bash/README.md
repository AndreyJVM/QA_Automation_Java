### 1. Advanced Project Build with Maven/Gradle
This script includes conditional logic to handle different build profiles and environments.

```shell
#!/bin/bash

# Set project directory
PROJECT_DIR="/path/to/your/project"
cd "$PROJECT_DIR" || exit 1

# Set build profile based on environment variable
BUILD_PROFILE=${BUILD_PROFILE:-"default"}  # Default profile if not specified

# Build the project using Maven or Gradle
if [[ "$BUILD_TOOL" == "maven" ]]; then
    echo "Building with Maven using profile: $BUILD_PROFILE"
    mvn clean install -P "$BUILD_PROFILE"
elif [[ "$BUILD_TOOL" == "gradle" ]]; then
    echo "Building with Gradle using profile: $BUILD_PROFILE"
    ./gradlew clean build -Pprofile="$BUILD_PROFILE"
else
    echo "Error: BUILD_TOOL must be 'maven' or 'gradle'."
    exit 1
fi

# Check build status
if [ $? -eq 0 ]; then
    echo "Build completed successfully."
else
    echo "Build failed. Exiting."
    exit 1
fi
```

### 2. Parallel Test Execution with Maven Surefire
This script runs tests in parallel using Maven Surefire and dynamically allocates threads based on available CPU cores.

```shell
#!/bin/bash

# Set project directory
PROJECT_DIR="/path/to/your/project"
cd "$PROJECT_DIR" || exit 1

# Calculate number of threads for parallel execution (based on CPU cores)
THREAD_COUNT=$(nproc)
echo "Running tests in parallel with $THREAD_COUNT threads."

# Run tests using Maven Surefire
mvn surefire:test -Dparallel=classes -DthreadCount=$THREAD_COUNT

# Check test execution status
if [ $? -eq 0 ]; then
    echo "All tests passed successfully."
else
    echo "Some tests failed. Check the reports for details."
    exit 1
fi
```

### 3. Dynamic Environment Configuration
This script dynamically configures the environment for testing (e.g., staging, production) and injects environment variables.

```shell
#!/bin/bash

# Set environment (default to staging if not provided)
ENVIRONMENT=${ENVIRONMENT:-"staging"}

# Load environment-specific configuration
case "$ENVIRONMENT" in
    "staging")
        export DB_URL="jdbc:mysql://staging-db:3306/testdb"
        export DB_USER="staging_user"
        export DB_PASSWORD="staging_password"
        ;;
    "production")
        export DB_URL="jdbc:mysql://production-db:3306/proddb"
        export DB_USER="prod_user"
        export DB_PASSWORD="prod_password"
        ;;
    *)
        echo "Unknown environment: $ENVIRONMENT. Exiting."
        exit 1
        ;;
esac

echo "Running tests in $ENVIRONMENT environment."
mvn test

# Check test results
if [ $? -eq 0 ]; then
    echo "Tests completed successfully in $ENVIRONMENT environment."
else
    echo "Tests failed in $ENVIRONMENT environment."
    exit 1
fi
```

### 4. Test Report Aggregation and Analysis
This script aggregates test results from multiple modules and analyzes them for failures.

```shell
#!/bin/bash

# Set project directory
PROJECT_DIR="/path/to/your/project"
cd "$PROJECT_DIR" || exit 1

# Run tests and generate reports
mvn test

# Aggregate test results from all modules
REPORT_DIR="$PROJECT_DIR/target/surefire-reports"
FAILED_TESTS=$(grep -h "FAILURE" "$REPORT_DIR"/*.txt)

# Analyze and report failures
if [[ -z "$FAILED_TESTS" ]]; then
    echo "All tests passed successfully."
else
    echo "The following tests failed:"
    echo "$FAILED_TESTS"
    exit 1
fi
```

### 5. Integration with CI/CD Pipeline
This script integrates with a CI/CD pipeline, including Git operations, build, and test execution.

```shell
#!/bin/bash

# Set project directory
PROJECT_DIR="/path/to/your/project"
cd "$PROJECT_DIR" || exit 1

# Pull latest changes from Git
echo "Pulling latest changes from Git..."
git pull origin main

# Build the project
echo "Building the project..."
mvn clean install

# Check build status
if [ $? -ne 0 ]; then
    echo "Build failed. Exiting."
    exit 1
fi

# Run tests
echo "Running tests..."
mvn test

# Check test results
if [ $? -eq 0 ]; then
    echo "All tests passed successfully."
else
    echo "Some tests failed. Exiting."
    exit 1
fi

# Deploy to staging (optional)
echo "Deploying to staging..."
mvn deploy -DskipTests
```

### 6. Dockerized Test Execution
This script builds a Docker image for the application and runs tests inside a container.

```shell
#!/bin/bash

# Set project directory
PROJECT_DIR="/path/to/your/project"
cd "$PROJECT_DIR" || exit 1

# Build Docker image
echo "Building Docker image..."
docker build -t my-java-app .

# Run tests inside Docker container
echo "Running tests in Docker container..."
docker run --rm my-java-app mvn test

# Check test results
if [ $? -eq 0 ]; then
    echo "All tests passed successfully in Docker container."
else
    echo "Some tests failed in Docker container."
    exit 1
fi
```

### 7. Advanced Logging and Artifact Archiving
This script adds advanced logging and archives test artifacts (e.g., reports, logs) for later analysis.

```shell
#!/bin/bash

# Set project directory and log file
PROJECT_DIR="/path/to/your/project"
LOG_FILE="$PROJECT_DIR/test_execution.log"
cd "$PROJECT_DIR" || exit 1

# Redirect all output to log file
exec > >(tee -a "$LOG_FILE") 2>&1

# Run tests
echo "Starting test execution at $(date)"
mvn test

# Archive test artifacts
echo "Archiving test artifacts..."
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")
ARTIFACT_DIR="$PROJECT_DIR/target/artifacts_$TIMESTAMP"
mkdir -p "$ARTIFACT_DIR"
cp -r "$PROJECT_DIR/target/surefire-reports" "$ARTIFACT_DIR"
cp "$LOG_FILE" "$ARTIFACT_DIR"

echo "Test artifacts archived in $ARTIFACT_DIR."
```

### 8. Email Notification for Test Results
This script sends an email notification with test results.

```shell
#!/bin/bash

# Set project directory
PROJECT_DIR="/path/to/your/project"
cd "$PROJECT_DIR" || exit 1

# Run tests
mvn test

# Check test results and send email
if [ $? -eq 0 ]; then
    SUBJECT="Test Execution: SUCCESS"
    BODY="All tests passed successfully."
else
    SUBJECT="Test Execution: FAILURE"
    BODY="Some tests failed. Please check the reports."
fi

# Send email
echo "$BODY" | mail -s "$SUBJECT" team@example.com
```