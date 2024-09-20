import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class PhoneValidator {
    public static void validatePhoneNumbers(String filename) {
        String regex = "^(\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4})$";
        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (pattern.matcher(line.trim()).matches()) {
                    System.out.println(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        validatePhoneNumbers("C:\\Users\\SikAr\\IdeaProjects\\task10\\src\\file.txt");
    }
}
