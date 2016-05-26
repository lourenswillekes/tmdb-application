package source;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	
	private boolean closeStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SearchGUI dialog = new SearchGUI();
			dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SearchGUI() {
		closeStatus = false;
		setBounds(100, 100, 351, 146);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Title Search");
			lblNewLabel.setBounds(10, 21, 86, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(10, 36, 167, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		JButton btnNewButton = new JButton("Advanced Search");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdvancedSearchGUI x = new AdvancedSearchGUI();
				if (x.getCloseStatus() == true) {
					
				}
			}
		});
		btnNewButton.setBounds(187, 40, 138, 23);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton SearchButton = new JButton("Search");
				SearchButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeStatus = true;
						dispose();
						//Create Return of Search
					}
				});
				SearchButton.setActionCommand("OK");
				buttonPane.add(SearchButton);
				getRootPane().setDefaultButton(SearchButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeStatus = true;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public Boolean getCloseStatus() {
		return closeStatus;
		//Status used to determine if everthing passes, it it is ok to
		//enter in the site
	}
}
