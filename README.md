# The Witcher Game

A command line based role playing game.

### Prerequisites

* [Maven](https://maven.apache.org/) - Dependency Management, version >= 3.x

### Installing

Build project:

```
mvn install
```
## Running
```
java -jar target\witcher4.jar
```
## Running using docker
```
docker build -f src/main/resources/Dockerfile . -t witcher4:latest
docker run -i -t witcher4:latest
```
