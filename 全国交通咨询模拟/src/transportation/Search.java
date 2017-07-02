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
	private String[] TableTitle={"����ʱ��","��������","�����г�����","�г�Ʊ��","�������","����ʱ��"};
	public Search(EditCity editcity,City head) throws Exception {
		Group=new ButtonGroup();
		HeadofCity=head;
		
		//Table
		TableTrip = new JTable();
		TableTrip.setBorder(null);
		TableTrip.setFont(new Font("��Բ", Font.PLAIN, 15));
		TableTrip.setBounds(65, 150, 470, 240);
		TableTrip.setRowHeight(30);//���ñ��߶�
		JScrollPane scrollpane = new JScrollPane(TableTrip);
		scrollpane.setBounds(14, 150, 575, 234);
		getContentPane().add(scrollpane);
		//��ӿհ�
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
		
		//����		
		JButton ButtonSearch = new JButton("\u641C\u7D22");
		ButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String StartCity=TextStartCity.getText();
				String EndCity=TextEndCity.getText();
				int Startindex=-1;
				int Endindex=-1;
				//���
				if(StartCity.equals("")||EndCity.equals(""))
				{
					JOptionPane.showMessageDialog(null,  "�����������", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Illegalchar(StartCity)||Illegalchar(EndCity))//�Ƿ�����Ƿ��ַ�
				{
					JOptionPane.showMessageDialog(null,  "�����������Ƿ��ַ�", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//�Ƿ��ظ�
				
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
					JOptionPane.showMessageDialog(null,  "�������в�����", "����!",JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null,  "Ŀ�ĵس��в�����", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(StartCity.equals(EndCity))
				{
					JOptionPane.showMessageDialog(null,  "��������Ŀ�ĵز�����ͬ", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//������
				if(RadioButtonFast.isSelected()||RadioButtonSave.isSelected()||RadioButtonFew.isSelected())
				{
					if(RadioButtonFast.isSelected())//���
					{
						try {
							FindTripFast(Startindex,Endindex);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(RadioButtonSave.isSelected())//��ʡ
					{
						FindTripSave(Startindex,Endindex);
					}
					else//��ת��������
					{
						FindTripFew(Startindex,Endindex);
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,  "ѡ��һ�ַ���", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		ButtonSearch.setBounds(475, 99, 63, 27);
		getContentPane().add(ButtonSearch);
	}
	public boolean Illegalchar(String s)//�ж��Ƿ�����Ƿ��ַ�
	{
		if(s.contains(".")||s.contains(",")||s.contains("'")||s.contains("\"")||s.contains("\\")||s.contains("|")||s.contains("!")||s.contains("~")||s.contains(" ")||s.contains("@")||s.contains("#")||s.contains("$")||s.contains("%")||s.contains("^")||s.contains("&")||s.contains("*")||s.contains("(")||s.contains(")")||s.contains("-")||s.contains("_")||s.contains("+")||s.contains("=")||s.contains("{")||s.contains("}")||s.contains("[")||s.contains("]")||s.contains("'")||s.contains("`"))
		{
			return true;
		}
		return false;
	}
	public void FindTripSave(int start,int end)//������ʡǮ
	{
		System.out.println("startcity="+start+" endcity="+end);
		//Dijstra�㷨
		int CityAmount=HeadofCity.getAmountofCity();
		String DijTrip[][]=new String[CityAmount][11];
		/*
		 * DijTrip[i][0]=�����ĵڼ���
		 * DijTrip[i][1]=���������Сʱ
		 * DijTrip[i][2]=��������ķ���
		 * DijTrip[i][3]=�����ĳ�����
		 * DijTrip[i][4]=�������г�����
		 * DijTrip[i][5]=�������г���Ʊ��
		 * DijTrip[i][6]=����ĳ�����
		 * DijTrip[i][7]=����ʱΪ�ڼ���
		 * DijTrip[i][8]=���ﵱ���Сʱ
		 * DijTrip[i][9]=���ﵱ��ķ���
		 * DijTrip[i][10]=�Ƿ񱻼����
		 */
		//��ʼ��
		for(int i=0;i<CityAmount;i++)
		{
			DijTrip[i][0]="0";
			DijTrip[i][1]="0";
			DijTrip[i][2]="0";
			DijTrip[i][3]=null;
			DijTrip[i][4]=null;
			DijTrip[i][5]="10000";
			DijTrip[i][6]=null;
			//��ֵ����ĳ�����
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
		//-------��ʼ��
		int count=0;//�ǵ�++***
		int index=start;//���������±�***
		Time time=new Time();
		DijTrip[index][8]=String.valueOf(time.getHour());
		DijTrip[index][9]=String.valueOf(time.getMinute());
		DijTrip[index][5]="0";//day
		DijTrip[index][10]="true";//��������
		
		//System.out.println("-----------��ʼ�����----------");
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
			//�ҵ���������
			tempcon=tempcity.getHeadofCityConnection().getnextCon();
			//��ʼ���������
			while(tempcon!=null)//ѡ��·��
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
				//endcityΪ��·�ߵĵ������
				while(temptrain!=null)//ѡ���г�--�۸���С
				{
					System.out.println("��������:"+tempcity.getNameofCity()+" �������:"+endcity.getNameofCity());
					days=0;//����ʱ��
					//�����г�ѡ��--��index����tempcon.getEndIndex()
					tempday=DijTrip[tempcon.getEndIndex()][7];
					temphour=DijTrip[tempcon.getEndIndex()][8];
					tempmin=DijTrip[tempcon.getEndIndex()][9];
					System.out.println("�������ʱ��: "+DijTrip[index][8]+":"+DijTrip[index][9]+" �г�����ʱ��: "+temptrain.getStartHour()+":"+temptrain.getStartMin());
					if(IsAfter(Integer.valueOf(DijTrip[index][8]),Integer.valueOf(DijTrip[index][9]),temptrain.getStartHour(),temptrain.getStartMin()))//ǰһ���е������������л𳵷���֮������Ҫ�ȴ��ڶ�����ܸ������л�
					{
						days++;
					}
					System.out.println("���Ե��г����ڵ�"+days+"��󵽴�");
					//�Ƚϵ������ڵ�����
					//System.out.println(tempday+":"+temphour+":"+tempmin+"  "+(days+Integer.valueOf(DijTrip[index][7]))+":"+DijTrip[index][8]+":"+DijTrip[index][9]);
					System.out.println(tempday+":"+temphour+":"+tempmin+"  "+(days+temptrain.getEndDay()+Integer.valueOf(DijTrip[index][7]))+":"+temptrain.getEndHour()+":"+temptrain.getEndMin());				
					
					if(Double.valueOf(DijTrip[tempcon.getEndIndex()][5])>Double.valueOf(DijTrip[tempcon.getStartIndex()][5])+temptrain.getPrice())//��Ҫ�ı�
					{
						System.out.println("Change");
						DijTrip[tempcon.getEndIndex()][0]=String.valueOf(Integer.valueOf(DijTrip[index][7])+days);//�����ڼ���
						DijTrip[tempcon.getEndIndex()][1]=String.valueOf(temptrain.getStartHour());//����Сʱ
						DijTrip[tempcon.getEndIndex()][2]=String.valueOf(temptrain.getStartMin());//��������
						DijTrip[tempcon.getEndIndex()][3]=String.valueOf(tempcity.getNameofCity());//��������
						DijTrip[tempcon.getEndIndex()][4]=temptrain.getSequence();//����
						DijTrip[tempcon.getEndIndex()][5]=String.valueOf(Double.valueOf(DijTrip[tempcon.getStartIndex()][5])+temptrain.getPrice());//Ʊ��
						DijTrip[tempcon.getEndIndex()][7]=String.valueOf(Integer.valueOf(DijTrip[index][7])+days+temptrain.getEndDay());//����ڼ���
						DijTrip[tempcon.getEndIndex()][8]=String.valueOf(temptrain.getEndHour());
						DijTrip[tempcon.getEndIndex()][9]=String.valueOf(temptrain.getEndMin());
						System.out.println("��·:"+DijTrip[tempcon.getEndIndex()][3]+"��"+DijTrip[tempcon.getEndIndex()][6]+" ����ʱ�� "+DijTrip[tempcon.getEndIndex()][0]+":"+DijTrip[tempcon.getEndIndex()][1]+":"+DijTrip[tempcon.getEndIndex()][2]+" ����ʱ�� "+DijTrip[tempcon.getEndIndex()][7]+":"+DijTrip[tempcon.getEndIndex()][8]+":"+DijTrip[tempcon.getEndIndex()][9]);
					}
					temptrain=temptrain.getnextTrain();
				}
				tempcon=tempcon.getnextCon();
			}
			//��ʼ��ʱ����̵�����index DijTrip[index][10]="true";//��·�����
			int save=0;
			while(save==start||Boolean.valueOf(DijTrip[save][10])==true)//������start��ͬ,Ҳ�����Ǽ������
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
			
			System.out.println("-----------һ�����----------");
			ShowDij(DijTrip);
		}
		
		
		//��ʼ���ñ��
		
		int row=0;//��
		int temprow;
		int rowlen=0;//������
		int column=0;//��
		double SumPrice=0;
		int SumDuration=0;
		String content[][];
		//����һ��������
		City scity,ecity;
		ecity=getCityByIndex(end);
		while(true)
		{
			scity=getCityByName(DijTrip[ecity.getIndexofCity()][3]);
			if(scity==null)//û���ҵ�
			{
				JOptionPane.showMessageDialog(null,  "�޷�����ó���", "����!",JOptionPane.ERROR_MESSAGE);
				return;
			}
			rowlen++;
			ecity=scity;
			if(ecity.getIndexofCity()==start)
			{
				break;
			}
		}
		System.out.println("·������:"+rowlen);
		if(rowlen<8)//���
		{
			content=new String[8][6];
		}
		else
		{
			content=new String[rowlen][6];
		}
		/*
		 * DijTrip[i][0]=�����ĵڼ���
		 * DijTrip[i][1]=���������Сʱ
		 * DijTrip[i][2]=��������ķ���
		 * DijTrip[i][3]=�����ĳ�����
		 * DijTrip[i][4]=�������г�����
		 * DijTrip[i][5]=�������г�Ʊ��
		 * DijTrip[i][6]=����ĳ�����
		 * DijTrip[i][7]=����ʱΪ�ڼ���
		 * DijTrip[i][8]=���ﵱ���Сʱ
		 * DijTrip[i][9]=���ﵱ��ķ���
		 * DijTrip[i][10]=�Ƿ񱻼����
		 */
		ecity=getCityByIndex(end);
		SumDuration=Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])*24+Integer.valueOf(DijTrip[ecity.getIndexofCity()][8])-time.getHour();//��ȥ��ǰʱ��
		LabelTime.setText(String.valueOf(SumDuration));//��ʱ
		for(row=rowlen-1;row>=0;row--)//�������
		{
			content[row][0]="��"+(Integer.valueOf(DijTrip[ecity.getIndexofCity()][0])+1)+"�� "+DijTrip[ecity.getIndexofCity()][1]+":"+DijTrip[ecity.getIndexofCity()][2];//����ʱ��
			content[row][1]=DijTrip[ecity.getIndexofCity()][3];//��������
			content[row][2]=DijTrip[ecity.getIndexofCity()][4];//�����г�����
			content[row][3]=String.valueOf(getTrainBySequence(DijTrip[ecity.getIndexofCity()][4]).getPrice());//�г�Ʊ��
			SumPrice+=getTrainBySequence(DijTrip[ecity.getIndexofCity()][4]).getPrice();
			content[row][4]=DijTrip[ecity.getIndexofCity()][6];//�������
			content[row][5]="��"+(Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])+1)+"�� "+DijTrip[ecity.getIndexofCity()][8]+":"+DijTrip[ecity.getIndexofCity()][9];//����ʱ��
			scity=getCityByName(DijTrip[ecity.getIndexofCity()][3]);
			ecity=scity;
		}
		LabelPrice.setText(String.valueOf(SumPrice));
		if(rowlen<8)//û�������
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
	public void FindTripFew(int start,int end)//��ת��������
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
		int index=0;//���ӵ����±�
		int read=0;//����ȡ�����±�
		Array[index][0]=-1;
		Array[index][1]=start;
		boolean status=false;
		while(true)
		{
			tempcon=getCityByIndex(Array[read][1]).getHeadofCityConnection().getnextCon();
			while(tempcon!=null)
			{
				int i;
				for(i=0;i<=index;i++)//������������û��tempcon.getEndIndex()
				{
					if(Array[i][1]==tempcon.getEndIndex())
					{
						break;
					}
				}
				if(i<=index)//�иó���
				{
					tempcon=tempcon.getnextCon();
					continue;
				}
				//�����������ӽ��
				index++;
				Array[index][0]=read;//�����±�
				Array[index][1]=tempcon.getEndIndex();
				Array[index][2]=1;//���������
				if(Array[index][1]==end)//�ҵ�
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
			JOptionPane.showMessageDialog(null,  "�޷�����ó���", "����!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//����ҵ�������
		System.out.println("================");
		for(int i=0;i<=index;i++)
		{
			System.out.println("Array["+i+"][0]="+Array[i][0]+" Array["+i+"][1]="+Array[i][1]);
		}
		//��ʼ����
		//����һ���¼��ת����
		int rowlen=0;
		int tempindex=index;
		while(tempindex!=-1)
		{
			tempindex=Array[tempindex][0];
			rowlen++;
		}
		rowlen--;
		//��ʼ�����鸳ֵ
		System.out.println("һ����ת:"+rowlen+"��");
		
		//��ʼ���ñ��
		
		int row=0;//��
		String content[][];
		if(rowlen<8)//���
		{
			content=new String[8][6];
		}
		else
		{
			content=new String[rowlen][6];
		}
		/*
		 * DijTrip[i][3]=�����ĳ�����
		 * DijTrip[i][6]=����ĳ�����
		 */
		//ecity=getCityByIndex(end);
		//SumDuration=Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])*24+Integer.valueOf(DijTrip[ecity.getIndexofCity()][8])-time.getHour();//��ȥ��ǰʱ��
		LabelTime.setText("");//��ʱ
		tempindex=index;
		for(row=rowlen-1;row>=0;row--)//�������
		{
			content[row][0]="";//����ʱ��
			content[row][1]=getCityByIndex(Array[Array[tempindex][0]][1]).getNameofCity();//��������
			content[row][2]="";//�����г�����
			content[row][3]="";//�г�Ʊ��
			content[row][4]=getCityByIndex(Array[tempindex][1]).getNameofCity();//�������
			content[row][5]="";//����ʱ��
			tempindex=Array[tempindex][0];
		}
		LabelPrice.setText("");
		if(rowlen<8)//û�������
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
	public void FindTripFast(int start,int end) throws Exception//�������
	{
		System.out.println("startcity="+start+" endcity="+end);
		//Dijstra�㷨
		int CityAmount=HeadofCity.getAmountofCity();
		String DijTrip[][]=new String[CityAmount][11];
		/*
		 * DijTrip[i][0]=�����ĵڼ���
		 * DijTrip[i][1]=���������Сʱ
		 * DijTrip[i][2]=��������ķ���
		 * DijTrip[i][3]=�����ĳ�����
		 * DijTrip[i][4]=�������г�����
		 * DijTrip[i][5]=�������г�Ʊ��
		 * DijTrip[i][6]=����ĳ�����
		 * DijTrip[i][7]=����ʱΪ�ڼ���
		 * DijTrip[i][8]=���ﵱ���Сʱ
		 * DijTrip[i][9]=���ﵱ��ķ���
		 * DijTrip[i][10]=�Ƿ񱻼����
		 */
		//��ʼ��
		for(int i=0;i<CityAmount;i++)
		{
			DijTrip[i][0]="0";
			DijTrip[i][1]="0";
			DijTrip[i][2]="0";
			DijTrip[i][3]=null;
			DijTrip[i][4]=null;
			DijTrip[i][5]="0";
			DijTrip[i][6]=null;
			//��ֵ����ĳ�����
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
		//-------��ʼ��
		int count=0;//�ǵ�++***
		int index=start;//���������±�***
		Time time=new Time();
		
		DijTrip[index][7]="0";//day
		DijTrip[index][8]=String.valueOf(time.getHour());//hour
		DijTrip[index][9]=String.valueOf(time.getMinute());//min
		DijTrip[index][10]="true";//��������
		
		System.out.println("-----------��ʼ�����----------");
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
			//�ҵ���������
			tempcon=tempcity.getHeadofCityConnection().getnextCon();
			//��ʼ���������
			while(tempcon!=null)//ѡ��·��
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
				//endcityΪ��·�ߵĵ������
				while(temptrain!=null)//ѡ���г�--ʱ�����
				{
					System.out.println("��������:"+tempcity.getNameofCity()+" �������:"+endcity.getNameofCity());
					days=0;//����ʱ��
					//�����г�ѡ��--��index����tempcon.getEndIndex()
					tempday=DijTrip[tempcon.getEndIndex()][7];
					temphour=DijTrip[tempcon.getEndIndex()][8];
					tempmin=DijTrip[tempcon.getEndIndex()][9];
					System.out.println("�������ʱ��: "+DijTrip[index][8]+":"+DijTrip[index][9]+" �г�����ʱ��: "+temptrain.getStartHour()+":"+temptrain.getStartMin());
					if(IsAfter(Integer.valueOf(DijTrip[index][8]),Integer.valueOf(DijTrip[index][9]),temptrain.getStartHour(),temptrain.getStartMin()))//ǰһ���е������������л𳵷���֮������Ҫ�ȴ��ڶ�����ܸ������л�
					{
						days++;
					}
					System.out.println("���Ե��г����ڵ�"+days+"��󵽴�");
					//�Ƚϵ������ڵ�����
					//System.out.println(tempday+":"+temphour+":"+tempmin+"  "+(days+Integer.valueOf(DijTrip[index][7]))+":"+DijTrip[index][8]+":"+DijTrip[index][9]);
					System.out.println(tempday+":"+temphour+":"+tempmin+"  "+(days+temptrain.getEndDay()+Integer.valueOf(DijTrip[index][7]))+":"+temptrain.getEndHour()+":"+temptrain.getEndMin());
					if(IsAfter(Integer.valueOf(tempday),Integer.valueOf(temphour),Integer.valueOf(tempmin),(days+temptrain.getEndDay()+Integer.valueOf(DijTrip[index][7])),temptrain.getEndHour(),temptrain.getEndMin()))//��Ҫ�ı�
					{
						System.out.println("Change");
						DijTrip[tempcon.getEndIndex()][0]=String.valueOf(Integer.valueOf(DijTrip[index][7])+days);//�����ڼ���
						DijTrip[tempcon.getEndIndex()][1]=String.valueOf(temptrain.getStartHour());//����Сʱ
						DijTrip[tempcon.getEndIndex()][2]=String.valueOf(temptrain.getStartMin());//��������
						DijTrip[tempcon.getEndIndex()][3]=String.valueOf(tempcity.getNameofCity());//��������
						DijTrip[tempcon.getEndIndex()][4]=temptrain.getSequence();//����
						DijTrip[tempcon.getEndIndex()][5]=String.valueOf(temptrain.getPrice());//Ʊ��
						//DijTrip[tempcon.getEndIndex()][6]=String.valueOf(endcity.getNameofCity());//����ĳ�����
						DijTrip[tempcon.getEndIndex()][7]=String.valueOf(Integer.valueOf(DijTrip[index][7])+days+temptrain.getEndDay());//����ڼ���
						DijTrip[tempcon.getEndIndex()][8]=String.valueOf(temptrain.getEndHour());
						DijTrip[tempcon.getEndIndex()][9]=String.valueOf(temptrain.getEndMin());
						System.out.println("��·:"+DijTrip[tempcon.getEndIndex()][3]+"��"+DijTrip[tempcon.getEndIndex()][6]+" ����ʱ�� "+DijTrip[tempcon.getEndIndex()][0]+":"+DijTrip[tempcon.getEndIndex()][1]+":"+DijTrip[tempcon.getEndIndex()][2]+" ����ʱ�� "+DijTrip[tempcon.getEndIndex()][7]+":"+DijTrip[tempcon.getEndIndex()][8]+":"+DijTrip[tempcon.getEndIndex()][9]);
					}
					temptrain=temptrain.getnextTrain();
				}
				tempcon=tempcon.getnextCon();
			}
			//��ʼ��ʱ����̵�����index DijTrip[index][10]="true";//��·�����
			int fast=0;
			while(fast==start||Boolean.valueOf(DijTrip[fast][10])==true)//������start��ͬ,Ҳ�����Ǽ������
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
			
			System.out.println("-----------һ�����----------");
			ShowDij(DijTrip);
		}
		
		
		//��ʼ���ñ��
		
		int row=0;//��
		int temprow;
		int rowlen=0;//������
		int column=0;//��
		double SumPrice=0;
		int SumDuration=0;
		String content[][];
		//rowlen=HeadofCity.getHeadofCityConnection().getHeadofTrain().getAmount();
		//����һ��������
		City scity,ecity;
		ecity=getCityByIndex(end);
		while(true)
		{
			scity=getCityByName(DijTrip[ecity.getIndexofCity()][3]);
			if(scity==null)//û���ҵ�
			{
				JOptionPane.showMessageDialog(null,  "�޷�����ó���", "����!",JOptionPane.ERROR_MESSAGE);
				return;
			}
			rowlen++;
			ecity=scity;
			if(ecity.getIndexofCity()==start)
			{
				break;
			}
		}
		System.out.println("·������:"+rowlen);
		if(rowlen<8)//���
		{
			content=new String[8][6];
		}
		else
		{
			content=new String[rowlen][6];
		}
		/*
		 * DijTrip[i][0]=�����ĵڼ���
		 * DijTrip[i][1]=���������Сʱ
		 * DijTrip[i][2]=��������ķ���
		 * DijTrip[i][3]=�����ĳ�����
		 * DijTrip[i][4]=�������г�����
		 * DijTrip[i][5]=�������г�Ʊ��
		 * DijTrip[i][6]=����ĳ�����
		 * DijTrip[i][7]=����ʱΪ�ڼ���
		 * DijTrip[i][8]=���ﵱ���Сʱ
		 * DijTrip[i][9]=���ﵱ��ķ���
		 * DijTrip[i][10]=�Ƿ񱻼����
		 */
		ecity=getCityByIndex(end);
		SumDuration=Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])*24+Integer.valueOf(DijTrip[ecity.getIndexofCity()][8])-time.getHour();//��ȥ��ǰʱ��
		LabelTime.setText(String.valueOf(SumDuration));//��ʱ
		for(row=rowlen-1;row>=0;row--)//�������
		{
			content[row][0]="��"+(Integer.valueOf(DijTrip[ecity.getIndexofCity()][0])+1)+"�� "+DijTrip[ecity.getIndexofCity()][1]+":"+DijTrip[ecity.getIndexofCity()][2];//����ʱ��
			content[row][1]=DijTrip[ecity.getIndexofCity()][3];//��������
			content[row][2]=DijTrip[ecity.getIndexofCity()][4];//�����г�����
			content[row][3]=DijTrip[ecity.getIndexofCity()][5];//�г�Ʊ��
			SumPrice+=Double.valueOf(DijTrip[ecity.getIndexofCity()][5]);
			content[row][4]=DijTrip[ecity.getIndexofCity()][6];//�������
			content[row][5]="��"+(Integer.valueOf(DijTrip[ecity.getIndexofCity()][7])+1)+"�� "+DijTrip[ecity.getIndexofCity()][8]+":"+DijTrip[ecity.getIndexofCity()][9];//����ʱ��
			scity=getCityByName(DijTrip[ecity.getIndexofCity()][3]);
			ecity=scity;
		}
		LabelPrice.setText(String.valueOf(SumPrice));
		if(rowlen<8)//û�������
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
	
	public boolean IsAfter(int houra,int mina,int hourb,int minb)//�ж�Aʱ���Ƿ���B��
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
	public boolean IsAfter(int daya,int houra,int mina,int dayb,int hourb,int minb)//�ж�Aʱ���Ƿ���B��
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
	public int WaitMin(int houra,int mina,int hourb,int minb)//��ʱ��A��ʱ��B��Ҫ���ٷ���
	{
		if(IsAfter(houra,mina,hourb,minb))//��һ��
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
			 * DijTrip[i][0]=�����ĵڼ���
			 * DijTrip[i][1]=���������Сʱ
			 * DijTrip[i][2]=��������ķ���
			 * DijTrip[i][3]=�����ĳ�����
			 * DijTrip[i][4]=�������г�����
			 * DijTrip[i][5]=�������г�Ʊ��
			 * DijTrip[i][6]=����ĳ�����
			 * DijTrip[i][7]=����ʱΪ�ڼ���
			 * DijTrip[i][8]=���ﵱ���Сʱ
			 * DijTrip[i][9]=���ﵱ��ķ���
			 * DijTrip[i][10]=�Ƿ񱻼����
			 */
			System.out.println("==========");
			System.out.println("�����ĵڼ���:"+Dij[i][0]);
			System.out.println("���������Сʱ:"+Dij[i][1]);
			System.out.println("��������ķ���:"+Dij[i][2]);
			System.out.println("�����ĳ�����:"+Dij[i][3]);
			System.out.println("�������г�����:"+Dij[i][4]);
			System.out.println("�������г�Ʊ��:"+Dij[i][5]);
			System.out.println("����ĳ�����:"+Dij[i][6]);
			System.out.println("����ʱΪ�ڼ���:"+Dij[i][7]);
			System.out.println("���ﵱ���Сʱ:"+Dij[i][8]);
			System.out.println("���ﵱ��ķ���:"+Dij[i][9]);
			System.out.println("�Ƿ񱻼����:"+Dij[i][10]);
		}
	}
}
