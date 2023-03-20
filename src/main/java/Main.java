import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    int countDegree = 0;
    private Window window = new Window(1400, 1400, "C14210131");

    private ArrayList<Object> objects = new ArrayList<>();

    private ArrayList<Rectangle1> objectsRectangle = new ArrayList<>();
    private ArrayList<Object> objectsPointControl = new ArrayList<>();
    private ArrayList<Object> bezierDots = new ArrayList<>();
    private ArrayList<Object> objectSphere = new ArrayList<>();
    boolean overlapped, overlapped2;

    public void init() {
        window.init();
        GL.createCapabilities();

        //code
        objectsPointControl.add(new Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(), new Vector4f(0.0f, 1.0f, 1.0f, 1.0f)
        ));

        bezierDots.add(new Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(), new Vector4f(1.0f, 0.0f, 1.0f, 1.0f)
        ));

        //Matahari
        objectSphere.add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(), new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                0f, 0f, 0.1f, 0.1f, 0.1f, 18, 9));
        //Merkurius
//        objectSphere.add(new Sphere(Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(), new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
//                0f, 0f, 0.1f, 0.1f, 0.1f, 18, 9));
//        //Venus
//        objectSphere.add(new Sphere(Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
//                0f, 0f, 0.1f, 0.1f, 0.1f, 18, 9));
//        //Bumi
//        objectSphere.add(new Sphere(Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(), new Vector4f(0.0f, 1.0f, 0.0f, 1.0f),
//                0f, 0f, 0.1f, 0.1f, 0.1f, 18, 9));
//        //Bulan
//        objectSphere.add(new Sphere(Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(), new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
//                0f, 0f, 0.03f, 0.03f, 0.03f, 18, 9));
//        //Mars
//        objectSphere.add(new Sphere(Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(), new Vector4f(1.0f, 0.0f, 1.0f, 1.0f),
//                0f, 0f, 0.1f, 0.1f, 0.1f, 18, 9));
//        objectSphere.get(1).translateObj(0.23f, 0.23f, 0.23f);
//        objectSphere.get(2).translateObj(0.43f, 0.43f, 0.43f);
//        objectSphere.get(3).translateObj(0.63f, 0.63f, 0.63f);
//        objectSphere.get(4).translateObj(0.72f, 0.77f, 0.72f);
//        objectSphere.get(5).translateObj(-0.32f, -0.32f, -0.32f);

