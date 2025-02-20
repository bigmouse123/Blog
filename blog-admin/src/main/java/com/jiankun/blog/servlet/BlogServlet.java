package com.jiankun.blog.servlet;

import com.jiankun.blog.pojo.Blog;
import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.pojo.vo.BlogVO;
import com.jiankun.blog.query.BlogQuery;
import com.jiankun.blog.service.IBlogService;
import com.jiankun.blog.service.ITypeService;
import com.jiankun.blog.service.impl.BlogServiceImpl;
import com.jiankun.blog.service.impl.TypeServiceImpl;
import com.jiankun.blog.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 19:36
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    private IBlogService blogService = new BlogServiceImpl();
    private ITypeService typeService = new TypeServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null || "".equals(method)) {
            method = "selectByPage";
        }
        switch (method) {
            case "selectByPage":
                selectByPage(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "selectById":
                selectById(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "exportExcel":
                exportExcel(req, resp);
                break;
            case "importExcel":
                importExcel(req, resp);
                break;
//            case "toList":
//                toList(req, resp);
//                break;
//            case "add":
//                toAdd(req, resp);
//                break;
        }
//    }
//
//    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/blog_add.jsp").forward(req, resp);
//    }
//
//    private void toList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/blog_list.jsp").forward(req, resp);

    }

    private void importExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        blogService.importExcel(req);
        JSONUtils.toJSON(resp, Result.ok("导入成功"));
    }

    private void exportExcel(HttpServletRequest req, HttpServletResponse resp) {
        blogService.exportExcel(resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Blog blog = MyBeanUtil.copyToBean(req, Blog.class);
        blogService.update(blog);
        JSONUtils.toJSON(resp, Result.ok("编辑成功"));
    }

    private void selectById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        System.out.println("id=" + id);
        Blog blog = blogService.selectById(id);
        List<Type> list = typeService.selectAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("blog", blog);
        map.put("list", list);
        JSONUtils.toJSON(resp, Result.ok(map));
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Blog blog = MyBeanUtil.copyToBean(req, Blog.class);
        System.out.println(blog);
        blogService.add(blog);
        JSONUtils.toJSON(resp, Result.ok("添加成功"));
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BlogQuery blogQuery = MyBeanUtil.copyToBean(req, BlogQuery.class);
        System.out.println(blogQuery);
        PageResult<BlogVO> pageResult = blogService.selectByPage(blogQuery);
        JSONUtils.toJSON(resp, pageResult);
    }
}
