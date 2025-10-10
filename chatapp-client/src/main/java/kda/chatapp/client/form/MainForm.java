package kda.chatapp.client.form;

import kda.chatapp.client.CallBack;
import kda.chatapp.client.CallBackType;

import javax.swing.*;
import java.awt.*;

public class MainForm {

    private JTextArea text;
    private CallBack callBack;
    private JTextField sendText;

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
        frame.setSize(800, 600);


        JButton buttonClear = new JButton("Clear");
        buttonClear.addActionListener(e -> text.setText(""));
        JPanel panelSouth = new JPanel();
        panelSouth.add(buttonClear);

        // Add the panel to the frame
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);
        frame.getContentPane().add(BorderLayout.SOUTH, panelSouth);

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

    public void incoming(String textStr){
        text.append("> "+textStr+" \n");
    }

}
