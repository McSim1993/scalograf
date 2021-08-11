package scalograf
package model.panels.map

import model.{Color, Target, Time}
import model.panels.{GridPosition, Panel, Transformation}

import io.circe._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredCodec

case class WorldMapPanel(
    circleMaxSize: String,
    circleMinSize: String,
    colors: List[Color] = List.empty,
    datasource: Option[String] = None,
    decimals: Option[String] = None,
    description: Option[String] = None,
    esMetric: String = "Count", //ToDo enum
    gridPos: GridPosition,
    hideEmpty: Boolean = false,
    hideZero: Boolean = false,
    id: Option[Int] = None,
    initialZoom: String = "2",
    interval: Option[Time] = None,
    locationData: String = "countries", //ToDo enum
    mapCenter: String, //ToDo geopoint
    mapCenterLatitude: Double,
    mapCenterLongitude: Double,
    maxDataPoints: Int,
    mouseWheelZoom: Boolean = true,
    pluginVersion: String,
    showLegend: Boolean = true,
    stickyLabels: Boolean = true,
    tableQueryOptions: Option[TableQueryOptions] = None,
    targets: List[Target] = List.empty,
    thresholds: Option[String] = None,
    timeFrom: Option[String] = None, //ToDo time model
    timeShift: Option[String] = None,
    title: Option[String] = None,
    transformations: List[Transformation] = List.empty,
    unitPlural: Option[String] = None,
    unitSingle: Option[String] = None,
    valueName: Option[String] = None
) extends Panel {
  override def `type`: String = "grafana-worldmap-panel"

  override def asJson: JsonObject = WorldMapPanel.codec.encodeObject(this)
}

object WorldMapPanel {
  implicit val codecConfig = Configuration.default.withDefaults
  implicit val codec: Codec.AsObject[WorldMapPanel] = deriveConfiguredCodec[WorldMapPanel]
}
