package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pong extends Game {
    SpriteBatch batch;
    BitmapFont bitmapFont;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bitmapFont = new BitmapFont(Gdx.files.internal("PongFont.fnt"));
        bitmapFont.setColor(1f, 1f, 1f, 1f);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        bitmapFont.dispose();
    }

    public static final int SCREEN_W = 800;
    public static final int SCREEN_H = 600;
}
