package com.scconsulting.olympicrings;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class Rings extends Activity {

    public static final int ShapeOlympicRings = 1;
    private Figure figure;
    private Paint paint;

    private float startX; // Center x
    private float stopX;  // Center x
    private float startY; // Bottom
    private float stopY; // Top


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new DrawingView(this));
        paint = new Paint();
        initializePaint();
    }

    protected void onResume() {
        super.onResume();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        startX = dm.widthPixels / 2.0f;
        stopX = dm.widthPixels / 2.0f;
        if (dm.widthPixels > dm.heightPixels) {
            startY = dm.heightPixels * 0.8f;
            stopY = dm.heightPixels * 0.2f;
        } else {
            float ringRadius = ((float) dm.widthPixels) / 7.5f;
            float ringsHeight = ringRadius * 3.0f;
            startY = ((dm.heightPixels/2.0f) + (ringsHeight / 2.0f));
            stopY = ((dm.heightPixels/2.0f) - (ringsHeight / 2.0f));
        }

        figure = new Figure();
        OlympicRings.drawFigure(figure, startX, startY, stopX, stopY);
    }

    private void initializePaint() {
        paint.setDither(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeWidth(0.0f);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class DrawingView extends View {
        public DrawingView(Context context) {
            super(context);
      }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            paint.setStrokeWidth(figure.getStrokeWidth());
            for (int i=0; i<figure.pathList.size(); i++) {
                paint.setColor(figure.colorList.get(i));
                paint.setShader(figure.shaderList.get(i));

                canvas.drawPath(figure.pathList.get(i), paint);
            }
        }
    }
}