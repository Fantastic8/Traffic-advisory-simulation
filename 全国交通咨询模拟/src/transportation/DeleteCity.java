package transportation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class DeleteCity extends JFrame {
	private JTextField TextCity;
	private EditCity editcity;
	private City HeadofCity;
	public DeleteCity(EditCity editcity,City Head) {
		//数据配置
		HeadofCity=Head;
		this.editcity=editcity;
		
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton ButtonOK = new JButton("\u786E\u5B9A");
		ButtonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name=TextCity.getText();
				System.out.println("删除前");
				ShowAllInfo(HeadofCity);
				//检查
				if(Name.equals(""))
				{
					JOptionPane.showMessageDialog(null,  "请输入想要删除的城市名", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				City city=HeadofCity.getnextCity();
				while(city!=null)
				{
					if(city.getNameofCity().equals(Name))
					{
						//找到
						break;
					}
					city=city.getnextCity();
				}
				if(city==null)
				{
					JOptionPane.showMessageDialog(null,  "该城市不存在", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//检查完毕
				//开始进行删除--注意!这里是头插法进行插入的
				//先删除火车--遍历
				City temp=HeadofCity.getnextCity();
				while(temp!=null)
				{
					CityConnection beforetempcon=temp.getHeadofCityConnection();
					CityConnection tempcon=temp.getHeadofCityConnection().getnextCon();
					while(tempcon!=null)
					{
						if(tempcon.getEndIndex()==city.getIndexofCity())//找到到达该城市火车的邻接表-delete
						{
							beforetempcon.setnextCon(tempcon.getnextCon());
						}
						tempcon=tempcon.getnextCon();
						beforetempcon=beforetempcon.getnextCon();
					}
					temp=temp.getnextCity();
				}
				//火车删除完毕
				System.out.println("火车删除完毕");
				ShowAllInfo(HeadofCity);
				//开始删除城市
				int index=city.getIndexofCity();
				System.out.println("index="+index);
				City beforecity=HeadofCity;
				for(int i=beforecity.getAmountofCity()-1;i>index;i--)//移动到该城市之前的一个结点
				{
					beforecity=beforecity.getnextCity();
				}
				beforecity.setnextCity(city.getnextCity());
				
				//开始重新编号
				City tempcity=HeadofCity.getnextCity();
				CityConnection tempconnection;
				int changeindex;
				if(city.getIndexofCity()==0)//最后一个城市被删除
				{
					City tc=HeadofCity.getnextCity();
					while(tc.getnextCity()!=null)
					{
						tc=tc.getnextCity();
					}
					//tc是目前最后一个结点
					tc.setIndexofCity(0);
					CityConnection contc;
					tc=HeadofCity.getnextCity();
					while(tc!=null)
					{
						contc=tc.getHeadofCityConnection().getnextCon();
						while(contc!=null)
						{
							if(contc.getEndIndex()==1)
							{
								contc.setEndIndex(0);
							}
							contc=contc.getnextCon();
						}
						tc=tc.getnextCity();
					}
				}
				while(tempcity.getnextCity()!=null)
				{
					if(tempcity.getIndexofCity()-tempcity.getnextCity().getIndexofCity()==2)
					{
						//修改
						changeindex=tempcity.getIndexofCity();
						System.out.println("ChangeIndex="+changeindex);
						tempcity.setIndexofCity(changeindex-1);//城市下标-1
						City all=HeadofCity.getnextCity();
						CityConnection allcon;
						while(all!=null)
						{
							allcon=all.getHeadofCityConnection().getnextCon();
							while(allcon!=null)
							{
								if(allcon.getEndIndex()==changeindex)
								{
									allcon.setEndIndex(changeindex-1);
								}
								allcon=allcon.getnextCon();
							}
							all=all.getnextCity();
						}
						tempcity=HeadofCity.getnextCity();
						continue;
					}
					tempcity=tempcity.getnextCity();
				}
				HeadofCity.setAmountofCity(HeadofCity.getAmountofCity()-1);//城市个数-1
				
				editcity.UpdateFromchain();
				
				System.out.println("删除完毕");
				ShowAllInfo(HeadofCity);
				editcity.setEnabled(true);
				DeleteCity.this.dispose();
			}
		});
		ButtonOK.setBounds(108, 213, 63, 27);
		getContentPane().add(ButtonOK);
		
		JButton ButtonCancel = new JButton("\u53D6\u6D88");
		ButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editcity.setEnabled(true);
				DeleteCity.this.dispose();
			}
		});
		ButtonCancel.setBounds(257, 213, 63, 27);
		getContentPane().add(ButtonCancel);
		
		TextCity = new JTextField();
		TextCity.setBounds(242, 86, 86, 24);
		getContentPane().add(TextCity);
		TextCity.setColumns(10);
		
		JLabel LabelInfo = new JLabel("\u8BF7\u8F93\u5165\u8981\u5220\u9664\u7684\u57CE\u5E02\u540D");
		LabelInfo.setBounds(33, 89, 167, 18);
		getContentPane().add(LabelInfo);

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
