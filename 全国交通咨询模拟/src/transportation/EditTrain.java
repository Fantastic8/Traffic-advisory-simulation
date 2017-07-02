package transportation;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditTrain extends JFrame {
	private JTable TableTrain;
	private TableModel tablemodel;
	private String[] TableTitle={"列车车次","出发城市","到达城市","发车时间","历时(分)","票价"};
	private City HeadofCity;
	private EditCity editcity;
	//--------必备
	private JPanel MainJPanel;//主面板--content Pane
	private JTextField TextSeq;
	public EditTrain(City head,EditCity editcity) throws Exception {
		this.editcity=editcity;
		HeadofCity=head;
		//===============================================================一系列初始化
		setBounds(410, 160, 600, 800);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//面板设置
		MainJPanel=(JPanel)getContentPane();
		//================================================================添加元件
		MainPane();
	}
	public void MainPane() throws Exception//添加组件
	{
//Table
		TableTrain = new JTable();
		TableTrain.setBorder(null);
		TableTrain.setFont(new Font("幼圆", Font.PLAIN, 18));
		TableTrain.setBounds(65, 150, 470, 485);
		TableTrain.setRowHeight(30);//设置表格高度
		JScrollPane scrollpane = new JScrollPane(TableTrain);
		scrollpane.setBounds(57, 150, 470, 485);
		MainJPanel.add(scrollpane);
		
		TextSeq = new JTextField();
		TextSeq.setBounds(196, 44, 86, 24);
		getContentPane().add(TextSeq);
		TextSeq.setColumns(10);
		
		JButton ButtonDeleteTrain = new JButton("\u5220\u9664\u5217\u8F66");
		ButtonDeleteTrain.setBounds(87, 691, 113, 27);
		getContentPane().add(ButtonDeleteTrain);
//修改事件		
		JButton ButtonEditTrain = new JButton("\u4FEE\u6539\u5217\u8F66");
		ButtonEditTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=TableTrain.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null,  "请选择列车", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				int startindex=-1;
				int endindex=-1;
				String startname=String.valueOf(TableTrain.getValueAt(row, 1));
				String endname=String.valueOf(TableTrain.getValueAt(row, 2));
				String sequence=String.valueOf(TableTrain.getValueAt(row, 0));
				City temp=HeadofCity.getnextCity();
				CityConnection tempcon;
				Train temptrain=null;
				Train aim=null;
				//查找train和startindex,endindex
				while(temp!=null)
				{
					if(startname.equals(temp.getNameofCity()))
					{
						startindex=temp.getIndexofCity();
						tempcon=temp.getHeadofCityConnection().getnextCon();
						while(tempcon!=null)
						{
							temptrain=tempcon.getHeadofTrain().getnextTrain();
							while(temptrain!=null)
							{
								if(temptrain.getSequence().equals(sequence))
								{
									endindex=tempcon.getEndIndex();
									aim=temptrain;//找到该列车
									break;
								}
								temptrain=temptrain.getnextTrain();
							}
							tempcon=tempcon.getnextCon();
						}
						break;
					}
					temp=temp.getnextCity();
				}
				
				EditTrainInfo traininfo=new EditTrainInfo(editcity,HeadofCity,EditTrain.this,startindex,endindex,aim);
				traininfo.setVisible(true);
				EditTrain.this.setEnabled(false);
			}
		});
		ButtonEditTrain.setBounds(337, 691, 113, 27);
		getContentPane().add(ButtonEditTrain);
		
		JLabel LabelInfo = new JLabel("\u5217\u8F66\u67E5\u8BE2");
		LabelInfo.setBounds(14, 13, 72, 18);
		getContentPane().add(LabelInfo);
		
		JLabel label = new JLabel("\u5217\u8F66\u8F66\u6B21");
		label.setBounds(109, 47, 72, 18);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u51FA\u53D1\u57CE\u5E02");
		label_1.setBounds(109, 88, 72, 18);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5230\u8FBE\u57CE\u5E02");
		label_2.setBounds(109, 119, 72, 18);
		getContentPane().add(label_2);
		
		JComboBox ComboBoxStartCity = new JComboBox();
		ComboBoxStartCity.setBounds(196, 81, 86, 24);
		getContentPane().add(ComboBoxStartCity);
		
		JComboBox ComboBoxEndCity = new JComboBox();
		ComboBoxEndCity.setBounds(196, 116, 86, 24);
		getContentPane().add(ComboBoxEndCity);
