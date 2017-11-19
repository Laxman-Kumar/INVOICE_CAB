package cab.invoice.generator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import connection.DatabaseConnection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyDetails extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DatabaseConnection db = new DatabaseConnection();

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfPAN;
	private JTextField tfDD;
	private JTextArea textArea;
	private JButton okButton;
	private JButton cancelButton;
	private static CompanyDetails dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new CompanyDetails();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CompanyDetails() {
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				try{
					
					Connection con = db.createConnection();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from company");
					while(rs.next()){
						tfName.setText(rs.getString(1));
						textArea.setText(rs.getString(2));
						tfPAN.setText(rs.getString(3));
						tfDD.setText(rs.getString(4));
					
					
					
					}
					con.close();
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		setTitle("Modify Company Details");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCompanyName = new JLabel("Company Name");
			lblCompanyName.setBounds(10, 28, 94, 14);
			contentPanel.add(lblCompanyName);
		}
		{
			tfName = new JTextField();
			tfName.setBounds(114, 25, 310, 20);
			contentPanel.add(tfName);
			tfName.setColumns(10);
		}
		{
			JLabel lblAddress = new JLabel("Address");
			lblAddress.setBounds(10, 53, 94, 14);
			contentPanel.add(lblAddress);
		}
		{
			textArea = new JTextArea();
			textArea.setBounds(114, 56, 310, 49);
			contentPanel.add(textArea);
		}
		{
			JLabel lblPanNumber = new JLabel("PAN Number");
			lblPanNumber.setBounds(10, 127, 94, 14);
			contentPanel.add(lblPanNumber);
		}
		{
			tfPAN = new JTextField();
			tfPAN.setBounds(114, 124, 310, 20);
			contentPanel.add(tfPAN);
			tfPAN.setColumns(10);
		}
		{
			JLabel lblDdName = new JLabel("DD Name");
			lblDdName.setBounds(10, 166, 94, 14);
			contentPanel.add(lblDdName);
		}
		{
			tfDD = new JTextField();
			tfDD.setBounds(114, 163, 310, 20);
			contentPanel.add(tfDD);
			tfDD.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					
						
						try{
							Connection con  = db.createConnection();
							PreparedStatement ps = con.prepareStatement("update company set compname=?,compaddr=?,comppan=?,compddname=?");
							ps.setString(1, tfName.getText());
							ps.setString(2, textArea.getText());
							ps.setString(3, tfPAN.getText());
							ps.setString(4, tfDD.getText());
							int i = ps.executeUpdate();
							if(i>0)
								JOptionPane.showMessageDialog(okButton, "Company Information Modified Successfully!");
							else
								JOptionPane.showMessageDialog(okButton, "Error!");
							con.close();
							
						
						
						
						}catch(Exception e){
							JOptionPane.showMessageDialog(okButton, e.getMessage());
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dialog.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
