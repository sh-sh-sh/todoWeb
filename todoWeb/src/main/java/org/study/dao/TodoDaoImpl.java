package org.study.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.study.sec.PasswordAuthentication;

public class TodoDaoImpl implements TodoService {

	private static final String URL="jdbc:mysql://192.168.0.213/todo_db?useSSL=no&characterEncoding=utf8";
	static final String USER_NAME = "sh";
	static final String PASSWORD = "12341234";
	
	private static Connection getConnection() {//DB에 연결하는 메서드
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		}catch(Exception e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
		}
		
		return conn;
	}

	@Override
	public boolean addTodo(Todo todo) {//할일을 추가하고 성공 여부를 반환한다
		int status = 0;
		try {
			PasswordAuthentication passAuth = new PasswordAuthentication();
			String sql="insert into todo (user_id, category, title, content,start_date,target_date) "
					+ "values (?, ?, ?, ?, ?, ?)";
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
			System.out.println(todo.getUser_id()+" : "+todo.getStart_date()+","+todo.getTarget_date());
			ex.printStackTrace();
		}
		
		return status==1?true:false;
	}

	@Override
	public Todo getTodo(int idx) {
		//idx에 해당하는 할일을 가져와 해당 할일을 Todo 객체로 반환한다
		Todo todo=new Todo();
		try {
			Connection conn = getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM todo WHERE idx=?");
			pstmt.setInt(1, idx);
			
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
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return todo;
	}

	@Override
	public boolean deleteTodo(int idx) {//idx에 해당하는 할일을 삭제하고 성공 여부를 반환한다
		try {
			Connection conn = getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM todo WHERE idx=?");
			pstmt.setInt(1, idx);
			
			int rs=pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			if(rs==1) {
				return true;
			}else if(rs==0){
				System.out.println("에러01 - deleteTodo");
				return false;
			}else {
				System.out.println("에러02 - deleteTodo");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean updateTodo(Todo todo) {//Todo 객체를 가져와 해당 할일을 업데이트(수정)하고 성공 여부를 반환함
		try {
			Connection conn = getConnection();
			
			String sql="UPDATE todo SET category=?, title=?, content=?, start_date=?, target_date=? WHERE idx=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, todo.getCategory());
			ps.setString(2, todo.getTitle());
			ps.setString(3, todo.getContent());
			ps.setTimestamp(4, java.sql.Timestamp.valueOf(todo.getStart_date()));
			ps.setTimestamp(5, java.sql.Timestamp.valueOf(todo.getTarget_date()));
			ps.setInt(6, todo.getIdx());
			
			
			int rs=ps.executeUpdate();
			
			ps.close();
			conn.close();
			if(rs==1) {
				return true;
			}else if(rs==0){
				System.out.println("에러01 - updateTodo");
				return false;
			}else {
				System.out.println("에러02 - updateTodo");
				return false;
			}
			
		}  catch (SQLException ee) {
			// TODO �ڵ� ������ catch ���
			ee.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean TodoDone(int idx, boolean done) {//할일 완료 상태를 done값에 따라 바꾸고 성공 여부를 반환함
		try {
			Connection conn = getConnection();

			PreparedStatement pstmt = conn.prepareStatement("UPDATE todo SET done=? WHERE idx=?");
			pstmt.setBoolean(1, done);
			pstmt.setInt(2, idx);

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
			
		}  catch (SQLException ee) {
			// TODO �ڵ� ������ catch ���
			ee.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Todo> getTodoList(String id,int page, int sort, String view) {
		//view에 해당하는 id의 할일을 page에 맞게 List에 담아 반환하는 메서드 
		List<Todo> list=new ArrayList<Todo>();
		try {
			Connection conn = getConnection();
			
			PreparedStatement pstmt;
			if(view.equals("today")) {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE "
						+ "DATE(start_date) <= DATE(NOW()) AND DATE(target_date) >= DATE(NOW()) "
						+ "AND user_id =? limit ?,"+pager);
			}else if(view.equals("week")) {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE "
						+ "YEARWEEK(DATE(start_date+interval -1 day)) <= YEARWEEK(DATE(now()+interval -1 day)) "
						+ "AND YEARWEEK(DATE(target_date+interval -1 day)) >= YEARWEEK(DATE(now()+interval -1 day)) "
						+ "AND user_id =? limit ?,"+pager);
				//date에 interval -1 day를 하는 이유는 yearweek의 일주일 기준이 일요일~월요일이기 때문에
				//월요일~일요일을 같은 주로 묶으려면 각 날짜에 1일을 뺀 후 yearweek를 구해야 함
			}else if(view.equals("month")) {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE YEAR(start_date) = YEAR(now()) "
						+ "AND YEAR(target_date) = YEAR(now()) "
						+ "AND MONTH(start_date) <= MONTH(now()) "
						+ "AND MONTH(target_date) >= MONTH(now()) "
						+ "AND user_id =? limit ?,"+pager);
			}else if(view.equals("done")) {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE done=true AND user_id =? limit ?,"+pager);
			}else if(view.equals("undone")) {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE done=false AND user_id =? limit ?,"+pager);
			}else {
				pstmt = conn.prepareStatement("SELECT * FROM todo WHERE user_id =? limit ?,"+pager);
			}
			
			pstmt.setString(1, id);
			pstmt.setInt(2, (page-1)*pager);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int idx = rs.getInt("idx");
				String user_id = rs.getString("user_id");
				int category = rs.getInt("category");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String start_date = rs.getTimestamp("start_date").toString().substring(0, 11);
				String target_date = rs.getTimestamp("target_date").toString().substring(0, 16);
				String create_date = rs.getTimestamp("create_date").toString().substring(0, 11);
				boolean done = rs.getBoolean("done");
				
				Todo todo=new Todo(idx,user_id,category,title,content,start_date,target_date,create_date,done);
				list.add(todo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int maxpage(String id,String view) {
		//veiw에 해당하는 id의 할일이 총 몇 페이지인지 반환해줌
		int count=0;
		try {
			Connection conn = getConnection();
			
			PreparedStatement pstmt;
			if(view.equals("today")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE "
						+ "DATE(start_date) <= DATE(NOW()) AND DATE(target_date) >= DATE(NOW()) "
						+ "AND user_id =?");
			}else if(view.equals("week")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE "
						+ "YEARWEEK(DATE(start_date+interval -1 day)) <= YEARWEEK(DATE(now()+interval -1 day)) "
						+ "AND YEARWEEK(DATE(target_date+interval -1 day)) >= YEARWEEK(DATE(now()+interval -1 day)) "
						+ "AND user_id =?");
			}else if(view.equals("month")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE YEAR(start_date) = YEAR(now()) "
						+ "AND YEAR(target_date) = YEAR(now()) "
						+ "AND MONTH(start_date) <= MONTH(now()) "
						+ "AND MONTH(target_date) >= MONTH(now()) "
						+ "AND user_id =?");
			}else if(view.equals("done")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE done=true AND user_id =?");
			}else if(view.equals("undone")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE done=false AND user_id =?");
			}else {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE user_id =?");
			}
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			rs.close();
			pstmt.close();
			conn.close();
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
		//cat_id에 해당하는 카테고리 이름을 반환한다.
		String cateName=null;
		try {
			Connection conn = getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM todo_category WHERE cat_id=?");
			pstmt.setInt(1, cat_id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cateName = rs.getString("name");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		return cateName;
		
	}

	@SuppressWarnings("resource")
	@Override
	public int doneRate(String id, String view) {
		//view에 해당하는 id의 할일중 몇퍼센트가 완료되었는지 int타입으로 반환함
		int all=0;//총 할일 개수를 담을 변수
		int done=0;//완료된 할일 개수를 담을 변수
		try {
			Connection conn = getConnection();

			PreparedStatement pstmt;
			if(view.equals("today")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE "
						+ "DATE(start_date) <= DATE(NOW()) AND DATE(target_date) >= DATE(NOW()) "
						+ "AND user_id =?");
			}else if(view.equals("week")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE "
						+ "YEARWEEK(DATE(start_date+interval -1 day)) <= YEARWEEK(DATE(now()+interval -1 day)) "
						+ "AND YEARWEEK(DATE(target_date+interval -1 day)) >= YEARWEEK(DATE(now()+interval -1 day)) "
						+ "AND user_id =?");
			}else if(view.equals("month")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE YEAR(start_date) = YEAR(now()) "
						+ "AND YEAR(target_date) = YEAR(now()) "
						+ "AND MONTH(start_date) <= MONTH(now()) "
						+ "AND MONTH(target_date) >= MONTH(now()) "
						+ "AND user_id =?");
			}else {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE user_id =?");
			}
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				all = rs.getInt("count(*)");
			}
			if(all==0) {
				return 0;
			}

			if(view.equals("today")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE done=true AND "
						+ "DATE(start_date) <= DATE(NOW()) AND DATE(target_date) >= DATE(NOW()) "
						+ "AND user_id =?");
			}else if(view.equals("week")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE done=true AND "
						+ "YEARWEEK(DATE(start_date+interval -1 day)) <= YEARWEEK(DATE(now()+interval -1 day)) "
						+ "AND YEARWEEK(DATE(target_date+interval -1 day)) >= YEARWEEK(DATE(now()+interval -1 day)) "
						+ "AND user_id =?");
			}else if(view.equals("month")) {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE done=true AND "
						+ "YEAR(start_date) = YEAR(now()) "
						+ "AND YEAR(target_date) = YEAR(now()) "
						+ "AND MONTH(start_date) <= MONTH(now()) "
						+ "AND MONTH(target_date) >= MONTH(now()) "
						+ "AND user_id =?");
			}else {
				pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE done=true AND user_id =?");
			}
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				done = rs.getInt("count(*)");
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		
		return (done*100)/all;
	}

	@Override
	public boolean isCorrectUser(String id, int todoIdx) {
		//todoidx에 해당하는 todo의 user_id가 id와 동일한지 체크해 반환함
		boolean rt=false;
		try {
			Connection conn = getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT user_id FROM todo WHERE idx=?");
			pstmt.setInt(1, todoIdx);
			
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
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		
		return rt;
	}

	@Override
	public int todoCount(String id) {
		//id에 해당하는 유저의 등록된 할일 개수를 반환함
		int rt=-999;
		try {
			Connection conn = getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT count(*) FROM todo WHERE user_id =?");
			pstmt.setString(1, id);
		
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rt = rs.getInt("count(*)");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		
		return rt;
	}

	@Override
	public int doneCount(String id) {
		//id에 해당하는 유저의 모든 할일 중 완료된 할일 개수를 반환함
		int rt=-999;
		try {
			Connection conn = getConnection();

			PreparedStatement pstmt = 
					conn.prepareStatement("SELECT count(*) FROM todo WHERE done=true AND user_id =?");
			pstmt.setString(1, id);
		
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rt = rs.getInt("count(*)");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		
		return rt;
	}


}
