package bsu.fpmi.educational_practice;

import java.awt.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test implements GraphSample {
	static final int WIDTH = 1000, HEIGHT = 500;

	public String getName() {
		return "Paints";
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	private My3dVertLine inf = new My3dVertLine(250);
	private final int X = 300, Y = 400;

	@Override
	public void draw(Graphics2D arg0, Component arg1) {
		inf.paint(arg0);

	}

}

class GraphSampleFrame extends JFrame {

	static final String classname = "bsu.fpmi.educational_practice.test";

	public GraphSampleFrame(final GraphSample[] examples) {
		super("GraphSampleFrame");

		Container cpane = getContentPane();
		cpane.setLayout(new BorderLayout());
		final JTabbedPane tpane = new JTabbedPane();
		cpane.add(tpane, BorderLayout.CENTER);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		for (int i = 0; i < examples.length; i++) {
			GraphSample e = examples[i];
			tpane.addTab(e.getName(), new GraphSamplePane(e));
		}
	}

	public class GraphSamplePane extends JComponent {
		GraphSample example;
		Dimension size;

		public GraphSamplePane(GraphSample example) {
			this.example = example;
			size = new Dimension(example.getWidth(), example.getHeight());
			setMaximumSize(size);
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, size.width, size.height);
			g.setColor(Color.black);
			example.draw((Graphics2D) g, this);
		}

		public Dimension getPreferredSize() {
			return size;
		}

		public Dimension getMinimumSize() {
			return size;
		}
	}

	public static void main(String[] args) {
		GraphSample[] examples = new GraphSample[1];

		try {
			Class exampleClass = Class.forName(classname);
			examples[0] = (GraphSample) exampleClass.newInstance();
		} catch (ClassNotFoundException e) {
			System.err.println("Couldn't find example: " + classname);
			System.exit(1);
		} catch (ClassCastException e) {
			System.err.println("Class " + classname + " is not a GraphSample");
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Couldn't instantiate example: " + classname);
			System.exit(1);
		}

		GraphSampleFrame f = new GraphSampleFrame(examples);
		f.pack();
		f.setVisible(true);
	}
}

interface GraphSample {
	public String getName();

	public int getWidth();

	public int getHeight();

	public void draw(Graphics2D g, Component c);
}
