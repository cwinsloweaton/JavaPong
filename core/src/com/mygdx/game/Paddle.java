package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends GameObject {
    final int speed = 5;

    public Paddle(Vector2 pos) {
        super(pos, 24, 100, new Color(0f, 1f, 0f, 1f));
    }
}
