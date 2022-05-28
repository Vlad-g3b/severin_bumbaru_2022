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
import com.antii.IntershipREST.models.Application;
import com.antii.IntershipREST.models.CompanyProfileDetails;
import com.antii.IntershipREST.models.IUserDetails;
import com.antii.IntershipREST.models.StudentDetails;

public class ApplicationDaoDB {
	private static ApplicationDaoDB INSTANCE = new ApplicationDaoDB();
	private static String SELECT = "SELECT in_id, in_theme, in_domain, in_start_date, in_duration,in_type, in_status, in_spots_av, "
			+ " in_skills, in_start_hour, in_end_hour, in_city, in_description, ap_status, co_id, co_name, co_address, co_description, co_status,ap_us_id "
			+ "FROM severin_bumbaru.t_tr_internship it "
			+ " join t_tr_applications ap on it.in_id = ap.ap_in_id "
			+ "left join t_ma_company cm on it.in_co_id = cm.co_id ";

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static ApplicationDaoDB getInstance() {
		return INSTANCE;
	}
	
	public Application getAppById (int usId,int intId) {
		Application item = new Application();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT + " where ap_us_id = ? and ap_in_id = ? ")){
			stm.setInt(1, usId);
			stm.setInt(2, intId);
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next()) {
					item = getApp(rs);
					}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return item;
	}
	public List<Application> getApps (int id) {
		List<Application> item = new ArrayList<>();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT + " where ap_us_id = ?")){
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
				while(rs.next()) {
					item.add(getApp(rs));
				}
				
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return item;
	}
	public List<Application> getAppsComp (IUserDetails user,int id) {
		List<Application> item = new ArrayList<>();
		CompanyProfileDetails comp = (CompanyProfileDetails) user;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT + "where it.in_co_id = ?")){
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
				while(rs.next()) {
					Application app = getApp(rs);
					if(comp.getCompany().getId() == app.getCompany().getId())
						item.add(app);
				}
				
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return item;
	}

	private Application getApp(ResultSet rs) throws SQLException {
		Application item = new Application();
		int i = 1;
		item.getInternship().setId(rs.getInt(i++));
		item.getInternship().setTheme(rs.getString(i++));
		item.getInternship().setDomain(rs.getString(i++));
		item.getInternship().setStartDate(rs.getDate(i++));
		item.getInternship().setDuration(rs.getInt(i++));
		item.getInternship().setType(rs.getString(i++));
		item.getInternship().setStatus(rs.getString(i++));
		item.getInternship().setSpots(rs.getInt(i++));
		item.getInternship().setSkills(rs.getString(i++).split(","));
		item.getInternship().setStartHour(rs.getTime(i++));
		item.getInternship().setEndHour(rs.getTime(i++));
		item.getInternship().setCity(rs.getString(i++));
		item.getInternship().setDescription(rs.getString(i++));
		item.setStatus(rs.getString(i++));
		item.getCompany().setId(rs.getInt(i++));
		item.getCompany().setName(rs.getString(i++));
		item.getCompany().setAddress(rs.getString(i++));
		item.getCompany().setDescription(rs.getString(i++));
		item.getCompany().setStatus(rs.getString(i++));
		item.setStudent((StudentDetails) StudentDaoDB.getInstance().getUserDetails(rs.getInt(i++)));
		return item;
	}
}
