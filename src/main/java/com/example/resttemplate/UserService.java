package com.example.resttemplate;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

public class UserService {
    private final RestTemplate restTemplate;
    private String sessionId;

    public UserService() {
        this.restTemplate = new RestTemplate();
    }

    // Получение всех пользователей
    public void getAllUsers() {
        String url = "http://94.198.50.185:7081/api/users";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Извлечение Session ID из Cookie
        HttpHeaders headers = response.getHeaders();
        String cookie = headers.getFirst(HttpHeaders.SET_COOKIE);
        if (cookie != null) {
            this.sessionId = cookie.split(";")[0];
        }
        System.out.println("Полученные пользователи: " + response.getBody());
        System.out.println("Session ID: " + this.sessionId);
    }

    // Добавление пользователя
    public String addUser(User user) {
        String url = "http://94.198.50.185:7081/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", this.sessionId);

        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println("Добавление пользователя: " + response.getBody());
        return response.getBody();
    }

    // Изменение пользователя
    public String updateUser(User user) {
        String url = "http://94.198.50.185:7081/api/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", this.sessionId);

        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        System.out.println("Изменение пользователя: " + response.getBody());
        return response.getBody();
    }

    // Удаление пользователя
    public String deleteUser(Long id) {
        String url = "http://94.198.50.185:7081/api/users/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", this.sessionId);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
        System.out.println("Удаление пользователя: " + response.getBody());
        return response.getBody();
    }
}