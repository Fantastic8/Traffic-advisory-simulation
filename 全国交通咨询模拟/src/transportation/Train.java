package transportation;

public class Train {
	//��Ϣ
	private String Sequence;//�д�
	private double Price;//Ʊ��
	private int StartHour;//ÿ�����ʱ��Сʱ
	private int StartMin;//ÿ�����ʱ�����
	private int DurationHour;//��ʱ Сʱ
	private int DurationMin;//��ʱ ����
	private static int Amount=-1;//�����ӵ��г�����--��ͷ���
	//����
	private Train next;//��һ���г�
	Train()
	{
		Amount++;//�г�����+1
		Sequence=null;
		Price=0;
		StartHour=0;
		StartMin=0;
		DurationHour=0;
		DurationMin=0;
		next=null;
	}
	//-------------------------------------------------------------------------------------------------
//set����
	public void setSequence(String sequence)//�����д�
	{
		Sequence=sequence;
	}
	public void setPrice(double price)//����Ʊ��
	{
		Price=price;
	}
	public void setStartTime(int hour,int min)//���ó���ʱ��
	{
		StartHour=hour;
		StartMin=min;
	}
	public void setDuration(int hour,int min)//������ʱ
	{
		DurationHour=hour;
		DurationMin=min;
	}
	public void setnextTrain(Train train)//������һ�г�
	{
		next=train;
	}
	public void setAmount(int amount)//�����г�����
	{
		Amount=amount;
	}
//End of set
	//-------------------------------------------------------------------------------------------------
//get����
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
	public String getSequence()//��ȡ�г�����
	{
		return Sequence;
	}
	public double getPrice()//��ȡƱ��
	{
		return Price;
	}
	public int getStartHour()//��ȡ����ʱ��-Сʱ
	{
		return StartHour;
	}
	public int getStartMin()//��ȡ����ʱ��-����
	{
		return StartMin;
	}
	public long getDuration()//��ȡ�г���ʻ��ʱ��-����
	{
		return DurationHour*60+DurationMin;
	}
	public int getDurationHour()//��ȡ�г���ʱСʱ
	{
		return DurationHour;
	}
	public int getDurationMin()//��ȡ�г���ʱ����
	{
		return DurationMin;
	}
	public int getAmount()//��ȡ�г�����
	{
		return Amount;
	}
	/*public int getEndDay()//��ȡ�ڼ��쵽��
	{}
	public int getEndHour()//��ȡ���ﵱ���Сʱ
	{}
	public int getEndMin()//��ȡ���ﵱ��ķ���
	{}*/
	public Train getnextTrain()//������һ���г�
	{
		return next;
	}
//End of get
	//-------------------------------------------------------------------------------------------------
//Others
	
//End of Others
}


//-------------�������ĺ���
//private int Mode;//����Ƶ��
//Mode=0;
//public void setMode(int mode)//���÷���Ƶ��
//{
//	Mode=mode;
//}
