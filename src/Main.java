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
	private JList list;
	private ArrayList<TVShows> tvShows = new ArrayList<TVShows>();
	private Font f1 = new Font("Dialog", Font.BOLD,14);

	public Main() {
		super();
		this.setLayout(new GridLayout(2,2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		shows = new JTextField();
		this.add(shows, BorderLayout.EAST);
		//this.add(options, BorderLayout.WEST);
		
		list = new JList(tvShows);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		...
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		options = new JPanel();
		options.setLayout(new GridLayout(6, 2));
		options.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Options"));

		TVTuner = new JMenu("Tv Tuner");
		
		menuItem = new JMenuItem("Preferences");
		menuItem.setActionCommand("preferences");
		menuItem.addActionListener(this);

		TVTuner.add(menuItem);

		menuItem = new JMenuItem("About");
		menuItem.setActionCommand("about");
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

		view.setFont(f1);

		menuBar.add(view);

		// subMenu = new JMenu("");

		this.setJMenuBar(menuBar);
		this.add(options);

		this.setResizable(true);
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "preferences":
			break;
		case "about":
			break;
		case "quit":
			break;
		case "new":
			break;
		case "open":
			break;
		case "save":
			break;
		
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
