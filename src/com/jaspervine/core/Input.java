package com.jaspervine.core;

import com.jaspervine.math.Vector2;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: abarax
 * Date: 11/12/14
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Input {

    public static final int NUM_KEYCODES = 349;
    public static final int NUM_MOUSE_BUTTONS = 5;

    private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> upKeys = new ArrayList<Integer>();

    private static ArrayList<Integer> currentMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> downMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> upMouse = new ArrayList<Integer>();

    public static void update() {

        upKeys.clear();

        for(int i = 0; i < NUM_KEYCODES; i++) {
            if(!getKey(i) && currentKeys.contains(i)) {
                upKeys.add(i);
            }
        }
        upMouse.clear();

        for(int i = 0; i < NUM_MOUSE_BUTTONS; i++) {
            if(!getMouse(i) && currentMouse.contains(i)) {
                upMouse.add(i);
            }
        }

        downKeys.clear();

        for(int i = 0; i < NUM_KEYCODES; i++) {
            if(getKey(i) && !currentKeys.contains(i)) {
                downKeys.add(i);
            }
        }

        downMouse.clear();

        for(int i = 0; i < NUM_MOUSE_BUTTONS; i++) {
            if(getMouse(i) && !currentMouse.contains(i)) {
                downMouse.add(i);
            }
        }

        currentKeys.clear();

        for(int i = 0; i < NUM_KEYCODES; i++) {
            if(getKey(i)) {
                currentKeys.add(i);
            }
        }

        currentMouse.clear();

        for(int i = 0; i < NUM_MOUSE_BUTTONS; i++) {
            if(getMouse(i)){
                currentMouse.add(i);
            }
        }


    }

    public static boolean getKey(int keyCode) {
        return GLFW.glfwGetKey(Window.getWindow(), keyCode) == GLFW.GLFW_PRESS;
    }

    public static boolean getKeyDown(int keyCode) {
       return downKeys.contains(keyCode);
    }

    public static boolean getKeyUp(int keyCode) {
        return upKeys.contains(keyCode);
    }

    public static boolean getMouse(int mouseButton){
        return GLFW.glfwGetMouseButton(Window.getWindow(), mouseButton) == GLFW.GLFW_PRESS;
    }

    public static boolean getMouseDown(int mouseButton) {
        return downMouse.contains(mouseButton);
    }

    public static boolean getMouseUp(int mouseButton) {
        return upMouse.contains(mouseButton);
    }

    public static Vector2 getMousePosition() {
        DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

        GLFW.glfwGetCursorPos(Window.getWindow(), x, y);
        return new Vector2((float)x.get(0), (float)y.get(0));
    }
}
