/**
 * 
 */
package application;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * @author Loj
 *
 */
public class studentService {
	private static Connection getConn() {//获得数据库，写成一个函数，省的每次调用数据库都重复写一遍，只需要调用这个函数就可以了
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "louyujing127";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
	
	public ArrayList<student> searchStudent(String stat) {
    	ArrayList<student> all = new ArrayList<student>();
    	
    	Connection conn = getConn();
        String sql = stat;
        PreparedStatement pstmt;
        
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
            	student newStu = new student();
            	newStu.setSid(rs.getString(1));
            	newStu.setSname(rs.getString(2));
            	newStu.setSage(rs.getString(3));
            	newStu.setSsex(rs.getString(4));
            	newStu.setSclass(rs.getString(5));
            	newStu.setDepartment(rs.getString(6));
            	newStu.setAddress(rs.getString(7));
            	all.add(newStu);
            }
            pstmt.close();//记得用完数据库要把数据库关掉
            conn.close();
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
