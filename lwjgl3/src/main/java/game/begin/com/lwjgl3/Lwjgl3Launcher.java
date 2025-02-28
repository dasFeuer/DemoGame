package game.begin.com.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.begin.com.BrickBreakerGame;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Brick Breaker");
        config.setWindowedMode(800, 600);
        new Lwjgl3Application(new BrickBreakerGame(), config);
    }
}
