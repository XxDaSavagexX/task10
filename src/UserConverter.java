import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        return json;
    }
}

public class UserConverter {
    public static void convertToJson(String inputFilename, String outputFilename) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename))) {
            // Read header
            String headerLine = br.readLine();
            String[] headers = headerLine.split(" ");

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                if (parts.length == headers.length) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    users.add(new User(name, age));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create JSON array and write to file
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            jsonArray.put(user.toJson());
        }

        try (FileWriter fileWriter = new FileWriter(outputFilename)) {
            fileWriter.write(jsonArray.toString(4)); // Indent with 4 spaces
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        convertToJson("C:\\Users\\SikAr\\IdeaProjects\\task10\\src\\file1.txt", "C:\\Users\\SikAr\\IdeaProjects\\task10\\src\\user.json");
    }
}
