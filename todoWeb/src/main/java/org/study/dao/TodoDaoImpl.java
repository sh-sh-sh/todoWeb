package org.study.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.study.sec.PasswordAuthentication;

public class TodoDaoImpl implements TodoService {

	private static final String URL="jdbc:mysql://192.168.0.213/todo_db?useSSL=no&characterEncoding=utf8";
	static final String USER_NAME = "sh";
	static final String PASSWORD = "12341234";
	
	private static Connection getConnection() {
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
	public boolean addTodo(Todo todo) {
		int status = 0;
		try {
			PasswordAuthentication passAuth = new PasswordAuthentication();
			String sql="insert into todo (user_id, category, title, content,start_date,target_date) values (?, ?, ?, ?, ?, ?)";
			Connection conn=getConnection();
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, todo.getUser_id());
			ps.setInt(2, todo.getCategory());
			ps.setString(3, todo.getTitle());
			ps.setString(4, todo.getContent());
			ps.setTimestamp(5, java.sql.Timestamp.valueOf(todo.getStart_date()));
			ps.setTimestamp(6, java.sql.Timestamp.valueOf(todo.getTarget_date()));
			
			status = ps.executeUpdate();
			
			conn.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return status==1?true:false;
	}

	@Override
	public Todo getTodo(int idx) {
		Todo todo=new Todo();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			if (conn != null) {
            } else {
                System.out.println("DB연결 실패 - getTodo");
            }

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM todo WHERE idx="+idx);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String user_id = rs.getString("user_id");
				int category = rs.getInt("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String start_date = rs.getTimestamp("start_date").toString().substring(0, 16);
				String target_date = rs.getTimestamp("target_date").toString().substring(0, 16);
				String create_date = rs.getTimestamp("create_date").toString().substring(0, 16);
				boolean done = rs.getBoolean("done");
				
				
				todo=new Todo(idx,user_id,category,title,content,start_date,target_date,create_date,done);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return todo;
	}

	@Override
	public boolean deleteTodo(int idx) {
		// TODO 자동 생성된 메소드 스텁
		return false;
	}

	@Override
	public boolean updateTodo(Todo todo) {
		// TODO 자동 생성된 메소드 스텁
		return false;
	}

	@Override
	public boolean isValidTodo(int idx) {
		// TODO 자동 생성된 메소드 스텁
		return false;
	}

	@Override
	public boolean TodoDone(int idx, boolean done) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			if (conn != null) {
            } else {
                System.out.println("DB연결 실패 - TodoDone");
            }

			PreparedStatement pstmt = conn.prepareStatement("UPDATE todo SET done="+done+" WHERE idx="+idx);

			int rs=pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			if(rs==1) {
				return true;
			}else if(rs==0){
				System.out.println("에러01 - updateEmp");
				return false;
			}else {
				System.out.println("에러02 - updateteEmp");
				return false;
			}
			
		} catch (ClassNotFoundException ee) {
			// TODO �ڵ� ������ catch ���
			ee.printStackTrace();
		} catch (SQLException ee) {
			// TODO �ڵ� ������ catch ���
			ee.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Todo> getTodoList(String id,int page, int sort, String view) {
		List<Todo> list=new ArrayList<Todo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			if (conn != null) {
            } else {
                System.out.println("DB연결 실패 - getTodoList");
            }
			
			PreparedStatement pstmt;
			if(view.equals("today")) {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE "
						+ "DATE(start_date) <= DATE(NOW()) AND DATE(target_date) >= DATE(NOW()) "
						+ "AND user_id ='"+id+"' limit ?,"+pager);
			}else if(view.equals("week")) {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE "
						+ "YEARWEEK(DATE(start_date)-1) <= YEARWEEK(DATE(now())+1) "
						+ "AND YEARWEEK(DATE(target_date)-1) >= YEARWEEK(DATE(now())+1) "
						+ "AND user_id ='"+id+"' limit ?,"+pager);
			}else if(view.equals("month")) {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE YEAR(start_date) = YEAR(now()) "
						+ "AND YEAR(target_date) = YEAR(now()) "
						+ "AND MONTH(start_date) <= MONTH(now()) "
						+ "AND MONTH(target_date) >= MONTH(now()) "
						+ "AND user_id ='"+id+"' limit ?,"+pager);
			}else {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE user_id ='"+id+"' limit ?,"+pager);
			}
			
			
			pstmt.setInt(1, (page-1)*pager);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String user_id = rs.getString("user_id");
				int category = rs.getInt("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String start_date = rs.getTimestamp("start_date").toString().substring(0, 11);
				String target_date = rs.getTimestamp("target_date").toString().substring(0, 11);
				String create_date = rs.getTimestamp("create_date").toString().substring(0, 11);
				boolean done = rs.getBoolean("done");
				
				
				Todo todo=new Todo(idx,user_id,category,title,content,start_date,target_date,create_date,done);
				list.add(todo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int maxpage(String id,String view) {
		int count=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			if (conn != null) {
            } else {
                System.out.println("DB연결 실패 - maxpage");
            }

//			PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE user_id='"+id+"'");
			
			PreparedStatement pstmt;
			if(view.equals("today")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE "
						+ "DATE(start_date) <= DATE(NOW()) AND DATE(target_date) >= DATE(NOW()) "
						+ "AND user_id ='"+id+"'");
			}else if(view.equals("week")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE "
						+ "YEARWEEK(DATE(start_date)-1) <= YEARWEEK(DATE(now())+1) "
						+ "AND YEARWEEK(DATE(target_date)-1) >= YEARWEEK(DATE(now())+1) "
						+ "AND user_id ='"+id+"'");
			}else if(view.equals("month")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE YEAR(start_date) = YEAR(now()) "
						+ "AND YEAR(target_date) = YEAR(now()) "
						+ "AND MONTH(start_date) <= MONTH(now()) "
						+ "AND MONTH(target_date) >= MONTH(now()) "
						+ "AND user_id ='"+id+"'");
			}else {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE user_id ='"+id+"'");
			}
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		int maxpage=count/pager;
		if(count%pager!=0) {//나머지가 있으면
			maxpage++;
		}
		return maxpage;
	}
	
	public String getCateName(int cat_id) {
		String cateName=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			if (conn != null) {
            } else {
                System.out.println("DB연결 실패 - getCateName");
            }

			PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM todo_category WHERE cat_id="+cat_id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cateName = rs.getString("name");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return cateName;
		
	}

	@Override
	public int doneRate(String id, int sort) {
		int all=0;
		int done=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			if (conn != null) {
            } else {
                System.out.println("DB연결 실패 - maxpage");
            }

			PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE user_id='"+id+"'");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				all = rs.getInt("count(*)");
			}

			pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE done=true AND user_id='"+id+"'");//아직
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				done = rs.getInt("count(*)");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return (done*100)/all;
	}

	@Override
	public boolean isCorrectUser(String id, int todoIdx) {
		boolean rt=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			if (conn != null) {
            } else {
                System.out.println("DB연결 실패 - getCateName");
            }

			PreparedStatement pstmt = conn.prepareStatement("SELECT user_id FROM todo WHERE idx="+todoIdx);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String userId= rs.getString("user_id");
				if(userId.equals(id)) {
					rt=true;
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		
		return rt;
	}


}
