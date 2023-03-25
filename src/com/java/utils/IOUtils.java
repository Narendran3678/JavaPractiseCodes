package com.java.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class IOUtils {
	public static String read(InputStream is)
	{
		StringBuilder sb = new StringBuilder();
		try
		{
			if(is!=null)
			{
				BufferedReader bis = new BufferedReader(new InputStreamReader(is));
				String line;
				while( (line = bis.readLine() )!=null)
				{
					sb.append(line);
				}
				//System.out.println(sb.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return sb.toString();
	}
	public static InputStream httpConnect(String sourceUrl)
	{
		try 
		{
			URL url= new URI(sourceUrl).toURL();
			System.out.println(sourceUrl);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			if(urlConn.getResponseCode() >= 200 && urlConn.getResponseCode() <300)
			{
				//System.out.println("Connection Status..."+urlConn.getResponseCode()); 
				return urlConn.getInputStream();
			}
			else
			{
				System.out.println("Error Connection URL.."+urlConn.getResponseCode());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	public static InputStream httpConnect(String sourceUrl,long id)
	{
		try 
		{
			URL url= new URI(sourceUrl).toURL();
			System.out.println(sourceUrl);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			if(urlConn.getResponseCode() >= 200 && urlConn.getResponseCode() <300)
			{
				//System.out.println(id+"- Connection Status..."+urlConn.getResponseCode()); 
				return urlConn.getInputStream();
			}
			else
			{
				System.out.println("Error Connection URL.."+urlConn.getResponseCode());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}
