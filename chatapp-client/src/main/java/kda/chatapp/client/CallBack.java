package kda.chatapp.client;

public class CallBack {

    public void action(CallBackType callBackType){
        switch (callBackType){
            case CONNECT -> System.out.println("connect");
            case DISCONNECT -> System.out.println("disconnect");
            case CLEAN -> System.out.println("clean");
        }
    }


    public void actionSend(String text){
        System.out.println("send: "+text);
    }

}
