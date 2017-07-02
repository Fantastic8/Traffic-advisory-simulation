package transportation;

import java.awt.Point;

public class City {
	//������Ϣ
	private String NameofCity;//������
	private int IndexofCity;//���б��
	private static int Amount=-1;//���и���--��ͷ���
	private int x;//����
	private int y;
	//���ݲ���
	private CityConnection HeadofConCity;//�ڽӱ�ͷ���
	private City next;//��һ������
	
	public City()
	{
		//��ʼ����Ա
		NameofCity=null;//������
		
		IndexofCity=Amount;
		Amount++;//���и�������1
		//System.out.println("IndexofCity="+IndexofCity+" Amount="+Amount);
		
		next=null;
		HeadofConCity=new CityConnection(IndexofCity);//�ڽӱ�ͷ���null
	}
	//-------------------------------------------------------------------------------------------------
//set����
	public void setNameofCity(String name)//���ó�����
	{
		NameofCity=name;
	}
	public void setIndexofCity(int index)//���ó��б��
	{
		IndexofCity=index;
		// �ڽӱ�ı�
		CityConnection tempcon=HeadofConCity.getnextCon();
		while(tempcon!=null)
		{
			tempcon.setStartIndex(index);
			tempcon=tempcon.getnextCon();
		}
	}
	public void setnextCity(City city)//������һ������
	{
		next=city;
	}
	public void setx(int x)//���ó���λ��
	{
		this.x=x;
	}
	public void sety(int y)//���ó���λ��
	{
		this.y=y;
	}
	public void setpos(Point pos)//���ó���λ��
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
//get����
	public String getNameofCity()//��ȡ������
	{
		return NameofCity;
	}
	public int getIndexofCity()//��ȡ���б��
	{
		return IndexofCity;
	}
	public CityConnection getHeadofCityConnection()//��ȡ�Ըó���Ϊ��������ڽӱ�ͷ���NULL
	{
		return HeadofConCity;
	}
	public int getAmountofCity()//��õ�ǰ���и���
	{
		return Amount;
	}
	public City getnextCity()//������һ������
	{
		return next;
	}
	public int getx()//��ȡ����λ��
	{
		return x;
	}
	public int gety()//��ȡ����λ��
	{
		return y;
	}
	public Point getpos()//��ȡ����λ��
	{
		return new Point(this.x,this.y);
	}
//End of get
	//-------------------------------------------------------------------------------------------------
//add����
	public void addConnection(CityConnection con)
	{
		//ͷ�巨
		con.setnextCon(HeadofConCity.getnextCon());
		//con.setStartIndex(IndexofCity);//���÷�������
		HeadofConCity.setnextCon(con);
	}
	public boolean addTrain(Train train,int IndexofEndCity)
	{
		//�����û��endcity������
		CityConnection p=HeadofConCity.getnextCon();
		while(p!=null)
		{
			if(p.getEndIndex()==IndexofEndCity)
			{
				break;
			}
			p=p.getnextCon();
		}
		if(p!=null)//�д�����,ֻ��Ҫ���û𳵼�������������
		{
			//System.out.println("��������");
			p.addTrain(train);
			return true;//�д����Ӳ���Ҫ��������
		}
		else//û�д����ӣ���Ҫ�½�����
		{
			//System.out.println("����������");
			CityConnection con=new CityConnection(IndexofCity);//���÷�������
			con.setEndIndex(IndexofEndCity);//���õ������
			City.this.addConnection(con);
			con.addTrain(train);
			return false;//û������,��Ҫ��������
		}
	}
//End of add
	//-------------------------------------------------------------------------------------------------
//Others
	
//End of Others
}
