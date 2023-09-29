package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;


public class Grass {
    private int x;
    private int y;
    private PImage sprite;

    public Grass(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSprite(PImage sprite){
        this.sprite = sprite;
        this.sprite.resize(32,32);
    }

    public void tick(){} // Remember to go back to look at what tick means

    public void draw(PApplet app){
        app.image(this.sprite, this.x, this.y);
    }
}
