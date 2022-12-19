package api.impl

import api.DrawingApi
import api.GraphDrawingStrategy
import model.components.Edge
import model.components.Point
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class CircularGraphDrawingStrategy() : GraphDrawingStrategy {
    override fun drawGraph(drawingApi: DrawingApi, nodesSet: Set<Int>, edgesSet: Set<Edge>) {
        if (nodesSet.isEmpty()) {
            throw IllegalArgumentException("Graph is empty!")
        }
        val nodeCount = nodesSet.size
        val width = drawingApi.getDrawingAreaWidth()
        val height = drawingApi.getDrawingAreaHeight()
        val radius = min(width, height) / 3
        val step = 2 * Math.PI / nodeCount
        val positionsMap = mutableMapOf<Int, Point>()
        nodesSet.forEachIndexed { index, node ->
            val position = Point(
                cos(step * index) * radius + width / 2,
                sin(step * index) * radius + height / 2
            )
            positionsMap[node] = position
            drawingApi.drawCircle(position)
        }
        edgesSet.forEach {
            val first = positionsMap[it.first]
            val second = positionsMap[it.second]
            if (first != null && second != null) {
                drawingApi.drawLine(first, second)
            }
        }
        drawingApi.draw()
    }
}
