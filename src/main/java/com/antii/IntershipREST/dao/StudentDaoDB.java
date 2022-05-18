package com.antii.IntershipREST.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antii.IntershipREST.helper.ConnectionHelper;
import com.antii.IntershipREST.models.IUserDetails;
import com.antii.IntershipREST.models.ProfessorDetails;
import com.antii.IntershipREST.models.StudentDetails;
import com.antii.IntershipREST.models.UserDao;

public class StudentDaoDB implements UserDao {
	private static StudentDaoDB INSTANCE = new StudentDaoDB();

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static StudentDaoDB getInstance() {
		return INSTANCE;
	}
	private static String SELECT_BY_ID_DETAILS = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_school, tp.pr_skills, tp.pr_professor_id from t_ma_user mu left join t_tr_profile tp on mu.us_id = tp.us_id where mu.us_id = ?";
	private static String SELECT_BY_ID_DETAILS_PROF = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_school, tp.pr_skills, tp.pr_professor_id from t_ma_user mu left join t_tr_profile tp on mu.us_id = tp.us_id  where tp.pr_professor_id = ?";

	@Override
	public IUserDetails getUserDetails(int id) {

		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_BY_ID_DETAILS)){
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next()) {
					return getStudentDetails(rs);
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return null;
	}

	private IUserDetails getStudentDetails(ResultSet rs) throws SQLException {
		int i = 1;
		StudentDetails student = new StudentDetails();
		student.setId(rs.getInt(i++));
		student.setUsername(rs.getString(i++));
		student.setPassword("############");
		student.setRole(rs.getString(i++));
		student.setProfilePic(rs.getString(i++));
		student.setProfileCv(rs.getString(i++));
		student.setProfileName(rs.getString(i++));
		student.setProfileSurname(rs.getString(i++));	
		student.setProfilePhone(rs.getString(i++));
		student.setProfileAddress(rs.getString(i++));
		student.setProfileSchool(rs.getString(i++));
		String  splited = rs.getString(i++);
		student.setProfileSkills(splited != null ? splited.split(",") : null);
		student.setProfessor((ProfessorDetails)ProfessorDaoDB.getInstance().getUserDetails(rs.getInt(i++)));
		return student;
	}

	public List<StudentDetails> getStudentsForProf(int id) {
		List<StudentDetails> list = new ArrayList<>();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_BY_ID_DETAILS_PROF)){
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
				while(rs.next()) {
					list.add((StudentDetails) getStudentDetails(rs));
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return list;
	}

}
