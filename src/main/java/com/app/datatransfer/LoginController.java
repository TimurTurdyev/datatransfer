package com.app.datatransfer;

import com.app.datatransfer.animations.Shake;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class LoginController {
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button authLogin;

    private String errorMessage;

    @FXML
    private void authAction() throws IOException, InterruptedException {

        if (email.getText() == "") {
            Shake emailShake = new Shake(email);
            emailShake.play();
            return;
        }

        if (password.getText() == "") {
            Shake passwordShake = new Shake(password);
            passwordShake.play();
            return;
        }


        Task<Integer> httpTask = new Task<Integer>() {
            @Override
            public Integer call() throws Exception {
                return authToServer();
            }
        };

        Thread thread = new Thread(httpTask);

        httpTask.setOnSucceeded(e -> {
            changeFieldsState(false);
            Integer responseCode = httpTask.getValue();

            if (responseCode == 200) {
                try {
                    AppApplication.changeScene("config-view.fxml");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(errorMessage);
                alert.show();
            }
        });

        changeFieldsState(true);

        thread.start();
    }

    public int authToServer() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // json formatted data
        String json = new StringBuilder()
                .append("{\"login\":\"")
                .append(email.getText())
                .append("\",")
                .append("\"password\":\"")
                .append(password.getText())
                .append("\"}")
                .toString();

        URI uri = URI.create("https://auth.waviot.ru/?action=user-login&true_api=1");

        // add json header
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(uri)
                .setHeader("User-Agent", "Java bot")
                .header("X-requested-with", "XMLHttpRequest")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        if (response.statusCode() != 200) {
            errorMessage = response.body();
        }
        // print response body
        System.out.println(response.body());

        return response.statusCode();
    }

    private void changeFieldsState(Boolean state) {
        email.setDisable(state);
        password.setDisable(state);
        authLogin.setDisable(state);
    }
}