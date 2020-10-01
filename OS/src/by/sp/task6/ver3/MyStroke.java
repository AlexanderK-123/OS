package by.sp.task6.ver3;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;

public class MyStroke implements Stroke {
    private BasicStroke stroke;

    MyStroke( float width ) { stroke = new BasicStroke( width ); }

    @Override
    public Shape createStrokedShape(Shape shape) {
        GeneralPath graph = new GeneralPath(); // Start with an empty shape

        float[] coordinate = new float[2];
        float[] prevCoordinate = new float[2];

        for (PathIterator iterator = shape.getPathIterator(null); !iterator.isDone(); iterator.next()) {
            int type = iterator.currentSegment(coordinate);
            switch (type) {
                case PathIterator.SEG_MOVETO:
                    graph.moveTo(coordinate[0], coordinate[1]);
                    break;

                case PathIterator.SEG_LINETO:
                    double x1 = prevCoordinate[0];
                    double y1 = prevCoordinate[1];
                    double x2 = coordinate[0];
                    double y2 = coordinate[1];

                    double dx = x2 - x1;
                    double dy = y2 - y1;

                    graph.lineTo( x1 + (dx / 3), y1 - (dx / 3) );
                    graph.lineTo( x1 + (dx / 3), y1 - 2 * (dx / 3) );
                    graph.lineTo( x1 + 2 * (dx / 3), y1 - 2 * (dx / 3) );
                    graph.lineTo( x1 + 2 * (dx / 3), y1 - (dx / 3) );
                    graph.lineTo( x2, y2 );

                    break;

            }
            prevCoordinate[0] = coordinate[0];
            prevCoordinate[1] = coordinate[1];
        }

        return stroke.createStrokedShape(graph);
    }

}
