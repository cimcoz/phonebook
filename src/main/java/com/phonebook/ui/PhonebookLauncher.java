package com.phonebook.ui;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.phonebook.constants.Constants;
import com.phonebook.utils.Utils;

public class PhonebookLauncher extends Utils{

//	Utils utils = new Utils();
	Constants constants = new Constants();
	private JFrame frmPhonebook;
	private JTextField textName;
	private JTextField textMobile;
	private JTextField textEmail;
	private JTable table;
	private static DefaultTableModel model = new DefaultTableModel();
	private static JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhonebookLauncher window = new PhonebookLauncher();
					window.frmPhonebook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public PhonebookLauncher() throws ClassNotFoundException, IOException {
		initialize();
	}

	public TableModel getRecords(){
		return table.getModel();
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, IOException {
		frmPhonebook = new JFrame();
		frmPhonebook.setTitle("PhoneBook");
		frmPhonebook.setBounds(100, 100, 634, 318);
		frmPhonebook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhonebook.getContentPane().setLayout(null);

		table = new JTable();
		table.setBounds(226, 23, 366, 232);
		frmPhonebook.getContentPane().add(table);
		
		Object[] column = {"Name", "Mobile", "Email"};
//		final DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
//		table.setModel(model);
//		model = (DefaultTableModel) utils.deserialize("src/main/resources/phonebookRecords.ser");
		model = (DefaultTableModel) deserialize(constants.PHONEBOOKFILE);
		table.setModel(model);
//		loadDropDown();

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(28, 26, 46, 14);
		frmPhonebook.getContentPane().add(lblName);

		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(28, 57, 46, 14);
		frmPhonebook.getContentPane().add(lblMobile);

		JLabel lblEmailId = new JLabel("Email");
		lblEmailId.setBounds(28, 88, 46, 14);
		frmPhonebook.getContentPane().add(lblEmailId);

		textName = new JTextField();
		textName.setBounds(89, 23, 127, 20);
		frmPhonebook.getContentPane().add(textName);
		textName.setColumns(10);

		textMobile = new JTextField();
		textMobile.setBounds(89, 54, 127, 20);
		frmPhonebook.getContentPane().add(textMobile);
		textMobile.setColumns(10);

		textEmail = new JTextField();
		textEmail.setBounds(89, 85, 127, 20);
		frmPhonebook.getContentPane().add(textEmail);
		textEmail.setColumns(10);

		JButton btnAdd = new JButton("Add");
		JButton btnExit = new JButton("Exit");
		JButton btnDelete = new JButton("Delete");
		
		btnAdd.setBounds(86, 122, 89, 23);
		frmPhonebook.getContentPane().add(btnAdd);

//		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(89, 168, 127, 20);
		frmPhonebook.getContentPane().add(comboBox);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(28, 168, 46, 14);
		frmPhonebook.getContentPane().add(lblSearch);

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				utils.serialize(model, "src/main/resources/phonebookRecords.ser");
				serialize(model, Constants.PHONEBOOKFILE);
				//serial();
//				try {
//					File phonebookFile = new File("phonebookRecords.ser");
//					FileOutputStream fo = new FileOutputStream(phonebookFile);
//					ObjectOutputStream oo = new ObjectOutputStream(fo);
//					oo.writeObject(model);
//					oo.close();
//					fo.close();
//					System.out.println("Records saved succesfully");
//				} catch (FileNotFoundException e1) {
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
				System.exit(0);
			}
		});
		btnExit.setBounds(89, 232, 89, 23);
		frmPhonebook.getContentPane().add(btnExit);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				if(table.getSelectedRow()>=0){
					System.out.println("row count after: "+model.getRowCount());
					model.removeRow(table.getSelectedRow());
					System.out.println("row count after: "+model.getRowCount());
//					loadDropDown();
				}
				table.setModel(model);
			}
		});
		btnDelete.setBounds(89, 198, 89, 23);
		frmPhonebook.getContentPane().add(btnDelete);

		final Object[] row = new Object[3];
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				if(!textName.getText().contentEquals("")){
					row[0] = textName.getText();
					row[1] = textMobile.getText();
					row[2] = textEmail.getText();
					model.addRow(row);
					table.setModel(model);
//					textName.setText("");
					textMobile.setText("");
					textEmail.setText("");
					textName.setText("");
				}else{
					JOptionPane.showMessageDialog(null, "No records selected");
				}
				loadDropDown();
//				comboBox = (JComboBox) loadSearch(model);
//				comboBox.setModel(new DefaultComboBoxModel(loadSearch(model).toArray()));
			}
		});
		
		frmPhonebook.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				try {
//					serial();
					serialize(model, "src/main/resources/phonebookRecords.ser");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void loadDropDown(){
		this.comboBox.setModel(new DefaultComboBoxModel(loadSearch(this.model).toArray()));
		
	}
//	private void serial(){
//		try {
//			File phonebookFile = new File("phonebookRecords.ser");
//			FileOutputStream fo = new FileOutputStream(phonebookFile);
//			ObjectOutputStream oo = new ObjectOutputStream(fo);
//			oo.writeObject(model);
//			oo.close();
//			fo.close();
//			System.out.println("Records saved succesfully");
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}
//	private TableModel deser() throws ClassNotFoundException, IOException{
//		TableModel model;
//		FileInputStream fi = new FileInputStream("phonebookRecords.ser");
//		ObjectInputStream oi = new ObjectInputStream(fi);
//		model =  (DefaultTableModel) oi.readObject();
//		oi.close();
//		fi.close();
//		return model;
//	}
}
