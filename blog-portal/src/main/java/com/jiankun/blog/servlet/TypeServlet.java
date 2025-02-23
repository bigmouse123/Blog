package com.jiankun.blog.servlet;

import com.jiankun.blog.pojo.Type;
import com.jiankun.blog.service.ITypeService;
import com.jiankun.blog.service.impl.TypeServiceImpl;
import com.jiankun.blog.utils.JSONUtils;
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
 * @date 2025/2/22 15:06
 */
@WebServlet("/type")
public class TypeServlet extends HttpServlet {
    private ITypeService typeService = new TypeServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service");
        String method = req.getParameter("method");
        switch (method) {
            case "selectAll":
                selectAll(req, resp);
                break;
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("selectAll");
        List<Type> types = typeService.selectAll();
        JSONUtils.toJSON(resp, Result.ok(types));
    }
}
