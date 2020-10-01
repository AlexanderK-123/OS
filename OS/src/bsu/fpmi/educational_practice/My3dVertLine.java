package bsu.fpmi.educational_practice;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;

public class My3dVertLine extends Canvas {

	private int width = 4;
	private int deep = 4;
	private int height = 50;
	private Color color = new Color(200, 200, 0);

	private int X = 200;

	private int Y = 300;

	public My3dVertLine() {
	}

	public My3dVertLine(int height) {
		this.height = height;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphics2D = (Graphics2D) g;
		Color c1 = new Color(0, 0, 0);
		graphics2D.setColor(c1);
		BasicStroke pen = new BasicStroke(1);
		graphics2D.setStroke(pen);
		int[] px1 = { this.X, this.X, this.X + width, this.X + width };
		int[] py1 = { Y, Y - height, Y - height, Y };
		double d1 = this.X + this.deep * (Math.cos(Math.PI / 6));
		double d2 = this.X + (int) this.deep * Math.cos(Math.PI / 6) + this.width;
		int[] px2 = { this.X, (int) d1, (int) d2, this.X + this.width };
		d1 = this.Y - height - this.deep * Math.sin(Math.PI / 6);
		int[] py2 = { this.Y - height, (int) d1, (int) d1, this.Y - height };
		int[] px3 = { this.X + width, (int) d2, (int) d2, this.X + width };
		int[] py3 = { this.Y - height, (int) d1, (int) d1 + height, this.Y };

		c1 = this.color;
		graphics2D.setColor(this.color);
		graphics2D.fillPolygon(px1, py1, 4);
		graphics2D.fillPolygon(px2, py2, 4);
		graphics2D.fillPolygon(px3, py3, 4);
		c1 = new Color(0, 0, 0);
		graphics2D.setColor(c1);
		graphics2D.drawPolygon(px1, py1, 4);
		graphics2D.drawPolygon(px2, py2, 4);
		graphics2D.drawPolygon(px3, py3, 4);

	}

}
