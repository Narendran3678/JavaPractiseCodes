package com.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;

public class JDBCDemo 
{
	static Connection conn =null;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	static PreparedStatement prepStmt = null;
	static Statement stmt =null;
	
	static
	{
		try
		{
			//Class.forName("oracle.jdbc.driver.OracleDriver");	
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); //Compile Time Safety Check
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.42:1521:oracledb","sys as sysdba","root");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateStatement() throws Exception
	{
		prepStmt = conn.prepareStatement("UPDATE BOOK SET CREATED_DATE=? WHERE ID=?");
		prepStmt.setDate(1,new Date(sdf.parse("11/4/1991 13:10:00").getTime()));
		prepStmt.setInt(2,21);
		int count=prepStmt.executeUpdate();
		if(count>0)
		{
			System.out.println("Updated");
		}
	}
	public static void insertStatement() throws Exception
	{
		prepStmt = conn.prepareStatement("Insert into BOOK (TITLE,PUBLICATION_YEAR,PUBLISHER_ID,BOOK_GENRE_ID,AMAZON_RATING,KID_FRIENDLY_STATUS,CREATED_DATE) \r\n"
													 + "values (?,?,?,?,?,?,?)");
		prepStmt.setString(1, "Informatica Tech");
		prepStmt.setInt(2,2004);
		prepStmt.setInt(3,2);
		prepStmt.setInt(4,103);
		prepStmt.setInt(5,3);
		prepStmt.setInt(6,1);
		prepStmt.setDate(7,new Date(System.currentTimeMillis()));
		
		int count=prepStmt.executeUpdate();
		if(count>0)
		{
			System.out.println("Inserted");
		}
	}
	public static void selectStatement() throws Exception
	{		
		stmt = conn.createStatement();
		ResultSet result=stmt.executeQuery("SELECT ID as BOOK_ID,TITLE,PUBLICATION_YEAR,PUBLISHER_ID,BOOK_GENRE_ID,AMAZON_RATING,KID_FRIENDLY_STATUS,CREATED_DATE FROM BOOK");
		while(result.next())
		{
			System.out.println(result.getInt("BOOK_ID")+"-"
					+result.getString("TITLE")+"-"
					+result.getInt("PUBLICATION_YEAR")+"-"
					+result.getInt("PUBLISHER_ID")+"-"
					+result.getInt("BOOK_GENRE_ID")+"-"
					+result.getDouble("AMAZON_RATING")+"-"
					+result.getInt("KID_FRIENDLY_STATUS")+"-"
					+result.getTimestamp("CREATED_DATE"));
			
			System.out.println("Time-"+result.getTimestamp("CREATED_DATE").toLocalDateTime());
		}
		if(result!=null)
			result.close();
	}
	public static void resultSetMetaData() throws SQLException 
	{
		int row=1;
		stmt = conn.createStatement();
		ResultSet result=stmt.executeQuery("SELECT ID as BOOK_ID,TITLE,PUBLICATION_YEAR,PUBLISHER_ID,BOOK_GENRE_ID,AMAZON_RATING,KID_FRIENDLY_STATUS,CREATED_DATE FROM BOOK");
		ResultSetMetaData resultMeta = result.getMetaData();
		System.out.println(resultMeta.getColumnCount());
		while(result.next())
		{
			System.out.println("Record......"+row);
			for(int count=1;count<=resultMeta.getColumnCount();count++)
			{
				System.out.println(resultMeta.getColumnName(count)+"-"+resultMeta.getColumnTypeName(count)+"-"+result.getString(resultMeta.getColumnName(count)));
			}	
			System.out.println();
			row++;
		}
	}
	public static void databaseInfo() throws SQLException
	{
		DatabaseMetaData dbmd=conn.getMetaData();  		  
		System.out.println("Driver Name: "+dbmd.getDriverName());  
		System.out.println("Driver Version: "+dbmd.getDriverVersion());  
		System.out.println("UserName: "+dbmd.getUserName());  
		System.out.println("Database Product Name: "+dbmd.getDatabaseProductName());  
		System.out.println("Database Product Version: "+dbmd.getDatabaseProductVersion());  
	}
	public static void main(String[] args)
	{
		try 
		{
			//insertStatement();
			//updateStatement();
			//selectStatement();
			//resultSetMetaData();
			databaseInfo();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(conn!=null)
				{
					conn.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
				if(prepStmt!=null)
				{
					prepStmt.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
