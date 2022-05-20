package com.antii.IntershipREST.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping("/getInternships")
	public	List<Internship> getAllInternships(@RequestBody Map<String, String[]> map) throws ClassNotFoundException, SQLException {
		//Map<String,String[]> map = new HashMap<>();
	//	map.put("TYPE", new String[]{"remote","online"});
	//	map.put("DOMAIN", new String[]{"remote","online"});
		LOGGER.debug(map.toString());
		List<Internship> list = new ArrayList<>();
		list = InternshipDaoDB.getInstance().getAll(map);
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
