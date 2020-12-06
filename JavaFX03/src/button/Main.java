package button;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		HBox root = new HBox();
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
		
		Button hello = new Button("Hello");
		
		hello.setOnAction(event-> {
			if (hello.getText().equals("Hello")) {
				hello.setText("Accept");
			} else {
				hello.setText("Hello");
			}
		});
		
		Button accept = new Button("Accept");
		
		DropShadow shadow = new DropShadow();
		accept.setOnMouseEntered(event-> {
			accept.setEffect(shadow);
		});
		accept.setOnMouseExited(event-> {
			accept.setEffect(null);
		});
		
		Image img = new Image(this.getClass().getResourceAsStream("Check_32x32.png"));
		Image delImg = new Image(this.getClass().getResourceAsStream("Delete_32x32.png"));
		Button imgBtn = new Button("Accept", new ImageView(img));
		
		imgBtn.setOnAction(event-> {
			if (imgBtn.getText().equals("Accept")) {
				imgBtn.setText("Hello");
				imgBtn.setGraphic(new ImageView(delImg));
			} else {
				imgBtn.setText("Accept");
				imgBtn.setGraphic(new ImageView(img));
			}
		});
		
		
		
		
		root.getChildren().addAll(hello, accept, imgBtn);
		
		Scene scene = new Scene(root, 300, 300);
		primaryStage.setTitle("Title");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
