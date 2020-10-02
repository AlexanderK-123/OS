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

public class StorageTable extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	static int i = 0;

	static ArrayList<StorageItem> beans = new ArrayList<StorageItem>();

	void addAtTable(String model, int quantity, int price) {
		beans.add(new StorageItem(model, quantity, price));
	}

	public void myTable() {
		TableModel model = new MyTableModel3(beans);
		JTable table = new JTable(model);

		getContentPane().add(new JScrollPane(table));

		setPreferredSize(new Dimension(800, 220));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public class MyTableModel3 implements TableModel {

		private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

		private List<StorageItem> beans;

		public MyTableModel3(List<StorageItem> beans) {
			this.beans = beans;
		}

		public void addTableModelListener(TableModelListener listener) {
			listeners.add(listener);
		}

		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		public int getColumnCount() {
			return 3;
		}

		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "Model";
			case 1:
				return "Number";
			case 2:
				return "Price";
			}
			return "";
		}

		public int getRowCount() {
			return beans.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			StorageItem bean = beans.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return bean.getModel();
			case 1:
				return bean.getQuantity();
			case 2:
				return bean.getPrice();
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
