
package av.game.riftrivals;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class GameView extends View {

    private final Paint paint = new Paint();
    private Bitmap playerSprite;
    private Bitmap environmentAtlas;

    public GameView(Context context) {
        super(context);

        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);

        try (InputStream input =
                     context.getAssets().open(
                             "PNG/Tiles/Tilemap/tilemap.png")) {
            environmentAtlas = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load environment atlas", e);
        }

        try (InputStream input =
                     context.getAssets().open(
                             "PNG/Players/Tiles/tile_0000.png")) {
            playerSprite = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load player sprite", e);
        }

        if (environmentAtlas == null || playerSprite == null) {
            throw new IllegalStateException(
                    "A game image could not be decoded.");
        }
    }

    private void fill(Canvas canvas, int color,
                      float left, float top,
                      float right, float bottom) {
        paint.setColor(color);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    private void drawAtlasRegion(
            Canvas canvas,
            int sourceX, int sourceY,
            int sourceWidth, int sourceHeight,
            float left, float top,
            float right, float bottom) {

        if (sourceX < 0 || sourceY < 0
                || sourceX + sourceWidth > environmentAtlas.getWidth()
                || sourceY + sourceHeight > environmentAtlas.getHeight()) {
            return;
        }

        Rect source = new Rect(
                sourceX, sourceY,
                sourceX + sourceWidth,
                sourceY + sourceHeight);

        RectF destination = new RectF(
                left, top, right, bottom);

        paint.setFilterBitmap(false);
        canvas.drawBitmap(
                environmentAtlas, source, destination, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float w = getWidth();
        float h = getHeight();

        if (w <= 0 || h <= 0) return;

        canvas.drawColor(Color.rgb(35, 32, 55));

        float panelHeight = h * 0.20f;

        drawAtlasRegion(canvas,
                0, 0, 48, 32,
                0, 0, w / 3f, panelHeight);

        drawAtlasRegion(canvas,
                85, 0, 48, 32,
                w / 3f, 0, w * 2f / 3f, panelHeight);

        drawAtlasRegion(canvas,
                170, 0, 48, 32,
                w * 2f / 3f, 0, w, panelHeight);

        float floorY = h * 0.80f;

        fill(canvas, Color.rgb(68, 61, 83),
                0, floorY, w, h);

        fill(canvas, Color.rgb(130, 111, 157),
                0, floorY, w, floorY + h * 0.025f);

        fill(canvas, Color.rgb(112, 98, 143),
                w * 0.56f, h * 0.60f,
                w * 0.83f, h * 0.64f);

        fill(canvas, Color.rgb(48, 42, 65),
                w * 0.56f, h * 0.64f,
                w * 0.83f, h * 0.68f);

        drawCharacter(
                canvas,
                w * 0.25f,
                floorY,
                Math.min(w * 0.12f, h * 0.30f));

        paint.setColor(Color.WHITE);
        paint.setTextSize(Math.min(w * 0.045f, h * 0.09f));
        paint.setFakeBoldText(true);
        canvas.drawText("RIFT RIVALS",
                w * 0.04f, h * 0.12f, paint);
        paint.setFakeBoldText(false);
    }

    private void drawCharacter(Canvas canvas,
                               float x, float groundY,
                               float size) {
        if (playerSprite == null) return;

        paint.setFilterBitmap(false);

        canvas.drawBitmap(
                playerSprite,
                null,
                new RectF(
                        x, groundY - size,
                        x + size, groundY),
                paint);
    }
}
