package tableview;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	TableView<Person> table = new TableView<Person>();
	final ObservableList<Person> data = FXCollections.observableArrayList(
		new Person("Jacob", "Smith", "jacob.smith@example.com"),
		new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
		new Person("ethan", "Williams", "ethan.williams@example.com"),
		new Person("Emma", "Watson", "emma.watson@example.com"),
		new Person("Michael", "Brown", "michael.brown@example.com")
	);
	public class Person {
		private SimpleStringProperty firstName;
		private SimpleStringProperty lastName;
		private SimpleStringProperty email;
		
		public Person(String fName, String lName, String email) {
			this.firstName = new SimpleStringProperty(fName);
			this.lastName = new SimpleStringProperty(lName);
			this.email = new SimpleStringProperty(email);
		}
		
		public String getFirstName() {
			return this.firstName.get();
		}
		
		public void setFirstName(String fName) {
			this.firstName.set(fName);
		}
		
		public String getLastName() {
			return this.lastName.get();
		}
		
		public void setLastName(String lName) {
			this.lastName.set(lName);
		}
		
		public String getEmail() {
			return this.email.get();
		}
		
		public void setEmail(String email) {
			this.email.set(email);
		}
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Title");
		
		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setSpacing(5);
		
		Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));
		
		table.setEditable(true);
		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		TableColumn lastNameCol = new TableColumn("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		TableColumn emailCol = new TableColumn("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
		table.setItems(data);
		
		HBox hb = new HBox();
		
		TextField ftf = new TextField();
		ftf.setPromptText("First Name");
		ftf.setMaxWidth(firstNameCol.getPrefWidth());
		TextField ltf = new TextField();
		ltf.setPromptText("Last Name");
		ltf.setMaxWidth(lastNameCol.getPrefWidth());
		TextField etf = new TextField();
		etf.setPromptText("Email");
		etf.setMaxWidth(emailCol.getPrefWidth());
		
		Button add = new Button("Add");
		add.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(add, Priority.ALWAYS);
		add.setOnAction(event-> {
			if (!ftf.getText().isEmpty() && !ltf.getText().isEmpty() && !etf.getText().isEmpty() ) {
				data.add(new Person(ftf.getText(), ltf.getText(), etf.getText()));
				ftf.clear();
				ltf.clear();
				etf.clear();
			}
		});
		
		hb.getChildren().addAll(ftf, ltf, etf, add);
		
		root.getChildren().addAll(label, table, hb);
		Scene scene = new Scene(root, 450, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
