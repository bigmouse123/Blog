package com.jiankun.blog.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/17 20:46
 */
@WebServlet("/page/*")
public class PageServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);
        String[] paths = requestURI.split("/");
        for (String path : paths) {
            System.out.print(path + "\t");
        }
        System.out.println();
        req.getRequestDispatcher("/WEB-INF/" + paths[2] + "_" + paths[3] + ".jsp").forward(req, resp);
    }
}
