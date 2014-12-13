package com.jaspervine.graphics;

import com.jaspervine.math.Vector2;
import com.jaspervine.math.Vector3;

/**
 * Created with IntelliJ IDEA.
 * User: abarax
 * Date: 13/12/14
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Material {
    private Texture texture;
    private Vector3 colour;

    public Material(Texture texture, Vector3 colour) {
        this.texture = texture;
        this.colour = colour;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector3 getColour() {
        return colour;
    }

    public void setColour(Vector3 colour) {
        this.colour = colour;
    }
}
