package application;





import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainPage {
	private VBox main;
	private Tab maintab;
	private TabPane tabs;
	private DLL flights=new DLL();
	private DNode tempFlight;
	private DNode tempFlightForPassengers;
	private Passenger tempPassenger;
	private FileChooser fileChooser;
	private Queue log=new Queue();
	private DatePicker dp=new DatePicker(LocalDate.now());
	TableView<Passenger> passengersinFidTable=new TableView<>();
	ObservableList<Passenger> passengersinFidList=FXCollections.observableArrayList();
	ObservableList<Passenger> bordedpassengersinFidList=FXCollections.observableArrayList();
	ObservableList<Passenger> canceledpassengersinFidList=FXCollections.observableArrayList();
	ObservableList<Passenger> vippassengersinFidList=FXCollections.observableArrayList();
	ObservableList<Passenger> regpassengersinFidList=FXCollections.observableArrayList();
	ObservableList<Flight> allFlightslist = FXCollections.observableArrayList();
	ObservableList<Flight> activeFlightslist = FXCollections.observableArrayList();
	ObservableList<Flight> inactiveFlightslist = FXCollections.observableArrayList();
	ObservableList<String> destinations = FXCollections.observableArrayList(
			 "United States", "Canada", "Mexico", "United Kingdom", "Germany", 
	            "France", "Italy", "Spain", "Australia", "Japan", 
	            "China", "India", "Brazil", "South Africa", "Russia", "Lebanon",
	            "Argentina", "Egypt", "Saudi Arabia", "South Korea", "Indonesia");
	TableView <Flight> flightsTable=new TableView<>();
	TableView <Flight> statisticsTable=new TableView<>();
	
	public MainPage(TabPane tabs) {
		this.tabs=tabs;
		main=new VBox(10);
		main.setAlignment(Pos.CENTER);
		main.setPadding(new Insets(30));
		main.setStyle("-fx-background-color: rgb(253, 240, 213);");
		setMainPage();
		statisticsTable.setItems(allFlightslist);
	}
	
	public VBox getMainPage() {
		return main;
	}
	
	////////////////////// main page////////////////////////
	
	private void setMainPage() {
		
		HBox header=new HBox(10);
		header.setAlignment(Pos.CENTER_LEFT);
		//header.setPadding(new Insets(0));
		
		ImageView logo = new ImageView("logo.png");
		logo.setScaleX(1);
		logo.setScaleY(1);
		logo.setX(2);
		logo.setY(0);
		logo.setFitWidth(100);
		logo.setFitHeight(70);
		
		Label airportName=new Label("Gaza International Airport");
		airportName.setFont(Font.font("elephant", FontWeight.BOLD, 35));
		airportName.setStyle("-fx-text-fill: #c1121f;");
		                
		Label space = new Label("                                                                                                                                                              ");
		Button login=new Button("Browse");
		Button admin=new Button("Admin");
		login.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		login.setPrefWidth(150);
		login.setPrefHeight(50);
		login.setOnMouseEntered(e -> login.setTranslateY(login.getTranslateY() - 7));
		login.setOnMouseExited(e -> login.setTranslateY(login.getTranslateY() + 7));
		admin.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		admin.setPrefWidth(150);
		admin.setPrefHeight(50);
		admin.setOnMouseEntered(e -> admin.setTranslateY(admin.getTranslateY() - 7));
		admin.setOnMouseExited(e -> admin.setTranslateY(admin.getTranslateY() + 7));
		
		//============ set on actions====================
		admin.setOnAction(e->{
//			tabs.getTabs().add(adminPage());
//			int currentIndex = tabs.getSelectionModel().getSelectedIndex();
//          int nextIndex = (currentIndex + 1) % tabs.getTabs().size(); 
//          tabs.getSelectionModel().select(nextIndex);
		    loginPage();
		});
		login.setOnAction(e->{
			tabs.getTabs().add(new Tab("uuuuuuuuuuuu"));
			int currentIndex = tabs.getSelectionModel().getSelectedIndex();
            int nextIndex = (currentIndex + 1) % tabs.getTabs().size(); 
            tabs.getSelectionModel().select(nextIndex);
		});
		
		header.getChildren().addAll(logo,airportName,space,login,admin);
		
		Separator separator = new Separator();
		Text intro = new Text("Gaza International Airport, located in the southern part of the Gaza Strip, was once a symbol of hope and connectivity for the Palestinian people. Opened in 1998 with the support of international donors, the airport had the potential to become a key gateway for travel and trade in the region. However, it was severely damaged in 2001 during the Israeli-Palestinian conflict and further destroyed in 2002. Despite efforts to rebuild and calls for its reconstruction, the airport has remained non-operational due to ongoing political tensions, blockade restrictions, and conflict. Its closure has isolated Gaza from the rest of the world, limiting the mobility of its residents and hindering economic development. The Gaza airport remains a powerful symbol of the challenges faced by the Palestinian people and the broader geopolitical struggles in the region.");
        intro.setStyle("-fx-font-weight: bold;-fx-font-style: italic;-fx-font-size: 25px;-fx-fill: #003566;"); // Apply bold style
        TextFlow paragraph = new TextFlow(intro);
        paragraph.setStyle("-fx-padding: 0 70px 0 70px;");
        paragraph.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        
        Label l= new Label();
        
        ImageView p = new ImageView("p.jpg");
		p.setScaleX(1);
		p.setScaleY(1);
		p.setX(2);
		p.setY(0);
		p.setFitWidth(400);
		p.setFitHeight(300);
		
		ImageView pp = new ImageView("pp.jpg");
		pp.setScaleX(1);
		pp.setScaleY(1);
		pp.setX(2);
		pp.setY(0);
		pp.setFitWidth(400);
		pp.setFitHeight(300);
		
		ImageView ppp = new ImageView("ppp.jpg");
		ppp.setScaleX(1);
		ppp.setScaleY(1);
		ppp.setX(2);
		ppp.setY(0);
		ppp.setFitWidth(400);
		ppp.setFitHeight(300);
		
		HBox pics=new HBox(30);
		pics.setAlignment(Pos.CENTER);
		pics.getChildren().addAll(p,pp,ppp);
		
		
		main.getChildren().addAll(header,separator,paragraph,l,pics);
	}
	
	/////////////////// Admin login Stage///////////////////
	
	private void loginPage() {
		
		Stage stage = new Stage();

		VBox vbox=new VBox(20);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setStyle("-fx-background-color: rgb(253, 240, 213);");
		
		Label header=new Label("Login As Admin");
		header.setStyle("-fx-text-fill: rgb(253, 240, 213); -fx-font-weight: bold;");
		header.setFont(Font.font("elephant", 20));
		
		ImageView logo = new ImageView("logo.png");
		logo.setScaleX(1);
		logo.setScaleY(1);
		logo.setX(2);
		logo.setY(0);
		logo.setFitWidth(50);
		logo.setFitHeight(35);
		
		HBox hbox=new HBox(40);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(header);
		hbox.setStyle("-fx-background-color:#003566");
		
		String style="-fx-text-fill: #c1121f; -fx-font-weight: bold;-fx-font-size: 15px;";
		Label username = new Label("User Name :");
        TextField unamef=new TextField();
        Label password = new Label("Password :");
        PasswordField passwordf=new PasswordField();
        username.setStyle(style);
        password.setStyle(style);
        unamef.setStyle("-fx-border-color: #c1121f;");
        passwordf.setStyle("-fx-border-color: #c1121f;");
        GridPane gpane=new GridPane();
		gpane.add(username, 0, 0);
		gpane.add(password, 0, 1);
		gpane.add(unamef, 1, 0);
		gpane.add(passwordf, 1, 1);
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(10);
		gpane.setHgap(10);
		
		Button login=new Button("Login");
		login.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 15px;-fx-background-radius: 30; -fx-border-radius: 30;");
		login.setPrefWidth(70);
		login.setPrefHeight(30);
		login.setOnMouseEntered(e -> login.setTranslateY(login.getTranslateY() - 7));
		login.setOnMouseExited(e -> login.setTranslateY(login.getTranslateY() + 7));
		login.setOnAction(e->{
			if(!unamef.getText().isEmpty()&&!passwordf.getText().isEmpty()) {
				String un="amal.zaben";
				String pass="12345";
				if(unamef.getText().equalsIgnoreCase(un)&&passwordf.getText().equalsIgnoreCase(pass)) {
					//maintab.setContent(new VBox());
					tabs.getTabs().add(adminPage());
					int currentIndex = tabs.getSelectionModel().getSelectedIndex();
		            int nextIndex = (currentIndex + 1) % tabs.getTabs().size(); 
		            tabs.getSelectionModel().select(nextIndex);
					stage.close();
				}
				else
					getAlert("CAN NOT LOGIN :( MAKE SURE THE USERNAME AND PASSWORD YOU ENTERED ARE VALID !!");
			}
			else
				getAlert("CAN NOT LOGIN :( MAKE SURE THE USERNAME AND PASSWORD YOU ENTERED ARE VALID !!");
				
		});
        
		vbox.getChildren().addAll(hbox,gpane,login);
		BorderPane borderpane=new BorderPane();
		borderpane.setCenter(vbox);
		Scene scene=new Scene(borderpane,300,220); 
		stage.setTitle("Login As Admin");
		stage.getIcons().add(new Image("logo.png"));
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	/////////////////AdminPage//////////////////////////
	
	private Tab adminPage() {
		
		Tab tab=new Tab("https://GazaAirport/AdminPage");
		
		VBox vbox=new VBox(10);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(30));
		vbox.setStyle("-fx-background-color: rgb(253, 240, 213);");
		
		BorderPane pane=new BorderPane();
		pane.setCenter(flightsManagement());
		//pane.setAlignment(flightsManagement(), Pos.CENTER);
		
		HBox header=new HBox(10);
		header.setAlignment(Pos.CENTER_LEFT);
		//header.setPadding(new Insets(0));
		
		Label airportName=new Label("Gaza International Airport");
		airportName.setFont(Font.font("elephant", FontWeight.BOLD, 35));
		airportName.setStyle("-fx-text-fill: #c1121f;");
		
		ImageView logo = new ImageView("logo.png");
		logo.setScaleX(1);
		logo.setScaleY(1);
		logo.setX(2);
		logo.setY(0);
		logo.setFitWidth(100);
		logo.setFitHeight(70);
		
		ImageView undoimg = new ImageView("undo.png");
		ImageView redoimg = new ImageView("redo.png");
		redoimg.setFitWidth(50); 
		redoimg.setFitHeight(50); 
		redoimg.setPreserveRatio(true);
		undoimg.setFitWidth(50); 
		undoimg.setFitHeight(50); 
		undoimg.setPreserveRatio(true);
		
		String unrestyle="-fx-background-color: transparent;";
		Button undo=new Button("",undoimg);
		Button redo=new Button("",redoimg);
		undo.setStyle(unrestyle);
		redo.setStyle(unrestyle);
		undo.setPrefWidth(50);
		undo.setPrefHeight(50);
		undo.setOnMouseEntered(e -> undo.setTranslateY(undo.getTranslateY() - 7));
		undo.setOnMouseExited(e -> undo.setTranslateY(undo.getTranslateY() + 7));
		redo.setPrefWidth(50);
		redo.setPrefHeight(50);
		redo.setOnMouseEntered(e -> redo.setTranslateY(redo.getTranslateY() - 7));
		redo.setOnMouseExited(e -> redo.setTranslateY(redo.getTranslateY() + 7));
		
		Label flightsl=new Label("Flights");
		Label logFile=new Label("Hestory");
		Label passengersl=new Label("Passengers");
		Label statl=new Label("Statistics");
		flightsl.setFont(Font.font("elephant", FontWeight.BOLD, 25));
		flightsl.setStyle("-fx-text-fill: #003566;");
		logFile.setFont(Font.font("elephant", FontWeight.BOLD, 25));
		logFile.setStyle("-fx-text-fill: #c1121f;");
		passengersl.setFont(Font.font("elephant", FontWeight.BOLD, 25));
		passengersl.setStyle("-fx-text-fill: #c1121f;");
		statl.setFont(Font.font("elephant", FontWeight.BOLD, 25));
		statl.setStyle("-fx-text-fill: #c1121f;");
		Label space =new Label("                                                                   ");
		flightsl.setOnMouseEntered(e -> flightsl.setTranslateY(flightsl.getTranslateY() - 7));
		flightsl.setOnMouseExited(e -> flightsl.setTranslateY(flightsl.getTranslateY() + 7));
		passengersl.setOnMouseEntered(e -> passengersl.setTranslateY(passengersl.getTranslateY() - 7));
		passengersl.setOnMouseExited(e -> passengersl.setTranslateY(passengersl.getTranslateY() + 7));
		statl.setOnMouseEntered(e -> statl.setTranslateY(statl.getTranslateY() - 7));
		statl.setOnMouseExited(e -> statl.setTranslateY(statl.getTranslateY() + 7));
		logFile.setOnMouseEntered(e -> logFile.setTranslateY(logFile.getTranslateY() - 7));
		logFile.setOnMouseExited(e -> logFile.setTranslateY(logFile.getTranslateY() + 7));
		
		passengersl.setOnMouseClicked(e->{
			flightsl.setStyle("-fx-text-fill: #c1121f;");
			statl.setStyle("-fx-text-fill: #c1121f;");
			passengersl.setStyle("-fx-text-fill: #003566;");
			pane.setCenter(PassengersManagement(undo,redo));
		});
		flightsl.setOnMouseClicked(e->{
			passengersl.setStyle("-fx-text-fill: #c1121f;");
			statl.setStyle("-fx-text-fill: #c1121f;");
			flightsl.setStyle("-fx-text-fill: #003566;");
			pane.setCenter(flightsManagement());
		});
		statl.setOnMouseClicked(e->{
			flightsl.setStyle("-fx-text-fill: #c1121f;");
			passengersl.setStyle("-fx-text-fill: #c1121f;");
			statl.setStyle("-fx-text-fill: #003566;");
			pane.setCenter(statistics());
		});
		logFile.setOnMouseClicked(e->{
			try {
		        fileChooser = new FileChooser();
		        File file = fileChooser.showOpenDialog(new Stage()); 
		        if (file != null) {
		            PrintWriter output = new PrintWriter(file);
		            output.print(""); 

		            for (int i = 0; i < log.getSize(); i++) {
		                String s =(String) log.dequeue();
							output.println(s);
							log.enqueue(s);
		            }

		            output.close(); 
		            getAlert("PASSENGERS INFO WAS SAVED SUCCESSFULLY :)");
		        } else {
		            getAlert("NO FILE WAS CHOSEN, INFORMATION WAS NOT SAVED!!");
		        }

		    } catch (FileNotFoundException ex) {
		        getAlert("THE FILE YOU CHOSE IS NOT VALID OR NOT FOUND!!");
		    } catch (Exception ex) {
		        getAlert("AN ERROR OCCURRED: " + ex.getMessage());
		    }
		});
		header.getChildren().addAll(logo,airportName,space,logFile,statl,passengersl,flightsl,redo,undo);
		
		Separator separator = new Separator();
		
		
		
		vbox.getChildren().addAll(header,separator,pane);
		tab.setContent(vbox);
		return tab;
	}
	
	////////////////// Flights Management///////////////////////
	
	private HBox flightsManagement() {

		HBox fliBox=new HBox(10);
		
		VBox vbox=new VBox(20);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setOnMouseClicked(event -> {
		    // Prevent focus loss for VBox's children
		    event.consume();
		});
		
		String style="-fx-text-fill:#003566; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;";
		String stylef="-fx-min-width: 250px;-fx-min-height: 40px;-fx-border-color: #003566;-fx-font-size: 20px;";
		Button search=new Button("Search");
		TextField searchf=new TextField();
		Label id=new Label("Flight ID");
		Label idf=new Label("idNum");
		Label dest=new Label("Destination");
		ComboBox<String> destf = new ComboBox<>(destinations);
        destf.setEditable(true);
		//Button destb=new Button("",new ImageView("show.png"));
		Label date=new Label("Boarding in");
		DatePicker datef=new DatePicker();
		Label active=new Label("Active");
		CheckBox activef=new CheckBox();
		id.setStyle(style);
		idf.setUnderline(true);
		idf.setStyle("-fx-text-fill:#003566; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
		dest.setStyle(style);
		date.setStyle(style);
		active.setStyle(style);
		searchf.setStyle(stylef);
		destf.setStyle(stylef);
		datef.setStyle(stylef);
		//activef.setStyle("-fx-padding: 20;");
		search.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		search.setPrefWidth(150);
		search.setPrefHeight(50);
		search.setOnMouseEntered(e -> search.setTranslateY(search.getTranslateY() - 7));
		search.setOnMouseExited(e -> search.setTranslateY(search.getTranslateY() + 7));
		
		datef.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);

                        // Disable dates that are before today
                        if (date.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Optional: Highlight disabled dates
                        }
                    }
                };
            }
        });
		
		GridPane gpane=new GridPane();
		gpane.add(search, 0, 0);
		gpane.add(id, 0, 1);
		gpane.add(dest, 0, 2);
		gpane.add(date, 0, 3);
		gpane.add(active, 0, 4);
	//	gpane.add(date, 0, 5);
		gpane.add(searchf, 1, 0);
		gpane.add(idf, 1, 1);
		gpane.add(destf, 1, 2);
		gpane.add(datef, 1, 3);
		gpane.add(activef, 1, 4);
		//gpane.add(datep, 1, 5);
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(30);
		gpane.setHgap(20);
		
		ImageView nimg = new ImageView("left.png");
		nimg.setRotate(180);
		nimg.setFitWidth(50); 
		nimg.setFitHeight(50); 
		nimg.setPreserveRatio(true);
		ImageView pimg = new ImageView("left.png");
		pimg.setFitWidth(50); 
		pimg.setFitHeight(50); 
		pimg.setPreserveRatio(true);
		String unrestyle="-fx-background-color: transparent;";
		Button next=new Button("",nimg);
		Button prev=new Button("",pimg);
		next.setStyle(unrestyle);
		prev.setStyle(unrestyle);
		prev.setOnMouseEntered(e -> prev.setTranslateY(prev.getTranslateY() - 7));
		prev.setOnMouseExited(e -> prev.setTranslateY(prev.getTranslateY() + 7));
		next.setOnMouseEntered(e -> next.setTranslateY(next.getTranslateY() - 7));
		next.setOnMouseExited(e -> next.setTranslateY(next.getTranslateY() + 7));
		
		HBox np=new HBox(30);
		np.setAlignment(Pos.CENTER);
		np.getChildren().addAll(prev,next);
		
		Button update=new Button("Update");
		update.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		update.setPrefWidth(150);
		update.setPrefHeight(50);
		update.setOnMouseEntered(e -> update.setTranslateY(update.getTranslateY() - 7));
		update.setOnMouseExited(e -> update.setTranslateY(update.getTranslateY() + 7));
		Button remove=new Button("Remove");
		remove.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		remove.setPrefWidth(150);
		remove.setPrefHeight(50);
		remove.setOnMouseEntered(e -> remove.setTranslateY(remove.getTranslateY() - 7));
		remove.setOnMouseExited(e -> remove.setTranslateY(remove.getTranslateY() + 7));
		Button add=new Button("Add New Flight");
		add.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		add.setPrefWidth(300);
		add.setPrefHeight(50);
		add.setOnMouseEntered(e -> add.setTranslateY(add.getTranslateY() - 7));
		add.setOnMouseExited(e -> add.setTranslateY(add.getTranslateY() + 7));
