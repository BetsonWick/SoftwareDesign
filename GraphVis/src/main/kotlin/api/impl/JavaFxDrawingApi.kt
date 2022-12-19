package api.impl

import api.DrawingApi
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.shape.Shape
import javafx.stage.Stage
import model.components.Point

class JavaFXDrawingApi(private val width: Long, private val height: Long) : DrawingApi {
    private companion object {
        val shapes: MutableList<Shape> = mutableListOf()
        var width: Double = 1280.0
        var height: Double = 720.0
    }

    override fun getDrawingAreaWidth() = width

    override fun getDrawingAreaHeight() = height

    override fun drawCircle(center: Point) {
        shapes.add(Circle(center.x, center.y, getCircleRadius()))
    }

    override fun drawLine(first: Point, second: Point) {
        shapes.add(Line(first.x, first.y, second.x, second.y))
    }

    override fun draw() {
        JavaFXDrawingApi.width = width.toDouble()
        JavaFXDrawingApi.height = height.toDouble()
        JavaFXApp().run()
    }

    class JavaFXApp : Application() {
        fun run() {
            launch()
        }

        override fun start(primaryStage: Stage) {
            primaryStage.title = "JavaFX"

            val root = Group().apply { shapes.forEach(this.children::add) }

            primaryStage.scene = Scene(root, width, height)
            primaryStage.show()
        }
    }
}
