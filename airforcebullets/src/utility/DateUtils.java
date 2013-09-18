package utility;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	
	public static Date getSqlDate(String strDate) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
		Date result = new java.sql.Date(formatter.parse(strDate).getTime());
		return result;
	}
	
	public static Date getCurrentSqlDate(){
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());
		return sqlDate;
	}
	
	//i don't need this after all.
//	public static String getJSDate(Date date){
//		SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
//		String jsDate = formatter.format(date);
//		return jsDate;
//	}

}
