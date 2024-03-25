package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connect.*;
import table.*;

public class LoginDAO {

	static Connection con=null;
	static PreparedStatement ps=null;
	static ResultSet rs=null;
	static Blob image = null;
	static byte[] imgData = null;
	
	public static List<Login> getAllRecords(){
		List<Login> list=new ArrayList<Login>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * from login");
			rs=ps.executeQuery();
			while(rs.next()) {
				Login log=new Login();
				log.setLogin_id(rs.getInt("login_id"));
				log.setUsername(rs.getString("username"));
				log.setPassword(rs.getString("password"));
				list.add(log);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Login> getARecord(int login_id){
		List<Login> list=new ArrayList<Login>();
		try {
			con=DBConnect.getConnection();
			ps=con.prepareStatement("SELECT * FROM login where login_id=?");
			
			ps.setInt(1, login_id);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Login log=new Login();
				log.setLogin_id(rs.getInt("login_id"));
				log.setUsername(rs.getString("username"));
				log.setPassword(rs.getString("password"));
				
				list.add(log);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Login getLoginByID(int login_id) {
		try{
				con=DBConnect.getConnection();
				ps=con.prepareStatement("SELECT * FROM login WHERE login_id=?");
				ps.setInt(1, login_id);
				
				rs= ps.executeQuery();
				Login log = new Login();
				
				if(rs.next()) {
					log.setLogin_id(rs.getInt(1));
					log.setUsername(rs.getString(2));
					log.setPassword(rs.getString(3));
					}
				
				rs.close();
				ps.close();
				con.close();
				return log;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static int deleteLogin(Login log) {
		try {
			con=DBConnect.getConnection();
			
			ps=con.prepareStatement("DELETE FROM login WHERE login_id=?");
			
			ps.setInt(1, log.getLogin_id());
			
			ps.executeUpdate();
			
			ps.close();
			con.close();
			
			return 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
}
