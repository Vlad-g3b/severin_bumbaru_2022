package com.antii.IntershipREST.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antii.IntershipREST.dao.CompanyDaoDB;
import com.antii.IntershipREST.dao.ProfessorDaoDB;
import com.antii.IntershipREST.dao.StudentDaoDB;
import com.antii.IntershipREST.dao.TutorDaoDB;
import com.antii.IntershipREST.dao.UserDaoDB;

public final class UserFactory {

	private static UserFactory INSTANCE = new UserFactory();

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static UserFactory getInstance() {
		return INSTANCE;
	}
	public UserDao getUser(String role) {
		
		switch (ERole.valueOf(role)) {
		case STUDENT:
			return new StudentDaoDB();
		case ADMIN:
			return new UserDaoDB();
		case TUTOR:
			return new TutorDaoDB();
		case PROFESSOR:
			return new ProfessorDaoDB();
		case COMPANY:
			return new CompanyDaoDB();
		default:
			break;

		}
		return null;
	}
}
