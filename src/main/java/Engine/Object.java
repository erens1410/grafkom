package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object extends ShaderProgram {
    List<Vector3f> vertices; //buat nyimpen titik2 yang mau digambar
    List<Vector3f> verticesColor; // warna
    int vao, vbo;

    UniformsMap uniformsMap;

    Vector4f color;

    int vboColor;

    public Object(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
        this.color = color;
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
        glDrawArrays(GL_TRIANGLE_FAN, 0, vertices.size());
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
}
