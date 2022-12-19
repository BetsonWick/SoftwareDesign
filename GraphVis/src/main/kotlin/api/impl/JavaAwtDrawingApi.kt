package api.impl

import api.DrawingApi
import model.components.Point
import java.awt.Frame
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.geom.Ellipse2D
import java.awt.geom.Line2D
import java.lang.IllegalArgumentException
import kotlin.system.exitProcess

class JavaAwtDrawingApi(private val width: Long, private val height: Long) : DrawingApi {

    override fun getDrawingAreaWidth() = width

    override fun getDrawingAreaHeight() = height

    override fun drawCircle(center: Point) {
        val radius = getCircleRadius()
        circles.add(
            Ellipse2D.Double(
                center.x - radius,
                center.y - radius,
                radius * 2,
                radius * 2
            )
        )
    }

    override fun drawLine(first: Point, second: Point) {
        lines.add(Line2D.Double(first.x, first.y, second.x, second.y))
    }

    override fun draw() {
        addWindowListener(
            object : WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    exitProcess(0)
                }
            }
        )

        setSize(width.toInt(), height.toInt())
        isVisible = true
    }

    private companion object : Frame("AWT") {
        val circles = mutableListOf<Ellipse2D>()
        val lines = mutableListOf<Line2D>()

        override fun paint(graphics: Graphics) {
            if (graphics is Graphics2D) {
                circles.forEach(graphics::fill)
                lines.forEach(graphics::draw)
            } else {
                throw IllegalArgumentException("Unsupported graphics type!")
            }
        }
    }
}
