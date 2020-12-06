package listview;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		
		Label label = new Label("Not Selected");
		String[] names = {"Apple", "Banana", "Kiwi", "Mango", "Berry"};
		ListView<String> list = new ListView<String>();
		list.getItems().addAll(names);
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		list.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				String str = "";
				ObservableList selIndices = list.getSelectionModel().getSelectedIndices();
				for (Object obj : selIndices) {
					str += names[obj.hashCode()] + " ";
				}
				label.setText(str);
				
			}
		});
		
		root.setTop(new Label("Select an item or multiple items"));
		root.setCenter(list);
		root.setBottom(label);
		BorderPane.setMargin(root, new Insets(10, 0, 10, 0));
		
		Scene scene = new Scene(root, 350, 300);
		primaryStage.setTitle("Title");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
