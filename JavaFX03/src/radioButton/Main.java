package radioButton;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();
		root.setPadding(new Insets(20));
		
		ToggleGroup group = new ToggleGroup();
		RadioButton b1 = new RadioButton("Up");
		RadioButton b2 = new RadioButton("Stop");
		RadioButton b3 = new RadioButton("Down");
		b1.setToggleGroup(group);
		b1.setSelected(true);
		b2.setToggleGroup(group);
		b3.setToggleGroup(group);
		
		Image up = new Image(this.getClass().getResourceAsStream("Stock Index Up_24x24.png"));
		ImageView icon = new ImageView(up);
		
		b1.setUserData("Stock Index Up_24x24.png");
		b2.setUserData("Stop_24x24.png");
		b3.setUserData("Stock Index Down_24x24.png");
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(javafx.beans.value.ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if(group.getSelectedToggle() != null) {
					Image img = new Image(this.getClass().getResourceAsStream(group.getSelectedToggle().getUserData().toString()));
					icon.setImage(img);
				}
			};
		});
		
		VBox left = new VBox();
		left.setSpacing(10);
		left.getChildren().addAll(b1,b2,b3);
		
		HBox right = new HBox();
		right.setPrefWidth(150);
		right.setAlignment(Pos.CENTER);
		right.getChildren().add(icon);
		
		root.getChildren().addAll(left, right);
		
		
		Scene scene = new Scene(root, 400, 150);
		primaryStage.setTitle("Title");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
