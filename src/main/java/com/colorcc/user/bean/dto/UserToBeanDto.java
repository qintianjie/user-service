package com.colorcc.user.bean.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Named;

import com.colorcc.user.bean.UserBean;
import com.colorcc.user.model.User;

//@Service(value="userToBeanDto")
//@Qualifier("userToBeanDto")
@Named(value = "userToBeanDto")
public class UserToBeanDto implements Dto<User, UserBean> {

	@Override
	public UserBean transferTypetoBean(User user, Object... o) {
		if (user != null) {
			UserBean userBean = new UserBean();
			userBean.setId(user.getId());
			userBean.setEmail(user.getEmail());
			userBean.setPasswd(user.getPasswd());
			userBean.setSalt(user.getSalt());
			// userBean.setRegisterTime(DateFormat.getDateTimeInstance().format(user.getRegisterTime()));
			userBean.setRegisterTime(dateToString(user.getRegisterTime()));
			userBean.setStatus(user.getStatus());
			return userBean;
		}
		return null;
	}

	@Override
	public User transferBeanToType(UserBean userBean, Object... objects) {
		if (userBean != null) {
			User user = new User();
			user.setId(userBean.getId());
			user.setEmail(userBean.getEmail());
			user.setPasswd(userBean.getPasswd());
			user.setSalt(userBean.getSalt());
			try {
				// user.setRegisterTime(DateFormat.getDateTimeInstance().parse(userBean.getRegisterTime()));
				user.setRegisterTime(stringToDate(userBean.getRegisterTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				user.setRegisterTime(null);
			}
			user.setStatus(userBean.getStatus());
			return user;
		}
		return null;
	}

	/**
	 * Format the date.
	 * 
	 * @param date
	 * @return
	 */
	private String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	private Date stringToDate(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(strDate);
	}

}
