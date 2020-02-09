import akka.stream.scaladsl._
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

object MainConsumer extends App {

  private implicit val actorSystem: ActorSystem = ActorSystem()

  val config = actorSystem.settings.config.getConfig("akka.kafka.consumer")
  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
      .withBootstrapServers("localhost:29092")
      .withGroupId("scala-main-consumer")
      .withProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
      .withProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000")

  Consumer
    .plainSource(consumerSettings, Subscriptions.topics("test-topic"))
    .map{msg => println("Message: " + msg.key() + " - " + msg.value())}
    .toMat(Sink.ignore)(Keep.both)
    .run
}