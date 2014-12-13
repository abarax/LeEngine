package com.jaspervine.graphics;

import com.jaspervine.core.Input;
import com.jaspervine.core.Time;
import com.jaspervine.core.Window;
import com.jaspervine.math.Vector2;
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

    private boolean mouseLocked = false;
    Vector2 centerPosition = new Vector2(Window.getWidth()/2, Window.getHeight()/2);
    boolean rotX = false;
    boolean rotY = false;

    double prevX;
    double prevY;

    public Camera() {
        this(new Vector3(0,0,0), new Vector3(0,0,1), new Vector3(0,1,0));
    }

    public Camera (Vector3 position, Vector3 forward, Vector3 up) {
        this.position = position;
        this.forward = forward.normalized();
        this.up = up.normalized();
    }

    public void input() {
        float movementAmount = (float)( 10.0f * Time.getDelta());
        float sensitivity = 0.8f;

        float rotateAmount = (float)( 100.0f * Time.getDelta());

        if(Input.getKey(GLFW_KEY_ESCAPE)){
            Input.setCursor(true);
            mouseLocked = false;
        }

        if (Input.getMouse(GLFW_MOUSE_BUTTON_1)) {
            Input.setMousePosition(centerPosition);
           // Input.setCursor(false);
            mouseLocked = true;
            prevX = centerPosition.getX();
            prevY = centerPosition.getY();

        }

//        if (Input.getMouse(GLFW_MOUSE_BUTTON_1)) {
//            System.out.println("Mouse Position: " + Input.getMousePosition());
//        }

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

        if (mouseLocked) {

            Vector2 input = Input.getMousePosition();

            Vector2 deltaPos = new Vector2((float) (input.getX() - prevX), (float) (input.getY() - prevY));

            rotX = deltaPos.getX() != 0;

            rotY = deltaPos.getY() != 0;

            if(rotY) {
                rotateY(deltaPos.getY() * sensitivity * (float)Time.getDelta());
            }
            if(rotX) {
                rotateX(deltaPos.getX() * sensitivity * (float)Time.getDelta());
            }

            if(rotY || rotX) {
                //Input.setMousePosition(new Vector2(Window.getWidth()/2, Window.getHeight()/2));
                prevX = centerPosition.getX();
                prevY = centerPosition.getY();
                rotY = false;
                rotX = false;
            }
        }


    }

    public void move(Vector3 direction, float amount) {
        position = position.add(direction.multiply(amount));
    }

    public void rotateX(float angle) {
        Vector3 horizontal = yAxis.crossProduct(forward).normalized();

        this.forward = forward.rotate(angle, yAxis).normalized();
        this.up = forward.crossProduct(horizontal).normalized();
    }

    public void rotateY(float angle) {
        Vector3 horizontal = yAxis.crossProduct(forward).normalized();

        this.forward = forward.rotate(angle, horizontal).normalized();
        this.up = forward.crossProduct(horizontal).normalized();
    }

    public Vector3 getLeft(){
        Vector3 left = forward.crossProduct(up).normalized();
        return left;
    }

    public Vector3 getRight(){
        Vector3 right = up.crossProduct(forward).normalized();
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
