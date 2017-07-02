package transportation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

public class SaveToFile {
	private String CityFile="CityInfo.txt";
	private String TrainFile="TrainInfo.txt";
	private BufferedWriter WriteCity;
	private BufferedWriter WriteTrain;
	public SaveToFile() throws IOException
	{
		WriteCity=null;
		WriteTrain=null;
	}
	public BufferedWriter getCityWriter()
	{
		return WriteCity;
	}
	public BufferedWriter getTrainWriter()
	{
		return WriteTrain;
	}
	public void SaveAll(City Head,JLabel[] LabelCity) throws IOException
	{
		SaveCity(Head,LabelCity);
		SaveTrain(Head);
	}
	public void SaveCity(City Head,JLabel[] LabelCity) throws IOException
	{
		WriteCity=new BufferedWriter(new FileWriter(CityFile));
		Head=Head.getnextCity();//指向数据域
		SaveCityAid headaid=new SaveCityAid();//头结点
		SaveCityAid temp=headaid;
		//翻向至辅助链表
		while(Head!=null)
		{
			SaveCityAid aid=new SaveCityAid();
			aid.CityName=Head.getNameofCity();
			aid.x=LabelCity[Head.getIndexofCity()].getX();
			aid.y=LabelCity[Head.getIndexofCity()].getY();
			//头插法
			aid.next=headaid.next;
			headaid.next=aid;
			Head=Head.getnextCity();
		}
		//从辅助链表里获取城市名,再输出
		temp=headaid.next;
		while(temp!=null)
		{
			WriteCity.write(temp.CityName);
			WriteCity.newLine();
			WriteCity.write(String.valueOf(temp.x));
			WriteCity.newLine();
			WriteCity.write(String.valueOf(temp.y));
			WriteCity.newLine();
			System.out.println("Name="+temp.CityName+" X="+temp.x+" Y="+temp.y);
			temp=temp.next;
		}
		WriteCity.close();
	}
	public void SaveTrain(City Head) throws IOException
	{
		WriteTrain=new BufferedWriter(new FileWriter(TrainFile));
		City scity=Head.getnextCity();
		int Startcity;
		int Endcity;
		SaveTrainAid head=new SaveTrainAid();
		//翻转
		while(scity!=null)
		{
			CityConnection tempcon=scity.getHeadofCityConnection().getnextCon();
			while(tempcon!=null)
			{
				Startcity=tempcon.getStartIndex();
				Endcity=tempcon.getEndIndex();
				Train temptrain=tempcon.getHeadofTrain().getnextTrain();
				while(temptrain!=null)
				{
					//为aid赋值
					SaveTrainAid aid=new SaveTrainAid();
					aid.StartIndex=Startcity;
					aid.EndIndex=Endcity;
					aid.Sequence=temptrain.getSequence();
					aid.StartHour=temptrain.getStartHour();
					aid.StartMin=temptrain.getStartMin();
					aid.DurationHour=temptrain.getDurationHour();
					aid.DurationMin=temptrain.getDurationMin();
					aid.Price=temptrain.getPrice();
					//头插法
					aid.next=head.next;
					head.next=aid;
					
					temptrain=temptrain.getnextTrain();
				}
				tempcon=tempcon.getnextCon();
			}
			scity=scity.getnextCity();
		}
		//存储
		/*
		 * int StartIndex
		 * int EndIndex
		 * String Sequence
		 * int StartHour
		 * int StartMin
		 * int DurationHour
		 * int DurationMin
		 * double Price
		 * (Blank)
		 */
		SaveTrainAid temp=head.next;
		while(temp!=null)
		{
			System.out.println(temp.StartIndex);
			WriteTrain.write(String.valueOf(temp.StartIndex));
			WriteTrain.newLine();
			System.out.println(temp.EndIndex);
			WriteTrain.write(String.valueOf(temp.EndIndex));
			WriteTrain.newLine();
			System.out.println(temp.Sequence);
			WriteTrain.write(temp.Sequence);
			WriteTrain.newLine();
			System.out.println(temp.StartHour);
			WriteTrain.write(String.valueOf(temp.StartHour));
			WriteTrain.newLine();
			System.out.println(temp.StartMin);
			WriteTrain.write(String.valueOf(temp.StartMin));
			WriteTrain.newLine();
			System.out.println(temp.DurationHour);
			WriteTrain.write(String.valueOf(temp.DurationHour));
			WriteTrain.newLine();
			System.out.println(temp.DurationMin);
			WriteTrain.write(String.valueOf(temp.DurationMin));
			WriteTrain.newLine();
			System.out.println(temp.Price);
			WriteTrain.write(String.valueOf(temp.Price));
			WriteTrain.newLine();
			System.out.println();
			WriteTrain.newLine();
			temp=temp.next;
		}
		WriteTrain.close();
	}
	public class SaveCityAid//辅助存储城市
	{
		public String CityName;
		public int x;
		public int y;
		public SaveCityAid next;
		public SaveCityAid()
		{
			CityName=null;
			x=0;
			y=0;
			next=null;
		}
	}
	public class SaveTrainAid//辅助存储列车
	{
		public int StartIndex;
		public int EndIndex;
		public String Sequence;
		public int StartHour;
		public int StartMin;
		public int DurationHour;
		public int DurationMin;
		public double Price;
		public SaveTrainAid next;
		public SaveTrainAid()
		{
			StartIndex=0;
			EndIndex=0;
			Sequence=null;
			Price=0;
			StartHour=0;
			StartMin=0;
			DurationHour=0;
			DurationMin=0;
			next=null;
		}
	}
}
