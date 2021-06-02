package org.example.game;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public JButton startGame;
    public JButton highScores;

    public void init() {
        startGame = new JButton("开始游戏");
        startGame.setFont(new Font("微软雅黑", Font.BOLD, 40));
        startGame.setPreferredSize(new Dimension(400, 100));
        startGame.setContentAreaFilled(false);

        highScores = new JButton("高分榜");
        highScores.setFont(new Font("微软雅黑", Font.BOLD, 40));
        highScores.setPreferredSize(new Dimension(400, 100));
        highScores.setContentAreaFilled(false);
    }

    public MenuPanel() {
        init();

        this.setBackground(new Color(232, 255, 233));

        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        this.add(startGame, constraints);
        constraints.gridy = 1;

        this.add(highScores, constraints);
    }
}
