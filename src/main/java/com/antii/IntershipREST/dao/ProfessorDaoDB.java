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
import com.antii.IntershipREST.models.UserDao;

public class ProfessorDaoDB implements UserDao {
	private static ProfessorDaoDB INSTANCE = new ProfessorDaoDB();

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static ProfessorDaoDB getInstance() {
		return INSTANCE;
	}
	private static String SELECT_BY_ID_DETAILS = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_skills from t_ma_user mu left join t_tr_profile tp on mu.us_id = tp.us_id where mu.us_id = ?";
	private static String SELECT = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_skills from t_ma_user mu left join t_tr_profile tp on mu.us_id = tp.us_id where mu.us_role ='PROFESSOR'";


	@Override
	public IUserDetails getUserDetails(int id) {
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_BY_ID_DETAILS)){
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next()) {
					return getDetails(rs);
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}		
		return null;
	}
	private IUserDetails getDetails(ResultSet rs) throws SQLException {
		int i = 1;
		ProfessorDetails user = new ProfessorDetails();
		user.setId(rs.getInt(i++));
		user.setUsername(rs.getString(i++));
		user.setPassword("############");
		user.setRole(rs.getString(i++));
		user.setProfilePic(rs.getString(i++));
		user.setProfileCv(rs.getString(i++));
		user.setProfileName(rs.getString(i++));
		user.setProfileSurname(rs.getString(i++));
		user.setProfilePhone(rs.getString(i++));
		user.setProfileAddress(rs.getString(i++));
		String  splited = rs.getString(i++);
		user.setProfileSkills(splited != null ? splited.split(",") : null);
		return user;
	}
	public List<ProfessorDetails> getAllProfessors() {
		List<ProfessorDetails> list = new ArrayList<>();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT)){
			try(ResultSet rs = stm.executeQuery()){
				while(rs.next()) {
					list.add((ProfessorDetails) getDetails(rs));
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
