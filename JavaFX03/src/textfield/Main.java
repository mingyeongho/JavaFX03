package textfield;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		GridPane root = new GridPane();
		root.setPadding(new Insets(20));
		root.setHgap(10);
		root.setVgap(10);
		
		TextField firstName = new TextField();
		firstName.setPrefColumnCount(20);
		firstName.setPromptText("Enter your first name");
		TextField secondName = new TextField();
		secondName.setPrefColumnCount(20);
		secondName.setPromptText("Enter your second name");
		TextField comment = new TextField();
		comment.setPrefColumnCount(20);
		comment.setPromptText("Enter your comment");
		
		Button submit = new Button("Submit");
		Button clear = new Button("Clear");
		
		Label label = new Label("You have not left a comments");
		
		submit.setOnAction(event-> {
			if (!comment.getText().isEmpty() && comment.getText() != null) {
				label.setText(firstName.getText()+" "+secondName.getText()+", "+comment.getText());
			} else {
				label.setText("You have not left a comments");
			}
		});
		
		clear.setOnAction(event-> {
			firstName.clear();
			secondName.clear();
			comment.clear();
			label.setText("You have not left a comments");
		});
		
		root.add(firstName, 0,0,2,1);
		root.add(secondName, 0, 1, 2, 1);
		root.add(comment, 0, 2, 2, 1);
		root.add(submit, 2, 0, 1, 1);
		root.add(clear, 2, 1, 1, 1);
		root.add(label, 0, 3, 2, 1);
		
		
		Scene scene = new Scene(root, 400, 200);
		arg0.setTitle("Title");
		arg0.setScene(scene);
		arg0.show();
		
	}
	public static void main(String[] args) {
		launch(args);

	}

}
