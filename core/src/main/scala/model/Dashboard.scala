package scalograf
package model

import model.Refresh.Never
import model.annotations.Annotation
import model.enums.DashboardStyle
import model.panels.Panel
import model.template.Template
import model.time.Time.now

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import io.circe.{Encoder, JsonObject}
import scalograf.model.link.Link

import scala.concurrent.duration._

case class Dashboard(
    __inputs: List[Input] = List.empty,
    __requires: List[Requirement] = List.empty,
    annotations: List[Annotation] = List.empty,
    description: Option[String] = None,
    editable: Boolean = true,
    gnetId: Option[Long] = None,
    graphTooltip: Option[Long] = None,
    id: Option[Long] = None, //ToDo Option or adt?
    iteration: Option[Long] = None,
    links: List[Link] = List.empty,
    panels: List[Panel] = List.empty,
    refresh: Refresh = Never,
    schemaVersion: Option[Long] = None,
    style: DashboardStyle = DashboardStyle.Default,
    tags: List[String] = List.empty,
    templating: List[Template] = List.empty,
    time: TimeRange = TimeRange(now - 1.hour, now),
    timepicker: Option[TimePicker] = None,
    timezone: String = "browser", //ToDo timezone model
    title: String,
    uid: Option[String] = None, //ToDo option or adt?
    version: Option[Long] = None
)

object Dashboard {
  implicit val codecConfig = Configuration.default.withDefaults

  private val defaultEncoder: Encoder.AsObject[Dashboard] = deriveConfiguredEncoder[Dashboard]
    .mapJsonObject(encodeAsListObject("annotations"))
    .mapJsonObject(encodeAsListObject("templating"))

  implicit val encoder = new Encoder.AsObject[Dashboard] {
    override def encodeObject(d: Dashboard): JsonObject = defaultEncoder.encodeObject(GridLayout.unpackPanels(d))
  }

  implicit val decoder = deriveConfiguredDecoder[Dashboard]
    .prepare(decodeAsListObject("annotations"))
    .prepare(decodeAsListObject("templating"))
}
