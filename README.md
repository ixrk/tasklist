# Tasklist

## About

Tasklist is a web application based on the Spring framework created to help you manage your tasks. You can group them in categories, mark as done, edit and delete.

### Used tools

* Spring Boot
* Thymeleaf
* Bootstrap
* Spring Security
* Spring Data JPA
* Apache Commons Lang 3
* H2 database (development profile)
* MariaDB database (production profile)

## How to use

### Prerequisites

* JDK >= 1.8
* MySQL or a database compatible with it.
* Docker (optional)

### Prepare

1. Change `spring.datasource.*` values in `src/main/resources/application-prod.properties` to match your database.
2. Change `.rememberMe().key("change-me")` parameter in `src/main/java/pl/sda/tasklist/config/SpringSecurityConfiguration.java`

### Build

```shell script
$ ./mvnw -P prod clean package
```

### Run

```shell script
$ java -jar target/tasklist-*.jar
```

### Containerize

```shell script
# docker build -t tasklist .
# docker run -t -p 8080:8080 tasklist
```
