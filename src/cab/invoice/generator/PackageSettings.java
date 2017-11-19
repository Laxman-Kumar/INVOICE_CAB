package cab.invoice.generator;

import connection.DatabaseConnection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class PackageSettings extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DatabaseConnection db = new DatabaseConnection();

	private JPanel contentPane;
	private JTextField textField_PID;
	private JTextField textField_Kms;
	private JTextField textField_Hours;
	private JComboBox comboBox_PID;
	private JTextArea textAreaPackage;
	private final ButtonGroup buttonGroupPackages = new ButtonGroup();
	private JRadioButton rdbtnAdd;
	private JRadioButton rdbtnEdit;
	private JRadioButton rdbtnRemove;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnReset;
	private JRadioButton rdbtnAdd_1;
	private JRadioButton rdbtnRemove_1;
	private JRadioButton rdbtnEdit_1;
	private JLabel lblCarNumber;
	private JTextField tfCarNumber;
	private JLabel lblChooseACab;
	private JComboBox cbCab;
	private JLabel lblCarModel;
	private JTextField tfCarModel;
	private JLabel lblSlabRate;
	private JTextField tfSlab1;
	private JLabel lblRskmsUpto;
	private final ButtonGroup buttonGroupCabs = new ButtonGroup();
	private JButton btnAdd_1;
	private JButton btnEdit_1;
	private JButton btnRemove_1;
	private JButton btnReset_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PackageSettings frame = new PackageSettings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PackageSettings() {
		
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 720, 560);
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEtchedBorder(Color.green, Color.blue));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(50, 65, 584, 407);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setFont(new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		contentPane.add(tabbedPane);
		
		JPanel panelPackage = new JPanel();
		
		tabbedPane.addTab("Packages", null, panelPackage, "Add, Edit or Remove the Rent-a-Cab package.");
		tabbedPane.setEnabledAt(0, true);
		panelPackage.setLayout(null);
		panelPackage.setBackground(Color.GRAY);
		JLabel lblPackageId = new JLabel("Package ID");
		lblPackageId.setBounds(10, 38, 83, 25);
		panelPackage.add(lblPackageId);
		
		textField_PID = new JTextField();
		textField_PID.setEditable(false);
		textField_PID.setBounds(140, 40, 90, 20);
		panelPackage.add(textField_PID);
		textField_PID.setColumns(10);
		
		JLabel lblForKms = new JLabel("For Kms");
		lblForKms.setBounds(10, 74, 83, 25);
		panelPackage.add(lblForKms);
		
		textField_Kms = new JTextField();
		textField_Kms.setEditable(false);
		textField_Kms.setBounds(140, 76, 120, 20);
		panelPackage.add(textField_Kms);
		textField_Kms.setColumns(10);
		
		JLabel lblForHours = new JLabel("For Hours");
		lblForHours.setBounds(10, 110, 83, 25);
		panelPackage.add(lblForHours);
		
		textField_Hours = new JTextField();
		textField_Hours.setEditable(false);
		textField_Hours.setBounds(140, 112, 120, 20);
		panelPackage.add(textField_Hours);
		textField_Hours.setColumns(10);
		
		JLabel lblPackageName = new JLabel("Package Detail");
		lblPackageName.setBounds(10, 146, 120, 25);
		panelPackage.add(lblPackageName);
		
		textAreaPackage = new JTextArea();
		textAreaPackage.setLineWrap(true);
		textAreaPackage.setWrapStyleWord(true);
		textAreaPackage.setEditable(false);
		textAreaPackage.setBounds(140, 143, 290, 100);
		panelPackage.add(textAreaPackage);
		
		rdbtnAdd = new JRadioButton("Add");
		rdbtnAdd.setBackground(Color.GRAY);
		rdbtnAdd.setForeground(Color.ORANGE);
		rdbtnAdd.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		buttonGroupPackages.add(rdbtnAdd);
		rdbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox_PID.setEnabled(false);
				textField_Hours.setEditable(true);
				textField_Kms.setEditable(true);
				textField_PID.setEditable(true);
				textAreaPackage.setEditable(true);
				btnAdd.setEnabled(true);
				btnRemove.setEnabled(false);
				btnEdit.setEnabled(false);
				btnReset.setEnabled(true);
			}
		});
		rdbtnAdd.setBounds(140, 7, 109, 23);
		panelPackage.add(rdbtnAdd);
		
		rdbtnEdit = new JRadioButton("Edit");
		rdbtnEdit.setBackground(Color.GRAY);
		rdbtnEdit.setForeground(Color.ORANGE);
		rdbtnEdit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		
		rdbtnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox_PID.setEnabled(true);
				textField_PID.setEditable(false);
				textField_Hours.setEditable(true);
				textField_Kms.setEditable(true);
				textAreaPackage.setEditable(true);
				btnAdd.setEnabled(false);
				btnRemove.setEnabled(false);
				btnReset.setEnabled(true);
				btnEdit.setEnabled(true);
			}
		});
		buttonGroupPackages.add(rdbtnEdit);
		rdbtnEdit.setBounds(251, 7, 109, 23);
		panelPackage.add(rdbtnEdit);
		
		JLabel lblChoosePackageId = new JLabel("Choose Package ID");
		lblChoosePackageId.setBounds(255, 43, 135, 14);
		panelPackage.add(lblChoosePackageId);
		
		comboBox_PID = new JComboBox();
		comboBox_PID.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String selectedPID = comboBox_PID.getSelectedItem().toString();
				try{
					
					
					Connection con = db.createConnection();
					PreparedStatement statement = con.prepareStatement("select * from packages where packname=?");
					statement.setString(1,selectedPID);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						textField_PID.setText(rs.getString(1));
						textField_Kms.setText(Integer.toString(rs.getInt(3)));
						textField_Hours.setText(Integer.toString(rs.getInt(2)));
						textAreaPackage.setText(rs.getString(4));
					}
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(comboBox_PID, e.getMessage());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		comboBox_PID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try{
					Connection con = db.createConnection();
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select packname from packages");
					comboBox_PID.removeAllItems();			//remove previous list of items before updating the list with new items.
					while(rs.next()){
						comboBox_PID.addItem(rs.getString("packname"));
					}
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(comboBox_PID,e.getMessage());
				}
			}
		});
		comboBox_PID.setEnabled(false);
		comboBox_PID.setBounds(382, 40, 90, 20);
		panelPackage.add(comboBox_PID);
		
		rdbtnRemove = new JRadioButton("Remove");
		rdbtnRemove.setBackground(Color.GRAY);
		rdbtnRemove.setForeground(Color.ORANGE);
		rdbtnRemove.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		rdbtnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox_PID.setEnabled(true);
				textField_PID.setEditable(false);
				textField_Hours.setEditable(false);
				textField_Kms.setEditable(false);
				textAreaPackage.setEditable(false);
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnReset.setEnabled(false);
				btnRemove.setEnabled(true);
			}
		});
		buttonGroupPackages.add(rdbtnRemove);
		rdbtnRemove.setBounds(364, 7, 109, 23);
		panelPackage.add(rdbtnRemove);
		
		btnAdd = new JButton("ADD");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection con  = db.createConnection();
					PreparedStatement st = con.prepareStatement("insert into packages values(?,?,?,?)");
					st.setString(1,textField_PID.getText());
					st.setInt(2,Integer.parseInt(textField_Hours.getText()));
					st.setInt(3,Integer.parseInt(textField_Kms.getText()));
					st.setString(4,textAreaPackage.getText());
					int status = st.executeUpdate();
					if(status>0)
						JOptionPane.showMessageDialog(btnAdd, "Package Added Successfully");
					else
						JOptionPane.showMessageDialog(btnAdd, "Package Not Added! Try Again");
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(btnAdd,e.getMessage());
				}
			}
		});
		btnAdd.setBounds(10, 268, 89, 23);
		panelPackage.add(btnAdd);
		
		btnEdit = new JButton("SAVE");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Connection con = db.createConnection();
					String insertStatement = "update packages set hours=?,kms=?,packdesc=? where packname=?";
					PreparedStatement st = con.prepareStatement(insertStatement);
					st.setInt(1,Integer.parseInt(textField_Hours.getText()));
					st.setInt(2,Integer.parseInt(textField_Kms.getText()));
					st.setString(3,textAreaPackage.getText());
					st.setString(4,comboBox_PID.getSelectedItem().toString());
					int status = st.executeUpdate();
					if(status>0)
						JOptionPane.showMessageDialog(btnEdit,comboBox_PID.getSelectedItem().toString()+" Package Updated Successfully");
					else
						JOptionPane.showMessageDialog(btnEdit,"Not Inserted, Try Again!");
					con.close();
				}catch(Exception e){
					JOptionPane.showMessageDialog(btnEdit, e.getMessage());
				}
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(140, 268, 89, 23);
		panelPackage.add(btnEdit);
		
		btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox_PID.getSelectedItem().toString().equals("")){
					JOptionPane.showConfirmDialog(btnRemove, "Choose a Package to delete");
				}
				else{
					try{
						Connection con  = db.createConnection();
						PreparedStatement st = con.prepareStatement("delete from packages where packname=?");
						st.setString(1,comboBox_PID.getSelectedItem().toString());
						int c = st.executeUpdate();
						if(c>0)
							JOptionPane.showMessageDialog(btnRemove, "Package Removed Successfully");
						else
							JOptionPane.showMessageDialog(btnRemove, "Could not be removed! Try Again.");
						con.close();
					}catch(Exception ee){
						JOptionPane.showMessageDialog(btnRemove, ee.getMessage());
					}
				}
			}
		});
		btnRemove.setEnabled(false);
		btnRemove.setBounds(251, 268, 89, 23);
		panelPackage.add(btnRemove);
		
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_Hours.setText("");
				textField_Kms.setText("");
				textAreaPackage.setText("");
			}
		});
		btnReset.setEnabled(false);
		btnReset.setBounds(362, 268, 89, 23);
		panelPackage.add(btnReset);
		
		JPanel panelCabs = new JPanel();
		tabbedPane.addTab("Cabs", null, panelCabs, "Settigs for Cab");
		tabbedPane.setEnabledAt(1, true);
		panelCabs.setLayout(null);
		
		rdbtnAdd_1 = new JRadioButton("ADD");
		rdbtnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cbCab.setEnabled(false);
				tfCarModel.setEditable(true);
				tfCarNumber.setEditable(true);
				tfSlab1.setEditable(true);
				
				btnAdd_1.setEnabled(true);
				btnEdit_1.setEnabled(false);
				btnRemove_1.setEnabled(false);
				btnReset_1.setEnabled(true);
			}
		});
		buttonGroupCabs.add(rdbtnAdd_1);
		rdbtnAdd_1.setBounds(78, 28, 109, 23);
		panelCabs.add(rdbtnAdd_1);
		
		rdbtnRemove_1 = new JRadioButton("REMOVE");
		rdbtnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAdd_1.setEnabled(false);
				btnEdit_1.setEnabled(false);
				btnReset_1.setEnabled(false);
				btnRemove_1.setEnabled(true);
				cbCab.setEnabled(true);
				tfCarModel.setEditable(false);
				tfCarNumber.setEditable(false);
				tfSlab1.setEditable(false);
				
			}
		});
		buttonGroupCabs.add(rdbtnRemove_1);
		rdbtnRemove_1.setBounds(300, 28, 109, 23);
		panelCabs.add(rdbtnRemove_1);
		
		rdbtnEdit_1 = new JRadioButton("EDIT");
		rdbtnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cbCab.setEnabled(true);
				tfCarModel.setEditable(true);
				tfCarNumber.setEditable(false);
				tfSlab1.setEditable(true);
				
				btnAdd_1.setEnabled(false);
				btnEdit_1.setEnabled(true);
				btnRemove_1.setEnabled(false);
				btnReset_1.setEnabled(true);
			}
		});
		buttonGroupCabs.add(rdbtnEdit_1);
		rdbtnEdit_1.setBounds(189, 28, 109, 23);
		panelCabs.add(rdbtnEdit_1);
		
		lblCarNumber = new JLabel("Cab Number");
		lblCarNumber.setBounds(25, 83, 90, 23);
		panelCabs.add(lblCarNumber);
		
		tfCarNumber = new JTextField();
		tfCarNumber.setEditable(false);
		tfCarNumber.setBounds(125, 84, 120, 20);
		panelCabs.add(tfCarNumber);
		tfCarNumber.setColumns(10);
		
		lblChooseACab = new JLabel("Choose a Cab");
		lblChooseACab.setBounds(300, 83, 90, 23);
		panelCabs.add(lblChooseACab);
		
		cbCab = new JComboBox();
		cbCab.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String selectedItem = cbCab.getSelectedItem().toString();
				try{
					Connection con = db.createConnection();
					PreparedStatement statement = con.prepareStatement("select * from cabs where car_number=?");
					statement.setString(1,selectedItem);
					ResultSet rs = statement.executeQuery();
					while(rs.next()){
						tfCarNumber.setText(rs.getString(2));
						tfCarModel.setText(rs.getString(1));
						tfSlab1.setText(Double.toString(rs.getDouble(3)));
						
					}
					con.close();
				}catch(Exception eee){
					JOptionPane.showConfirmDialog(cbCab, eee.getMessage());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		cbCab.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				try{
					Connection con  = db.createConnection();
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select car_number from cabs");
					cbCab.removeAllItems();			//remove previous list of items before updating the list with new items.
					while(rs.next()){
						cbCab.addItem(rs.getString("car_number"));
					}
					con.close();
				}catch(Exception ee){
					JOptionPane.showMessageDialog(cbCab,ee.getCause());
				}
			}
		});
		cbCab.setEnabled(false);
		cbCab.setBounds(400, 84, 120, 20);
		panelCabs.add(cbCab);
		
		lblCarModel = new JLabel("Car Model");
		lblCarModel.setBounds(25, 117, 90, 23);
		panelCabs.add(lblCarModel);
		
		tfCarModel = new JTextField();
		tfCarModel.setEditable(false);
		tfCarModel.setBounds(125, 118, 120, 20);
		panelCabs.add(tfCarModel);
		tfCarModel.setColumns(10);
		
		lblSlabRate = new JLabel("Default Rate");
		lblSlabRate.setBounds(25, 151, 90, 23);
		panelCabs.add(lblSlabRate);
		
		tfSlab1 = new JTextField();
		tfSlab1.setEditable(false);
		tfSlab1.setBounds(125, 152, 120, 20);
		panelCabs.add(tfSlab1);
		tfSlab1.setColumns(10);
		
		lblRskmsUpto = new JLabel("Rs/Km");
		lblRskmsUpto.setBounds(300, 155, 220, 14);
		panelCabs.add(lblRskmsUpto);
		
		btnAdd_1 = new JButton("ADD");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfCarModel.getText().length()<1)
					JOptionPane.showMessageDialog(btnAdd_1,"Enter the Car Model Name!");
				else if(tfCarNumber.getText().equals(""))
					JOptionPane.showMessageDialog(btnAdd_1,"Enter the Car Number!");
				else if(tfSlab1.getText().equals(""))
					JOptionPane.showMessageDialog(btnAdd_1,"Enter the Default Rate!");
				
				else{
					try{
						Connection con = db.createConnection();
						String insertStatement = "insert into cabs values(?,?,?)";
						PreparedStatement st = con.prepareStatement(insertStatement);
						st.setString(1,tfCarModel.getText());
						st.setString(2,tfCarNumber.getText());
						st.setDouble(3,Double.parseDouble(tfSlab1.getText()));
						
						int status = st.executeUpdate();
						if(status>0)
							JOptionPane.showMessageDialog(btnAdd_1, "Cab Added Successfully");
						else
							JOptionPane.showMessageDialog(btnAdd_1,"Not Inserted, Try Again!");
						con.close();
					}catch(Exception e){
						JOptionPane.showMessageDialog(btnAdd_1, e.getMessage());
					}
				}
			}
		});
		btnAdd_1.setEnabled(false);
		btnAdd_1.setBounds(25, 281, 89, 23);
		panelCabs.add(btnAdd_1);
		
		btnEdit_1 = new JButton("UPDATE");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfCarModel.getText().equals(""))
					JOptionPane.showMessageDialog(btnEdit_1,"Enter the Car Model!");
				else if(tfSlab1.getText().equals(""))
					JOptionPane.showMessageDialog(btnEdit_1,"Enter the Slab 1 Rate!");
				
				else{
					try{
						Connection con = db.createConnection();
						String insertStatement = "update cabs set car_model=?,slab_1=? where car_number=?";
						PreparedStatement st = con.prepareStatement(insertStatement);
						st.setString(1,tfCarModel.getText());
						st.setDouble(2,Double.parseDouble(tfSlab1.getText()));
						
						st.setString(3,cbCab.getSelectedItem().toString());
						int status = st.executeUpdate();
						if(status>0)
							JOptionPane.showMessageDialog(btnEdit_1,cbCab.getSelectedItem().toString()+" "+tfCarModel.getText()+ " Cab Updated Successfully");
						else
							JOptionPane.showMessageDialog(btnEdit_1,"Not Inserted, Try Again!");
						con.close();
					}catch(Exception e){
						JOptionPane.showMessageDialog(btnEdit_1, e.getMessage());
					}
				}
			}
		});
		btnEdit_1.setEnabled(false);
		btnEdit_1.setBounds(156, 281, 89, 23);
		panelCabs.add(btnEdit_1);
		
		btnRemove_1 = new JButton("REMOVE");
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String toDelete = cbCab.getSelectedItem().toString();
				if(toDelete.equals("")){
					JOptionPane.showMessageDialog(btnRemove_1, "Select a car name to delete");
				}
				else{
					try{
						Connection con  = db.createConnection();
						PreparedStatement st = con.prepareStatement("delete from cabs where car_number=?");
						st.setString(1,toDelete);
						int c = st.executeUpdate();
						if(c>0)
							JOptionPane.showMessageDialog(btnRemove_1, toDelete+" Cab Removed Successfully");
						else
							JOptionPane.showMessageDialog(btnRemove_1, toDelete+" Could not be removed! Try Again.");
						con.close();
					}catch(Exception ee){
						JOptionPane.showMessageDialog(btnRemove_1, ee.getMessage());
					}
				}
			}
		});
		btnRemove_1.setEnabled(false);
		btnRemove_1.setBounds(301, 281, 89, 23);
		panelCabs.add(btnRemove_1);
		
		btnReset_1 = new JButton("RESET");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfCarModel.setText("");
				tfSlab1.setText("");
				
			}
		});
		btnReset_1.setEnabled(false);
		btnReset_1.setBounds(434, 281, 89, 23);
		panelCabs.add(btnReset_1);
		
		JLabel lblPackages = new JLabel("Rent A Cab Settings");
		lblPackages.setForeground(Color.ORANGE);
		
		
		lblPackages.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackages.setFont(new Font(Font.SERIF, Font.BOLD, 28));
		lblPackages.setBounds(10, 0, 674, 62);
		contentPane.add(lblPackages);
	}
}
