package kda.chatapp.client;

import kda.chatapp.client.form.MainForm;

import javax.swing.JFrame;

public class ChatAppClient {

	public static void main(String[] args) {
        CallBack callBack = new CallBack();
        MainForm mainForm = new MainForm(callBack);

        mainForm.incoming("OK");
	}

}
