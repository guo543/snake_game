package org.example.game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartGame {

    public static void main(String[] args) throws IOException {
        JFrame jFrame = new JFrame();
        CardLayout cards = new CardLayout();
        JPanel container = new JPanel(cards);

        jFrame.setTitle("贪吃蛇");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        MenuPanel menuPanel = new MenuPanel();
        GamePanel gamePanel = null;
        try {
            gamePanel = new GamePanel();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "程序根目录下找不到文件 scores.txt", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }


        jFrame.add(container);
        //container.add(menuPanel, "menu");

        container.add(gamePanel);

//        menuPanel.startGame.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                GamePanel gamePanel = new GamePanel();
//                gamePanel.setEnabled(true);
//                gamePanel.requestFocus();
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                container.add(gamePanel, "game");
//                cards.show(container, "game");
//            }
//        });

        jFrame.setBounds((width - 800) / 2, (height - 800) / 2, 800, 800);
        jFrame.setSize(800, 800);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private static void startGame() throws IOException {
        JFrame gameFrame = new JFrame();
        gameFrame.add(new GamePanel());

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        gameFrame.setBounds((width - 800) / 2, (height - 800) / 2, 800, 800);
        gameFrame.setSize(800, 800);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }
}
