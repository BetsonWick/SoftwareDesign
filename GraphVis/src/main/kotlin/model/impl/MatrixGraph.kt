package model.impl

import api.DrawingApi
import api.GraphDrawingStrategy
import model.Graph

class MatrixGraph(
    drawingApi: DrawingApi,
    graphDrawingStrategy: GraphDrawingStrategy,
    private val matrix: Array<Array<Boolean>>,
) : Graph(drawingApi, graphDrawingStrategy) {
    override fun drawGraph() {
        matrix.forEachIndexed { row, markers ->
            markers.forEachIndexed { column, value ->
                if (value) {
                    proceedNode(row, column)
                }
            }
        }
        drawNodeMap()
    }
}