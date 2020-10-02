package by.sp.task7;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class StoreTable extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	static int i = 0;

	static ArrayList<CatalogueItem> beans = new ArrayList<CatalogueItem>();

	void addAtTable(String manufacturer, String model, int year, String country, String type) {
		beans.add(new CatalogueItem(manufacturer, model, year, country, type));
	}

	public void myTable() {
		TableModel model = new MyTableModel2(beans);
		JTable table = new JTable(model);

		getContentPane().add(new JScrollPane(table));

		setPreferredSize(new Dimension(800, 220));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public class MyTableModel2 implements TableModel {

		private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

		private List<CatalogueItem> beans;

		public MyTableModel2(List<CatalogueItem> beans) {
			this.beans = beans;
		}

		public void addTableModelListener(TableModelListener listener) {
			listeners.add(listener);
		}

		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		public int getColumnCount() {
			return 5;
		}

		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "Manufactured by";
			case 1:
				return "Model";
			case 2:
				return "Year";
			case 3:
				return "Country";
			case 4:
				return "Type";
			}
			return "";
		}

		public int getRowCount() {
			return beans.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			CatalogueItem bean = beans.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return bean.getManufacturer();
			case 1:
				return bean.getModel();
			case 2:
				return bean.getYear();
			case 3:
				return bean.getCountry();
			case 4:
				return bean.getType();
			}
			return "";
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		public void removeTableModelListener(TableModelListener listener) {
			listeners.remove(listener);
		}

		public void setValueAt(Object value, int rowIndex, int columnIndex) {
		}
	}

}