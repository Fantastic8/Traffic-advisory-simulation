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
		//��������
		HeadofCity=Head;
		this.editcity=editcity;
		
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton ButtonOK = new JButton("\u786E\u5B9A");
		ButtonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name=TextCity.getText();
				System.out.println("ɾ��ǰ");
				ShowAllInfo(HeadofCity);
				//���
				if(Name.equals(""))
				{
					JOptionPane.showMessageDialog(null,  "��������Ҫɾ���ĳ�����", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				City city=HeadofCity.getnextCity();
				while(city!=null)
				{
					if(city.getNameofCity().equals(Name))
					{
						//�ҵ�
						break;
					}
					city=city.getnextCity();
				}
				if(city==null)
				{
					JOptionPane.showMessageDialog(null,  "�ó��в�����", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//������
				//��ʼ����ɾ��--ע��!������ͷ�巨���в����
				//��ɾ����--����
				City temp=HeadofCity.getnextCity();
				while(temp!=null)
				{
					CityConnection beforetempcon=temp.getHeadofCityConnection();
					CityConnection tempcon=temp.getHeadofCityConnection().getnextCon();
					while(tempcon!=null)
					{
						if(tempcon.getEndIndex()==city.getIndexofCity())//�ҵ�����ó��л𳵵��ڽӱ�-delete
						{
							beforetempcon.setnextCon(tempcon.getnextCon());
						}
						tempcon=tempcon.getnextCon();
						beforetempcon=beforetempcon.getnextCon();
					}
					temp=temp.getnextCity();
				}
				//��ɾ�����
				System.out.println("��ɾ�����");
				ShowAllInfo(HeadofCity);
				//��ʼɾ������
				int index=city.getIndexofCity();
				System.out.println("index="+index);
				City beforecity=HeadofCity;
				for(int i=beforecity.getAmountofCity()-1;i>index;i--)//�ƶ����ó���֮ǰ��һ�����
				{
					beforecity=beforecity.getnextCity();
				}
				beforecity.setnextCity(city.getnextCity());
				
				//��ʼ���±��
				City tempcity=HeadofCity.getnextCity();
				CityConnection tempconnection;
				int changeindex;
				if(city.getIndexofCity()==0)//���һ�����б�ɾ��
				{
					City tc=HeadofCity.getnextCity();
					while(tc.getnextCity()!=null)
					{
						tc=tc.getnextCity();
					}
					//tc��Ŀǰ���һ�����
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
						//�޸�
						changeindex=tempcity.getIndexofCity();
						System.out.println("ChangeIndex="+changeindex);
						tempcity.setIndexofCity(changeindex-1);//�����±�-1
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
				HeadofCity.setAmountofCity(HeadofCity.getAmountofCity()-1);//���и���-1
				
				editcity.UpdateFromchain();
				
				System.out.println("ɾ�����");
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
	public void ShowAllInfo(City HeadofCity)//���ȫ����Ϣ
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
				Train temptrain=tempcon.getHeadofTrain().getnextTrain();//��ͷ���
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
