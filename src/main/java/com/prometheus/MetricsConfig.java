package com.prometheus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class MetricsConfig {

    private final Set<String> uniqueVisitors = new HashSet<>();

    @Bean
    public Counter pageViewCounter(MeterRegistry registry) {
        return Counter.builder("page_views")
                .description("Total number of page views")
                .register(registry);
    }

    @Bean
    public Counter uniqueVisitorCounter(MeterRegistry registry) {
        return Counter.builder("unique_visitors")
                .description("Total number of unique visitors")
                .register(registry);
    }

    public void countMetrics(HttpServletRequest request, Counter pageViewCounter, Counter uniqueVisitorCounter) {
        pageViewCounter.increment();
        String clientIp = getClientIp(request);
        if (uniqueVisitors.add(clientIp)) {
            uniqueVisitorCounter.increment();
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String xffHeader = request.getHeader("X-Forwarded-For");
        if (xffHeader == null) {
            return request.getRemoteAddr();
        }
        return xffHeader.split(",")[0];
    }
}