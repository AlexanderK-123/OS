package by.sp.task6.ver1;

//MyIterator.java
//Д
//
//Тип
//Java
//Размер
//2 КБ (1 821 байт)
//Занимаемое пространство
//0 байт
//Расположение
//lab v.6 var.14 au.Gotovec
//Владелец
//Денис Ковалевский
//Изменено
//пользователем Денис Ковалевский 18 окт. 2019 г.
//Создано
//18 окт. 2019 г.
//Добавить описание
//Читателям разрешено скачивать файл
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.NoSuchElementException;


public class MyIterator implements PathIterator {
    double t;
    double a = 55;
    AffineTransform at;
    boolean begin = true;

    public MyIterator(AffineTransform at) {
        this.at = at;
        this.t = 0;
    }

    @Override
    public boolean isDone() {
        return (t > Math.PI*2);
    }

    @Override
    public int getWindingRule() {
        return 0;
    }

    @Override
    public void next() {
        t+=Math.PI/55;
    }

    @Override
    public int currentSegment(float[] coords) {
    	
        if (isDone()) {
            throw new NoSuchElementException("line iterator out of bounds");
        }
        if(begin){
        	coords[0] = (float) (a * t - a * Math.sin(t));
			coords[1] = (float) (a - a * Math.cos(t)); if (at != null) {
                at.transform(coords, 0, coords, 0, 1);
            }
            begin = false;
            return SEG_MOVETO;
        }
        coords[0] = (float) (a * t - a * Math.sin(t));
		coords[1] = (float) (a - a * Math.cos(t));
        if (at != null) {
            at.transform(coords, 0, coords, 0, 1);
        }

        return SEG_LINETO;
    }

    @Override
    public int currentSegment(double[] coords) {
        if (isDone()) {
            throw new NoSuchElementException("line iterator out of bounds");
        }
        if(begin){
        	coords[0] = (float) (a * t - a * Math.sin(t));
			coords[1] = (float) (a - a * Math.cos(t));  begin = false;
            return SEG_MOVETO;
        }

        coords[0] = (float) (a * t - a * Math.sin(t));
		coords[1] = (float) (a - a * Math.cos(t));
        if (at != null) {
            at.transform(coords, 0, coords, 0, 1);
        }

        return SEG_LINETO;
    }
}
