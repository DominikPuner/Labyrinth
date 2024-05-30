package com.example.labyrinth;

import android.graphics.Rect;
import android.widget.ImageView;

public class Mauer {
    Rect hitbox;
    int x;
    int y;
    ImageView mauerView;

    public Mauer(int x, int y, ImageView mauerView){
        this.x = x;
        this.y = y;
        this.mauerView = mauerView;

        hitbox = new Rect(x, y, x + mauerView.getWidth(), y + mauerView.getHeight());
        this.mauerView.getHitRect(hitbox);
    }

    public Rect getHitbox(){
        return hitbox;
    }


}
