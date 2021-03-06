package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    protected ShapeRenderer shapeRenderer;
    protected Vector2 pos;
    protected int width, height;
    protected Color color;

    public GameObject(Vector2 pos, int width, int height, Color color) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.color = color;
        this.shapeRenderer = new ShapeRenderer();
    }

    public int top() {
        return (int) (pos.y + height);
    }

    public int bottom() {
        return (int) pos.y;
    }

    public int left() {
        return (int) (pos.x);
    }

    public int right() {
        return (int) (pos.x + width);
    }

    public Vector2 center() {
        return new Vector2(this.pos.x + (this.width / 2f), this.pos.y + (this.height / 2f));
    }

    public void update() {

    }

    public void draw(SpriteBatch batch) {
        this.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(this.color);
        this.shapeRenderer.rect(pos.x, pos.y, width, height);
        this.shapeRenderer.end();
    }
}
