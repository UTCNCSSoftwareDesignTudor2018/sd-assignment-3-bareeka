package client.login;

import server.Controller;

public class Login {

    public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        Controller controller = Controller.getController();
        if (username.equals("Georgie") && password.equals(controller.findByUsername("George R.R. Martin").getPassword())) {
            return true;
        }
        return false;
    }

}
