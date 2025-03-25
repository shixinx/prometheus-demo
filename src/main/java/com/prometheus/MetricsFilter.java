package com.prometheus;

import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MetricsFilter extends OncePerRequestFilter {

    @Autowired
    private MetricsConfig metricsConfig;

    @Autowired
    private Counter pageViewCounter;

    @Autowired
    private Counter uniqueVisitorCounter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("-----------");
        metricsConfig.countMetrics(request, pageViewCounter, uniqueVisitorCounter);
        filterChain.doFilter(request, response);
    }

}