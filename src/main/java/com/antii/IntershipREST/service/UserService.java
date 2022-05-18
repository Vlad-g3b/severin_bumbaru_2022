package com.antii.IntershipREST.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antii.IntershipREST.dao.UserDaoDB;
import com.antii.IntershipREST.helper.CustomException;
import com.antii.IntershipREST.helper.CustomMessage;
import com.antii.IntershipREST.helper.MessageHelper;
import com.antii.IntershipREST.models.ERole;
import com.antii.IntershipREST.models.IUserDetails;
import com.antii.IntershipREST.models.LoginUser;
import com.antii.IntershipREST.models.TutorDetails;
import com.antii.IntershipREST.models.User;
import com.antii.IntershipREST.models.UserFactory;

public class UserService {

	private static UserService INSTANCE = new UserService();
		Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static UserService getInstance() {
		return INSTANCE;
	}

	public CustomMessage register(LoginUser user) throws CustomException {
		CustomMessage msg = new CustomMessage();
		LoginUser userResponse = UserDaoDB.getInstance().checkUser(user);
		if(userResponse == null) {
			 try {
				UserDaoDB.getInstance().register(user);
				msg.setMessage(MessageHelper.SUCCESS);
			} catch (SQLException e) {
				msg.setErrCode(e.getMessage());
				msg.setMessage("User Already Exists!");
			}
		} else {
			msg.setMessage("User Already Exists!");
		}
		return msg;
	}

	
	
	public IUserDetails getDetails(int id) {
		User user = UserDaoDB.getInstance().getUserById(id);
		IUserDetails userDetails = UserFactory.getInstance().getUser(user.getRole()).getUserDetails(id);
		return userDetails;
	}

	public CustomMessage registerTutor(TutorDetails user) {
		CustomMessage msg = new CustomMessage();
		LoginUser temp = new LoginUser((User)user);
		LoginUser userResponse = UserDaoDB.getInstance().checkUser(temp);
		if(userResponse == null) {
			 try {
				 temp.setRole(ERole.TUTOR.name());
				UserDaoDB.getInstance().register(temp);
				msg.setMessage(MessageHelper.SUCCESS);
			} catch (SQLException e) {
				msg.setErrCode(e.getMessage());
				msg.setMessage("User Already Exists!");
			}
		} else {
			msg.setMessage("User Already Exists!");
		}
		return msg;
	}
}
