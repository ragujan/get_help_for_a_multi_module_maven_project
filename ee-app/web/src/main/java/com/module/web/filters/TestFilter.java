package com.module.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Date;

public class TestFilter implements Filter {



    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        String testString = fConfig.getInitParameter("test-param");

        //Print the init parameter
        System.out.println("Test Param: " + testString);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;


        String ipAddress = req.getRemoteAddr();
        System.out.println("IP Address "+ipAddress + ", Time is"
                + new Date().toString());

        // pass the request along the filter chain
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
