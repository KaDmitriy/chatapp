package kda.chatapp.client.service;

import kda.chatapp.client.dto.CallIn;
import kda.chatapp.client.form.MainForm;

public class CallService {

    private MainForm mainForm;

    public void setMainForm(MainForm mainForm){
        this.mainForm = mainForm;
    }

    public void callIn(CallIn callIn) {
        mainForm.incoming("call userID:"+callIn.getCallUserID());
    }

}
