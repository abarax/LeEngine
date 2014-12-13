package com.jaspervine.graphics;

import com.jaspervine.core.Util;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Mesh
{
    private int vao;
    private int vbo;
    private int ibo;
    private int size;

    public Mesh()
    {

        size = 0;

        vbo = glGenBuffers();
        ibo = glGenBuffers();
        vao = glGenVertexArrays();

    }

    public void addVertices(Vertex[] vertices, int [] indices)
    {
        size = indices.length;

        glBindBuffer (GL_ARRAY_BUFFER, vbo);
        glBufferData (GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
        glBindVertexArray(vao);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);

    }

    public void draw()
    {
        glEnableVertexAttribArray (0);
        glBindBuffer (GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer (0, 3, GL_FLOAT, false, 0, 0);

        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
        // draw points 0-3 from the currently bound VAO with current in-use shader
        //glDrawArrays(GL_TRIANGLES, 0, 3);
        // update other events like input handling
    }
}