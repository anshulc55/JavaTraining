package utilities;

import java.util.Date;

public class DateUtil {
		
		public static String getTimeStamp(){
			Date date = new Date();
			return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
		}
}
