/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import information.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.UserAct;

/**
 *
 * @author xingxiaoyu
 */
@WebServlet(name = "RegServlet", urlPatterns = {"/RegServlet"})
public class RegServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idName = request.getParameter("regidName");
        String name = request.getParameter("name");
        String password = request.getParameter("regpassword");
        UserAct userAct = new UserAct();
        if (userAct.idNameIsExist(idName) == false) {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setIdName(idName);
            userAct.saveUser(user);
            user = userAct.login(idName, password,request);
            userAct.defaultClass(user,"未分类博文");//默认分类 
            userAct.update(user,request);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("idName", user.getIdName());
            request.setAttribute("info", "成功注册，正在跳转...");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        } else {
            request.setAttribute("info", "对不起，该账号已被注册，请更换登录账号");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
    }

}
