package source;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

public class LogIn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LogIn dialog = new LogIn();
			dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LogIn() {
		setBounds(100, 100, 347, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton button = new JButton("Register");
			button.setBounds(10, 12, 88, 23);
			contentPanel.add(button);
		}
		{
			JButton button = new JButton("Sign In");
			button.setBounds(108, 12, 89, 23);
			contentPanel.add(button);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(207, 61, 105, 20);
			contentPanel.add(passwordField);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(207, 26, 107, 20);
			contentPanel.add(textField);
		}
		{
			JLabel label = new JLabel("Username");
			label.setBounds(207, 11, 55, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Password");
			label.setBounds(207, 46, 46, 14);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
