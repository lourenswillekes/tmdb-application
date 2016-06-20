package source;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** 
 * Class that extends JDialog to Create Login GUI
 * @author Caleb Hayward
 */
public class LogIn extends JDialog {

	/** Private Serial Version UID */
	private static final long serialVersionUID = 1L;
	/** Public JPanel for Loging in GUI */
	public  JPanel contentPanel = new JPanel();
	/** Private Field for the Password */
	private JPasswordField passwordField;
	/** Private Field for the UserName*/
	private JTextField UserNameField;
	
	/**
	 * Creates the Login GUI and contains the action Listeners
	 * @param a is the API Functions Class that contains information
	 * of the parent GUI
	 */
	public LogIn(ApiFunctions a) {
		setBounds(100, 100, 347, 137);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton button = new JButton("Register");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
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
			contentPanel.add(button);
		}
		{
			JButton button = new JButton("Sign In");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String username = new String(UserNameField.getText());
					char[] temp = passwordField.getPassword();
					String password = new String(temp);
					a.setUserName(username);
					a.setPassword(password);
					dispose();
					//Function for Signing in
				}
			});
			button.setBounds(108, 12, 89, 23);
			contentPanel.add(button);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(207, 61, 105, 20);
			contentPanel.add(passwordField);
		}
		{
			UserNameField = new JTextField();
			UserNameField.setBounds(207, 26, 107, 20);
			UserNameField.setColumns(10);
			contentPanel.add(UserNameField);
		}
		{
			JLabel label = new JLabel("Username");
			label.setBounds(207, 11, 80, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Password");
			label.setBounds(207, 46, 80, 14);
			contentPanel.add(label);
		}
		
		JButton btnCtnAsGuest = new JButton("Continue As Guest");
		btnCtnAsGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a.setUserName("guest");
				a.setPassword("guest");
				dispose();
			}
		});
		btnCtnAsGuest.setBounds(10, 64, 187, 23);
		contentPanel.add(btnCtnAsGuest);
		
		setAlwaysOnTop(true);
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
