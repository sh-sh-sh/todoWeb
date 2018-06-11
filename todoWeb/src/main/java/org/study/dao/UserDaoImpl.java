package org.study.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.study.sec.PasswordAuthentication;

public class UserDaoImpl implements UserService {
	
	private static final String URL="jdbc:mysql://192.168.0.213/todo_db?useSSL=no&characterEncoding=utf8";
	static final String USER_NAME = "sh";
	static final String PASSWORD = "12341234";
	
	private static Connection getConnection() {//db에 연결함
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	@Override
	public boolean addUser(User user) {//user를 DB에 insert하고 성공 여부를 반환함
		int status = 0;
		try {
			PasswordAuthentication passAuth = new PasswordAuthentication();
			String sql="insert into todo_user (id, password, name, email) values (?, ?, ?, ?)";
			Connection conn=getConnection();
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			String token = passAuth.hash(user.getPassword().toCharArray());
			ps.setString(2, token);
			ps.setString(3, user.getName());
			ps.setString(4, user.getEmail());
			
			status = ps.executeUpdate();
			
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return status==1?true:false;
	}

	@Override
	public User getUser(String id) {
		//DB에서 id에 해당하는 유저를 User 객체로 반환함
		User user=new User();
		try {
			Connection conn=getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM todo_user WHERE id=?");
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String  pw= rs.getString("password");
				String name = rs.getString("name");
				String  email= rs.getString("email");
				
				user=new User(id,pw,name,email);
			}else {
				return null;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean deleteUser(String id) {
		//db에서 id에 해당하는 유저를 지우고 성공 여부를 반환함
		try {
			Connection conn=getConnection();

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM todo_user WHERE id=?");
			pstmt.setString(1, id);
			int rs=pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			if(rs==1) {
				return true;
			}else if(rs==0){
				System.out.println("에러01 - deleteUser");
				return false;
			}else {
				System.out.println("에러02 - deleteUser");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		//db에서 user를 업데이트하고 성공 여부를 반환함
		try {
			PasswordAuthentication passAuth = new PasswordAuthentication();

			Connection conn=getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("UPDATE todo_user SET "
					+ "password=?, name=?, email=? WHERE id=?");
			String token = passAuth.hash(user.getPassword().toCharArray());
			pstmt.setString(1, token);
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getId());
			
			int rs=pstmt.executeUpdate();
			 
			pstmt.close();
			conn.close();
			if(rs==1) {
				return true;
			}else if(rs==0){
				System.out.println("에러01 - updateUser");
				return false;
			}else {
				System.out.println("에러02 - updateUser");
				return false;
			}
			
		} catch (SQLException ee) {
			// TODO �ڵ� ������ catch ���
			ee.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isValidUser(String id) {
		//db에 id에 해당하는 유저가 있으면 true를 반환함
		try {
			Connection conn=getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM todo_user WHERE id=?");
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				rs.close();
				pstmt.close();
				conn.close();
				return true;
			}else {
				rs.close();
				pstmt.close();
				conn.close();
				return false;
			}
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean passwordCheck(String id, String password) {
		//id에 해당하는 유저의 패스워드가 password와 일치하면 true를 반환함
		PasswordAuthentication passAuth = new PasswordAuthentication();
		User user=getUser(id);
		if(passAuth.authenticate(password.toCharArray(), user.getPassword())) {
			return true;
		}
		return false;
	}
}
