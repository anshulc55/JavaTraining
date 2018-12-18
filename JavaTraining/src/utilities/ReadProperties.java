package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		InputStream readFile = null;
		
		try{
			readFile = new FileInputStream("config.properties");
			
			prop.load(readFile);
			
			System.out.println(prop.getProperty("DBServer"));
			System.out.println(prop.getProperty("DBPass"));
			System.out.println(prop.getProperty("DBName"));
			System.out.println(prop.getProperty("username"));
		} catch(IOException io){
			io.printStackTrace();
		} finally {
			if(readFile!=null){
				try {
					readFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
