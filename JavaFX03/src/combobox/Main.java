package combobox;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane root = new FlowPane();
		root.setPadding(new Insets(10));
		root.setHgap(10);
		
		Label label = new Label("Hello");
		label.setStyle("-fx-font: 25 Consolas");
		
		String[] greetings = {"Hello", "Hola", "안녕하세요."};
		ComboBox cb = new ComboBox();
		cb.getItems().addAll("English", "Spanish", "Korean");
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				label.setText(greetings[newValue.intValue()]);
				
			}
		});
		cb.setTooltip(new Tooltip("Select thr language"));
		cb.setValue("English");
		
		root.getChildren().addAll(cb, label);
		
		
		Scene scene = new Scene(root, 350, 350);
		primaryStage.setTitle("Title");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);

	}

}
