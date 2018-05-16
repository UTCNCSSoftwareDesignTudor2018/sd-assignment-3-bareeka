package server;

import server.communication.Server;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        new Server().listen();

    }
}
