package com.jiankun.blog.filter;

import com.jiankun.blog.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/7 15:11
 */
@WebFilter(filterName = "login", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        System.out.println("requestURL:" + requestURI);

        //若请求是要去完成登录操作的，就不再拦截
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if (requestURI.startsWith("/static") ||
                requestURI.equals("/page/login") ||
                requestURI.equals("/page/fail") ||
                requestURI.equals("/page/user/register") ||
                requestURI.equals("/verifyCode") ||
                (requestURI.equals("/user") && "login".equals(method)) ||
                (requestURI.equals("/user") && "register".equals(method))) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/page/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
