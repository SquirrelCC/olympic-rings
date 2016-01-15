package com.scconsulting.olympicrings;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;

/**
 * Created by Shirley Christenson on 1/14/2016.
 *
 * A Java Class that creates the Olympic Rings as a Figure (a collection of graphics Paths).
 */
public class OlympicRings {

    public static float shaderToRadius = 0.0f;
    public static float gradientPos = 0.90f;

    public OlympicRings() {
        super();
    }

    /**
     * Constructor for OlympicRings
     *
     * @param thisFigure  int representing the figure type
     * @param startX  float, The horizontal center of the bottom of Figure
     * @param startY  float, The vertical bottom of the Figure
     * @param stopX  float, The horizontal center of the top of Figure
     * @param stopY  float, The vertical top of the Figure
     * @return
     */
    public static Figure drawFigure(Figure thisFigure, float startX, float startY, float stopX, float stopY) {

        thisFigure.figureType = Rings.ShapeOlympicRings;

        int colorBlue = 0XFF007AC9; // The color of the blue ring
        int colorBlack = 0XFF000000; // The color of the black ring
        int colorRed = 0XFFE10E49; // The color of the red ring
        int colorYellow = 0XFFFFA100; // The color of the yellow ring
        int colorGreen = 0XFF009B3A; // The color of the green ring
        int colorShine = 0XFFFFFFFF; // The center color of each RadialGradient which adds shine to each ring.
        int colorShadow = 0XCCCCCCCC; // Grey with alpha (transparency)

		/*
    	 * If top and bottom values are reversed, reverse startY and stopY.
    	 */
        if (startY < stopY) {
            float tempY = startY;
            startY = stopY;
            stopY = tempY;
        }

        float x = startX;
        float y = startY;
        stopX = startX; // Use startX as the center horizontal position, no slant.

        float outerRadius = (startY - stopY) / 3;
        float gap = outerRadius/6; // The gap between the rings in the top row.
        float dx = outerRadius/16; // x offset of shadow
        float dy = outerRadius/16; // y offset of shadow

        // Set the strokeWidth for drawing each ring based on the size of the figure.
        thisFigure.setStrokeWidth(outerRadius/5);
        thisFigure.strokeCap = Paint.Cap.BUTT;
        thisFigure.strokeJoin = Paint.Join.ROUND;
        thisFigure.paintStyle = Paint.Style.STROKE;

        // Set radius as the outside dimension of a ring minus 1/2 of the strokeWidth.
        float radius = outerRadius - (thisFigure.strokeWidth/2);
        x -=  ((outerRadius*2) + gap);
        y -= (outerRadius*2);

        float shaderHeight = outerRadius * 1.5f; // Height of shiny gradient
        shaderToRadius = 1.5f;
        thisFigure.colorShine = colorShine;

        thisFigure.pathList.clear();
        thisFigure.colorList.clear();
        thisFigure.shaderList.clear();

        Path path = new Path(); // Shadow of blue ring
        path.addCircle(x+dx, y+dy, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorShadow);
        thisFigure.shaderList.add(null);

        // Draw shadows of all 5 rings first.
        x = startX;
        path = new Path(); // Shadow of black ring
        path.addCircle(x+dx, y+dy, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorShadow);
        thisFigure.shaderList.add(null);

        x += ((outerRadius*2) + gap);
        path = new Path(); // Shadow of red ring
        path.addCircle(x+dx, y+dy, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorShadow);
        thisFigure.shaderList.add(null);

        x -= (outerRadius + (gap/2));
        y += outerRadius;
        path = new Path(); // Shadow of green ring
        path.addCircle(x+dx, y+dy, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorShadow);
        thisFigure.shaderList.add(null);

        x -= ((outerRadius*2) + gap);
        path = new Path(); // Shadow of yellow ring
        path.addCircle(x+dx, y+dy, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorShadow);
        thisFigure.shaderList.add(null);

        // Draw the 5 colored Olympic Rings
        x = startX - ((outerRadius*2) + gap);
        y = startY -(outerRadius*2);
        path = new Path(); // Blue ring
        path.addCircle(x, y, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorBlue);
        // Add gradient to make the appearance 3D and shiny.
        RadialGradient radBlue = new RadialGradient(
                x, y - (outerRadius*gradientPos),
                shaderHeight, colorShine, colorBlue, Shader.TileMode.CLAMP);
        thisFigure.shaderList.add(radBlue);

        x = startX;
        path = new Path(); // Black ring
        path.addCircle(x, y, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorBlack);
        RadialGradient radBlack = new RadialGradient(
                x, y - (outerRadius*gradientPos),
                shaderHeight, colorShine, colorBlack, Shader.TileMode.CLAMP);
        thisFigure.shaderList.add(radBlack);

        x += ((outerRadius*2) + gap);
        path = new Path(); // Red ring
        path.addCircle(x, y, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorRed);
        RadialGradient radRed = new RadialGradient(
                x, y - (outerRadius*gradientPos),
                shaderHeight, colorShine, colorRed, Shader.TileMode.CLAMP);
        thisFigure.shaderList.add(radRed);

        x -= (outerRadius + (gap/2));
        y += outerRadius;
        path = new Path(); // Green ring
        path.addCircle(x, y, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorGreen);
        RadialGradient radGreen = new RadialGradient(
                x, y - (outerRadius*gradientPos),
                shaderHeight, colorShine, colorGreen, Shader.TileMode.CLAMP);
        thisFigure.shaderList.add(radGreen);

        x -= ((outerRadius*2) + gap);
        path = new Path(); // Yellow ring
        path.addCircle(x, y, radius, Path.Direction.CW);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorYellow);
        RadialGradient radYellow = new RadialGradient(
                x, y - (outerRadius*gradientPos),
                shaderHeight, colorShine, colorYellow, Shader.TileMode.CLAMP);
        thisFigure.shaderList.add(radYellow);

