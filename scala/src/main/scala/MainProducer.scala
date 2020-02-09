import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl._
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

object MainProducer extends App {

  private implicit val actorSystem: ActorSystem = ActorSystem()

  val config = actorSystem.settings.config.getConfig("akka.kafka.producer")
  val producerSettings =
    ProducerSettings(config, new StringSerializer, new StringSerializer)
      .withBootstrapServers("localhost:29092")

  val source = Source("Scala" :: "Akka" :: Nil)
  val flow = Flow[String]
    .map(value => new ProducerRecord[String, String]("test-topic", value, "Record: " + value))
  val sink = Producer.plainSink(producerSettings)

  source.via(flow).runWith(sink)
}