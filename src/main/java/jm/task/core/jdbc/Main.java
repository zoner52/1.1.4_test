package jm.task.core.jdbc;


import com.mysql.cj.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        User [] users = {new User("Oleg", "Golovin", (byte) 27),
        new User("Mike", "Smirnov", (byte) 26),
        new User("Sergey", "Berezin", (byte) 28),
        new User("asd", "dsa", (byte) 1)};

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        for (User user : users ) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n",user.getName());

        }
        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}







