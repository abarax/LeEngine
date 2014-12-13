package com.jaspervine.graphics;

import com.jaspervine.core.ResourceLoader;
import com.jaspervine.math.Matrix4;
import com.jaspervine.math.Vector3;

/**
 * Created with IntelliJ IDEA.
 * User: abarax
 * Date: 13/12/14
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhongShader extends Shader {

    private static final PhongShader instance = new PhongShader();

    private static Vector3 ambientLight;

    public static PhongShader getInstance() {
        return instance;
    }

    private PhongShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("phongVertex.vert"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.frag"));

        compileShader();

        addUniform("transform");
        addUniform("baseColour");
        addUniform("ambientLight");
    }

    public void updateUniforms(Matrix4 worldMatrix, Matrix4 projectedMatrix, Material material) {

        if( material.getTexture() != null) {
            material.getTexture().bind();
        } else {
            RenderUtil.unbindTextures();
        }

        setUniform("transform", projectedMatrix);
        setUniform("baseColour", material.getColour());
        setUniform("ambientLight", ambientLight);
    }

    public static Vector3 getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3 ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }
}
