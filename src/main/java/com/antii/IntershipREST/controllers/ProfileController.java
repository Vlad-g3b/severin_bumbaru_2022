package com.antii.IntershipREST.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antii.IntershipREST.helper.CustomException;
import com.antii.IntershipREST.helper.CustomMessage;
import com.antii.IntershipREST.models.IUserDetails;
import com.antii.IntershipREST.models.StudentDetails;
import com.antii.IntershipREST.models.TutorDetails;
import com.antii.IntershipREST.service.UserService;


@RestController
public class ProfileController {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@GetMapping("/getProfile")
	@ResponseBody
	public IUserDetails getProfile(@RequestParam(name="id") int id) throws CustomException {
		return UserService.getInstance().getDetails(id);
	}
	@PutMapping("/registerTutor")
	public CustomMessage registerTutor(@RequestBody TutorDetails user) throws CustomException {
		return UserService.getInstance().registerTutor(user);
	}
	
	@PutMapping("/updateProfile")
	public CustomMessage updateProfile(@RequestBody StudentDetails user) throws CustomException {
		return UserService.getInstance().updateProfile(user);
	}
	
}
