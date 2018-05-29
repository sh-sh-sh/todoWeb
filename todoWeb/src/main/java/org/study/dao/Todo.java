package org.study.dao;

public class Todo {
	private int idx;
	private String user_id;
	private int category;
	private String title;
	private String content;
	private String start_date;
	private String target_date;
	private String create_date;
	private boolean done;
	
	private TodoService service=new TodoDaoImpl();
	
	public Todo(int idx, String user_id, int category, String title, String content, String start_date,
			String target_date, String create_date, boolean done) {
		super();
		this.idx = idx;
		this.user_id = user_id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.start_date = start_date;
		this.target_date = target_date;
		this.create_date = create_date;
		this.done = done;
	}
	public Todo() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getCategory() {
		return category;
	}
	public String getCateName() {
		return service.getCateName(category);
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getTarget_date() {
		return target_date;
	}
	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
	
}