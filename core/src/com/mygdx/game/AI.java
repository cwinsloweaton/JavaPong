package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class AI extends Paddle {
    private int speed = 5;
    private Ball ball;

    public AI(Vector2 pos, Ball ball) {
        super(pos);
        this.ball = ball;
    }

    @Override
    public void update() {
        super.update();
        Vector2 target = new Vector2(pos.x, ball.pos.y - (height / 2f) + (ball.height / 2f));
        pos.lerp(target, speed * Gdx.graphics.getDeltaTime());
    }
}
