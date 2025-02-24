package demoGame.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Core extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture playerImage;
    private float playerX;
    private float playerY;

    @Override
    public void create(){
        batch = new SpriteBatch();
        playerImage = new Texture("1.jpg");

        playerX = 100;
        playerY = 100;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            playerX += 2;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            playerX -= 2;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            playerY += 2;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            playerY -= 2;
        }

        batch.begin();
        batch.draw(playerImage, playerX, playerY);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        playerImage.dispose();
    }
}
