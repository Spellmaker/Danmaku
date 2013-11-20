package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.me.mygdxgame.Danmaku;

public class TitleScreen implements Screen {

	private final Danmaku owner;
	private OrthographicCamera camera;
	
	public TitleScreen(Danmaku g){
		this.owner = g;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
		camera.update();
		owner.batch.setProjectionMatrix(camera.combined);
	
		owner.batch.begin();
		owner.font.draw(owner.batch, "Danmaku", 800/2, 480/2);
		owner.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
