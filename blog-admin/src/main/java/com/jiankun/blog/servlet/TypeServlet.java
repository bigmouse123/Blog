
package com.jiankun.blog.servlet;

import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.query.TypeQuery;
import com.jiankun.blog.service.ITypeService;
import com.jiankun.blog.service.impl.TypeServiceImpl;
import com.jiankun.blog.utils.JSONUtils;
import com.jiankun.blog.utils.MyBeanUtil;
import com.jiankun.blog.utils.PageResult;
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
 * @date 2025/2/17 19:36
 */
@WebServlet("/type")
public class TypeServlet extends HttpServlet {
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
            case "selectAll":
                selectAll(req, resp);
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
//        req.getRequestDispatcher("/WEB-INF/type_add.jsp").forward(req, resp);
//    }
//
//    private void toList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/type_list.jsp").forward(req, resp);

    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Type> list = typeService.selectAll();
        JSONUtils.toJSON(resp, Result.ok(list));
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Type type = MyBeanUtil.copyToBean(req, Type.class);
        System.out.println(type);
        typeService.add(type);
        JSONUtils.toJSON(resp, Result.ok("添加成功"));
    }

    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TypeQuery typeQuery = MyBeanUtil.copyToBean(req, TypeQuery.class);
        System.out.println(typeQuery);
        PageResult<Type> pageResult = typeService.selectByPage(typeQuery);
        JSONUtils.toJSON(resp, pageResult);
    }
}
