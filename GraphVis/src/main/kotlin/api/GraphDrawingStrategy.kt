package api

import model.components.Edge

interface GraphDrawingStrategy {
    fun drawGraph(drawingApi: DrawingApi, nodesSet: Set<Int>, edgesSet: Set<Edge>)
}
