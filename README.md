# departments-employees
***
___Copy repository___
```
$ git clone https://github.com/igormzhn/departments-employees.git
```

___Сollect services with maven___
```
/employees-service $ mvn clean
/employees-service $ mvn install

/departments $ mvn clean
/departments $ mvn install
```

___Create docker network for microservices and database___
```
$ docker network create micro-service-network
```

__Сhange the path to the volums block in the docker-compose file so that the data in the databases is saved on disk after the container is deleted__
```
pgdata for employees database
pgdata1 for departments database
```

___Change the path to the Dockerfile in the docker compose build block___

___Build the docker-compose___
```
$ docer-compose build
```

___Run docker-compose.yaml___
```
$ docker-compose up
```
