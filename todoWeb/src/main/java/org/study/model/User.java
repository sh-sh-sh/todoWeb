package org.study.model;

import org.study.dao.TodoDaoImpl;
import org.study.dao.TodoService;

public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	
	private TodoService service=new TodoDaoImpl();
	
	
	public User() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	
	public User(String id, String password, String name, String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public String toString() {
//		return "<br>["+id+"]"+name+" | "+email;
//	}
	
	public int getTodoNum() {
		return service.todoCount(id);
	}
	
	public int getDoneNum() {
		return service.doneCount(id);
	}




}
