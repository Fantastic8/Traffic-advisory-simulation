package transportation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {
	private Date date;
	public String getFullTime()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		return time;
	}
	public String getCurrentTime()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("HH:mm:ss");
		String time=format.format(date);
		return time;
	}
	public int getYear()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy");
		int year=Integer.valueOf(format.format(date));
		return year;
	}
	public int getMonth()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("MM");
		int month=Integer.valueOf(format.format(date));
		return month;
	}
	public int getDay()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("dd");
		int day=Integer.valueOf(format.format(date));
		return day;
	}
	public int getHour()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("HH");
		int hour=Integer.valueOf(format.format(date));
		return hour;
	}
	public int getMinute()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("mm");
		int minute=Integer.valueOf(format.format(date));
		return minute;
	}
	public int getSecond()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("ss");
		int second=Integer.valueOf(format.format(date));
		return second;
	}
	public String getValidTimetoStore()
	{
		date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time=format.format(date);
		return time;
	}
	public int getChargeHours(String time1,String time2) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date1=sdf.parse(time1);
		Date date2=sdf.parse(time2);
		long between=Math.abs(date1.getTime()-date2.getTime());//Ê±¼ä²î
		if((int)between/1000/60>0)
		{
			return (int)(between/(1000*60*60))+1;
		}
		else
		{
			return (int)Math.ceil(between/(1000*60*60));
		}
	}
	public int getYearFromString(String s) throws ParseException
	{
		return Integer.valueOf(s.substring(0,4));
	}
	public int getMonthFromString(String s) throws ParseException
	{
		return Integer.valueOf(s.substring(5,7));
	}
	public int getDayFromString(String s) throws ParseException
	{
		return Integer.valueOf(s.substring(8,10));
	}
	public int getDaysofMonth(int year,int month)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH,month-1);

		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return maxDay;
	}
}
