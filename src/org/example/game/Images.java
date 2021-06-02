package org.example.game;

import javax.swing.*;
import java.net.URL;

public class Images {

    public static URL bodyUrl = Images.class.getResource("/images/body.jpg");

    public static ImageIcon bodyImg = new ImageIcon(bodyUrl);

    public static URL headUrl = Images.class.getResource("/images/head.jpg");

    public static ImageIcon headImg = new ImageIcon(headUrl);

    public static URL foodUrl = Images.class.getResource("/images/food.jpg");

    public static ImageIcon foodImg = new ImageIcon(foodUrl);
}
