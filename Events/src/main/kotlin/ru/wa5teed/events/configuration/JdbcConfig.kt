package ru.wa5teed.events.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
class JdbcConfig {
    @Autowired
    lateinit var env: Environment

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(env.getProperty("driverClassName") ?: "")
        dataSource.url = env.getProperty("url")
        dataSource.username = env.getProperty("user")
        dataSource.password = env.getProperty("password")
        return dataSource
    }
}