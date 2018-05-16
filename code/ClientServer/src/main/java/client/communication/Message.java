package client.communication;

import java.util.Arrays;

public class Message {

    private final String name;
    private final byte[] msg;

    public Message(String name, byte[] msg){
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public byte[] getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", msg=" + Arrays.toString(msg) +
                '}';
    }
}
