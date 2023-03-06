package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL15.*;

public class Oval extends Object {
    List<Integer> index;

    float centerX, centerY, radiusX,radiusY;



    public Oval(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float centerX, float centerY, float radiusX, float radiusY) {
        super(shaderModuleDataList, vertices, color);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;

        createOval();
        setupVAOVBO();
    }


    public void createOval() {
        vertices.clear();
        for (double i = 0; i <= 360; i += 0.01f) {
            float y = (float) (centerY + (radiusY * Math.sin(i)));
            float x = (float) (centerX + (radiusX * Math.cos(i)));
            vertices.add(new Vector3f(x,y,0.0f));
        }
    }

    @Override
    public void draw() {
        drawSetup();

        glLineWidth(10);
        glPointSize(10);
        glDrawArrays(GL_POLYGON,0,vertices.size());
    }

}

