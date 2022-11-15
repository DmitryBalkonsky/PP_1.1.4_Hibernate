package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("dima","balynin", (byte) 26);
        System.out.println("пользователь с именем – dima добавлен в базу данных");
        userService.saveUser("dima1","balynin1", (byte) 27);
        System.out.println("пользователь с именем – dima1 добавлен в базу данных");
        userService.saveUser("dima2","balynin2", (byte) 28);
        System.out.println("пользователь с именем – dima2 добавлен в базу данных");
        userService.saveUser("dima3","balynin3", (byte) 29);
        System.out.println("пользователь с именем – dima3 добавлен в базу данных");
        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
