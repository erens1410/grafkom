package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class Triangle extends Object {

    List<Integer> index;
    int ibo;
    float centerX, centerY;
    float radius;

    public Triangle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Integer> index, float centerX, float centerY, float radius) {
        super(shaderModuleDataList, vertices, color);
        this.index = index;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.listoInt(index), GL_STATIC_DRAW);

        createTriangle();
        setupVAOVBO();
    }

    public void createTriangle() {
        vertices.clear();
        for (double i = -30; i < 360; i += 120) {
            float y = (float) (centerY + (radius * Math.sin(Math.toRadians(i))));
            float x = (float) (centerX + (radius * Math.cos(Math.toRadians(i))));
            vertices.add(new Vector3f(x, y, 0.0f));
        }
    }


    public void draw() {
        drawSetup();
        glLineWidth(3.2f);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_LINES, index.size(), GL_UNSIGNED_INT, 0);
    }
}


