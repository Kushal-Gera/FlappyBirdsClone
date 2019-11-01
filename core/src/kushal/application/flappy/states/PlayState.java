package kushal.application.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import kushal.application.flappy.FlappyDemo;
import kushal.application.flappy.sprites.Bird;
import kushal.application.flappy.sprites.Tube;

public class PlayState extends State {
    private Bird bird;
    private Texture bg;
    private Texture ground;

    private Vector2 groundPos1, groundPos2;

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private static final int GROUND_Y_OFFSET = -50;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager manager) {
        super(manager);
        bird = new Bird(50, 300);

        bg = new Texture("background.png");
        ground = new Texture("ground.png");
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);

        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(cam.position.x - cam.viewportWidth/2 + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

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
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < TUBE_COUNT; i++) {
            Tube tube = tubes.get(i);

            if (cam.position.x - (cam.viewportWidth)/2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.rePosition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING)*TUBE_COUNT);
            }

            if (tube.collides(bird.getBounds()))
                gsm.set(new GameOverState(gsm));
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            bird.setPosition(new Vector3(bird.getPosition().x, ground.getHeight() + GROUND_Y_OFFSET, 0));
//            gsm.set(new PlayState(gsm));
        }

        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg, cam.position.x - cam.viewportHeight/2, 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube: tubes){

            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);

        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube: tubes){
            tube.dispose();
        }
    }

    public void updateGround(){

        if (cam.position.x - cam.viewportWidth/2 >groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth()*2, 0);
        if (cam.position.x - cam.viewportWidth/2 >groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth()*2, 0);

    }


}
