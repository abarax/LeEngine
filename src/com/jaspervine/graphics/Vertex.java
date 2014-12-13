package com.jaspervine.graphics;

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

    public static final int SIZE = 3;

    public Vertex(Vector3 v) {
        position = v;
    }

    public Vertex(float x, float y, float z) {
        position = new Vector3(x, y, z);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }
}
