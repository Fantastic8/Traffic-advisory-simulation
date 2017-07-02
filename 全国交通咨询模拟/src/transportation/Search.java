package transportation;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class Search extends JFrame {
	private JTextField TextStartCity;
	private JTextField TextEndCity;
	private City HeadofCity;
	private ButtonGroup Group;
	private JTable TableTrip;
	private TableModel tablemodel;
	private JLabel LabelTime;
	private JLabel LabelPrice;
	private String[] TableTitle={"乘坐时间","乘坐城市","乘坐列车车次","列车票价","到达城市","到达时间"};
	public Search(EditCity editcity,City head) throws Exception {
		Group=new ButtonGroup();
		HeadofCity=head;
		
		//Table
		TableTrip = new JTable();
		TableTrip.setBorder(null);
		TableTrip.setFont(new Font("幼圆", Font.PLAIN, 15));
		TableTrip.setBounds(65, 150, 470, 240);
		TableTrip.setRowHeight(30);//设置表格高度
		JScrollPane scrollpane = new JScrollPane(TableTrip);
		scrollpane.setBounds(14, 150, 575, 234);
		getContentPane().add(scrollpane);
		//添加空白
		String content[][]=new String[8][6];
		for(int i=0;i<8;i++)
		{
			for(int y=0;y<6;y++)
			{
				content[i][y]=null;
			}
		}
		tablemodel=new DefaultTableModel(content,TableTitle){
		public boolean isCellEditable(int rowIndex,int columnIndex)
			{
				return false;
			}
		};
		TableTrip.setModel(tablemodel);
		
		setBounds(100, 100, 621, 545);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel LabelStartCity = new JLabel("\u51FA\u53D1\u5730");
		LabelStartCity.setBounds(164, 65, 72, 18);
		getContentPane().add(LabelStartCity);
		
		JLabel LabelEndCity = new JLabel("\u76EE\u7684\u5730");
		LabelEndCity.setBounds(332, 65, 72, 18);
		getContentPane().add(LabelEndCity);
		
		TextStartCity = new JTextField();
		TextStartCity.setBounds(150, 100, 86, 24);
		getContentPane().add(TextStartCity);
		TextStartCity.setColumns(10);
		
		TextEndCity = new JTextField();
		TextEndCity.setBounds(317, 100, 86, 24);
		getContentPane().add(TextEndCity);
		TextEndCity.setColumns(10);
		
		JLabel LabelTimeInfo = new JLabel("\u5386\u65F6");
		LabelTimeInfo.setBounds(196, 415, 40, 18);
		getContentPane().add(LabelTimeInfo);
		
		LabelTime = new JLabel("");
		LabelTime.setBounds(251, 415, 72, 18);
		getContentPane().add(LabelTime);
		
		JLabel LabelPriceInfo = new JLabel("\u65C5\u8D39");
		LabelPriceInfo.setBounds(196, 446, 40, 18);
		getContentPane().add(LabelPriceInfo);
		
		LabelPrice = new JLabel("");
		LabelPrice.setBounds(251, 446, 72, 18);
		getContentPane().add(LabelPrice);
		
		JLabel LabelInfo1 = new JLabel("\u5C0F\u65F6");
		LabelInfo1.setBounds(333, 415, 30, 18);
		getContentPane().add(LabelInfo1);
		
		JLabel LabelInfo2 = new JLabel("\u5143");
		LabelInfo2.setBounds(332, 446, 31, 18);
		getContentPane().add(LabelInfo2);
		
		JRadioButton RadioButtonFast = new JRadioButton("\u6700\u5FEB\u65B9\u6848");
		RadioButtonFast.setBounds(10, 29, 118, 27);
		getContentPane().add(RadioButtonFast);
		Group.add(RadioButtonFast);
		
		JRadioButton RadioButtonSave = new JRadioButton("\u6700\u7701\u65B9\u6848");
		RadioButtonSave.setBounds(10, 61, 118, 27);
		getContentPane().add(RadioButtonSave);
		Group.add(RadioButtonSave);
		
		JRadioButton RadioButtonFew = new JRadioButton("\u8F6C\u4E58\u6700\u5C11");
		RadioButtonFew.setBounds(10, 93, 102, 27);
		getContentPane().add(RadioButtonFew);
		Group.add(RadioButtonFew);
		
		JButton ButtonBack = new JButton("\u8FD4\u56DE");
		ButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editcity.setEnabled(true);
				Search.this.dispose();
			}
		});
		ButtonBack.setBounds(526, 458, 63, 27);
		getContentPane().add(ButtonBack);
		
		//搜索		
		JButton ButtonSearch = new JButton("\u641C\u7D22");
		ButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String StartCity=TextStartCity.getText();
				String EndCity=TextEndCity.getText();
				int Startindex=-1;
				int Endindex=-1;
				//检查
				if(StartCity.equals("")||EndCity.equals(""))
				{
					JOptionPane.showMessageDialog(null,  "请输入城市名", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Illegalchar(StartCity)||Illegalchar(EndCity))//是否包含非法字符
				{
					JOptionPane.showMessageDialog(null,  "城市名包含非法字符", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//是否重复
				
				City temp=HeadofCity.getnextCity();
				while(temp!=null)
				{
					if(temp.getNameofCity().equals(StartCity))
					{
						Startindex=temp.getIndexofCity();
						break;
					}
					temp=temp.getnextCity();
				}
				if(temp==null)
				{
					JOptionPane.showMessageDialog(null,  "出发城市不存在", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				temp=HeadofCity.getnextCity();
				while(temp!=null)
				{
					if(temp.getNameofCity().equals(EndCity))
					{
						Endindex=temp.getIndexofCity();
						break;
					}
					temp=temp.getnextCity();
				}
				if(temp==null)
				{
					JOptionPane.showMessageDialog(null,  "目的地城市不存在", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(StartCity.equals(EndCity))
				{
					JOptionPane.showMessageDialog(null,  "出发地与目的地不能相同", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//检查完毕
				if(RadioButtonFast.isSelected()||RadioButtonSave.isSelected()||RadioButtonFew.isSelected())
				{
					if(RadioButtonFast.isSelected())//最快
					{
						try {
							FindTripFast(Startindex,Endindex);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(RadioButtonSave.isSelected())//最省
					{
						FindTripSave(Startindex,Endindex);
					}
					else//中转次数最少
					{
						FindTripFew(Startindex,Endindex);
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "选择一种方案", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		ButtonSearch.setBounds(475, 99, 63, 27);
		getContentPane().add(ButtonSearch);
	}
	public boolean Illegalchar(String s)//判断是否包含非法字符
	{
		if(s.contains(".")||s.contains(",")||s.contains("'")||s.contains("\"")||s.contains("\\")||s.contains("|")||s.contains("!")||s.contains("~")||s.contains(" ")||s.contains("@")||s.contains("#")||s.contains("$")||s.contains("%")||s.contains("^")||s.contains("&")||s.contains("*")||s.contains("(")||s.contains(")")||s.contains("-")||s.contains("_")||s.contains("+")||s.contains("=")||s.contains("{")||s.contains("}")||s.contains("[")||s.contains("]")||s.contains("'")||s.contains("`"))
		{
			return true;
		}
		return false;
	}
	public void FindTripSave(int start,int end)//查找最省钱
	{
		System.out.println("startcity="+start+" endcity="+end);
		//Dijstra算法
		int CityAmount=HeadofCity.getAmountofCity();
		String DijTrip[][]=new String[CityAmount][11];
		/*
		 * DijTrip[i][0]=乘坐的第几天
		 * DijTrip[i][1]=乘坐当天的小时
		 * DijTrip[i][2]=乘坐当天的分钟
		 * DijTrip[i][3]=乘坐的城市名
		 * DijTrip[i][4]=乘坐的列车车次
		 * DijTrip[i][5]=乘坐的列车总票价
		 * DijTrip[i][6]=到达的城市名
		 * DijTrip[i][7]=到达时为第几天
		 * DijTrip[i][8]=到达当天的小时
		 * DijTrip[i][9]=到达当天的分钟
		 * DijTrip[i][10]=是否被计算过
		 */
		//初始化
		for(int i=0;i<CityAmount;i++)
		{
			DijTrip[i][0]="0";
			DijTrip[i][1]="0";
			DijTrip[i][2]="0";
			DijTrip[i][3]=null;
			DijTrip[i][4]=null;
			DijTrip[i][5]="10000";
			DijTrip[i][6]=null;
			//赋值到达的城市名
			City temp=HeadofCity.getnextCity();
			while(temp!=null)
			{
				if(temp.getIndexofCity()==i)
				{
					DijTrip[i][6]=temp.getNameofCity();
					break;
				}
				temp=temp.getnextCity();
			}
			DijTrip[i][7]="0";
			DijTrip[i][8]="0";
			DijTrip[i][9]="0";
			DijTrip[i][10]="false";
		}
		//-------开始算
		int count=0;//记得++***
		int index=start;//出发城市下标***
		Time time=new Time();
		DijTrip[index][8]=String.valueOf(time.getHour());
		DijTrip[index][9]=String.valueOf(time.getMinute());
		DijTrip[index][5]="0";//day
		DijTrip[index][10]="true";//出发城市
		
		//System.out.println("-----------初始化完毕----------");
		//ShowDij(DijTrip);
		
		City tempcity=HeadofCity.getnextCity();
		CityConnection tempcon;
		Train temptrain;
		String tempday,temphour,tempmin;
		int days;
		double Sum;
		double tempsum;
		while(count<CityAmount-1)
		{
			tempcity=HeadofCity.getnextCity();
			while(tempcity!=null)
			{
				if(tempcity.getIndexofCity()==index)
				{
					break;
				}
				tempcity=tempcity.getnextCity();
			}
			//找到出发城市
			tempcon=tempcity.getHeadofCityConnection().getnextCon();
			//开始对数组更新
			while(tempcon!=null)//选择路线
			{
				temptrain=tempcon.getHeadofTrain().getnextTrain();
				
				City endcity=HeadofCity.getnextCity();
				while(endcity!=null)
				{
					if(endcity.getIndexofCity()==tempcon.getEndIndex())
					{
						break;
					}
					endcity=endcity.getnextCity();
				}
				//endcity为该路线的到达城市
				while(temptrain!=null)//选择列车--价格最小
				{
					System.out.println("出发城市:"+tempcity.getNameofCity()+" 到达城市:"+endcity.getNameofCity());
					days=0;//计算时间
					//尝试列车选择--从index到达tempcon.getEndIndex()
					tempday=DijTrip[tempcon.getEndIndex()][7];
					temphour=DijTrip[tempcon.getEndIndex()][8];
					tempmin=DijTrip[tempcon.getEndIndex()][9];
					System.out.println("到达城市时间: "+DijTrip[index][8]+":"+DijTrip[index][9]+" 列车发车时间: "+temptrain.getStartHour()+":"+temptrain.getStartMin());
					if(IsAfter(Integer.valueOf(DijTrip[index][8]),Integer.valueOf(DijTrip[index][9]),temptrain.getStartHour(),temptrain.getStartMin()))//前一城市到达日期在这列火车发车之后，则需要等待第二天才能赶上这列火车
					{
						days++;
					}
					System.out.println("尝试的列车将在第"+days+"天后到达");
					//比较到达日期的早晚
					//System.out.println(tempday+":"+temphour+":"+tempmin+"  "+(days+Integer.valueOf(DijTrip[index][7]))+":"+DijTrip[index][8]+":"+DijTrip[index][9]);
					System.out.println(tempday+":"+temphour+":"+tempmin+"  "+(days+temptrain.getEndDay()+Integer.valueOf(DijTrip[index][7]))+":"+temptrain.getEndHour()+":"+temptrain.getEndMin());				
					
					if(Double.valueOf(DijTrip[tempcon.getEndIndex()][5])>Double.valueOf(DijTrip[tempcon.getStartIndex()][5])+temptrain.getPrice())//需要改变
					{
						System.out.println("Change");
						DijTrip[tempcon.getEndIndex()][0]=String.valueOf(Integer.valueOf(DijTrip[index][7])+days);//发车第几天
						DijTrip[tempcon.getEndIndex()][1]=String.valueOf(temptrain.getStartHour());//发车小时
						DijTrip[tempcon.getEndIndex()][2]=String.valueOf(temptrain.getStartMin());//发车分钟
						DijTrip[tempcon.getEndIndex()][3]=String.valueOf(tempcity.getNameofCity());//出发城市
						DijTrip[tempcon.getEndIndex()][4]=temptrain.getSequence();//车次
						DijTrip[tempcon.getEndIndex()][5]=String.valueOf(Double.valueOf(DijTrip[tempcon.getStartIndex()][5])+temptrain.getPrice());//票价
						DijTrip[tempcon.getEndIndex()][7]=String.valueOf(Integer.valueOf(DijTrip[index][7])+days+temptrain.getEndDay());//到达第几天
						DijTrip[tempcon.getEndIndex()][8]=String.valueOf(temptrain.getEndHour());
						DijTrip[tempcon.getEndIndex()][9]=String.valueOf(temptrain.getEndMin());
						System.out.println("线路:"+DijTrip[tempcon.getEndIndex()][3]+"到"+DijTrip[tempcon.getEndIndex()][6]+" 发车时间 "+DijTrip[tempcon.getEndIndex()][0]+":"+DijTrip[tempcon.getEndIndex()][1]+":"+DijTrip[tempcon.getEndIndex()][2]+" 到达时间 "+DijTrip[tempcon.getEndIndex()][7]+":"+DijTrip[tempcon.getEndIndex()][8]+":"+DijTrip[tempcon.getEndIndex()][9]);
					}
					temptrain=temptrain.getnextTrain();
				}
				tempcon=tempcon.getnextCon();
			}
			//开始找时间最短的设置index DijTrip[index][10]="true";//该路线最短
			int save=0;
			while(save==start||Boolean.valueOf(DijTrip[save][10])==true)//不能与start相同,也不能是计算过的
			{
				save++;
				if(save>=CityAmount)
				{
					break;
				}
			}
			System.out.println("Init Save="+save);
			for(int i=0;i<CityAmount;i++)
			{
				if(Boolean.valueOf(DijTrip[i][10])==true)
				{
					continue;
				}
				//System.out.println("Compare indexA="+i+" DayA:"+Integer.valueOf(DijTrip[i][7])+" HourA:"+Integer.valueOf(DijTrip[i][8])+" MinA:"+Integer.valueOf(DijTrip[i][9])+" indexB="+fast+" DayB:"+DijTrip[fast][7]+" HourB:"+DijTrip[fast][8]+" MinB"+DijTrip[fast][9]);
				if(Double.valueOf(DijTrip[i][5])<Double.valueOf(DijTrip[save][5]))
				{
					save=i;
				}
			}
			index=save;
			System.out.println("Fast="+index+" Day="+DijTrip[index][7]+" Hour="+DijTrip[index][8]+" Min="+DijTrip[index][9]);
			DijTrip[index][10]="true";
			count++;
			
			System.out.println("-----------一个结点----------");
			ShowDij(DijTrip);
		}
		
		
		//开始设置表格
		
		int row=0;//行
		int temprow;
		int rowlen=0;//行数量
		int column=0;//列
		double SumPrice=0;
		int SumDuration=0;
		String content[][];
		//遍历一遍获得行数
		City scity,ecity;
		ecity=getCityByIndex(end);
		while(true)
		{
			scity=getCityByName(DijTrip[ecity.getIndexofCity()][3]);
			if(scity==null)//没有找到
			{
				JOptionPane.showMessageDialog(null,  "无法到达该城市", "错误!",JOptionPane.ERROR_MESSAGE);
				return;
			}
			rowlen++;
			ecity=scity;
			if(ecity.getIndexofCity()==start)
			{
				break;
			}
		}
		System.out.println("路线数量:"+rowlen);
		if(rowlen<8)//填充
		{
			content=new String[8][6];
		}
		else
		{
			content=new String[rowlen][6];
		}
		/*
		 * DijTrip[i][0]=乘坐的第几天
		 * DijTrip[i][1]=乘坐当天的小时
		 * DijTrip[i][2]=乘坐当天的分钟
		 * DijTrip[i][3]=乘坐的城市名
		 * DijTrip[i][4]=乘坐的列车车次
		 * DijTrip[i][5]=乘坐的列车票价
		 * DijTrip[i][6]=到达的城市名
		 * DijTrip[i][7]=到达时为第几天
		 * DijTrip[i][8]=到达当天的小时
		 * DijTrip[i][9]=到达当天的分钟
		 * DijTrip[i][10]=是否被计算过
		 */
		ecity=getCityByIndex(end);
		SumDuration=Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])*24+Integer.valueOf(DijTrip[ecity.getIndexofCity()][8])-time.getHour();//减去当前时间
		LabelTime.setText(String.valueOf(SumDuration));//历时
		for(row=rowlen-1;row>=0;row--)//逆序插入
		{
			content[row][0]="第"+(Integer.valueOf(DijTrip[ecity.getIndexofCity()][0])+1)+"天 "+DijTrip[ecity.getIndexofCity()][1]+":"+DijTrip[ecity.getIndexofCity()][2];//乘坐时间
			content[row][1]=DijTrip[ecity.getIndexofCity()][3];//乘坐城市
			content[row][2]=DijTrip[ecity.getIndexofCity()][4];//乘坐列车车次
			content[row][3]=String.valueOf(getTrainBySequence(DijTrip[ecity.getIndexofCity()][4]).getPrice());//列车票价
			SumPrice+=getTrainBySequence(DijTrip[ecity.getIndexofCity()][4]).getPrice();
			content[row][4]=DijTrip[ecity.getIndexofCity()][6];//到达城市
			content[row][5]="第"+(Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])+1)+"天 "+DijTrip[ecity.getIndexofCity()][8]+":"+DijTrip[ecity.getIndexofCity()][9];//到达时间
			scity=getCityByName(DijTrip[ecity.getIndexofCity()][3]);
			ecity=scity;
		}
		LabelPrice.setText(String.valueOf(SumPrice));
		if(rowlen<8)//没有填充满
		{
			for(row=rowlen;row<8;row++)
			{
				content[row][0]=null;
				content[row][1]=null;
				content[row][2]=null;
				content[row][3]=null;
				content[row][4]=null;
				content[row][5]=null;
			}
		}
		tablemodel=new DefaultTableModel(content,TableTitle){
			public boolean isCellEditable(int rowIndex,int columnIndex)
			{
				return false;
			}
		};
		TableTrip.setModel(tablemodel);
	}
	public void FindTripFew(int start,int end)//中转次数最少
	{
		System.out.println("start="+start+" end="+end);
		int[][] Array=new int[100][3];//
		for(int i=0;i<100;i++)
		{
			for(int y=0;y<3;y++)
			{
				Array[i][y]=0;
			}
		}
		City startcity=getCityByIndex(start);
		City temp;
		CityConnection tempcon;
		int index=0;//增加到的下标
		int read=0;//所读取到的下标
		Array[index][0]=-1;
		Array[index][1]=start;
		boolean status=false;
		while(true)
		{
			tempcon=getCityByIndex(Array[read][1]).getHeadofCityConnection().getnextCon();
			while(tempcon!=null)
			{
				int i;
				for(i=0;i<=index;i++)//检查该数组中有没有tempcon.getEndIndex()
				{
					if(Array[i][1]==tempcon.getEndIndex())
					{
						break;
					}
				}
				if(i<=index)//有该城市
				{
					tempcon=tempcon.getnextCon();
					continue;
				}
				//在数组中增加结点
				index++;
				Array[index][0]=read;//数组下标
				Array[index][1]=tempcon.getEndIndex();
				Array[index][2]=1;//不能再添加
				if(Array[index][1]==end)//找到
				{
					status=true;
					break;
				}
				tempcon=tempcon.getnextCon();
			}
			if(status==true)
			{
				break;
			}
			read++;
			if(read>index)
			{
				break;
			}
		}
		if(status==false)
		{
			JOptionPane.showMessageDialog(null,  "无法到达该城市", "错误!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//输出找到的数组
		System.out.println("================");
		for(int i=0;i<=index;i++)
		{
			System.out.println("Array["+i+"][0]="+Array[i][0]+" Array["+i+"][1]="+Array[i][1]);
		}
		//开始回溯
		//遍历一遍记录中转次数
		int rowlen=0;
		int tempindex=index;
		while(tempindex!=-1)
		{
			tempindex=Array[tempindex][0];
			rowlen++;
		}
		rowlen--;
		//开始对数组赋值
		System.out.println("一共中转:"+rowlen+"次");
		
		//开始设置表格
		
		int row=0;//行
		String content[][];
		if(rowlen<8)//填充
		{
			content=new String[8][6];
		}
		else
		{
			content=new String[rowlen][6];
		}
		/*
		 * DijTrip[i][3]=乘坐的城市名
		 * DijTrip[i][6]=到达的城市名
		 */
		//ecity=getCityByIndex(end);
		//SumDuration=Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])*24+Integer.valueOf(DijTrip[ecity.getIndexofCity()][8])-time.getHour();//减去当前时间
		LabelTime.setText("");//历时
		tempindex=index;
		for(row=rowlen-1;row>=0;row--)//逆序插入
		{
			content[row][0]="";//乘坐时间
			content[row][1]=getCityByIndex(Array[Array[tempindex][0]][1]).getNameofCity();//乘坐城市
			content[row][2]="";//乘坐列车车次
			content[row][3]="";//列车票价
			content[row][4]=getCityByIndex(Array[tempindex][1]).getNameofCity();//到达城市
			content[row][5]="";//到达时间
			tempindex=Array[tempindex][0];
		}
		LabelPrice.setText("");
		if(rowlen<8)//没有填充满
		{
			for(row=rowlen;row<8;row++)
			{
				content[row][0]=null;
				content[row][1]=null;
				content[row][2]=null;
				content[row][3]=null;
				content[row][4]=null;
				content[row][5]=null;
			}
		}
		tablemodel=new DefaultTableModel(content,TableTitle){
			public boolean isCellEditable(int rowIndex,int columnIndex)
			{
				return false;
			}
		};
		TableTrip.setModel(tablemodel);
	}
	public void FindTripFast(int start,int end) throws Exception//查找最快
	{
		System.out.println("startcity="+start+" endcity="+end);
		//Dijstra算法
		int CityAmount=HeadofCity.getAmountofCity();
		String DijTrip[][]=new String[CityAmount][11];
		/*
		 * DijTrip[i][0]=乘坐的第几天
		 * DijTrip[i][1]=乘坐当天的小时
		 * DijTrip[i][2]=乘坐当天的分钟
		 * DijTrip[i][3]=乘坐的城市名
		 * DijTrip[i][4]=乘坐的列车车次
		 * DijTrip[i][5]=乘坐的列车票价
		 * DijTrip[i][6]=到达的城市名
		 * DijTrip[i][7]=到达时为第几天
		 * DijTrip[i][8]=到达当天的小时
		 * DijTrip[i][9]=到达当天的分钟
		 * DijTrip[i][10]=是否被计算过
		 */
		//初始化
		for(int i=0;i<CityAmount;i++)
		{
			DijTrip[i][0]="0";
			DijTrip[i][1]="0";
			DijTrip[i][2]="0";
			DijTrip[i][3]=null;
			DijTrip[i][4]=null;
			DijTrip[i][5]="0";
			DijTrip[i][6]=null;
			//赋值到达的城市名
			City temp=HeadofCity.getnextCity();
			while(temp!=null)
			{
				if(temp.getIndexofCity()==i)
				{
					DijTrip[i][6]=temp.getNameofCity();
					break;
				}
				temp=temp.getnextCity();
			}
			DijTrip[i][7]="10000";
			DijTrip[i][8]="10000";
			DijTrip[i][9]="10000";
			DijTrip[i][10]="false";
		}
		//-------开始算
		int count=0;//记得++***
		int index=start;//出发城市下标***
		Time time=new Time();
		
		DijTrip[index][7]="0";//day
		DijTrip[index][8]=String.valueOf(time.getHour());//hour
		DijTrip[index][9]=String.valueOf(time.getMinute());//min
		DijTrip[index][10]="true";//出发城市
		
		System.out.println("-----------初始化完毕----------");
		ShowDij(DijTrip);
		
		City tempcity=HeadofCity.getnextCity();
		CityConnection tempcon;
		Train temptrain;
		String tempday,temphour,tempmin;
		int days=0;
		long Durationone,Durationtwo;
		while(count<CityAmount-1)
		{
			tempcity=HeadofCity.getnextCity();
			while(tempcity!=null)
			{
				if(tempcity.getIndexofCity()==index)
				{
					break;
				}
				tempcity=tempcity.getnextCity();
			}
			//找到出发城市
			tempcon=tempcity.getHeadofCityConnection().getnextCon();
			//开始对数组更新
			while(tempcon!=null)//选择路线
			{
				temptrain=tempcon.getHeadofTrain().getnextTrain();
				
				City endcity=HeadofCity.getnextCity();
				while(endcity!=null)
				{
					if(endcity.getIndexofCity()==tempcon.getEndIndex())
					{
						break;
					}
					endcity=endcity.getnextCity();
				}
				//endcity为该路线的到达城市
				while(temptrain!=null)//选择列车--时间最短
				{
					System.out.println("出发城市:"+tempcity.getNameofCity()+" 到达城市:"+endcity.getNameofCity());
					days=0;//计算时间
					//尝试列车选择--从index到达tempcon.getEndIndex()
					tempday=DijTrip[tempcon.getEndIndex()][7];
					temphour=DijTrip[tempcon.getEndIndex()][8];
					tempmin=DijTrip[tempcon.getEndIndex()][9];
					System.out.println("到达城市时间: "+DijTrip[index][8]+":"+DijTrip[index][9]+" 列车发车时间: "+temptrain.getStartHour()+":"+temptrain.getStartMin());
					if(IsAfter(Integer.valueOf(DijTrip[index][8]),Integer.valueOf(DijTrip[index][9]),temptrain.getStartHour(),temptrain.getStartMin()))//前一城市到达日期在这列火车发车之后，则需要等待第二天才能赶上这列火车
					{
						days++;
					}
					System.out.println("尝试的列车将在第"+days+"天后到达");
					//比较到达日期的早晚
					//System.out.println(tempday+":"+temphour+":"+tempmin+"  "+(days+Integer.valueOf(DijTrip[index][7]))+":"+DijTrip[index][8]+":"+DijTrip[index][9]);
					System.out.println(tempday+":"+temphour+":"+tempmin+"  "+(days+temptrain.getEndDay()+Integer.valueOf(DijTrip[index][7]))+":"+temptrain.getEndHour()+":"+temptrain.getEndMin());
					if(IsAfter(Integer.valueOf(tempday),Integer.valueOf(temphour),Integer.valueOf(tempmin),(days+temptrain.getEndDay()+Integer.valueOf(DijTrip[index][7])),temptrain.getEndHour(),temptrain.getEndMin()))//需要改变
					{
						System.out.println("Change");
						DijTrip[tempcon.getEndIndex()][0]=String.valueOf(Integer.valueOf(DijTrip[index][7])+days);//发车第几天
						DijTrip[tempcon.getEndIndex()][1]=String.valueOf(temptrain.getStartHour());//发车小时
						DijTrip[tempcon.getEndIndex()][2]=String.valueOf(temptrain.getStartMin());//发车分钟
						DijTrip[tempcon.getEndIndex()][3]=String.valueOf(tempcity.getNameofCity());//出发城市
						DijTrip[tempcon.getEndIndex()][4]=temptrain.getSequence();//车次
						DijTrip[tempcon.getEndIndex()][5]=String.valueOf(temptrain.getPrice());//票价
						//DijTrip[tempcon.getEndIndex()][6]=String.valueOf(endcity.getNameofCity());//到达的城市名
						DijTrip[tempcon.getEndIndex()][7]=String.valueOf(Integer.valueOf(DijTrip[index][7])+days+temptrain.getEndDay());//到达第几天
						DijTrip[tempcon.getEndIndex()][8]=String.valueOf(temptrain.getEndHour());
						DijTrip[tempcon.getEndIndex()][9]=String.valueOf(temptrain.getEndMin());
						System.out.println("线路:"+DijTrip[tempcon.getEndIndex()][3]+"到"+DijTrip[tempcon.getEndIndex()][6]+" 发车时间 "+DijTrip[tempcon.getEndIndex()][0]+":"+DijTrip[tempcon.getEndIndex()][1]+":"+DijTrip[tempcon.getEndIndex()][2]+" 到达时间 "+DijTrip[tempcon.getEndIndex()][7]+":"+DijTrip[tempcon.getEndIndex()][8]+":"+DijTrip[tempcon.getEndIndex()][9]);
					}
					temptrain=temptrain.getnextTrain();
				}
				tempcon=tempcon.getnextCon();
			}
			//开始找时间最短的设置index DijTrip[index][10]="true";//该路线最短
			int fast=0;
			while(fast==start||Boolean.valueOf(DijTrip[fast][10])==true)//不能与start相同,也不能是计算过的
			{
				fast++;
				if(fast>=CityAmount)
				{
					break;
				}
			}
			System.out.println("Init Fast="+fast);
			for(int i=0;i<CityAmount;i++)
			{
				if(Boolean.valueOf(DijTrip[i][10])==true)
				{
					continue;
				}
				System.out.println("Compare indexA="+i+" DayA:"+Integer.valueOf(DijTrip[i][7])+" HourA:"+Integer.valueOf(DijTrip[i][8])+" MinA:"+Integer.valueOf(DijTrip[i][9])+" indexB="+fast+" DayB:"+DijTrip[fast][7]+" HourB:"+DijTrip[fast][8]+" MinB"+DijTrip[fast][9]);
				if(Integer.valueOf(DijTrip[i][7])<Integer.valueOf(DijTrip[fast][7]))
				{
					fast=i;
				}
				else if(Integer.valueOf(DijTrip[i][7])==Integer.valueOf(DijTrip[fast][7]))
				{
					if(Integer.valueOf(DijTrip[i][8])<Integer.valueOf(DijTrip[fast][8]))
					{
						fast=i;
					}
					else if(Integer.valueOf(DijTrip[i][8])==Integer.valueOf(DijTrip[fast][8]))
					{
						if(Integer.valueOf(DijTrip[i][9])<Integer.valueOf(DijTrip[fast][9]))
						{
							fast=i;
						}
					}
				}
			}
			index=fast;
			System.out.println("Fast="+index+" Day="+DijTrip[index][7]+" Hour="+DijTrip[index][8]+" Min="+DijTrip[index][9]);
			DijTrip[index][10]="true";
			count++;
			
			System.out.println("-----------一个结点----------");
			ShowDij(DijTrip);
		}
		
		
		//开始设置表格
		
		int row=0;//行
		int temprow;
		int rowlen=0;//行数量
		int column=0;//列
		double SumPrice=0;
		int SumDuration=0;
		String content[][];
		//rowlen=HeadofCity.getHeadofCityConnection().getHeadofTrain().getAmount();
		//遍历一遍获得行数
		City scity,ecity;
		ecity=getCityByIndex(end);
		while(true)
		{
			scity=getCityByName(DijTrip[ecity.getIndexofCity()][3]);
			if(scity==null)//没有找到
			{
				JOptionPane.showMessageDialog(null,  "无法到达该城市", "错误!",JOptionPane.ERROR_MESSAGE);
				return;
			}
			rowlen++;
			ecity=scity;
			if(ecity.getIndexofCity()==start)
			{
				break;
			}
		}
		System.out.println("路线数量:"+rowlen);
		if(rowlen<8)//填充
		{
			content=new String[8][6];
		}
		else
		{
			content=new String[rowlen][6];
		}
		/*
		 * DijTrip[i][0]=乘坐的第几天
		 * DijTrip[i][1]=乘坐当天的小时
		 * DijTrip[i][2]=乘坐当天的分钟
		 * DijTrip[i][3]=乘坐的城市名
		 * DijTrip[i][4]=乘坐的列车车次
		 * DijTrip[i][5]=乘坐的列车票价
		 * DijTrip[i][6]=到达的城市名
		 * DijTrip[i][7]=到达时为第几天
		 * DijTrip[i][8]=到达当天的小时
		 * DijTrip[i][9]=到达当天的分钟
		 * DijTrip[i][10]=是否被计算过
		 */
		ecity=getCityByIndex(end);
		SumDuration=Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])*24+Integer.valueOf(DijTrip[ecity.getIndexofCity()][8])-time.getHour();//减去当前时间
		LabelTime.setText(String.valueOf(SumDuration));//历时
		for(row=rowlen-1;row>=0;row--)//逆序插入
		{
			content[row][0]="第"+(Integer.valueOf(DijTrip[ecity.getIndexofCity()][0])+1)+"天 "+DijTrip[ecity.getIndexofCity()][1]+":"+DijTrip[ecity.getIndexofCity()][2];//乘坐时间
			content[row][1]=DijTrip[ecity.getIndexofCity()][3];//乘坐城市
			content[row][2]=DijTrip[ecity.getIndexofCity()][4];//乘坐列车车次
			content[row][3]=DijTrip[ecity.getIndexofCity()][5];//列车票价
			SumPrice+=Double.valueOf(DijTrip[ecity.getIndexofCity()][5]);
			content[row][4]=DijTrip[ecity.getIndexofCity()][6];//到达城市
			content[row][5]="第"+(Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])+1)+"天 "+DijTrip[ecity.getIndexofCity()][8]+":"+DijTrip[ecity.getIndexofCity()][9];//到达时间
			scity=getCityByName(DijTrip[ecity.getIndexofCity()][3]);
			ecity=scity;
		}
		LabelPrice.setText(String.valueOf(SumPrice));
		if(rowlen<8)//没有填充满
		{
			for(row=rowlen;row<8;row++)
			{
				content[row][0]=null;
				content[row][1]=null;
				content[row][2]=null;
				content[row][3]=null;
				content[row][4]=null;
				content[row][5]=null;
			}
		}
		tablemodel=new DefaultTableModel(content,TableTitle){
			public boolean isCellEditable(int rowIndex,int columnIndex)
			{
				return false;
			}
		};
		TableTrip.setModel(tablemodel);
	}
	
	public boolean IsAfter(int houra,int mina,int hourb,int minb)//判断A时间是否在B后
	{
		if(houra>hourb)
		{
			return true;
		}
		else if(houra==hourb&&mina>minb)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean IsAfter(int daya,int houra,int mina,int dayb,int hourb,int minb)//判断A时间是否在B后
	{
		if(daya>dayb)
		{
			return true;
		}
		else if(daya==dayb&&houra>hourb)
		{
			return true;
		}
		else if(daya==dayb&&houra==hourb&&mina>minb)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int WaitMin(int houra,int mina,int hourb,int minb)//从时间A到时间B需要多少分钟
	{
		if(IsAfter(houra,mina,hourb,minb))//过一天
		{
			return 1440-WaitMin(hourb,minb,houra,mina);
		}
		else
		{
			return (hourb-houra)*60+minb-mina;
		}
	}
	public City getCityByIndex(int index)
	{
		City city=HeadofCity.getnextCity();
		while(city!=null)
		{
			if(city.getIndexofCity()==index)
			{
				return city;
			}
			city=city.getnextCity();
		}
		return null;
	}
	public City getCityByName(String name)
	{
		City city=HeadofCity.getnextCity();
		while(city!=null)
		{
			if(city.getNameofCity().equals(name))
			{
				return city;
			}
			city=city.getnextCity();
		}
		return null;
	}
	public Train getTrainBySequence(String sequence)
	{
		City temp=HeadofCity.getnextCity();
		CityConnection tempcon;
		Train train;
		while(temp!=null)
		{
			tempcon=temp.getHeadofCityConnection().getnextCon();
			while(tempcon!=null)
			{
				train=tempcon.getHeadofTrain().getnextTrain();
				while(train!=null)
				{
					if(train.getSequence().equals(sequence))
					{
						return train;
					}
					train=train.getnextTrain();
				}
				tempcon=tempcon.getnextCon();
			}
			temp=temp.getnextCity();
		}
		return null;
	}
	public void ShowDij(String[][] Dij)
	{
		for(int i=0;i<HeadofCity.getAmountofCity();i++)
		{
			/*
			 * DijTrip[i][0]=乘坐的第几天
			 * DijTrip[i][1]=乘坐当天的小时
			 * DijTrip[i][2]=乘坐当天的分钟
			 * DijTrip[i][3]=乘坐的城市名
			 * DijTrip[i][4]=乘坐的列车车次
			 * DijTrip[i][5]=乘坐的列车票价
			 * DijTrip[i][6]=到达的城市名
			 * DijTrip[i][7]=到达时为第几天
			 * DijTrip[i][8]=到达当天的小时
			 * DijTrip[i][9]=到达当天的分钟
			 * DijTrip[i][10]=是否被计算过
			 */
			System.out.println("==========");
			System.out.println("乘坐的第几天:"+Dij[i][0]);
			System.out.println("乘坐当天的小时:"+Dij[i][1]);
			System.out.println("乘坐当天的分钟:"+Dij[i][2]);
			System.out.println("乘坐的城市名:"+Dij[i][3]);
			System.out.println("乘坐的列车车次:"+Dij[i][4]);
			System.out.println("乘坐的列车票价:"+Dij[i][5]);
			System.out.println("到达的城市名:"+Dij[i][6]);
			System.out.println("到达时为第几天:"+Dij[i][7]);
			System.out.println("到达当天的小时:"+Dij[i][8]);
			System.out.println("到达当天的分钟:"+Dij[i][9]);
			System.out.println("是否被计算过:"+Dij[i][10]);
		}
	}
}
