package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.Buy;
import com.model.Policy;
import com.model.PolicyDetails;
import com.model.PolicyHolder;



public class PolicyHolderRepository {
	private String url="jdbc:mysql://localhost:3306/insurance";
	private String userdb="root";
	private String passdb="Abc@123#";
	private String driver="com.mysql.jdbc.Driver";
	Connection con;
	public void dbConnect() {
		try {
			Class.forName(driver);
			//			System.out.println("driver loaded...");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, userdb, passdb);
			//			System.out.println("conn established");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void dbClose() {
		try {
			con.close();
			//			System.out.println("DB closed...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void insertUser(PolicyHolder policyHolder) {
		dbConnect();
		String sql="insert into PolicyHolder(name,age, username, password) values(?,?,?,?)";
		
		try {
			PreparedStatement preparedStatement= con.prepareStatement(sql);
			preparedStatement.setString(1, policyHolder.getName());
			preparedStatement.setInt(2, policyHolder.getAge());
			preparedStatement.setString(3, policyHolder.getUsername());
			preparedStatement.setString(4, policyHolder.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbClose();
	}
	public boolean doLogin(String userName, String password) {
		boolean status=false;
		dbConnect();
		String sql="select * from PolicyHolder where username=? and password=?";
		
		try {
			PreparedStatement preparedStatement= con.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			
			ResultSet rst = preparedStatement.executeQuery();
			if(rst.next()) {
				status=true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		dbClose();
		return status;
	}
	public List<Policy> fetchAllPolicies() {
		dbConnect();
		List<Policy> list = new ArrayList<Policy>();
		String sql = "select * from Policy";
		
		try {
			PreparedStatement preparedStatement= con.prepareStatement(sql);
			ResultSet rst = preparedStatement.executeQuery();
			while(rst.next()) {
				Policy policy=new Policy();
				policy.setId(rst.getInt("id"));
				policy.setTitle(rst.getString("title"));
				policy.setDescription(rst.getString("description"));
				policy.setTermDuration(rst.getInt("termDuration"));
				policy.setPremiumAmount(rst.getDouble("premiumAmount"));
				list.add(policy);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	public PolicyDetails fetchPolicyDetails(int id) {
		dbConnect();
		//List<PolicyDetails> list = new ArrayList<>();
		String sql=" select p.title, p.premiumAmount, p.termDuration,p.description, d.long_des from policy p join policydetails d on p.id = d.policy_id where p.id=?";
		PolicyDetails policyDetails=new PolicyDetails();
		try {
			PreparedStatement preparedStatement= con.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet rst = preparedStatement.executeQuery();
			if(rst.next()) {
				
				
//				policyDetails.setId(rst.getInt("id"));
				policyDetails.setTitle(rst.getString("title"));
				policyDetails.setDescription(rst.getString("description"));
				policyDetails.setTermDuration(rst.getInt("termDuration"));
				policyDetails.setPremiumAmount(rst.getDouble("premiumAmount"));
				policyDetails.setLongDesc(rst.getString("long_des"));
			}
				
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		dbClose();
		return policyDetails;
		
	}
	public void buyPolicy(Buy buy) {
		dbConnect();
		String sql="insert into buy(id,user_id, policy_id, dop) values(?,?,?,?)";
		
		try {
			
			PreparedStatement preparedStatement= con.prepareStatement(sql);
			preparedStatement.setInt(1, buy.getId());
			preparedStatement.setInt(2, buy.getUser_id());
			preparedStatement.setInt(3, buy.getPolicy_id());
			preparedStatement.setString(4, buy.getDate_of_purchase());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbClose();
	}
	public Buy fetchUserId(String username) {
		dbConnect();
		
		String sql="select id from policyholder where username=?";
		Buy buy=new Buy();
		try {
			PreparedStatement preparedStatement= con.prepareStatement(sql);
			preparedStatement.setString(1,username);
			ResultSet rst = preparedStatement.executeQuery();
			if(rst.next()) {
				
				buy.setUser_id(rst.getInt("id"));
//				
			}
				
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		dbClose();
		return buy;
		
	}
	
}