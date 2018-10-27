package akka.persistence.pravega
import io.pravega.client.{ClientConfig, ClientFactory}

trait PravegaCapabilities {
  val clientFactory = ClientFactory.withScope("", ClientConfig.builder().build())

}
