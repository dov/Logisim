package com.cburch.logisim.std.ttl;

import java.awt.Graphics;

import com.cburch.logisim.instance.InstancePainter;
import com.cburch.logisim.util.GraphicsUtil;

public class Drawgates {
	// x and y correspond to output port coordinates
	public static void paintAnd(Graphics g, int x, int y, int width, int height, boolean negated) {
		if (negated)
			paintNegatedOutput(g, x, y);
		int[] xp = new int[] { x - width / 2, x - width, x - width, x - width / 2 };
		int[] yp = new int[] { y - width / 2, y - width / 2, y + width / 2, y + width / 2 };
		GraphicsUtil.drawCenteredArc(g, x - width / 2, y, width / 2, -90, 180);

		g.drawPolyline(xp, yp, 4);
		if (height > width) {
			g.drawLine(x - width, y - height / 2, x - width, y + height / 2);
		}
	}

	static void paintBuffer(Graphics g, int x, int y, int width, int height) {
		int[] xp = new int[4];
		int[] yp = new int[4];
		xp[0] = x - 4;
		yp[0] = y;
		xp[1] = x - width;
		yp[1] = y - height / 2;
		xp[2] = x - width;
		yp[2] = y + height / 2;
		xp[3] = x - 4;
		yp[3] = y;
		g.drawPolyline(xp, yp, 4);
	}

	static void paintDoubleInputgate(Graphics g, int xrightpin, int y, int xinput, int youtput, int portheight,
			boolean up, boolean rightToLeft) {
		int[] yPoints, xPoints;
		// rightmost input if !rightToLeft
		if (!rightToLeft)
			xPoints = new int[] { xrightpin, xrightpin, xrightpin - 10, xrightpin - 10, xinput };
		else // leftmost input if rightToLeft
			xPoints = new int[] { xrightpin - 20, xrightpin - 20, xrightpin - 10, xrightpin - 10, xinput };
		if (!up)
			yPoints = new int[] { y + AbstractTtlGate.height - AbstractTtlGate.pinheight,
					y + AbstractTtlGate.height - AbstractTtlGate.pinheight - (10 - AbstractTtlGate.pinheight),
					y + AbstractTtlGate.height - AbstractTtlGate.pinheight - (10 - AbstractTtlGate.pinheight),
					youtput + portheight / 3, youtput + portheight / 3 };
		else
			yPoints = new int[] { y + AbstractTtlGate.pinheight,
					y + AbstractTtlGate.pinheight + (10 - AbstractTtlGate.pinheight),
					y + AbstractTtlGate.pinheight + (10 - AbstractTtlGate.pinheight), youtput - portheight / 3,
					youtput - portheight / 3 };
		g.drawPolyline(xPoints, yPoints, 5);
		// leftmost input if !rightToLeft
		if (!rightToLeft)
			xPoints = new int[] { xrightpin - 20, xrightpin - 20, xinput };
		else // rightmost input if rightToLeft
			xPoints = new int[] { xrightpin, xrightpin, xinput };
		if (!up)
			yPoints = new int[] { y + AbstractTtlGate.height - AbstractTtlGate.pinheight, youtput - portheight / 3,
					youtput - portheight / 3 };
		else
			yPoints = new int[] { y + AbstractTtlGate.pinheight, youtput + portheight / 3, youtput + portheight / 3 };
		g.drawPolyline(xPoints, yPoints, 3);
	}

	private static void paintNegatedOutput(Graphics g, int x, int y) {
		g.drawOval(x, y - 2, 4, 4);
	}

	static void paintNot(Graphics g, int x, int y, int width, int height) {
		int[] xp = new int[4];
		int[] yp = new int[4];
		xp[0] = x - 4;
		yp[0] = y;
		xp[1] = x - width;
		yp[1] = y - height / 2;
		xp[2] = x - width;
		yp[2] = y + height / 2;
		xp[3] = x - 4;
		yp[3] = y;
		g.drawPolyline(xp, yp, 4);
		g.drawOval(x - 4, y - 2, 4, 4);
	}

	static void paintOr(Graphics g, int x, int y, boolean negated, boolean rightToLeft) {
		if (!rightToLeft) {
			if (negated)
				paintNegatedOutput(g, x, y);
			GraphicsUtil.drawCenteredArc(g, x - 14, y - 10, 17, -90, 54);
			GraphicsUtil.drawCenteredArc(g, x - 14, y + 10, 17, 90, -54);
			GraphicsUtil.drawCenteredArc(g, x - 28, y, 15, -27, 54);
		} else {
			if (negated)
				paintNegatedOutput(g, x - 4, y);
			GraphicsUtil.drawCenteredArc(g, x + 14, y - 10, 17, -90, -54);
			GraphicsUtil.drawCenteredArc(g, x + 14, y + 10, 17, 90, 54);
			GraphicsUtil.drawCenteredArc(g, x + 28, y, 15, 153, 54);
		}
	}

	static void paintOutputgate(Graphics g, int xpin, int y, int xoutput, int youtput, boolean up) {
		int[] yPoints, xPoints;
		xPoints = new int[] { xoutput, xpin, xpin };
		if (!up)
			yPoints = new int[] { youtput, youtput, y + AbstractTtlGate.height - AbstractTtlGate.pinheight };
		else
			yPoints = new int[] { youtput, youtput, y + AbstractTtlGate.pinheight };
		g.drawPolyline(xPoints, yPoints, 3);
	}

	static void paintPortNames(InstancePainter painter, int x, int y, int height, String[] portnames) {
		Graphics g = painter.getGraphics();
		g.drawRect(x + 10, y + AbstractTtlGate.pinheight + 10, portnames.length * 10,
				height - 2 * AbstractTtlGate.pinheight - 20);
		for (byte i = 0; i < 2; i++) {
			for (byte j = 0; j < portnames.length / 2; j++) {
				GraphicsUtil.drawCenteredText(g, portnames[j + (i * 7)],
						i == 0 ? x + 10 + j * 20 : x + 160 - j * 20 - 10,
						y + height - AbstractTtlGate.pinheight - 7 - i * (height - 2 * AbstractTtlGate.pinheight - 11));
			}
		}
	}

	static void paintSingleInputgate(Graphics g, int xpin, int y, int xinput, int youtput, boolean up) {
		int[] yPoints, xPoints;
		xPoints = new int[] { xpin, xpin, xinput };
		if (!up)
			yPoints = new int[] { y + AbstractTtlGate.height - AbstractTtlGate.pinheight, youtput, youtput };
		else
			yPoints = new int[] { y + AbstractTtlGate.pinheight, youtput, youtput };
		g.drawPolyline(xPoints, yPoints, 3);
	}

	static void paintXor(Graphics g, int x, int y, boolean negated, boolean rightToLeft) {
		paintOr(g, x, y, negated, rightToLeft);
		GraphicsUtil.drawCenteredArc(g, x - 32, y, 15, -27, 54);
	}
}