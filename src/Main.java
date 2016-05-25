import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
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
	private Show[] tempArray;
	private Object[] showCount;
	private int[] seasonNumber = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	private Font f1 = new Font("Dialog", Font.BOLD, 14);
	private File img = new File("imagen.png");
	private File fiile;
	private Graphics2D g;
	private BufferedImage in;
	private BufferedImage newImage;
	private Icon icon;
	private JComboBox c;
	private JFileChooser chooser;
	private FileOutputStream outStream;
	private ObjectOutputStream outFile;
	private FileInputStream inStream;
	private ObjectInputStream inFile;
	private String tempString;

	public Main() {
		super();
		this.setLayout(new GridLayout(2, 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		icon = new ImageIcon("tv.png");
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
			removeShow();
			break;
		case "info":
			getInfo();
			break;
		case "edit":
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
			openFile();
			break;
		case "save":
			saveFile();
			break;
		case "fScreen":
			this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
			break;
		case "sWindow":
			this.setSize(this.getPreferredSize());
			break;
		case "combo":
			break;
		case "picButton":
			try {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File("Saved Show Pictures"));
				int open = file.showOpenDialog(this);
				if (open == JFileChooser.APPROVE_OPTION) {
					tempString = file.getSelectedFile().toString();
				}
			} catch (Exception es) {
				es.printStackTrace();
			}
			break;
		}

	}

	public void addShow() {
		icon = new ImageIcon("tv.png");
		Show newShow = new Show();
		JTextField name = new JTextField();
		JTextField description = new JTextField();
		JButton button = new JButton("Set Image");
		button.setActionCommand("picButton");
		button.addActionListener(this);
		button.setSize(50, 10);
		c = new JComboBox();

		for (int i = 0; i < seasonNumber.length; i++) {
			c.addItem(seasonNumber[i]);
		}

		Object[] message = { "Name:", name, "Description:", description, "Number of Seasons:", c, "Show Image:",
				button };
		int option = JOptionPane.showConfirmDialog(this, message, "New Show", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.OK_CANCEL_OPTION, icon);
		if (option == JOptionPane.OK_OPTION) {
			newShow.setName(name.getText());
			newShow.setDescription(description.getText());
			newShow.setSeasons(c.getSelectedIndex() + 1);
			newShow.setImage(tempString);
			c.addActionListener(this);
			c.setActionCommand("combo");
			showList.add(newShow);
			showCount = new Show[showList.size()];
			showCount = showList.toArray();
			list.setListData(showCount);
		}

		listScroller.repaint();

		// http://www.java2s.com/Code/Java/Swing-JFC/Usingdropdownlists.htm

	}

	public void saveFile() {
		String sb = "TEST CONTENT";
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Saved Shows"));
		int retrival = chooser.showSaveDialog(this);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				outStream = new FileOutputStream(chooser.getSelectedFile());
				outFile = new ObjectOutputStream(outStream);
				for (int i = showList.size() - 1; i >= 0; i--) {
					outFile.writeObject(showList.get(i));
				}
			} catch (Exception eq) {
				System.out.println("Exception: " + eq.getMessage());
				eq.printStackTrace();
			}
		}
	}

	public void openFile() {
		Show object;
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("Saved Shows"));
		int open = chooser.showOpenDialog(this);
		if (open == JFileChooser.APPROVE_OPTION) {
			showList.clear();
			repaint();
			try {
				inStream = new FileInputStream(chooser.getSelectedFile());
				inFile = new ObjectInputStream(inStream);
				while (inStream.available() > 0) {
					object = (Show) inFile.readObject();
					showList.add(object);
				}
				showCount = new Show[showList.size()];
				showCount = showList.toArray();
				list.setListData(showCount);
			} catch (Exception es) {
				System.out.println("Cannot Retrieve File: " + es.getMessage());
			}
		}
	}

	public void removeShow() {
		showList.remove(list.getSelectedIndex());
		showCount = new Show[showList.size()];
		showCount = showList.toArray();
		list.setListData(showCount);
	}

	public void getInfo() {
		Show tempShow = showList.get(list.getSelectedIndex());
		if (tempShow.getImage() != null) {
			img = new File(tempShow.getImage());
			try {
				System.out.println(img.toString());
				newImage = ImageIO.read(img);
				icon = new ImageIcon(newImage.getScaledInstance(250, 175, Image.SCALE_SMOOTH));
			} catch (IOException esq) {
				JOptionPane.showMessageDialog(this, "No Image Avalable!");;
			}
		}
		Object[] message = { "Name:", tempShow.getName(), "Description:", tempShow.getDescription(),
				"Number of Seasons:", tempShow.getSeasons() };
		int option = JOptionPane.showConfirmDialog(this, message, "New Show", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.OK_CANCEL_OPTION, icon);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
