# OCTS-OSSProducer
- `Its for testing purpose in local machine`

- `http://localhost:8080/v1/publish`

Sample Request Body
-------------------
`
{
"id": "1002",
}`


Kafka Integration
================

[Download and install Apache Kafka](http://kafka.apache.org/downloads.html)

Start Zookeeper
-----------------
- `bin/zookeeper-server-start.sh config/zookeeper.properties`

Start Kafka Server
-----------------
- `bin/kafka-server-start.sh config/server.properties`

Create Kafka Topic
------------------
- `./kafka-topics.sh --create --topic ECOM_TEST_DEV -zookeeper localhost:2181 --replication-factor 1 --partitions 4`

Consume from the Kafka Topic via Console
----------------------------------------
- `./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ECOM_TEST_DEV --from-beginning
  `