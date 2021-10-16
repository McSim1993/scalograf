package scalograf
package model.panels.graph

import io.circe.Codec
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredCodec

case class Threshold(
    colorMode: ThresholdColorMode = ThresholdColorMode.Critical,
    fill: Boolean = false,
    line: Boolean = false,
    op: String, // ToDo operation model
    value: Option[Double] = None
)

object Threshold {
  implicit val config: Configuration = Configuration.default.withDefaults
  implicit val codec: Codec.AsObject[Threshold] = deriveConfiguredCodec[Threshold]
}
