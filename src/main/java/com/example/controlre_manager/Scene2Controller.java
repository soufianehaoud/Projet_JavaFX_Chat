package com.example.controlre_manager;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Scene2Controller {

    @FXML
    private TextField MymsgID;
    @FXML
    private TextField PortID;
    @FXML
    private TextField HostID;
    @FXML
    private ListView testview;
    PrintWriter pw;
    @FXML
    protected void onconnect() throws IOException {
        String host=HostID.getText();
        int port=Integer.parseInt(PortID.getText());
        //socket
        Socket s=new Socket(host,port);
        InputStream is=s.getInputStream();//octet
        InputStreamReader isr=new InputStreamReader(is);//caractere
        BufferedReader br=new BufferedReader(isr);
        OutputStream os=s.getOutputStream();//octet
         pw=new PrintWriter(os,true);
       new Thread(()-> {
           while(true){
               try {
                   String reponse = br.readLine();
                   Platform.runLater(()-> {
                       testview.getItems().add(reponse);
                   });

               }catch(IOException e){
                   e.printStackTrace();
               }
           }

       }).start();


    }
     @FXML
    public void onsubmit(){
        String message=MymsgID.getText();
        pw.println(message);
     }



}
