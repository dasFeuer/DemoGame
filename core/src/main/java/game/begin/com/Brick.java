package game.begin.com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Brick {
    private Rectangle bounds;
    private Texture texture;
    private boolean destroyed = false;

    public Brick(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
        texture = new Texture("brick.png");
    }

    public void draw(SpriteBatch batch){
        if(!destroyed){
            batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public boolean checkCollision(Ball ball){
        if(!destroyed && bounds.overlaps(ball.getBounds())){
            destroyed = true;
            return true;
        }
        return false;
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public void dispose(){
        texture.dispose();
    }

}
