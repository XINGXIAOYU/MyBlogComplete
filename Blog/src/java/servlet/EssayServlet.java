/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import information.Essay;
import information.EssayClass;
import information.User;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;

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
@WebServlet(name = "EssayServlet", urlPatterns = {"/EssayServlet"})
public class EssayServlet extends HttpServlet {

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
        processRequest(request, response);
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
        //System.out.println(request.getParameter("essay_id"));
        EssayAct essayAct = new EssayAct();
        User user = (User) request.getSession().getAttribute("user");//author
        String essayName = request.getParameter("biaoti");
        String content = request.getParameter("textarea");
        String newclassName = request.getParameter("class");
        String className = request.getParameter("fenlei");
        String tag = request.getParameter("biaoqian");
        int user_id = user.getId();//author id
        Essay essay = null;
        if (request.getSession().getAttribute("refine") == null) {
            essay = new Essay();
        } else {
            for (int i = 0; i < user.getEssay().size(); i++) {
                if ((int) request.getSession().getAttribute("refine") == user.getEssay().get(i).getEssay_id()) {
                    essay = user.getEssay().get(i);
                }
            }
        }
        essay.setEname(essayName);
        essay.setContent(content);
        essay.setUser_id(user_id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        essay.setPbtime(df.format(new Date()));

        essay.setAuthorName(essayAct.getAuthorName(user.getId()));
        if (!"".equals(newclassName) && essayAct.isEssayClassExist(newclassName, user) == false) {//新建分类
            EssayClass essayClass = new EssayClass(newclassName);
            essayClass.setClassName(newclassName);
            essayClass.getEssays().add(essay);
            essayAct.saveEssayClass(user, essayClass);
            user.getEssayClass().add(essayClass);
            essay.setClassName(newclassName);

        } else {//配对已有分类
            for (int i = 0; i < user.getEssayClass().size(); i++) {
                if (user.getEssayClass().get(i).getClassName().equals(className)) {
                    user.getEssayClass().get(i).getEssays().add(essay);
                    essay.setClassName(className);
                }
            }

        }

        //更新数据库博文分类中的博文数量
        for (int i = 0; i < user.getEssayClass().size(); i++) {
            essayAct.EssaySum(user.getEssayClass().get(i), user);
        }
        if (request.getSession().getAttribute("refine") == null) {

            essayAct.saveEssay(essay);
        } else {
            essayAct.updateEssay(essay);
        }

        //保存标签
        if (tag.equals("") == false) {
            essayAct.deletetagEssayMap(essay.getEssay_id());
            String[] temp = tag.split(" ");
            for (int i = 0; i < temp.length; i++) {
                if (essayAct.isTagNameExist(temp[i]) == false) {
                    essayAct.saveTag(temp[i]);
                }
                essayAct.tagEssayMap(essayAct.getTagId(temp[i]), essayAct.getEssayId(essay.getEname(), user.getId(), essay.getPbtime()));

            }
            ArrayList tags = new ArrayList();
            for (int i = 0; i < temp.length; i++) {
                tags.add(temp[i]);
            }

            essay.setTags(tags);
        }
        if (request.getSession().getAttribute("refine") != null) {
            request.getSession().removeAttribute("refine");
        }

        UserAct act = new UserAct();
        act.update(user, request);

        request.getRequestDispatcher("main.jsp").forward(request, response);
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
