package com.jdbc;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.io.*; 
public class JdbcMultimediaDemo {
	static Connection conn =null;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	static PreparedStatement prepStmt = null;
	static Statement stmt =null;
	static
	{
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.42:1521:oracledb","sys as sysdba","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void insertMultimediaContent() throws SQLException, IOException
	{
		prepStmt = conn.prepareStatement("insert into multimedia(file_name,image_content,file_content) values(?,?,?)");
		prepStmt.setString(1, "Image_And_Text");
		
		FileInputStream fisImage = null;
		FileReader frText  = null;
		try {
			fisImage = new FileInputStream("E:\\Eclipse_Workspace\\JavaApplication\\resources\\Image.png");
			prepStmt.setBinaryStream(2, fisImage,fisImage.available());
			File file = new File("E:\\Eclipse_Workspace\\JavaApplication\\resources\\SampleOut.txt");
			frText = new FileReader(file);
			prepStmt.setCharacterStream(3, frText,file.length());
			
			if(prepStmt.executeUpdate()>0)
				System.out.println("Multimedia Inserted");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally
		{
			if(fisImage!=null)
			{
				fisImage.close();
			}
			if(frText!=null)
			{
				frText.close();
			}
		}
		
	}
	
	public static void retreiveMultimediaContent() throws SQLException, IOException
	{
		FileOutputStream fosImage = null;
		FileWriter wr = null;
		try {
			prepStmt = conn.prepareStatement("select file_name,image_content,file_content from multimedia");
			ResultSet result= prepStmt.executeQuery();
			while(result.next())
			{
				System.out.println(result.getString("file_name")+"-"+result.getString("file_content"));
				Blob img = result.getBlob(2);
				fosImage = new FileOutputStream("D:\\Test.jpg");
				byte[] bt =img.getBytes(1,(int) img.length());
				fosImage.write(bt);
				
				Clob txt = result.getClob(3);
				Reader reader = txt.getCharacterStream();
				
				wr= new FileWriter("D:\\FileText.txt");
				int i=0;
				while( (i=reader.read())!=-1 )
				{
					wr.write(i);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fosImage!=null)
				fosImage.close();
			if(wr!=null)
				wr.close();
		}
	}
	public static void main(String[] args) throws IOException
	{
		try 
		{
			insertMultimediaContent();
			retreiveMultimediaContent();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
}
