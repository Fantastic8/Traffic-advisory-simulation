package transportation;

public class CityConnection {
	//�ڽӱ���Ϣ
	private int StartIndex;
	private int EndIndex;
	private Train HeadofTrain;//��ͷ���
	private Train TailofTrain;//��β���
	private static int Degree=-1;//�ó��еĶ�--��ͷ���
	//���ݲ���
	private CityConnection next;//��һ���ڽӽڵ�
	
//	CityConnection()
//	{
//		Degree++;//��+1
//		StartIndex=-1;//��������
//		EndIndex=0;
//		HeadofTrain=new Train();//�г�ͷ���null
//		next=null;
//	}
	
	CityConnection(int startindex)
	{
		Degree++;//��+1
		StartIndex=startindex;//���ó����ĳ���
		EndIndex=0;
		HeadofTrain=new Train();//�г�ͷ���null
		TailofTrain=HeadofTrain;
		next=null;
	}
//set����
	public void setStartIndex(int startindex)//���÷�������
	{
		StartIndex=startindex;
	}
	public void setEndIndex(int endindex)//���õ������
	{
		EndIndex=endindex;
	}
	public void setnextCon(CityConnection con)
	{
		next=con;
	}
//End of set
	//-------------------------------------------------------------------------------------------------
//get����
	public int getStartIndex()//��ȡ��������
	{
		return StartIndex;
	}
	public int getEndIndex()//��ȡ�������
	{
		return EndIndex;
	}
	public Train getHeadofTrain()//��ȡ��ͷ���-NULL
	{
		return HeadofTrain;
	}
	public Train getTailofTrain()//��ȡ��β���
	{
		return TailofTrain;
	}
	public int getDegree()//��øó��еĶ�
	{
		return Degree;
	}
	public CityConnection getnextCon()//������һ������
	{
		return next;
	}
//End of get
	//-------------------------------------------------------------------------------------------------
//add����
	public void addTrain(Train train)
	{
		//ͷ�巨
		train.setnextTrain(HeadofTrain.getnextTrain());
		HeadofTrain.setnextTrain(train);
	}
//End of add
	//-------------------------------------------------------------------------------------------------
//Others

//End of Others
	//-------------------------------------------------------------------------------------------------
}



//--------------------------------------------��������
//private static CityConnection Tail;//�ڽ�β�ڵ�
//public void addtotail()//��ӵ�����ĩβ
//{
//	Tail.next=this;
//	Tail=this;
//}
