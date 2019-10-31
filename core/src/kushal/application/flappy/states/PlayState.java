package kushal.application.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import kushal.application.flappy.FlappyDemo;
import kushal.application.flappy.sprites.Bird;

public class PlayState extends State {
    private Bird bird;
    private Texture bg;

    protected PlayState(GameStateManager manager) {
        super(manager);
        bird = new Bird(50, 300);
        bg = new Texture("background.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);

    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg, cam.position.x - cam.viewportHeight/2, 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
