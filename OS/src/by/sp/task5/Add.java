package by.sp.task5;

import javax.swing.*;

public class Add extends JFrame {

	private static final long serialVersionUID = 1L;

	Confiranse frame;
	private JLabel prodLabel;
	private JTextField prodTextField;
	private JButton closeButton;
	private JLabel modLabel;
	private JTextField modTextField;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JLabel relatLabel;
	private JTextField relatTextField;
	private JButton okButton;
	private JLabel typeLabel;
	private JTextField typeTextField;

	public Add(Confiranse temp) {
		frame = temp;
		initComponents();
		this.setTitle("Добавление участника");
		this.setBounds(400, 200, 400, 150);
		this.setSize(350, 250);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	private void initComponents() {
		prodLabel = new JLabel();
		typeLabel = new JLabel();
		relatLabel = new JLabel();
		prodTextField = new JTextField();
		typeTextField = new JTextField();
		relatTextField = new JTextField();
		modTextField = new JTextField();
		modLabel = new JLabel();
		jSeparator1 = new JSeparator();
		jSeparator2 = new JSeparator();
		okButton = new JButton();
		closeButton = new JButton();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		prodLabel.setText("Научная степень:");
		typeLabel.setText("Научная область:");
		relatLabel.setText("Тип выступления:");
		prodTextField.addCaretListener(this::relatTextFieldCaretUpdate);
		typeTextField.addCaretListener(this::typeTextFieldCaretUpdate);
		relatTextField.addCaretListener(this::prodTextFieldCaretUpdate);
		modLabel.setText("Имя выступающего:");
		okButton.setText("Добавить");
		okButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				okButtonMouseClicked(evt);
			}
		});
		closeButton.setText("Закрыть");
		closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				closeButtonMouseClicked(evt);
			}
		});
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jSeparator1)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout
										.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(modLabel)
										.addComponent(relatLabel).addComponent(typeLabel).addComponent(prodLabel))
								.addGap(28, 28, 28)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(typeTextField, GroupLayout.Alignment.TRAILING)
										.addComponent(relatTextField, GroupLayout.Alignment.TRAILING)
										.addComponent(modTextField, GroupLayout.Alignment.TRAILING)
										.addComponent(prodTextField)))
						.addComponent(jSeparator2)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup().addComponent(okButton)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(closeButton))
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(18, 18, 18)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))))
								.addGap(0, 4, Short.MAX_VALUE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(16, 16, 16)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addGap(8, 8, 8)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(prodLabel)
								.addComponent(prodTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(typeLabel)
								.addComponent(typeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(relatLabel)
								.addComponent(relatTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(modTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(modLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(okButton)
								.addComponent(closeButton))
						.addContainerGap()));
		pack();
	}

	private void relatTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {
		checkOkClose();
	}

	private void typeTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {// GEN-FIRST:event_typeTextFieldCaretUpdate
		checkOkClose();
	}

	private void prodTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {
		checkOkClose();
	}

	private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_closeButtonMouseClicked
		this.dispose();
	}

	private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {
		if (this.prodTextField.getText().isEmpty() || this.typeTextField.getText().isEmpty()
				|| this.relatTextField.getText().isEmpty() || this.modTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Необходимо заполнить все поля!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (this.okButton.isEnabled()) {
			DevNode nb = new DevNode(this.prodTextField.getText(), this.typeTextField.getText(),
					this.relatTextField.getText(),
					this.modTextField.getText().isEmpty() ? "-" : this.modTextField.getText());
			Confiranse.addResult = nb;
			frame.addNewItem();
			this.dispose();
		}
	}

	public void checkOkClose() {
		if (!this.prodTextField.getText().isEmpty() && !this.typeTextField.getText().isEmpty()
				&& !this.relatTextField.getText().isEmpty())
			this.okButton.setEnabled(true);
	}

	public void setKeyboardState(boolean flag) {
		this.prodTextField.setEditable(flag);
		this.typeTextField.setEditable(flag);
		this.typeTextField.setEditable(flag);
		this.relatTextField.setEditable(flag);
		this.modTextField.setEditable(flag);
		this.okButton.setEnabled(false);
		this.closeButton.setEnabled(true);
		checkOkClose();
	}
}
