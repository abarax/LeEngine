package com.jaspervine.core;

import com.jaspervine.graphics.*;
import com.jaspervine.math.Vector2;
import com.jaspervine.math.Vector3;

/**
 * Created with IntelliJ IDEA.
 * User: abarax
 * Date: 20/12/14
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Door {

    private static Mesh mesh;
    private Material material;

    private Transform transform;
    private Shader shader;

    public Door(Transform transform, Material material) {

        this.transform = transform;
        this.material = material;

        if(mesh == null) {
            mesh = new Mesh();

            Vertex[] vertices = new Vertex[] {
                    new Vertex(new Vector3(0, 0, 0), new Vector2(0, 0)),
                    new Vertex(new Vector3(0, 1, 0), new Vector2(0, 0)),
                    new Vertex(new Vector3(1, 1, 0), new Vector2(0, 0)),
                    new Vertex(new Vector3(1, 0, 0), new Vector2(0, 0))};

            int [] indices = new int[] {0,1,2,
                                        0,2,3};

            mesh.addVertices(vertices, indices);
        }
    }

    public void update() {

    }

    public void render() {

        shader = Game.getShader();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
    }
}
