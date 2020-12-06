package mediaview;


import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: black");
		
		Media media = new Media(new File("test.mp4").toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		player.setAutoPlay(false);
		MediaView mediaview = new MediaView(player);
		mediaview.setPreserveRatio(true);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(30);
		Image pauseImg = new Image(getClass().getResourceAsStream("Pause_24x24.png"));
		Image playImg = new Image(getClass().getResourceAsStream("Play_24x24.png"));
		Image stopImg = new Image(getClass().getResourceAsStream("Stop_24x24.png"));
		Button pause = new Button();
		pause.setGraphic(new ImageView(pauseImg));
		Button play = new Button();
		play.setGraphic(new ImageView(playImg));
		Button  stop = new Button();
		stop.setGraphic(new ImageView(stopImg));
		
		pause.setOnAction(event-> {
			player.pause();
		});
		play.setOnAction(event->{
			player.play();
		});
		stop.setOnAction(event-> {
			player.stop();
		});
		
		
		
		hbox.getChildren().addAll(pause, play, stop);
		root.setCenter(mediaview);
		root.setBottom(hbox);
		
		Scene scene = new Scene(root, 400, 350);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Media Player");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
