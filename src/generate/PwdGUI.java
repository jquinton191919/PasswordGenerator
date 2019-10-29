package generate;
import javax.swing.JFrame;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
//import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;



public class PwdGUI extends JFrame {
	
	@SuppressWarnings("rawtypes")
	private JComboBox selection;
	private static final long serialVersionUID = -3526368980649420473L;
	
	private JPanel dropDownPanel = new JPanel();
	private JPanel numberPanel = new JPanel();
	private JPanel checkBoxPanel = new JPanel();
	private JPanel adjPanel = new JPanel();
	
	private JLabel numberLabel = new JLabel("Number");
	private JLabel checkBoxLabel = new JLabel(" ");
	private JLabel adjLabel = new JLabel("Adjective (optional)");
	
	private Checkbox checkbox = new Checkbox("", null, false);
	private JPasswordField adjective = new JPasswordField(15);
	private JPasswordField number = new JPasswordField(4);
	private JButton button = new JButton("Generate");
	
	private int attempts = 0;
	
	MyImageIcon[] items =
        {
            new MyImageIcon( new ImageIcon( getClass().getResource( "none.png" ) ), "none" ),
            new MyImageIcon( new ImageIcon( getClass().getResource( "kitten.png" ) ), "kitten" ),
            new MyImageIcon( new ImageIcon( getClass().getResource( "puppy.png" ) ), "puppy" ),
            new MyImageIcon( new ImageIcon( getClass().getResource( "pony.png" ) ), "pony" ),
            new MyImageIcon( new ImageIcon( getClass().getResource( "spider.png" ) ), "spider" ),
            new MyImageIcon( new ImageIcon( getClass().getResource( "bear.png" ) ), "bear" ),
            new MyImageIcon( new ImageIcon( getClass().getResource( "dragon.png" ) ), "dragon" ),
            new MyImageIcon( new ImageIcon( getClass().getResource( "bunny.png" ) ), "bunny" )
        };
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PwdGUI() {
		super("9000 Fluffy Kittens! Password Generator");
		
		dropDownPanel.setPreferredSize(new Dimension(150, 75));
		
		selection = new JComboBox( items );
		MyListCellRenderer render = new MyListCellRenderer();
		selection.setRenderer(render);
		selection.setToolTipText("Select an animal from the drop down");
		dropDownPanel.add(selection);
		
		numberPanel.setPreferredSize(new Dimension(150, 40));
		checkBoxPanel.setPreferredSize(new Dimension(20, 20));
		adjPanel.setPreferredSize(new Dimension(200, 40));
		
		// add listener for the generate button
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
					getPwd();
				}
		});
		button.setToolTipText("Generate a password that will automatically be copied"); 
		
		// add listener for textarea
		adjective.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {
				if ( (int) e.getKeyChar() == 10 ) getPwd();
			}
		});
		
		adjective.setToolTipText("<html>Enter an adjective for the animal. "
				+ "Some systems require special characters "
				+ "</html>");
		
		// listener for number field
		number.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {
				if ( (int) e.getKeyChar() == 10 ) getPwd();
			}
		});
		
		ToolTipManager.sharedInstance().setDismissDelay(10000);
		number.setToolTipText("<html>"
				+ "Enter a number up to 4 digits long. Numbers correspond to special characters in your password. "
				+ "<br>For example, the number 80 will contain the special characters * and ) while the number 56 will contain the special characters % and ^ "
				+ "<br>Click the checkbox if one or more of your special characters was rejected." 
				+ "</html>");
		
		numberPanel.setLayout(new GridLayout(2,0));
		numberPanel.add(numberLabel);
		numberPanel.add(number); 
		
		checkBoxPanel.setLayout(new GridLayout(2,0));
		checkBoxPanel.add(checkBoxLabel);
		checkBoxPanel.add(checkbox);
		checkBoxPanel.setToolTipText("Check this box if one of your special characters was rejected, and try again");
		
		adjPanel.setLayout(new GridLayout(2,0));
		adjPanel.add(adjLabel);
		adjPanel.add(adjective);
		
		
		setLayout( new FlowLayout(FlowLayout.LEFT, 10, 10) );
		
		//icon
		URL imgURL = getClass().getResource("keyicon.png");
		ImageIcon img = new ImageIcon( imgURL );
		setIconImage(img.getImage());
		
		
		getContentPane().add(numberPanel);
		getContentPane().add(checkBoxPanel);
		getContentPane().add(adjPanel);
		getContentPane().add(dropDownPanel);
		getContentPane().add(button);
		
		GUIcontrols.restrictToNumberCharacters( (AbstractDocument) number.getDocument() );
		GUIcontrols.restrictToAlphabetCharacters( (AbstractDocument) adjective.getDocument() );
		
		pack();
		number.grabFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	/******
	 * Gets the user's input password and their algorithm choice and copies the result to the user's clipboard
	 * *********/
	private void getPwd() {
		String input = "", adj = "";
		char[] pwd = number.getPassword();
		String animal = selection.getSelectedItem().toString();
		
		if(animal.equalsIgnoreCase("none")) {
			JOptionPane.showMessageDialog(this, "Select an animal from the drop down menu", "WARNING", JOptionPane.WARNING_MESSAGE);
		}
		
		else if(pwd.length > 0) {
			for(int i=0; i<pwd.length; i++) input += pwd[i];
			
			char [] a = adjective.getPassword();
			for(int i=0; i<a.length; i++) adj += a[i];
						
			String output = (a.length > 0) ? Pwd.generate(input, animal, adj, checkbox.getState()) : Pwd.generate(input, animal, checkbox.getState());
			
			if( (input.equalsIgnoreCase("9000")) && (adj.equalsIgnoreCase("fluffy")) ) {
				attempts++;
				if(attempts <= 3)
					JOptionPane.showMessageDialog(this, "You probably shouldn't use the title of this app as your password...", "WARNING", JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(this, "Do not use the title of this app as your password!!!!!!!!!!!".toUpperCase(), 
							"MAKER'S BREATH!!!!!!!!!!!!!!!!!!!!!!!", JOptionPane.WARNING_MESSAGE);
				
			}
				
			else {
				StringSelection selection = new StringSelection(output);
			    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    clipboard.setContents(selection, selection);

				JOptionPane.showMessageDialog(this, "Password copied to clipboard! "
						+ "\nPaste your new password by utilizing your system's paste command, or by pressing Ctr+v or Cmd+v", "New Password!", JOptionPane.INFORMATION_MESSAGE);
			}
			number.setText("");
			adjective.setText(""); 
			number.grabFocus();
		}
		else JOptionPane.showMessageDialog(this, "Number field is not optional", "WARNING", JOptionPane.WARNING_MESSAGE);
		
	}
	
	/*****
	 * Private ImageIcon class that is modified so that method ImageIcon.toString() returns the image's name
	 * ********/
	private class MyImageIcon extends ImageIcon {
		private static final long serialVersionUID = 1L;
		String name;
		public MyImageIcon(ImageIcon i, String n){			
			super(i.getImage());
			i.setDescription(n);
			name = n;
		}
		@Override
	    public String toString() { return name; }
	}
	
	/*****
	 * Private DefaultListCellRenderer class to manage the color of selected items in the JCombobox
	 * ********/
	private class MyListCellRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = -2663615361801381804L;
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	    @Override
	    public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value,
	            int index, boolean isSelected, boolean cellHasFocus) {
	        Component c = defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        
	        if (c instanceof JLabel) { 
	            if (isSelected) {
	                // do nothing
	            } 
	            else c.setBackground(Color.WHITE);
	            
	        } else {
	            c.setBackground(Color.WHITE);
	            c = super.getListCellRendererComponent(
	                    list, value, index, isSelected, cellHasFocus);
	        }
	        return c;
	    }
	}
	
	
	public static void main(String [] args) {
		if(args.length > 0){
			String animal = "kitten";
			System.out.println("Input: " + args[0] );
			if(args.length > 1) {
				switch( args[1].toUpperCase() ) {
				case "KITTEN":
					animal = "kitten";
					break;
				case "PONY":
					animal = "pony";
					break;
				case "SPIDER":
					animal = "spider";
					break;
				default:
					animal = "dragon";
					break;
				}
			}
			
			System.out.println("Password generated: "+ Pwd.generate( args[0], animal, false ) );
		}
		
		else {
			// it's GUI time!!!
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new PwdGUI().setVisible(true);
				}
			});
		}
		}
	
	class DocumentSizeFilter extends DocumentFilter {
	    

	}

}
