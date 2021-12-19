# departments-employees
***
___Clone repository___
```
$ git clone https://github.com/igormzhn/departments-employees.git
```

___Build services with maven___
```
(cd employees-service && mvn install)
(cd departments && mvn install)
```

__Сhange the path in volumes block in the docker-compose file so that the data in the databases is saved on disk after the container is deleted__
```
pgdata for employees database
pgdata1 for departments database
```

___Run docker-compose.yaml___
```
$ docker-compose up
```
