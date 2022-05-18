package com.antii.IntershipREST.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.antii.IntershipREST.helper.ConnectionHelper;
import com.antii.IntershipREST.models.Company;
import com.antii.IntershipREST.models.Internship;

public class InternshipDaoDB {

	private static InternshipDaoDB INSTANCE = new InternshipDaoDB();
	private static String SELECT = "select in_id, in_theme, in_domain, in_duration, in_type, in_status, in_spots_av, in_start_date, in_co_id from t_tr_internship";
	
	public static InternshipDaoDB getInstance() {
		return INSTANCE;
	}
	
	public List<Internship> getAll(){
		List<Internship> list = new ArrayList<>();
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT)){
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
		Company company = CompanyDaoDB.getInstance().getCompanyById(rs.getInt(i++));
		item.setCompany(company);
		return item;
	}
}
