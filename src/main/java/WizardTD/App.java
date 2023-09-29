package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;
// import processing.data.JSONArray;
// import processing.data.JSONObject;
import processing.event.MouseEvent;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
// import java.io.*;
import java.util.*;
import java.lang.*;

public class App extends PApplet {

    public static final int CELLSIZE = 32;
    public static final int SIDEBAR = 120;
    public static final int TOPBAR = 40;
    public static final int BOARD_WIDTH = 20;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE+TOPBAR;

    public static final int FPS = 60;

    public String configPath;

    public Random random = new Random();

    public Grass grass;
    public Grass grass2;
    public char[][] grassArr = new char[20][20];
    public Grass[] grassLoc = new Grass[400]; // MAKE INTO ARRAY LIST LATER(DYNAMIC)
	
	// Feel free to add any additional methods or attributes you want. Please put classes in different files.

    public App() {
        this.configPath = "config.json";
        this.grass = new Grass(32, 40);
        this.grass2 = new Grass(0,40); // MAKE INTO ARRAY LIST LATER
        System.out.println("App");
        // loadMap();
    }

    /**
     * Initialise the setting of the window size.
     */
	@Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
     */
	@Override
    public void setup() {
        frameRate(FPS);

        // Load images during setup
		// Eg:
        // loadImage("src/main/resources/WizardTD/tower0.png");
        // loadImage("src/main/resources/WizardTD/tower1.png");
        // loadImage("src/main/resources/WizardTD/tower2.png");
        this.grass.setSprite(this.loadImage(this.grass.getImageName()));
        this.grass2.setSprite(this.loadImage(this.grass.getImageName()));
        System.out.println("setup run");
        //Setting grass
        loadMap();
        int counter = 0;
        int xloc = 0;
        int yloc = 0;
        for (int i = 0; i < 20; i ++){
            for (int j = 0; j < 20; j++){
                if (((Character)grassArr[i][j]).equals('S')){
                    System.out.println(" x "+ i + "y "+ j);
                    grassLoc[counter] = new Grass(i+xloc,j+xloc);
                    grassLoc[counter].setSprite(loadImage(grassLoc[counter].getImageName()));
                    counter++;
                    xloc += 32;
                }
                else{
                    grassLoc[counter] = null;
                    counter ++;
                    xloc += 32;
                }
            }
        }

    }
    /**
     * Receive key pressed signal from the keyboard.
     */
	@Override
    public void keyPressed(){
        if (this.keyCode == 9){
            System.exit(0);
        }
    }
    
    public void loadMap(){
        String[] lines = loadStrings("level1.txt");
        int sCounter = 0;
        String[] stringArray = new String[20];
        // char[][] arrayOfChar = new char[20][20];
        for (int i = 0; i < lines.length; i ++){
            String line = lines[i].toString();
            stringArray[i] = line;
            // char[] characters = line.toCharArray();
            // for (int j = 0; j < characters.length; j++){
            //     Character character = characters[j];
            //     if (character.equals('S')){
            //         sCounter++;
            //     }
            // }
            // System.out.println(i + ": " + sCounter);    
        }
        for (int i = 0; i < stringArray.length; i ++){
            System.out.println(stringArray[i]);
            char[] characters = stringArray[i].toCharArray();
            this.grassArr[i] = characters;
            // for (int j = 0; j < characters.length; j++){
            //     Character character = characters[j];
            //     if (character.equals('S')){
            //     }
            // }
        }

    }
    /**
     * Receive key released signal from the keyboard.
     */
	@Override
    public void keyReleased(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /*@Override
    public void mouseDragged(MouseEvent e) {

    }*/

    /**
     * Draw all elements in the game by current frame.
     */
	@Override
    public void draw() {
        background(102, 51, 0);
        grass.draw(this);
        grass2.draw(this);

        for (int i = 0; i < 400; i ++){
            if (grassLoc[i] != null){
               grassLoc[i].draw(this); 
            }
        }
    }

    public static void main(String[] args) {     
        PApplet.main("WizardTD.App");
    }

    /**
     * Source: https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java
     * @param pimg The image to be rotated
     * @param angle between 0 and 360 degrees
     * @return the new rotated image
     */
    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        PImage result = this.createImage(newWidth, newHeight, ARGB);
        //BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        BufferedImage rotated = (BufferedImage) result.getNative();
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                result.set(i, j, rotated.getRGB(i, j));
            }
        }

        return result;
    }
}
