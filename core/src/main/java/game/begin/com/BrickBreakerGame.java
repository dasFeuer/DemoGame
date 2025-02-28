package game.begin.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class BrickBreakerGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private int score = 0;
    private int lives = 3;
    private boolean gameOver = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);

        // Initialize paddle
        float paddleWidth = 100;
        float paddleHeight = 20;
        paddle = new Paddle(
            Gdx.graphics.getWidth() / 2 - paddleWidth / 2,
            20,
            paddleWidth,
            paddleHeight
        );

        ball = new Ball(
            Gdx.graphics.getWidth() / 2 - 10,
            paddle.getBounds().y + paddle.getBounds().height + 5,
            20
        );

        createBricks();
    }

    private void createBricks() {
        bricks = new ArrayList<>();
        int rows = 5;
        int cols = 8;
        float brickWidth = (Gdx.graphics.getWidth() - 100) / cols;
        float brickHeight = 30;
        float startX = 50;
        float startY = Gdx.graphics.getHeight() - 150;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                bricks.add(new Brick(
                    startX + col * brickWidth,
                    startY - row * brickHeight,
                    brickWidth - 5,
                    brickHeight - 5
                ));
            }
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(Gdx.graphics.getDeltaTime());

        batch.begin();

        if (!gameOver) {
            paddle.draw(batch);
            ball.draw(batch);

            for (Brick brick : bricks) {
                brick.draw(batch);
            }

            // Draw score and lives
            font.draw(batch, "Score: " + score, 20, Gdx.graphics.getHeight() - 20);
            font.draw(batch, "Lives: " + lives, Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 20);
        } else {
            font.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() / 2);
            font.draw(batch, "Final Score: " + score, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 40);
            font.draw(batch, "Press SPACE to restart", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 - 80);
        }

        batch.end();
    }

    private void update(float delta) {
        if (gameOver) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                score = 0;
                lives = 3;
                gameOver = false;
                createBricks();
            }
            return;
        }

        paddle.update(delta);

        if (!ball.isActive()) {
            ball.stickToPaddle(paddle);

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                ball.start();
            }
        }

        ball.update(delta);

        ball.checkPaddleCollision(paddle);

        for (Brick brick : bricks) {
            if (brick.checkCollision(ball)) {
                ball.reverseYVelocity();

                score += 10;

                boolean allDestroyed = true;
                for (Brick b : bricks) {
                    if (!b.isDestroyed()) {
                        allDestroyed = false;
                        break;
                    }
                }

                if (allDestroyed) {
                    createBricks();
                    ball.reset(paddle);
                }

                break;
            }
        }

        if (ball.isBelowScreen()) {
            lives--;

            if (lives <= 0) {
                gameOver = true;
            } else {
                ball.reset(paddle);
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        paddle.dispose();
        ball.dispose();

        for (Brick brick : bricks) {
            brick.dispose();
        }
    }
}
