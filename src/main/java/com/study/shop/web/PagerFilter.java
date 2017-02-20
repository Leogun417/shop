package com.study.shop.web;

import com.study.shop.model.SystemContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 傲然 on 2017/2/12.
 */
public class PagerFilter implements Filter {

    private int pageSize;
    private int offset;

    public void init(FilterConfig filterConfig) throws ServletException {
        pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
        offset = Integer.parseInt(filterConfig.getInitParameter("offset"));
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String sortBy = servletRequest.getParameter("sortBy");
        String order = servletRequest.getParameter("order");
        if (servletRequest.getParameter("pager.offset") != null) {
            offset = Integer.parseInt(servletRequest.getParameter("pager.offset"));
        }
        SystemContext.setSortBy(sortBy);
        SystemContext.setOrder(order);
        SystemContext.setOffset(offset);
        SystemContext.setPageSize(pageSize);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