//		Button showpassengers=new Button("Display Flight Passengers");
//		showpassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
//		showpassengers.setPrefWidth(300);
//		showpassengers.setPrefHeight(50);
//		showpassengers.setOnMouseEntered(e -> showpassengers.setTranslateY(showpassengers.getTranslateY() - 7));
//		showpassengers.setOnMouseExited(e -> showpassengers.setTranslateY(showpassengers.getTranslateY() + 7));
		
		HBox ur=new HBox(10);
		ur.setAlignment(Pos.CENTER);
		ur.getChildren().addAll(update,remove);
		
		Button improt=new Button("Import");
		improt.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		improt.setPrefWidth(150);
		improt.setPrefHeight(50);
		improt.setOnMouseEntered(e -> improt.setTranslateY(improt.getTranslateY() - 7));
		improt.setOnMouseExited(e -> improt.setTranslateY(improt.getTranslateY() + 7));
		Button export=new Button("Export");
		export.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		export.setPrefWidth(150);
		export.setPrefHeight(50);
		export.setOnMouseEntered(e -> export.setTranslateY(export.getTranslateY() - 7));
		export.setOnMouseExited(e -> export.setTranslateY(export.getTranslateY() + 7));
		
		HBox ie=new HBox(10);
		ie.setAlignment(Pos.CENTER);
		ie.getChildren().addAll(improt,export);
		
		//===================== SetOnActions==================
		add.setOnAction(e->{
			addFlightStage();
		});
		search.setOnAction(e->{
			if(!searchf.getText().isEmpty()) {
				updateAllFlightsOB();
				String searchwith=searchf.getText().charAt(0)+"";
				if(searchwith.matches("\\d+")) {
					try {
					tempFlight=flights.searchwithid(Integer.parseInt(searchf.getText()));
					Flight tempf=(Flight)tempFlight.getElement();
					idf.setText(tempf.getFid()+"");
					destf.setValue(tempf.getDestination());
					String[] d=tempf.getDate().split("-");
					datef.setValue(LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2])));
					activef.setSelected(tempf.isStatus());
					for (Flight f : allFlightslist) {
	                    if (f.getFid()==tempf.getFid()) {
	                    	allFlightslist.remove(f);
	                    	allFlightslist.add(0, f);
	                    	flightsTable.setRowFactory(tv -> new TableRow<>() {
	                            @Override
	                            protected void updateItem(Flight item, boolean empty) {
	                                super.updateItem(item, empty);
	                                if (item == null || empty) {
	                                    setStyle(""); 
	                                } else if (item.getFid()==tempf.getFid()) {
	                                    setStyle("-fx-background-color: rgb(250, 80, 80)\r\n"
	                                    		+ "; -fx-font-weight: bold;"); 
	                                } else {
	                                    setStyle(""); // Reset non-matching rows
	                                }
	                            }
	                    	});
	                    	flightsTable.refresh(); 
	                    	break;
				        }
					}
					}catch(NullPointerException u) {
						getAlert("THE FLIGHT ID YOU ARE SEARCHING FOR DOES NOT EXIST !!");
					}
				}
				else {
					try {
						DLL des=flights.searchWithDestination(searchf.getText());
						DNode d=des.getFirst();
						ObservableList<Flight> deslist = FXCollections.observableArrayList();
						System.out.println(des.getCount());
						for(int i=0;i<des.getCount();i++) {
							deslist.add((Flight)d.getElement());
							d=d.getNext();
						}
						flightsTable.setItems(deslist);
						flightsTable.refresh();
						}catch(NullPointerException u) {
							getAlert("THE DISTANCE YOU ARE SEARCHING FOR DOES NOT EXIST !!");
						}
				}
					
			}
		});
		next.setOnAction(e->{
			if(tempFlight!=null) {
				tempFlight=tempFlight.getNext();
				Flight tempf=(Flight)tempFlight.getElement();
				searchf.setText(tempf.getFid()+"");
				idf.setText(tempf.getFid()+"");
				destf.setValue(tempf.getDestination());
				String[] d=tempf.getDate().split("-");
				datef.setValue(LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2])));
				if(tempf.isStatus())
					activef.setSelected(true);
				else
					activef.setSelected(false);
			}
		});
		prev.setOnAction(e->{
			if(tempFlight!=null) {
				tempFlight=tempFlight.getPrev();
				Flight tempf=(Flight)tempFlight.getElement();
				searchf.setText(tempf.getFid()+"");
				idf.setText(tempf.getFid()+"");
				destf.setValue(tempf.getDestination());
				String[] d=tempf.getDate().split("-");
				datef.setValue(LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2])));
				if(tempf.isStatus())
					activef.setSelected(true);
				else
					activef.setSelected(false);
			}
		});
		remove.setOnAction(e->{
			Alert alert=new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText("YOU ARE TRYING TO REMOVE THIS FLIGHT FROM THE SYSTEM , PRESS CONFIRM TO PROCEED !");
			alert.setHeaderText("Please Pay Attention !!");
			ButtonType confirm= new ButtonType("confirm");
			alert.getDialogPane().getButtonTypes().clear();
			alert.getDialogPane().getButtonTypes().add(confirm);
			alert.setResultConverter(dialogButton -> {
	            if (dialogButton == confirm) {
	    			try {
	    				Flight f=(Flight)tempFlight.getElement();
	    				tempFlight=tempFlight.getNext();
	    				flights.remove(f);
	    				Flight tempf=(Flight)tempFlight.getElement();
	    				searchf.setText(tempf.getFid()+"");
	    				idf.setText(tempf.getFid()+"");
	    				destf.setValue(tempf.getDestination());
	    				String[] d=tempf.getDate().split("-");
	    				datef.setValue(LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2])));
	    				if(tempf.isStatus())
	    					activef.setSelected(true);
	    				else
	    					activef.setSelected(false);
	    			}catch(NullPointerException u) {
	    				tempFlight=new DNode(new Object());
	    				
	    			}
	    			updateAllFlightsOB();
	            }
			return null;
		    });
			alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			alert.showAndWait();
		});

		update.setOnAction(e->{
			Alert alert=new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText("YOU ARE TRYING TO UPDATE THIS FLIGHT INFORMATION , PRESS CONFIRM TO PROCEED !");
			alert.setHeaderText("Please Pay Attention !!");
			ButtonType confirm= new ButtonType("confirm");
			alert.getDialogPane().getButtonTypes().clear();
			alert.getDialogPane().getButtonTypes().add(confirm);
			alert.setResultConverter(dialogButton -> {
	            if (dialogButton == confirm) {
	    			Flight f=(Flight)tempFlight.getElement();
	    			if(destf.getSelectionModel().getSelectedItem()!=null&&datef.getValue()!=null) {
	    				f.setDate(datef.getValue()+"");
	    				f.setDestination(destf.getSelectionModel().getSelectedItem());
	    				f.setStatus(activef.isSelected());
	    				updateAllFlightsOB();
	    			}
	    			else
	    				getAlert("NO EMPTY FIELDS ARE ALLAWED, PLEASE FILL ALL FLIGHT INFO THEN PRESS THE BUTTON!!");

	            }
			return null;
		    });
			alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			alert.showAndWait();
		});
		
		improt.setOnAction(e->{
			fileChooser=new FileChooser();
			//fileChooser.setInitialDirectory(new File("C:\\Users\\HP\\Desktop"));
			File file =fileChooser.showOpenDialog(new Stage());
			try {
				Scanner scanner=new Scanner(file);
				while(scanner.hasNext()){
					String line=scanner.nextLine();
					String [] atributes =line.split(",");
					boolean y=false;
					if(atributes[2].equalsIgnoreCase("Active"))
						y=true;
					if(atributes.length==3) {
						Flight f=new Flight(atributes[0],atributes[1],y);
						flights.addLast(f);
						if(y==true)
							activeFlightslist.add(f);
						else
							inactiveFlightslist.add(f);
					}
					else
						throw new  IllegalArgumentException();
				}
				scanner.close();
			}
			catch(FileNotFoundException m) {
				getAlert("The System Is Not Able To Read File !!");
			}catch(NullPointerException h) {
				
			}catch(IllegalArgumentException o) {
				getAlert("File has Wrong Data Format");
			}
			updateAllFlightsOB();
		});
		
		export.setOnAction(e->{
			try {
				fileChooser= new FileChooser();
			    File file =fileChooser.showOpenDialog(new Stage());
			    PrintWriter output = new PrintWriter(file);
			    output.print("");//clear file content
			    for(int i=0;i<allFlightslist.size();i++) {
			    	Flight s=allFlightslist.get(i);
			    	String ac="Inactive";
			    	if(s.isStatus())
			    		ac="Active";
			    	    output.println(s.getFid()+""+","+s.getDestination()+","+s.getDate()+""+","+ac);
			    }
			    output.close();
			    getAlert("FLIGHTS INFO WAS SAVED SUCCESSFULY :)");	
			}catch(NullPointerException i) {
				//getAlert("NO FILE WAS CHOOSEN , INFORMATION WAS NOT SAVED !!");
			}catch(FileNotFoundException u) {
				getAlert("THE FILE YOU CHOOSED IS NOT VALID OR NOT FOUND !!");
			}
		});
		Separator seprator = new Separator();

		flightsTable.setStyle("-fx-border-color:#003566;-fx-border-Width:3;-fx-text-fill: white;-fx-font-weight: bold;-fx-min-width: 800px; -fx-min-height: 500px;");
		TableColumn<Flight, Integer> idColumn = new TableColumn<>("Flighgt ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("fid"));
		idColumn.setMinWidth(250); 
		idColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, String> destColumn = new TableColumn<>("Destenation");
		destColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
		destColumn.setMinWidth(250); 
		destColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, String> dateColumn = new TableColumn<>("Boarding Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateColumn.setMinWidth(250); 
		dateColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, Boolean> statColumn = new TableColumn<>("Active");
		statColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		statColumn.setMinWidth(250); 
		statColumn.setStyle("-fx-alignment: CENTER;");
		flightsTable.getColumns().addAll(idColumn,destColumn,dateColumn,statColumn);
		flightsTable.setItems(allFlightslist);
		
		Button all=new Button("All Flights");
		all.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		all.setPrefWidth(150);
		all.setPrefHeight(50);
		all.setOnMouseEntered(e -> all.setTranslateY(all.getTranslateY() - 7));
		all.setOnMouseExited(e -> all.setTranslateY(all.getTranslateY() + 7));
		Button activeFlights=new Button("Active");
		activeFlights.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		activeFlights.setPrefWidth(150);
		activeFlights.setPrefHeight(50);
		activeFlights.setOnMouseEntered(e -> activeFlights.setTranslateY(activeFlights.getTranslateY() - 7));
		activeFlights.setOnMouseExited(e -> activeFlights.setTranslateY(activeFlights.getTranslateY() + 7));
		Button inactiveFlights=new Button("Inactive");
		inactiveFlights.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		inactiveFlights.setPrefWidth(150);
		inactiveFlights.setPrefHeight(50);
		inactiveFlights.setOnMouseEntered(e -> inactiveFlights.setTranslateY(inactiveFlights.getTranslateY() - 7));
		inactiveFlights.setOnMouseExited(e -> inactiveFlights.setTranslateY(inactiveFlights.getTranslateY() + 7));
		
		all.setOnAction(e->{
			all.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			activeFlights.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			inactiveFlights.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			flightsTable.setRowFactory(tv -> new TableRow<>() {
                @Override
                protected void updateItem(Flight item, boolean empty) {
                    super.updateItem(item, empty);
                    setStyle(""); // Reset all rows to their original style
                }
            });
			updateAllFlightsOB();
			flightsTable.setItems(allFlightslist);
			flightsTable.refresh();
		});
		activeFlights.setOnAction(e->{
			activeFlights.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			inactiveFlights.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			all.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			flightsTable.setItems(activeFlightslist);
			flightsTable.refresh();
		});
		inactiveFlights.setOnAction(e->{
			inactiveFlights.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			activeFlights.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			all.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			flightsTable.setItems(inactiveFlightslist);
			flightsTable.refresh();
		});
		
		flightsTable.setOnMouseClicked(event -> {
	        if (event.getClickCount() == 2) {
	            Flight selectedF = flightsTable.getSelectionModel().getSelectedItem();
	            if (selectedF != null) {
	                tempFlight=flights.searchwithid(selectedF.getFid());
	                searchf.setText(((Flight)tempFlight.getElement()).getFid()+"");
	                Flight tempf=(Flight)tempFlight.getElement();
					searchf.setText(tempf.getFid()+"");
					idf.setText(tempf.getFid()+"");
					destf.setValue(tempf.getDestination());
					String[] d=tempf.getDate().split("-");
					datef.setValue(LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2])));
					if(tempf.isStatus())
						activef.setSelected(true);
					else
						activef.setSelected(false);
	            }
	        }
	    });
		
		
		
		HBox aai=new HBox(20);
		aai.getChildren().addAll(all,activeFlights,inactiveFlights);
		
		VBox tb=new VBox(20);
		tb.setAlignment(Pos.TOP_LEFT);
		tb.setPadding(new Insets(10));
		tb.getChildren().addAll(flightsTable,aai);
		
		seprator.setOrientation(javafx.geometry.Orientation.VERTICAL);
		//seprator.setStyle("-fx-background-color: #003566;");
		
		vbox.setMinWidth(450);
		vbox.getChildren().addAll(gpane,np,ie,ur,add);
		fliBox.getChildren().addAll(vbox,seprator,tb);
		return fliBox;
	}
	//////////////// Manage Passengers/////////////////////
	private HBox PassengersManagement(Button undo,Button redo) {
		tempFlightForPassengers=flights.getFirst();
		updatePassengersInFidList();
		HBox fliBox=new HBox(10);
		
		VBox vbox=new VBox(20);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER);
		vbox.setOnMouseClicked(event -> {
		    // Prevent focus loss for VBox's children
		    event.consume();
		});
		
		String showstyle="-fx-background-color: transparent;";
		ImageView fidimg = new ImageView("show.png");
		fidimg.setFitWidth(50); 
		fidimg.setFitHeight(50); 
		fidimg.setPreserveRatio(true);
		
		String style="-fx-text-fill:#003566; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;";
		String stylef="-fx-min-width: 250px;-fx-min-height: 40px;-fx-border-color: #003566;-fx-font-size: 20px;";
		Button search=new Button("Search");
		TextField searchf=new TextField();
		Label id=new Label("Passenger ID");
		Label idf=new Label("idNum");
		Label name=new Label("Full Name");
		TextField namef=new TextField();
		Button fidb=new Button("",fidimg);
		Label fid=new Label("Flight ID");
		//TextField fidf=new TextField();
		Label vip=new Label("VIP");
		CheckBox vipf=new CheckBox();
		HBox h=new HBox();
		ComboBox<Flight> fidf = new ComboBox<>();
		fidf.setEditable(true);
		fidf.setItems(allFlightslist);
		TextField searchforfightf=new TextField();
		
		
		id.setStyle(style);
		idf.setUnderline(true);
		idf.setStyle("-fx-text-fill:#003566; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
		name.setStyle(style);
		fidf.setStyle(stylef);
		vip.setStyle(style);
		searchf.setStyle(stylef);
		namef.setStyle(stylef);
		searchforfightf.setStyle(stylef);
		fid.setStyle(style);
		fidb.setStyle(showstyle);
		search.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		search.setPrefWidth(150);
		search.setPrefHeight(50);
		search.setOnMouseEntered(e -> search.setTranslateY(search.getTranslateY() - 7));
		search.setOnMouseExited(e -> search.setTranslateY(search.getTranslateY() + 7));
		fidb.setOnMouseEntered(e -> fidb.setTranslateY(fidb.getTranslateY() - 7));
		fidb.setOnMouseExited(e -> fidb.setTranslateY(fidb.getTranslateY() + 7));
		
		//h.getChildren().addAll(fidf,fidb);
		GridPane gpane=new GridPane();
		gpane.add(search, 0, 0);
		gpane.add(id, 0, 1);
		gpane.add(name, 0, 2);
		gpane.add(fid, 0, 3);
		gpane.add(vip, 0, 4);
	//	gpane.add(date, 0, 5);
		gpane.add(searchf, 1, 0);
		gpane.add(idf, 1, 1);
		gpane.add(namef, 1, 2);
		gpane.add(fidf, 1, 3);
		gpane.add(vipf, 1, 4);
		//gpane.add(datep, 1, 5);
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(30);
		gpane.setHgap(20);
		
		
		Button update=new Button("Update");
		update.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		update.setPrefWidth(150);
		update.setPrefHeight(50);
		update.setOnMouseEntered(e -> update.setTranslateY(update.getTranslateY() - 7));
		update.setOnMouseExited(e -> update.setTranslateY(update.getTranslateY() + 7));
		Button cancel=new Button("Cancel");
		cancel.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		cancel.setPrefWidth(150);
		cancel.setPrefHeight(50);
		cancel.setOnMouseEntered(e -> cancel.setTranslateY(cancel.getTranslateY() - 7));
		cancel.setOnMouseExited(e -> cancel.setTranslateY(cancel.getTranslateY() + 7));
		Button remove=new Button("Remove");
		remove.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		remove.setPrefWidth(150);
		remove.setPrefHeight(50);
		remove.setOnMouseEntered(e -> remove.setTranslateY(remove.getTranslateY() - 7));
		remove.setOnMouseExited(e -> remove.setTranslateY(remove.getTranslateY() + 7));
		Button add=new Button("Add New Passenger");
		add.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		add.setPrefWidth(300);
		add.setPrefHeight(50);
		add.setOnMouseEntered(e -> add.setTranslateY(add.getTranslateY() - 7));
		add.setOnMouseExited(e -> add.setTranslateY(add.getTranslateY() + 7));
		Button improt=new Button("Import");
		improt.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		improt.setPrefWidth(150);
		improt.setPrefHeight(50);
		improt.setOnMouseEntered(e -> improt.setTranslateY(improt.getTranslateY() - 7));
		improt.setOnMouseExited(e -> improt.setTranslateY(improt.getTranslateY() + 7));
		Button checkin=new Button("Check In");
		checkin.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		checkin.setPrefWidth(150);
		checkin.setPrefHeight(50);
		checkin.setOnMouseEntered(e -> checkin.setTranslateY(checkin.getTranslateY() - 7));
		checkin.setOnMouseExited(e -> checkin.setTranslateY(checkin.getTranslateY() + 7));
		Button export=new Button("Export");
		export.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		export.setPrefWidth(150);
		export.setPrefHeight(50);
		export.setOnMouseEntered(e -> export.setTranslateY(export.getTranslateY() - 7));
		export.setOnMouseExited(e -> export.setTranslateY(export.getTranslateY() + 7));
		Button border=new Button("Board");
		border.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		border.setPrefWidth(150);
		border.setPrefHeight(50);
		border.setOnMouseEntered(e -> border.setTranslateY(border.getTranslateY() - 7));
		border.setOnMouseExited(e -> border.setTranslateY(border.getTranslateY() + 7));
		Button searchforflight=new Button("Search For Flight");
		searchforflight.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		searchforflight.setPrefWidth(300);
		searchforflight.setPrefHeight(50);
		searchforflight.setOnMouseEntered(e -> searchforflight.setTranslateY(searchforflight.getTranslateY() - 7));
		searchforflight.setOnMouseExited(e -> searchforflight.setTranslateY(searchforflight.getTranslateY() + 7));
		
		Separator seprator = new Separator();
		seprator.setOrientation(javafx.geometry.Orientation.VERTICAL);
		//seprator.setStyle("-fx-background-color: #003566;");
		
		//================ table View==============================
		passengersinFidTable.setStyle("-fx-border-color:#003566;-fx-border-Width:3;-fx-text-fill: white;-fx-font-weight: bold;-fx-min-width: 750px; -fx-min-height: 400px;");
		TableColumn<Passenger, Integer> idColumn = new TableColumn<>("Passenger ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
		idColumn.setMinWidth(230); 
		idColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Passenger, String> nameColumn = new TableColumn<>("Full Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
		nameColumn.setMinWidth(230); 
		nameColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Passenger, Integer> fidColumn = new TableColumn<>("Flight ID");
		fidColumn.setCellValueFactory(new PropertyValueFactory<>("flightid"));
		fidColumn.setMinWidth(230); 
		fidColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Passenger, Boolean> statColumn = new TableColumn<>("VIP");
		statColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		statColumn.setMinWidth(230); 
		statColumn.setStyle("-fx-alignment: CENTER;");
		passengersinFidTable.getColumns().addAll(idColumn,nameColumn,fidColumn,statColumn);
		passengersinFidTable.setItems(passengersinFidList);
		
		ImageView nimg = new ImageView("left.png");
		nimg.setRotate(180);
		nimg.setFitWidth(50); 
		nimg.setFitHeight(50); 
		nimg.setPreserveRatio(true);
		ImageView pimg = new ImageView("left.png");
		pimg.setFitWidth(50); 
		pimg.setFitHeight(50); 
		pimg.setPreserveRatio(true);
		String unrestyle="-fx-background-color: transparent;";
		Button next=new Button("",nimg);
		Button prev=new Button("",pimg);
		next.setStyle(unrestyle);
		prev.setStyle(unrestyle);
		prev.setOnMouseEntered(e -> prev.setTranslateY(prev.getTranslateY() - 7));
		prev.setOnMouseExited(e -> prev.setTranslateY(prev.getTranslateY() + 7));
		next.setOnMouseEntered(e -> next.setTranslateY(next.getTranslateY() - 7));
		next.setOnMouseExited(e -> next.setTranslateY(next.getTranslateY() + 7));
		
		Label chekedinl=new Label("Checked In");
		Label boardedl=new Label("Boarded");
		Label canceledl=new Label("Canceled");
		chekedinl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
		boardedl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
		canceledl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
		
		
		Label passingersinflightid=new Label("fidNum");
		if(tempFlightForPassengers!=null) {
			passingersinflightid.setText(((Flight)tempFlightForPassengers.getElement()).getFid()+"");
		}
		passingersinflightid.setUnderline(true);
		passingersinflightid.setStyle("-fx-text-fill:#003566; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
		
		next.setOnAction(e->{
			tempFlightForPassengers=tempFlightForPassengers.getNext();
			updatePassengersInFidList();
			passingersinflightid.setText(((Flight)tempFlightForPassengers.getElement()).getFid()+"");
		});
		
		prev.setOnAction(e->{
			tempFlightForPassengers=tempFlightForPassengers.getPrev();
			updatePassengersInFidList();
			passingersinflightid.setText(((Flight)tempFlightForPassengers.getElement()).getFid()+"");
		});
		
		
		HBox np=new HBox(30);
		np.setAlignment(Pos.CENTER);
		np.getChildren().addAll(prev,passingersinflightid,next);
		
		Button all=new Button("All Passengers");
		all.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		all.setPrefWidth(200);
		all.setPrefHeight(50);
		all.setOnMouseEntered(e -> all.setTranslateY(all.getTranslateY() - 7));
		all.setOnMouseExited(e -> all.setTranslateY(all.getTranslateY() + 7));
		Button vipPassengers=new Button("VIP");
		vipPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		vipPassengers.setPrefWidth(150);
		vipPassengers.setPrefHeight(50);
		vipPassengers.setOnMouseEntered(e -> vipPassengers.setTranslateY(vipPassengers.getTranslateY() - 7));
		vipPassengers.setOnMouseExited(e -> vipPassengers.setTranslateY(vipPassengers.getTranslateY() + 7));
		Button regPassengers=new Button("Regular");
		regPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		regPassengers.setPrefWidth(150);
		regPassengers.setPrefHeight(50);
		regPassengers.setOnMouseEntered(e -> regPassengers.setTranslateY(regPassengers.getTranslateY() - 7));
		regPassengers.setOnMouseExited(e -> regPassengers.setTranslateY(regPassengers.getTranslateY() + 7));
		Button cancelledPassengers=new Button("Cancelled");
		cancelledPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		cancelledPassengers.setPrefWidth(150);
		cancelledPassengers.setPrefHeight(50);
		cancelledPassengers.setOnMouseEntered(e -> cancelledPassengers.setTranslateY(cancelledPassengers.getTranslateY() - 7));
		cancelledPassengers.setOnMouseExited(e -> cancelledPassengers.setTranslateY(cancelledPassengers.getTranslateY() + 7));
		Button bordededPassengers=new Button("Borded");
		bordededPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		bordededPassengers.setPrefWidth(150);
		bordededPassengers.setPrefHeight(50);
		bordededPassengers.setOnMouseEntered(e -> bordededPassengers.setTranslateY(bordededPassengers.getTranslateY() - 7));
		bordededPassengers.setOnMouseExited(e -> bordededPassengers.setTranslateY(bordededPassengers.getTranslateY() + 7));
		//=========set on actions=====================
		all.setOnAction(e->{
			bordededPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			all.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			cancelledPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			vipPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			regPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			passengersinFidTable.setItems(passengersinFidList);
			passengersinFidTable.refresh();
		});
		vipPassengers.setOnAction(e->{
			bordededPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			vipPassengers.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			cancelledPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			regPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			all.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			passengersinFidTable.setItems(vippassengersinFidList);
			passengersinFidTable.refresh();
		});
		regPassengers.setOnAction(e->{
			bordededPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			regPassengers.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			cancelledPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			vipPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			all.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			passengersinFidTable.setItems(regpassengersinFidList);
			passengersinFidTable.refresh();
		});
		cancelledPassengers.setOnAction(e->{
			bordededPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			regPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			cancelledPassengers.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			vipPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			all.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			passengersinFidTable.setItems(canceledpassengersinFidList);
			passengersinFidTable.refresh();
		});
		bordededPassengers.setOnAction(e->{
			cancelledPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			regPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			bordededPassengers.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			vipPassengers.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			all.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
			passengersinFidTable.setItems(bordedpassengersinFidList);
			passengersinFidTable.refresh();
		});
		add.setOnAction(e->{
			addPassengerStage();
			updatePassengersInFidList();
		});
		remove.setOnAction(e->{
			Passenger p=flights.searchforPassenger(Integer.parseInt(idf.getText()));
			Alert alert=new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText("YOU ARE TRYING TO REMOVE THIS PASSENGER INFORMATION , PRESS CONFIRM TO PROCEED !");
			alert.setHeaderText("Please Pay Attention !!");
			ButtonType confirm= new ButtonType("confirm");
			alert.getDialogPane().getButtonTypes().clear();
			alert.getDialogPane().getButtonTypes().add(confirm);
			alert.setResultConverter(dialogButton -> {
	            if (dialogButton == confirm) {
	            	int pfid=Integer.parseInt(fidf.getSelectionModel().getSelectedItem()+"");
	            	((Flight)flights.searchwithid(p.getFlightid()).getElement()).getAllPasseners().remove(p);
	            	if(p.isCheckedin()) {
		            	if(p.isStatus()) {
	    					((Flight)flights.searchwithid(p.getFlightid()).getElement()).getVIPpassengers().remove(p);
						}
						else {
							((Flight)flights.searchwithid(p.getFlightid()).getElement()).getRegularPassengers().remove(p);
						}
		            	if(p.isBoarded())
		            		((Flight)flights.searchwithid(p.getFlightid()).getElement()).getBordedPassengers().remove(p);
	            	}
	            	if(p.isCanceled())
	            		((Flight)flights.searchwithid(p.getFlightid()).getElement()).getCanceledPassengers().remove(p);
	            	searchf.setText("");
	            	namef.setText("");
	            	fidf.setValue(null);
	            	vipf.setSelected(false);
	            	idf.setText("IDnum");
	            	chekedinl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
	        		boardedl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
	        		canceledl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
	    			updatePassengersInFidList();
	            }
			return null;
		    });
			alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			alert.showAndWait();
	    });
		checkin.setOnAction(e->{
			
			Passenger p=flights.searchforPassenger(Integer.parseInt(searchf.getText()));
			Flight f=(Flight)flights.searchwithid(p.getFlightid()).getElement();
			if(f.isStatus()) {
			Alert alert=new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText("YOU ARE CHANGING THIS PASSENGER STATE TO CHECKED IN , PRESS CONFIRM TO PROCEED !");
			alert.setHeaderText("Please Pay Attention !!");
			ButtonType confirm= new ButtonType("confirm");
			alert.getDialogPane().getButtonTypes().clear();
			alert.getDialogPane().getButtonTypes().add(confirm);
			alert.setResultConverter(dialogButton -> {
	            if (dialogButton == confirm) {
					if(p.isStatus()) 
						f.getVIPpassengers().enqueue(p);
					else
						f.getRegularPassengers().enqueue(p);
					chekedinl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
					p.setCheckedin(true);
					f.getUndo().push("checkin,"+p.getFlightid()+","+p.getPid());
					if(p.isCanceled()) {
						p.setCanceled(false);
						f.getCanceledPassengers().remove(p);
						canceledl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
					}
					updatePassengersInFidList();
					LocalTime currentTime = LocalTime.now();
	    			String g=dp.getValue()+"|"+currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"|"+"Check-In"+"|"+p.getPname()+"|"+p.getPid()+"|"+"Checked In "+" "+p.getPname()+" from flight "+p.getFlightid();
	    			log.enqueue(g);
					}
			return null;
			
			});
			alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			alert.showAndWait();
			}else {
				getAlert("THE FLIGHT IS INACTIVE !!");
			}
		});
		
		update.setOnAction(e->{
			Passenger p=flights.searchforPassenger(Integer.parseInt(idf.getText()));
			Alert alert=new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText("YOU ARE TRYING TO UPDATE THIS PASSENGER INFORMATION , PRESS CONFIRM TO PROCEED !");
			alert.setHeaderText("Please Pay Attention !!");
			ButtonType confirm= new ButtonType("confirm");
			alert.getDialogPane().getButtonTypes().clear();
			alert.getDialogPane().getButtonTypes().add(confirm);
			alert.setResultConverter(dialogButton -> {
	            if (dialogButton == confirm) {
	    			if(!namef.getText().isEmpty()) {
	    				p.setPname(namef.getText());
	    				int pfid=Integer.parseInt(fidf.getSelectionModel().getSelectedItem()+"");
	    				boolean isvip=vipf.isSelected();
	    				if (fidf.getValue() != null&&p.getFlightid()!=pfid) {
		    					Flight f=((Flight)flights.searchwithid(p.getFlightid()).getElement());
		    					f.getAllPasseners().remove(p);
		    					if(p.isCheckedin()) {
		    						if(p.isStatus())
		    							f.getVIPpassengers().remove(p);
		    						else
		    							f.getRegularPassengers().remove(p);
		    						if(p.isBoarded())
		    							f.getBordedPassengers().remove(p);
		    					}
		    					if(p.isCanceled())
	    							f.getCanceledPassengers().remove(p);
		    					 p.setFlightid(pfid);
		    					Flight s= ((Flight)flights.searchwithid(p.getFlightid()).getElement());
		    					 if(p.isStatus())
		    						 s.getAllPasseners().addFirst(p);
		    					 else
		    						 s.getAllPasseners().addLast(p);
		    					 if(p.isCheckedin()) {
			    						if(p.isStatus()) {
			    							s.getVIPpassengers().enqueue(p);
			    							s.getAllPasseners().addFirst(p);
			    						}
			    						else {
			    							s.getRegularPassengers().enqueue(p);
			    							s.getAllPasseners().addLast(p);
			    						}
			    						if(p.isBoarded()) {
			    							if(p.isStatus())
				    							s.getBordedPassengers().addFirst(p);
				    						else
				    							s.getBordedPassengers().addLast(p);
			    						}	
			    					}
			    					if(p.isCanceled())
		    							s.getCanceledPassengers().addLast(p);
	    					}
	    					
    				if(p.isStatus()!=isvip) {
    					if(p.isStatus()) {
	    					Flight f=((Flight)flights.searchwithid(p.getFlightid()).getElement());
	    					f.getAllPasseners().remove(p);
	    					if(p.isCheckedin()) {
	    						f.getVIPpassengers().remove(p);		
	    					}
	    					 p.setStatus(false);
	    					 Flight s=((Flight)flights.searchwithid(p.getFlightid()).getElement());
	    					 s.getAllPasseners().addLast(p);
	    					 if(p.isCheckedin()) {
	    							s.getRegularPassengers().enqueue(p);
		    					}
    					}
    					else {
    						Flight f=((Flight)flights.searchwithid(p.getFlightid()).getElement());
	    					f.getAllPasseners().remove(p);
	    					if(p.isCheckedin()) {
	    						f.getRegularPassengers().remove(p);		
	    					}
	    					 p.setStatus(true);
	    					 Flight s=((Flight)flights.searchwithid(p.getFlightid()).getElement());
	    					 s.getAllPasseners().addFirst(p);
	    					 if(p.isCheckedin()) {
	    							s.getVIPpassengers().enqueue(p);
		    					}
    					}
    				}
	    				updatePassengersInFidList();
	    			}
	    			else
	    				getAlert("NO EMPTY FIELDS ARE ALLAWED, PLEASE FILL ALL PASSENGERS INFO THEN PRESS THE BUTTON!!");

	            }
			return null;
		    });
			alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			alert.showAndWait();
		});
		search.setOnAction(e->{
			if(!searchf.getText().isEmpty()) {
				Passenger p=flights.searchforPassenger(Integer.parseInt(searchf.getText()));
				chekedinl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
        		boardedl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
                canceledl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
				 if(p!=null) {
					 if(p.isCheckedin())
							chekedinl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
						if(p.isBoarded())
							boardedl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
						if(p.isCanceled())
							canceledl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
		                searchf.setText(p.getPid()+"");
		                namef.setText(p.getPname());
						idf.setText(p.getPid()+"");
						fidf.setValue((Flight)flights.searchwithid(p.getFlightid()).getElement());
						if(p.isStatus())
							vipf.setSelected(true);
						else
							vipf.setSelected(false);
						tempFlightForPassengers=flights.searchwithid(p.getFlightid());
						updatePassengersInFidList();
						passingersinflightid.setText(p.getFlightid()+"");
	             }
				 else
					 getAlert("THE PASSENGER YOU ARE LOOKING FOR DOESNOT EXIST!!");
			}
		});
		
		improt.setOnAction(e->{
			if(flights.getCount()!=0) {
			fileChooser=new FileChooser();
			//fileChooser.setInitialDirectory(new File("C:\\Users\\HP\\Desktop"));
			File file =fileChooser.showOpenDialog(new Stage());
			try {
				//int i=1;
				Scanner scanner=new Scanner(file);
				while(scanner.hasNext()){
					String line=scanner.nextLine();
					String [] atributes =line.split(",");
					boolean y=false;
					if(atributes[2].equalsIgnoreCase("VIP"))
						y=true;
					if(atributes.length==3) {
						Passenger p=new Passenger(atributes[0],Integer.parseInt(atributes[1]),y);
						Flight f=(Flight)(flights.searchwithid(Integer.parseInt(atributes[1])).getElement());
						if(y==true)
							f.getAllPasseners().addFirst(p);
						else
							f.getAllPasseners().addLast(p);
					}
					else
						throw new  IllegalArgumentException();
				}
				scanner.close();
			}
			catch(FileNotFoundException m) {
				getAlert("The System Is Not Able To Read File !!");
			}catch(NullPointerException k) {
				
			}catch(IllegalArgumentException o) {
				getAlert("File has Wrong Data Format");
			}
			updatePassengersInFidList();
			}
			else
				getAlert("NO FLIGHTS I SYSTEM , PLEASE INSERT FLIGHTS THEN ADD PASSENGERS!!");
		});

		export.setOnAction(e -> {
		    try {
		        fileChooser = new FileChooser();
		        File file = fileChooser.showOpenDialog(new Stage()); 
		        if (file != null) {
		            PrintWriter output = new PrintWriter(file);
		            output.print(""); 

		            for (int i = 0; i < allFlightslist.size(); i++) {
		                Flight s = allFlightslist.get(i);
		                SLL allpins=s.getAllPasseners();
		                SNode d=allpins.getFirst();
						for(int j=0;j<allpins.getCount();j++) {
							Passenger p=(Passenger)d.getElement();
							String a="VIP";
							if(!p.isStatus())
								a="Regular";
							output.println(p.getPid() + "," + p.getPname() + "," + p.getFlightid() + "," + a);
							d=d.getNext();
							}
		            }

		            output.close(); 
		            getAlert("PASSENGERS INFO WAS SAVED SUCCESSFULLY :)");
		        } else {
		            getAlert("NO FILE WAS CHOSEN, INFORMATION WAS NOT SAVED!!");
		        }

		    } catch (FileNotFoundException ex) {
		        getAlert("THE FILE YOU CHOSE IS NOT VALID OR NOT FOUND!!");
		    } catch (Exception ex) {
		        getAlert("AN ERROR OCCURRED: " + ex.getMessage());
		    }
		});
		cancel.setOnAction(e->{
			Alert alert=new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText("YOU ARE TRYING TO CANCEL THIS PASSENGER FROM FLIGHT , PRESS CONFIRM TO PROCEED !");
			alert.setHeaderText("Please Pay Attention !!");
			ButtonType confirm= new ButtonType("confirm");
			alert.getDialogPane().getButtonTypes().clear();
			alert.getDialogPane().getButtonTypes().add(confirm);
			alert.setResultConverter(dialogButton -> {
	            if (dialogButton == confirm) {
	            	if(!searchf.getText().isEmpty()) {
	    				Passenger p=flights.searchforPassenger(Integer.parseInt(searchf.getText()));
	    				 if(p!=null) {
	    		                Flight f=(Flight)(flights.searchwithid(p.getFlightid()).getElement());
	    		                if(p.isCheckedin()) {
	    		                	if(!p.isBoarded()) {
			    		                if(p.isStatus()) {
			    		                	f.getVIPpassengers().remove(p);
			    		                	f.getCanceledPassengers().addFirst(p);
			    		                	p.setCanceled(true);
			    		                }
			    		                else {
			    		                	f.getRegularPassengers().remove(p);
			    		                	f.getCanceledPassengers().addLast(p);
			    		                	p.setCanceled(true);
			    		                }
			    		                p.setCheckedin(false);
			    		                f.getUndo().push("cancel,"+p.getFlightid()+","+p.getPid());
			    		                chekedinl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
			    		        		boardedl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
			    		                canceledl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
	    		                   }
	    		                	else
	    		                		getAlert("THIS PASSENGER HAS ALREADY BOARDED , HE/SHE CAN NOT BE CANCELED !!");
	    		                }
	    		                else
	    		                	getAlert("THE PASSENGER YOU ARE TRYING TO CANCEL IS NOT CHECKED IN , CAN NOT BE CANCELED !!");
	    		                updatePassengersInFidList();
	    		                LocalTime currentTime = LocalTime.now();
	    		    			String g=dp.getValue()+"|"+currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"|"+"Cancel"+"|"+p.getPname()+"|"+p.getPid()+"|"+"Cancel"+" "+p.getPname()+" from flight "+p.getFlightid();
	    		    			log.enqueue(g);
	    	             }
	    			}

	            }
			return null;
			});
			alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			alert.showAndWait();
		});
		border.setOnAction(e->{
			Alert alert=new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText("YOU ARE CHANGING THIS PASSENGER STATE TO BOAREDED , PRESS CONFIRM TO PROCEED !");
			alert.setHeaderText("Please Pay Attention !!");
			ButtonType confirm= new ButtonType("confirm");
			alert.getDialogPane().getButtonTypes().clear();
			alert.getDialogPane().getButtonTypes().add(confirm);
			alert.setResultConverter(dialogButton -> {
	            if (dialogButton == confirm) {
	            	if(!searchf.getText().isEmpty()) {
	    				Passenger p=flights.searchforPassenger(Integer.parseInt(searchf.getText()));
	    				 if(p!=null) {
	    					 if(p.isCheckedin()) {
	    		                Flight f=(Flight)(flights.searchwithid(p.getFlightid()).getElement());
	    		                if(p.isCanceled()) {
	    		                	getAlert("THIS PASSENGER HAS CANCELLED HIS FLIGHTS !! HE CAN NOT BE BOaRDED !!");
	    		                }
	    		                else {
	    		                	if(p.isStatus()) {
	    		                		f.getVIPpassengers().remove(p);
	    		                		f.getBordedPassengers().addFirst(p);
	    		                	}
	    		                	else {
	    		                		f.getRegularPassengers().remove(p);
	    		                		f.getBordedPassengers().addLast(p);
	    		                	}
	    		                	p.setBoarded(true);
	    		                	f.getUndo().push("board,"+p.getFlightid()+","+p.getPid());
	    		                	boardedl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
	    		                }    
	    		                updatePassengersInFidList();
	    		                LocalTime currentTime = LocalTime.now();
	    		    			String g=dp.getValue()+"|"+currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"|"+"Boarding"+"|"+p.getPname()+"|"+p.getPid()+"|"+"Boarding "+" "+p.getPname()+" from flight "+p.getFlightid();
	    		    			log.enqueue(g);
	    					 }
	    					 else
	    						 getAlert("THIS PASSENGER IS NOT CHECKED IN , HE/SHE CAN NOT BE BOARDERED !!");
	    				 }
	    			}

	            }
			return null;
			});
			alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			alert.showAndWait();
		});
		searchforflight.setOnAction(e->{
			try {
				if(!searchforfightf.getText().isEmpty()) {
					tempFlightForPassengers=flights.searchwithid(Integer.parseInt(searchforfightf.getText()));
					updatePassengersInFidList();
					passingersinflightid.setText(((Flight)tempFlightForPassengers.getElement()).getFid()+"");
				}
			}catch(NullPointerException o) {
				getAlert("THE FLIGHT YOU ARE SEARCHING FOR DOES NOT EXIST");
			}
		});
		passengersinFidTable.setOnMouseClicked(event -> {
	        if (event.getClickCount() == 2) {
	            Passenger selectedF = passengersinFidTable.getSelectionModel().getSelectedItem();
	            if (selectedF != null) {
	                Passenger p=flights.searchforPassenger(selectedF.getPid());
	                if(p!=null) {
	                	chekedinl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
	            		boardedl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
	            		canceledl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
		                searchf.setText(p.getPid()+"");
		                namef.setText(p.getPname());
						idf.setText(p.getPid()+"");
						fidf.setValue((Flight)flights.searchwithid(p.getFlightid()).getElement());
						if(p.isStatus())
							vipf.setSelected(true);
						else
							vipf.setSelected(false);
						if(p.isCheckedin())
							chekedinl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
						if(p.isBoarded())
							boardedl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
						if(p.isCanceled())
							canceledl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
	                }
	            }
	        }
	    });
		redo.setOnAction(e->{
			Flight f=(Flight)tempFlightForPassengers.getElement();
			Passenger p=undo(f);
			if(p!=null) {
            	chekedinl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
        		boardedl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
        		canceledl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
                searchf.setText(p.getPid()+"");
                namef.setText(p.getPname());
				idf.setText(p.getPid()+"");
				fidf.setValue((Flight)flights.searchwithid(p.getFlightid()).getElement());
				if(p.isStatus())
					vipf.setSelected(true);
				else
					vipf.setSelected(false);
				if(p.isCheckedin())
					chekedinl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
				if(p.isBoarded())
					boardedl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
				if(p.isCanceled())
					canceledl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
            }
			updatePassengersInFidList();
		});
		undo.setOnAction(e->{
			Flight f=(Flight)tempFlightForPassengers.getElement();
			Passenger p=redo(f);
			if(p!=null) {
            	chekedinl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
        		boardedl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
        		canceledl.setStyle("-fx-text-fill:#c1121f; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
                searchf.setText(p.getPid()+"");
                namef.setText(p.getPname());
				idf.setText(p.getPid()+"");
				fidf.setValue((Flight)flights.searchwithid(p.getFlightid()).getElement());
				if(p.isStatus())
					vipf.setSelected(true);
				else
					vipf.setSelected(false);
				if(p.isCheckedin())
					chekedinl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
				if(p.isBoarded())
					boardedl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
				if(p.isCanceled())
					canceledl.setStyle("-fx-text-fill:green; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 20px;");
            }
			updatePassengersInFidList();
		});
		HBox aai=new HBox(20);
		aai.getChildren().addAll(all,vipPassengers,regPassengers,cancelledPassengers,bordededPassengers);
		
		HBox ss=new HBox(20);
		ss.getChildren().addAll(searchforflight,searchforfightf);
		
		VBox pntb=new VBox(10);
		pntb.setAlignment(Pos.TOP_LEFT);
		pntb.setPadding(new Insets(0,10,0,10));
		pntb.getChildren().addAll(np,passengersinFidTable,aai,ss);
		
		HBox ur=new HBox(10);
		ur.setAlignment(Pos.CENTER);
		ur.getChildren().addAll(checkin,border,cancel);
		
		HBox ee=new HBox(15);
		ee.setAlignment(Pos.CENTER);
		ee.getChildren().addAll(chekedinl,boardedl,canceledl);
		
		HBox uu=new HBox(10);
		uu.setAlignment(Pos.CENTER);
		uu.getChildren().addAll(remove,add);
		
		HBox ie=new HBox(10);
		ie.setAlignment(Pos.CENTER);
		ie.getChildren().addAll(improt,export,update);
		
		vbox.getChildren().addAll(gpane,ee,ie,ur,uu);
		vbox.setMinWidth(500);
		fliBox.getChildren().addAll(vbox,seprator,pntb);
		return fliBox;
	}

	////////////// Add Flight Stage/////////////////
	private void addFlightStage() {
		 
		Stage stage = new Stage();

		VBox vbox=new VBox(20);
		vbox.setAlignment(Pos.TOP_CENTER);
		//vbox.setPadding(new Insets(10));
		vbox.setStyle("-fx-background-color: rgb(253, 240, 213);");
		
		Label header=new Label("Add New Flight");
		header.setStyle("-fx-text-fill: rgb(253, 240, 213); -fx-font-weight: bold;");
		header.setFont(Font.font("elephant", 20));
		
		ImageView logo = new ImageView("logo.png");
		logo.setScaleX(1);
		logo.setScaleY(1);
		logo.setX(2);
		logo.setY(0);
		logo.setFitWidth(50);
		logo.setFitHeight(35);
		
		HBox hbox=new HBox(40);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(header);
		hbox.setStyle("-fx-background-color:#003566");
		
		String style="-fx-text-fill: #c1121f; -fx-font-weight: bold;-fx-font-size: 15px;";
		Label date = new Label("Boarding in:");
        DatePicker datef=new DatePicker();
        Label stat = new Label("Active :");
        CheckBox statf=new CheckBox();
        Label id=new Label ("FLIGHT ID:");
        Label idf=new Label("idNum");
        id.setStyle(style);
        idf.setUnderline(true);
		idf.setStyle("-fx-text-fill:#003566; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 15px;");
		date.setStyle(style);
		stat.setStyle(style);
        datef.setStyle("-fx-border-color: #c1121f;");
        
        datef.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);

                        // Disable dates that are before today
                        if (date.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Optional: Highlight disabled dates
                        }
                    }
                };
            }
        });
        
        Label dest=new Label("Destination:");
        dest.setStyle(style);
        ComboBox<String> destinationsbox = new ComboBox<>(destinations);
        destinationsbox.setEditable(true);
        destinationsbox.setStyle("-fx-border-color: #c1121f;");
		
		GridPane gpane=new GridPane();
		gpane.add(id, 0, 0);
		gpane.add(dest, 0, 1);
		gpane.add(date, 0, 2);
		gpane.add(stat, 0, 3);
		gpane.add(idf, 1, 0);
		gpane.add(destinationsbox, 1, 1);
		gpane.add(datef, 1, 2);
		gpane.add(statf, 1, 3);
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(10);
		gpane.setHgap(30);
		gpane.setPadding(new Insets(0,10,0,10));
		
		idf.setText(Flight.getDefaultid()+"");
		Button add=new Button("ADD");
		add.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 15px;-fx-background-radius: 30; -fx-border-radius: 30;");
		add.setPrefWidth(70);
		add.setPrefHeight(30);
		add.setOnMouseEntered(e -> add.setTranslateY(add.getTranslateY() - 7));
		add.setOnMouseExited(e -> add.setTranslateY(add.getTranslateY() + 7));
		add.setOnAction(e->{
			if(destinationsbox.getSelectionModel().getSelectedItem()!=null&&datef.getValue()!=null) {
				flights.addLast(new Flight(destinationsbox.getSelectionModel().getSelectedItem(),datef.getValue().toString(),statf.isSelected()));
				updateAllFlightsOB();
				destinationsbox.getSelectionModel().clearSelection();
				destinationsbox.setValue(null);
				datef.setValue(null);
				statf.setSelected(false);
				idf.setText(Flight.getDefaultid()+"");
			}
			else
				getAlert("NO EMPTY FIELDS ARE ALLAWED, PLEASE FILL ALL FLIGHT INFO THEN PRESS THE BUTTON!!");
		});
		
		//========================= Table Side===================
		
		
		vbox.getChildren().addAll(hbox,gpane,add);
		BorderPane borderpane=new BorderPane();
		borderpane.setCenter(vbox);
		Scene scene=new Scene(borderpane,320,265); 
		stage.setTitle("Add New Flight");
		stage.getIcons().add(new Image("logo.png"));
		stage.setScene(scene);
		stage.show();
	}
	
	//////////////// add Passenger Stage//////////////////
	private void addPassengerStage() {
		 
		Stage stage = new Stage();

		VBox vbox=new VBox(20);
		vbox.setAlignment(Pos.TOP_CENTER);
		//vbox.setPadding(new Insets(10));
		vbox.setStyle("-fx-background-color: rgb(253, 240, 213);");
		
		Label header=new Label("Add New Passenger");
		header.setStyle("-fx-text-fill: rgb(253, 240, 213); -fx-font-weight: bold;");
		header.setFont(Font.font("elephant", 20));
		
		ImageView logo = new ImageView("logo.png");
		logo.setScaleX(1);
		logo.setScaleY(1);
		logo.setX(2);
		logo.setY(0);
		logo.setFitWidth(50);
		logo.setFitHeight(35);
		
		HBox hbox=new HBox(40);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		hbox.getChildren().addAll(header);
		hbox.setStyle("-fx-background-color:#003566");
		
		String style="-fx-text-fill: #c1121f; -fx-font-weight: bold;-fx-font-size: 15px;";
		Label flightID = new Label("FlightID:");
        
        Label stat = new Label("VIP :");
        CheckBox statf=new CheckBox();
        Label id=new Label ("PassengerID:");
        Label idf=new Label("idNum");
        id.setStyle(style);
        idf.setUnderline(true);
		idf.setStyle("-fx-text-fill:#003566; -fx-font-weight: bold;-fx-font-family: 'Elephant';-fx-font-weight: bold;-fx-font-size: 15px;");
		flightID.setStyle(style);
		stat.setStyle(style);
       
        Label name=new Label("Name:");
        name.setStyle(style);
        TextField namef=new TextField();
		namef.setStyle("-fx-border-color: #c1121f;");
		ComboBox<Flight> fidf = new ComboBox<>();
		fidf.setEditable(true);
		fidf.setItems(allFlightslist);
		fidf.setStyle("-fx-border-color: #c1121f;");
		
		
		GridPane gpane=new GridPane();
		gpane.add(id, 0, 0);
		gpane.add(name, 0, 1);
		gpane.add(flightID, 0, 2);
		gpane.add(stat, 0, 3);
		gpane.add(idf, 1, 0);
		gpane.add(namef, 1, 1);
		gpane.add(fidf, 1, 2);
		gpane.add(statf, 1, 3);
		gpane.setAlignment(Pos.CENTER);
		gpane.setVgap(10);
		gpane.setHgap(30);
		gpane.setPadding(new Insets(0,10,0,10));
		
		idf.setText(Passenger.getDefaultid()+"");
		Button add=new Button("ADD");
		add.setStyle("-fx-background-color: #c1121f; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 15px;-fx-background-radius: 30; -fx-border-radius: 30;");
		add.setPrefWidth(70);
		add.setPrefHeight(30);
		add.setOnMouseEntered(e -> add.setTranslateY(add.getTranslateY() - 7));
		add.setOnMouseExited(e -> add.setTranslateY(add.getTranslateY() + 7));
		add.setOnAction(e->{
			if(fidf.getSelectionModel().getSelectedItem()!=null&&!namef.getText().isEmpty()) {
				int pfid=Integer.parseInt(fidf.getSelectionModel().getSelectedItem()+"");
				boolean y=false;
				if(statf.isSelected())
					y=true;
				Passenger p=new Passenger(namef.getText(),pfid,y);
				Flight f=(Flight)(flights.searchwithid(pfid)).getElement();
				//System.out.println(i++);
//				if(y==true)
//					f.getVIPpassengers().enqueue(p);
//				else
//					f.getRegularPassengers().enqueue(p);
				if(y==true)
					f.getAllPasseners().addFirst(p);
				else
					f.getAllPasseners().addLast(p);
				updatePassengersInFidList();
				namef.setText("");
				statf.setSelected(false);
				fidf.setValue(null);
				idf.setText(Passenger.getDefaultid()+"");
			}
			else
				getAlert("NO EMPTY FIELDS ARE ALLAWED, PLEASE FILL ALL FLIGHT INFO THEN PRESS THE BUTTON!!");
		});
		
		//========================= Table Side===================
		
		
		vbox.getChildren().addAll(hbox,gpane,add);
		BorderPane borderpane=new BorderPane();
		borderpane.setCenter(vbox);
		Scene scene=new Scene(borderpane,320,265); 
		stage.setTitle("Add New Flight");
		stage.getIcons().add(new Image("logo.png"));
		stage.setScene(scene);
		stage.show();
	}
	//////////////// update AllFlights List/////////////////
	private void updateAllFlightsOB() {
		allFlightslist.clear();
		activeFlightslist.clear();
		inactiveFlightslist.clear();
		DNode d=flights.getFirst();
		for(int i=0;i<flights.getCount();i++) {
			allFlightslist.add((Flight)d.getElement());
			if(((Flight)d.getElement()).isStatus())
				activeFlightslist.add((Flight)d.getElement());
			else
				inactiveFlightslist.add((Flight)d.getElement());
			d=d.getNext();
		}
		flightsTable.refresh();
	}
	
	/////////////// undo-redo///////////////////////
	private Passenger undo(Flight f) {
		String i="";
		Stack undo=f.getUndo();
		Stack redo=f.getRedo();
		if(undo.getSize()!=0) {
			String s=(String)undo.pop();
			redo.push(s);
			String[] ops=s.split(",");
			String op=ops[0];
			int fid=Integer.parseInt(ops[1]);
			int pid=Integer.parseInt(ops[2]);
			try {
			Passenger p=flights.searchforPassenger(pid);
			switch (op) {
	            case "checkin":{
	            	i="Check-In";
	            	if(p.isStatus())
	            		f.getVIPpassengers().remove(p);
	            	else
	            		f.getRegularPassengers().remove(p);
	            	p.setCheckedin(false);
	            }
	                break;
	            case "board":{
	            	i="Boareding ";
	            	f.getBordedPassengers().remove(p);
	            	p.setCanceled(false);
	            	if(p.isStatus())
	            		f.getVIPpassengers().enqueue(p);
	            	else
	            		f.getRegularPassengers().enqueue(p);
	            	p.setBoarded(false);
	            }   
	                break;
	            case "cancel":{
	            	i="Cancel";
	            	f.getCanceledPassengers().remove(p);
	            	p.setCanceled(false);
	            	if(p.isStatus())
	            		f.getVIPpassengers().enqueue(p);
	            	else
	            		f.getRegularPassengers().enqueue(p);
	            	p.setCheckedin(true);
	            }  
	                break;
                
           }
			LocalTime currentTime = LocalTime.now();
			String g=dp.getValue()+"|"+currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"|"+"Undo"+"|"+i+"|"+p.getPname()+"|"+p.getPid()+"|"+"undo "+i+" "+p.getPname()+" from flight "+p.getFlightid();
			log.enqueue(g);
			return p;
			}catch(NullPointerException u) {
				
			}
		}
		return null;
	}
	private Passenger redo(Flight f) {
		String i="";
		Stack undo=f.getUndo();
		Stack redo=f.getRedo();
		if(redo.getSize()!=0) {
			String s=(String)redo.pop();
			undo.push(s);
			String[] ops=s.split(",");
			String op=ops[0];
			int fid=Integer.parseInt(ops[1]);
			int pid=Integer.parseInt(ops[2]);
			try {
			Passenger p=flights.searchforPassenger(pid);
			switch (op) {
	            case "checkin":{
	            	i="Check-In";
	            	if(p.isStatus())
	            		f.getVIPpassengers().enqueue(p);
	            	else
	            		f.getRegularPassengers().enqueue(p);
	            	p.setCheckedin(true);
	            }
	                break;
	            case "board":{
	            	i="Boarding";
	            	if(p.isStatus()) {
	            		f.getVIPpassengers().remove(p);
	            		f.getBordedPassengers().addFirst(p);
	            	}else {
	            		f.getBordedPassengers().addLast(p);
	            		f.getRegularPassengers().remove(p);
	            	}
	            	p.setBoarded(true);
	            }   
	                break;
	            case "cancel":{
	            	i="Cancel";
	            	p.setCanceled(true);
	            	if(p.isStatus()) {
	            		f.getCanceledPassengers().addFirst(p);
	            		f.getVIPpassengers().remove(p);
	            	}
	            	else {
	            		f.getCanceledPassengers().addLast(p);
	            		f.getRegularPassengers().remove(p);
	            	}
	            	p.setCheckedin(false);
	            }  
	                break;
           }
			LocalTime currentTime = LocalTime.now();
			String g=dp.getValue()+"|"+currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))+"|"+"Redo"+"|"+i+"|"+p.getPname()+"|"+p.getPid()+"|"+"undo "+i+" "+p.getPname()+" from flight "+p.getFlightid();
			log.enqueue(g);
			return p;
			}catch(NullPointerException r) {
				
			}
		}
		return null;
	}
	
	/////////////////// Statistics////////////////////////
	private HBox statistics() {
		HBox hbox=new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		statisticsTable.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Flight item, boolean empty) {
                super.updateItem(item, empty);
                setStyle(""); // Reset all rows to their original style
            }
        });
		updateAllFlightsOB();
		
		statisticsTable.setStyle("-fx-border-color:#003566;-fx-border-Width:3;-fx-text-fill: white;-fx-font-weight: bold;-fx-max-width: 1060px; -fx-min-height: 500px;");
		TableColumn<Flight, Integer> idColumn = new TableColumn<>("Flighgt ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("fid"));
		idColumn.setMinWidth(150); 
		idColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, Integer> vipcheckinColumn = new TableColumn<>("VIP-checkedIn");
		vipcheckinColumn.setCellValueFactory(new PropertyValueFactory<>("nvipcheckedin"));
		vipcheckinColumn.setMinWidth(150); 
		vipcheckinColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, Integer> regcheckinColumn = new TableColumn<>("Regular-checkIn");
		regcheckinColumn.setCellValueFactory(new PropertyValueFactory<>("nregcheckedin"));
		regcheckinColumn.setMinWidth(150); 
		regcheckinColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, Integer> vipborderedColumn = new TableColumn<>("VIP-Boarded");
		vipborderedColumn.setCellValueFactory(new PropertyValueFactory<>("nvipbordered"));
		vipborderedColumn.setMinWidth(150); 
		vipborderedColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, Integer> regbordedColumn = new TableColumn<>("Regular-Boarded");
		regbordedColumn.setCellValueFactory(new PropertyValueFactory<>("nregboredred"));
		regbordedColumn.setMinWidth(150); 
		regbordedColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, Integer> vipcanceledColumn = new TableColumn<>("VIP-Canceled");
		vipcanceledColumn.setCellValueFactory(new PropertyValueFactory<>("nvipcancelled"));
		vipcanceledColumn.setMinWidth(150); 
		vipcanceledColumn.setStyle("-fx-alignment: CENTER;");
		TableColumn<Flight, Integer> regcanceledColumn = new TableColumn<>("Regular-Canceled");
		regcanceledColumn.setCellValueFactory(new PropertyValueFactory<>("nregcancelled"));
		regcanceledColumn.setMinWidth(150); 
		regcanceledColumn.setStyle("-fx-alignment: CENTER;");
		statisticsTable.getColumns().addAll(idColumn,vipcheckinColumn,regcheckinColumn,vipborderedColumn,regbordedColumn,vipcanceledColumn,regcanceledColumn);
		updateAllFlightsOB();
		//statisticsTable.setPrefWidth(1060);
		statisticsTable.refresh();
		
		Button search=new Button("Search For Flight");
		search.setStyle("-fx-background-color: #003566; -fx-text-fill: white; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 20px;-fx-background-radius: 30; -fx-border-radius: 30;");
		search.setPrefWidth(250);
		search.setPrefHeight(50);
		search.setOnMouseEntered(e -> search.setTranslateY(search.getTranslateY() - 7));
		search.setOnMouseExited(e -> search.setTranslateY(search.getTranslateY() + 7));
		TextField searchf=new TextField();
		String stylef="-fx-min-width: 250px;-fx-min-height: 40px;-fx-border-color: #003566;-fx-font-size: 20px;";
		searchf.setStyle(stylef);

		search.setOnAction(e -> {
		    if (!searchf.getText().isEmpty()) {
		        updateAllFlightsOB();
		        tempFlight = flights.searchwithid(Integer.parseInt(searchf.getText()));
		        Flight tempf = (Flight) tempFlight.getElement();
		        
		        // Collect the flights to be removed
		        List<Flight> flightsToRemove = new ArrayList<>();
		        for (Flight f : allFlightslist) {
		            if (f.getFid() == tempf.getFid()) {
		                flightsToRemove.add(f);
		            }
		        }
		        allFlightslist.removeAll(flightsToRemove);
		        allFlightslist.add(0, tempf);

		        statisticsTable.setRowFactory(tv -> new TableRow<>() {
		            @Override
		            protected void updateItem(Flight item, boolean empty) {
		                super.updateItem(item, empty);
		                if (item == null || empty) {
		                    setStyle(""); 
		                } else if (item.getFid() == tempf.getFid()) {
		                    setStyle("-fx-background-color: rgb(250, 80, 80)\r\n"
		                            + "; -fx-font-weight: bold;");
		                } else {
		                    setStyle(""); // Reset non-matching rows
		                }
		            }
		        });
		        statisticsTable.refresh();
		    }
		});
		
		HBox s=new HBox(20);
		s.setAlignment(Pos.CENTER);
		s.getChildren().addAll(search,searchf);
		VBox vbox=new VBox(20);
		vbox.setPrefHeight(1200);
		vbox.setAlignment(Pos.CENTER );
		vbox.getChildren().addAll(statisticsTable,s);
		hbox.getChildren().addAll(vbox);
		
		return hbox;
	}
	///////////// updatePassengersList////////////////////
	private void updatePassengersInFidList() {
		if(tempFlightForPassengers!=null) {
			passengersinFidList.clear();
			vippassengersinFidList.clear();
			regpassengersinFidList.clear();
			bordedpassengersinFidList.clear();
			canceledpassengersinFidList.clear();
			Queue vip=((Flight)tempFlightForPassengers.getElement()).getVIPpassengers();
			Queue reg=((Flight)tempFlightForPassengers.getElement()).getRegularPassengers();
			SLL canceled=((Flight)tempFlightForPassengers.getElement()).getCanceledPassengers();
			SLL borded=((Flight)tempFlightForPassengers.getElement()).getBordedPassengers();
			SLL all=((Flight)tempFlightForPassengers.getElement()).getAllPasseners();
			
			
			SNode atemp=all.getFirst();
			if(atemp!=null) {
				for(int i=0;i<all.getCount();i++) {
					passengersinFidList.add((Passenger)atemp.getElement());
					atemp=atemp.getNext();
				}
			}
			
			for(int i=0;i<vip.getSize();i++) {
				Passenger p=(Passenger)vip.dequeue();
				vippassengersinFidList.add(p);
				vip.enqueue(p);
			}
			for(int i=0;i<reg.getSize();i++) {
				Passenger p=(Passenger)reg.dequeue();
				regpassengersinFidList.add(p);
				reg.enqueue(p);
			}
			SNode temp=canceled.getFirst();
			if(temp!=null) {
				for(int i=0;i<canceled.getCount();i++) {
					canceledpassengersinFidList.add((Passenger)temp.getElement());
					temp=temp.getNext();
				}
			}
			SNode btemp=borded.getFirst();
			if(btemp!=null) {
				for(int i=0;i<borded.getCount();i++) {
					bordedpassengersinFidList.add((Passenger)btemp.getElement());
					btemp=btemp.getNext();
				}
			}
			
			passengersinFidTable.refresh();
		}
	}

	
    ///////////////// getAlert//////////////////////////////////
	
	private void getAlert(String str) {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERORR");
		alert.setContentText(str);
		alert.setHeaderText("ERROR ALERT");
		alert.showAndWait();
	}



	

}
