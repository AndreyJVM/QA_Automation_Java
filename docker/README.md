## Test Automation Framework with Docker
This repository contains a Java-based test automation framework with examples of how to use Docker to create isolated environments for running tests. Docker helps ensure consistency across different environments and simplifies the setup process.

### Prerequisites
- Docker installed on your machine. [Install Docker](https://docs.docker.com/get-started/get-docker/)
- Java Development Kit (JDK) 11 or higher.
- Maven or Gradle for dependency management.

### Docker Examples
This example demonstrates how to run your test suite inside a Docker container.

Create a `Dockerfile` in the root of your project:

```dockerfile
# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . /app

# Build the project using Maven
RUN mvn clean install

# Command to run the tests
CMD ["mvn", "test"]
```

Build and Run the Docker Container

1. Build the Docker image:
```shell
docker build -t java-test-automation .
```
2. Run the tests inside the container:
```shell
docker run --rm java-test-automation
```
The `--rm` flag removes the container after the tests are completed.

---

### Using Docker Compose for Multi-Container Setup
If your tests require additional services (e.g., a database, Selenium Grid, or a web server), you can use Docker Compose to manage multiple containers.

Create a `docker-compose.yml` file:

```dockerfile
version: '3.8'

services:
  selenium-hub:
    image: selenium/hub:4.1.0
    ports:
      - "4444:4444"

  chrome:
    image: selenium/node-chrome:4.1.0
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443

  test-automation:
    build: .
    depends_on:
      - selenium-hub
    environment:
      - SELENIUM_HUB_URL=http://selenium-hub:4444
```
**Run the Setup**
1. Build and start the containers:
```shell
docker-compose up --build
```
2. The tests will run automatically in the `test-automation` container.
3. To stop and remove the containers:
```shell
docker-compose down
```

---

### Running Tests with a Headless Browser
If you want to run tests in a headless browser, you can modify the Dockerfile or Docker Compose setup to include a headless browser configuration.

```dockerfile
FROM openjdk:11-jdk-slim

WORKDIR /app

COPY . /app

RUN mvn clean install

# Install Chrome and ChromeDriver
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    gnupg \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    && wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$(curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE)/chromedriver_linux64.zip \
    && unzip /tmp/chromedriver.zip -d /usr/local/bin/ \
    && rm /tmp/chromedriver.zip

CMD ["mvn", "test", "-Dchrome.options.headless=true"]
```

