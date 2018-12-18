package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class WriteProperties {

	public static void main(String[] args) {

		Properties prop = new Properties();
		OutputStream writeFile = null;

		try {
			writeFile = new FileOutputStream("config.properties");

			prop.setProperty("DBServer", "ins01.kui02.anshul");
			prop.setProperty("DBName", "db_anshul");
			prop.setProperty("DBPass", "testpassword");
			prop.setProperty("username", "root");

			prop.store(writeFile, null);
			System.out.println("Create Properties Successfully");
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (writeFile != null) {
				try {
					writeFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
