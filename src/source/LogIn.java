package source;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField UserNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LogIn dialog = new LogIn(new ApiFunctions());
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LogIn(ApiFunctions a) {
		setBounds(100, 100, 390, 200);
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
			//button.setBounds(10, 12, 88, 23);
			button.setBounds(100, 109, 90, 23);
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
					
					a.setSessionToken(a.getSessionToken());
					
					dispose();
					//Function for Signing in
				}
			});
			button.setBounds(200, 109, 90, 23);
			contentPanel.add(button);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(100, 80, 190, 20);
			contentPanel.add(passwordField);
		}
		{
			UserNameField = new JTextField();
			UserNameField.setBounds(100, 45, 190, 20);
			UserNameField.setColumns(10);
			contentPanel.add(UserNameField);
		}
		{
			JLabel label = new JLabel("Username");
			label.setBounds(100, 30, 80, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Password");
			label.setBounds(100, 65, 80, 14);
			contentPanel.add(label);
		}
		
		
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

}
