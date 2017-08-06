/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import information.User;
import javax.servlet.annotation.WebServlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import operation.UserAct;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author xingxiaoyu
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String uploadPath = "/Users/xingxiaoyu/NetBeansProjects/Blog/web/temp"; // 上传文件的目录
    private String tempPath = "/Users/xingxiaoyu/NetBeansProjects/Blog/web/temp"; // 临时文件目录
    File tempPathFile;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int user_id = (int) request.getSession().getAttribute("user_id");
        User user = (User) request.getSession().getAttribute("user");

        try {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Set factory constraints
            factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
            factory.setRepository(tempPathFile);// 设置缓冲区目录

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Set overall request size constraint
            upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

            List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
            Iterator<FileItem> i = items.iterator();
            UserAct act = new UserAct();
            String name = new String();
            String pw = new String();
            String photo = new String();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    //如果是普通表单字段  

                    if (fi.getFieldName().equals("cute")) {
                        name = fi.getString("UTF-8"); //注意这里的username不能直接等于item.getString()会乱码的 因为文件上传组件的表单有个enctype="multipart/form-data"

                    }
                    if (fi.getFieldName().equals("pw")) {
                        pw = fi.getString("UTF-8");//这里和前面username一样
                    }
                    act.updateUser(user_id, name, pw);
                    user.setName(name);
                    user.setPassword(pw);
                    request.getSession().setAttribute("name", name);

                } else {
                    String fileName = fi.getName();
                    File fullFile = new File(fileName);
                    File savedFile = new File(uploadPath, fullFile.getName());
                    fi.write(savedFile);
                    photo = "temp/" + savedFile.getName();
                    act.updatePhoto(user_id, photo);
                    user.setPhoto(photo);
                    request.getSession().setAttribute("photo", photo);
                }
            }
            response.sendRedirect("main.jsp");

        } catch (Exception e) {
            // 可以跳转出错页面
            e.printStackTrace();
        }

    }

    public void init() throws ServletException {
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        File tempPathFile = new File(tempPath);
        if (!tempPathFile.exists()) {
            tempPathFile.mkdirs();
        }
    }
}
