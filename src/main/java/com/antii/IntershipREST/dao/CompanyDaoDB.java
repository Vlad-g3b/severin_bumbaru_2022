package com.antii.IntershipREST.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.antii.IntershipREST.helper.ConnectionHelper;
import com.antii.IntershipREST.models.Company;

public class CompanyDaoDB {
	private static CompanyDaoDB INSTANCE = new CompanyDaoDB();
	private static String SELECT_BY_ID = "select co_id, co_name, co_address, co_description, co_status from t_ma_company where co_id = ?";
	
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
}
