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

public class TypeTable extends JFrame {

	private static final long serialVersionUID = 1L;

	static int i = 0;

	static ArrayList<TypeItem> beans = new ArrayList<TypeItem>();

	void addAtTable(String type, String description) {
		beans.add(new TypeItem(type, description));
	}

	public void myTable() {
		TableModel model = new MyTableModel1(beans);
		JTable table = new JTable(model);

		getContentPane().add(new JScrollPane(table));

		setPreferredSize(new Dimension(800, 220));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public class MyTableModel1 implements TableModel {

		private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

		private List<TypeItem> beans;

		public MyTableModel1(List<TypeItem> beans) {
			this.beans = beans;
		}

		public void addTableModelListener(TableModelListener listener) {
			listeners.add(listener);
		}

		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		public int getColumnCount() {
			return 2;
		}

		public String getColumnName(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return "Type";
			case 1:
				return "Description";
			}
			return "";
		}

		public int getRowCount() {
			return beans.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			TypeItem bean = beans.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return bean.getType();
			case 1:
				return bean.getDescription();
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
