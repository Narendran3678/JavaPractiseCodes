package com.io;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class BufferReaderDemo 
{
	static File inputFile = new File("E:\\Eclipse_Workspace\\TestApplication\\resources\\Image.png"); 
	static File outputFile = new File("E:\\Eclipse_Workspace\\TestApplication\\resources\\Image1.png"); 
	public static void ioReadWriteWithoutBuffer()
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		long startTime = 0l;
		double elapsedTimeInMillies = 0l;
		int elapsedTimeInSec = 0;
		int byteRead = 0;
		try
		{
			System.out.println("Read Write Operation Without Buffer");
			startTime = System.nanoTime();
			fis = new FileInputStream(inputFile);
			fos = new FileOutputStream(outputFile);
			while ((byteRead = fis.read()) != -1)
			{
				//System.out.print((char)byteRead);
				fos.write(byteRead);
			}
			elapsedTimeInMillies 	= (System.nanoTime() - startTime)/1000000.0;
			elapsedTimeInSec 		= (int) (elapsedTimeInMillies/1000.0);
			System.out.println("Time Required to finish in millies..."+elapsedTimeInMillies+" msec");
			System.out.println("Time Required to finish in seconds..."+elapsedTimeInSec+" sec");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(fis!=null)
				{
					fis.close();
				}
				if(fos!=null)
				{
					fos.close();
				}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public static void ioReadWriteWithBuffer() throws FileNotFoundException, IOException
	{
		long startTime = 0l;
		double elapsedTimeInMillies = 0l;
		int elapsedTimeInSec = 0;
		byte[] byteReadArr = new byte[4000];
		int numBytesRead = 0;
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile)))
		{
			System.out.println("Read Write Operation With Buffer");
			startTime = System.nanoTime();
			while ((numBytesRead = bis.read(byteReadArr)) != -1)
			{
				//System.out.print((char)byteRead);
				bos.write(byteReadArr,0,numBytesRead);
			}
			elapsedTimeInMillies 	= (System.nanoTime() - startTime)/1000000.0;
			elapsedTimeInSec 		= (int) (elapsedTimeInMillies/1000.0);
			System.out.println("Time Required to finish in millies..."+elapsedTimeInMillies+" msec");
			System.out.println("Time Required to finish in seconds..."+elapsedTimeInSec+" sec");	
		}
	}
	public static void EfficientReaderWriter() throws IOException
	{
		String readLine  = "";
		StringBuilder sb = new StringBuilder();
		try(
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ConsoleFile.txt")))
			)
		{
			while((readLine=br.readLine())!=null && !readLine.equalsIgnoreCase("END"))
			{
				sb.append(readLine).append("\n");
			}
		
			bw.write(sb.toString());
		}
		try( BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("ConsoleFile.txt"))); )
		{
			readLine="";
			sb = new StringBuilder();
			while((readLine=br1.readLine())!=null)
			{
				sb.append(readLine).append("\n");
			}
			System.out.println("Read From File....\n"+sb.toString());
		}
	}
	public static void main(String args[]) throws FileNotFoundException, IOException
	{
		//BufferReaderDemo.ioReadWriteWithoutBuffer();
		BufferReaderDemo.EfficientReaderWriter();
		//BufferReaderDemo.ioReadWriteWithBuffer();
	}
}
