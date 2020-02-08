const kafka = require('kafka-node'),
    Producer = kafka.Producer,
    KeyedMessage = kafka.KeyedMessage,
    client = new kafka.KafkaClient({kafkaHost: 'localhost:29092'}),
    producer = new Producer(client),
    km = new KeyedMessage('key', 'message'),
    payloads = [
        { topic: 'test-topic', messages: 'hi', partition: 0 },
        { topic: 'test-topic', messages: ['hello', 'world', km] }
    ];

producer.on('ready', () => {
    producer.send(payloads, (err, data) => {
        if (err) console.log('Error', err) 
        else console.log('Success', data);
    });
});

producer.on('error', console.log)
