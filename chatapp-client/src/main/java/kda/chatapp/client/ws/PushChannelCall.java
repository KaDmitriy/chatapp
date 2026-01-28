package kda.chatapp.client.ws;

public class PushChannelCall<CallToCheck> implements PushChannel {

    private CallToCheck  callToCheck;

    public PushChannelCall(CallToCheck callToCheck) {
        this.callToCheck = callToCheck;
    }

    public String getUrl(){
        return "/app/call/tocheck";
    }

    @Override
    public CallToCheck getModel() {
        return callToCheck;
    }

}
