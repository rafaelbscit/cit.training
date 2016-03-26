import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import java.nio.charset.Charset

def byDay = timestamp("yyyy-MM-dd")

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName("UTF-8")
        pattern = "%d{HH:mm:ss.SSS} %-5level [uri:%X{req.requestURI}][method:%X{req.method}][%X{uuid}] %logger{36} - %msg%n"
    }
}
appender("FILE", RollingFileAppender) {
    file = "/var/log/contacts/main_${byDay}.log"
    append = true
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName("UTF-8")
        pattern = "%d{HH:mm:ss.SSS} %-5level [%X{uuid}] %logger{36} - %msg%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "/var/log/contacts/main_%d{yyyy-MM-dd}.%i.log"
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = "10MB"
        }
        maxHistory = 2
    }
}

logger("br.com.cit.contacts", DEBUG)

root(INFO, ["STDOUT", "FILE"])