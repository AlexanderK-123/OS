package by.sp.task7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GUI extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void createGUI() {
		JFrame jFrame = new JFrame("Data base");
		jFrame.setSize(640, 480);

		JMenuBar menubar = new JMenuBar(); // Create the menubar
		jFrame.setJMenuBar(menubar); // Add it to the frame
		JMenu filemenu = new JMenu("Options"); // Create a File menu
		menubar.add(filemenu);
		JMenuItem addTable = new JMenuItem("Add table");
		filemenu.add(addTable);
		JMenuItem fillDB = new JMenuItem("Fill");
		filemenu.add(fillDB);
		JMenuItem showDB = new JMenuItem("Show DB");
		filemenu.add(showDB);
		JMenuItem quit = new JMenuItem("Quit"); // Create a Quit item
		filemenu.add(quit); // Add it to the menu

		addTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTable();
			}
		});

		fillDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addInfo();
			}
		});

		showDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showInformation();
			}
		});

		// Tell the Quit menu item what to do when selected
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// quit
				System.exit(0);
			}
		});

		jFrame.setVisible(true);
	}

	public void addTable() {
		JTextField textField;
		textField = new JTextField("Enter TableName(Types/Catalogue/Storage)", 30);

		JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contents.add(textField);
		JButton jButton = new JButton("OK");
		contents.add(jButton);
		setContentPane(contents);

		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
				// creatin table str=name
			}
		});
		setSize(400, 200);
		setVisible(true);
	}

	public void addInfo() {
		JTextField tableName;
		JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton buttonOK = new JButton("OK");
		tableName = new JTextField("Enter table to add(Types/Catalogue/Storage)", 30);
		contents.add(tableName);
		contents.add(buttonOK);
		setContentPane(contents);

		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String table = tableName.getText();
				if (table.equals("Catalogue"))
					addInfoAboutCatalogue(table);
				if (table.equals("Storage"))
					addInfoAboutStorage(table);
				if (table.equals("Types"))
					addInfoAboutTypes(table);
			}
		});

		setSize(400, 150);
		setVisible(true);
	}

	public void addInfoAboutTypes(String tableName) {
		JTextField type, description;
		JButton buttonOK = new JButton("OK");

		type = new JTextField("Type of device", 25);
		description = new JTextField("Tye description", 25);
		JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));

		contents.add(type);
		contents.add(description);
		contents.add(buttonOK);
		setContentPane(contents);

		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tName, devType, descr;
				tName = tableName;
				devType = type.getText();
				descr = description.getText();
				// fill DB with tName, devType, descr
			}
		});

		setSize(400, 200);
		setVisible(true);
	}

	public void addInfoAboutCatalogue(String tableName) {
		JTextField manufacturer, model, year, country, type;
		JButton buttonOK = new JButton("OK");

		manufacturer = new JTextField("Manufactured by", 25);
		model = new JTextField("Model", 25);
		year = new JTextField("Year", 25);
		country = new JTextField("Country", 25);
		type = new JTextField("Type", 25);
		JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));

		contents.add(manufacturer);
		contents.add(model);
		contents.add(year);
		contents.add(country);
		contents.add(type);
		contents.add(buttonOK);
		setContentPane(contents);

		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tName, man, mod, rCountry, devType;
				int rYear;
				tName = tableName;
				man = manufacturer.getText();
				mod = model.getText();
				rYear = Integer.parseInt(year.getText());
				rCountry = country.getText();
				devType = type.getText();

				// fill DB with tName, man, mod, rYear, rCountry, devType
			}
		});

		setSize(400, 200);
		setVisible(true);
	}

	public void addInfoAboutStorage(String tableName) {
		JTextField model, quantity, price;

		JButton buttonOK = new JButton("OK");

		model = new JTextField("Model", 25);
		quantity = new JTextField("Number", 25);
		price = new JTextField("Price", 25);
		JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));

		contents.add(model);
		contents.add(quantity);
		contents.add(price);
		contents.add(buttonOK);
		setContentPane(contents);

		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tName, mod;
				int quan, pr;
				tName = tableName;
				mod = model.getText();
				quan = Integer.parseInt(quantity.getText());
				pr = Integer.parseInt(price.getText());
				// fill DB with tName, mod, quan, pr
			}
		});

		setSize(400, 200);
		setVisible(true);
	}

	public void showInformation() {
		JTextField textFieldTableName = new JTextField("Table Name to display(Types/Catalogue/Storage)", 30);
		JButton buttonOk = new JButton("OK");

		JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT));
		contents.add(textFieldTableName);
		contents.add(buttonOk);
		setContentPane(contents);

		buttonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StorageTable.beans.clear();
				TypeTable.beans.clear();
				StoreTable.beans.clear();
				String tableName = textFieldTableName.getText();
				// display DB
			}
		});

		setSize(400, 200);
		setVisible(true);
	}
}
