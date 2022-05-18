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
import com.antii.IntershipREST.models.Company;
import com.antii.IntershipREST.models.CompanyProfileDetails;
import com.antii.IntershipREST.models.IUserDetails;
import com.antii.IntershipREST.models.UserDao;

public class CompanyDaoDB implements UserDao {
	private static CompanyDaoDB INSTANCE = new CompanyDaoDB();
	private static String SELECT_BY_ID = "select co_id, co_name, co_address, co_description, co_status from t_ma_company where co_id = ?";
	private static String SELECT_ALL = "select co_id, co_name, co_address, co_description, co_status from t_ma_company";
	private static String SELECT_BY_ID_DETAILS = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_skills from t_ma_user mu left join t_tr_profile tp on mu.us_id = tp.us_id where mu.us_id = ?";
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static CompanyDaoDB getInstance() {
		return INSTANCE;
	}
	
	public Company getCompanyById(int id) {
		Company company = new Company();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_BY_ID)){
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next()) {
					company = getCompany(rs);
				}
				
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return company;
	}


	private Company getCompany(ResultSet rs) throws SQLException {
		Company comp = new Company();
		int i = 1;
		comp.setId(rs.getInt(i++));
		comp.setName(rs.getString(i++));
		comp.setAddress(rs.getString(i++));
		comp.setDescription(rs.getString(i++));
		comp.setStatus(rs.getString(i++));
		return comp;
	}

	public List<Company> getCompanies() {
		List<Company> list = new ArrayList<>();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_ALL)){
			try(ResultSet rs = stm.executeQuery()){
				while(rs.next()) {
					list.add(getCompany(rs));
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return list;
	}

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
		}			return null;
	}
	private IUserDetails getDetails(ResultSet rs) throws SQLException {
		int i = 1;
		CompanyProfileDetails user = new CompanyProfileDetails();
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
}
