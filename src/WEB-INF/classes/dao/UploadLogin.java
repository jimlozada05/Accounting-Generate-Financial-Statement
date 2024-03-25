
package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connect.DBConnect;
import table.Login;


@WebServlet("/uploadlogin")
@MultipartConfig(maxFileSize = 16177216)//1.5mb
public class UploadLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PrintWriter out;
	
    public UploadLogin() {
        super();
        
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login log=new Login();
		response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        int result = 0;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into login(username,password) values(?,?)");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            
            log.setUsername(username);
            log.setPassword(password);
            
            ps.setString(1, log.getUsername());
            ps.setString(2, log.getPassword());
            
            result = ps.executeUpdate();
            if (result > 0) {
            	response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp?message=Some+Error+Occurred");
            }
        } catch (Exception e) {
            out.println(e);
        }
	}

}
