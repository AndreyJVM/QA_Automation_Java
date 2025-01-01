# **Selenium WedDriver** #
![selenium-java-1.jpg](src/main/resources/images/selenium-java-1.jpg)
Доброго времени суток, меня зовут Андрей Воробьёв, решил собрать и структурировать информацию о Selenium
WebDriver, которую разобью на несколько статей. Начнём с самого простого и закончим написанием полноценных
тестов с помощью Selenium WebDriver.

`Используется следующий стек:`

![Linkedin-url](https://img.shields.io/badge/Java-_11-red)

![Linkedin-url](https://img.shields.io/badge/Maven-version_4.0.0-blue)
![Linkedin-url](https://img.shields.io/badge/JUnit_4-version_4.13.2-blue)
![Linkedin-url](https://img.shields.io/badge/Selenium_Java-version_4.10.0-blue)

Основные источники:
- Selenium Testing Tools Cookbook [Unmesh Gundecha](https://github.com/upgundecha)
- Официальная документация [Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/).

Начнём с начала, что такое Selenium WebDriver? Чем он нам поможет в автоматизированном тестировании?

**Selenium WebDriver** - это инструмент для автоматизации действий веб-браузера.
*В большинстве случаев используется для тестирования Web-приложений*.
В частности, он может быть использован *для решения рутинных задач администрирования
сайта* или регулярного получения данных из различных источников (сайтов).

Так, теперь по порядку. Зачем нам вообще автоматизировать наши действия? Что мы так, не справимся?
Конечно же справимся, не зря же твоему другу *Чебурашка* (ручному тестировщику) платят денежку, думаю
не зря. К вопросу, как лучше тестировать, мы ещё вернемся, а пока сосредоточимся на автоматизации наших действий.

Программисты и тестировщики не единственные в своем роде ленивые люди. Которые тут пытаются всё
автоматизировать лишь-бы ничего не делать и грести $$$ лопатами. Какие плюсы и минусы от
автоматизации наших тестов и рабочего процесса в целом?

Пример из истории: в 1947 году американская автомобилестроительная компания [Ford](https://ru.wikipedia.org/wiki/Ford),
начала широко использовать термин автоматизация, когда был создан отдел автоматизации. Автоматизация рутинных
процессов вывела компанию на новый уровень, вследствие чего повысила свою прибыль.

Автоматизация позволяет повысить производительность труда, улучшить качество продукции, оптимизировать
процессы управления, отстранить человека от производств, опасных для здоровья.
Автоматизация, за исключением простейших случаев, требует комплексного, системного подхода к решению задачи.

Стоит отметить, что Selenium не будет за нас всё автоматизировать, он даёт нам инструменты
с помощью которых мы автоматизируем наши тесты. Именно поэтому он широко используется в
автоматизированном тестирование.

# **Начало работы с Selenium** #
Чтобы добавить, *Selenium WebDriver*, в наш проект, понадобиться один из сборщиков проектов `Maven / Gradle`
надеюсь они вам знакомы, вот пару ссылок на Хабр, которые освежат память:

- [Apache Maven — основы](https://habr.com/ru/articles/77382/);

- [Краткое знакомство с Gradle](https://javarush.com/groups/posts/2126-kratkoe-znakomstvo-s-gradle).

### Maven ###
Добавляем *dependency* `selenium-java` в файл `pom.xml`.
Самую свежую версию вы можете найти на сайте [Maven Repository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
```markdown
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.10.0</version>
</dependency>
```

### Gradle ###
Добавляем *dependency* `selenium-java` в файл `build.gradle`
<details>
    <summary>Gradle Selenium Java </summary>

```
testImplementation 'org.seleniumhq.selenium:selenium-java:4.10.0'
```
</details>

### JUnit ###
Добавляем также в *dependency* `JUnit 4` / `JUnit 5`

В конечном итоге получаем вот такой `pom.xml`
<details>
    <summary>pom.xml </summary>

```markdown
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Selenium_Testing_Tools_Cookbook</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <selenium-java.version>4.0.0</selenium-java.version>
        <junit.version>4.13.2</junit.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium-java.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```
</details>


### Разбираем первые Selenium script ###
Все, что делает Selenium, — это отправляет браузеру команды что-то сделать или отправляет запросы
на получение информации. Большая часть того, что вы будете делать с Selenium, —
это комбинация этих основных команд:

1. Запуск браузера, который мы укажем после ключевого слова `new`:
```java
WebDriver driver = new ChromeDriver();
```
2. Открытие страницы, методу `get()` в качестве параметра передаём URL:
```java
  //Вот, можете по залипать немного на миленьких котиков, 
  //но потом обязательно возвращайтесь =)
  driver.get("https://www.youtube.com/watch?v=UXy0KoQwick");
```
3. Мы также можем получать информацию от браузера например метод `getTitle()` который вернёт
   название:
```java
String title = driver.getTitle();
```
4. Часто нам требуется время пока браузер обработает наш запрос, для это в Selenium есть так называем **условия
   ожидания**:
```java
driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
```
5. **Поиск элемента на странице**, наши действия мы будем делать после того как найдём нужный нам элемент, без этого
   Selenium нас не поймёт:
   В примере мы рассмотрели два способа нахождения элемента через `name` и `cssSelector`
```java
WebElement textBox = driver.findElement(By.name("my-text")); 
WebElement submitButton = driver.findElement(By.cssSelector("button"));
```
6. **Действия над элементом**, хорошо в предыдущем шаге мы нашли элемент, теперь можем производить
   всяческие манипуляции:
```java
textBox.sendKeys("Selenium");
submitButton.click();
```
7. **Запросить данные у элемента**: Например текст
```java
String value = message.getText();
```
8. **Закрытие браузера**, После всей нашей проделанном работы нам необходимо завершить сессию:
```java
driver.quit();
```
С основными возможностями разобрались, теперь можно попробовать, что-нибудь автоматизировать.

Первым делам создаём пустой тест, назовём его например - `FirefoxGoogleSearchTest`.

В `@Before` оставляем стандартные действия для каждого теста - это открыть браузер (начнём с Firefox),
на весь экран (можете оставить так как есть на качество тестов это никак не влияет)
и перейдём на тестируемую страницу:
```java
    private WebDriver driver;
    private static final String GOOGLE_URL = "https://www.google.ru/";

    @Before
    public void setUp() {
        //окрываем браузер Firefox на весь экран и переходим на поисковую страницу Google
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(GOOGLE_URL);
    }
```

`@After` главная его задача - закрыть браузер, почистить кэш и удалить всех пользователей,
которых мы создали для своих тестов:
```java
    @After
    public void tearDown() throws Exception{
        //закрываем браузер
        driver.quit();
    }
```
Теперь давайте, что-нибудь протестируем. Например, зайдем в Google в поиске вобьём "**Автоматизированное
тестирование**" подожнём загрузки страницы и проверим, что наш запрос совпадает с вкладкой в Google.

Для тестирования данного кейса, будем использовать один метод - `firefoxGoogleSearchTest()`, так как в `@Before`
мы уже проделали часть работы, нам необходимо:
- Тут нам может встретиться понятие **DOM (Document Object Model)** — это независящий от платформы и языка
  программный интерфейс, позволяющий программам и скриптам получить доступ к содержимому HTML-, XHTML-
  и XML-документов, а также изменять содержимое, структуру и оформление таких документов. _Простыми словами DOM_ —
  представление документа в виде иерархии тегов. Пример:
```html
<html>
    <head>
        <title> "Текст запроса" - Поиск в Google</title>
    </head>
    <body>
        Наполнение страницы
    </body>
</html>
```
Теги могут в себе содержать атрибуты. Вот они та нам и нужны, в большей степень правильность и уникальность найденного
атрибута напрямую влияет на успешное прохождение теста. Искать будем в **DevTools**, находим нужный элемент на странице.

![](src/main/resources/images/img.png)

Для этого у **driver** вызываем метод `findElement()`, а в качестве аргумента передаём
один из способов нахождения элемента на странице. В нашем случае будет **name**.

```java
WebElement element = driver.findElement(By.name("q");
```