package com.main.api;

import com.main.entity.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class API {
    private final String baseURL = "http://gameworldserver.ga:6969/api";
    private int userId = 0;
    private static  API instance = null;

    private API() {
    }

    public static API getInstance() {
        if(instance==null){
            instance=new API();
        }
        return instance;
    }

    public boolean playerLogin(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);

            URL url = new URL(baseURL + "/clients/login");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setRequestProperty("Content-Type", "application/json");
            http.setDoOutput(true);

            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(json.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = http.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            StringBuilder content;
            JSONParser parser = new JSONParser();
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(http.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {

                    content.append(line);
//                    content.append(System.lineSeparator());
                }
                JSONObject jsonObject = (JSONObject) parser.parse(content.toString());
                instance.userId = Integer.parseInt(jsonObject.get("userId").toString());
            } finally {
                http.disconnect();
            }


            if (responseCode == 200) {
                return true;
            } else {
                return false;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean playerRegister(String username, String password) {

        try {
            JSONObject json = new JSONObject();
            json.put("email", username + "@game.com");
            json.put("name", username);
            json.put("username", username);
            json.put("password", password);

            URL url = new URL(baseURL + "/clients/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setRequestProperty("Content-Type", "application/json");
            http.setDoOutput(true);

            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(json.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = http.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);

            if (responseCode == 200) {
                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveScore(int score) {
        try {
            JSONObject json = new JSONObject();
            System.out.println("Player ID: " + instance.userId);
            json.put("client_score_id", instance.userId);
            json.put("score", score);
            json.put("movement", 0);

            URL url = new URL(baseURL + "/scores");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST"); // PUT is another valid option
            http.setRequestProperty("Content-Type", "application/json");
            http.setDoOutput(true);

            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            OutputStream os = http.getOutputStream();
            os.write(json.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = http.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            StringBuilder content;
            JSONParser parser = new JSONParser();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                JSONObject jsonObject = (JSONObject) parser.parse(content.toString());
                System.out.println(content.toString());
            } finally {
                http.disconnect();
            }


            if (responseCode == 200) {
                return true;
            } else {
                return false;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public long getHighScore() {
        try {

            URL url = new URL(baseURL + "/scores?filter={\"order\":\"score%20DESC\",%20\"limit\":1}");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET"); // PUT is another valid option
            http.setRequestProperty("Content-Type", "application/json");
            http.setDoOutput(true);

            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();


            int responseCode = http.getResponseCode();
            System.out.println("GET Response Code :  " + responseCode);
            StringBuilder content;
            JSONParser parser = new JSONParser();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                JSONArray jsonArray = (JSONArray) parser.parse(content.toString());
                int size = jsonArray.size();
                if (size > 0) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                    return (long) jsonObject.get("score");
                }
            } finally {
                http.disconnect();
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getYourHighScore() {
        try {

            URL url = new URL(baseURL + "/scores?filter={\"where\":{\"client_score_id\":" + userId + "},\"order\":\"score%20DESC\",%20\"limit\":1}");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET"); // PUT is another valid option
            http.setRequestProperty("Content-Type", "application/json");
            http.setDoOutput(true);

            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();


            int responseCode = http.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            StringBuilder content;
            JSONParser parser = new JSONParser();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                JSONArray jsonArray = (JSONArray) parser.parse(content.toString());
                int size = jsonArray.size();
                if (size > 0) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                    return (long) jsonObject.get("score");
                }
            } finally {
                http.disconnect();
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long getPlayersCount() {
        try {

            URL url = new URL(baseURL + "/clients/count");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET"); // PUT is another valid option
            http.setRequestProperty("Content-Type", "application/json");
            http.setDoOutput(true);

            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();


            int responseCode = http.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            StringBuilder content;
            JSONParser parser = new JSONParser();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                JSONObject result = (JSONObject) parser.parse(content.toString());
                return (long) result.get("count");
            } finally {
                http.disconnect();
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
