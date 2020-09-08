package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Month;

public class Main extends Application {
    private Label title, dateLb, marksLb;
    private  TextField marksTF;
    private DatePicker datepicker;
    private  Button savedata;
    private  HBox titleHbox, dateHbox, marksHbox,buttonHbox, currentDateHbox;
    private  VBox root;
    private Alert alert;
    private  CheckBox currentDate;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //set title
        title=new Label("My CP Tracker");
       title.setFont(new Font("Arial",36));
        titleHbox=new HBox(40,title);
        titleHbox.setAlignment(Pos.CENTER);

        //set Date
        dateLb=new Label("Date: ");
        dateLb.setPrefWidth(40);
        datepicker=new DatePicker();
        datepicker.setPrefWidth(Double.valueOf(180));
        datepicker.setMaxWidth(Double.MAX_VALUE);
        dateHbox= new HBox( 10,dateLb, datepicker);
        dateHbox.setPadding(new Insets(20));
        dateHbox.setAlignment(Pos.CENTER);



        //set Current Date
        currentDate=new CheckBox("Current Date");
        currentDate.setPrefWidth(150);
        currentDateHbox =new HBox(10,currentDate);
        currentDateHbox.setAlignment(Pos.CENTER);

        currentDate.setOnAction(event -> {
            if(currentDate.isSelected()){
                datepicker.setDisable(true);
            }
            else {
                datepicker.setDisable(false);
            }
        });




        //set Marks
        marksLb=new Label("Marks:");
        marksLb.setPrefWidth(40);
        marksTF=new TextField();
        marksTF.setPrefColumnCount(15);
        marksTF.setPromptText("Marks");
        marksTF.setMaxWidth(Double.MAX_VALUE);
        marksHbox=new HBox(10, marksLb, marksTF);
        marksHbox.setPadding(new Insets(20));
        marksHbox.setAlignment(Pos.CENTER);

        //set button
        savedata=new Button();
        savedata.setText("Save Data");
        buttonHbox=new HBox(40,savedata);
        buttonHbox.setPadding(new Insets(0,80,0,0));
        buttonHbox.setAlignment(Pos.CENTER_RIGHT);

        //button on set

           savedata.setOnAction(event -> save());

           //allert setting
        alert= new Alert(Alert.AlertType.INFORMATION);

        //root setting
        root =new VBox();
        root.getChildren().addAll(titleHbox, dateHbox,currentDateHbox, marksHbox,buttonHbox);
        Scene scene=new Scene(root,400,350);
        primaryStage.setTitle("181380035 CP Tacker");
        primaryStage.setScene(scene);
        primaryStage.show();




    }




    private  void save(){
        String dat, result;
        LocalDate currentdt= LocalDate.now();

             if(currentDate.isSelected()){
                 dat=currentdt.toString();
             }
             else {
                 dat =datepicker.getValue().toString();

             }




        File file=new File("181380035.txt");
        boolean exisit= file.exists();
         if(exisit){

             try{
                 // Create file
                 FileWriter fstream = new FileWriter(file,true);
                 BufferedWriter out = new BufferedWriter(fstream);
                 result="-----------CP Mar"+ dat + "-----------"+ "\n"
                         + "Marks: " + marksTF.getText().toString() + "\n\n";
                 fstream.write(result);
                 //Close the output stream
                 out.close();
                 fstream.close();
                 alert.setHeaderText("your CP data is saved successfully");
                alert.setContentText(result);
                alert.show();

             }
             catch (Exception e){//Catch exception if any
                 System.err.println("Error: " + e.getMessage());
             }
         }// if end
         else{

             try{
                 // Create file
                 FileWriter fstream = new FileWriter(file);
                 BufferedWriter out = new BufferedWriter(fstream);
                 result="-----------CP Mar"+ dat + "-----------"+ "\n"
                         + "Marks: " + marksTF.getText().toString() + "\n\n";
                 out.write(result);
                 //Close the output stream
                 out.close();
                 alert.setHeaderText("New file is created");
                 alert.setContentText(result);
                 alert.show();

             }
             catch (Exception e){//Catch exception if any
                 System.err.println("Error: " + e.getMessage());
             }


         }






    }


    public static void main(String[] args) {
        launch(args);
    }
}
