package dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import connect.DBConnect;
import table.Login;


@WebServlet("/editlogin")
@MultipartConfig(maxFileSize = 16177216)//1.5mb
public class EditLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PrintWriter out;
	
    public EditLogin() {
        super();
  
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login log=new Login();
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        int result = 0;
        String check = request.getParameter("check");
        Part part = request.getPart("l_image");
        if (part != null) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE login SET username=?, password=?, l_image=? WHERE login_id=?");
            int login_id=Integer.parseInt(request.getParameter("login_id"));
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            
            
            log.setLogin_id(login_id);
            log.setUsername(username);
            log.setPassword(password);
            InputStream is = part.getInputStream();
            
            ps.setString(1, log.getUsername());
            ps.setString(2, log.getPassword());
            ps.setBlob(3, is);
            ps.setInt(4, log.getLogin_id());
            result = ps.executeUpdate();
            if (result > 0) {
            	JOptionPane.showMessageDialog(null, "profile sucessfuly changed, PLEASE RELOAD PAGE TO APPLY CHANGES");
            	System.out.print(check);
                response.sendRedirect("webpages/Redirect.jsp");
            } else {
                response.sendRedirect("webpages/Redirect.jsp");
            }
        } catch (Exception e) {
            out.println(e);
        }
	}
        else {
        	try {
                Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE login SET username=?, password=? WHERE login_id=?");
                int login_id=Integer.parseInt(request.getParameter("login_id"));
                String username=request.getParameter("username");
                String password=request.getParameter("password");
                
                log.setLogin_id(login_id);
                log.setUsername(username);
                log.setPassword(password);
                
                ps.setString(1, log.getUsername());
                ps.setString(2, log.getPassword());
                ps.setInt(3, log.getLogin_id());
                result = ps.executeUpdate();
                if (result > 0) {
                	JOptionPane.showMessageDialog(null, "profile sucessfuly changed, PLEASE RELOAD PAGE TO APPLY CHANGES");
                	System.out.print(check);
                    response.sendRedirect("webpages/Redirect.jsp");
                } else {
                    response.sendRedirect("webpages/Redirect.jsp");
                }
            } catch (Exception e) {
                out.println(e);
            }
        }

  }
}
