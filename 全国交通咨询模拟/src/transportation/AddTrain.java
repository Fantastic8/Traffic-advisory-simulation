package transportation;

import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

public class AddTrain extends JFrame {
	private JTextField TextSequence;
	private JTextField TextStartTimeHour;
	private JTextField TextDurationHour;
	private City HeadofCity;
	private JTextField TextPrice;
	private JTextField TextStartTimeMin;
	private JTextField TextDurationMin;
	public AddTrain(EditCity edit,City head) {
		//��������
		HeadofCity=head;
		
		setBounds(100, 100, 450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel LabelAddTrain = new JLabel("\u6DFB\u52A0\u5217\u8F66");
		LabelAddTrain.setBounds(177, 13, 72, 18);
		getContentPane().add(LabelAddTrain);
		
		JLabel LabelSequence = new JLabel("\u5217\u8F66\u8F66\u6B21");
		LabelSequence.setBounds(69, 52, 72, 18);
		getContentPane().add(LabelSequence);
		
		JLabel LabelStartTime = new JLabel("\u53D1\u8F66\u65F6\u95F4");
		LabelStartTime.setBounds(69, 187, 72, 18);
		getContentPane().add(LabelStartTime);
		
		JLabel LabelDuration = new JLabel("\u5386\u65F6");
		LabelDuration.setBounds(79, 218, 36, 18);
		getContentPane().add(LabelDuration);
		
		JLabel LabelStartCity = new JLabel("\u8D77\u59CB\u57CE\u5E02");
		LabelStartCity.setBounds(69, 102, 72, 18);
		getContentPane().add(LabelStartCity);
		
		JLabel LabelEndCity = new JLabel("\u5230\u8FBE\u57CE\u5E02");
		LabelEndCity.setBounds(69, 133, 72, 18);
		getContentPane().add(LabelEndCity);
		
		TextSequence = new JTextField();
		TextSequence.setBounds(163, 49, 86, 24);
		getContentPane().add(TextSequence);
		TextSequence.setColumns(10);
		
		JButton ButtonCancel = new JButton("\u53D6\u6D88");
		ButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit.setEnabled(true);
				AddTrain.this.dispose();
			}
		});
		ButtonCancel.setBounds(262, 300, 63, 27);
		getContentPane().add(ButtonCancel);
		
		JComboBox ComboBoxStartCity = new JComboBox();
		ComboBoxStartCity.setBounds(163, 99, 86, 24);
		getContentPane().add(ComboBoxStartCity);
		
		JComboBox ComboBoxEndCity = new JComboBox();
		ComboBoxEndCity.setBounds(163, 130, 86, 24);
		getContentPane().add(ComboBoxEndCity);
		
		TextStartTimeHour = new JTextField();
		TextStartTimeHour.setBounds(163, 184, 36, 24);
		getContentPane().add(TextStartTimeHour);
		TextStartTimeHour.setColumns(10);
		
		TextDurationHour = new JTextField();
		TextDurationHour.setBounds(163, 215, 36, 24);
		getContentPane().add(TextDurationHour);
		TextDurationHour.setColumns(10);
		
		String[] StartCityName=new String[HeadofCity.getAmountofCity()];
		City p=HeadofCity.getnextCity();
		for(int i=0;p!=null;i++)
		{
			StartCityName[i]=p.getNameofCity();
			p=p.getnextCity();
		}
		ComboBoxModel startcity=new DefaultComboBoxModel(StartCityName);
		ComboBoxStartCity.setModel(startcity);
		ComboBoxStartCity.setSelectedIndex(-1);
		
		ComboBoxStartCity.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)//ִֻ��һ��
				{
					String[] EndCityName=new String[HeadofCity.getAmountofCity()-1];
					City p=HeadofCity.getnextCity();
					for(int i=0;p!=null;p=p.getnextCity())
					{
						if(p.getNameofCity().equals(ComboBoxStartCity.getSelectedItem().toString()))//��ͬ����
						{
							continue;
						}
						EndCityName[i]=p.getNameofCity();
						i++;
					}
					ComboBoxModel endcity=new DefaultComboBoxModel(EndCityName);
					ComboBoxEndCity.setModel(endcity);
				}
			}});
		
		JButton ButtonOK = new JButton("\u786E\u5B9A");
		ButtonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Sequence;
				String startcity;
				String endcity;
				double Price;
				int StartHour;
				int StartMin;
				int DurationHour;
				int DurationMin;
			//���
				//��鳵��
				if(TextSequence.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,  "�������г�����", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Illegalchar(TextSequence.getText()))//�Ƿ�����
				{
					JOptionPane.showMessageDialog(null,  "�г����ΰ����Ƿ��ַ�", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//�����ظ�
				City temp=HeadofCity.getnextCity();
				while(temp!=null)
				{
					CityConnection tempcon=temp.getHeadofCityConnection().getnextCon();
					while(tempcon!=null)
					{
						Train temptrain=tempcon.getHeadofTrain().getnextTrain();
						while(temptrain!=null)
						{
							if(temptrain.getSequence().equals(TextSequence.getText()))
							{
								JOptionPane.showMessageDialog(null,  "�ó����Ѵ���", "����!",JOptionPane.ERROR_MESSAGE);
								return;
							}
							temptrain=temptrain.getnextTrain();
						}
						tempcon=tempcon.getnextCon();
					}
					temp=temp.getnextCity();
				}
				//��鷢�����к͵������
				if(ComboBoxStartCity.getSelectedIndex()<0)
				{
					JOptionPane.showMessageDialog(null,  "��ѡ�񷢳�����", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(ComboBoxEndCity.getSelectedIndex()<0)
				{
					JOptionPane.showMessageDialog(null,  "��ѡ�񵽴����", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//��鷢��ʱ�����ʱ
				try {
					StartHour=Integer.parseInt(TextStartTimeHour.getText());
					StartMin=Integer.parseInt(TextStartTimeMin.getText());
					DurationHour=Integer.parseInt(TextDurationHour.getText());
					DurationMin=Integer.parseInt(TextDurationMin.getText());
					if(StartHour<0||StartHour>23||StartMin<0||StartMin>59||DurationHour<0||DurationMin<0||DurationMin>59)//ʱ�����
					{
						JOptionPane.showMessageDialog(null,  "ʱ�����", "����!",JOptionPane.ERROR_MESSAGE);
						return;
					}
					Double.parseDouble(TextPrice.getText());
				}catch(NullPointerException e1)
				{
					JOptionPane.showMessageDialog(null,  "Ʊ�۴���", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}catch(NumberFormatException e2)
				{
					JOptionPane.showMessageDialog(null,  "Ʊ�ۻ�ʱ�����", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			//������
				
			//��ȡ��Ϣ
				Sequence=TextSequence.getText();//��ȡ�г�����
				startcity=ComboBoxStartCity.getSelectedItem().toString();//��ȡ��������
				endcity=ComboBoxEndCity.getSelectedItem().toString();//��ȡ�������
				Price=Double.parseDouble(TextPrice.getText());//��ȡƱ��
				
			//���ݲ���
				//�ҳ������к͵������
				City start=HeadofCity.getnextCity();//����ͷ���
				while(start!=null)
				{
					if(start.getNameofCity().equals(startcity))//�ҵ���������
					{
						System.out.println("�ҵ���������index"+start.getIndexofCity());
						break;
					}
					start=start.getnextCity();
				}
				City end=HeadofCity.getnextCity();//����ͷ���
				while(end!=null)
				{
					if(end.getNameofCity().equals(endcity))//�ҵ��������
					{
						System.out.println("�ҵ��������index="+end.getIndexofCity());
						break;
					}
					end=end.getnextCity();
				}
				//�ҵ���������
				Train train=new Train();//�½���
				train.setSequence(Sequence);//�����г�����
				train.setPrice(Price);//����Ʊ��
				train.setStartTime(StartHour, StartMin);//���÷���ʱ��
				train.setDuration(DurationHour, DurationMin);//������ʱ
				if(start.addTrain(train, end.getIndexofCity())==false)//������ӵ��ó��е��ڽ�����Ļ�������
				{
					edit.addALine(start,end);
				}
				
				ShowAllInfo();//��ʾȫ����Ϣ
				
				edit.setEnabled(true);
				AddTrain.this.dispose();
			}
		});
		ButtonOK.setBounds(114, 300, 63, 27);
		getContentPane().add(ButtonOK);
		
		JLabel LabelPrice = new JLabel("\u7968\u4EF7");
		LabelPrice.setBounds(79, 255, 36, 18);
		getContentPane().add(LabelPrice);
		
		TextPrice = new JTextField();
		TextPrice.setBounds(163, 252, 86, 24);
		getContentPane().add(TextPrice);
		TextPrice.setColumns(10);
		
		JLabel LabelColon1 = new JLabel(":");
		LabelColon1.setBounds(207, 187, 14, 18);
		getContentPane().add(LabelColon1);
		
		JLabel LabelColon2 = new JLabel(":");
		LabelColon2.setBounds(207, 218, 14, 18);
		getContentPane().add(LabelColon2);
		
		TextStartTimeMin = new JTextField();
		TextStartTimeMin.setBounds(224, 184, 36, 24);
		getContentPane().add(TextStartTimeMin);
		TextStartTimeMin.setColumns(10);
		
		TextDurationMin = new JTextField();
		TextDurationMin.setColumns(10);
		TextDurationMin.setBounds(224, 215, 36, 24);
		getContentPane().add(TextDurationMin);
	}
	public void ShowAllInfo()//���ȫ����Ϣ
	{
		System.out.println("=-=-=-=-=-");
		City temp=HeadofCity.getnextCity();
		while(temp!=null)
		{
			System.out.println("CityName="+temp.getNameofCity()+" Index="+temp.getIndexofCity());
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
	public boolean Illegalchar(String s)//�ж��Ƿ�����Ƿ��ַ�
	{
		if(s.contains(".")||s.contains(",")||s.contains("'")||s.contains("\"")||s.contains("\\")||s.contains("|")||s.contains("!")||s.contains("~")||s.contains(" ")||s.contains("@")||s.contains("#")||s.contains("$")||s.contains("%")||s.contains("^")||s.contains("&")||s.contains("*")||s.contains("(")||s.contains(")")||s.contains("-")||s.contains("_")||s.contains("+")||s.contains("=")||s.contains("{")||s.contains("}")||s.contains("[")||s.contains("]")||s.contains("'")||s.contains("`"))
		{
			return true;
		}
		return false;
	}
}
