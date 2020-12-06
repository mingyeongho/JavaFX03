package menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Vector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
//		MenuBar
		MenuBar menubar = new MenuBar();
		
//		Menu
		Menu file = new Menu("_File");
		Menu edit = new Menu("_Edit");
		Menu project = new Menu("Project");
		Menu help = new Menu("_Help");
		Menu dialog = new Menu("Dialog");
		
//		Image
		Image newImg = new Image(this.getClass().getResourceAsStream("New_16x16.png"));
		Image open = new Image(new FileInputStream("Open_16x16.png"));
		
//		MenuItem
		MenuItem newItem = new MenuItem("_New",new ImageView(newImg));
		newItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+N"));
		MenuItem openItem = new MenuItem("_Open",new ImageView(open));
		MenuItem exitItem = new MenuItem("E_xit");
		
		MenuItem copy = new MenuItem("_Copy");
		copy.setAccelerator(KeyCombination.keyCombination("SHORTCUT+C"));
		MenuItem paste = new MenuItem("_Paste");
		
		CheckMenuItem build = new CheckMenuItem("Build Automatically");
		build.setSelected(true);
		
		MenuItem info = new MenuItem("Information");
		MenuItem error = new MenuItem("Error");
		MenuItem warning = new MenuItem("Warning");
		MenuItem confirm = new MenuItem("Confirmation");
		MenuItem inputDialog = new MenuItem("input");
		MenuItem choice = new MenuItem("Choice Dialog");
		
//		ContextMenu
		ContextMenu conMenu = new ContextMenu(file, edit);
		root.setOnContextMenuRequested(event-> {
			conMenu.show(root, event.getScreenX(), event.getScreenY());
		});
		
//		FileChooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All File", "*.*"),
				new FileChooser.ExtensionFilter("Jpeg File", "*.jpeg"),
				new FileChooser.ExtensionFilter("Png File", "*.png"),
				new FileChooser.ExtensionFilter("Gif File", "*.gif")
		);
		
//		Event
		exitItem.setOnAction(event-> {
			System.exit(0);
		});
		
		newItem.setOnAction(event-> {
			File file_1 = fileChooser.showOpenDialog(primaryStage);
			if (file_1 != null) {
				try {
					Image img = new Image(new FileInputStream(file_1));
					ImageView imgview = new ImageView(img);
					imgview.fitWidthProperty().bind(primaryStage.widthProperty());
					imgview.setPreserveRatio(true);
					root.setCenter(new ScrollPane(imgview));
				} catch(FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		info.setOnAction(event-> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Header");
//			alert.setHeaderText(null);
//			alert.setContentText("Information Dialog");
			alert.setContentText(null);
			alert.showAndWait(); // show
		});
		
		error.setOnAction(event-> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Error Dialog");
			alert.showAndWait();
		});
		
		warning.setOnAction(event-> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Warning Dialog");
			alert.showAndWait();
		});
		
		confirm.setOnAction(event-> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Confirmation Dialog");
			alert.showAndWait();
		});
		
		inputDialog.setOnAction(event-> {
			TextInputDialog textInputDialog = new TextInputDialog();
			textInputDialog.setTitle("textInputDialog");
			textInputDialog.setHeaderText("Name");
			textInputDialog.setContentText("ContentText");
			
			Optional<String> result = textInputDialog.showAndWait();
			if (result.isPresent()) {
				System.out.println("Your name: " + result.get());
			}
		});
		
		choice.setOnAction(event-> {
			Vector<String> choices = new Vector<String>();
			choices.add("Plant");
			choices.add("Apple");
			choices.add("Person");
			
			ChoiceDialog<String> a_dialog = new ChoiceDialog<String>("Plant", choices);
			a_dialog.setTitle("Choice Dialog");
			a_dialog.setHeaderText("Choice Dialog");
			a_dialog.setContentText("Choose One: ");
			Optional<String> result = a_dialog.showAndWait();
			if (result.isPresent()) {
				System.out.println("Your name: " + result.get());
			}
		});
		
		file.getItems().addAll(newItem, openItem, new SeparatorMenuItem(), exitItem);
		edit.getItems().addAll(copy, paste);
		project.getItems().addAll(build);
		dialog.getItems().addAll(info, error, warning, confirm, inputDialog, choice);
		menubar.getMenus().addAll(file, edit, project, help, dialog);
		root.setTop(menubar);
		
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setTitle("Menu");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
