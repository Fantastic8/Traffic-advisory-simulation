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
	private static int MaxCity=100;//���������и���
	private static City HeadofCity;//����ͷ���
	//=======================��ť��
	//��ǩ
	private static JLabel[] LabelCity;
	private static JLabel[] LabelCityName;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//��������
				HeadofCity=new City();//����ͷ���
				LabelCity=new JLabel[MaxCity];
				LabelCityName=new JLabel[MaxCity];
				
				//�����������
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
	public City getHeadofCity()//��ȡ����ͷ���
	{
		return HeadofCity;
	}
	public JLabel[] getLabelCity()//��ȡ����ͼƬ
	{
		return LabelCity;
	}
	public JLabel[] getLabelCityName()//��ȡ�������ֱ�ǩ
	{
		return LabelCityName;
	}
}
