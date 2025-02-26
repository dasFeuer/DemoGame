package demoGame.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class StickMan {
    private Texture texture;
    private float x, y;
    private float speed = 200f;
    private Rectangle bounds;

    public StickMan(float x, float y) {
        this.x = x;
        this.y = y;

        System.out.println("Working directory: " + Gdx.files.getLocalStoragePath());

        try {
            Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
            pixmap.setColor(1, 0, 0, 1);
            pixmap.fill();

            // simple stickman figure
            pixmap.setColor(0, 0, 0, 1);
            pixmap.fillCircle(32, 20, 10);
            pixmap.fillRectangle(30, 30, 4, 20);
            pixmap.drawLine(32, 35, 42, 45);
            pixmap.drawLine(32, 35, 22, 45);
            pixmap.drawLine(32, 50, 42, 60);
            pixmap.drawLine(32, 50, 22, 60);

            PixmapIO.writePNG(Gdx.files.local("stickman.png"), pixmap);
            pixmap.dispose();

            texture = new Texture(Gdx.files.local("stickman.png"));
        } catch (Exception e) {
            System.err.println("Failed to create and load stickman.png: " + e.getMessage());
            e.printStackTrace();
            // Create a fallback texture
            Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
            pixmap.setColor(1, 0, 0, 1); // Red color
            pixmap.fill();
            texture = new Texture(pixmap);
            pixmap.dispose();
        }

        bounds = new Rectangle(x, y, 64f, 64f);
    }

    public void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed * delta;
        }
        bounds.setPosition(x, y);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);
    }

    public void dispose(){
        if (texture != null) {
            texture.dispose();
        }
    }
}
