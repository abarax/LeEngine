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

    private static Shader shader;
    Mesh mesh;
    Mesh ground;

    Mesh level;

    Material material;
    Transform transform;
    Camera camera;
    Door door;

    PointLight p1 = new PointLight(new BaseLight(new Vector3(1,0.5f,0), 0.8f), new Vector3(-2f, 0, 5f), new Attenuation(0,0,1f), 10);
    PointLight p2 = new PointLight(new BaseLight(new Vector3(0,0.5f,1), 0.8f), new Vector3(2f, 0, 7f), new Attenuation(0,0,1f), 10);

    SpotLight s1 = new SpotLight(
            new PointLight(
                    new BaseLight(
                            new Vector3(0,0.5f,1), 0.8f),
                    new Vector3(2f, 0, 7f),
                    new Attenuation(1,1,1), 1),
            new Vector3(1, 1, 1),
            .7f
    );

    public Game(){

        //mesh = ResourceLoader.loadMesh("box.obj");

        mesh = new Mesh();

        material = new Material(ResourceLoader.loadTexture("bricks.jpg"), new Vector3(1, 1, 1), 1, 8);

        Vertex[] vertices = new Vertex[] {


                new Vertex(new Vector3(-1.0f, -1.0f, 0.5773f), new Vector2(0.0f, 0.0f)),
                new Vertex(new Vector3(0.0f, -1.0f, -1.15475f), new Vector2(0.5f, 0.0f)),
                new Vertex(new Vector3(1.0f, -1.0f, 0.5773f), new Vector2(1.0f, 0.0f)),
                new Vertex(new Vector3(0.0f, 1.0f, 0.0f), new Vector2(0.5f, 1.0f)) };

        int indices[] = {
                         0, 3, 1,
                         1, 3, 2,
                         2, 3, 0,
                         0, 1, 2

                 };

        mesh.addVertices(vertices, indices, true);

        ground = new Mesh();

        float groundWidth = 10;
        float groundDepth = 10;

        Vertex [] groundVertices = new Vertex[] {
                new Vertex(new Vector3(-groundWidth, 0.0f, -groundDepth), new Vector2(0.0f, 0.0f)),
                new Vertex(new Vector3(-groundWidth, 0.0f, groundDepth * 3), new Vector2(0.0f, 1.0f)),
                new Vertex(new Vector3(groundWidth * 3, 0.0f, -groundDepth), new Vector2(1.0f, 0.0f)),
                new Vertex(new Vector3(groundWidth * 3, 0.0f, groundDepth * 3), new Vector2(1.0f, 1.0f))
        };

        int [] groundFaces = new int [] {
                0, 1, 2,
                2, 1, 3
        };

        ground.addVertices(groundVertices, groundFaces, true);

        shader = BasicShader.getInstance();
        camera = new Camera();

        PhongShader.setAmbientLight(new Vector3(0.2f, 0.2f, 0.2f));
        PhongShader.setDirectionalLight(
            new DirectionalLight(
                new BaseLight(
                    new Vector3(1f, 1f, 1f),
                        0.8f
                ),
                new Vector3(1f, 1f, 1f)
            )
        );


        PhongShader.setPointLights(new PointLight[] {p1, p2});

        PhongShader.setSpotLights(new SpotLight[] {s1});

        transform = new Transform();
        transform.setProjection(50f, Window.getWidth(), Window.getHeight(), 0.01f, 100f);
        transform.setCamera(camera);


        Transform t = new Transform();

        t.setTranslation(7f, 1.0f, 7f);

        door = new Door(t, material);

       // level = ResourceLoader.loadMesh("level1.obj");

    }

    public void input() {

       camera.input();

    }

    float temp = 0.0f;

    public void update() {

        temp += Time.getDelta();

        float sin = (float)Math.sin(temp);

        transform.setTranslation(0, -1, 5);

        //transform.setRotation(0, sin * 180, 0);

        //transform.setScale(0.5f,0.5f , 0.5f);

        p1.setPosition(new Vector3(3, 0, (float)(8 * Math.sin(temp))));
        p2.setPosition(new Vector3(7, 0, (float)(8 * Math.cos(temp))));

        s1.getPointLight().setPosition(camera.getPosition());
        s1.setDirection(camera.getForward());
    }

    public void render() {

        shader.bind();


        //TRIANGLE 1
        Transform ttt = new Transform();

        ttt.setTranslation(0, 3.0f, 0f);

        shader.updateUniforms( ttt.getTransformation(), ttt.getProjectedTransformation(), material);
        ground.draw();

        mesh.draw();

        //TRIANGLE 2
        Transform tt = new Transform();

        tt.setTranslation(3f, -3.0f, 3f);
        tt.setScale(10, 10, 10);

        shader.updateUniforms(tt.getTransformation(), tt.getProjectedTransformation(), material);

        mesh.draw();

        //TRIANGLE 3
        Transform t = new Transform();

        t.setTranslation(7f, -1.0f, 7f);

        shader.updateUniforms(t.getTransformation(), t.getProjectedTransformation(), material);
        mesh.draw();

//        //TRIANGLE 4
        Transform t1 = new Transform();

        t1.setTranslation(6f, -1.0f, 6f);
//
        shader.updateUniforms(t1.getTransformation(), t1.getProjectedTransformation(), material);
//        mesh.draw();
//
//        //DOOR

//        level.draw();
//        Transform t2 = new Transform();
//        t1.setTranslation(0, 0f, 0f);
//        //shader.updateUniforms(t2.getTransformation(), t2.getProjectedTransformation(), material);
//        door.render();
//        door.render();


    }

    public static Shader getShader() {
        return shader;
    }
}
