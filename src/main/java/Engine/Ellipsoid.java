package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class Ellipsoid extends Circle {
    float radiusY, radiusZ;
    ArrayList<Float> centerPoint = new ArrayList<>();

    public Ellipsoid(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float centerX, float centerY, float radius, float radiusY, float radiusZ) {
        super(shaderModuleDataList, vertices, color, centerX, centerY, radius);
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
        centerPoint.add(0f);
        centerPoint.add(0f);
        centerPoint.add(0f);
        createEllipsoid();
        setupVAOVBO();
    }

    public void createEllipsoid() {
        for (double v = -Math.PI / 2; v <= Math.PI / 2; v += Math.PI / 60) {
            for (double u = -Math.PI; u <= Math.PI; u += Math.PI / 60) {
                float x = centerPoint.get(0) * (float) (Math.cos(v) * Math.cos(u));
                float y = centerPoint.get(1) * (float) (Math.cos(v) * Math.sin(u));
                float z = centerPoint.get(2) * (float) (Math.sin(v));
                vertices.add(new Vector3f(x, y, z));
            }
        }

    }

    public void draw() {
        drawSetup();

        glLineWidth(1);
        glPointSize(0);
        glDrawArrays(GL_POLYGON, 0, vertices.size());
    }
}
