package com.colorcc.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.colorcc.user.bean.UserBean;
import com.colorcc.user.bean.dto.Dto;
import com.colorcc.user.mapper.UserMapper;
import com.colorcc.user.model.User;
import com.colorcc.user.service.UserService;

@Named(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name = "userMapper")
	UserMapper userMapper;

	// @Resource(name = "userRoleMapper")
	// UserRoleMapper userRoleMapper;

	@Resource(name = "userToBeanDto")
	Dto<User, UserBean> userToBeanDto;

	@Override
	public void createUser(UserBean userBean) {
		User user = userToBeanDto.transferBeanToType(userBean);
		if (user != null) {
			userMapper.insertOne(user);
			if (logger.isInfoEnabled()) {
				logger.info("Create new user, email is : " + userBean.getEmail());
			}

			// Add user roles, to save the space, all the user will have USER_ROLE role
			// User createdUser = userMapper.findUserByEmail(userBean.getEmail());
			// UserRole userRole = new UserRole();
			// userRole.setUserId(createdUser.getId());
			// userRole.setRoleId(1);
			// userRoleMapper.insertOne(userRole);
		} else {
			if (logger.isWarnEnabled()) {
				logger.warn("Failed to create a user for the parameter is null.");
			}
		}

	}

	@Override
	public void deleteUser(Integer id) {
		userMapper.deleteOne(id);
		// userRoleMapper.deleteRolesByUserID(id);
	}

	@Override
	public UserBean loadUser(Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Load a user");
		}
		User user = userMapper.selectOne(id);
		return userToBeanDto.transferTypetoBean(user);
	}

	@Override
	public void update(UserBean userBean) {
		User user = userToBeanDto.transferBeanToType(userBean);
		userMapper.updateOne(user);
	}

	@Override
	public UserBean loadUserByEmail(String email) {
		if (email == null) {
			return null;
		}

		User user = userMapper.findUserByEmail(email);
		UserBean userBean = userToBeanDto.transferTypetoBean(user);
		return userBean;
	}

	@Override
	public List<UserBean> findUser(int startRow, int fetchSize) {
		if (logger.isDebugEnabled()) {
			logger.debug("startRows : " + startRow + ", fetchSize : " + fetchSize);
		}

		List<User> users = userMapper.getUsers(startRow, fetchSize);
		List<UserBean> userBeans = new ArrayList<UserBean>();
		for (User user : users) {
			UserBean userBean = userToBeanDto.transferTypetoBean(user);
			if (userBean != null) {
				userBeans.add(userBean);
			}
		}

		return userBeans;
	}

}
