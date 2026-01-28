package kda.chatapp.client.form;

import kda.chatapp.client.CallBack;
import kda.chatapp.client.CallBackType;
import kda.chatapp.client.ConnectHttp;
import kda.chatapp.client.dto.CallTo;
import kda.chatapp.client.dto.CallToCheck;
import kda.chatapp.client.service.CallService;
import kda.chatapp.client.ws.WS;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainForm {

    private JTextArea text;
    private CallBack callBack;
    private JTextField sendText;

    JTextField userText;
    JTextField passText;
    JCheckBox checkBoxConfirm;

    ConnectHttp connectHttp;
    WS ws;
    CallService callService;

    public MainForm(CallBack callBack, ConnectHttp connectHttp, WS ws, CallService callService){
        this.connectHttp =  connectHttp;
        this.callBack = callBack;
        this.callService = callService;
        callService.setMainForm(this);
        this.ws = ws;
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

        //CALL
        JPanel panelCall = new JPanel();
        panelCall.setLayout(new FlowLayout());
        JButton buttonCall = new JButton("Call");
        buttonCall.addActionListener(e -> callTo());
        JButton buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener(e -> callConfirm());
        checkBoxConfirm = new JCheckBox("Confirm");
        panelCall.add(buttonCall);
        panelCall.add(buttonConfirm);
        panelCall.add(checkBoxConfirm);

        JButton buttonConnect = new JButton("Connect");
        buttonConnect.addActionListener(e -> connectServer());


        JPanel panelSettings = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panelSettings, BoxLayout.Y_AXIS);
        buttonConnect.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelSettings.setLayout( boxLayout);
        panelSettings.add(label);
        panelSettings.add(panelUser);
        panelSettings.add(buttonConnect);
        panelSettings.add(panelCall);

        return panelSettings;

    }

    private void connectServer(){
        System.out.println("Connecting to server...");
        //ConnectHttp connectHttp = new ConnectHttp();
        connectHttp.connect(userText.getText(), passText.getText());
        if(connectHttp.getConnected()){
            incoming("Connect > user:" + connectHttp.getUsername() );
            incoming("HTTP SessionID:" + connectHttp.getSessionID() );
            incoming("User> id:" + connectHttp.getUserInfo().getId() + ", name:" + connectHttp.getUserInfo().getName() );
            ws.start();
        } else {
            incoming("Connect error" );
        }
    }

    public void incoming(String textStr){
        text.append("> "+textStr+" \n");
    }

    public void callTo() {
        System.out.println("Calling to...");
    }

    public void callConfirm() {
        Boolean val = checkBoxConfirm.isSelected();
        System.out.println("callConfirm > select:"+val);
        callService.callToCheck(new CallToCheck(callService.getLastCallUserId(), connectHttp.getUserInfo().getId(), val));
    }
}
