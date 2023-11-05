package client.entities.enemy;

import client.entities.common.ClientEntity;
import client.entities.enemy.factories.EnemyFactory;
import client.entities.enemy.types.Enemy;
import client.utilities.Drawer;

import java.awt.*;

public class EnemyClientEntity extends ClientEntity {
    private final Enemy enemy;
    private final int height = 24;

    public EnemyClientEntity(int id, int x, int y, EnemyFactory factory) {
        super(id, x, y);

        enemy = factory.createEnemy();

        rectangle = new Rectangle(x, y, enemy.getSize(), height);
    }

    public void updateRect(){
        rectangle = new Rectangle(x, y, enemy.getSize(),height);
    }

    public void left(){ // move alien to left
        x -= 18;
        updateRect();
    }

    public void smoothLeft(){ // used for the special UFO (moves slower so it looks smoother)
        x -= 6;
        updateRect();
    }

    public void right(){ // move alien to right
        x += 18;
        updateRect();
    }

    public void down(){ // moves down alien
        y += height;
        updateRect();
    }

    public int getSizeX(){
        return enemy.getSize();
    }

    public int getX(){
        return x;
    } // return alien position

    public int getY(){
        return y;
    }

    public Image getImage(int beat){ // method determines which image to display depending on beat value
        if (beat==0) return enemy.getFirstPose();
        else return enemy.getSecondPose();
    }
}
