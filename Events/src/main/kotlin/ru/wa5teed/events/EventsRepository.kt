package ru.wa5teed.events

import com.google.gson.Gson
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import javax.sql.DataSource

@Repository
class EventsRepository(dataSource: DataSource, val gson: Gson) : JdbcTemplate(dataSource) {
    inner class ObjectMapper() : RowMapper<String> {
        override fun mapRow(rs: ResultSet, rowNum: Int): String {
            return rs.getString("content")
        }
    }

    val objectMapper = ObjectMapper()

    fun create() {
        update(
            """
            CREATE TABLE events(
              id integer,
              type integer,
              content JSON
            );
        """.trimIndent()
        )
    }

    fun selectAllEvents(): MutableList<String> {
        return query("select * from events", objectMapper);
    }

    fun getEvents(id: Int, types: List<Int>): MutableList<String> {
        return query(
            "select * from events where id = $id and type in " +
                    types.joinToString(", ", "(", ")"),
            objectMapper
        );
    }

    fun getEvents(types: List<Int>): MutableList<String> {
        return query(
            "select * from events where type in " +
                    types.joinToString(", ", "(", ")"),
            objectMapper
        );
    }

    fun addEvent(id: Int, type: Int, content: String) {
        update(
            "insert into events values (?, ?, ?)",
            id,
            type,
            content
        )
    }
}
