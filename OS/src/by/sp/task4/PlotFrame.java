package by.sp.task4;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

public class PlotFrame extends JFrame implements Printable {

	private static final long serialVersionUID = 1L;

	private String[] textLines;
	private int[] pageBreaks;

	private PlotFrame() {
		initComponents();
		this.setTitle("Task 4");
		this.setSize(700, 470);
		File f = new File("src");
		if (f.exists()) {
			System.out.println(f.getAbsolutePath());
		}
		textLines = initTextLines(new File("src/by/sp/task4/MyShape.java"));

//		for(String e : textLines) {
//			System.out.println(e);
//		}
	}

	private void initComponents() {

		JPanel jPanel1 = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Graphics2D graphics2D = (Graphics2D) g;
				graphics2D.setColor(Color.WHITE);
				graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
				graphics2D.setColor(Color.BLACK);
				graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics2D.setStroke(new MyStroke(5));
				graphics2D.draw(new MyShape(50, 10, 200));
			}
		};

		JMenuBar jMenuBar1 = new JMenuBar();
		JMenu jMenu2 = new JMenu();
		JMenuItem jMenuItem1 = new JMenuItem();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new Color(255, 255, 255));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 279, Short.MAX_VALUE));

		jMenu2.setText("Options");

		jMenuItem1.setText("Print");
		jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
		jMenu2.add(jMenuItem1);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel1,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
		PrinterJob job = PrinterJob.getPrinterJob();
		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		printRequestAttributeSet.add(Sides.DUPLEX);
		printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);

		job.setPrintable(this);
		boolean ok = job.printDialog(printRequestAttributeSet);
		if (ok) {
			try {
				job.print(printRequestAttributeSet);
			} catch (PrinterException ex) {
				System.err.print(ex);
			}
		}

	}

	public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException,
			InstantiationException, IllegalAccessException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(() -> new PlotFrame().setVisible(true));
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		int y = 0;
		Font font = new Font("Serif", Font.PLAIN, 10);
		FontMetrics metrics = graphics.getFontMetrics(font);
		int lineHeight = metrics.getHeight();
		if (pageIndex == 0) {
			BufferedImage bufferedImageAll = new BufferedImage(this.getWidth(), this.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2DForImage = bufferedImageAll.createGraphics();
			this.printAll(graphics2DForImage);

			double scale = pageFormat.getWidth() / this.getWidth();
			int newWidth = (int) (this.getWidth() * scale / 1.5);
			int newHeight = (int) (this.getHeight() * scale / 1.5);
			graphics.drawString("Simple Cicloid", getX() + getWidth() / 2, getY() + getHeight() - 60);

			// b = bufferedImageAll;
			Image scaledImage = bufferedImageAll.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			graphics.drawImage(scaledImage, (int) ((pageFormat.getImageableWidth() / 2) - 130), // ������ ���
																								// ������������� ��
																								// �����������
					(int) (pageFormat.getImageableHeight() / 40), null);

			return PAGE_EXISTS;
		}

		if (pageBreaks == null) {
			int linesPerPage = (int) (pageFormat.getImageableHeight() / lineHeight);
			int numBreaks = (textLines.length - 1) / linesPerPage + 1;
			pageBreaks = new int[numBreaks];
			for (int b = 0; b < numBreaks; b++) {
				pageBreaks[b] = b * linesPerPage;
			}
		}

		if (pageIndex > pageBreaks.length) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2D = (Graphics2D) graphics;
		g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		int start = pageBreaks[pageIndex - 1];
		int end = (pageIndex == pageBreaks.length) ? textLines.length : pageBreaks[pageIndex];
		for (int line = start; line < end; line++) {
			y += lineHeight;
			graphics.drawString(textLines[line], 0, y);
		}
		return PAGE_EXISTS;
	}

	private String[] initTextLines(File file) {
		ArrayList<String> result = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				result.add(line);
			}
		} catch (FileNotFoundException ex) {
			return null;
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return result.toArray(new String[result.size()]);
	}
}
