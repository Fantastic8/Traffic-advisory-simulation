package transportation;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	private static int MaxCity=100;//定义最大城市个数
	private static City HeadofCity;//城市头结点
	//=======================按钮类
	//标签
	private static JLabel[] LabelCity;
	private static JLabel[] LabelCityName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//数据配置
				HeadofCity=new City();//城市头结点
				LabelCity=new JLabel[MaxCity];
				LabelCityName=new JLabel[MaxCity];
				
				//数据配置完毕
				try {
					EditCity edit;
					edit = new EditCity(LabelCity,LabelCityName,HeadofCity);
					edit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public City getHeadofCity()//获取城市头结点
	{
		return HeadofCity;
	}
	public JLabel[] getLabelCity()//获取城市图片
	{
		return LabelCity;
	}
	public JLabel[] getLabelCityName()//获取城市名字标签
	{
		return LabelCityName;
	}
}
