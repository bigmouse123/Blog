package com.jiankun.blog.servlet;

import com.jiankun.blog.pojo.User;
import com.jiankun.blog.service.IUserService;
import com.jiankun.blog.service.impl.UserServiceImpl;
import com.jiankun.blog.utils.JSONUtils;
import com.jiankun.blog.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/7 13:56
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private IUserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method) {
            case "login":
                login(req, resp);
                break;
            case "logout":
                logout(req, resp);
                break;
            case "updatePassword":
                updatePassword(req, resp);
                break;
        }
    }

    private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("updatePassword");
        String name = req.getParameter("name");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        User user = userService.login(name, oldPassword);
        System.out.println(user);
        if (user == null) {
            JSONUtils.toJSON(resp, Result.error("密码错误"));
        } else {
            userService.updatePassword(name, newPassword);
            JSONUtils.toJSON(resp, Result.ok("修改成功"));
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
//        session.removeAttribute("user");
        resp.sendRedirect("/login.jsp");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        String codeInSession = (String) session.getAttribute("codeInSession");
        if (!codeInSession.equals(code)) {
            JSONUtils.toJSON(resp, Result.error("验证码错误"));
            return;
        }

        User user = userService.login(name, password);
        System.out.println(user);
        if (user == null) {
            JSONUtils.toJSON(resp, Result.error("登录失败"));
        } else {
            session.setAttribute("user", user);
            JSONUtils.toJSON(resp, Result.ok("登录成功"));
        }

    }
}
