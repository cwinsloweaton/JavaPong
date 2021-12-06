package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Pong.SCREEN_H;
import static com.mygdx.game.Pong.SCREEN_W;


public class GameScreen implements Screen {

    final Pong game;

    OrthographicCamera camera;

    Player player;
    Ball ball;
    AI ai;

    int playerScore = 0;
    int aiScore = 0;

    public GameScreen(final Pong game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_W, SCREEN_H);

        player = new Player(new Vector2(100, 100));
        ball = new Ball(new Vector2(400, 300), this);
        ai = new AI(new Vector2(600, 100), ball);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        player.update();
        ai.update();
        ball.update(player, ai);

        if (playerScore >= 5) {
            game.setScreen(new GameOverScreen(game, true));
        } else if (aiScore >= 5) {
            game.setScreen(new GameOverScreen(game, false));
        }

        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);
        game.batch.begin();

        player.draw(game.batch);
        ai.draw(game.batch);
        ball.draw(game.batch);

        game.batch.end();

        game.batch.begin();
        game.bitmapFont.draw(game.batch, Integer.toString(playerScore), 25, 50);
        game.bitmapFont.draw(game.batch, Integer.toString(aiScore), Gdx.graphics.getWidth() - 50, 50);
        game.batch.end();
    }

    public void incrementPlayerScore() {
        playerScore++;
        reset();
    }

    public void incrementAiScore() {
        aiScore++;
        reset();
    }

    private void reset() {
        // Reset the ball.
        ball.reset();
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
