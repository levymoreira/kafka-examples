const kafka = require('kafka-node'),
    Consumer = kafka.Consumer,
    client = new kafka.KafkaClient({kafkaHost: 'localhost:29092'}),
    consumer = new Consumer(
        client,
        [
            { topic: 'test-topic' }
        ],
        {
            autoCommit: false
        }
    );

consumer.on('message', console.log);