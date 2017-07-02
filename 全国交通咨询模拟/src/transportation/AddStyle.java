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
	private int xold;//按钮拖拽
	private int yold;//按钮拖拽
	public void AddBorderButton(final JPanel jp,final JFrame jf)
	{
		JButton ButtonExit = new JButton();//关闭按钮
		ButtonExit.setContentAreaFilled(false);
		ButtonExit.setBorderPainted(false);
		ButtonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int height=jp.getHeight();
				int width=jp.getWidth();
				for(int y=height;y>0;y-=5)//关闭效果
				{
					jf.setSize(width, y);
				}
				System.exit(0);//设置关闭
			}
		});
		ButtonExit.setBounds(jf.getWidth()-32, 0, 30, 33);
		jp.add(ButtonExit);
		
		JButton ButtonMinimize = new JButton();//最小化按钮
		ButtonMinimize.setContentAreaFilled(false);
		ButtonMinimize.setBorderPainted(false);
		ButtonMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setExtendedState(jf.ICONIFIED);//设置最小化
			}
		});
		ButtonMinimize.setBounds(jf.getWidth()-64, 0, 30, 33);
		jp.add(ButtonMinimize);
	}
	public void AddSubBorderButton(final JPanel jp,JFrame subjf,JFrame mainjf)
	{
		JButton ButtonExit = new JButton();//关闭按钮
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
		
		JButton ButtonMinimize = new JButton();//最小化按钮
		ButtonMinimize.setContentAreaFilled(false);
		ButtonMinimize.setBorderPainted(false);
		ButtonMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subjf.setExtendedState(subjf.ICONIFIED);//设置最小化
			}
		});
		ButtonMinimize.setBounds(subjf.getWidth()-64, 0, 30, 33);
		jp.add(ButtonMinimize);
	}
	public void AddMyButton(JPanel jp,String imageaddress,int x,int y)
	{
		returnimageicon=new ImageIcon(imageaddress);//添加图片
		returnlabel=new JLabel(returnimageicon);//图片容器-label
		returnlabel.setBounds(x, y, returnimageicon.getIconWidth(), returnimageicon.getIconHeight());
		jp.add(returnlabel);
		returnbutton=new JButton();//添加透明按钮
		returnbutton.setContentAreaFilled(false);
		returnbutton.setBorderPainted(false);
		returnbutton.setBounds(x, y, returnimageicon.getIconWidth(), returnimageicon.getIconHeight());
		jp.add(returnbutton);
	}
	public void AddCity(EditCity editcity,JLayeredPane jp,String name,int x,int y,JLabel[] label,JLabel[] labelname,int index,City city)//可拖拽的按钮
	{
		System.out.println("index of button="+index);
		java.awt.Graphics g=jp.getGraphics();
		ImageIcon image=new ImageIcon("UI/CityCircle.png");//添加图片
		label[index]=new JLabel(image);//图片容器-label
		label[index].setOpaque(false);
		labelname[index]=new JLabel();
		
		label[index].setBounds(x, y, image.getIconWidth(), image.getIconHeight());
		labelname[index].setText(name);//设置城市名
		labelname[index].setFont(new Font("方正兰亭超细黑简体",0,20));
		labelname[index].setBounds(x+5, y, 50, 50);
		
		//增加拖拽效果
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
//				  if((label[index].getX()<=50&&xnew<0)||(label[index].getX()>900&&xnew>0)||(label[index].getY()<=50&&ynew<0)||(label[index].getY()>600&&ynew>0))//超出范围
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
	public void UpdateCity(EditCity editcity,JLayeredPane jp,String name,int x,int y,JLabel[] label,JLabel[] labelname,City city)//可拖拽的按钮
	{
		//System.out.println("index of button="+index);
		java.awt.Graphics g=jp.getGraphics();
		int index=city.getIndexofCity();
		ImageIcon image=new ImageIcon("UI/CityCircle.png");//添加图片
		label[index]=new JLabel(image);//图片容器-label
		labelname[index]=new JLabel();
		
		label[index].setBounds(x, y, image.getIconWidth(), image.getIconHeight());
		labelname[index].setText(name);//设置城市名
		labelname[index].setFont(new Font("方正兰亭超细黑简体",0,20));
		labelname[index].setBounds(x+5, y, 50, 50);
		
		//增加拖拽效果
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
//				  if((label[index].getX()<=50&&xshift<0)||(label[index].getX()>900&&xshift>0)||(label[index].getY()<=50&&ynew<0)||(label[index].getY()>600&&ynew>0))//超出范围
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
