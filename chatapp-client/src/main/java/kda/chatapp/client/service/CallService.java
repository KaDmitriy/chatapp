package kda.chatapp.client.service;

import kda.chatapp.client.dto.CallTo;
import kda.chatapp.client.form.MainForm;

public class CallService {

    private MainForm mainForm;

    public void setMainForm(MainForm mainForm){
        this.mainForm = mainForm;
    }

    public void callIn(CallTo callTo) {
        mainForm.incoming("call UserFromId:"+callTo.getUserFromId());
    }

}
