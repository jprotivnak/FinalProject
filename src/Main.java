import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.sound.sampled.*;
import javax.swing.border.EtchedBorder;

/**
 * @author SJHSStudent
 *
 */
public class Main extends JFrame implements ActionListener, Serializable {
	public static final long serialVersionUID = 1;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	
	
	public Main() {
		super();
        this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setResizable(true);
        
        menuItem = new JMenuItem( "License Agreement" );
        menuItem.setActionCommand( "license" );
        menuItem.addActionListener( this );
        
        menu.add( menuItem );
        
        menuBar.add( menu );
        
        
        this.setJMenuBar( menuBar );
		
        this.setVisible( true );
	}
	
	
	
	public void actionPerformed( ActionEvent e ) {
		
	}
	
	
	
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
