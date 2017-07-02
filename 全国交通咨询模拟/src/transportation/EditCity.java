package transportation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.IOException;

public class EditCity extends JFrame {
	
	private City HeadofCity;//����ͷ���
	
	private Graphics g;
	
	private ReadFromFile Read;
	private SaveToFile Save;
	//--------�ر�
	private AddStyle ADS;//������Ӱ�ť��
	private JPanel MainJPanel;//�����--content Pane
	public JLayeredPane DrawPanel;//��ͼ���--����һ��-Layered Pane
	private ImageIcon MainBG;//����ͼƬ
	private JLabel mainbackground;//������ǩ
	private int xframeold;
	private int yframeold;
	//=======================��ť��
	//��ť
	private JButton ButtonAddCity;
	private JButton ButtonDeleteCity;
	private JButton ButtonSave;
	private JButton ButtonAddTrain;
	//private JButton[] ButtonCity;
	//��ǩ
	private JLabel[] LabelCity;
	private JLabel[] LabelCityName;
	private JButton ButtonEditTrain;
	private JButton ButtonSearch;
	
	public EditCity(JLabel[] label,JLabel[] labelname,City head) throws IOException {
		//��������
		HeadofCity=head;
		ADS=new AddStyle();
		Read=new ReadFromFile();
		Save=new SaveToFile();
		
		LabelCity=label;
		LabelCityName=labelname;
		//===============================================================һϵ�г�ʼ��
		
		setUndecorated(true);//ȥ�߿�
		setResizable(false);//���ò��ܵ�����С
		setBounds(410, 160, 1000, 800);
		this.setSize(1000, 800);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�������
		MainJPanel=(JPanel)getContentPane();
		DrawPanel=new JLayeredPane();
		DrawPanel.setBounds(MainJPanel.getX()+50, MainJPanel.getY()+50, 900, 600);
		//==-=-=-=-=-=-=-=-=-=-����
		//���ر���ͼƬ
		MainBG=new ImageIcon("UI/MainUI.jpg");
		//���ر���ͼƬ�ı�ǩ
		mainbackground=new JLabel(MainBG);
		mainbackground.setBounds(0, 0, MainBG.getIconWidth(),MainBG.getIconHeight());
		
		MainJPanel.setOpaque(false);
		MainJPanel.setLayout(null);
		//DrawPanel.setOpaque(false);
		//DrawPanel.setLayout(null);
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(mainbackground, new Integer(Integer.MIN_VALUE));
		this.getLayeredPane().add(DrawPanel, new Integer(Integer.MAX_VALUE));
		this.g=DrawPanel.getGraphics();
		//===============================================================����Ԫ��
		ADS.AddBorderButton(MainJPanel, EditCity.this);
		MainPane();//���������
		//===============================================================ʵ����קЧ��
		this.addMouseListener(new MouseAdapter() 
		{
		  @Override
		  public void mousePressed(MouseEvent e) {
		  xframeold = e.getX();
		  yframeold = e.getY();
		  }
		 });
		this.addMouseMotionListener(new MouseMotionAdapter() {
			  public void mouseDragged(MouseEvent e) {
			  int xOnScreen = e.getXOnScreen();
			  int yOnScreen = e.getYOnScreen();
			  int xframenew = xOnScreen - xframeold;
			  int yframenew = yOnScreen - yframeold;
			  EditCity.this.setLocation(xframenew, yframenew);
			  }
			 });
	}
	public void MainPane() throws IOException//����Ԫ��
	{
		UpdateIcon();
//��ӳ���
		ButtonAddCity = new JButton("\u6DFB\u52A0\u57CE\u5E02");
		ButtonAddCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCity addcity=new AddCity(EditCity.this,HeadofCity);
				addcity.setVisible(true);
				EditCity.this.setEnabled(false);
			}
		});
		ButtonAddCity.setBounds(57, 710, 100, 27);
		MainJPanel.add(ButtonAddCity);
//ɾ������
		ButtonDeleteCity = new JButton("\u5220\u9664\u57CE\u5E02");
		ButtonDeleteCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteCity deletecity=new DeleteCity(EditCity.this,HeadofCity);
				deletecity.setVisible(true);
				EditCity.this.setEnabled(false);
			}
		});
		ButtonDeleteCity.setBounds(209, 710, 100, 27);
		MainJPanel.add(ButtonDeleteCity);
//����
		ButtonSave = new JButton("\u4FDD\u5B58");
		ButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Save.SaveCity(HeadofCity);
					Save.SaveAll(HeadofCity,LabelCity);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ButtonSave.setBounds(516, 710, 113, 27);
		MainJPanel.add(ButtonSave);
//����г�
		ButtonAddTrain = new JButton("\u6DFB\u52A0\u5217\u8F66");
		ButtonAddTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTrain addtrain=new AddTrain(EditCity.this,HeadofCity);
				addtrain.setVisible(true);
				EditCity.this.setEnabled(false);
			}
		});
		ButtonAddTrain.setBounds(364, 710, 100, 27);
		MainJPanel.add(ButtonAddTrain);
