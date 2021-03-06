package org.study.dao;

import java.util.List;

import org.study.model.User;

public interface UserService {
	public boolean addUser(User user);
	//user를 DB에 insert하고 성공 여부를 반환함
	
	public User getUser(String id);
	//DB에서 id에 해당하는 유저를 User 객체로 반환함
	
	public boolean deleteUser(String id);
	//db에서 id에 해당하는 user를 지우고 성공 여부를 반환함
	
	public boolean updateUser(User user);
	//db에서 user를 업데이트하고 성공 여부를 반환함
	
	public boolean isValidUser(String id);
	//db에 id에 해당하는 유저가 있으면 true를 반환함
	
	public boolean passwordCheck(String id,String password);
	//id에 해당하는 유저의 패스워드가 password와 일치하면 true를 반환함
	
}
