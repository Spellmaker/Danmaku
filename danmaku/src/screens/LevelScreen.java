package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.Danmaku;

public class LevelScreen implements Screen{
	private Danmaku owner;
	private Texture background;
	private OrthographicCamera camera;
	
	public LevelScreen(Danmaku owner){
		this.owner = owner;
		background = new Texture(Gdx.files.internal("backgrounds\\game_back.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 960);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
		camera.update();
		owner.batch.setProjectionMatrix(camera.combined);
	
		owner.batch.begin();
		owner.batch.draw(background, 0, 0, 0, 0, 1600, 960);
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
