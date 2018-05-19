package com.phonebook.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Utils implements Serializable{

	public void serialize(Object o, String filePath){
		try {
//			File phonebookFile = new File("src/main/resources/phonebookRecords.ser");
			File phonebookFile = new File(filePath);
//			phonebookFile.
			FileOutputStream fo = new FileOutputStream(phonebookFile);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
//			oo.reset();
			oo.writeObject(o);
			oo.close();
			fo.close();
			System.out.println("Records saved succesfully");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public Object deserialize(String filePath) throws IOException, ClassNotFoundException{
		
		DefaultTableModel model;
//		FileInputStream fi = new FileInputStream("src/main/resources/phonebookRecords.ser");
		FileInputStream fi = new FileInputStream(filePath);
		ObjectInputStream oi = new ObjectInputStream(fi);
		model =  (DefaultTableModel) oi.readObject();
		oi.close();
		fi.close();
		return model;
	}
	
	public List<String> loadSearch(TableModel model){
		boolean flag = false;
		int count = model.getRowCount();
		List<String> names = new ArrayList<String>();
		
		for (int i=0 ; i<count ; i++){
			System.out.println("name: " + model.getValueAt(i, 0).toString());
			names.add(model.getValueAt(i, 0).toString());
		}
//		combo.setModel((ComboBoxModel<String>) names);
		
		return names;
	}

}
