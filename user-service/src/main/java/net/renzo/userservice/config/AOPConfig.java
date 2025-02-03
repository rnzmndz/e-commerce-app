package net.renzo.userservice.config;

import net.renzo.userservice.aspect.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfig {

//    @Bean
//    public LoggingAspect loggingAspect(LoggingAspect loggingAspect) {
//        return new LoggingAspect();
//    }
}
