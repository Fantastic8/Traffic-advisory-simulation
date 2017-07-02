package transportation;

import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EditTrainInfo extends JFrame {
	private City HeadofCity;
	private EditTrain edittrain;
	private int startindex;
	private int endindex;
	private String startname=null;
	private String endname=null;
	private Train train;
	private JTextField TextSequence;
	private JTextField TextStartHour;
	private JTextField TextStartMin;
	private JTextField TextDurationHour;
	private JTextField TextDurationMin;
	private JTextField TextPrice;
	private JComboBox ComboBoxStartCity;
	private JComboBox ComboBoxEndCity;
	public EditTrainInfo(EditCity editcity,City head,EditTrain edittrain,int startindex,int endindex,Train train) {
		this.edittrain=edittrain;
		HeadofCity=head;
		this.startindex=startindex;
		this.endindex=endindex;
		this.train=train;
		setBounds(100, 100, 553, 468);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
//����		
		JButton ButtonSave = new JButton("\u4FDD\u5B58");
		ButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sequence=TextSequence.getText();
				String startcity=ComboBoxStartCity.getSelectedItem().toString();
				String endcity=ComboBoxEndCity.getSelectedItem().toString();
				int starthour;
				int startmin;
				int durationhour;
				int durationmin;
				double price;
				//���
				//��鳵��
				if(sequence.equals(""))
				{
					JOptionPane.showMessageDialog(null,  "�������г�����", "����!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Illegalchar(sequence))//�Ƿ�����
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
							if(!temptrain.getSequence().equals(train.getSequence())&&temptrain.getSequence().equals(sequence))
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
					starthour=Integer.parseInt(TextStartHour.getText());
					startmin=Integer.parseInt(TextStartMin.getText());
					durationhour=Integer.parseInt(TextDurationHour.getText());
					durationmin=Integer.parseInt(TextDurationMin.getText());
					if(starthour<0||starthour>23||startmin<0||startmin>59||durationhour<0||durationmin<0||durationmin>59)//ʱ�����
					{
						JOptionPane.showMessageDialog(null,  "ʱ�����", "����!",JOptionPane.ERROR_MESSAGE);
						return;
					}
					price=Double.parseDouble(TextPrice.getText());
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
				train.setDuration(durationhour, durationmin);
				train.setPrice(price);
				train.setSequence(sequence);
				train.setStartTime(starthour, startmin);
				if(startcity.equals(startname)&&endcity.equals(endname))//ֻ�޸Ļ���Ϣ
				{
					System.out.println("ֻ�޸Ļ���Ϣ");
				}
				else//�ı�����
				{
					//���ȴӸ��ڽӱ���ɾ���û�
					City tcity=HeadofCity.getnextCity();
					CityConnection tcon;
					Train ttrain;
					int sindex=-1;//�޸ĺ��index
					int eindex=-1;//�޸ĺ��index
					while(tcity!=null)
					{
						if(tcity.getNameofCity().equals(startname))
						{
							System.out.println("startname="+startname);
							tcon=tcity.getHeadofCityConnection().getnextCon();
							while(tcon!=null)
							{
								ttrain=tcon.getHeadofTrain();
								while(ttrain!=null)
								{
									if(ttrain.getnextTrain()==train)//�ҵ�train֮ǰ�Ļ�
									{
										System.out.println("�ҵ�train֮ǰ�Ļ�");
										ttrain.setnextTrain(train.getnextTrain());//ɾ��
										if(tcon.getHeadofTrain().getnextTrain()==null)//�ڽӱ�û�л�-ɾ��
										{
											System.out.println("ɾ���ڽӱ�");
											CityConnection con=tcity.getHeadofCityConnection();//�ҵ����ڽӽ���ǰһ���
											while(con!=null)
											{
												if(con.getnextCon()==tcon)
												{
													break;
												}
												con=con.getnextCon();
											}
											con.setnextCon(tcon.getnextCon());
										}
										break;
									}
									ttrain=ttrain.getnextTrain();
								}
								tcon=tcon.getnextCon();
							}
							break;
						}
						tcity=tcity.getnextCity();
					}
					//��ֵsindex,eindex
					tcity=HeadofCity.getnextCity();
					while(tcity!=null)
					{
						if(tcity.getNameofCity().equals(startcity))
						{
							sindex=tcity.getIndexofCity();
						}
						if(tcity.getNameofCity().equals(endcity))
						{
							eindex=tcity.getIndexofCity();
						}
						tcity=tcity.getnextCity();
					}
					//����train
					tcity=HeadofCity.getnextCity();
					while(tcity!=null)
					{
						if(sindex==tcity.getIndexofCity())
						{
							tcity.addTrain(train, eindex);
							train.setAmount(train.getAmount()-1);
						}
						tcity=tcity.getnextCity();
					}
				}
				try {
					edittrain.UpdateTable();
					edittrain.setEnabled(true);
					editcity.UpdateGraphics();
					EditTrainInfo.this.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ButtonSave.setBounds(94, 381, 113, 27);
		getContentPane().add(ButtonSave);
//ȡ��		
		JButton ButtonCancel = new JButton("\u53D6\u6D88");
		ButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edittrain.setEnabled(true);
				EditTrainInfo.this.dispose();
			}
		});
		ButtonCancel.setBounds(329, 381, 113, 27);
		getContentPane().add(ButtonCancel);
		
		JLabel LabelSeq = new JLabel("\u5217\u8F66\u8F66\u6B21");
		LabelSeq.setBounds(109, 60, 72, 18);
		getContentPane().add(LabelSeq);
		
		JLabel LabelStartCity = new JLabel("\u51FA\u53D1\u57CE\u5E02");
		LabelStartCity.setBounds(109, 108, 72, 18);
		getContentPane().add(LabelStartCity);
		
		JLabel LabelEndCity = new JLabel("\u5230\u8FBE\u57CE\u5E02");
		LabelEndCity.setBounds(109, 152, 72, 18);
		getContentPane().add(LabelEndCity);
		
		JLabel LabelStartTime = new JLabel("\u51FA\u53D1\u65F6\u95F4");
		LabelStartTime.setBounds(109, 201, 72, 18);
		getContentPane().add(LabelStartTime);
		
		JLabel label = new JLabel("\u5386\u65F6");
		label.setBounds(113, 246, 44, 18);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u7968\u4EF7");
		label_1.setBounds(118, 290, 39, 18);
		getContentPane().add(label_1);
		
		TextSequence = new JTextField();
		TextSequence.setBounds(221, 57, 86, 24);
		getContentPane().add(TextSequence);
		TextSequence.setColumns(10);

		ComboBoxStartCity = new JComboBox();
		ComboBoxStartCity.setBounds(221, 105, 86, 24);
		getContentPane().add(ComboBoxStartCity);
		
		ComboBoxEndCity = new JComboBox();
		ComboBoxEndCity.setBounds(221, 149, 86, 24);
		getContentPane().add(ComboBoxEndCity);
		
		TextStartHour = new JTextField();
		TextStartHour.setBounds(221, 201, 32, 24);
		getContentPane().add(TextStartHour);
		TextStartHour.setColumns(10);
		
		TextStartMin = new JTextField();
		TextStartMin.setBounds(279, 201, 32, 24);
		getContentPane().add(TextStartMin);
		TextStartMin.setColumns(10);
		
		TextDurationHour = new JTextField();
		TextDurationHour.setBounds(221, 243, 32, 24);
		getContentPane().add(TextDurationHour);
		TextDurationHour.setColumns(10);
		
		TextDurationMin = new JTextField();
		TextDurationMin.setBounds(279, 243, 32, 24);
		getContentPane().add(TextDurationMin);
		TextDurationMin.setColumns(10);
		
		TextPrice = new JTextField();
		TextPrice.setBounds(221, 287, 86, 24);
		getContentPane().add(TextPrice);
		TextPrice.setColumns(10);
		
		JLabel LabelInfo = new JLabel(":");
		LabelInfo.setBounds(257, 204, 8, 18);
		getContentPane().add(LabelInfo);
		
		JLabel LabelInfo2 = new JLabel(":");
		LabelInfo2.setBounds(257, 246, 8, 18);
		getContentPane().add(LabelInfo2);
		
		Update();
	}
	public void Update()
	{
		TextSequence.setText(train.getSequence());
		TextStartHour.setText(String.valueOf(train.getStartHour()));
		TextStartMin.setText(String.valueOf(train.getStartMin()));
		TextDurationHour.setText(String.valueOf(train.getDurationHour()));
		TextDurationMin.setText(String.valueOf(train.getDurationMin()));
		TextPrice.setText(String.valueOf(train.getPrice()));
		
		String[] StartCityName=new String[HeadofCity.getAmountofCity()];
		City p=HeadofCity.getnextCity();
		for(int i=0;p!=null;i++)
		{
			if(p.getIndexofCity()==startindex)
			{
				startname=p.getNameofCity();
			}
			if(p.getIndexofCity()==endindex)
			{
				endname=p.getNameofCity();
			}
			StartCityName[i]=p.getNameofCity();
			p=p.getnextCity();
		}
		ComboBoxModel startcity=new DefaultComboBoxModel(StartCityName);
		ComboBoxStartCity.setModel(startcity);
		ComboBoxStartCity.setSelectedItem(startname);
		
		String[] EndCityName=new String[HeadofCity.getAmountofCity()-1];
		City q=HeadofCity.getnextCity();
		for(int i=0;q!=null;q=q.getnextCity())
		{
			if(q.getIndexofCity()==startindex)//�����뷢�������ظ�
			{
				continue;
			}
			EndCityName[i]=q.getNameofCity();
			i++;
		}
		ComboBoxModel endcity=new DefaultComboBoxModel(EndCityName);
		ComboBoxEndCity.setModel(endcity);
		ComboBoxEndCity.setSelectedItem(endname);
		
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
