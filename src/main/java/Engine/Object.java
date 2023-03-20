package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object extends ShaderProgram {
    public List<Float> getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(List<Float> centerPoint) {
        this.centerPoint = centerPoint;
    }

    public List<Object> getChildObject() {
        return childObject;
    }

    public void setChildObject(List<Object> childObject) {
        this.childObject = childObject;
    }

    List<Float> centerPoint;
    List<Object> childObject;
    List<Vector3f> vertices; //buat nyimpen titik2 yang mau digambar
    List<Vector3f> verticesColor; // warna
    int vao, vbo;

    UniformsMap uniformsMap;

    Vector4f color;

    int vboColor;

    Matrix4f model;


    public Object(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
        uniformsMap.createUniform("model");
        this.color = color;
        model = new Matrix4f();
        childObject = new ArrayList<>();
    }

    public Object(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, List<Vector3f> verticesColor) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesColor = verticesColor;
        setupVAOVBOWithVerticesColor();

    }

    public void setupVAOVBO() {
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);


    }

    public void setupVAOVBOWithVerticesColor() {
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);

        //set vboColor
        vboColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(verticesColor), GL_STATIC_DRAW);
    }

    public void drawSetup() {
        bind();
        uniformsMap.setUniform("uni_color", color);
        uniformsMap.setUniform("model", model);

        //Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);


    }

    public void drawSetupWithVerticesColor() {
        bind();

        //Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        //Bind VBO COlor
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
    }

    public void draw() {
        drawSetup();

        //optional
        glLineWidth(1); //ngatur ketebalan garis
        glPointSize(0); //ngatur besar/kecilnya titik vertex
        //wajib
        /*
        GL_LINES
        GL_LINE_STRIP
        GL_LINE_LOOP
        GL_TRIANGLES
        GL_TRIANGLE_FAN
        GL_POINT
        */
        // first -> start gambar dari mana
        // param ke 3 -> batas gambar titik nya
        glDrawArrays(GL_POLYGON, 0, vertices.size());

        for (Object child : childObject) {
            child.draw();
        }
    }

    public void drawWithVerticesColor() {
        drawSetupWithVerticesColor();

        //optional
        glLineWidth(1); //ngatur ketebalan garis
        glPointSize(0); //ngatur besar/kecilnya titik vertex
        //wajib
        /*
        GL_LINES
        GL_LINE_STRIP
        GL_LINE_LOOP
        GL_TRIANGLES
        GL_TRIANGLE_FAN
        GL_POINT
        */
        // first -> start gambar dari mana
        // param ke 3 -> batas gambar titik nya
        glDrawArrays(GL_TRIANGLE_STRIP, 0, vertices.size());
    }

    public void drawLine() {
        drawSetup();

        //optional
        glLineWidth(1); //ngatur ketebalan garis
        glPointSize(0); //ngatur besar/kecilnya titik vertex
        //wajib
        /*
        GL_LINES
        GL_LINE_STRIP
        GL_LINE_LOOP
        GL_TRIANGLES
        GL_TRIANGLE_FAN
        GL_POINT
        */
        // first -> start gambar dari mana
        // param ke 3 -> batas gambar titik nya
        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
    }

    public void addVertices(Vector3f newVertices) {
        vertices.add(newVertices);
        setupVAOVBO();
    }

    public void updateVertices(int index, Vector3f vector) {
        vertices.set(index, vector);
        setupVAOVBO();
    }

    public void translateObj(float offsetX, float offsetY, float offsetZ) {
        model = new Matrix4f().translate(offsetX, offsetY, offsetZ).mul(new Matrix4f(model));
        for (Object child : childObject) {
            child.translateObj(offsetX, offsetY, offsetZ);
        }
    }

    public void rotateObj(float degree, float x, float y, float z) {
        model = new Matrix4f().rotate(degree, x, y, z).mul(new Matrix4f(model));
        for (Object child : childObject) {
            child.rotateObj(degree, x, y, z);
        }
    }

    public void scaleObj(float scaleX, float scaleY, float scaleZ) {
        model = new Matrix4f().scale(scaleX, scaleY, scaleZ).mul(new Matrix4f(model));
        for (Object child : childObject) {
            child.scaleObj(scaleX, scaleY, scaleZ);
        }
    }
}
