package transportation;

public class CityConnection {
	//邻接表信息
	private int StartIndex;
	private int EndIndex;
	private Train HeadofTrain;//火车头结点
	private Train TailofTrain;//火车尾结点
	private static int Degree=-1;//该城市的度--有头结点
	//数据操作
	private CityConnection next;//下一个邻接节点
	
//	CityConnection()
//	{
//		Degree++;//度+1
//		StartIndex=-1;//出发城市
//		EndIndex=0;
//		HeadofTrain=new Train();//列车头结点null
//		next=null;
//	}
	
	CityConnection(int startindex)
	{
		Degree++;//度+1
		StartIndex=startindex;//设置出发的城市
		EndIndex=0;
		HeadofTrain=new Train();//列车头结点null
		TailofTrain=HeadofTrain;
		next=null;
	}
//set方法
	public void setStartIndex(int startindex)//设置发车城市
	{
		StartIndex=startindex;
	}
	public void setEndIndex(int endindex)//设置到达城市
	{
		EndIndex=endindex;
	}
	public void setnextCon(CityConnection con)
	{
		next=con;
	}
//End of set
	//-------------------------------------------------------------------------------------------------
//get方法
	public int getStartIndex()//获取发车城市
	{
		return StartIndex;
	}
	public int getEndIndex()//获取到达城市
	{
		return EndIndex;
	}
	public Train getHeadofTrain()//获取火车头结点-NULL
	{
		return HeadofTrain;
	}
	public Train getTailofTrain()//获取火车尾结点
	{
		return TailofTrain;
	}
	public int getDegree()//获得该城市的度
	{
		return Degree;
	}
	public CityConnection getnextCon()//移向下一个连接
	{
		return next;
	}
//End of get
	//-------------------------------------------------------------------------------------------------
//add方法
	public void addTrain(Train train)
	{
		//头插法
		train.setnextTrain(HeadofTrain.getnextTrain());
		HeadofTrain.setnextTrain(train);
	}
//End of add
	//-------------------------------------------------------------------------------------------------
//Others

//End of Others
	//-------------------------------------------------------------------------------------------------
}



//--------------------------------------------被抛弃的
//private static CityConnection Tail;//邻接尾节点
//public void addtotail()//添加到链表末尾
//{
//	Tail.next=this;
//	Tail=this;
//}
