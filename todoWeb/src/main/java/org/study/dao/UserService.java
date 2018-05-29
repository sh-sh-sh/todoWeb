package org.study.dao;

import java.util.List;

public interface UserService {
	public boolean addUser(User user);
	
	public User getUser(String id);
	
	public boolean deleteUser(String id);
	
	public boolean updateUser(User user);
	
	public boolean isValidUser(String id);//존재하는 유저인지
	
	public boolean passwordCheck(String id,String password);
	
}
