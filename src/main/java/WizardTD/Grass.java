package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;


public class Grass {
    private int x;
    private int y;
    private PImage sprite;
    private String Image = "src/main/resources/WizardTD/grass.png";

    public Grass(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSprite(PImage sprite){
        this.sprite = sprite;
        this.sprite.resize(32,32);
    }

    public void tick(){} 

    public void draw(PApplet app){
        app.image(this.sprite, this.x, this.y);
    }

    public String getImageName(){
        return Image;
    }
}
