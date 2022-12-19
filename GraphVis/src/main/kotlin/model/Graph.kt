package model

import api.DrawingApi
import api.GraphDrawingStrategy
import model.components.Edge
import kotlin.math.max
import kotlin.math.min

abstract class Graph(
    private val drawingApi: DrawingApi,
    private val graphDrawingStrategy: GraphDrawingStrategy,
) {
    private var nodesSet = mutableSetOf<Int>()
    private val edgesSet = mutableSetOf<Edge>()

    abstract fun drawGraph()

    protected fun proceedNode(first: Int, second: Int) {
        nodesSet.add(first)
        nodesSet.add(second)
        edgesSet.add(Edge(min(first, second), max(first, second)))
    }

    protected fun drawNodeMap() {
        graphDrawingStrategy.drawGraph(drawingApi, nodesSet, edgesSet)
    }
}
