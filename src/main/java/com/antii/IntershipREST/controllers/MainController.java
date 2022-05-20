package com.antii.IntershipREST.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antii.IntershipREST.dao.ApplicationDaoDB;
import com.antii.IntershipREST.dao.CompanyDaoDB;
import com.antii.IntershipREST.dao.ProfessorDaoDB;
import com.antii.IntershipREST.dao.StudentDaoDB;
import com.antii.IntershipREST.dao.UserDaoDB;
import com.antii.IntershipREST.helper.CustomException;
import com.antii.IntershipREST.helper.CustomMessage;
import com.antii.IntershipREST.models.Application;
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
	public List<StudentDetails> getStudentsForProf (@RequestParam(name="id") String id){
		return StudentDaoDB.getInstance().getStudentsForProf(Integer.valueOf(id));
	}
	@GetMapping("/getProfessors")
	public List<ProfessorDetails> getAllProfessors (){
		return ProfessorDaoDB.getInstance().getAllProfessors();
	}
	
	@PostMapping("/doActionCompany")
	public CustomMessage doAction ( @RequestParam(name="id") String id,
								    @RequestParam(name="action") String action
			) throws CustomException {
		return CompanyService.getInstance().doAction(Integer.valueOf(id), action);
	}
	@PostMapping("/apply")
	public CustomMessage doApply (@RequestParam(name="internshipId") String internshipId,
								   @RequestParam(name="studentId") String studentId
			) throws CustomException {
		return CompanyService.getInstance().doApply(Integer.valueOf(internshipId), Integer.valueOf(studentId));
	}
	@PostMapping("/doActionApplication")
	public CustomMessage doActionApplication (@RequestParam(name="internshipId") String internshipId,
								   @RequestParam(name="studentId") String studentId,
								   @RequestParam(name="status") String status
			) throws CustomException {
		return UserService.getInstance().deActionApplication(Integer.valueOf(internshipId), Integer.valueOf(studentId),status);
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
	
	@PutMapping(value="/updateProfile")
	public CustomMessage updateProfile(@RequestBody  StudentDetails user) throws CustomException {
		LOGGER.debug(user.getProfilePhone());
		 CustomMessage msg = UserService.getInstance().updateProfile(user);
		 LOGGER.debug("aaaa");
			
		 return msg;
	}
	@PutMapping(value="/test")
	public CustomMessage updateProfile(@RequestBody TestReq user) throws CustomException {
		LOGGER.debug(user.getNume());
		return new CustomMessage();
	}
	
	@RequestMapping(value = "/testUpdate", method = RequestMethod.POST, consumes =MediaType.APPLICATION_JSON_VALUE)
	public void greetingJson(@RequestBody HttpEntity<String> httpEntity) {
	    String json = httpEntity.getBody();
	    LOGGER.debug(json);
	}
	

	@GetMapping(value="/getApplication")
	public Application getApplication(@RequestParam(name="userId") String usId,@RequestParam(name="internshipId") String intId) {
	  Application app = new Application();
	  app = ApplicationDaoDB.getInstance().getAppById(Integer.valueOf(usId),Integer.valueOf(intId));
	  LOGGER.debug("OK");
	  return app;
	}
	
	@GetMapping(value="/getApplicationList")
	public List<Application> getApplications(@RequestParam(name="id") String usId) {
	  List<Application> app = new ArrayList<>();
	  app = ApplicationDaoDB.getInstance().getApps(Integer.valueOf(usId));
	  LOGGER.debug("OK");
	  return app;
	}
	
	@GetMapping(value="/getApplicationListForComp")
	public List<Application> getApplicationCompany(@RequestParam(name="id") String userId) {
	  List<Application> app = new ArrayList<>();
	  CompanyProfileDetails comp = (CompanyProfileDetails) CompanyDaoDB.getInstance().getUserDetails(Integer.valueOf(userId));
	  LOGGER.debug(comp.toString());
	  app = ApplicationDaoDB.getInstance().getAppsComp(comp, comp.getCompany().getId());
	  LOGGER.debug("OK");
	  return app;
	}
	
	
}