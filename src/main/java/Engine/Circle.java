package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL15.*;

public class Circle extends Object {
    List<Integer> index;
    int ibo;
    float radius, centerY;
    float centerX;



    public Circle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float centerX, float centerY, float radius) {
        super(shaderModuleDataList, vertices, color);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

//        createCircle();
        setupVAOVBO();
    }


    public void createCircle() {
        vertices.clear();
        for (double i = 0; i <= 360; i += 0.01f) {
            float y = (float) (centerY + (radius * Math.sin(i)));
            float x = (float) (radius + (radius * Math.cos(i)));
            vertices.add(new Vector3f(x, y, 0.0f));
        }
    }

    @Override
    public void draw() {
        drawSetup();

        glLineWidth(1);
        glPointSize(0);
        glDrawArrays(GL_POLYGON, 0, vertices.size());
        for (Object child : childObject) {
            child.draw();
        }
    }

}

