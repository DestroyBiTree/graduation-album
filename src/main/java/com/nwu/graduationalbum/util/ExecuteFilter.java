package com.nwu.graduationalbum.util;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: NwuGraduationAlbum
 * @description: 计算请求执行时间过滤器
 * @author: TD.Miracle
 * @create: 2022-05-20 09:57
 **/
public class ExecuteFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start =System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Execute cost:"+(System.currentTimeMillis()-start)+"ms");
    }
}
