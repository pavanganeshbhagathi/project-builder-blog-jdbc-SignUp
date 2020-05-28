package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface{
	String INSERT_USERS_SQL = "INSERT INTO userlogin(email, password)VALUES(?,?)";
	@Override
	public int signUp(User user) {
		
		int result=0;
		try{
			
			ConnectionManager connection=new ConnectionManager();
			
			PreparedStatement pst=connection.getConnection().prepareStatement(INSERT_USERS_SQL);
			
			pst.setString(0, user.getEmail());
			pst.setString(1, user.getPassword());
			System.out.println(pst);
			
			
			
			result=pst.executeUpdate();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public boolean loginUser(User user) {
		boolean status = false;
		try{
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from userlogin where email = ? and password = ? ");
			
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	return status;
	}
	
}
