package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ball extends GameObject {
    private float speed;
    private Vector2 dir;
    private GameScreen game;
    private float maxSpeed;

    public Ball(Vector2 pos, GameScreen game) {
        super(pos, 24, 24, new Color(0f, 0f, 1f, 1f));
        this.speed = 300f;
        this.maxSpeed = 1000f;
        this.dir = new Vector2(-1, 0f);
        this.dir.nor();
        this.game = game;
    }

    public void reset() {
        this.pos.y = (Gdx.graphics.getHeight() / 2f) - (this.height / 2f);
        this.pos.x = (Gdx.graphics.getWidth() / 2f) - (this.width / 2f);
        this.dir.y = 0f;
        this.dir.x = -1f;
        this.speed = 300f;
    }

    private boolean checkOverlapping(GameObject other) {
        return this.left() < other.right() &&
                this.right() > other.left() &&
                this.top() > other.bottom() &&
                this.bottom() < other.top();
    }

    private void increaseSpeed() {
        if (this.speed < this.maxSpeed) {
            this.speed += 100;
        }
    }

    public void update(Player player, AI ai) {
        super.update();
        float dt = Gdx.graphics.getDeltaTime();

        Vector2 movement = dir.cpy().scl(speed * dt);

        this.pos.x += movement.x;
        this.pos.y += movement.y;

        // Check if we are intersecting with a paddle.
        if (checkOverlapping(player)) {
            float colHeightOnPaddle = player.center().y - center().y;

            // Range is +- half paddle height + half ball height - 1
            this.dir.y = MathUtils.map(61f, -61f, -1f, 1f, colHeightOnPaddle);
            this.dir.nor();
            this.dir.x *= -1;
            this.pos.x = player.right();
            increaseSpeed();
        } else if (checkOverlapping(ai)) {
            float colHeightOnPaddle = ai.center().y - center().y;

            // Range is +- half paddle height + half ball height - 1
            this.dir.y = MathUtils.map(61f, -61f, -1f, 1f, colHeightOnPaddle);
            this.dir.nor();
            this.dir.x *= -1;
            this.pos.x = ai.left() - width;
            increaseSpeed();
        }

        // Bounce off the top and bottom walls.
        if (bottom() < 0) {
            this.pos.y = 0;
            this.dir.y *= -1;
        } else if (top() > Gdx.graphics.getHeight()) {
            this.pos.y = Gdx.graphics.getHeight() - height;
            this.dir.y *= -1;
        }

        if (left() < 0) {
            // AI scores
            this.game.incrementAiScore();
        } else if (right() > Gdx.graphics.getWidth()) {
            // Player scores
            this.game.incrementPlayerScore();
        }

    }
}
