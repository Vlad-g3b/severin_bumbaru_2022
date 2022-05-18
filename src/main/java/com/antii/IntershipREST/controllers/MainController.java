package com.antii.IntershipREST.controllers;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.antii.IntershipREST.dao.InternshipDaoDB;

import com.antii.IntershipREST.models.Internship;

@RestController
public class MainController {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	@ResponseBody
	@GetMapping("/all-internships")
	public	List<Internship> getAllInternships() throws ClassNotFoundException, SQLException {
		List<Internship> list = InternshipDaoDB.getInstance().getAll();
		return list;
	}
}
