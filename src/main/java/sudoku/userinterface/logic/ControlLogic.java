package sudoku.userinterface.logic;

import sudoku.computationlogic.GameLogic;
import sudoku.constants.GameStage;
import sudoku.constants.Message;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;

    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try{
            SudokuGame gameData = storage.getGameData();
            int[][] newGrindState = gameData.getCopyOfGrindStage();
            newGrindState[x][y] = input;

            gameData = new SudokuGame(
                    GameLogic.CheckForCompletion(newGrindState),
                    newGrindState
            );

            storage.updateGameData(gameData);

            view.updateSquare(x, y, input);
            if (gameData.getGameStage() == GameStage.COMPLETE){
                view.showDialog(Message.GAME_COMPLETE);
            }
        }catch (IOException e){
            e.printStackTrace();;
            view.showError(Message.ERROR);
        }
    }

    @Override
    public void onDialogClick() {
        try {
            storage.updateGameData(
                    GameLogic.getNewGame()
            );

            view.updateBoard(storage.getGameData());
        }catch (IOException e){
            view.showError(Message.ERROR);
        }
    }
}
