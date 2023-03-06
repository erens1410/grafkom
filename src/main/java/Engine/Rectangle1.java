package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class Rectangle1 extends Object {

    List<Integer> index;
    int ibo;
    float centerX, centerY;
    float radius;

    public Rectangle1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Integer> index, float centerX, float centerY, float radius) {
        super(shaderModuleDataList, vertices, color);
        this.index = index;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utils.listoInt(index), GL_STATIC_DRAW);

        createRectangle1();
        setupVAOVBO();
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getRadius() {
        return radius;
    }

    public void createRectangle1() {
        vertices.clear();
        for (double i = -45; i < 360; i += 90) {
            float y = (float) (centerY + (radius * Math.sin(Math.toRadians(i))));
            float x = (float) (centerX + (radius * Math.cos(Math.toRadians(i))));
            vertices.add(new Vector3f(x, y, 0.0f));
        }
    }

    public boolean isOverlapped(float x, float y) {
        float dist = (float) Math.sqrt(Math.pow((x - centerX), 2) + Math.pow((y - centerY), 2));
        if (dist <= 2 * radius) {
            return true;
        }
        return false;

    }

    public boolean isOverlappedd(float x, float y) {
        float dist = (float) Math.sqrt(Math.pow((x - centerX), 2) + Math.pow((y - centerY), 2));
        if (dist <= 2 * radius - (radius/2)) {
            return true;
        }
        return false;

    }

    public void updateVertices(int index, Vector3f vector) {}

    public void move(float x, float y, float r) {
        this.centerX = x;
        this.centerY = y;
        this.radius = r;
        createRectangle1();
        setupVAOVBO();
    }
    public void draw() {
        drawSetup();
        glLineWidth(3.2f);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_POLYGON, index.size(), GL_UNSIGNED_INT, 0);
    }
}


