package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_LINE;

public class Sphere extends Circle {

    float radiusY, radiusZ;
    int sectorCount, stackCount;
    ArrayList<Float> centerPoint = new ArrayList<>();

    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, float centerX, float centerY,
                  float radius, float radiusY, float radiusZ, int sectorCount, int stackCount) {
        super(shaderModuleDataList, vertices, color, centerX, centerY, radius);
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
        this.sectorCount = sectorCount;
        this.stackCount = stackCount;

        centerPoint.add(0f);
        centerPoint.add(0f);
        centerPoint.add(0f);
//        createHyperbolloid1();
//        createHyperbolloid2();
        createEllipsoid();
//        createSphere();
//        createBox();
        setupVAOVBO();
    }

    public void createSphere() {
        float pi = (float) Math.PI;

        float sectorStep = 2 * (float) Math.PI / sectorCount;

        float stackStep = (float) Math.PI / stackCount;

        float sectorAngle, stackAngle, x, y, z;
        for (int i = 0; i <= stackCount; i++) {
            stackAngle = pi / 2 - i * stackStep;
            x = radius * (float) Math.cos(stackAngle);
            y = radiusY * (float) Math.cos(stackAngle);
            z = radiusZ * (float) Math.sin(stackAngle);

            for (int j = 0; j <= sectorCount; ++j) {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float) Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float) Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;

                vertices.add(temp_vector);
            }
        }
    }

    public void createEllipsoid() {
        float pi = (float) Math.PI;
        float sectorStep = 2 * (float) Math.PI / sectorCount;
        float stackStep = (float) Math.PI / stackCount;
        float sectorAngle, stackAngle, x, y, z;

        for (float v = -pi / 2; v <= pi / 2; v += pi / 36) {
            for (float u = -pi; u <= pi; u += pi / 36) {
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = (float) (centerPoint.get(0) + radius * (Math.cos(v) * Math.cos(u)));
                temp_vector.y = (float) (centerPoint.get(1) + radiusY * (Math.cos(v) * Math.sin(u)));
                temp_vector.z = (float) (centerPoint.get(2) + radiusZ * (Math.sin(v)));
                vertices.add(temp_vector);
            }
        }


    }

    public void createHyperbolloid1() {
        float pi = (float) Math.PI;
        float sectorStep = 2 * (float) Math.PI / sectorCount;
        float stackStep = (float) Math.PI / stackCount;
        float sectorAngle, stackAngle, x, y, z;

        for (float v = -pi / 2; v <= pi / 2; v += pi / 36) {
            for (float u = -pi; u <= pi; u += pi / 36) {
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = (float) (centerPoint.get(0) + radius * ((1 / Math.cos(v)) * Math.cos(u)));
                temp_vector.y = (float) (centerPoint.get(1) + radiusY * ((1 / Math.cos(v)) * Math.sin(u)));
                temp_vector.z = (float) (centerPoint.get(2) + radiusZ * (Math.tan(v)));
                vertices.add(temp_vector);
            }
        }
    }

    public void createHyperbolloid2() {
        float pi = (float) Math.PI;
        float sectorStep = 2 * (float) Math.PI / sectorCount;
        float stackStep = (float) Math.PI / stackCount;
        float sectorAngle, stackAngle, x, y, z;

        for (float v = -pi / 2; v <= pi / 2; v += pi / 36) {
            for (float u = -pi / 2; u <= pi / 2; u += pi / 36) {
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = (float) (centerPoint.get(0) + radius * (Math.cos(v) * Math.cos(u)));
                temp_vector.y = (float) (centerPoint.get(1) + radiusY * (Math.cos(v) * Math.sin(u)));
                temp_vector.z = (float) (centerPoint.get(2) + radiusZ * (Math.sin(v)));
                vertices.add(temp_vector);
            }
            for (float u = pi / 2; u <= 3 * pi / 2; u += pi / 36) {
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = (float) (centerPoint.get(0) + radius * (Math.cos(v) * Math.cos(u)));
                temp_vector.y = (float) (centerPoint.get(1) + radiusY * (Math.cos(v) * Math.sin(u)));
                temp_vector.z = (float) (centerPoint.get(2) + radiusZ * (Math.sin(v)));
                vertices.add(temp_vector);
            }

        }

    }

    public void createBox() {
        //Titik 1
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        temp.x = centerPoint.get(0) - radius / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;

        tempVertices.add(temp);
        temp = new Vector3f();

        //2
        temp.x = centerPoint.get(0) + radius / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;

        tempVertices.add(temp);
        temp = new Vector3f();

        //3
        temp.x = centerPoint.get(0) + radius / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;

        tempVertices.add(temp);
        temp = new Vector3f();

        //4
        temp.x = centerPoint.get(0) - radius / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;

        tempVertices.add(temp);
        temp = new Vector3f();

        //5
        temp.x = centerPoint.get(0) - radius / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;

        tempVertices.add(temp);
        temp = new Vector3f();

        //6
        temp.x = centerPoint.get(0) + radius / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;

        tempVertices.add(temp);
        temp = new Vector3f();

        //7
        temp.x = centerPoint.get(0) + radius / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;

        tempVertices.add(temp);
        temp = new Vector3f();

        //8
        temp.x = centerPoint.get(0) - radius / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;

        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //Kotak Belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //Kotak Depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        //Kotak Kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //Kotak Kanan
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //Kotak Bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));


    }

    public void draw() {
        drawSetup();

        glLineWidth(1);
        glPointSize(0);
        glDrawArrays(GL_LINES, 0, vertices.size());
    }
}
