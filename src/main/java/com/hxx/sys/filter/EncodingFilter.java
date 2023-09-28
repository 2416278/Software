package com.hxx.sys.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一设置Post方式提交数据的编码方式
 */
@WebFilter(filterName = "encodingFiler",urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        req.setCharacterEncoding("utf-8"); // 设置编码方式为utf-8
        chain.doFilter(req,res); // 放过请求
    }
    @Override
    public void destroy() {
    }
}
