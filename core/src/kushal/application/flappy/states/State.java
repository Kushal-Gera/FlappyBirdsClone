package kushal.application.flappy.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

abstract public class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;


    protected State(GameStateManager manager){

        this.gsm = manager;
        cam = new OrthographicCamera();
        mouse = new Vector3();

    }


    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();



}
