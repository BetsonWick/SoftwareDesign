package model.impl

import api.DrawingApi
import api.GraphDrawingStrategy
import model.Graph
import model.components.Edge

class EdgeGraph(
    drawingApi: DrawingApi,
    graphDrawingStrategy: GraphDrawingStrategy,
    private val edgeList: List<Edge>,
) : Graph(drawingApi, graphDrawingStrategy) {
    override fun drawGraph() {
        for (edge in edgeList) {
            proceedNode(edge.first, edge.second)
        }
        drawNodeMap()
    }
}
