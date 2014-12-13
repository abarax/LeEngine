package com.jaspervine.core;

import com.jaspervine.graphics.*;
import com.jaspervine.math.Vector3;

import static org.lwjgl.glfw.GLFW.*;


/**
 * Created with IntelliJ IDEA.
 * User: abarax
 * Date: 11/12/14
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    Shader shader;
    Mesh mesh;
    Transform transform;
    Camera camera;

    public Game(){

        mesh = ResourceLoader.loadMesh("box.obj");

        shader = new Shader();
        camera = new Camera();

        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vert"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.frag"));

        shader.compileShader();

        shader.addUniform("transform");

        transform = new Transform();
        transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f);
        transform.setCamera(camera);



    }

    public void input() {

       camera.input();

    }

    float temp = 0.0f;

    public void update() {

        temp += Time.getDelta();

        float sin = (float)Math.sin(temp);

        transform.setTranslation(sin, 0, 5);

        transform.setRotation(0, sin * 180, 0);

        //transform.setScale(0.5f,0.5f , 0.5f);
    }

    public void render() {

        shader.bind();
        shader.setUniform("transform", transform.getProjectedTransformation());
        mesh.draw();

    }
}
