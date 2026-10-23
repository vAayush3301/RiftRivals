package av.game.riftrivals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class GameView extends View {

    private final Paint paint = new Paint();

    public GameView(Context context) {
        super(context);
        paint.setAntiAlias(false);
    }

    private void fill(Canvas canvas, int color,
                      float left, float top,
                      float right, float bottom) {
        paint.setColor(color);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float w = getWidth();
        float h = getHeight();

        if (w == 0 || h == 0) return;

        canvas.drawColor(Color.rgb(111, 196, 224));

        paint.setColor(Color.rgb(255, 221, 105));
        canvas.drawCircle(w * 0.82f, h * 0.20f,
                Math.min(w, h) * 0.09f, paint);

        paint.setColor(Color.rgb(224, 143, 104));
        PathHelper.drawMountain(canvas, paint,
                w * 0.02f, h * 0.68f,
                w * 0.25f, h * 0.30f,
                w * 0.46f, h * 0.68f);

        PathHelper.drawMountain(canvas, paint,
                w * 0.35f, h * 0.68f,
                w * 0.62f, h * 0.37f,
                w * 0.90f, h * 0.68f);

        fill(canvas, Color.rgb(231, 178, 105),
                0, h * 0.70f, w, h);

        fill(canvas, Color.rgb(187, 119, 65),
                0, h * 0.84f, w, h);

        fill(canvas, Color.rgb(125, 78, 55),
                w * 0.56f, h * 0.59f,
                w * 0.79f, h * 0.64f);

        fill(canvas, Color.rgb(238, 185, 105),
                w * 0.56f, h * 0.58f,
                w * 0.79f, h * 0.60f);

        drawCharacter(canvas, w * 0.27f, h * 0.51f,
                Math.min(w * 0.12f, h * 0.30f));

        paint.setColor(Color.WHITE);
        paint.setTextSize(Math.min(w * 0.045f, h * 0.09f));
        paint.setFakeBoldText(true);
        canvas.drawText("RIFT RIVALS",
                w * 0.04f, h * 0.12f, paint);
        paint.setFakeBoldText(false);
    }

    private void drawCharacter(Canvas canvas,
                               float x, float y, float s) {

        paint.setColor(Color.rgb(145, 93, 56));
        canvas.drawOval(
                new RectF(x - s * 0.05f, y + s * 1.65f,
                        x + s * 1.05f, y + s * 1.82f),
                paint);

        fill(canvas, Color.rgb(49, 63, 83),
                x + s * 0.20f, y + s * 1.10f,
                x + s * 0.43f, y + s * 1.65f);

        fill(canvas, Color.rgb(49, 63, 83),
                x + s * 0.58f, y + s * 1.10f,
                x + s * 0.81f, y + s * 1.65f);

        fill(canvas, Color.rgb(54, 43, 43),
                x + s * 0.12f, y + s * 1.55f,
                x + s * 0.46f, y + s * 1.72f);

        fill(canvas, Color.rgb(54, 43, 43),
                x + s * 0.55f, y + s * 1.55f,
                x + s * 0.91f, y + s * 1.72f);

        fill(canvas, Color.rgb(64, 147, 106),
                x + s * 0.12f, y + s * 0.58f,
                x + s * 0.90f, y + s * 1.22f);

        fill(canvas, Color.rgb(42, 104, 82),
                x + s * 0.43f, y + s * 0.62f,
                x + s * 0.59f, y + s * 1.15f);

        fill(canvas, Color.rgb(226, 164, 119),
                x - s * 0.02f, y + s * 0.65f,
                x + s * 0.18f, y + s * 1.02f);

        fill(canvas, Color.rgb(226, 164, 119),
                x + s * 0.82f, y + s * 0.65f,
                x + s * 1.02f, y + s * 0.95f);

        fill(canvas, Color.rgb(226, 164, 119),
                x + s * 0.22f, y + s * 0.16f,
                x + s * 0.78f, y + s * 0.65f);

        fill(canvas, Color.rgb(54, 43, 43),
                x + s * 0.19f, y + s * 0.10f,
                x + s * 0.80f, y + s * 0.27f);

        fill(canvas, Color.BLACK,
                x + s * 0.35f, y + s * 0.34f,
                x + s * 0.42f, y + s * 0.42f);

        fill(canvas, Color.BLACK,
                x + s * 0.62f, y + s * 0.34f,
                x + s * 0.69f, y + s * 0.42f);

        fill(canvas, Color.rgb(164, 113, 58),
                x - s * 0.12f, y + s * 0.64f,
                x + s * 0.12f, y + s * 1.06f);
    }

    private static class PathHelper {
        static void drawMountain(Canvas canvas, Paint paint,
                                 float x1, float y1,
                                 float x2, float y2,
                                 float x3, float y3) {
            android.graphics.Path path =
                    new android.graphics.Path();

            path.moveTo(x1, y1);
            path.lineTo(x2, y2);
            path.lineTo(x3, y3);
            path.close();

            paint.setColor(Color.rgb(224, 143, 104));
            canvas.drawPath(path, paint);
        }
    }
}
