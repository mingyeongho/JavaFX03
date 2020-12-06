package textarea;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		
		TextArea ta = new TextArea();
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		GridPane panel = new GridPane();
		panel.setHgap(15);
		TextField tf = new TextField();
		tf.setPrefColumnCount(40);
		tf.setPromptText("여기에 입력 후 엔터를 치세요.");
		Button submit = new Button("Submit");
		
		panel.add(tf, 0, 0,2,1);
		panel.add(submit, 2,0,1,1);
		
		BorderPane.setMargin(root, new Insets(10, 0, 0, 0));
		
		tf.setOnAction(event-> {
			ta.appendText(tf.getText()+"\n");
			tf.clear();
		});
		submit.setOnAction(event-> {
			ta.appendText(tf.getText()+"\n");
			tf.clear();
		});
		
		
		root.setCenter(ta);
		root.setBottom(panel);
		
		Scene scene = new Scene(root, 350, 350);
		primaryStage.setTitle("Title");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
