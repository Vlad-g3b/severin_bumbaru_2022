package com.antii.IntershipREST.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antii.IntershipREST.dao.InternshipDaoDB;
import com.antii.IntershipREST.helper.CustomMessage;
import com.antii.IntershipREST.helper.MessageHelper;
import com.antii.IntershipREST.models.Internship;

public class InternshipService {
	private static InternshipService INSTANCE = new InternshipService();
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	public static InternshipService getInstance() {
	return INSTANCE;
	}	

	public CustomMessage update(Internship item) {
	CustomMessage msg = new CustomMessage();
	try {
		int resp = InternshipDaoDB.getInstance().update(item);
		if(resp == 1) {
			msg.setMessage(MessageHelper.SUCCESS);
		}else {
			msg.setMessage(MessageHelper.FAILURE);		
		}
	} catch (Exception e) {
		msg.setErrCode(e.getMessage());
		msg.setMessage(MessageHelper.FAILURE);
	}
	return msg;
	}
	
	
	public CustomMessage insert(Internship item) {
		CustomMessage msg = new CustomMessage();
		try {
			int resp = InternshipDaoDB.getInstance().insert(item);
			if(resp == 1) {
				msg.setMessage(MessageHelper.SUCCESS);
			}else {
				msg.setMessage(MessageHelper.FAILURE);		
			}
		} catch (Exception e) {
			msg.setErrCode(e.getMessage());
			msg.setMessage(MessageHelper.FAILURE);
		}
		return msg;
	}
	
	public CustomMessage delete(int  id) {
		CustomMessage msg = new CustomMessage();
		try {
			int resp = InternshipDaoDB.getInstance().delete(id);
			if(resp == 1) {
				msg.setMessage(MessageHelper.SUCCESS);
			}else {
				msg.setMessage(MessageHelper.FAILURE);		
			}
		} catch (Exception e) {
			msg.setErrCode(e.getMessage());
			msg.setMessage(MessageHelper.FAILURE);
		}
		return msg;
	}
}
