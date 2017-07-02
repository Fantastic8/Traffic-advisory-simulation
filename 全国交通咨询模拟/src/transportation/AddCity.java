package transportation;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCity extends JFrame {
	private JTextField TextNameofCity;
	private City HeadofCity;//城市头结点
	private EditCity editcity;
	public AddCity(EditCity editcity,City head) {
//数据配置
		HeadofCity=head;
		this.editcity=editcity;
		//System.out.println("TailofCity3 index="+TailofCity.getIndexofCity()+" name="+TailofCity.getNameofCity());
//配置完毕
		setBounds(100, 100, 450, 247);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel LabelAddCity = new JLabel("\u6DFB\u52A0\u57CE\u5E02");
		LabelAddCity.setBounds(174, 34, 72, 18);
		getContentPane().add(LabelAddCity);
		
		TextNameofCity = new JTextField();
		TextNameofCity.setBounds(160, 82, 86, 24);
		getContentPane().add(TextNameofCity);
		TextNameofCity.setColumns(10);
		
		JLabel LabelNameofCity = new JLabel("\u57CE\u5E02\u540D");
		LabelNameofCity.setBounds(59, 85, 72, 18);
		getContentPane().add(LabelNameofCity);
		
		JButton ButtonOK = new JButton("\u786E\u5B9A");
		ButtonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//数据操作=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=确定添加
				String NameofCity=TextNameofCity.getText();//城市名
				//检查
				if(NameofCity.equals(""))
				{
					JOptionPane.showMessageDialog(null,  "请输入城市名", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Illegalchar(NameofCity))//是否包含非法字符
				{
					JOptionPane.showMessageDialog(null,  "城市名包含非法字符", "错误!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//是否重复
				City temp=HeadofCity.getnextCity();
				while(temp!=null)
				{
					if(temp.getNameofCity().equals(NameofCity))
					{
						JOptionPane.showMessageDialog(null,  "该城市已存在", "错误!",JOptionPane.ERROR_MESSAGE);
						return;
					}
					temp=temp.getnextCity();
				}
				//检查完毕
				City city=new City();//新建一个城市
				city.setNameofCity(NameofCity);//城市名
				
				Point pos=editcity.addACity(NameofCity,city);//图形
				
				city.setpos(pos);
				
				//头插法
				city.setnextCity(HeadofCity.getnextCity());
				HeadofCity.setnextCity(city);
				
				ShowAllInfo();//输出全部信息
				
				editcity.setEnabled(true);
				AddCity.this.dispose();
			}
		});
		ButtonOK.setBounds(82, 144, 63, 27);
		getContentPane().add(ButtonOK);
		
		JButton ButtonCancel = new JButton("\u53D6\u6D88");
		ButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editcity.setEnabled(true);
				AddCity.this.dispose();
			}
		});
		ButtonCancel.setBounds(263, 144, 63, 27);
		getContentPane().add(ButtonCancel);

	}
	public void ShowAllInfo()//输出全部信息
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
	public boolean Illegalchar(String s)//判断是否包含非法字符
	{
		if(s.contains(".")||s.contains(",")||s.contains("'")||s.contains("\"")||s.contains("\\")||s.contains("|")||s.contains("!")||s.contains("~")||s.contains(" ")||s.contains("@")||s.contains("#")||s.contains("$")||s.contains("%")||s.contains("^")||s.contains("&")||s.contains("*")||s.contains("(")||s.contains(")")||s.contains("-")||s.contains("_")||s.contains("+")||s.contains("=")||s.contains("{")||s.contains("}")||s.contains("[")||s.contains("]")||s.contains("'")||s.contains("`"))
		{
			return true;
		}
		return false;
	}
}
