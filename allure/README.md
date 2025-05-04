# Allure Report

## Allure Report installation
### Install via the system package manager (for Linux)

- Go to the latest Allure Report release on [GitHub](https://github.com/allure-framework/allure2/releases/tag/2.28.0) and download the allure-*.deb
- ```shell
  sudo dpkg -i allure_2.24.0-1_all.deb
  ```

- ```shell
  allure --version
  ```

## Run command to generate a report.

- ```shell
  mvn clean test
  ```
- ```shell
  allure serve target/surefire-reports/
  ```

![img.png](resources/allure_demo.png)


## To create more informative reports, you need to connect Allure to the project.

### To connect Allure to the project, add the following code to pom.xml:

```xml

<properties>
    <!-- версия Aspectj -->
    <aspectj.version>1.9.7</aspectj.version>
    <!-- версия Allure -->
    <allure.version>2.15.0</allure.version>
</properties>

<dependencies>
<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-junit4</artifactId>
    <version>${allure.version}</version>
</dependency>
<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-rest-assured</artifactId>
    <version>${allure.version}</version>
</dependency>
</dependencies>

<build>
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.22.2</version>
        <configuration>
            <testFailureIgnore>false</testFailureIgnore>
            <argLine>
                -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
            </argLine>
            <properties>
                <property>
                    <name>listener</name>
                    <value>io.qameta.allure.junit4.AllureJunit4</value>
                </property>
            </properties>
            <systemProperties>
                <property>
                    <name>allure.results.directory</name>
                    <value>${project.build.directory}/allure-results</value>
                </property>
            </systemProperties>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>${aspectj.version}</version>
            </dependency>
        </dependencies>
    </plugin>
    <plugin>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-maven</artifactId>
        <version>2.10.0</version>
        <configuration>
            <reportVersion>2.15.0</reportVersion>
        </configuration>
    </plugin>
</plugins>
</build>
```

## Annotations

### The @DisplayName annotation allows you to add readable method names.
```java
@Test
@DisplayName("Check status code of /users/me")
```
### The @Description annotation allows you to add a description to a test.
```java
@Test
@DisplayName("Check status code of /users/me")
@Description("Basic test for /users/me endpoint")
```
### The @Step annotation allows you to add descriptions to autotest steps.
```java
@Step("Compare user name to something")
```

## Base command

```shell
allure serve
```