import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;

/**
 * @author Jack Protivnak This program is designed to enable a user to enter
 *         different TV Shows they watch and keep track of how many seasons
 *         there are. The user can enter the name, a description, an image, and
 *         how many seasons there are for the show. They can edit the show,
 *         create a new show, get info about the show, and remove the show from
 *         the list.
 */
public class Main extends JFrame implements ActionListener, Serializable {
	public static final long serialVersionUID = 1;
	private JMenu TVTuner;
	private JMenu file;
	private JMenu view;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JPanel options;
	private JButton button;
	private JScrollPane listScroller;
	private ArrayList<Show> showList = new ArrayList<Show>();
	private Font f1 = new Font("Dialog", Font.BOLD, 14);
	private File img = new File("imagen.png");
	private BufferedImage newImage;
	private Icon icon;
	private JComboBox<Integer> c = new JComboBox<Integer>();
	private JFileChooser chooser;
	private FileOutputStream outStream;
	private ObjectOutputStream outFile;
	private FileInputStream inStream;
	private ObjectInputStream inFile;
	private String tempString;
	private boolean save;
	private JTable table;
	private DefaultTableModel tModel;

	/**
	 * Constructor contains general layout of the GUI and sets the framework for
	 * the program.
	 */
	public Main() {
		super();
		this.setLayout(new GridLayout(2, 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				while (!save) {
					int option = JOptionPane.showConfirmDialog(null,
							"You have unsaved work!\nWould you like to save you work?", tempString,
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						save = true;
						saveFile();
						System.exit(-1);
					} else {
						System.exit(-1);
					}
				}
			}
		});

		for (int i = 0; i < 20; i++)
			c.addItem(i + 1);

		icon = new ImageIcon("tv.png");
		save = true;

		menuBar = new JMenuBar();

		tModel = new DefaultTableModel();
		String[] columnNames = { "Show Name", "Description", "Seasons" };
		tModel.setColumnIdentifiers(columnNames);
		table = new JTable(tModel);
		listScroller = new JScrollPane(table);
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
		menuItem = new JMenuItem("Clear");
		menuItem.setActionCommand("clear");
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

		this.setJMenuBar(menuBar);
		this.add(options);

