import api.impl.JavaAwtDrawingApi
import api.impl.JavaFXDrawingApi
import model.components.Edge
import model.impl.EdgeGraph
import model.impl.MatrixGraph

fun main(args: Array<String>) {
    if (args.size < 2) {
        throw IllegalArgumentException("Required options: [fx|awt] [edge|matrix] width? height?")
    }
    val width = if (args.size >= 3) args[2].toLong() else 1280
    val height = if (args.size >= 4) args[3].toLong() else 720
    val drawingApi = when (args[0]) {
        "fx" -> JavaFXDrawingApi(width, height)
        "awt" -> JavaAwtDrawingApi(width, height)
        else -> throw IllegalArgumentException("Unsupported drawing api!")
    }
    when (args[1]) {
        "edge" -> {
            println("Write edges count")
            val count = readLine()!!.toInt()
            val edgesList = mutableListOf<Edge>()
            repeat(count) {
                val edgeLine = readLine()!!.split(" ")
                edgesList.add(Edge(edgeLine[0].toInt(), edgeLine[1].toInt()))
            }
            EdgeGraph(drawingApi, edgesList).drawGraph()
        }
        "matrix" -> {
            println("Write matrix size")
            val size = readLine()!!.toInt()
            val matrix = mutableListOf<Array<Boolean>>()
            repeat(size) {
                matrix.add(readLine()!!.split(" ").map { it == "1" }.toTypedArray())
            }
            MatrixGraph(drawingApi, matrix.toTypedArray()).drawGraph()
        }
        else -> throw IllegalArgumentException("Unsupported graph type!")
    }
}
