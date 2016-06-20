package source;

import java.awt.Desktop;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.event.ActionEvent;

/** 
 * Class that extends JDialog to Create Login GUI.
 * @author Caleb Hayward
 */
public class LogIn extends JDialog {

	/** Private Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** Public JPanel for Loghing in GUI. */
	public  JPanel contentPanel = new JPanel();
	
	/** Private Field for the Password. */
	private JPasswordField passwordField;
	
	/** Private Field for the UserName. */
	private JTextField userNameField;
	
	/** Private Static Field that controls the Pictured Background. */
	private static JLabel lblBackdrop;
	
	/**
	 * Creates the Login GUI and contains the action Listeners.
	 * @param a is the API Functions Class that contains information
	 * of the parent GUI
	 */
	public LogIn(final ApiFunctions a) {
		setBounds(500, 200, 352, 148);
		getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(207, 61, 105, 20);
	
		userNameField = new JTextField();
		userNameField.setBounds(207, 26, 107, 20);
		userNameField.setColumns(10);
	
		JLabel label = new JLabel("Username");
		label.setBounds(207, 11, 80, 14);
	
		JLabel label1 = new JLabel("Password");
		label1.setBounds(207, 46, 80, 14);
		
		JButton btnCtnAsGuest = new JButton("Continue As Guest");
		btnCtnAsGuest.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				a.setUserName("guest");
				a.setPassword("guest");
				dispose();
			}
		});
		btnCtnAsGuest.setBounds(10, 64, 187, 23);
		
		JButton button1 = new JButton("Sign In");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				String username = new String(userNameField.getText());
				char[] temp = passwordField.getPassword();
				String password = new String(temp);
				a.setUserName(username);
				a.setPassword(password);
				dispose();
				//Function for Signing in
			}
		});
		button1.setBounds(108, 12, 89, 23);
		
		JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				String url = "https://www.themoviedb.org/account/signup";
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(url));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Function for registering
			}
		});
		button.setBounds(10, 12, 88, 23);
		
		getContentPane().add(button1);
		getContentPane().add(button);
		getContentPane().add(btnCtnAsGuest);
		getContentPane().add(label1);
		getContentPane().add(label);
		getContentPane().add(userNameField);
		getContentPane().add(passwordField);
		
		try {
			setBackdrop("http://www.ipages.am/files/companies/1476/"
						+ "template/bg/Blue-movie-film-strip-backgrounds.jpg");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblBackdrop.setBounds(0, 0, 352, 148);
		getContentPane().add(lblBackdrop);
		
		setAlwaysOnTop(true);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	/**
	 * Method that Sets the Background based on a HTML string. 
	 * @param backdropPath The Path to the Image that will be loaded
	 * @throws IOException I/O exception
	 */
	public static void setBackdrop(final String backdropPath)
			throws IOException {
		URL backdropURL = new URL(backdropPath);
		
		BufferedImage backdrop = ImageIO.read(backdropURL);
	    
		RescaleOp rescaleOp = new RescaleOp(1.2f, 150, null);
		rescaleOp.filter(backdrop, backdrop);
		
		Image scaledBackdrop = backdrop.getScaledInstance(
				352, 148, Image.SCALE_SMOOTH);
		
	    lblBackdrop = new JLabel();
	    lblBackdrop.setIcon(new ImageIcon(scaledBackdrop));
	}
}
