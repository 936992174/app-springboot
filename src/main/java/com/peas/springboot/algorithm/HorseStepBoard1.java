package com.peas.springboot.algorithm;

import java.util.Stack;

/**
 * @Description 马踏棋盘
 * @Author 7287
 * @Date 2020/6/2 15:08
 * @Version V1.0
 **/
public class HorseStepBoard1 {

    private static int steps = 1;
    private static Stack<Elem> stack = new Stack<>();
    private static int[][] move = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    private static int[][] board = new int[12][12];

    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == 0 || i == 1 || j == 0 || j == 1 || i == 10 || i == 11 || j == 10 || j == 11) {
                    board[i][j] = -1;
                }
            }
        }
        horse(2, 2);
        printBoard();
    }

    public static void horse(int x, int y) {
        int i = 0;
        int a = 0;
        int b = 0;
        board[x][y] = steps;
        while (steps < 64) {
            for (; i < 8; i++) {
                a = x + move[i][0];
                b = y + move[i][1];
                if (board[a][b] == 0) {
                    break;
                }
            }
            if (i < 8) {
                board[a][b] = ++steps;
                stack.push(new Elem(i, x, y));//当前位置
                x = a;
                y = b;
                i = 0;
            } else {
                steps--;
                board[x][y] = 0;
                Elem pop = stack.pop();
                x = pop.getX();
                y = pop.getY();
                i = pop.getI();
                i++;
            }
        }
    }

    private static void printBoard() {
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }

    static class Elem {
        int i;
        int x;
        int y;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Elem(int i, int x, int y) {
            this.i = i;
            this.x = x;
            this.y = y;
        }
    }

}
