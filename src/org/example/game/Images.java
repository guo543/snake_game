package org.example.game;

import javax.swing.*;
import java.io.File;

public class Images {
    public static ImageIcon bodyImg;

    public static ImageIcon headImg;

    public static ImageIcon foodImg;

    static {
        File head = new File("images/head.jpg");
        String headPath = head.getAbsolutePath();
        headImg = new ImageIcon(headPath);

        File body = new File("images/body.jpg");
        String bodyPath = body.getAbsolutePath();
        bodyImg = new ImageIcon(bodyPath);

        File food = new File("images/food.jpg");
        String foodPath = food.getAbsolutePath();
        foodImg = new ImageIcon(foodPath);
    }
}
