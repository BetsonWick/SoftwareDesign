package model.impl

import api.DrawingApi
import model.Graph
import model.components.Edge

class EdgeGraph(
    private val drawingApi: DrawingApi,
    private val edgeList: List<Edge>,
) : Graph(drawingApi) {
    override fun drawGraph() {
        for (edge in edgeList) {
            proceedNode(edge.first, edge.second)
        }
        drawNodeMap()
    }
}
