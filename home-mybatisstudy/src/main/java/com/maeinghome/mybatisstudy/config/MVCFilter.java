package com.maeinghome.mybatisstudy.config;


import com.maeinghome.mybatisstudy.maeingpool.info.UserContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*")
public class MVCFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String serverName = servletRequest.getServerName();
        System.out.printf(serverName);
        int i = serverName.indexOf(".");
        String substring = serverName.substring(0, i);
        if (substring != null) {
            UserContextHolder.userName.set(substring);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println(serverName);
    }
}
