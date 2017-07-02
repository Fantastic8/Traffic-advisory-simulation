package transportation;

public class Train {
	//信息
	private String Sequence;//列次
	private double Price;//票价
	private int StartHour;//每天出发时间小时
	private int StartMin;//每天出发时间分钟
	private int DurationHour;//历时 小时
	private int DurationMin;//历时 分钟
	private static int Amount=-1;//该连接的列车数量--有头结点
	//数据
	private Train next;//下一班列车
	Train()
	{
		Amount++;//列车数量+1
		Sequence=null;
		Price=0;
		StartHour=0;
		StartMin=0;
		DurationHour=0;
		DurationMin=0;
		next=null;
	}
	//-------------------------------------------------------------------------------------------------
//set方法
	public void setSequence(String sequence)//设置列次
	{
		Sequence=sequence;
	}
	public void setPrice(double price)//设置票价
	{
		Price=price;
	}
	public void setStartTime(int hour,int min)//设置出发时间
	{
		StartHour=hour;
		StartMin=min;
	}
	public void setDuration(int hour,int min)//设置历时
	{
		DurationHour=hour;
		DurationMin=min;
	}
	public void setnextTrain(Train train)//设置下一列车
	{
		next=train;
	}
	public void setAmount(int amount)//设置列车数量
	{
		Amount=amount;
	}
//End of set
	//-------------------------------------------------------------------------------------------------
//get方法
	public int getEndDay()
	{
		return (StartHour+DurationHour+(StartMin+DurationMin)/60)/24;
	}
	public int getEndHour()
	{
		return (StartHour+DurationHour+(StartMin+DurationMin)/60)%24;
	}
	public int getEndMin()
	{
		return (StartMin+DurationMin)%60;
	}
	public String getSequence()//获取列车车次
	{
		return Sequence;
	}
	public double getPrice()//获取票价
	{
		return Price;
	}
	public int getStartHour()//获取发车时间-小时
	{
		return StartHour;
	}
	public int getStartMin()//获取发车时间-分钟
	{
		return StartMin;
	}
	public long getDuration()//获取列车行驶的时间-分钟
	{
		return DurationHour*60+DurationMin;
	}
	public int getDurationHour()//获取列车历时小时
	{
		return DurationHour;
	}
	public int getDurationMin()//获取列车历时分钟
	{
		return DurationMin;
	}
	public int getAmount()//获取列车数量
	{
		return Amount;
	}
	/*public int getEndDay()//获取第几天到达
	{}
	public int getEndHour()//获取到达当天的小时
	{}
	public int getEndMin()//获取到达当天的分钟
	{}*/
	public Train getnextTrain()//移向下一班列车
	{
		return next;
	}
//End of get
	//-------------------------------------------------------------------------------------------------
//Others
	
//End of Others
}


//-------------被抛弃的孩子
//private int Mode;//发车频率
//Mode=0;
//public void setMode(int mode)//设置发车频率
//{
//	Mode=mode;
//}