//删除事件		
		ButtonDeleteTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=TableTrain.getSelectedRow();
				String startname;
				String endname;
				String sequence;
				int startindex=-1;
				int endindex=-1;
				City temp=HeadofCity.getnextCity();
				CityConnection tempcon;
				Train temptrain;
				Train btemptrain;
				if(row==-1)
				{
					JOptionPane.showMessageDialog(null,  "请选择列车", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Object[] options = { "确定", "取消" };   
				int response=JOptionPane.showOptionDialog(null, "确定要删除此列车？删除后不能撤销!", "警告",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
				if(response==1)
				{
					return;
				}
				sequence=String.valueOf(TableTrain.getValueAt(row,0));
				startname=String.valueOf(TableTrain.getValueAt(row,1));
				endname=String.valueOf(TableTrain.getValueAt(row,2));
				while(temp!=null)
				{
					if(startname.equals(temp.getNameofCity()))
					{
						startindex=temp.getIndexofCity();
					}
					if(endname.equals(temp.getNameofCity()))
					{
						endindex=temp.getIndexofCity();
					}
					temp=temp.getnextCity();
				}
				//获取到了两城市下标
				//开始删除
				temp=HeadofCity.getnextCity();
				while(temp!=null)
				{
					if(startname.equals(temp.getNameofCity()))//匹配出发城市
					{
						tempcon=temp.getHeadofCityConnection().getnextCon();
						while(tempcon!=null)
						{
							if(endindex==tempcon.getEndIndex())//匹配到达城市
							{
								btemptrain=tempcon.getHeadofTrain();
								temptrain=tempcon.getHeadofTrain().getnextTrain();
								while(temptrain!=null)
								{
									if(sequence.equals(temptrain.getSequence()))//匹配列车车次
									{
										btemptrain.setnextTrain(temptrain.getnextTrain());//删除
										if(tempcon.getHeadofTrain().getnextTrain()==null)//此邻接表上没有火车--删除该邻接结点
										{
											CityConnection con=temp.getHeadofCityConnection();//找到该邻接结点的前一结点
											while(con!=null)
											{
												if(con.getnextCon()==tempcon)
												{
													break;
												}
												con=con.getnextCon();
											}
											con.setnextCon(tempcon.getnextCon());
										}
										break;
									}
									temptrain=temptrain.getnextTrain();
									btemptrain=btemptrain.getnextTrain();
								}
								break;
							}
							tempcon=tempcon.getnextCon();
						}
						break;
					}
					temp=temp.getnextCity();
				}
				try {
					UpdateTable();
					editcity.UpdateGraphics();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		//增加Text动态监听事件
		Document doc=TextSeq.getDocument();
		doc.addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					String StartCityName=null;
					String EndCityName=null;
					if(ComboBoxStartCity.getSelectedItem()!=null)
					{
						StartCityName=ComboBoxStartCity.getSelectedItem().toString();
					}
					if(ComboBoxEndCity.getSelectedItem()!=null)
					{
						EndCityName=ComboBoxEndCity.getSelectedItem().toString();
					}
					UpdateFind(TextSeq.getText(),StartCityName,EndCityName);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					String StartCityName=null;
					String EndCityName=null;
					if(ComboBoxStartCity.getSelectedItem()!=null)
					{
						StartCityName=ComboBoxStartCity.getSelectedItem().toString();
					}
					if(ComboBoxEndCity.getSelectedItem()!=null)
					{
						EndCityName=ComboBoxEndCity.getSelectedItem().toString();
					}
					UpdateFind(TextSeq.getText(),StartCityName,EndCityName);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					String StartCityName=null;
					String EndCityName=null;
					if(ComboBoxStartCity.getSelectedItem()!=null)
					{
						StartCityName=ComboBoxStartCity.getSelectedItem().toString();
					}
					if(ComboBoxEndCity.getSelectedItem()!=null)
					{
						EndCityName=ComboBoxEndCity.getSelectedItem().toString();
					}
					UpdateFind(TextSeq.getText(),StartCityName,EndCityName);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		String[] CityName=new String[HeadofCity.getAmountofCity()+1];
		City p=HeadofCity.getnextCity();
		CityName[0]="全部";
		for(int i=1;p!=null;i++)
		{
			CityName[i]=p.getNameofCity();
			p=p.getnextCity();
		}
		ComboBoxModel startcity=new DefaultComboBoxModel(CityName);
		ComboBoxModel endcity=new DefaultComboBoxModel(CityName);
		ComboBoxStartCity.setModel(startcity);
		ComboBoxStartCity.setSelectedIndex(0);
		ComboBoxEndCity.setModel(endcity);
		ComboBoxEndCity.setSelectedIndex(0);
		
		ComboBoxStartCity.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)//只执行一次
				{
					String StartCityName=null;
					String EndCityName=null;
					if(ComboBoxStartCity.getSelectedItem()!=null)
					{
						StartCityName=ComboBoxStartCity.getSelectedItem().toString();
					}
					if(ComboBoxEndCity.getSelectedItem()!=null)
					{
						EndCityName=ComboBoxEndCity.getSelectedItem().toString();
					}
					try {
						UpdateFind(TextSeq.getText(),StartCityName,EndCityName);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}});
		ComboBoxEndCity.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)//只执行一次
				{
					String StartCityName=null;
					String EndCityName=null;
					if(ComboBoxStartCity.getSelectedItem()!=null)
					{
						StartCityName=ComboBoxStartCity.getSelectedItem().toString();
					}
					if(ComboBoxEndCity.getSelectedItem()!=null)
					{
						EndCityName=ComboBoxEndCity.getSelectedItem().toString();
					}
					try {
						UpdateFind(TextSeq.getText(),StartCityName,EndCityName);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}});
		
		
		UpdateTable();
	}
	public void UpdateTable() throws Exception
	{
		int row=0;//行
		int rowlen=0;//行数量
		int column=0;//列
		String content[][];
		//rowlen=HeadofCity.getHeadofCityConnection().getHeadofTrain().getAmount();
		//遍历一遍获得行数
		City cityrow=HeadofCity.getnextCity();
		CityConnection conrow;
		Train trainrow;
		while(cityrow!=null)
		{
			conrow=cityrow.getHeadofCityConnection().getnextCon();
			while(conrow!=null)
			{
				trainrow=conrow.getHeadofTrain().getnextTrain();
				while(trainrow!=null)
				{
					rowlen++;
					trainrow=trainrow.getnextTrain();
				}
				conrow=conrow.getnextCon();
			}
			cityrow=cityrow.getnextCity();
		}
		System.out.println("列车数量:"+rowlen);
		if(rowlen<16)//填充
		{
			content=new String[16][6];
		}
		else
		{
			content=new String[rowlen][6];
		}
		//开始赋值
		City findname;
		int findstart,findend;
		City temp=HeadofCity.getnextCity();
		CityConnection tempcon;
		Train temptrain;
		while(temp!=null)
		{
			tempcon=temp.getHeadofCityConnection().getnextCon();
			while(tempcon!=null)
			{
				temptrain=tempcon.getHeadofTrain().getnextTrain();
				while(temptrain!=null)
				{
					//"列车车次","出发城市","到达城市","发车时间","历时(分)","票价"
					content[row][0]=temptrain.getSequence();
					content[row][3]=String.valueOf(temptrain.getStartHour())+":"+String.valueOf(temptrain.getStartMin());
					content[row][4]=String.valueOf(temptrain.getDuration());
					content[row][5]=String.valueOf(temptrain.getPrice());
					
					findname=HeadofCity.getnextCity();
					findstart=tempcon.getStartIndex();
					findend=tempcon.getEndIndex();
					while(findname!=null)
					{
						if(findstart==findname.getIndexofCity())
						{
							content[row][1]=findname.getNameofCity();
						}
						if(findend==findname.getIndexofCity())
						{
							content[row][2]=findname.getNameofCity();
						}
						findname=findname.getnextCity();
					}
					row++;
					temptrain=temptrain.getnextTrain();
				}
				tempcon=tempcon.getnextCon();
			}
			temp=temp.getnextCity();
		}
		if(row<16)//没有填充满
		{
			for(;row<16;row++)
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
		TableTrain.setModel(tablemodel);
	}

	public void UpdateFind(String sequence,String startname,String endname) throws Exception
	{
		int row=0;//行
		int rowlen=0;//行数量
		int column=0;//列
		int startindex=-1;
		int endindex=-1;
		String content[][];
		City find=HeadofCity.getnextCity();
		while(find!=null)
		{
			if(find.getNameofCity().equals(startname))
			{
				startindex=find.getIndexofCity();
			}
			if(find.getNameofCity().equals(endname))
			{
				endindex=find.getIndexofCity();
			}
			find=find.getnextCity();
		}
		//找到出发城市和到达城市的下标
		System.out.println("出发城市下标:"+startindex+" 到达城市下标"+endindex);
		//遍历一遍获得行数
		City cityrow=HeadofCity.getnextCity();
		CityConnection conrow;
		Train trainrow;
		
		while(cityrow!=null)
		{
			conrow=cityrow.getHeadofCityConnection().getnextCon();
			while(conrow!=null)
			{
				if((startindex==-1&&endindex==-1)||(conrow.getStartIndex()==startindex&&endindex==-1)||(startindex==-1&&conrow.getEndIndex()==endindex)||(startindex==conrow.getStartIndex()&&endindex==conrow.getEndIndex()))
				{
					trainrow=conrow.getHeadofTrain().getnextTrain();
					while(trainrow!=null)
					{
						if(trainrow.getSequence().contains(sequence))
						{
							rowlen++;
						}
						trainrow=trainrow.getnextTrain();
					}
				}
				conrow=conrow.getnextCon();
			}
			cityrow=cityrow.getnextCity();
		}
		System.out.println("Find列车数量:"+rowlen);
		if(rowlen<16)//填充
		{
			content=new String[16][6];
		}
		else
		{
			content=new String[rowlen][6];
		}
		
		
		//开始赋值
		City findname;
		int findstart,findend;
		City temp=HeadofCity.getnextCity();
		CityConnection tempcon;
		Train temptrain;
		while(temp!=null)
		{
			tempcon=temp.getHeadofCityConnection().getnextCon();
			while(tempcon!=null)
			{
				if((startindex==-1&&endindex==-1)||(tempcon.getStartIndex()==startindex&&endindex==-1)||(startindex==-1&&tempcon.getEndIndex()==endindex)||(startindex==tempcon.getStartIndex()&&endindex==tempcon.getEndIndex()))
				{
					temptrain=tempcon.getHeadofTrain().getnextTrain();
					while(temptrain!=null)
					{
						if(temptrain.getSequence().contains(sequence))
						{
							//"列车车次","出发城市","到达城市","发车时间","历时(分)","票价"
							content[row][0]=temptrain.getSequence();
							content[row][3]=String.valueOf(temptrain.getStartHour())+":"+String.valueOf(temptrain.getStartMin());
							content[row][4]=String.valueOf(temptrain.getDuration());
							content[row][5]=String.valueOf(temptrain.getPrice());
							
							findname=HeadofCity.getnextCity();
							findstart=tempcon.getStartIndex();
							findend=tempcon.getEndIndex();
							while(findname!=null)
							{
								if(findstart==findname.getIndexofCity())
								{
									content[row][1]=findname.getNameofCity();
								}
								if(findend==findname.getIndexofCity())
								{
									content[row][2]=findname.getNameofCity();
								}
								findname=findname.getnextCity();
							}
							row++;
						}
						temptrain=temptrain.getnextTrain();
					}
				}
				tempcon=tempcon.getnextCon();
			}
			temp=temp.getnextCity();
		}
		if(row<16)//没有填充满
		{
			for(;row<16;row++)
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
		TableTrain.setModel(tablemodel);
	}
}
