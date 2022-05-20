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
	private static final String UPDATE_PROFILE = "update t_tr_profile set pr_profile_pic = ?, pr_phone =? where pr_id = ?";
	private static String SELECT_BY_ID = "select co_id, co_name, co_address, co_description, co_status from t_ma_company where co_id = ?";
	private static String SELECT_ALL = "select co_id, co_name, co_address, co_description, co_status from t_ma_company";
	private static String SELECT_BY_ID_DETAILS = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_skills from t_ma_user mu left join t_tr_profile tp on mu.us_id = tp.us_id where mu.us_id = ?";
	private static String ACTION = "update t_ma_company set co_status = ? where co_id = ?";
	private static String APPLY = "insert into t_tr_applications (ap_us_id, ap_in_id, ap_application_date, ap_status) values (?,?,sysdate(),'PENDING')";
	private static String INSERT = "insert into t_ma_company (co_name, co_address, co_description, co_status) values (?,?,?,'PENDING')";
	private static String UPDATE = "update t_ma_company set co_name = ?, co_address = ?, co_description = ?, co_status = ? where co_id = ?";
	private static String INSERT_PROFILE = "insert into t_ma_company (us_id) values (?)";
	private static String GET_COMP_FOR_USER = "SELECT  co_id, co_name, co_address, co_description, co_status FROM t_company_users cu join t_ma_user ma on ma.us_id = cu.cu_us_id join t_ma_company co on co.co_id = cu.cu_co_id where us_id = ?";
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
	public Company getCompanyForUser(int id) {
		Company company = new Company();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(GET_COMP_FOR_USER)){
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
		user.setProfilePhone(rs.getString(i++));
		user.setCompany(getCompanyForUser(user.getId()));
		return user;
	}

	public int doAction(int id, String action) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(ACTION)){
			stm.setString(1,action);
			stm.setInt(2, id);
			resp =  stm.executeUpdate();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return resp;
	}
	public int insert(CompanyProfileDetails company) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(INSERT)){
			stm.setString(1,company.getCompany().getName());
			stm.setString(2,company.getCompany().getAddress());
			stm.setString(3,company.getCompany().getDescription());
			stm.setString(4,company.getCompany().getStatus());
			resp =  stm.executeUpdate();
			try(PreparedStatement stm2 = con.prepareStatement(INSERT_PROFILE)){
				stm2.setInt(1, company.getId());
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return resp;
	}
	public int update(CompanyProfileDetails company) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(UPDATE)){
			stm.setString(1,company.getCompany().getName());
			stm.setString(2,company.getCompany().getAddress());
			stm.setString(3,company.getCompany().getDescription());
			stm.setString(4,company.getCompany().getStatus());
			stm.setInt(5, company.getId());
			resp =  stm.executeUpdate();
			try(PreparedStatement stm2 = con.prepareStatement(UPDATE_PROFILE)){
				stm2.setString(1, company.getProfilePic());
				stm2.setString(2, company.getProfilePhone());
				stm2.executeUpdate();
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return resp;
	}

	public int doApply(int internshipId, int studentId) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(APPLY)){
			stm.setInt(1, studentId);
			stm.setInt(2,internshipId);
			resp =  stm.executeUpdate();
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return resp;
	}
}
