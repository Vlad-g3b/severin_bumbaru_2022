package com.antii.IntershipREST.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antii.IntershipREST.dao.CompanyDaoDB;
import com.antii.IntershipREST.dao.ProfessorDaoDB;
import com.antii.IntershipREST.dao.StudentDaoDB;
import com.antii.IntershipREST.dao.UserDaoDB;
import com.antii.IntershipREST.helper.CustomException;
import com.antii.IntershipREST.helper.CustomMessage;
import com.antii.IntershipREST.models.Assessment;
import com.antii.IntershipREST.models.Company;
import com.antii.IntershipREST.models.CompanyProfileDetails;
import com.antii.IntershipREST.models.LoginUser;
import com.antii.IntershipREST.models.ProfessorDetails;
import com.antii.IntershipREST.models.StudentDetails;
import com.antii.IntershipREST.service.CompanyService;
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
	
	@GetMapping("/getCompanies")
	public List<Company> getCompanies (){
		return CompanyDaoDB.getInstance().getCompanies();
	}
	
	@GetMapping("/getStudents")
	public List<StudentDetails> getStudentsForProf (@RequestParam(name="id") int id){
		return StudentDaoDB.getInstance().getStudentsForProf(id);
	}
	@GetMapping("/getProfessors")
	public List<ProfessorDetails> getAllProfessors (){
		return ProfessorDaoDB.getInstance().getAllProfessors();
	}
	
	@PostMapping("/doActionCompany")
	public CustomMessage doAction ( @RequestParam(name="id") int id,
								    @RequestParam(name="action") String action
			) throws CustomException {
		return CompanyService.getInstance().doAction(id, action);
	}
	@PostMapping("/apply")
	public CustomMessage doApply (@RequestParam(name="internshipId") int internshipId,
								   @RequestParam(name="studentId") int studentId
			) throws CustomException {
		return CompanyService.getInstance().doApply(internshipId, studentId);
	}
	@PostMapping("/doActionApplication")
	public CustomMessage doActionApplication (@RequestParam(name="internshipId") int internshipId,
								   @RequestParam(name="studentId") int studentId,
								   @RequestParam(name="status") String status
			) throws CustomException {
		return UserService.getInstance().deActionApplication(internshipId, studentId,status);
	}
	
	@PutMapping("/insertAssessment")
	public CustomMessage insertAss (@RequestBody Assessment item) throws CustomException {
		return UserService.getInstance().insertAss(item);
	}
	@PutMapping("/updateAssessment")
	public CustomMessage updateAss (@RequestBody Assessment item) throws CustomException {
		return UserService.getInstance().updateAss(item);
	}
	
	@PutMapping("/registerCompany")
	public CustomMessage registerCompany (@RequestBody CompanyProfileDetails company) throws CustomException {
		return CompanyService.getInstance().insert(company);
	}
	
	@PutMapping("/updateCompany")
	public CustomMessage updateCompany (@RequestBody CompanyProfileDetails company) throws CustomException {
		return CompanyService.getInstance().update(company);
	}
}