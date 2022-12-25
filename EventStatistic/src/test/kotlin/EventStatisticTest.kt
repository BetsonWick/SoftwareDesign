import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import statistic.EventStatistic
import statistic.EventStatisticImpl
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.util.concurrent.TimeUnit

class EventStatisticTest {

    private lateinit var eventStatistic: EventStatistic
    private lateinit var testClock: TestClock

    companion object {
        private val DEFAULT_INSTANT: Instant = Instant.ofEpochMilli(100000000L)
        private val HOUR_MILLIS: Long = TimeUnit.HOURS.toMillis(1)
        private val SINGLE_PER_HOUR: Double = 1 / TimeUnit.HOURS.toMinutes(1).toDouble()
        private const val EVENT_1 = "First event"
        private const val EVENT_2 = "Second event"
    }

    @BeforeEach
    fun init() {
        testClock = TestClock(DEFAULT_INSTANT)
        eventStatistic = EventStatisticImpl(testClock)
    }

    @Test
    fun severalEventsSimultaneously() {
        eventStatistic.incEvent(EVENT_1)
        eventStatistic.incEvent(EVENT_2)
        //Test that events occur
        assertEquals(SINGLE_PER_HOUR, eventStatistic.getEventStatisticByName(EVENT_1))
        assertEquals(SINGLE_PER_HOUR, eventStatistic.getEventStatisticByName(EVENT_2))
        //Test that events occur in right order
        assertEquals(
            listOf(Pair(EVENT_1, SINGLE_PER_HOUR), Pair(EVENT_2, SINGLE_PER_HOUR)),
            eventStatistic.getAllEventStatistic()
        )

    }

    @Test
    fun noEventsAfterOneHour() {
        eventStatistic.incEvent(EVENT_1)
        eventStatistic.incEvent(EVENT_2)
        testClock.currentInstant = DEFAULT_INSTANT.plusMillis(HOUR_MILLIS + 1)
        //Test that after one hour there are no actual events
        assertEquals(0, eventStatistic.getAllEventStatistic().size)
    }

    @Test
    fun oneEventIsLeft() {
        eventStatistic.incEvent(EVENT_1)
        testClock.currentInstant = DEFAULT_INSTANT.plusMillis(HOUR_MILLIS - 1)
        eventStatistic.incEvent(EVENT_2)
        testClock.currentInstant = testClock.currentInstant.plusMillis(2)
        assertEquals(1, eventStatistic.getAllEventStatistic().size)
        assertEquals(0.0, eventStatistic.getEventStatisticByName(EVENT_1))
        assertEquals(SINGLE_PER_HOUR, eventStatistic.getEventStatisticByName(EVENT_2))
    }

    class TestClock(var currentInstant: Instant) : Clock() {
        override fun instant(): Instant = currentInstant

        override fun withZone(zone: ZoneId?): Clock {
            TODO("Not yet implemented")
        }

        override fun getZone(): ZoneId {
            TODO("Not yet implemented")
        }

    }
}
