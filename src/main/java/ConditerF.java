package main.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConditerF {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	Controller controller;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConditerF window = new ConditerF();
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
	public ConditerF() {
		try {
			controller = new Controller();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void setDlm(String[] items) {
		for(String item: items)
		dlm.addElement(item);
	}
	DefaultListModel<String> dlm;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 532, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		dlm = new DefaultListModel<String>();
        String products[]= controller.getAllProducts().toArray(new String[0]);
		setDlm(products);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 11, 275, 442);
		frame.getContentPane().add(scrollPane);
		
		final JList listProduction = new JList();
		listProduction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2&&!listProduction.isSelectionEmpty()) {
					ArrayList<String> productDataValues=controller.getProductDataValues(listProduction.getSelectedValue().toString());
					ArrayList<String> productComposition=controller.getComposition(listProduction.getSelectedValue().toString());
					ProductF productForm=new ProductF();
					productForm.setProductDataAndComposition(productDataValues,productComposition,listProduction.getSelectedValue().toString());
					productForm.frame.setVisible(true);
					System.out.println(productComposition);
				}
			}
		});
		listProduction.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		scrollPane.setViewportView(listProduction);
		listProduction.setModel(dlm);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBounds(38, 36, 144, 92);
		frame.getContentPane().add(panel);
		
		final JRadioButton rbtnConditer = new JRadioButton("\u041A\u043E\u043D\u0434\u0438\u0442\u0435\u0440\u0441\u043A\u0430\u044F");
		rbtnConditer.setBackground(Color.WHITE);
		panel.add(rbtnConditer);
		buttonGroup.add(rbtnConditer);
		
		final JRadioButton rbtnSladko = new JRadioButton("\u0421\u043B\u0430\u0434\u043A\u041E");
		rbtnSladko.setBackground(Color.WHITE);
		panel.add(rbtnSladko);
		buttonGroup.add(rbtnSladko);
		
		JRadioButton rbtnNotSet = new JRadioButton("\u041D\u0435 \u0432\u0430\u0436\u043D\u043E");
		rbtnNotSet.setBackground(Color.WHITE);
		panel.add(rbtnNotSet);
		buttonGroup.add(rbtnNotSet);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(38, 10, 144, 25);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u041F\u0440\u043E\u0438\u0437\u0432\u043E\u0434\u0438\u0442\u0435\u043B\u044C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 144, 25);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.BLACK);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_2.setBounds(38, 176, 144, 121);
		frame.getContentPane().add(panel_2);
		
		final JCheckBox chckbxFruit = new JCheckBox("\u0424\u0440\u0443\u043A\u0442\u043E\u0432\u043E-\u044F\u0433\u043E\u0434\u043D\u0430\u044F");
		chckbxFruit.setBackground(Color.WHITE);
		panel_2.add(chckbxFruit);
		
		final JCheckBox chckbxChocolate = new JCheckBox("\u0428\u043E\u043A\u043E\u043B\u0430\u0434\u043D\u0430\u044F");
		chckbxChocolate.setBackground(Color.WHITE);
		panel_2.add(chckbxChocolate);
		
		final JCheckBox chckbxOil = new JCheckBox("\u0421\u043B\u0438\u0432\u043E\u0447\u043D\u0430\u044F");
		chckbxOil.setBackground(Color.WHITE);
		panel_2.add(chckbxOil);
		
		final JCheckBox chckbxSdoba = new JCheckBox("\u0421\u0434\u043E\u0431\u043D\u0430\u044F");
		chckbxSdoba.setBackground(Color.WHITE);
		panel_2.add(chckbxSdoba);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.BLACK);
		panel_3.setBounds(38, 151, 144, 25);
		frame.getContentPane().add(panel_3);
		
		JLabel label = new JLabel("\u041E\u0441\u043E\u0431\u0435\u043D\u043D\u043E\u0441\u0442\u0438");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setBounds(0, 0, 144, 25);
		panel_3.add(label);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_4.setBounds(38, 347, 144, 61);
		frame.getContentPane().add(panel_4);
		
		final JCheckBox chckbxCheap = new JCheckBox("\u0414\u0435\u0448\u0435\u0432\u0430\u044F");
		chckbxCheap.setBackground(Color.WHITE);
		panel_4.add(chckbxCheap);
		
		final JCheckBox chckbxDiet = new JCheckBox("\u0414\u0438\u0435\u0442\u0438\u0447\u0435\u0441\u043A\u0430\u044F");
		chckbxDiet.setBackground(Color.WHITE);
		panel_4.add(chckbxDiet);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(Color.BLACK);
		panel_5.setBounds(38, 322, 144, 25);
		frame.getContentPane().add(panel_5);
		
		JLabel label_1 = new JLabel("\u0414\u0440\u0443\u0433\u043E\u0435");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.BLACK);
		label_1.setBounds(0, 0, 144, 25);
		panel_5.add(label_1);
		
		JButton button = new JButton("\u0424\u0438\u043B\u044C\u0442\u0440\u043E\u0432\u0430\u0442\u044C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> selectedFilters=new ArrayList<String>();
				dlm.clear();
				if(rbtnConditer.isSelected()) {
					selectedFilters.add("ПродукцияОтКондитерской");
				}
				if(rbtnSladko.isSelected()) {
					selectedFilters.add("ПродукцияСладкО");
				}
				if(chckbxFruit.isSelected()) {
					selectedFilters.add("ФруктовоЯгоднаяПродукция");
				}
				if(chckbxChocolate.isSelected()) {
					selectedFilters.add("ПродукцияШоколадная");
				}
				if(chckbxSdoba.isSelected()) {
					selectedFilters.add("ПродукцияСдобная");
				}
				if(chckbxOil.isSelected()) {
					selectedFilters.add("ПродукцияСливочная");
				}
				if(chckbxCheap.isSelected()) {
					selectedFilters.add("ПродукцияДешевая");
				}
				if(chckbxDiet.isSelected()) {
					selectedFilters.add("ПродукцияНизкокалорийная");
				}
				
				System.out.println(selectedFilters);
				setDlm(controller.getProductsByFilterList(selectedFilters));
			}
		});
		button.setBounds(38, 419, 144, 34);
		frame.getContentPane().add(button);
	}
}
