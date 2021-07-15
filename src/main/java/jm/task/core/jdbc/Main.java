package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Oleg", "Golovin", (byte) 27);
        userService.saveUser("Mike", "Smirnov", (byte) 26);
        userService.saveUser("Sergey", "Berezin", (byte) 28);
        userService.saveUser("asd", "dsa", (byte) 1);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}







