package by.sp.task6.ver2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import by.sp.task4.MyStroke;

import java.awt.datatransfer.*; // Clipboard, Transferable, DataFlavor, etc.
import java.awt.dnd.*;
import java.util.ArrayList;

public class MainFrame extends JComponent implements DragGestureListener, // For recognizing the start of drags
		DragSourceListener, // For processing drag source events
		DropTargetListener, // For processing drop target events
		MouseListener, // For processing mouse clicks
		MouseMotionListener // For processing mouse drags

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ArrayList<MyFigure> fig = new ArrayList<MyFigure>();
	// A list of Scribbles to draw
	MyFigure currentScribble; // The scribble in progress
	MyFigure beingDragged; // The scribble being dragged
	DragSource dragSource; // A central DnD object
	boolean dragMode; // Are we dragging or scribbling?

	// These are some constants we use
	static final int LINEWIDTH = 12;
	static final MyStroke linestyle = new MyStroke((1));

	/**
	 * The constructor: set up drag-and-drop stuff
	 */
	public MainFrame() {

		// Register listeners to handle drawing
		addMouseListener(this);
		addMouseMotionListener(this);

		// Create a DragSource and DragGestureRecognizer to listen for drags
		// The DragGestureRecognizer will notify the DragGestureListener
		// when the user tries to drag an object
		dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(this, // What component
				DnDConstants.ACTION_COPY_OR_MOVE, // What drag types?
				this);// the listener

		// Create and set up a DropTarget that will listen for drags and
		// drops over this component, and will notify the DropTargetListener
		DropTarget dropTarget = new DropTarget(this, // component to monitor
				this); // listener to notify
		this.setDropTarget(dropTarget); // Tell the component about it.
	}

	/**
	 * The component draws itself by drawing each of the Scribble objects.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(linestyle);

		int numScribbles = fig.size();
		for (int i = 0; i < numScribbles; i++) {
			MyFigure s = fig.get(i);
			g2.translate(s.getCenterX(), s.getCenterY());
			g2.draw(s);
			g2.translate(-s.getCenterX(), -s.getCenterY());
		}
	}

	public void setDragMode(boolean dragMode) {
		this.dragMode = dragMode;
	}

	public boolean getDragMode() {
		return dragMode;
	}

	/**
	 * This method, and the following four methods are from the MouseListener
	 * interface. If we're in drawing mode, this method handles mouse down events
	 * and starts a new scribble.
	 */
	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	/**
	 * This method and mouseMoved() below are from the MouseMotionListener
	 * interface. If we're in drawing mode, this method adds a new point to the
	 * current scribble and requests a redraw
	 */
	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	/**
	 * This method implements the DragGestureListener interface. It will be invoked
	 * when the DragGestureRecognizer thinks that the user has initiated a drag. If
	 * we're not in drawing mode, then this method will try to figure out which
	 * Scribble object is being dragged, and will initiate a drag on that object.
	 */
	public void dragGestureRecognized(DragGestureEvent e) {
		// Figure out where the drag started
		MouseEvent inputEvent = (MouseEvent) e.getTriggerEvent();
		int x = inputEvent.getX();
		int y = inputEvent.getY();

		// Figure out which scribble was clicked on, if any by creating a
		// small rectangle around the point and testing for intersection.
		Rectangle r = new Rectangle(x - LINEWIDTH, y - LINEWIDTH, LINEWIDTH * 2, LINEWIDTH * 2);
		int numLeafs = fig.size();
		for (int i = 0; i < numLeafs; i++) { // Loop through the leafs
			MyFigure s = (MyFigure) fig.get(i);
			if (s.intersects(r)) {
				// The user started the drag on top of this scribble, so
				// start to drag it.

				// First, remember which scribble is being dragged, so we can
				// delete it later (if this is a move rather than a copy)
				beingDragged = s;

				// Next, create a copy that will be the one dragged
				MyFigure dragScribble = (MyFigure) s.clone();
				// Adjust the origin to the point the user clicked on.
				dragScribble.translate(-x, -y);

				// Choose a cursor based on the type of drag the user initiated
				Cursor cursor;
				switch (e.getDragAction()) {
				case DnDConstants.ACTION_COPY:
					cursor = DragSource.DefaultCopyDrop;
					break;
				case DnDConstants.ACTION_MOVE:
					cursor = DragSource.DefaultMoveDrop;
					break;
				default:
					return; // We only support move and copys
				}

				// Some systems allow us to drag an image along with the
				// cursor. If so, create an image of the scribble to drag
				if (dragSource.isDragImageSupported()) {
					Rectangle scribbleBox = dragScribble.getBounds();
					Image dragImage = this.createImage(scribbleBox.width, scribbleBox.height);
					Graphics2D g = (Graphics2D) dragImage.getGraphics();
					g.setColor(new Color(0, 0, 0, 0)); // transparent background
					g.fillRect(0, 0, scribbleBox.width, scribbleBox.height);
					g.setColor(Color.black);
					g.setStroke(linestyle);
					g.translate(-scribbleBox.x, -scribbleBox.y);
					g.draw(dragScribble);
					Point hotspot = new Point(-scribbleBox.x, -scribbleBox.y);

					// Now start dragging, using the image.
					e.startDrag(cursor, dragImage, hotspot, dragScribble, this);
				} else {
					// Or start the drag without an image
					e.startDrag(cursor, dragScribble, this);
				}
				// After we've started dragging one scribble, stop looking
				return;
			}
		}
	}

	/**
	 * This method, and the four unused methods that follow it implement the
	 * DragSourceListener interface. dragDropEnd() is invoked when the user drops
	 * the scribble she was dragging. If the drop was successful, and if the user
	 * did a "move" rather than a "copy", then we delete the dragged scribble from
	 * the list of leafs to draw.
	 */
	public void dragDropEnd(DragSourceDropEvent e) {
		if (!e.getDropSuccess()) {
			return;
		}
		int action = e.getDropAction();
		if (action == DnDConstants.ACTION_MOVE) {
			fig.remove(beingDragged);
			beingDragged = null;
			repaint();
		}
	}

	// These methods are also part of DragSourceListener.
	// They are invoked at interesting points during the drag, and can be
	// used to perform "drag over" effects, such as changing the drag cursor
	// or drag image.
	public void dragEnter(DragSourceDragEvent e) {
	}

	public void dragExit(DragSourceEvent e) {
	}

	public void dropActionChanged(DragSourceDragEvent e) {
	}

	public void dragOver(DragSourceDragEvent e) {
	}

	// The next five methods implement DropTargetListener

	/**
	 * This method is invoked when the user first drags something over us. If we
	 * understand the data type being dragged, then call acceptDrag() to tell the
	 * system that we're receptive. Also, we change our border as a "drag under"
	 * effect to signal that we can accept the drop.
	 */
	public void dragEnter(DropTargetDragEvent e) {
		if (e.isDataFlavorSupported(MyFigure.scribbleDataFlavor) || e.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			e.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
		}
	}

	/**
	 * The user is no longer dragging over us, so restore the border
	 */
	public void dragExit(DropTargetEvent e) {
	}

	/**
	 * This is the key method of DropTargetListener. It is invoked when the user
	 * drops something on us.
	 */
	public void drop(DropTargetDropEvent e) {

		// First, check whether we understand the data that was dropped.
		// If we supports our data flavors, accept the drop, otherwise reject.
		if (e.isDataFlavorSupported(MyFigure.scribbleDataFlavor) || e.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
		} else {
			e.rejectDrop();
			return;
		}

		// We've accepted the drop, so now we attempt to get the dropped data
		// from the Transferable object.
		Transferable t = e.getTransferable(); // Holds the dropped data
		MyFigure droppedScribble; // This will hold the Scribble object

		// First, try to get the data directly as a scribble object
		try {
			droppedScribble = (MyFigure) t.getTransferData(MyFigure.scribbleDataFlavor);
		} catch (Exception ex) { // unsupported flavor, IO exception, etc.
			// If that doesn't work, try to get it as a String and parse it
			try {
				String s = (String) t.getTransferData(DataFlavor.stringFlavor);
				droppedScribble = MyFigure.parse(s);
			} catch (Exception ex2) {
				// If we still couldn't get the data, tell the system we failed
				e.dropComplete(false);
				return;
			}
		}

		// If we get here, we've got the Scribble object
		Point p = e.getLocation(); // Where did the drop happen?
		droppedScribble.translate(p.getX(), p.getY()); // Move it there
		fig.add(droppedScribble); // add to display list
		repaint(); // ask for redraw
		e.dropComplete(true); // signal success!
	}

	// These are unused DropTargetListener methods
	public void dragOver(DropTargetDragEvent e) {
	}

	public void dropActionChanged(DropTargetDragEvent e) {
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Cycloid. Drag-And-Drop");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		final MainFrame scribblePane = new MainFrame();
		frame.getContentPane().add(scribblePane, BorderLayout.CENTER);
		scribblePane.setDragMode(true);

		// Pop up the window

		frame.setSize(600, 400);

		MyFigure figure = new MyFigure();
		figure.translate(100, 100);
		fig.add(figure);
		scribblePane.repaint();
		frame.setVisible(true);
	}
}
