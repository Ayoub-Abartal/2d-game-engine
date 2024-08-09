package fame;

import org.lwjgl.glfw.GLFWErrorCallback;
import sun.java2d.marlin.Version;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private int width, height;
    private String title;

    private static Window window;
    private long glfwWindow;
    private Window(){
        this.width = 1920;
        this.height = 1080;
        this.title = "Game Engine";
    }

    public static Window get(){
        if(Window.window == null){
            return new Window();
        }else
            return Window.window;
    }

    public void run(){
        System.out.println("Welcome to LWJGL "+ Version.getVersion()+"!");
        init();
        loop();
    }

    public void init(){
        // Setup err callback
        GLFWErrorCallback.createPrint(System.err).set();

        // initialize GLFW
        if(!glfwInit()){
            throw new IllegalStateException("Unable to intialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the window
        glfwWindow = glfwCreateWindow(this.width,this.height,this.title, NULL,NULL);
    }

    public void loop(){

    }
}
