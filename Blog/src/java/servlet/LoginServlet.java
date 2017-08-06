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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        UserAct userAct = new UserAct();
        userAct.tourist(request.getParameter("essay_id"), request);
        request.getRequestDispatcher("blog.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        UserAct userAct = new UserAct();
        String idName = request.getParameter("idName");
        String password = request.getParameter("password");
        User user = userAct.login(idName, password, request);
        if (user == null) {
            // 登录失败
            request.setAttribute("info", "错误：用户名或密码错误！");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        } else if (user.getState() == 1) {
            request.setAttribute("info", "用户已登陆,直接跳转到主页面...");
            request.getRequestDispatcher("message.jsp").forward(request, response);
        } else {
            user.setState(1);
            userAct.in(user.getId());
            //request.getSession().setAttribute("active", true);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }

    }

}
