package com.example.resttemplate;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        // 1. Получить всех пользователей и сохранить sessionId
        userService.getAllUsers();

        // 2. Добавить пользователя
        User newUser = new User(3L, "James", "Brown", (byte) 30);
        String part1 = userService.addUser(newUser);

        // 3. Изменить пользователя
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        String part2 = userService.updateUser(newUser);

        // 4. Удалить пользователя
        String part3 = userService.deleteUser(3L);

        // Собираем итоговый код
        String finalCode = part1 + part2 + part3;
        System.out.println("Итоговый код: " + finalCode);
    }
}

