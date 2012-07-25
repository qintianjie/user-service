package com.colorcc.user.service;

import java.util.List;

import com.colorcc.user.bean.UserBean;

public interface UserService {
	
	public void createUser(UserBean userBean);
	
	public void deleteUser(Integer id);
	
	public void update(UserBean userBean);
	
	public UserBean loadUser(Integer id);
	
	public UserBean loadUserByEmail(String email);
	
	public List<UserBean> findUser(int startRow, int fetchSize);

}
