package game.begin.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    private Rectangle bounds;
    private Texture texture;
    private Vector2 velocity;
    private boolean active = false;

    public Ball(float x, float y, float size) {
        bounds = new Rectangle(x, y, size, size);
        texture = new Texture("ball.png");
        velocity = new Vector2(300, -300);
    }

    public void update(float delta) {
        if (!active) {
            return;
        }

        bounds.x += velocity.x * delta;
        bounds.y += velocity.y * delta;

        if (bounds.x <= 0 || bounds.x + bounds.width >= Gdx.graphics.getWidth()) {
            velocity.x = -velocity.x;
        }

        if (bounds.y + bounds.height >= Gdx.graphics.getHeight()) {
            velocity.y = -velocity.y;
        }

        if (bounds.y < 0) {

            active = false;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void start() {
        active = true;
    }

    public void stickToPaddle(Paddle paddle) {
        if (!active) {
            bounds.x = paddle.getBounds().x + paddle.getBounds().width / 2 - bounds.width / 2;
            bounds.y = paddle.getBounds().y + paddle.getBounds().height;
        }
    }

    public void checkPaddleCollision(Paddle paddle) {
        if (bounds.overlaps(paddle.getBounds())) {
            velocity.y = Math.abs(velocity.y);

            float relativeIntersectX = (paddle.getBounds().x + (paddle.getBounds().width / 2)) - (bounds.x + (bounds.width / 2));
            float normalizedRelativeIntersectionX = relativeIntersectX / (paddle.getBounds().width / 2);
            float bounceAngle = normalizedRelativeIntersectionX * 60;

            velocity.x = (float) (-Math.sin(Math.toRadians(bounceAngle)) * getSpeed());
            velocity.y = (float) Math.abs(Math.cos(Math.toRadians(bounceAngle)) * getSpeed());
        }
    }

    private float getSpeed() {
        return (float) Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y);
    }

    public boolean isActive() {
        return active;
    }

    public void reset(Paddle paddle) {
        active = false;
        velocity.set(300, -300);
        stickToPaddle(paddle);
    }

    public void reverseYVelocity() {
        velocity.y = -velocity.y;
    }

    public boolean isBelowScreen() {
        return bounds.y < 0;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
