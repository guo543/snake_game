package org.example.game;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathFinder {

    public static void findPath(int[] snakeX, int[] snakeY, int foodX, int foodY, LinkedList<String> steps, int length) {
        int[][] matrix = buildMatrix(snakeX, snakeY, length);

        Vertex start = new Vertex(snakeX[0], snakeY[0], null, null);

        LinkedList<Vertex> q = new LinkedList<>();
        ArrayList<Vertex> traversed = new ArrayList<>();
        q.add(start);
        traversed.add(start);

        int index = 0;
        
        while (!q.isEmpty()) {
            Vertex v = q.pop();

            if (v.getX() == foodX && v.getY() == foodY) {
                index = traversed.indexOf(v);
                break;
            }

            int x = v.getX();
            int y = v.getY();

            int upX = x;
            int upY = y - 25;
            if (upY < 50) {
                upY = 700;
            }
            if (matrix[upX][upY] != 1) {
                Vertex up = new Vertex(upX, upY, v, "U");
                q.add(up);
                traversed.add(up);
                matrix[upX][upY] = 1;
            }

            int rightX = x + 25;
            int rightY = y;
            if (rightX == 750) {
                rightX = 25;
            }
            if (matrix[rightX][rightY] != 1) {
                Vertex right = new Vertex(rightX, rightY, v, "R");
                q.add(right);
                traversed.add(right);
                matrix[rightX][rightY] = 1;
            }

            int downX = x;
            int downY = y + 25;
            if (downY == 725) {
                downY = 50;
            }
            if (matrix[downX][downY] != 1) {
                Vertex down = new Vertex(downX, downY, v, "D");
                q.add(down);
                traversed.add(down);
                matrix[downX][downY] = 1;
            }

            int leftX = x - 25;
            int leftY = y;
            if (leftX < 25) {
                leftX = 725;
            }
            if (matrix[leftX][leftY] != 1) {
                Vertex left = new Vertex(leftX, leftY, v, "L");
                q.add(left);
                traversed.add(left);
                matrix[leftX][leftY] = 1;
            }
        }

        Vertex current = traversed.get(index);

        while (current.getStep() != null) {
            steps.add(0, current.getStep());
            current = current.getPrev();
        }

        System.out.println(steps);

    }

    public static int[][] buildMatrix(int[] snakeX, int[] snakeY, int length) {
        int[][] matrix = new int[800][800];

        for (int i = 0; i < length; i++) {
            matrix[snakeX[i]][snakeY[i]] = 1;
        }

        return matrix;
    }

}
