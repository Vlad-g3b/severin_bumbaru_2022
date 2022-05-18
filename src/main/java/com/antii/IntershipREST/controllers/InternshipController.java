package com.antii.IntershipREST.controllers;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antii.IntershipREST.dao.InternshipDaoDB;
import com.antii.IntershipREST.helper.CustomMessage;
import com.antii.IntershipREST.models.Internship;
import com.antii.IntershipREST.service.InternshipService;

@RestController
public class InternshipController {
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@ResponseBody
	@GetMapping("/getInternships")
	public	List<Internship> getAllInternships() throws ClassNotFoundException, SQLException {
		List<Internship> list = InternshipDaoDB.getInstance().getAll();
		return list;
	}
	@PutMapping("/updateInternship")
	public CustomMessage updateInternship(@RequestBody Internship item) {
		return InternshipService.getInstance().update(item);
	}
	@PutMapping("/insertInternship")
	public CustomMessage insertInternship(@RequestBody Internship item) {
		return InternshipService.getInstance().insert(item);
	}
	@DeleteMapping("/deleteInternship")
	public CustomMessage deleteInternship(@RequestParam(name="id")int id) {
		return InternshipService.getInstance().delete(id);
	}
}
