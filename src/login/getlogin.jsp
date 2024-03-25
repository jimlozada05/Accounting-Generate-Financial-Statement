<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="connect.*"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
	String id = request.getParameter("login_id");
	try {
    Connection con = DBConnect.getConnection();
    PreparedStatement ps = con.prepareStatement("select * from member where member_id=?");
    ps.setString(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        Blob blob = rs.getBlob("m_image");
        byte byteArray[] = blob.getBytes(1, (int) blob.length());
        response.setContentType("image/gif");
        OutputStream os = response.getOutputStream();
        os.write(byteArray);
        os.flush();
        os.close();
    } else {
        System.out.println("No image found with this id.");
    }
	} catch (Exception e) {
	    out.println(e);
	}
%>