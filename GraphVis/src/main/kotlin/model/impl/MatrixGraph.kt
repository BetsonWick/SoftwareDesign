package model.impl

import api.DrawingApi
import model.Graph

class MatrixGraph(
    private val drawingApi: DrawingApi,
    private val matrix: Array<Array<Boolean>>
) : Graph(drawingApi) {
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