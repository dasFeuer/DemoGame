package demoGame.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


import java.awt.*;

public class StickMan {
    private Texture texture;
    private float x, y;
    private float speed = 200f;
    private Rectangle bounds;

    public StickMan(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Texture("stickman.png");
        bounds = new Rectangle(x, y, 64f, 64f);
    }

    public void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * delta;
        }
        bounds.setPosition(x, y);
    }
    public void render(SpriteBatch batch){
        batch.draw(texture, x, y);
    }
    public void dispose(){
        texture.dispose();
    }
}
