package sudoku.problemdomain;

import sudoku.computationlogic.SudokuUtilities;
import sudoku.constants.GameStage;

import java.io.Serializable;

public class SudokuGame implements Serializable {
    private final GameStage gameStage;
    private final int[][] grindStage;

    public static final int GRID_BOUNDARY = 9;


    public GameStage getGameStage() {
        return gameStage;
    }

    public int[][] getCopyOfGrindStage() {
        return SudokuUtilities.copyToNewArray(grindStage);
    }

    public SudokuGame(GameStage gameStage, int[][] grindStage) {
        this.gameStage = gameStage;
        this.grindStage = grindStage;
    }
}
