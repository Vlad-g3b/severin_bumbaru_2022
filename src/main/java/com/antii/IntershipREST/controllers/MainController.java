package com.antii.IntershipREST.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antii.IntershipREST.dao.CompanyDaoDB;
import com.antii.IntershipREST.dao.StudentDaoDB;
import com.antii.IntershipREST.dao.UserDaoDB;
import com.antii.IntershipREST.helper.CustomException;
import com.antii.IntershipREST.helper.CustomMessage;
import com.antii.IntershipREST.models.Company;
import com.antii.IntershipREST.models.IUserDetails;
import com.antii.IntershipREST.models.LoginUser;
import com.antii.IntershipREST.models.StudentDetails;
import com.antii.IntershipREST.models.TutorDetails;
import com.antii.IntershipREST.service.UserService;

@RestController
public class MainController {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/login")
	public LoginUser login(@RequestBody LoginUser user) {
		LoginUser userResponse = UserDaoDB.getInstance().checkUser(user);
		return userResponse;
	}
	
	@PostMapping("/register")
	public CustomMessage register(@RequestBody LoginUser user) throws CustomException {
		return UserService.getInstance().register(user);
	}
	
	@GetMapping("/getProfile")
	@ResponseBody
	public IUserDetails getProfile(@RequestParam(name="id") int id) throws CustomException {
		return UserService.getInstance().getDetails(id);
	}
	@GetMapping("/getCompanies")
	public List<Company> getCompanies (){
		return CompanyDaoDB.getInstance().getCompanies();
	}
	
	@GetMapping("/getStudents")
	public List<StudentDetails> getStudentsForProf (@RequestParam(name="id") int id){
		return StudentDaoDB.getInstance().getStudentsForProf(id);
	}
	
	@PostMapping("/registerTutor")
	public CustomMessage registerTutor(@RequestBody TutorDetails user) throws CustomException {
		return UserService.getInstance().registerTutor(user);
	}
	
}