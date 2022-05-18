package com.antii.IntershipREST.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antii.IntershipREST.helper.ConnectionHelper;
import com.antii.IntershipREST.models.ERole;
import com.antii.IntershipREST.models.IUserDetails;
import com.antii.IntershipREST.models.LoginUser;
import com.antii.IntershipREST.models.User;
import com.antii.IntershipREST.models.UserDao;

public class UserDaoDB implements UserDao{

	private static UserDaoDB INSTANCE = new UserDaoDB();
	private static String SELECT_LOGIN = "select us_id, us_email, us_role from t_ma_user where us_email = ? and us_password = ?";
	private static String INSERT = "INSERT INTO `t_ma_user` (`us_email`, `us_password`, `us_role`) VALUES (?, ?, ?)";
	private static String SELECT_BY_ID = "select us_id, us_email, us_role from t_ma_user where us_id = ?";
	private static String SELECT_BY_ID_DETAILS = "select mu.us_id, mu.us_email, mu.us_role, tp.pr_profile_pic, tp.pr_cv,tp.pr_name, tp.pr_surname, tp.pr_phone, tp.pr_address, tp.pr_school, tp.pr_skills, tp.pr_professor_id from t_ma_user mu join t_tr_profile tp on mu.us_id = tp.us_id where mu.us_id = ?";

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static UserDaoDB getInstance() {
		return INSTANCE;
	}
	
	public LoginUser checkUser(LoginUser user) {
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_LOGIN)){
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next()) {
					int i = 1;
					return new LoginUser(rs.getInt(i++),rs.getString(i++),"#########",rs.getString(i++));
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return null;
	}
	
	public int register(LoginUser user) throws SQLException {
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(INSERT)){
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getRole() == null ? ERole.STUDENT.name() : user.getRole());
			int i = stm.executeUpdate();	
			return i;
		}catch (SQLException se) {
			LOGGER.debug(se.getMessage());
			throw new SQLException(se);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return 0;
	}

	public User getUserById(int id) {
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_BY_ID)){
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next()) {
					int i = 1;
					return new LoginUser(rs.getInt(i++),rs.getString(i++),"#########",rs.getString(i++));
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return null;
	}

	@Override
	public IUserDetails getUserDetails(int id) {
		try(Connection con = new ConnectionHelper().getConnection();PreparedStatement stm = con.prepareStatement(SELECT_BY_ID_DETAILS)){
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
				if(rs.next()) {
					int i = 1;
					return new LoginUser(rs.getInt(i++),rs.getString(i++),"#########",rs.getString(i++));
				}
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}	
		return null;
	}
}
