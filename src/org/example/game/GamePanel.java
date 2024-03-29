package org.example.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

public class GamePanel extends JPanel {

    private int[] snakeX = new int[200];
    private int[] snakeY = new int[200];
    private int length = 3;

    private LinkedList<String> steps;

    private HighScores highScores;
    private int max;

    private int foodX;
    private int foodY;
    private int score;

    private String direction;

    private Timer timer;

    boolean started;

    boolean beginning;

    boolean scored;

    boolean dead;

    boolean pathFindingEnabled;

    public void init() {
        beginning = true;

        started = false;
        scored = false;
        dead = false;

        length = 3;

        direction = "R";

        snakeX[0] = 175;
        snakeY[0] = 275;

        snakeX[1] = 150;
        snakeY[1] = 275;

        snakeX[2] = 125;
        snakeY[2] = 275;

        foodX = 300;
        foodY = 200;

        steps = new LinkedList<>();

        score = 0;

        pathFindingEnabled = false;
        PathFinder.findPath(snakeX, snakeY, foodX, foodY, steps, length);
    }

    public GamePanel() throws IOException {
        init();

        highScores = new HighScores();

        if (highScores.getScores().size() != 0) {
            max = highScores.getScores().get(highScores.getScores().size() - 1);
        } else {
            max = 0;
        }

        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_SPACE) {
                    if (dead) {
                        init();
                        started = false;
                        beginning = true;
                    } else {
                        started = !started;
                        if (beginning) {
                            beginning = false;
                        }
                    }

                    repaint();
                }

                if (keyCode == KeyEvent.VK_A) {
                    if (beginning) {
                        pathFindingEnabled = true;
                        started = true;
                        timer.setDelay(30);

                    }
                    if (!beginning && !started) {
                        pathFindingEnabled = false;
                        started = true;
                        timer.setDelay(110);
                    }
                }

                if (!pathFindingEnabled) {
                    if (keyCode == KeyEvent.VK_UP) {
                        if (!"U".equals(direction)) {
                            direction = "U";
                        }
                    }

                    if (keyCode == KeyEvent.VK_DOWN) {
                        if (!"D".equals(direction)) {
                            direction = "D";
                        }
                    }

                    if (keyCode == KeyEvent.VK_LEFT) {
                        if (!"L".equals(direction)) {
                            direction = "L";
                        }
                    }

                    if (keyCode == KeyEvent.VK_RIGHT) {
                        if (!"R".equals(direction)) {
                            direction = "R";
                        }
                    }
                }
            }
        });

        timer = new Timer(110, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (started && !dead) {
                    if (scored) {
                        length++;
                        System.out.println(length);
                        scored = false;
                    }

                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }

                    if (pathFindingEnabled) {
                        try {
                            direction = steps.pop();
                        } catch (NoSuchElementException noSuchElementException) {
                            dead = true;
                            timer.setDelay(110);
                        }

                    }

                    if ("U".equals(direction)) {
                        snakeY[0] -= 25;
                    } else if ("D".equals(direction)) {
                        snakeY[0] += 25;
                    } else if ("L".equals(direction)) {
                        snakeX[0] -= 25;
                    } else {
                        snakeX[0] += 25;
                    }

                    if (snakeX[0] == 750) {
                        snakeX[0] = 25;
                    }
                    if (snakeX[0] < 25) {
                        snakeX[0] = 725;
                    }
                    if (snakeY[0] < 50) {
                        snakeY[0] = 700;
                    }
                    if (snakeY[0] == 725) {
                        snakeY[0] = 50;
                    }
                    for (int i = 1; i < length; i++) {
                        if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]) {
                            dead = true;
                            break;
                        }
                    }

                    if (snakeX[0] == foodX && snakeY[0] == foodY) {
                        scored = true;
                        Random r = new Random();

                        boolean overlap;
                        do {
                            overlap = false;
                            foodX = (r.nextInt(29) + 1) * 25;
                            foodY = (r.nextInt(27) + 2) * 25;

                            for (int i = 0; i < length; i++) {
                                if (foodX == snakeX[i] && foodY == snakeY[i]) {
                                    overlap = true;
                                    break;
                                }
                            }
                        } while (overlap);

                        score += 1;

                        if (pathFindingEnabled) {
                            PathFinder.findPath(snakeX, snakeY, foodX, foodY, steps, length);
                        }
                    }
                    repaint();
                }
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(0, 10, 151));

        g.setColor(new Color(255, 255, 255));
        g.fillRect(25, 50, 725, 675);
        g.setColor(new Color(1, 1, 1));
        g.drawRect(25, 50, 725, 675);

        Images.foodImg.paintIcon(this, g, foodX, foodY);
        Images.headImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        if (!started) {
            g.setColor(new Color(27, 31, 255));
            g.setFont(new Font("TimesNewRoman", Font.BOLD, 30));
            g.setColor(new Color(255, 255, 255));
            g.fillRect(100, 280, 520, 190);
            g.setColor(new Color(27, 31, 255));
            g.drawRect(100, 280, 520, 190);
            if (beginning) {
                g.drawString("Press Space to Start", 130, 330);
                g.drawString("Press A to enable path-finding", 130, 380);
                g.drawString("(beta)", 130, 430);
            } else {
                g.drawString("Press Space to Continue", 130, 330);
                if (pathFindingEnabled) {
                    g.drawString("Press A to disable path-finding", 130, 380);
                }

            }
            this.setBackground(new Color(0, 10, 99));
        }

        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        g.drawString("High Score：" + Math.max(max, score) + "     Score： " + score, 450, 40);

        if (dead) {
            g.setColor(new Color(139, 83, 46));
            g.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
            g.drawString("Press Space to restart", 200, 100);
            g.drawString("Score：" + score, 450, 100);
            try {
                highScores.add(score);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (highScores.getScores().size() != 0) {
                max = highScores.getScores().get(highScores.getScores().size() - 1);
            } else {
                max = 0;
            }

            int rank = 1;
            g.drawString("High Scores", 450, 200);
            for (int i = highScores.getScores().size() - 1; i >= 0; i--) {
                g.drawString(rank + ". " + highScores.getScores().get(i), 450, 200 + rank * 30);
                rank++;
            }
        }
    }
}
