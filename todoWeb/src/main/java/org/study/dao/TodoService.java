package org.study.dao;

import java.util.List;

public interface TodoService {
	
	int pager=10;
	
	public boolean addTodo(Todo todo);
	
	public Todo getTodo(int idx);
	
	public boolean deleteTodo(int idx);
	
	public boolean updateTodo(Todo todo);
	
	public boolean isValidTodo(int idx);//존재하는 투두인지
	
	public boolean TodoDone(int idx, boolean done);
	
	public List<Todo> getTodoList(String id, int page, int sort, String view);
	
	public int maxpage(String id,String view);
	
	public String getCateName(int cat_id);
	
	public int doneRate(String id, String view);
	
	public boolean isCorrectUser(String id, int todoIdx);
	
}