//�鿴�г�	
		ButtonEditTrain = new JButton("\u67E5\u770B\u5217\u8F66");
		ButtonEditTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditTrain edittrain;
				try {
					edittrain = new EditTrain(HeadofCity,EditCity.this);
					edittrain.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ButtonEditTrain.setBounds(691, 710, 113, 27);
		getContentPane().add(ButtonEditTrain);
		
		ButtonSearch = new JButton("\u67E5\u8BE2\u7EBF\u8DEF");
		ButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search search;
				try {
					search = new Search(EditCity.this,HeadofCity);
					search.setVisible(true);
					EditCity.this.setEnabled(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ButtonSearch.setBounds(851, 710, 113, 27);
		getContentPane().add(ButtonSearch);
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		this.g=DrawPanel.getGraphics();
		//UpdateIcon();//��ӳ��н����
		UpdateGraphics();
	}
//��ӳ���ͼ��
	public Point addACity(String name,City city)//����һ������,λ�����
	{
		int x=(int)(Math.random()*850+50);
		int y=(int)(Math.random()*550+50);
		ADS.AddCity(EditCity.this,DrawPanel, name,x , y,LabelCity,LabelCityName,HeadofCity.getAmountofCity()-1,city);
		MainJPanel.paintComponents(getGraphics());//����
		return new Point(x,y);
	}
	public void addACity(String name,int x,int y,City city)//����һ������,λ�ù̶�
	{
		ADS.AddCity(EditCity.this,DrawPanel, name,x , y,LabelCity,LabelCityName,HeadofCity.getAmountofCity()-1,city);
		MainJPanel.paintComponents(getGraphics());//����
	}
	public void UpdateCity(String name,int x,int y,City city)//�������г���
	{
		ADS.UpdateCity(EditCity.this,DrawPanel, name,x , y,LabelCity,LabelCityName,city);
		MainJPanel.paintComponents(getGraphics());//����
	}
//���һ��ֱ��
	public void addALine(City start,City end)
	{
		//this.g=MainJPanel.getGraphics();
		int startx=LabelCity[start.getIndexofCity()].getX()+25;
		int starty=LabelCity[start.getIndexofCity()].getY()+25;
		int endx=LabelCity[end.getIndexofCity()].getX()+25;
		int endy=LabelCity[end.getIndexofCity()].getY()+25;
		
		System.out.println("startx="+startx+" starty="+starty+" endx="+endx+" endy="+endy);
		
		float lineWidth = 1.7f;
	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
		g.drawLine(startx, starty, endx, endy);
		g.clearRect(startx-25, starty-25, 50, 50);//ȥ��label����
		g.clearRect(endx-25, endy-25, 50, 50);//ȥ��label����
		MainJPanel.paintComponents(getGraphics());
	}
//����ȫ��ֱ��
	public void UpdateGraphics()//������(50,50,900,600)
	{
		//System.out.println("UpdateGraphics");
		//paintComponents(getGraphics());
		//paintComponents(g);
		//MainJPanel.paintComponents(getGraphics());
		//MainJPanel.paintComponents(g);
		DrawPanel.repaint();
		//DrawPanel.paint(g);		
		//DrawPanel.paintComponents(g);
		if(g==null)
		{
			System.out.println("G is NULL!");
			return;
		}
		float lineWidth = 1.7f;
	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
		int startx;
		int starty;
		int endx;
		int endy;
		int city1,city2;
		City temp=HeadofCity.getnextCity();
		if(temp==null)
		{
			return;//��ֹ��ͼ
		}
		int index=temp.getIndexofCity();
		while(temp!=null)
		{
			//��������
			temp.setx(LabelCity[temp.getIndexofCity()].getX());
			temp.sety(LabelCity[temp.getIndexofCity()].getY());
			CityConnection tempcon=temp.getHeadofCityConnection().getnextCon();
			while(tempcon!=null)
			{
				startx=LabelCity[tempcon.getStartIndex()].getX()+25;
				starty=LabelCity[tempcon.getStartIndex()].getY()+25;
				endx=LabelCity[tempcon.getEndIndex()].getX()+25;
				endy=LabelCity[tempcon.getEndIndex()].getY()+25;
				g.drawLine(startx, starty, endx, endy);
				//g.clearRect(startx-25, starty-25, 50, 50);//ȥ��label����
				//g.clearRect(endx-25, endy-25, 50, 50);//ȥ��label����
				tempcon=tempcon.getnextCon();
			}
			index--;
			temp=temp.getnextCity();
		}
		DrawPanel.paint(g);
	}
//���³���ͼ��
	public void UpdateIcon() throws IOException
	{
		//System.out.println("UpdateIcon");
		BufferedReader temp=Read.getCityReader();
		Read.ReadAll(LabelCity, LabelCityName, HeadofCity,EditCity.this);
		for(int i=0;i<HeadofCity.getAmountofCity();i++)
		{
			DrawPanel.add(LabelCityName[i]);
			//MainJPanel.add(ButtonCity[i]);
			DrawPanel.add(LabelCity[i]);
		}
	}
//�������и��³���ͼ��
	public void UpdateFromchain()
	{
		DrawPanel.removeAll();
		System.out.println("UpdateFromchain");
		for(int i=0;i<LabelCity.length;i++)
		{
			//ButtonCity[i]=null;
			LabelCity[i]=null;
			LabelCityName[i]=null;
		}
		//MainJPanel.paintComponents(g);
		City temp=HeadofCity.getnextCity();
		while(temp!=null)
		{
			UpdateCity(temp.getNameofCity(),temp.getx(),temp.gety(),temp);
			temp=temp.getnextCity();
		}
		//AddBacis();
		UpdateGraphics();
	}
	public void AddBacis()
	{
		MainJPanel.add(ButtonAddCity);
		MainJPanel.add(ButtonDeleteCity);
		MainJPanel.add(ButtonSave);
		MainJPanel.add(ButtonAddTrain);
		MainJPanel.add(ButtonEditTrain);
		MainJPanel.add(ButtonSearch);
		ADS.AddBorderButton(MainJPanel, EditCity.this);
	}
}
