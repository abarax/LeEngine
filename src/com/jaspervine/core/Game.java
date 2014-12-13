package com.jaspervine.core;

import com.jaspervine.graphics.*;
import com.jaspervine.math.Vector2;
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
    Material material;
    Transform transform;
    Camera camera;

    public Game(){

        //mesh = ResourceLoader.loadMesh("box.obj");

        mesh = new Mesh();
        material = new Material(ResourceLoader.loadTexture("test.jpg"), new Vector3(0, 1, 1));

        Vertex [] vertices = new Vertex[] {
                new Vertex(new Vector3(-1, -1, 0), new Vector2(0, 0)),
                new Vertex(new Vector3(0, 1, 0), new Vector2(0.5f, 0)),
                new Vertex(new Vector3(1, -1, 0), new Vector2(1.0f, 0)),
                new Vertex(new Vector3(0, -1, 1), new Vector2(0, 0.5f))
        };

        int [] indices = new int [] {
                0, 1, 2,
                2, 1, 3,
                3, 1, 0,
                0, 2, 3
        };

        mesh.addVertices(vertices, indices);

        shader = PhongShader.getInstance();
        camera = new Camera();

        PhongShader.setAmbientLight(new Vector3(0.2f, 0.2f, 0.2f));


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

        //transform.setRotation(0, sin * 180, 0);

        //transform.setScale(0.5f,0.5f , 0.5f);
    }

    public void render() {

        shader.bind();
        shader.updateUniforms( transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();

    }
}
