package kda.chatapp.client.ws;

public interface PushChannel<T> {

    public String getUrl();

    public T getModel();
}
