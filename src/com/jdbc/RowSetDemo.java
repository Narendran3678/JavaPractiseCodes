package com.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class RowSetDemo {
	static String title = "";
	public RowSetDemo() {}

	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		JdbcRowSet rowSet =  RowSetProvider.newFactory().createJdbcRowSet();
		
		rowSet.setUrl("jdbc:oracle:thin:@192.168.1.42:1521:oracledb");
		rowSet.setUsername("sys as sysdba");
		rowSet.setPassword("root");
		rowSet.setCommand("select * from book");
		rowSet.addRowSetListener(new CustomListener());
		
		rowSet.execute();
		System.out.println("Entered Once");
		while(rowSet.next())
		{
			title = rowSet.getString("TITLE");
			System.out.println(rowSet.getInt("ID")+"-"
					+rowSet.getString("TITLE")+"-"
					+rowSet.getInt("PUBLICATION_YEAR")+"-"
					+rowSet.getInt("PUBLISHER_ID")+"-"
					+rowSet.getInt("BOOK_GENRE_ID")+"-"
					+rowSet.getDouble("AMAZON_RATING")+"-"
					+rowSet.getInt("KID_FRIENDLY_STATUS")+"-"
					+rowSet.getTimestamp("CREATED_DATE"));
		}
	}
}
class CustomListener implements RowSetListener
{
	@Override
	public void rowSetChanged(RowSetEvent event) {
		System.out.println("RowSet Changed....");
	}

	@Override
	public void rowChanged(RowSetEvent event) {
		System.out.println("Row Moved");
	}

	@Override
	public void cursorMoved(RowSetEvent event) {
		String title = RowSetDemo.title;
		System.out.println("Cursor Moved..."+title);
	}
	
}