		this.setResizable(true);
		this.getPreferredSize();
		this.pack();
		this.setVisible(true);
	}

	/**
	 * ActionListener for the program will take direct the flow of traffic for
	 * each action that is performed.
	 */
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
			editShow();
			break;
		case "about":
			JOptionPane.showMessageDialog(this,
					"TVTUNER\u2122\nCopyright \u00A92016 Pro Inc. All Rights Reserved \u00AE. License Agreement");
			break;
		case "license":
			JOptionPane.showMessageDialog(this,
					"Copyright (\u00A9) 2016, 1998 Pro Inc.\n800 Montana Ave., Natrona Heights, PA  15065, USA\nEveryone is permitted to copy and distribute verbatim copies\nof this license document, but changing it is not allowed.");
			break;
		case "quit":
			while (!save) {
				int option = JOptionPane.showConfirmDialog(this,
						"You have unsaved work!\nWould you like to save you work?", tempString,
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					save = true;
					saveFile();
					System.exit(-1);
				} else {
					System.exit(-1);
				}
			}
			break;
		case "clear":
			save = false;
			tModel.setRowCount(0);
			showList = new ArrayList<Show>();
			table.repaint();
			listScroller.repaint();
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

	/**
	 * When adding a show, the user will have their choice of giving a name,
	 * description, total number of seasons, and an image.
	 */
	public void addShow() {
		icon = new ImageIcon("tv.png");
		Show newShow = new Show();
		JTextField name = new JTextField();
		JTextField description = new JTextField();
		JButton button = new JButton("Set Image");
		button.setActionCommand("picButton");
		button.addActionListener(this);
		button.setSize(50, 10);
		c.setSelectedIndex(0);
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
			String[] tempArray = new String[3];
			tempArray[0] = newShow.getName();
			tempArray[1] = newShow.getDescription();
			tempArray[2] = Integer.toString(newShow.getSeasons());
			tModel.addRow(tempArray);
			save = false;
		}
		listScroller.repaint();
	}

	/**
	 * Called when saving a file and will write the show list to disk.
	 */
	public void saveFile() {
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
				save = true;
			} catch (Exception eq) {
				System.out.println("Exception: " + eq.getMessage());
				eq.printStackTrace();
			} finally {
				try {
					outFile.close();
					outStream.close();
				} catch (IOException eas) {
					eas.printStackTrace();
				}
			}
		}
	}

	/**
	 * Called to open a file and read the information into the showList
	 * ArrayList for all of the shows. The DefaultTableModel that holds all of
	 * the shows is updated so the can be displayed correctly.
	 */
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
				tModel.setRowCount(0);
				for (int i = showList.size() - 1; i >= 0; i--) {
					String[] tempArray = new String[3];
					tempArray[0] = showList.get(i).getName();
					tempArray[1] = showList.get(i).getDescription();
					tempArray[2] = Integer.toString(showList.get(i).getSeasons());
					tModel.addRow(tempArray);
				}
				table.repaint();
				listScroller.repaint();
				save = true;
			} catch (Exception es) {
				System.out.println("Cannot Retrieve File: " + es.getMessage());
			} finally {
				try {
					inStream.close();
					inFile.close();
				} catch (Exception wer) {
					wer.printStackTrace();
				}
			}
		}
	}

	/**
	 * Called to remove a show from the list that is displayed.
	 */
	public void removeShow() {
		if (table.getSelectedRow() != -1) {
			showList.remove(table.getSelectedRow());
			tModel.setRowCount(0);
			for (int i = 0; i < showList.size(); i++) {
				String[] tempArray = new String[3];
				tempArray[0] = showList.get(i).getName();
				tempArray[1] = showList.get(i).getDescription();
				tempArray[2] = Integer.toString(showList.get(i).getSeasons());
				tModel.addRow(tempArray);
			}
			save = false;
			table.repaint();
			listScroller.repaint();
		}
	}

	/**
	 * Called to get information about a show based on the user's choice from
	 * the list.
	 */
	public void getInfo() {
		if (table.getSelectedRow() != -1) {
			Show tempShow = showList.get(table.getSelectedRow());
			if (tempShow.getImage() != null) {
				img = new File(tempShow.getImage());
				try {
					newImage = ImageIO.read(img);
					icon = new ImageIcon(newImage.getScaledInstance(250, 175, Image.SCALE_SMOOTH));
				} catch (IOException esq) {
					JOptionPane.showMessageDialog(this, "No Image Avalable!");
					;
				}
			}
			Object[] message = { "Name:", tempShow.getName(), "Description:", tempShow.getDescription(),
					"Number of Seasons:", tempShow.getSeasons() };
			JOptionPane.showMessageDialog(this, message, "New Show",JOptionPane.INFORMATION_MESSAGE, icon);
		}

	}

	/**
	 * Called to edit a show based on the selection on the JTable by the user.
	 */
	public void editShow() {
		if (table.getSelectedRow() != -1) {
			Show tempShow = showList.get(table.getSelectedRow());
			if (tempShow.getImage() != null) {
				img = new File(tempShow.getImage());
				try {
					newImage = ImageIO.read(img);
					icon = new ImageIcon(newImage.getScaledInstance(250, 175, Image.SCALE_SMOOTH));
				} catch (IOException esq) {
					JOptionPane.showMessageDialog(this, "No Image Avalable!");
					icon = new ImageIcon("tv.png");
				}
			}
			Show newShow = new Show();
			JTextField name = new JTextField();
			name.setText(tempShow.getName());
			JTextField description = new JTextField();
			description.setText(tempShow.getDescription());
			JButton button = new JButton("Set Image");
			button.setActionCommand("picButton");
			button.addActionListener(this);
			button.setSize(50, 10);
			c.setSelectedItem(tempShow.getSeasons());
			Object[] message = { "Name:", name, "Description:", description, "Number of Seasons:", c, "Show Image:",
					button };
			int option = JOptionPane.showConfirmDialog(this, message, tempShow.getName(), JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.OK_CANCEL_OPTION, icon);
			if (option == JOptionPane.OK_OPTION) {
				newShow.setName(name.getText());
				newShow.setDescription(description.getText());
				newShow.setSeasons(c.getSelectedIndex() + 1);
				newShow.setImage(tempString);
				c.addActionListener(this);
				c.setActionCommand("combo");
				showList.set(table.getSelectedRow(), newShow);
				tModel.setRowCount(0);
				for (int i = 0; i < showList.size(); i++) {
					String[] tempArray = new String[3];
					tempArray[0] = showList.get(i).getName();
					tempArray[1] = showList.get(i).getDescription();
					tempArray[2] = Integer.toString(showList.get(i).getSeasons());
					tModel.addRow(tempArray);
				}
			}
			save = false;
			table.repaint();
			listScroller.repaint();
		}
	}

	/**
	 * Program main.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
}