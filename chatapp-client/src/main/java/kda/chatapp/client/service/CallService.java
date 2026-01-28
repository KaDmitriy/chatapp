package kda.chatapp.client.service;

import kda.chatapp.client.dto.CallTo;
import kda.chatapp.client.dto.CallToCheck;
import kda.chatapp.client.form.MainForm;
import kda.chatapp.client.ws.PushChannelCall;
import kda.chatapp.client.ws.WS;

public class CallService {

    private MainForm mainForm;
    private WS ws;

    private Integer lastCallUserId = 0;

    public void setMainForm(MainForm mainForm){
        this.mainForm = mainForm;
    }

    public void setWS(WS ws){
        this.ws = ws;
    }

    public void callIn(CallTo callTo) {
        lastCallUserId = callTo.getUserFromId();
        mainForm.incoming("call UserFromId:"+callTo.getUserFromId());
    }

    public void callToCheck(CallToCheck callTo) {
        var pushChannelCall = new PushChannelCall<CallToCheck>(callTo);
        ws.sendMessage(pushChannelCall);
    }

    public Integer getLastCallUserId() {
        return lastCallUserId;
    }
}
