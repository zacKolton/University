public class Game2048 {

    private GraphicsWindow2048 myWindow;
    private Board2048[] boardList;
    private static int MAX = 100000;
    private int currentPos;

    public Game2048(GraphicsWindow2048 theWindow, int size) {
        myWindow = theWindow;
        boardList = new Board2048[MAX];
        boardList[0] = new Board2048(size);
        boardList[0].newTile();
        currentPos = 0;
        theWindow.displayBoard(boardList[0].getMatrix());
    }//Game2048

    public void tryMove(int direction){
        if(boardList[currentPos].validMove(direction))
        {
            Board2048 newBoard = boardList[currentPos].shift(direction);
            newBoard.newTile();
            boardList[++currentPos] = newBoard;
            myWindow.displayBoard(newBoard.getMatrix());
            if(newBoard.gameOver())
            {
                myWindow.displayMessage("Game Over");
            }
        }
    }//tryMove

    public void undo(){
        Board2048 newBoard = boardList[--currentPos];
        myWindow.displayBoard(newBoard.getMatrix());
        myWindow.displayMessage(null);
    }//undo

}//Game2048 class
 