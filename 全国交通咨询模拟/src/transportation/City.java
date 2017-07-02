package transportation;

import java.awt.Point;

public class City {
	//城市信息
	private String NameofCity;//城市名
	private int IndexofCity;//城市编号
	private static int Amount=-1;//城市个数--有头结点
	private int x;//坐标
	private int y;
	//数据操作
	private CityConnection HeadofConCity;//邻接表头结点
	private City next;//下一个城市
	
	public City()
	{
		//初始化成员
		NameofCity=null;//城市名
		
		IndexofCity=Amount;
		Amount++;//城市个数增加1
		//System.out.println("IndexofCity="+IndexofCity+" Amount="+Amount);
		
		next=null;
		HeadofConCity=new CityConnection(IndexofCity);//邻接表头结点null
	}
	//-------------------------------------------------------------------------------------------------
//set方法
	public void setNameofCity(String name)//设置城市名
	{
		NameofCity=name;
	}
	public void setIndexofCity(int index)//设置城市编号
	{
		IndexofCity=index;
		// 邻接表改变
		CityConnection tempcon=HeadofConCity.getnextCon();
		while(tempcon!=null)
		{
			tempcon.setStartIndex(index);
			tempcon=tempcon.getnextCon();
		}
	}
	public void setnextCity(City city)//设置下一个城市
	{
		next=city;
	}
	public void setx(int x)//设置城市位置
	{
		this.x=x;
	}
	public void sety(int y)//设置城市位置
	{
		this.y=y;
	}
	public void setpos(Point pos)//设置城市位置
	{
		this.x=pos.x;
		this.y=pos.y;
	}
	public void setAmountofCity(int amount)
	{
		Amount=amount;
	}
//End of set
	//-------------------------------------------------------------------------------------------------
//get方法
	public String getNameofCity()//获取城市名
	{
		return NameofCity;
	}
	public int getIndexofCity()//获取城市编号
	{
		return IndexofCity;
	}
	public CityConnection getHeadofCityConnection()//获取以该城市为发车点的邻接表头结点NULL
	{
		return HeadofConCity;
	}
	public int getAmountofCity()//获得当前城市个数
	{
		return Amount;
	}
	public City getnextCity()//移向下一个城市
	{
		return next;
	}
	public int getx()//获取城市位置
	{
		return x;
	}
	public int gety()//获取城市位置
	{
		return y;
	}
	public Point getpos()//获取城市位置
	{
		return new Point(this.x,this.y);
	}
//End of get
	//-------------------------------------------------------------------------------------------------
//add方法
	public void addConnection(CityConnection con)
	{
		//头插法
		con.setnextCon(HeadofConCity.getnextCon());
		//con.setStartIndex(IndexofCity);//设置发车城市
		HeadofConCity.setnextCon(con);
	}
	public boolean addTrain(Train train,int IndexofEndCity)
	{
		//检查有没有endcity的连接
		CityConnection p=HeadofConCity.getnextCon();
		while(p!=null)
		{
			if(p.getEndIndex()==IndexofEndCity)
			{
				break;
			}
			p=p.getnextCon();
		}
		if(p!=null)//有此连接,只需要将该火车加入至此连接中
		{
			//System.out.println("存在连接");
			p.addTrain(train);
			return true;//有此链接不需要增加连线
		}
		else//没有此连接，需要新建连接
		{
			//System.out.println("建立新连接");
			CityConnection con=new CityConnection(IndexofCity);//设置发车城市
			con.setEndIndex(IndexofEndCity);//设置到达城市
			City.this.addConnection(con);
			con.addTrain(train);
			return false;//没有连接,需要增加连线
		}
	}
//End of add
	//-------------------------------------------------------------------------------------------------
//Others
	
//End of Others
}
