package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class Utils {

    public static String readFile(String filePath) {
        String str;
        try {
            str = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException excp) {
            throw new RuntimeException("Error reading file [" + filePath + "]", excp);
        }
        return str;
    }

    public static float[] listoFloat(List<Vector3f> arraylist) {
        float[] arr = new float[arraylist.size() * 3];
        int index = 0;
        for (int i = 0; i < arraylist.size(); i++) {
            arr[index++] = arraylist.get(i).x;
            arr[index++] = arraylist.get(i).y;
            arr[index++] = arraylist.get(i).z;
        }
        return arr;
    }

    public static int[] listoInt(List<Integer> arraylist) {
        int[] arr = new int[arraylist.size()];
        for (int i = 0; i < arraylist.size(); i++) {
            arr[i] = arraylist.get(i);
        }
        return arr;
    }

    public static ArrayList<Object> calculatePoint(ArrayList<Rectangle1> rectangles) {
        int n = rectangles.size() - 1;

        ArrayList<Object> bezierDots = new ArrayList<>();
        bezierDots.add(new Object(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(), new Vector4f(1.0f, 0.0f, 1.0f, 1.0f)
        ));
        for (float j = 0f; j <= 1; j += 0.01f) {
            float x = 0;
            float y = 0;
            for (int i = 0; i <= n; i++) {
                double factor = kombinasi(n, i) * Math.pow(1 - j, n - i) * Math.pow(j, i);
                x += factor * rectangles.get(i).centerX;
                y += factor * rectangles.get(i).centerY;
            }
            bezierDots.get(0).addVertices(new Vector3f(x, y, 0));
        }

        return bezierDots;
    }

    private static int kombinasi(int n, int k) {
        int result = 1;
        for (int i = 1; i <= k; i++) {
            result *= n - i + 1;
            result /= i;
        }
        return result;
    }
}
