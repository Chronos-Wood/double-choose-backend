import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.spi.FilterReply

import static ch.qos.logback.classic.Level.*

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
}
appender("params", RollingFileAppender) {
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy){
        fileNamePattern = 'params-%d{yyyy-MM-dd-HH}.log'
    }

}

appender("debug", RollingFileAppender) {
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy){
        fileNamePattern = 'debug-%d{yyyy-MM-dd-HH}.log'
    }
    filter(ThresholdFilter){
        level=DEBUG
    }
}

appender("info", RollingFileAppender) {
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy){
        fileNamePattern = 'info-%d{yyyy-MM-dd-HH}.log'
    }
    filter(LevelFilter){
        level=INFO
        onMatch=FilterReply.ACCEPT
        onMismatch=FilterReply.DENY
    }
}

appender("error", RollingFileAppender) {
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy){
        fileNamePattern = 'error-%d{yyyy-MM-dd-HH}.log'
    }
    filter(LevelFilter){
        level=ERROR
        onMatch=FilterReply.ACCEPT
        onMismatch=FilterReply.DENY
    }
}



root(DEBUG, ["STDOUT", "debug", "info", "error"])
logger('params',INFO, ['params'], false)