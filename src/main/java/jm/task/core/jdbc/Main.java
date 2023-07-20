package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
//         реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Keks","KeksLastName", (byte) 16);
        userService.saveUser("Keks2","KeksLastName2", (byte) 26);
        userService.saveUser("Keks3","KeksLastName3", (byte) 36);
        userService.saveUser("Keks4","KeksLastName4", (byte) 46);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
