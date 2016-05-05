import javax.swing.*;
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
	private JMenu TVTech;
	private JMenu file;
	private JMenu view;
	private JMenu subMenu;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JTextField shows;
	private JPanel options;
	private Font f1 = new Font("Dialog", Font.BOLD,14);

	public Main() {
		super();
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		shows = new JTextField();
		this.add(shows, BorderLayout.EAST);
		//this.add(options, BorderLayout.WEST);

		TVTech = new JMenu("Tv Tech");
		
		menuItem = new JMenuItem("Preferences");
		menuItem.setActionCommand("preferences");
		menuItem.addActionListener(this);

		TVTech.add(menuItem);

		menuItem = new JMenuItem("About");
		menuItem.setActionCommand("about");
		menuItem.addActionListener(this);
		
		TVTech.add(menuItem);
		
		TVTech.addSeparator();
		
		menuItem = new JMenuItem("Quit");
		menuItem.setActionCommand("quit");
		menuItem.addActionListener(this);
		
		TVTech.add(menuItem);
		
		TVTech.setFont(f1);

		TVTech.add(menuItem);

		menuBar.add(TVTech);

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

		this.setResizable(true);
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		
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
