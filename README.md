# CRYPTO BETS BACKEND
Persistence and logic of game of CRYPTO BEST:

* Register players to a game
* Game has a cyclic duration of time parameter
* A game execute when 2 o more players are registered
* Send message to notification service through a topic of kafka (https://github.com/guidorolando/test-cb-notification)

###Pre condition
* JDK 11
* Maven 3 or higger
* MySQL 8
* DB created with name "game"
* Running kafka and zookeeper
* Running cb-test-notification to send emails repository (https://github.com/guidorolando/test-cb-notification)


# Getting Started

Run kafka:
  * go kafka location  cd c:\kafka 
  * initial zookeeper  .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties 
  * initial kafka  .\bin\windows\kafka-server-start.bat .\config\server.properties

Run MYSQL

 * the code include a docker-compose with a container of MySQL.


    docker-compose build
    docker-compose up

Change over application.properties:

* Add environment variables to set credentials of mysql 

        spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/game
        spring.datasource.username=${MYSQL_USER:root}
        spring.datasource.password=${MYSQL_PASSWORD:root}

* Nomics token:

        nomics.token= ${NOMICS_TOKEN}

* Needs configurations of kafka
    
        spring.kafka.producer.bootstrap-servers= localhost:9092
        spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
        spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
* Configuration of times in milliseconds

        env.play.time=60000
        env.play.warning.time=5000