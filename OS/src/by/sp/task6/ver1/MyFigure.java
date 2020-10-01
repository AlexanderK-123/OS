package by.sp.task6.ver1;
//MyFigure.java
//Д
//
//Тип
//Java
//Размер
//5 КБ (4 929 байт)
//Занимаемое пространство
//0 байт
//Расположение
//lab v.6 var.14 au.Gotovec
//Владелец
//Денис Ковалевский
//Изменено
//пользователем Денис Ковалевский 18 окт. 2019 г.
//Открыто
//мной 17:40
//Создано
//18 окт. 2019 г.
//Добавить описание
//Читателям разрешено скачивать файл
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.StringTokenizer;


public class MyFigure implements Shape, Transferable, Serializable, Cloneable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	double centerX,centerY;

    double maxX = 600;     // The bounding box
    double maxY = 600;
    double minX = -600;
    double minY = -600;

    public MyFigure() {
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public void translate(double x,double y){
        this.centerX = x;
        this.centerY = y;
    }



    public Object clone() {
        try {
            MyFigure s = (MyFigure) super.clone(); // make a copy of all fields
            return s;
        } catch (CloneNotSupportedException e) {  // This should never happen
            return this;
        }
    }

    /**
     * Create a new Scribble object and initialize it by parsing a string of
     * coordinate data in the format produced by toString()
     */
    public static MyFigure parse(String s) throws NumberFormatException {
        StringTokenizer st = new StringTokenizer(s);
        MyFigure scribble = new MyFigure();
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
        }
        return scribble;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return new MyIterator(at);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (minX - 0.5f), (int) (minY - 0.5f), (int) (maxX - minX + 0.5f),
                (int) (maxY - minY + 0.5f));
    }

    @Override
    public Rectangle2D getBounds2D() {
        return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return intersects(new Rectangle2D.Double(x, y, w, h));
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        double t = 0;

        double x1, y1, x2, y2;
        x2 = (240 * Math.tan(t)) / (1 + Math.pow(Math.tan(t), 3)) + this.getCenterX();
        y2 = (240 * Math.pow(Math.tan(t), 2)) / (1 + Math.pow(Math.tan(t), 3)) + this.getCenterY();

        while (t <= Math.PI) {
            x1 = x2;
            y1 = y2;
            x2 = (240 * Math.tan(t)) / (1 + Math.pow(Math.tan(t), 3)) + this.getCenterX();
            y2 = (240 * Math.pow(Math.tan(t), 2)) / (1 + Math.pow(Math.tan(t), 3)) + this.getCenterY();
            t += Math.PI / 55;
            if (r.intersectsLine(x1, y1, x2, y2)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return false;
    }

    @Override public PathIterator getPathIterator(AffineTransform at) {
        return new MyIterator(at);
    }


    //====== The following methods implement the Transferable interface =====

    // This is the custom DataFlavor for Scribble objects
    public static DataFlavor scribbleDataFlavor = new DataFlavor(MyFigure.class, "Scribble");

    // This is a list of the flavors we know how to work with
    public static DataFlavor[] supportedFlavors = { scribbleDataFlavor, DataFlavor.stringFlavor };

    /**
     * Return the data formats or "flavors" we know how to transfer
     */
    public DataFlavor[] getTransferDataFlavors() {
        return (DataFlavor[]) supportedFlavors.clone();
    }

    /**
     * Check whether we support a given flavor
     */
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return (flavor.equals(scribbleDataFlavor) || flavor.equals(DataFlavor.stringFlavor));
    }

    /**
     * Return the scribble data in the requested format, or throw an exception
     * if we don't support the requested format
     */
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(scribbleDataFlavor)) {
            return this;
        } else if (flavor.equals(DataFlavor.stringFlavor)) {
            return toString();
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}