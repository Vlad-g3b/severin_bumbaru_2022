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
import com.antii.IntershipREST.models.Assessment;
import com.antii.IntershipREST.models.EStatus;
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
	private static final String INSERT_APPLY = "insert into t_tr_assessment (as_in_id, as_us_id_student,as_us_id_tutor) values (?,?,?)";

	private static final String UPDATE_PROF = " UPDATE `t_tr_profile`  SET  `pr_profile_pic` = ?,  `pr_cv` = ?,  `pr_name` = ?,  `pr_surname` = ?,  `pr_phone` = ?,  `pr_address` = ?,  `pr_school` = ?,  `pr_skills` = ?,  `pr_professor_id` = ? WHERE `pr_id` = ?;";
	private static String SELECT_BY_ID_DETAILS = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_school, tp.pr_skills, tp.pr_professor_id from t_ma_user mu left join t_tr_profile tp on mu.us_id = tp.us_id where mu.us_id = ?";
	private static String SELECT_BY_ID_DETAILS_PROF = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_school, tp.pr_skills, tp.pr_professor_id from t_ma_user mu left join t_tr_profile tp on mu.us_id = tp.us_id  where tp.pr_professor_id = ?";
	private static String DO_ACTION = "update t_tr_application set ap_application_date_response = sysdate(), ap_status = ? where ap_us_id = ? and ap_in_id = ?";
	private static String INSERT_ASS = "insert into t_tr_assessment (as_in_id, as_us_id_student,as_us_id_tutor,ass_evaluation_note, as_progress, as_date) values (?,?,?,?,?,?)" ; 
	private static String UPDATE_ASS = "update t_tr_assessment set ass_evaluation_note = ?, as_progress = ?, as_date = ? where as_id = ? " ; 

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

	public int insertAss(Assessment item) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(INSERT_ASS)){
			stm.setInt(1, item.getInternship().getId());
			stm.setInt(2, item.getStudent().getId());
			stm.setInt(3, item.getTutor().getId());
			stm.setDouble(4, item.getEvNote());
			stm.setDouble(5, item.getProgress()) ;
			stm.setDate(6, item.getDate());
			resp =  stm.executeUpdate();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return resp;
	}
	public int updateAss(Assessment item) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(UPDATE_ASS)){
			stm.setDouble(1, item.getEvNote());
			stm.setDouble(2, item.getProgress()) ;
			stm.setDouble(3, item.getProgress());
			stm.setDate(4, item.getDate());
			stm.setInt(5, item.getId());
			resp =  stm.executeUpdate();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return resp;
	}
	
	public int doAction(int internshipId, int studentId, String status) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(DO_ACTION)){
			stm.setString(1, status);
			stm.setInt(2, studentId);
			stm.setInt(3, internshipId);
			resp =  stm.executeUpdate();
			if(EStatus.ACCEPTED.name().equals(status)) {
				try(PreparedStatement stm2 = con.prepareStatement(INSERT_APPLY)){
					
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return resp;
	}

	public int updateProfile(StudentDetails user) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(UPDATE_PROF)){
			int i = 1;
			stm.setString(i++, user.getProfilePic());
			stm.setString(i++, user.getProfileCv());
			stm.setString(i++, user.getProfileName());
			stm.setString(i++, user.getProfileSurname());
			stm.setString(i++, user.getProfilePhone());
			stm.setString(i++, user.getProfileAddress());
			stm.setString(i++, user.getProfileSchool());
			stm.setString(i++, String.join(",",user.getProfileSkills()));
			stm.setInt(i++, user.getProfessor() != null? user.getProfessor().getId() : null);
			stm.setInt(i++,user.getId());
			resp =  stm.executeUpdate();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return resp;
	}

 
}
