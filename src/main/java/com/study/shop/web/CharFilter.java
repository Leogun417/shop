package com.study.shop.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 傲然 on 2017/2/12.
 */
public class CharFilter implements Filter {

    private String encoding;

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request,response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        if (encoding == null || encoding.equals("")) {
            encoding = "UTF-8";
        }
    }
}
