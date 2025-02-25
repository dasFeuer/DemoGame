import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import demoGame.com.StickmanGame;

public class StickmanGameDesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Stickman Game");
        config.setWindowedMode(800, 480);

        new Lwjgl3Application(new StickmanGame(), config);
    }
}
