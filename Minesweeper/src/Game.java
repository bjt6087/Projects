import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by benjamin on 6/29/2016.
 */
public class Game extends Application{
    private Button[][] buttonGrid;
    private Model game;
    private GridPane board;
    private javafx.scene.control.Label gametext;
    private Integer[][] Gridcheck;
    private Stage stage;

    /**
     * Method created for setting the background of buttons.
     * @param: Button button
     * @param: String bgImgName
     */
    private void setButtonBackground(Button button, String bgImgName) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new javafx.scene.image.Image(getClass().getResource("resources/" + bgImgName).toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
    }

    /**
     * Initializes GUI
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        GridPane board = new GridPane();
        this.game = new Model(16);
        buttonGrid = new Button[16][16];
        Gridcheck = new Integer[16][16];
        for(int r = 0; r< game.getDimensions(); r++ ){
            for(int c = 0; c < game.getDimensions(); c++){
                Button space = new Button("   ");
                setButtonBackground(space,"grid-unknown.png");
                spaceListener(space,r,c);
                board.add(space,c,r);
                buttonGrid[r][c] = space;
                if(game.getData(r,c)==9){
                    Gridcheck[r][c] = 3;
                }
                else{
                    Gridcheck[r][c]=1;
                }
            }
        }
        pane.setCenter(board);
        HBox text = new HBox();
        gametext = new javafx.scene.control.Label("Game Start!");
        text.getChildren().add(gametext);
        pane.setTop(text);
        text.setAlignment(Pos.TOP_CENTER);
        board.setAlignment(Pos.CENTER);
        FlowPane Bottom = new FlowPane();
        Button Restart = new Button("Restart");
        Bottom.getChildren().add(Restart);
        RestartListener(Restart);
        Bottom.setAlignment(Pos.BOTTOM_CENTER);
        pane.setBottom(Bottom);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        stage = primaryStage;
        primaryStage.show();
    }

    /**
     * Listener for the restart button.
     * @param: Button button
     */
    public void RestartListener(Button button){
        button.setOnAction(e -> {
            try {
                restart();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void floodfill(int r, int c){
        if(game.getData(r,c)==9){
            return;
        }
        else{
            buttonGrid[r][c].setText(new Integer(game.getData(r, c)).toString());
            setButtonBackground(buttonGrid[r][c], "white.png");
            Gridcheck[r][c] = 2;
            update();
            if(r+1<16 && Gridcheck[r+1][c]!=2){
                floodfill(r+1,c);
            }
            if(r-1>0 && Gridcheck[r-1][c]!=2){
                floodfill(r-1,c);
            }
            if(c+1<16 && Gridcheck[r][c+1]!=2){
                floodfill(r,c+1);
            }
            if(c-1>0 && Gridcheck[r][c-1]!=2){
                floodfill(r,c-1);
            }

        }
    }

    /**
     * Listener for each grid space in the game.
     * @param: Button button
     * @param: int  r
     * @param: int c
     */
    public void spaceListener(Button button , int r, int c){
        button.setOnAction(e -> {
            if(game.getData(r,c)==9){
                setButtonBackground(buttonGrid[r][c],"icon.png");
                GameOver();
            }
            else{

                buttonGrid[r][c].setText(new Integer(game.getData(r, c)).toString());
                setButtonBackground(buttonGrid[r][c], "white.png");
                Gridcheck[r][c] = 2;
                update();
                floodfill(r,c);
            }
        });
    }



    /**
     * Method dictates what occurs when the game ends.
     */
    public void GameOver() {
        for (int row = 0; row < game.getDimensions(); row++) {
            for (int column = 0; column < game.getDimensions(); column++) {
                if (game.getData(row, column) == 9) {
                    setButtonBackground(buttonGrid[row][column], "icon.png");
                } else {
                    buttonGrid[row][column].setText(new Integer(game.getData(row, column)).toString());
                    setButtonBackground(buttonGrid[row][column], "white.png");
                }
            }
        }
        gametext.setText("Game Over");
    }

    /**
     * Checks after each button press to see if after each button press if the game has been won.
     */
    public void update(){
        if(SolutionCheck()==true){
            gametext.setText("Congratulations you won");
            for (int r = 0; r < game.getDimensions(); r++) {
                for (int c = 0; c < game.getDimensions(); c++) {
                    if (game.getData(r, c) == 9) {
                        setButtonBackground(buttonGrid[r][c], "icon.png");
                    } else {
                        buttonGrid[r][c].setText(new Integer(game.getData(r, c)).toString());
                        setButtonBackground(buttonGrid[r][c], "white.png");
                    }
                }
            }
        }
    }

    /**
     * method to check to see if the game has ended.
     * @return: boolean
     */
    public boolean SolutionCheck(){
        boolean check = true;
        outerloop:
        for(int r=0; r<16; r++){
            for(int c = 0; c<16; c++){
                if(Gridcheck[r][c]==1){
                    check = false;
                    break outerloop;
                }
            }
        }
        return check;
    }

    /**
     * Restarts the game
     * @throws Exception
     */
    public void restart() throws Exception {
        start(stage);
    }

    /**
     * main function launches application.
     * @param args
     */
    public static void main(String args[]){
        Application.launch();}

}
