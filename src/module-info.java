module Airport_System {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
	//opens MainPage to java.base;
}
