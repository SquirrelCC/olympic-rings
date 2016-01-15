package com.scconsulting.olympicrings;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import java.util.ArrayList;

/**
 * Created by Shirley Christenson on 1/14/2016.
 *
 * A collection of Paths (android.graphics.Path) that defines an object to be drawn on a Canvas.
 * Each Path is stored in the ArrayList pathList.
 * The ArrayList colorList contains the int color associated with each Path.
 * The ArrayList shaderList contains the RadialGradient associated with each Path.
 */
public class Figure extends Object {

    public int figureType;
    public int colorShine = 0XFFFFFFFF;
    public float strokeWidth;
    public Paint.Cap strokeCap;
    public Paint.Join strokeJoin;
    public Paint.Style paintStyle;

    public ArrayList<Path> pathList;
    public ArrayList<Integer> colorList;
    public ArrayList<RadialGradient> shaderList;

    public Figure(){
        pathList = new ArrayList<Path>();
        colorList = new ArrayList<Integer>();
        shaderList = new ArrayList<RadialGradient>();
        strokeWidth = 2;
        paintStyle = Paint.Style.STROKE;
        strokeCap = Paint.Cap.ROUND;
        figureType = -1;
        colorList.add(null);
        pathList.add(null);
        shaderList.add(null);
    }

    public Figure(int figureType,
                  Paint.Style paintStyle,
                  float strokeWidth,
                  ArrayList<Path> pList,
                  ArrayList<Integer> cList,
                  ArrayList<RadialGradient> sList) {

        this.figureType = figureType;
        this.paintStyle = paintStyle;
        this.strokeWidth = strokeWidth;
        this.strokeCap = Paint.Cap.ROUND;

        this.pathList = new ArrayList<Path>();
        for (int i=0; i<pList.size(); i++) {
            this.pathList.add(pList.get(i));
        }
        this.colorList = new ArrayList<Integer>();
        for (int i=0; i<cList.size(); i++) {
            this.colorList.add(cList.get(i));
        }
        this.shaderList = new ArrayList<RadialGradient>();
        for (int i=0; i<sList.size(); i++) {
            this.shaderList.add(sList.get(i));
        }
    }

    public void setStrokeWidth(float w){
        strokeWidth = w;
    }

    public float getStrokeWidth(){
        return strokeWidth;
    }

    public void setPaintStyle(Paint.Style ps){
        paintStyle = ps;
    }

    public Paint.Style getPaintStyle(){
        return paintStyle;
    }

    public void setfigureType(int type){
        figureType = type;
    }

    public int getfigureType() {
        return figureType;
    }
}