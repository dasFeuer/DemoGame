package stickMan.com;

import com.badlogic.gdx.Game;

public class StickmanGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}

