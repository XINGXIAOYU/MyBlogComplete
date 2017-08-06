/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import information.Essay;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.EssayAct;
import operation.UserAct;

/**
 *
 * @author xingxiaoyu
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int sort = Integer.parseInt(request.getParameter("sort"));
        EssayAct act = new EssayAct();
        ArrayList<Essay> essay = (ArrayList<Essay>) request.getSession().getAttribute("result");
        ArrayList<Essay> result = new ArrayList<Essay>();
        if (sort == 1) {
            result = act.sortDateResult(essay);
        } else if (sort == 2) {
            result = act.sortDateResultDesc(essay);
        } 
        request.getSession().setAttribute("result", result);
        request.getRequestDispatcher("searchresult.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String range = request.getParameter("range");
        String keyword = request.getParameter("keyword");
        String cond = request.getParameter("condition");
        int user_id = (int) request.getSession().getAttribute("user_id");
        UserAct act = new UserAct();
        ArrayList<Essay> result = new ArrayList<Essay>();
        if (range.equals("1")) {
            for (int i = 0; i < act.search(user_id, Integer.parseInt(keyword), cond).size(); i++) {
                result.add(act.search(user_id, Integer.parseInt(keyword), cond).get(i));
            }
        } else if (range.equals("2")) {
            for (int i = 0; i < act.search(Integer.parseInt(keyword), cond).size(); i++) {
                result.add(act.search(Integer.parseInt(keyword), cond).get(i));
            }
        }
        request.getSession().setAttribute("result", result);
        request.getRequestDispatcher("searchresult.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
