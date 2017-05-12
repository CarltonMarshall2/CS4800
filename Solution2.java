import java.util.ArrayList;
import java.util.Scanner;

// In this challenge, you are asked to devise an algorithm which given a partial
// Latin square completes it to a full one. Use the "backtracking" approach which 
// simulates a run of the DFS algorithm on a suitable graph.

class Cell {
    final int row;
    final int column;

    Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class LatinSquare {
    private final int[][] matrix;

    LatinSquare(int[][] matrix) {
        this.matrix = matrix;
    }

    boolean isLatinSquareSolved(Cell currentCell) {
        if (currentCell == null) {
            return true;
        }
        if (matrix[currentCell.row][currentCell.column] != 0) {
            return this.isLatinSquareSolved(this.getnextCell(currentCell));
        }
        int i = 1;
        while (i <= matrix.length) {
            if (this.isCellValueValid(currentCell, i)) {
                matrix[currentCell.row][currentCell.column] = i;
                boolean isSolved;
                if (this.isLatinSquareSolved(this.getnextCell(currentCell))) {
                    isSolved = true;
                }
                else {
                    isSolved = false;
                }
                if (isSolved) {
                    return true;
                }
                else {
                    matrix[currentCell.row][currentCell.column] = 0;
                }
            }
            i++;
        }
        return false;
    }

    private boolean isCellValueValid(Cell cell, int cellValue) {
        int i = 0;
        while (i < matrix[cell.row].length) {
            if (matrix[cell.row][i] == cellValue) {
                return false;
            }
            i++;
        }
        for (int[] aMatrix : matrix) {
            if (aMatrix[cell.column] == cellValue) {
                return false;
            }
        }
        return true;
    }

    private Cell getnextCell(Cell currentCell) {
        int row = currentCell.row;
        int column = currentCell.column;

        column++;

        if (column == matrix[row].length) {
            column = 0;
            row++;
        }

        if (row == matrix.length) {
            return null;
        }

        return new Cell(row, column);
    }

    void printLatinSquare() {
        String result = "";
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                result += anAMatrix + " ";
            }
            result += "\n";
        }
        System.out.println(result);
    }
}

public class Solution2 {
    public static void main(String[] args) {
        ArrayList<Integer> inputList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            inputList.add(scanner.nextInt());
        }

        int matrixSize = (int) (Math.sqrt(inputList.size()));
        int matrix[][] = new int[matrixSize][matrixSize];

        for (int i = 0; i < inputList.size(); i++) {
            matrix[i / matrixSize][i % matrixSize] = inputList.get(i);
        }

        LatinSquare square = new LatinSquare(matrix);
        square.isLatinSquareSolved(new Cell(0, 0));
        square.printLatinSquare();
    }
}
