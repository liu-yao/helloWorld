package com.ocs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBConnection {
	
	
	
	public static String cudTable(String sql,ArrayList<Object> sqlParam){
		Connection conn = null;
		PreparedStatement stmt = null;
		int success=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		            try {
						conn=DriverManager.getConnection("jdbc:mysql://database2.cqvt1hrchqff.ap-southeast-1.rds.amazonaws.com","admin","liuyaodb");  
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
		            if (conn != null) {
		                try {
							stmt = (PreparedStatement) conn.prepareStatement(sql);
							for(int i=0;i<sqlParam.size();i++){
								if(sqlParam.get(i) instanceof String) {stmt.setString(i+1,(String) sqlParam.get(i));}
								else if(sqlParam.get(i) instanceof Integer) {stmt.setInt(i+1,(Integer) sqlParam.get(i));}
								else if(sqlParam.get(i) instanceof Double){stmt.setDouble(i+1, (Double) sqlParam.get(i));}
								//date/char can use string, money can use double
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "failed";
						}
						
		                try {
							success=stmt.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "failed";
						}
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "failed";
						}
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return "failed";
						}
		                
						} 
		          if(success==0){ return "failed";} else {return "success";}
	           }
	
	
	
	public static ArrayList<String[]> searchTable(String sql,ArrayList<Object> sqlParam){
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		Connection conn=null;

		
		PreparedStatement stmt = null;
		ArrayList<String[]> allObjects= new ArrayList<String[]>();
		boolean haveResults=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		            try {
		            	conn=DriverManager.getConnection("jdbc:mysql://database2.cqvt1hrchqff.ap-southeast-1.rds.amazonaws.com","admin","liuyaodb");  
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
		            if (conn != null) {
		                try {
							stmt = (PreparedStatement) conn.prepareStatement(sql);
							for(int i=0;i<sqlParam.size();i++){
								if(sqlParam.get(i) instanceof String) {stmt.setString(i+1,(String) sqlParam.get(i));}
								else if(sqlParam.get(i) instanceof Integer) {stmt.setInt(i+1,(Integer) sqlParam.get(i));}
								else if(sqlParam.get(i) instanceof Double){stmt.setDouble(i+1, (Double) sqlParam.get(i));}
								//date/char can use string, money can use double
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
		                
		                ResultSet rs = null;
						try {
							rs = stmt.executeQuery();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
		                try {
		                	ArrayList<String> fieldNames=new ArrayList<String>();
		                	ResultSetMetaData rsmd = rs.getMetaData();
		                	int fieldNamesCount=rsmd.getColumnCount();
							for (int i = 1; i <=fieldNamesCount ; i++) {
								fieldNames.add(rsmd.getColumnName(i));
							}
		                	
							while(rs.next()){
								haveResults=true;
								String [] oneObject=new String[fieldNamesCount];
								for(int i=0;i<fieldNamesCount;i++){
									oneObject[i]=rs.getString(fieldNames.get(i).toString());
								}
								allObjects.add(oneObject);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
		            }
		            if(haveResults){return allObjects; } else{return new ArrayList<String[]>();}
	           }
}

