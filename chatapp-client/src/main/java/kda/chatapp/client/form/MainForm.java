package kda.chatapp.client.form;

import kda.chatapp.client.CallBack;
import kda.chatapp.client.CallBackType;
import kda.chatapp.client.ConnectHttp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainForm {

    private JTextArea text;
    private CallBack callBack;
    private JTextField sendText;

    JTextField userText;
    JTextField passText;

    public MainForm(CallBack callBack){
        this.callBack = callBack;
        sendText = new JTextField(10);
        sendText.setColumns(40);

        // Create a button
        JButton buttonSend = new JButton("Send >");
        buttonSend.addActionListener(e -> actionSend());

        JButton buttonConnect = new JButton("Connect");
        buttonConnect.addActionListener(e -> actionConnect());
        JButton buttonDisconnect = new JButton("Disconnect");
        buttonDisconnect.addActionListener(e -> actionDisconnect());

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.add(sendText);
        panel.add(buttonSend);
        panel.add(buttonConnect);
        panel.add(buttonDisconnect);

        text = new JTextArea();
        text.setColumns(70);
        text.setRows(30);
        JPanel panel2 = new JPanel();
        panel2.add(text);

        // Create the main frame
        JFrame frame = new JFrame("My First Swing GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 600);


        JButton buttonClear = new JButton("Clear");
        buttonClear.addActionListener(e -> text.setText(""));
        JPanel panelSouth = new JPanel();
        panelSouth.add(buttonClear);



        // Add the panel to the frame
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.getContentPane().add(BorderLayout.SOUTH, panelSouth);
        frame.getContentPane().add(BorderLayout.WEST, panelUser());


        // Make the frame visible
        frame.setVisible(true);
    }

    private void actionConnect(){
        if(callBack!=null) callBack.action(CallBackType.CONNECT);
        text.append("Connect\n");
    }

    private void actionDisconnect(){
        if(callBack!=null) callBack.action(CallBackType.DISCONNECT);
        text.append("Disconnect\n");
    }

    private void actionSend(){
        String textStr = sendText.getText();
        if(callBack!=null) callBack.actionSend(textStr);
        text.append("< "+textStr+" \n");
    }

    private JPanel panelUser(){
        JLabel label = new JLabel("Settings user");

        userText = new JTextField(10);
        passText = new JTextField(10);
        JLabel labelUser = new JLabel("User");
        JLabel labelPass = new JLabel("Pass");
        JPanel panelUser = new JPanel();
        panelUser.setLayout(new FlowLayout());
        panelUser.add(labelUser);
        panelUser.add(userText);
        panelUser.add(labelPass);
        panelUser.add(passText);
        Border titledBorder = BorderFactory.createTitledBorder("Login");
        panelUser.setBorder(titledBorder);
        //panelUser.setPreferredSize(new Dimension(400, 100));

        JButton buttonConnect = new JButton("Connect");
        buttonConnect.addActionListener(e -> connectServer());
        JButton buttonCall = new JButton("Call");

        JPanel panelSettings = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panelSettings, BoxLayout.Y_AXIS);
        buttonConnect.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSettings.setLayout( boxLayout);
        panelSettings.add(label);
        panelSettings.add(panelUser);
        panelSettings.add(buttonConnect);
        panelSettings.add(buttonCall);

        return panelSettings;

    }

    private void connectServer(){
        System.out.println("Connecting to server...");
        ConnectHttp connectHttp = new ConnectHttp();
        connectHttp.connect(userText.getText(), passText.getText());
    }

    public void incoming(String textStr){
        text.append("> "+textStr+" \n");
    }

}
