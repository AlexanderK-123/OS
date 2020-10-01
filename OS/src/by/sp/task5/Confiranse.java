package by.sp.task5;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.*;

//научная конференция(статьи, доклады, презентации докладчики и т. п.)
public class Confiranse extends JFrame {

	private static final long serialVersionUID = 1L;

	public static DevNode addResult = null;
	public static String path = null;
	JTable infoPanel = new JTable();
	JTree notebooksTree = new JTree();
	myTableModel tableModel = null;
	myTreeModel treeModel = null;

	public Confiranse() throws HeadlessException {
		JButton addButton = new JButton("Добавить участника конференции");
		addButton.addActionListener(e -> SwingUtilities.invokeLater(() -> openAddDialog()));
		JButton removeButton = new JButton("Удалить участника конференции");
		removeButton.addActionListener(e -> removeItem());
		tableModel = new myTableModel();
		infoPanel = new JTable(tableModel);
		treeModel = new myTreeModel(new treeNode("Научная конференция"));
		notebooksTree = new JTree(treeModel);
		notebooksTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				treeNode node = (treeNode) notebooksTree.getLastSelectedPathComponent();
				if (node == null)
					return;
				ArrayList<DevNode> array = node.getAllNodes();
				tableModel = new myTableModel(array);
				infoPanel.setModel(tableModel);
			}
		});
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, new JScrollPane(notebooksTree),
				new JScrollPane(infoPanel));
		splitPane.setDividerLocation(300);
		getContentPane().add(splitPane);
		getContentPane().add("North", addButton);
		getContentPane().add("South", removeButton);
		setBounds(100, 100, 600, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		Confiranse mainClass = new Confiranse();
		mainClass.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainClass.setVisible(true);
	}

	public void openAddDialog() {
		Add addForm = new Add(this);
		addForm.setVisible(true);
	}

	public void addNewItem() {
		treeNode temp, where, insert, root = treeModel.getRoot();
		try {
			insert = new treeNode(addResult.mod, addResult);
			if ((where = findNode(root, addResult.prod)) != null)
				treeModel.insertNodeInto(insert, where, where.getChildCount(), false);
			else if (findNode(root, addResult.relat) != null) {
				treeModel.insertNodeInto(new treeNode(addResult.prod), (temp = findNode(root, addResult.relat)),
						temp.getChildCount(), false);
				where = findNode(root, addResult.prod);
				treeModel.insertNodeInto(insert, where, where.getChildCount(), false);
			} else if (findNode(root, addResult.type) != null) {
				treeModel.insertNodeInto(new treeNode(addResult.relat), (temp = findNode(root, addResult.type)),
						temp.getChildCount(), false);
				treeModel.insertNodeInto(new treeNode(addResult.prod), (temp = findNode(root, addResult.relat)),
						temp.getChildCount(), false);
				where = findNode(root, addResult.prod);
				treeModel.insertNodeInto(insert, where, where.getChildCount(), false);
			} else {
				treeModel.insertNodeInto(new treeNode(addResult.type), root, root.getChildCount(), false);
				treeModel.insertNodeInto(new treeNode(addResult.relat), (temp = findNode(root, addResult.type)),
						temp.getChildCount(), false);
				treeModel.insertNodeInto(new treeNode(addResult.prod), (temp = findNode(root, addResult.relat)),
						temp.getChildCount(), false);
				where = findNode(root, addResult.prod);
				treeModel.insertNodeInto(insert, where, where.getChildCount(), false);
			}
		} catch (Exception e) {
			path = null;
			addResult = null;
			return;
		}
		path = null;
		addResult = null;
	}

	public void removeItem() {
		TreePath currentSelection = notebooksTree.getSelectionPath();
		if (currentSelection != null) {
			treeNode currentNode = (treeNode) (currentSelection.getLastPathComponent());
			treeNode parent = (treeNode) (currentNode.getParent());
			if (parent != null) {
				treeModel.removeNodeFromParent(currentNode);
				parent.deleteNode(currentNode);
				ArrayList<DevNode> array = parent.getAllNodes();
				tableModel = new myTableModel(array);
				infoPanel.setModel(tableModel);
			}
		}
	}

	public treeNode findNode(treeNode root, String s) {
		Enumeration<TreeNode> e = root.depthFirstEnumeration();
		while (e.hasMoreElements()) {
			treeNode node = (treeNode) e.nextElement();
			if (node.toString().equalsIgnoreCase(s))
				return node;
		}
		return null;
	}
}

