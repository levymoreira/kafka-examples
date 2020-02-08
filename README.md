# Viewer Kafka

Example Kafka acessing using external and internal clients with Scala and Node JS.

### Start up Kafka and Zookeeper
docker-compose up -d

### Create topic
```
docker exec -it kafka /bin/bash -c "kafka-topics.sh --create --topic test-topic --partitions 4 --replication-factor 1 --zookeeper zookeeper:2181"
```
### Send a message
```
docker exec -it kafka /bin/bash -c "echo "111" | kafka-console-producer.sh --topic=test-topic --broker-list=:9092"
```
### Create a consumer
```
docker exec -it kafka /bin/bash -c "kafka-console-consumer.sh --topic=test-topic --from-beginning --bootstrap-server :9092"
```
### Stop everything and prune
docker-compose stop && docker rm $(docker ps -a -q) && docker volume prune

### Links 
[https://github.com/bitnami/bitnami-docker-kafka](https://github.com/bitnami/bitnami-docker-kafka)
[https://hub.docker.com/r/bitnami/kafka/](https://hub.docker.com/r/bitnami/kafka/)
[https://docs.bitnami.com/oci/infrastructure/kafka/get-started/understand-default-config/](https://docs.bitnami.com/oci/infrastructure/kafka/get-started/understand-default-config/)

