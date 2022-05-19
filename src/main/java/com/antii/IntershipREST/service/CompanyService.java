package com.antii.IntershipREST.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antii.IntershipREST.dao.CompanyDaoDB;
import com.antii.IntershipREST.helper.CustomMessage;
import com.antii.IntershipREST.helper.MessageHelper;
import com.antii.IntershipREST.models.CompanyProfileDetails;

public class CompanyService {
	private static CompanyService INSTANCE = new CompanyService();
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	public static CompanyService getInstance() {
		return INSTANCE;
	}	
	
	public CustomMessage doAction(int id, String action) {
		CustomMessage msg = new CustomMessage();
		int resp = 0;
		try {
		resp = CompanyDaoDB.getInstance().doAction(id, action);
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

	public CustomMessage doApply(int internshipId, int studentId) {
		CustomMessage msg = new CustomMessage();
		int resp = 0;
		try {
		resp = CompanyDaoDB.getInstance().doApply(internshipId, studentId);
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
	public CustomMessage insert(CompanyProfileDetails company) {
		CustomMessage msg = new CustomMessage();
		int resp = 0;
		try {
		resp = CompanyDaoDB.getInstance().insert(company);
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
	
	public CustomMessage update(CompanyProfileDetails company) {
		CustomMessage msg = new CustomMessage();
		int resp = 0;
		try {
		resp = CompanyDaoDB.getInstance().update(company);
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
