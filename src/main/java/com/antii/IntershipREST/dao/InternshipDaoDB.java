package com.antii.IntershipREST.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.antii.IntershipREST.helper.ConnectionHelper;
import com.antii.IntershipREST.models.Company;
import com.antii.IntershipREST.models.Internship;

public class InternshipDaoDB {
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static InternshipDaoDB INSTANCE = new InternshipDaoDB();
	private static String SELECT = "select in_id, in_theme, in_domain, in_duration, in_type, in_status, in_spots_av, in_start_date, in_skills, in_co_id, in_start_hour, in_end_hour,in_city, in_description from t_tr_internship";
	private static String SELECT_BY_ID = "select in_id, in_theme, in_domain, in_duration, in_type, in_status, in_spots_av, in_start_date, in_skills, in_co_id, in_start_hour, in_end_hour,in_city, in_description from t_tr_internship where in_id = ?";
	private static String UPDATE = "UPDATE `t_tr_internship` SET  `in_theme` = ?, `in_domain` = ?, `in_start_date` = ?, `in_duration` = ?, `in_status` = ?, `in_spots_av` = ?, `in_type` = ?, `in_skills` = ?, `in_start_hour` =?, `in_end_hour` = ?, `in_city` = ?, in_description = ? WHERE `in_id` = ?";
	private static String INSERT = "INSERT INTO `t_tr_internship`  (  `in_co_id`,  `in_theme`,  `in_domain`,  `in_start_date`,  `in_duration`,  `in_status`,  `in_spots_av`,  `in_type`,  `in_skills`,  `in_start_hour`,  `in_end_hour`,  `in_city`, in_description)  VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static String DELETE = "DELETE FROM `t_tr_internship` WHERE in_id = ?";
	public static InternshipDaoDB getInstance() {
		return INSTANCE;
	}
	
	public List<Internship> getAll(Map<String,String[]> crit){
		List<Internship> list = new ArrayList<>();
		StringBuilder types  = new StringBuilder();
		types.append("'").append(String.join("','",crit.get("type"))).append("'");
		StringBuilder cat = new StringBuilder();
		cat.append("'").append(String.join("','",crit.get("domain"))).append("'");
		String sql = SELECT + " where in_type in (" + types.toString() + ") and in_domain in ("+ cat.toString() +")";
		LOGGER.debug(sql);
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(sql)){
			try(ResultSet rs = stm.executeQuery()){
				while(rs.next()) {
					list.add(getInternship(rs));
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		return list;
	}
	public Internship getInternshipById(int id){
		Internship item = new Internship();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_BY_ID)){
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next()) {
					item = getInternship(rs);
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		return item;
	}
	
	
	private Internship getInternship(ResultSet rs) throws SQLException {
		Internship item = new Internship();
		int i = 1;
		item.setId(rs.getInt(i++));
		item.setTheme(rs.getString(i++));
		item.setDomain(rs.getString(i++));
		item.setDuration(rs.getInt(i++));
		item.setType(rs.getString(i++));
		item.setStatus(rs.getString(i++));
		item.setSpots(rs.getInt(i++));
		item.setStartDate(rs.getDate(i++));
		item.setSkills(rs.getString(i++).split(","));
		Company company = CompanyDaoDB.getInstance().getCompanyById(rs.getInt(i++));
		item.setCompany(company);
		item.setStartHour(rs.getTime(i++));
		item.setEndHour(rs.getTime(i++));
		item.setCity(rs.getString(i++));
		item.setDescription(rs.getString(i++));
		return item;
	}

	public int update(Internship item) {
	    int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(UPDATE)){
			int i = 1;
			stm.setString(i++, item.getTheme());
			stm.setString(i++, item.getDomain());
			stm.setDate(i++, item.getStartDate());
			stm.setInt(i++, item.getDuration());
			stm.setString(i++, item.getStatus());
			stm.setInt(i++, item.getSpots());
			stm.setString(i++, item.getType());
			stm.setString(i++, String.join(",",item.getSkills()));
			stm.setTime(i++, item.getStartHour());
			stm.setTime(i++, item.getEndHour());
			stm.setString(i++, item.getCity());
			stm.setString(i++, item.getDescription());
			stm.setInt(i++, item.getId());
			resp = stm.executeUpdate();	
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return resp;
	}
	public int insert(Internship item) {
	    int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(INSERT)){
			int i = 1;
			stm.setInt(i++, item.getCompany().getId());
			stm.setString(i++, item.getTheme());
			stm.setString(i++, item.getDomain());
			stm.setDate(i++, item.getStartDate());
			stm.setInt(i++, item.getDuration());
			stm.setString(i++, item.getStatus());
			stm.setInt(i++, item.getSpots());
			stm.setString(i++, item.getType());
			stm.setString(i++, String.join(",",item.getSkills()));
			stm.setTime(i++, item.getStartHour());
			stm.setTime(i++, item.getEndHour());
			stm.setString(i++, item.getCity());
			stm.setString(i++, item.getDescription());
			resp = stm.executeUpdate();	
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return resp;
	}

	public int delete(int id) {
		int resp = 0;
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(DELETE)){
			stm.setInt(1,id);
			resp = stm.executeUpdate();	
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return resp;

	}
}
