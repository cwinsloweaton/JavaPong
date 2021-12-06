package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Pong.SCREEN_H;
import static com.mygdx.game.Pong.SCREEN_W;

public class GameOverScreen implements Screen {

    final Pong game;

    OrthographicCamera camera;

    boolean playerWin;
    public GameOverScreen(final Pong game, boolean playerWin) {
        this.game = game;
        this.playerWin = playerWin;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_W, SCREEN_H);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.3f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        if (playerWin) {
            game.bitmapFont.draw(game.batch, "You win!", 100, 100);
        } else {
            game.bitmapFont.draw(game.batch, "You lose!", 100, 100);
        }
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
