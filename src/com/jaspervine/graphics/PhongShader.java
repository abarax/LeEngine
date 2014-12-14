package com.jaspervine.graphics;

import com.jaspervine.core.ResourceLoader;
import com.jaspervine.math.Matrix4;
import com.jaspervine.math.Vector3;

import static org.lwjgl.opengl.GL20.glUniform1f;

/**
 * Created with IntelliJ IDEA.
 * User: abarax
 * Date: 13/12/14
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhongShader extends Shader {

    private static final PhongShader instance = new PhongShader();

    private static Vector3 ambientLight = new Vector3(0.5f, 0.5f, 0.5f);
    private static DirectionalLight directionalLight = new DirectionalLight(
            new BaseLight(new Vector3(1.0f, 1.0f, 1.0f), 1.0f),
            new Vector3(0, 0, 0)
    );

    public static PhongShader getInstance() {
        return instance;
    }

    private PhongShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("phongVertex.vert"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.frag"));

        compileShader();

        addUniform("transform");
        addUniform("transformProjected");
        addUniform("baseColour");
        addUniform("ambientLight");
        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    public void updateUniforms(Matrix4 worldMatrix, Matrix4 projectedMatrix, Material material) {

        if( material.getTexture() != null) {
            material.getTexture().bind();
        } else {
            RenderUtil.unbindTextures();
        }

        setUniform("transformProjected", projectedMatrix);
        setUniform("transform", worldMatrix);
        setUniform("baseColour", material.getColour());
        setUniform("ambientLight", ambientLight);
        setUniform("directionalLight", directionalLight);
    }

    public static Vector3 getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3 ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }

    public static void setDirectionalLight(DirectionalLight light) {
        PhongShader.directionalLight = light;
    }

    public void setUniform(String uniformName, BaseLight baseLight) {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }
    public void setUniform(String uniformName, DirectionalLight value){
        setUniform(uniformName + ".base", value.getBaseLight());
        setUniform(uniformName + ".direction", value.getDirection());
    }
}
