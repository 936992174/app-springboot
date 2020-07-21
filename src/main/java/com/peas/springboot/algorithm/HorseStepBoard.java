package com.peas.springboot.algorithm;

/**
 * @Description 马踏棋盘
 * @Author 7287
 * @Date 2020/6/2 15:08
 * @Version V1.0
 **/
public class HorseStepBoard {

    private static int steps = 1;
    private static int sum;
    private static int[][] move = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, 1}};
    private static int[][] board = new int[12][12];

    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == 0 || i == 1 || j == 0 || j == 1 || i == 10 || i == 11 || j == 10 || j == 11) {
                    board[i][j] = -1;
                }
            }
        }
        board[2][2] = steps;
        horse(2, 2);
    }

    public static void horse(int x, int y) {
        if (steps >= 64) {
            sum += 1;
            printBoard();
            return;
        }
        for (int i = 0; i < 8; i++) {
            int a = x + move[i][0];
            int b = y + move[i][1];
            if (board[a][b] == 0) {//没走过
                steps++;
                board[a][b] = steps;
                horse(a, b);
                steps--;
                board[a][b] = 0;
            }
        }
    }

    private static void printBoard() {
        System.out.println("第" + sum + "中方案：");
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                System.out.println(board[i][j] + " ");
            }
            System.out.println("");
        }
    }



}
