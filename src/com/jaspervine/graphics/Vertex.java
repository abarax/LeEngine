package com.jaspervine.graphics;

import com.jaspervine.math.Vector2;
import com.jaspervine.math.Vector3;

/**
 * Created with IntelliJ IDEA.
 * User: abarax
 * Date: 11/12/14
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vertex {
    private Vector3 position;
    private Vector2 textureCoordinates;

    public static final int SIZE = 5;

    public Vertex(Vector3 v) {
        this(v, new Vector2(0, 0));
    }

    public Vertex(float x, float y, float z) {
        position = new Vector3(x, y, z);
    }

    public Vertex(Vector3 v, Vector2 textureCoordinates) {
        this.position = v;
        this.textureCoordinates = textureCoordinates;
    }


    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector2 getTextureCoordinates() {
        return textureCoordinates;
    }

    public void setTextureCoordinates(Vector2 textureCoordinates) {
        this.textureCoordinates = textureCoordinates;
    }
}
