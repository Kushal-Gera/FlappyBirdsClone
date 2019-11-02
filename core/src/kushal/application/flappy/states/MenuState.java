package kushal.application.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.logging.Handler;

import kushal.application.flappy.FlappyDemo;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Texture title;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("background.png");
        playBtn = new Texture("playbtn.png");
        title = new Texture("flappyBird.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2 + 10, cam.position.y - 150, 80, 40);
        sb.draw(title, cam.position.x - title.getWidth() / 2 + 105, cam.position.y + 100, 200, 60);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }


}
