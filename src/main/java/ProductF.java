package main.java;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProductF {

	JFrame frame;
	private JLabel lbl1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductF window = new ProductF();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProductF() {
		initialize();
	}
	JLabel lblCallories;
	JLabel lblPrice;
	JLabel lblProvider;
	JTextPane textPaneComposition;
	JTextPane textPaneCook;
	JPanel panel;
	private JLabel lblName;
	public void setProductDataAndComposition(ArrayList<String> data,ArrayList<String>composition,final String productName) {
		lblName.setText(productName);
		lblCallories.setText(data.get(0).split("имеет алорийность:")[1]);
		lblPrice.setText(data.get(1).split("имеет÷ену:")[1]+ " руб.");	
		lblProvider.setText(data.get(2).split("имеетѕроизводител€:")[1]);
		for(String element : composition) {
			if(element.contains("имеет»нгредиенты"))
				textPaneComposition.setText(textPaneComposition.getText()+"-"+element.split("имеет»нгредиенты:")[1]+"\n\n");
			if(element.contains("имеет—пособѕриготовлени€"))
				textPaneCook.setText(textPaneCook.getText()+"-"+element.split("имеет—пособѕриготовлени€:")[1]+"\n\n");
		}		
				panel = new JPanel() {
					public void paint(Graphics g) {
						super.paintComponent(g);
						DrawProductPicture(g);
						repaint();
					}

					private void DrawProductPicture(Graphics g) {
						try {
						// TODO Auto-generated method stub
						BufferedImage img = null;
						JLayeredPane layer = new JLayeredPane();
						if (productName.contains("“орт")) {
							img = ImageIO.read(new File("source\\images\\cake.jpg"));					
						}
						if (productName.contains(" онфета")) {
							img = ImageIO.read(new File("source\\images\\candy.png"));					
						}
						if (productName.contains(" екс")) {
							img = ImageIO.read(new File("source\\images\\keks.jpg"));
						}
						if (productName.contains("¬афл€")) {
							img = ImageIO.read(new File("source\\images\\vaflya.png"));					
						}
						if (productName.contains("ѕр€ник")) {
							img = ImageIO.read(new File("source\\images\\pryanik.jpg"));					
						}
						if (productName.contains("ѕеченье")) {
							img = ImageIO.read(new File("source\\images\\cookie.jpg"));					
						}
						g.drawImage(img, 0, 0, layer);
					}catch (IOException e) {
						System.out.println(e);
					}
				}
				};
				panel.setBounds(10, 10, 150, 150);
				frame.getContentPane().add(panel);
		}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		lbl1 = new JLabel("\u0421\u0442\u043E\u0438\u043C\u043E\u0441\u0442\u044C:");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl1.setBounds(192, 81, 117, 25);
		frame.getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("\u041A\u0430\u043B\u043B\u043E\u0440\u0438\u0438:");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl2.setBounds(192, 110, 117, 33);
		frame.getContentPane().add(lbl2);
		
		JLabel label_1 = new JLabel("\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044C:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(192, 146, 117, 25);
		frame.getContentPane().add(label_1);
		
		lblPrice = new JLabel("-");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrice.setBounds(319, 81, 284, 25);
		frame.getContentPane().add(lblPrice);
		
		lblCallories = new JLabel("-");
		lblCallories.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCallories.setBounds(319, 110, 284, 33);
		frame.getContentPane().add(lblCallories);
		
		lblProvider = new JLabel("-");
		lblProvider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProvider.setBounds(319, 146, 284, 25);
		frame.getContentPane().add(lblProvider);
		
		JLabel label = new JLabel("\u0421\u043E\u0441\u0442\u0430\u0432:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 196, 106, 14);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("\u0421\u043F\u043E\u0441\u043E\u0431 \u043F\u0440\u0438\u0433\u043E\u0442\u043E\u0432\u043B\u0435\u043D\u0438\u044F:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(304, 196, 181, 14);
		frame.getContentPane().add(label_2);
		
		textPaneComposition = new JTextPane();
		textPaneComposition.setFont(new Font("Tahoma", Font.ITALIC, 12));
		textPaneComposition.setEditable(false);
		textPaneComposition.setBackground(Color.WHITE);
		textPaneComposition.setBounds(20, 243, 274, 235);
		frame.getContentPane().add(textPaneComposition);
		
		textPaneCook = new JTextPane();
		textPaneCook.setFont(new Font("Tahoma", Font.ITALIC, 12));
		textPaneCook.setEditable(false);
		textPaneCook.setBackground(Color.WHITE);
		textPaneCook.setBounds(314, 243, 274, 235);
		frame.getContentPane().add(textPaneCook);
		
		lblName = new JLabel("-");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(192, 11, 293, 25);
		frame.getContentPane().add(lblName);
		frame.setBounds(100, 100, 629, 528);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
