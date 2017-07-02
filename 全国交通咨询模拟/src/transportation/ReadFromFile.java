package transportation;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ReadFromFile {
	private String CityFile="CityInfo.txt";
	private String TrainFile="TrainInfo.txt";
	private BufferedReader ReadCity;
	private BufferedReader ReadTrain;
	private int xold;
	private int yold;
	public ReadFromFile() throws FileNotFoundException
	{
		ReadCity=new BufferedReader(new FileReader(CityFile));
		ReadTrain=new BufferedReader(new FileReader(TrainFile));
	}
	public BufferedReader getCityReader()
	{
		return ReadCity;
	}
	public BufferedReader getTrainReader()
	{
		return ReadTrain;
	}
	public void ReadAll(JLabel[] label,JLabel[] labelname,City head,EditCity editcity) throws IOException
	{
		ReadCity(label,labelname,head,editcity);
		ReadTrain(head);
	}
	public void ReadCity(JLabel[] label,JLabel[] labelname,City head,EditCity editcity) throws IOException
	{
		
		City temp=head;
		String Name;
		int x,y;
		ImageIcon image=new ImageIcon("UI/CityCircle.png");//添加图片
		while(true)
		{
			Name=ReadCity.readLine();
			if(Name==null)
			{
				break;
			}
			x=Integer.parseInt(ReadCity.readLine());
			y=Integer.parseInt(ReadCity.readLine());
			//System.out.println("Name="+Name+" X="+x+" Y="+y);
			City city=new City();
			city.setNameofCity(Name);
			city.setpos(new Point(x,y));
			int index=city.getIndexofCity();
			//System.out.println("IndexofCity="+city.getIndexofCity());
			
			//button[index]=new JButton();
			label[index]=new JLabel(image);
			labelname[index]=new JLabel();
			//button[index].setBounds(city.getx(), city.gety(), 50, 50);
			label[index].setBounds(city.getx(), city.gety(), 50, 50);
			labelname[index].setBounds(city.getx()+5, city.gety(), 50, 50);
			label[index].setBounds(x, y, image.getIconWidth(), image.getIconHeight());
			labelname[index].setText(Name);//设置城市名
			labelname[index].setFont(new Font("方正兰亭超细黑简体",0,20));
			labelname[index].setBounds(x+5, y, 50, 50);
			
			label[index].addMouseListener(new MouseAdapter() 
			{
				  @Override
				  public void mousePressed(MouseEvent e) 
				  {
					  xold=e.getX();
					  yold=e.getY();
				  }
			});
			label[index].addMouseMotionListener(new MouseMotionAdapter() {
				  public void mouseDragged(MouseEvent e) {
					  int xshift = e.getX() - xold;
					  int yshift = e.getY() - yold;
//					  if((label[index].getX()<=50&&xnew<0)||(label[index].getX()>900&&xnew>0)||(label[index].getY()<=50&&ynew<0)||(label[index].getY()>600&&ynew>0))//超出范围
//					  {
//						  return;
//					  }
					  labelname[index].setLocation(labelname[index].getX()+xshift,labelname[index].getY()+yshift);
					  label[index].setLocation(label[index].getX()+xshift,label[index].getY()+yshift);
					  //label[index].setLocation(label[index].getX()+xnew, label[index].getY()+ynew); 
					  editcity.UpdateGraphics();
				  }
			});
			
			//头插法建立链表
			city.setnextCity(temp.getnextCity());
			temp.setnextCity(city);
		}
		ReadCity.close();
	}
	public void ReadTrain(City head) throws NumberFormatException, IOException
	{
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
		ShowAllInfo(head);
		//获取信息
		int startcity;//获取发车城市
		int endcity;//获取到达城市
		String sequence;//获取列车车次
		int starthour;
		int startmin;
		int durationhour;
		int durationmin;
		double price;//获取票价
		String temp;
		while(true)
		{
			//获取信息
			temp=ReadTrain.readLine();
			if(temp==null)
			{
				break;
			}
			startcity=Integer.parseInt(temp);
			endcity=Integer.parseInt(ReadTrain.readLine());
			sequence=ReadTrain.readLine();
			starthour=Integer.parseInt(ReadTrain.readLine());
			startmin=Integer.parseInt(ReadTrain.readLine());
			durationhour=Integer.parseInt(ReadTrain.readLine());
			durationmin=Integer.parseInt(ReadTrain.readLine());
			price=Double.parseDouble(ReadTrain.readLine());
			ReadTrain.readLine();
//			System.out.println(startcity);
//			System.out.println(endcity);
//			System.out.println(sequence);
//			System.out.println(starthour);
//			System.out.println(startmin);
//			System.out.println(durationhour);
//			System.out.println(durationmin);
//			System.out.println(price);
//			System.out.println();
			//建立链表
			//数据操作
			//找出发城市和到达城市
			City start=head.getnextCity();//城市头结点
			while(start!=null)
			{
				if(start.getIndexofCity()==startcity)//找到发车城市
				{
					//System.out.println("Find startcity!");
					break;
				}
				start=start.getnextCity();
			}
			City end=head.getnextCity();//城市头结点
			while(end!=null)
			{
				if(end.getIndexofCity()==endcity)//找到到达城市
				{
					//System.out.println("Find endcity!");
					break;
				}
				end=end.getnextCity();
			}
			//找到两个城市
			Train train=new Train();//新建火车
			train.setSequence(sequence);//设置列车车次
			train.setPrice(price);//设置票价
			train.setStartTime(starthour, startmin);//设置发车时间
			train.setDuration(durationhour, durationmin);//设置历时
			
			start.addTrain(train, end.getIndexofCity());//将火车添加到该城市的邻接链表的火车链表中
		}
	}
	public void ShowAllInfo(City HeadofCity)//输出全部信息
	{
		System.out.println("=-=-=-=-=-");
		City temp=HeadofCity.getnextCity();
		while(temp!=null)
		{
			System.out.println("CityName="+temp.getNameofCity()+" Index="+temp.getIndexofCity()+" PosX="+temp.getx()+" PosY="+temp.gety());
			CityConnection tempcon=temp.getHeadofCityConnection().getnextCon();
			while(tempcon!=null)
			{
				System.out.println("  StartCity="+tempcon.getStartIndex()+" EndCity="+tempcon.getEndIndex());
				Train temptrain=tempcon.getHeadofTrain().getnextTrain();//火车头结点
				while(temptrain!=null)
				{
					System.out.println("    TrainSequence="+temptrain.getSequence()+" TrainPrce="+temptrain.getPrice()+" StartTime="+temptrain.getStartHour()+":"+temptrain.getStartMin()+" Duration(Min)="+temptrain.getDuration());
					temptrain=temptrain.getnextTrain();
				}
				tempcon=tempcon.getnextCon();
			}
			temp=temp.getnextCity();
		}
	}
}
