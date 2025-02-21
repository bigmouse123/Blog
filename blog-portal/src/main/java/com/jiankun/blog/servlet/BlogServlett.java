package com.jiankun.blog.servlet;

import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.query.BlogQuery;
import com.jiankun.blog.service.IBlogService;
import com.jiankun.blog.service.impl.BlogServiceImpl;
import com.jiankun.blog.utils.JSONUtils;
import com.jiankun.blog.utils.MyBeanUtil;
import com.jiankun.blog.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/21 18:37
 */
@WebServlet("/blog")
public class BlogServlett extends HttpServlet {
    private IBlogService blogService = new BlogServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BlogServlett.service");
        String method = req.getParameter("method");
        switch (method) {
            case "list":
                list(req, resp);
                break;
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogQuery blogQuery = MyBeanUtil.copyToBean(req, BlogQuery.class);
        List<Blog> list = blogService.list(blogQuery);
        System.out.println(list);
        JSONUtils.toJSON(resp, Result.ok(list));
    }
}
