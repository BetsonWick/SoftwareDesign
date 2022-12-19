package api

import model.components.Point

interface DrawingApi {
    fun getDrawingAreaWidth(): Long

    fun getDrawingAreaHeight(): Long

    fun drawCircle(center: Point)

    fun drawLine(first: Point, second: Point)

    fun getCircleRadius(): Double = getDrawingAreaWidth() / 100.0

    fun draw()
}
