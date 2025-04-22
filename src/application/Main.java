package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	TabPane tabs=new TabPane();
	private Tab mainPageT =new Tab ("https://GazaAirport.com");
	private MainPage mainPage =new MainPage(tabs);
	@Override
	public void start(Stage primaryStage) {
		try {
			mainPageT.setContent(mainPage.getMainPage());
			tabs.getTabs().addAll(mainPageT);
			Scene scene = new Scene(tabs,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Airport Check-In and Boarding System.");
			primaryStage.setMaximized(true);
			primaryStage.getIcons().add(new Image("logo.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
