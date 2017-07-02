package transportation;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.*;

import com.sun.prism.Graphics;

public class AddStyle {
	private JLabel returnlabel=null;
	private JButton returnbutton=null;
	private ImageIcon returnimageicon=null;
	private int xold;//��ť��ק
	private int yold;//��ť��ק
	public void AddBorderButton(final JPanel jp,final JFrame jf)
	{
		JButton ButtonExit = new JButton();//�رհ�ť
		ButtonExit.setContentAreaFilled(false);
		ButtonExit.setBorderPainted(false);
		ButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int height=jp.getHeight();
				int width=jp.getWidth();
				for(int y=height;y>0;y-=5)//�ر�Ч��
				{
					jf.setSize(width, y);
				}
				System.exit(0);//���ùر�
			}
		});
		ButtonExit.setBounds(jf.getWidth()-32, 0, 30, 33);
		jp.add(ButtonExit);
		
		JButton ButtonMinimize = new JButton();//��С����ť
		ButtonMinimize.setContentAreaFilled(false);
		ButtonMinimize.setBorderPainted(false);
		ButtonMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setExtendedState(jf.ICONIFIED);//������С��
			}
		});
		ButtonMinimize.setBounds(jf.getWidth()-64, 0, 30, 33);
		jp.add(ButtonMinimize);
	}
	public void AddSubBorderButton(final JPanel jp,JFrame subjf,JFrame mainjf)
	{
		JButton ButtonExit = new JButton();//�رհ�ť
		ButtonExit.setContentAreaFilled(false);
		ButtonExit.setBorderPainted(false);
		ButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainjf.setEnabled(true);
				subjf.dispose();
			}
		});
		ButtonExit.setBounds(subjf.getWidth()-32, 0, 30, 33);
		jp.add(ButtonExit);
		
		JButton ButtonMinimize = new JButton();//��С����ť
		ButtonMinimize.setContentAreaFilled(false);
		ButtonMinimize.setBorderPainted(false);
		ButtonMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subjf.setExtendedState(subjf.ICONIFIED);//������С��
			}
		});
		ButtonMinimize.setBounds(subjf.getWidth()-64, 0, 30, 33);
		jp.add(ButtonMinimize);
	}
	public void AddMyButton(JPanel jp,String imageaddress,int x,int y)
	{
		returnimageicon=new ImageIcon(imageaddress);//���ͼƬ
		returnlabel=new JLabel(returnimageicon);//ͼƬ����-label
		returnlabel.setBounds(x, y, returnimageicon.getIconWidth(), returnimageicon.getIconHeight());
		jp.add(returnlabel);
		returnbutton=new JButton();//���͸����ť
		returnbutton.setContentAreaFilled(false);
		returnbutton.setBorderPainted(false);
		returnbutton.setBounds(x, y, returnimageicon.getIconWidth(), returnimageicon.getIconHeight());
		jp.add(returnbutton);
	}
	public void AddCity(EditCity editcity,JLayeredPane jp,String name,int x,int y,JLabel[] label,JLabel[] labelname,int index,City city)//����ק�İ�ť
	{
		System.out.println("index of button="+index);
		java.awt.Graphics g=jp.getGraphics();
		ImageIcon image=new ImageIcon("UI/CityCircle.png");//���ͼƬ
		label[index]=new JLabel(image);//ͼƬ����-label
		label[index].setOpaque(false);
		labelname[index]=new JLabel();
		
		label[index].setBounds(x, y, image.getIconWidth(), image.getIconHeight());
		labelname[index].setText(name);//���ó�����
		labelname[index].setFont(new Font("������ͤ��ϸ�ڼ���",0,20));
		labelname[index].setBounds(x+5, y, 50, 50);
		
		//������קЧ��
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
//				  if((label[index].getX()<=50&&xnew<0)||(label[index].getX()>900&&xnew>0)||(label[index].getY()<=50&&ynew<0)||(label[index].getY()>600&&ynew>0))//������Χ
//				  {
//					  return;
//				  }
				  labelname[index].setLocation(labelname[index].getX()+xshift,labelname[index].getY()+yshift);
				  label[index].setLocation(label[index].getX()+xshift,label[index].getY()+yshift);
				  city.setx(label[index].getX()+xshift);
				  city.sety(label[index].getY()+yshift);
				  editcity.UpdateGraphics();
			  }
		});
		jp.add(labelname[index]);
		jp.add(label[index]);
	}
	public void UpdateCity(EditCity editcity,JLayeredPane jp,String name,int x,int y,JLabel[] label,JLabel[] labelname,City city)//����ק�İ�ť
	{
		//System.out.println("index of button="+index);
		java.awt.Graphics g=jp.getGraphics();
		int index=city.getIndexofCity();
		ImageIcon image=new ImageIcon("UI/CityCircle.png");//���ͼƬ
		label[index]=new JLabel(image);//ͼƬ����-label
		labelname[index]=new JLabel();
		
		label[index].setBounds(x, y, image.getIconWidth(), image.getIconHeight());
		labelname[index].setText(name);//���ó�����
		labelname[index].setFont(new Font("������ͤ��ϸ�ڼ���",0,20));
		labelname[index].setBounds(x+5, y, 50, 50);
		
		//������קЧ��
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
//				  if((label[index].getX()<=50&&xshift<0)||(label[index].getX()>900&&xshift>0)||(label[index].getY()<=50&&ynew<0)||(label[index].getY()>600&&ynew>0))//������Χ
//				  {
//					  return;
//				  }
				  labelname[index].setLocation(labelname[index].getX()+xshift,labelname[index].getY()+yshift);
				  label[index].setLocation(label[index].getX()+xshift,label[index].getY()+yshift);
				  city.setx(label[index].getX()+xshift);
				  city.sety(label[index].getY()+yshift);
				  
				  editcity.UpdateGraphics();
			  }
		});
		jp.add(labelname[index]);
		jp.add(label[index]);
	}
	public void setCity()
	{}
	public JLabel getLabel()
	{
		return returnlabel;
	}
	public JButton getbutton()
	{
		return returnbutton;
	}
	public ImageIcon getimageicon()
	{
		return returnimageicon;
	}
}
