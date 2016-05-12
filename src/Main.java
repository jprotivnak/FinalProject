import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.sound.sampled.*;
import javax.swing.border.EtchedBorder;

/**
 * @author Jack Protivnak
 *
 */
public class Main extends JFrame implements ActionListener, Serializable {
	public static final long serialVersionUID = 1;
	private JMenu TVTuner;
	private JMenu file;
	private JMenu view;
	private JMenu subMenu;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JTextField shows;
	private JPanel options;
	private JButton button;
	private JList list;
	private JScrollPane listScroller;
	private ArrayList<Show> showList = new ArrayList<Show>();
	private Object[] showCount;
	private int[] seasonNumber = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	private Font f1 = new Font("Dialog", Font.BOLD, 14);

	public Main() {
		super();
		this.setLayout(new GridLayout(2, 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showCount = new Show[2];

		menuBar = new JMenuBar();

		list = new JList(showCount);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);

		listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));

		this.add(listScroller, BorderLayout.NORTH);

		options = new JPanel();
		options.setLayout(new GridLayout(1, 2));
		options.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Options"));

		TVTuner = new JMenu("Tv Tuner");

		menuItem = new JMenuItem("Preferences");
		menuItem.setActionCommand("preferences");
		menuItem.addActionListener(this);

		TVTuner.add(menuItem);

		menuItem = new JMenuItem("About");
		menuItem.setActionCommand("about");
		menuItem.addActionListener(this);

		TVTuner.add(menuItem);

		menuItem = new JMenuItem("License");
		menuItem.setActionCommand("license");
		menuItem.addActionListener(this);

		TVTuner.add(menuItem);

		TVTuner.addSeparator();

		menuItem = new JMenuItem("Quit");
		menuItem.setActionCommand("quit");
		menuItem.addActionListener(this);

		TVTuner.add(menuItem);

		TVTuner.setFont(f1);

		TVTuner.add(menuItem);

		menuBar.add(TVTuner);

		file = new JMenu("File");
		menuItem = new JMenuItem("New");
		menuItem.setActionCommand("new");
		menuItem.addActionListener(this);

		file.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.setActionCommand("open");
		menuItem.addActionListener(this);

		file.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.setActionCommand("save");
		menuItem.addActionListener(this);

		file.add(menuItem);

		file.setFont(f1);

		menuBar.add(file);

		view = new JMenu("View");
		menuItem = new JMenuItem("Maximize Window");
		menuItem.setActionCommand("fScreen");
		menuItem.addActionListener(this);

		view.add(menuItem);

		menuItem = new JMenuItem("Shrink Window");
		menuItem.setActionCommand("sWindow");
		menuItem.addActionListener(this);

		view.add(menuItem);

		view.setFont(f1);

		menuBar.add(view);

		button = new JButton("Add Show");
		button.setActionCommand("add");
		button.addActionListener(this);

		options.add(button);

		button = new JButton("Remove Show");
		button.setActionCommand("remove");
		button.addActionListener(this);

		options.add(button);

		button = new JButton("Info");
		button.setActionCommand("info");
		button.addActionListener(this);

		options.add(button);

		button = new JButton("Edit Show");
		button.setActionCommand("edit");
		button.addActionListener(this);

		options.add(button);

		// subMenu = new JMenu("");

		this.setJMenuBar(menuBar);
		this.add(options);

		this.setResizable(true);
		this.getPreferredSize();
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "add":
			addShow();
			break;
		case "remove":
			break;
		case "info":
			break;
		case "edit":
			break;
		case "preferences":
			break;
		case "about":
			JOptionPane.showMessageDialog(null,
					"TVTUNER\u2122\nCopyright \u00A92016 Pro Inc. All Rights Reserved \u00AE. License Agreement");
			break;
		case "license":
			JOptionPane.showMessageDialog(null,
					"Copyright (\u00A9) 2016, 1998 Pro Inc.\n800 Montana Ave., Natrona Heights, PA  15065, USA\nEveryone is permitted to copy and distribute verbatim copies\nof this license document, but changing it is not allowed.");
			break;
		case "quit":
			System.exit(-1);
			break;
		case "new":
			break;
		case "open":
			break;
		case "save":
			break;
		case "fScreen":
			this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			break;
		case "sWindow":
			this.setSize(this.getPreferredSize());
			break;

		}

	}

	public void addShow() {
		Show newShow = new Show();
		newShow.name = JOptionPane.showInputDialog("Please enter the name of your show");
		showList.add(newShow);
		showCount = new Show[showList.size()];
		showCount = showList.toArray(showCount);
		for(int i = 0; i < showCount.length; i++) {
			System.out.println(((Show) showCount[i]).getName());
		}
		
		
		
		
		
		
		
		
		
		
		JTextField name = new JTextField();
		JTextField description = new JTextField();
		JComboBox c = new JComboBox();
		Object[] message = {"Name:", name, "Description:", description, "Number of Seasons:", c};
		int option = JOptionPane.showConfirmDialog(null, message, "New Show", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
		    for (int i = 0; i < seasonNumber.length; i++)
		      c.addItem(seasonNumber[i]);
		    c.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        t.setText("index: " + c.getSelectedIndex() + "   "
		            + ((JComboBox) e.getSource()).getSelectedItem());
		      }
		    });
		
		
		
		
		
		
		
		
		
//		http://www.java2s.com/Code/Java/Swing-JFC/Usingdropdownlists.htm
		
		
//		JTextField username = new JTextField();
//		JTextField password = new JPasswordField();
//		Object[] message = {
//		    "Username:", username,
//		    "Password:", password
//		};
//
//		int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
//		if (option == JOptionPane.OK_OPTION) {
//		    if (username.getText().equals("h") && password.getText().equals("h")) {
//		        System.out.println("Login successful");
//		    } else {
//		        System.out.println("login failed");
//		    }
//		} else {
//		    System.out.println("Login canceled");
//		}
		
		
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
