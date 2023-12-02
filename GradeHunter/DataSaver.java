package GradeHunter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {
    public void saveGameResult(String studentID, int subject, String totalTime) {
        String subjectName = convertSubjectToString(subject);
        String record = studentID + " " + totalTime + " " + subjectName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("GradeHunter/data.txt", true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertSubjectToString(int subject) {
        return switch (subject) {
            case 1 -> "국어";
            case 2 -> "수학";
            case 3 -> "영어";
            case 4 -> "지리";
            case 5 -> "역사";
            case 6 -> "과학";
            default -> "알 수 없음";
        };
    }

}
