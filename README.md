![Build](https://github.com/softwarejimenez/contratos/actions/workflows/ci.yml/badge.svg)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=softwarejimenez_contratos&metric=alert_status)](https://sonarcloud.io/dashboard?id=softwarejimenez_contratos)

# Contratos

Follow the latest agreement in the healthcare system.

## Running

### Manually run sprint-boot
```
mvn spring-boot:run
```

### Using Docker
- Build the application:
```
mvn package -B
```
- Build the docker image:
```
docker build . -t contratos .
```
- Run the docker image
```
docker run -p 8080:8080 contratos
```
- Push the container
    - Created the repository https://hub.docker.com/repository/docker/ajimenez15/contratos/general
```
docker tag contratos ajimenez15/contratos
docker login
docker push ajimenez15/contratos
```