class myTreeModel extends DefaultTreeModel {

	private static final long serialVersionUID = 1L;

	private treeNode root;

	public myTreeModel(treeNode r) {
		super(r);
		root = r;
	}

	public treeNode getRoot() {
		return root;
	}

	public void insertNodeInto(treeNode child, treeNode parent, int i, boolean flag) {
		this.insertNodeInto(child, parent, i);
		parent.addNode(child);
	}
}

class myTableModel implements TableModel {
	static final String[] columnNames = new String[] { "Научная область", "Тип выступления", "Научная степень",
			"Имя выступающего" };

	@SuppressWarnings("rawtypes")
	static final Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class,
			String.class, Integer.class, Integer.class };
	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	private ArrayList<DevNode> infoNodes;

	public myTableModel() {
		infoNodes = new ArrayList<DevNode>();
	}

	public myTableModel(ArrayList<DevNode> al) {
		this.infoNodes = al;
	}

	public void setInfoArray(ArrayList<DevNode> al) {
		infoNodes = al;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return infoNodes.size();
	}

	public Class<?> getColumnClass(int columnIndex) {
		return columnTypes[columnIndex];
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		DevNode nb = infoNodes.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return nb.getType();
		case 1:
			return nb.getRelat();
		case 2:
			return nb.getProd();
		case 3:
			return nb.getMod();
		}
		return "";
	}

	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
	}
}

class treeNode extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 1L;

	String name;
	DevNode ifNode = null;
	ArrayList<treeNode> nodes;
	boolean isThisTheEnd = false;

	public treeNode() {
		name = "-";
		nodes = new ArrayList<treeNode>();
		ifNode = null;
		isThisTheEnd = false;
	}

	public treeNode(String str) {
		name = str;
		nodes = new ArrayList<treeNode>();
		ifNode = null;
		isThisTheEnd = false;
	}

	public treeNode(String str, DevNode nbNode) {
		name = str;
		nodes = new ArrayList<treeNode>();
		ifNode = nbNode;
		isThisTheEnd = true;
	}

	public ArrayList<DevNode> getAllNodes() {
		ArrayList<DevNode> ret = new ArrayList<DevNode>();
		Deque<treeNode> deque = new ArrayDeque<treeNode>();
		treeNode temp;
		deque.push(this);
		while (!deque.isEmpty()) {
			temp = deque.removeFirst();
			if (temp.isThisTheEnd)
				ret.add(temp.getIfNode());
			else
				for (int i = 0; i < temp.nodes.size(); i++)
					deque.push(temp.nodes.get(i));
		}
		return ret;
	}

	public void addNode(treeNode tn) {
		nodes.add(tn);
	}

	public void deleteNode(treeNode tn) {
		for (int i = 0; i < nodes.size(); i++)
			if (nodes.get(i).toString().compareToIgnoreCase(tn.toString()) == 0)
				nodes.remove(i);
	}

	public DevNode getIfNode() {
		return ifNode;
	}

	public ArrayList<treeNode> getNodes() {
		return nodes;
	}

	public String toString() {
		return name;
	}
}

class DevNode {
	String mod, prod, type, relat;

	DevNode() {
	}

	DevNode(String name, String type, String relat, String mod) {
		this.prod = name;
		this.type = type;
		this.relat = relat;
		this.mod = mod;
	}

	public String getType() {
		return type;
	}

	public String getRelat() {
		return relat;
	}

	public String getProd() {
		return prod;
	}

	public String getMod() {
		return mod;
	}
}