//        objectSphere.get(1).scaleObj(1.0f, 1.0f, 1.0f);
//        objectSphere.get(2).scaleObj(1.0f, 1.0f, 1.0f);
//        objectSphere.get(3).scaleObj(1.0f, 1.0f, 1.0f);
//        objectSphere.get(4).scaleObj(1.0f, 1.0f, 1.0f);
//        objectSphere.get(5).scaleObj(1.0f, 1.0f, 1.0f);
//        System.out.println(objectSphere.size());
        objectSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(), new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                0f, 0f, 0.2f, 0.2f, 0.2f, 18, 9));

        objectSphere.get(0).getChildObject().get(0).translateObj(0.23f, 0.23f, 0.23f);

        objectSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(), new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                0f, 0f, 0.2f, 0.2f, 0.2f, 18, 9));

        objectSphere.get(0).getChildObject().get(1).translateObj(0.43f, 0.43f, 0.43f);

        objectSphere.get(0).getChildObject().get(1).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(), new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                0f, 0f, 0.2f, 0.2f, 0.2f, 18, 9));
    }

    public void input() {
        if (window.isKeyPressed(GLFW_KEY_G)) {
            countDegree++;

            objectSphere.get(0).rotateObj((float) Math.toRadians(4f), 0f, 0f, 1f);

//            objectSphere.get(1).translateObj(-0.23f, -0.23f, -0.23f);
//            objectSphere.get(1).rotateObj((float) Math.toRadians(1f), 0f, 0f, 1f);
//            objectSphere.get(1).translateObj(0.23f, 0.23f, 0.23f);
//
//            objectSphere.get(2).translateObj(-0.43f, -0.43f, -0.43f);
//            objectSphere.get(2).rotateObj((float) Math.toRadians(2f), 0f, 0f, 1f);
//            objectSphere.get(2).translateObj(0.43f, 0.43f, 0.43f);
//
//            objectSphere.get(3).translateObj(-0.63f, -0.63f, -0.63f);
//            objectSphere.get(3).rotateObj((float) Math.toRadians(0.9f), 0f, 0f, 1f);
//            objectSphere.get(3).translateObj(0.63f, 0.63f, 0.63f);
//
//            objectSphere.get(4).translateObj(-0.72f, -0.77f, -0.72f);
//            objectSphere.get(4).rotateObj((float) Math.toRadians(0.9f), 0f, 0f, 1f);
//            objectSphere.get(4).translateObj(0.72f, 0.77f, 0.72f);
//
//            objectSphere.get(5).translateObj(0.32f, 0.32f, 0.32f);
//            objectSphere.get(5).rotateObj((float) Math.toRadians(1.1f), 0f, 0f, 1f);
//            objectSphere.get(5).translateObj(-0.32f, -0.32f, -0.32f);

            for (Object child : objectSphere.get(0).getChildObject()) {
                child.rotateObj((float) Math.toRadians(countDegree * -1 * 0.5f), 0.0f, 0.0f, 1.0f);
                child.translateObj(child.getCenterPoint().get(0) * -1, child.getCenterPoint().get(1) * -1, child.getCenterPoint().get(2) * -1);
                child.rotateObj((float) Math.toRadians(0.5f), 0.0f, 0.0f, 1.0f);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_F)) {
            objectSphere.get(0).rotateObj((float) Math.toRadians(0.5f), 0f, 0f, 1f);
//            objectSphere.get(1).rotateObj((float) Math.toRadians(1f), 0f, 0f, 1f);
//            objectSphere.get(2).rotateObj((float) Math.toRadians(2f), 0f, 0f, 1f);
//            objectSphere.get(3).rotateObj((float) Math.toRadians(0.9f), 0f, 0f, 1f);
//            objectSphere.get(4).translateObj(-0.72f, -0.77f, -0.72f);
//            objectSphere.get(4).rotateObj((float) Math.toRadians(0.5f), 0f, 0f, 1f);
//            objectSphere.get(4).translateObj(0.63f, 0.63f, 0.63f);
//            objectSphere.get(5).rotateObj((float) Math.toRadians(1.1f), 0f, 0f, 1f);

        }
        if (window.getMouseInput().isLeftButtonPressed()) {
            Vector2f pos = window.getMouseInput().getCurrentPos();
            pos.x = (pos.x - (window.getWidth()) / 2.0f) / (window.getWidth() / 2.0f);
            pos.y = (pos.y - (window.getHeight()) / 2.0f) / (-window.getHeight() / 2.0f);

            if ((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1))) {

                System.out.println("x: " + pos.x + "y: " + pos.y);
                int index = 0;
                overlapped = false;
                for (Rectangle1 object : objectsRectangle) {
                    overlapped = object.isOverlapped(pos.x, pos.y);
                    if (overlapped) {
                        break;
                    }

                }
                for (Rectangle1 object : objectsRectangle) {
                    overlapped2 = object.isOverlappedd(pos.x, pos.y);
                    if (overlapped2) {
                        if (object.isOverlapped(pos.x, pos.y)) {
                            objectsPointControl.get(0).updateVertices(index, new Vector3f(pos.x, pos.y, 0));
                            object.move(pos.x, pos.y, 0.1f);
                        }
                    }
                    index++;
                }
                System.out.println(index);
                if (!overlapped) {
                    objectsPointControl.get(0).addVertices(new Vector3f(pos.x, pos.y, 0));
                    objectsRectangle.add(new Rectangle1(Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                            new ArrayList<>(),
                            new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                            Arrays.asList(0, 1, 1, 2, 2, 3, 3, 0), pos.x, pos.y, 0.1f
                    ));
                }
            }
        }


    }


    public void loop() {
        while (window.isOpen()) {

            window.update();
            glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

            GL.createCapabilities();
            input();
            //code


//            objectSphere.get(0).scaleObj(2f,2f,2f);
//            //Tanah Hijau
//            objectsRectangle.add(new Rectangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(
//                            List.of(
//                                    new Vector3f(-1.0f, -1.0f, 0.0f),
//                                    new Vector3f(1.0f, -1.0f, 0.0f),
//                                    new Vector3f(-1.0f, -0.6f, 0.0f),
//                                    new Vector3f(1.0f, -0.6f, 0.0f)
//                            )
//                    ), new Vector4f(0.0f, 1.0f, 0.0f, 1.0f),
//                    Arrays.asList(0, 1, 2, 1, 2, 3)
//            ));
//
//            //Langit Biru
//            objectsRectangle.add(new Rectangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(
//                            List.of(
//                                    new Vector3f(-1.0f, -0.6f, 0.0f),
//                                    new Vector3f(1.0f, -0.6f, 0.0f),
//                                    new Vector3f(-1.0f, 1.0f, 0.0f),
//                                    new Vector3f(1.0f, 1.0f, 0.0f)
//                            )
//                    ), new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
//                    Arrays.asList(0, 1, 2, 1, 2, 3)
//            ));
//
//            //Base Rumah
//            objectsRectangle.add(new Rectangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(
//                            List.of(
//                                    new Vector3f(-0.3f, -0.6f, 0.0f),
//                                    new Vector3f(0.6f, -0.6f, 0.0f),
//                                    new Vector3f(-0.3f, 0.1f, 0.0f),
//                                    new Vector3f(0.6f, 0.1f, 0.0f)
//                            )
//                    ), new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
//                    Arrays.asList(0, 1, 2, 1, 2, 3)
//            ));
//
//            //Atap Kuning
//            objectsRectangle.add(new Rectangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(
//                            List.of(
//                                    new Vector3f(-0.2f, 0.5f, 0.0f),
//                                    new Vector3f(0.5f, 0.5f, 0.0f),
//                                    new Vector3f(-0.4f, 0.0f, 0.0f),
//                                    new Vector3f(0.7f, 0.0f, 0.0f)
//                            )
//                    ), new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
//                    Arrays.asList(0, 1, 2, 1, 2, 3)
//            ));
//            objectsRectangle.add(new Rectangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(
//                            List.of(
//                                    new Vector3f(-0.15f, 0.4f, 0.0f),
//                                    new Vector3f(0.03f, 0.0f, 0.0f),
//                                    new Vector3f(-0.3f, 0.0f, 0.0f)
//                            )
//                    ), new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
//                    Arrays.asList(0, 1, 2, 1)
//            ));
//            //Pintu
//            objectsRectangle.add(new Rectangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(
//                            List.of(
//                                    new Vector3f(-0.25f, -0.6f, 0.0f),
//                                    new Vector3f(0.0f, -0.6f, 0.0f),
//                                    new Vector3f(-0.25f, -0.1f, 0.0f),
//                                    new Vector3f(0.0f, -0.1f, 0.0f)
//                            )
//                    ), new Vector4f(1.0f, 0.89f, 0.52f, 1.0f),
//                    Arrays.asList(0, 1, 2, 1, 2, 3)
//            ));
//            //Cerobong
//            objectsRectangle.add(new Rectangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(
//                            List.of(
//                                    new Vector3f(0.35f, 0.6f, 0.0f),
//                                    new Vector3f(0.45f, 0.6f, 0.0f),
//                                    new Vector3f(0.35f, 0.3f, 0.0f),
//                                    new Vector3f(0.45f, 0.3f, 0.0f)
//                            )
//                    ), new Vector4f(1.0f, 0.78f, 1.0f, 1.0f),
//                    Arrays.asList(0, 1, 2, 1, 2, 3)
//            ));
//
//            //Bulan
//            objectsRectangle.add(new Circle(Arrays.asList(
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                    ), new ArrayList<>(), new Vector4f(1.0f, 1.0f, 0.2f, 1.0f), -0.6f, 0.7f, 0.2f)
//            );
//
//            objectsRectangle.add(new Circle(Arrays.asList(
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                    ), new ArrayList<>(), new Vector4f(0.0f, 0.0f, 1.0f, 1.0f), -0.51f, 0.7f, 0.2f)
//            );
//
//            //Asap
//            objectsRectangle.add(new Oval(Arrays.asList(
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                    ), new ArrayList<>(), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), 0.45f, 0.7f, 0.1f, 0.05f)
//            );
//
//            objectsRectangle.add(new Oval(Arrays.asList(
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                    ), new ArrayList<>(), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), 0.48f, 0.75f, 0.1f, 0.05f)
//            );
//
//            objectsRectangle.add(new Oval(Arrays.asList(
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                    ), new ArrayList<>(), new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), 0.51f, 0.8f, 0.1f, 0.05f)
//            );
//
//            //STARS
//            objectsRectangle.add(new Star(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(1.0f, 0.0f, 1.0f, 1.0f),
//                    Arrays.asList(3, 1, 1, 4, 4, 2, 2, 0, 0, 3), 0.26f, 0.7f, 0.05f
//            ));
//            objectsRectangle.add(new Star(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(1.0f, 0.0f, 1.0f, 1.0f),
//                    Arrays.asList(3, 1, 1, 4, 4, 2, 2, 0, 0, 3), -0.29f, 0.67f, 0.05f
//            ));
//            objectsRectangle.add(new Star(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(1.0f, 0.0f, 1.0f, 1.0f),
//                    Arrays.asList(3, 1, 1, 4, 4, 2, 2, 0, 0, 3), 0f, 0.64f, 0.05f
//            ));
//            objectsRectangle.add(new Star(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(1.0f, 0.0f, 1.0f, 1.0f),
//                    Arrays.asList(3, 1, 1, 4, 4, 2, 2, 0, 0, 3), 0.7f, 0.7f, 0.05f
//            ));
//            //Hiasan Segitiga
//            objectsRectangle.add(new Triangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(0f, 0f, 0f, 1.0f),
//                    Arrays.asList(0, 1, 1, 2, 2, 0), 0.23f, -0.22f, 0.088f
//            ));
//            objectsRectangle.add(new Triangle(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(0f, 0f, 0f, 1.0f),
//                    Arrays.asList(0, 1, 1, 2, 2, 0), 0.37f, -0.22f, 0.088f
//            ));
//            //Jendela
//            //kiri atas
//            objectsRectangle.add(new Rectangle1(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(0f, 0f, 0f, 1.0f),
//                    Arrays.asList(0, 1, 1, 2, 2, 3, 3, 0), 0.23f, -0.2f, 0.1f
//            ));
//            //kanan atas
//            objectsRectangle.add(new Rectangle1(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(0f, 0f, 0f, 1.0f),
//                    Arrays.asList(0, 1, 1, 2, 2, 3, 3, 0), 0.37f, -0.2f, 0.1f
//            ));
//            //kiri bawah
//            objectsRectangle.add(new Rectangle1(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(0f, 0f, 0f, 1.0f),
//                    Arrays.asList(0, 1, 1, 2, 2, 3, 3, 0), 0.23f, -0.34f, 0.1f
//            ));
//            //kanan bawah
//            objectsRectangle.add(new Rectangle1(Arrays.asList(
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//            ),
//                    new ArrayList<>(),
//                    new Vector4f(0f, 0f, 0f, 1.0f),
//                    Arrays.asList(0, 1, 1, 2, 2, 3, 3, 0), 0.37f, -0.34f, 0.1f
//            ));

            for (Object object : objects) {
                object.draw();
            }

            for (Object object : objectsPointControl) {
                object.drawLine();
            }

            for (Object object : objectsRectangle) {
                object.draw();
            }

            ArrayList<Object> bezierDots = Utils.calculatePoint(objectsRectangle);
            for (Object object : bezierDots) {
                object.drawLine();
            }

            for (Object object : objectSphere) {
                object.draw();
            }

            glDisableVertexAttribArray(0);

            glfwPollEvents();
        }
    }

    public void run() {
        init();
        loop();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