        // Redraw small arc segments on each ring where it overlaps other ring(s).
        x = startX - ((outerRadius*2) + gap);
        y = startY -(outerRadius*2);
        path = new Path(); // Draw arc where Blue ring overlaps other ring(s).
        path.addArc(new RectF(x-radius, y-radius, x+radius, y+radius), -20f, 40f);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorBlue);
        thisFigure.shaderList.add(radBlue);

        x = startX;
        path = new Path(); // Draw arc where Black ring overlaps other ring(s).
        path.addArc(new RectF(x-radius, y-radius, x+radius, y+radius), -20f, 40f);
        path.addArc(new RectF(x-radius, y-radius, x+radius, y+radius), 80f, 40f);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorBlack);
        thisFigure.shaderList.add(radBlack);

        x += ((outerRadius*2) + gap);
        path = new Path(); // Draw arc where Red ring overlaps other ring(s).
        path.addArc(new RectF(x-radius, y-radius, x+radius, y+radius), 80f, 40f);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorRed);
        thisFigure.shaderList.add(radRed);

        x -= (outerRadius + (gap/2));
        y += outerRadius;
        path = new Path(); // Draw arc where Green ring overlaps other ring(s).
        path.addArc(new RectF(x-radius, y-radius, x+radius, y+radius), 160f, 40f);
        path.addArc(new RectF(x-radius, y-radius, x+radius, y+radius), 270f, 40f);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorGreen);
        thisFigure.shaderList.add(radGreen);

        x -= ((outerRadius*2) + gap);
        path = new Path(); // Draw arc where Yellow ring overlaps other ring(s).
        path.addArc(new RectF(x-radius, y-radius, x+radius, y+radius), 160f, 40f);
        path.addArc(new RectF(x-radius, y-radius, x+radius, y+radius), 270f, 40f);
        thisFigure.pathList.add(path);
        thisFigure.colorList.add(colorYellow);
        thisFigure.shaderList.add(radYellow);

        return thisFigure;
    }
}
