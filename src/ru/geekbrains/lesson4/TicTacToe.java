package ru.geekbrains.lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final int SIZE = 3;
    private static final char[][] map = new char[SIZE][SIZE];

    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    public static final Scanner SCANNER = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeGame();
        printMap();

        while (true) {
            humanTurn();
            if (checkEndGame(DOT_X)) {
                printMap();
                break;
            }

            computerTurn();
            printMap();
            if (checkEndGame(DOT_O)) {
                printMap();
                break;
            }
        }
    }

    private static boolean checkEndGame(char symbol) {
        if (checkWin(symbol)) {
            System.out.println(symbol == DOT_X ? "Человек победил!" : "Компьютер победил!");
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья");
            return true;
        }
        return false;
    }

    private static boolean isMapFull() {
        for (char[] rows : map) {
            for (char cellValue : rows) {
                if (cellValue == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char symbol) {
        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;

        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;

        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
        return map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol;
    }

//    public static boolean checkWin2(char symbol) {
//        boolean toright, toleft;
//        toright = true;
//        toleft = true;
//            for (int i=0; i<map.length; i++) {
//                    toright &= (map[i][i] == symbol);
//                    toleft &= (map[map.length - i - 1][i] == symbol);
//                    if (toright || toleft) return true;
//                }
//            return false;
//    }

//    public static boolean checkWin3(char symbol) {
//        for (char[] row : map) {
//            for (char col : row) {
//                if (map[col][col] == symbol){
//                    return true;
//                }
//            }
//
//        }
//        return false;
//    }



        private static void humanTurn() {
        int rowIndex = -1;
        int colIndex = -1;
        do {
            System.out.print("Введите номер строки: ");
            if (!SCANNER.hasNextInt()) {
                SCANNER.nextLine();
                System.out.println("Введите число!");
                continue;
            }
            rowIndex = SCANNER.nextInt() - 1;

            System.out.print("Введите номер столбца: ");
            if (!SCANNER.hasNextInt()) {
                SCANNER.nextLine();
                System.out.println("Введите число!");
                continue;
            }

            colIndex = SCANNER.nextInt() - 1;
        } while (isCellValid(rowIndex, colIndex, DOT_X));

        map[rowIndex][colIndex] = DOT_X;
    }

    private static void computerTurn() {
        int rowIndex;
        int colIndex;
        Random rand = new Random();
        do {
            rowIndex = rand.nextInt(SIZE);
            colIndex = rand.nextInt(SIZE);
        } while (isCellValid(rowIndex, colIndex, DOT_O));

        map[rowIndex][colIndex] = DOT_O;
    }


    private static boolean isCellValid(int rowIndex, int colIndex, char symbol) {
        if (isArrayIndexValid(rowIndex) || isArrayIndexValid(colIndex)) {
            System.out.println("Ты должен был ввести от 0 до " + SIZE);
            return true;
        }
        if (map[rowIndex][colIndex] != DOT_EMPTY) {
            if (isHumanTurn(symbol)) {
            System.out.println("Ячейка занята");
            }
            return true;
        }
        return false;
    }

    private static boolean isHumanTurn(char symbol) {
        return symbol == DOT_X;
    }

    private static boolean isArrayIndexValid(int index) {
        return index < 0 || index >= SIZE;
    }

    private static void printMap() {
        printHeader();
        printMapState();
        System.out.println();
    }

    private static void printMapState() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            System.out.print((rowIndex + 1) + " ");
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                System.out.print(map[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }
    }

    private static void printHeader() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void initializeGame() {
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int colIndex = 0; colIndex < SIZE; colIndex++) {
                map[rowIndex][colIndex] = DOT_EMPTY;

            }
        }
    }

}
