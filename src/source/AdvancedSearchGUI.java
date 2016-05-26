package source;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdvancedSearchGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtJustin_1;
	private JTextField txtJustin;
	private boolean closeStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdvancedSearchGUI dialog = new AdvancedSearchGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdvancedSearchGUI() {
		setBounds(100, 100, 292, 299);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Year");
			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label.setBounds(10, 11, 46, 14);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setText("2010\r\n");
			textField.setColumns(10);
			textField.setBounds(10, 29, 86, 20);
			contentPanel.add(textField);
		}
		{
			JLabel label = new JLabel("To");
			label.setBounds(101, 32, 12, 14);
			contentPanel.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setText("CURRENT");
			textField_1.setColumns(10);
			textField_1.setBounds(123, 29, 86, 20);
			contentPanel.add(textField_1);
		}
		{
			JLabel label = new JLabel("Genre");
			label.setBounds(10, 55, 46, 14);
			contentPanel.add(label);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Horror", "Comedy", "Love", "Action"}));
			comboBox.setBounds(10, 74, 103, 20);
			contentPanel.add(comboBox);
		}
		{
			JLabel label = new JLabel("Rating");
			label.setBounds(10, 105, 46, 14);
			contentPanel.add(label);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"10/10", "9/10", "8/10", "7/10", "6/10", "5/10", "4/10", "3/10", "2/10", "1/10"}));
			comboBox.setBounds(10, 130, 103, 20);
			contentPanel.add(comboBox);
		}
		{
			JLabel label = new JLabel("Language");
			label.setBounds(10, 161, 63, 14);
			contentPanel.add(label);
		}
		{
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"English", "Spanish", "French"}));
			comboBox.setBounds(10, 186, 103, 20);
			contentPanel.add(comboBox);
		}
		{
			JLabel label = new JLabel("Actor/Actreses");
			label.setBounds(139, 91, 86, 14);
			contentPanel.add(label);
		}
		{
			txtJustin_1 = new JTextField();
			txtJustin_1.setText("Justin");
			txtJustin_1.setColumns(10);
			txtJustin_1.setBounds(139, 115, 86, 20);
			contentPanel.add(txtJustin_1);
		}
		{
			JLabel label = new JLabel("Director");
			label.setBounds(139, 146, 46, 14);
			contentPanel.add(label);
		}
		{
			txtJustin = new JTextField();
			txtJustin.setText("Justin ");
			txtJustin.setColumns(10);
			txtJustin.setBounds(139, 166, 86, 20);
			contentPanel.add(txtJustin);
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
