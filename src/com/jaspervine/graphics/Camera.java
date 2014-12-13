package com.jaspervine.graphics;

import com.jaspervine.core.Input;
import com.jaspervine.core.Time;
import com.jaspervine.math.Vector3;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created with IntelliJ IDEA.
 * User: abarax
 * Date: 13/12/14
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class Camera {

    public static final Vector3 yAxis = new Vector3(0, 1, 0);

    private Vector3 position;
    private Vector3 forward;
    private Vector3 up;

    public Camera() {
        this(new Vector3(0,0,0), new Vector3(0,0,1), new Vector3(0,1,0));
    }

    public Camera (Vector3 position, Vector3 forward, Vector3 up) {
        this.position = position;
        this.forward = forward;
        this.up = up;

        forward.normalize();
        up.normalize();
    }

    public void input() {
        float movementAmount = (float)( 10.0f * Time.getDelta());
        float rotateAmount = (float)( 100.0f * Time.getDelta());

        if(Input.getKey(GLFW_KEY_W))
            move(getForward(), movementAmount);
        if(Input.getKey(GLFW_KEY_S))
            move(getForward(), -movementAmount);
        if(Input.getKey(GLFW_KEY_A))
            move(getLeft(), movementAmount);
        if(Input.getKey(GLFW_KEY_D))
            move(getRight(), movementAmount);

        if(Input.getKey(GLFW_KEY_UP))
            rotateY(-rotateAmount);
        if(Input.getKey(GLFW_KEY_DOWN))
            rotateY(rotateAmount);
        if(Input.getKey(GLFW_KEY_LEFT))
            rotateX(-rotateAmount);
        if(Input.getKey(GLFW_KEY_RIGHT))
            rotateX(rotateAmount);


    }

    public void move(Vector3 direction, float amount) {
        position = position.add(direction.multiply(amount));
    }

    public void rotateX(float angle) {
        Vector3 horizontal = yAxis.crossProduct(forward);
        horizontal.normalize();

        forward.rotate(angle, yAxis);
        forward.normalize();

        up = forward.crossProduct(horizontal);
        up.normalize();
    }

    public void rotateY(float angle) {
        Vector3 horizontal = yAxis.crossProduct(forward);
        horizontal.normalize();

        forward.rotate(angle, horizontal);
        forward.normalize();

        up = forward.crossProduct(horizontal);
        up.normalize();
    }

    public Vector3 getLeft(){
        Vector3 left = forward.crossProduct(up);
        left.normalize();
        return left;
    }

    public Vector3 getRight(){
        Vector3 right = up.crossProduct(forward);
        right.normalize();
        return right;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getForward() {
        return forward;
    }

    public void setForward(Vector3 forward) {
        this.forward = forward;
    }

    public Vector3 getUp() {
        return up;
    }

    public void setUp(Vector3 up) {
        this.up = up;
    }
}
