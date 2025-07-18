package com.aurionpro.model;

public class Board {
    private CellValue[] cells = new CellValue[9];

    public Board() {
        for (int i = 0; i < 9; i++) {
            cells[i] = CellValue.EMPTY;
        }
    }
    
    public CellValue getCell(int index) {
        return cells[index];
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (cells[i] == CellValue.EMPTY) {
                System.out.print(" " + (i + 1) + " ");
            } else {
                System.out.print(" " + cells[i].getSymbol() + " ");
            }

            if (i % 3 != 2) System.out.print("|");
            else if (i != 8) System.out.println("\n-----------");
        }
        System.out.println("\n");
    }

    public boolean updateCell(int index, CellValue value) {
        if (cells[index] == CellValue.EMPTY) {
            cells[index] = value;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (CellValue cell : cells) {
            if (cell == CellValue.EMPTY) return false;
        }
        return true;
    }

    public CellValue checkWinner() {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        //take winConditions and check if in cells they are followed, i.e {0,1,2} so check in cells what are in 0,1,2
        // they may all be empty so check for empty then check they are same
        for (int[] line : winConditions) {
            CellValue a = cells[line[0]];
            CellValue b = cells[line[1]];
            CellValue c = cells[line[2]];
            if (a != CellValue.EMPTY && a == b && b == c) {
                return a;
            }
        }

        return CellValue.EMPTY;
    }
}

