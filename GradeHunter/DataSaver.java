package GradeHunter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * GradeHunter 게임의 게임 결과 데이터를 저장하는 클래스
 * @author 김봄
 */


public class DataSaver {

    /**
     * 게임 결과를 파일에 저장하는 메소드 - 학생의 ID, 과목 번호, 총 걸린 시간을 받아 파일에 저장
     * @param studentID 학번
     * @param subject 과목
     * @param totalTime 총 클리어 시간
     */


    public void saveGameResult(String studentID, int subject, String totalTime) {
        String subjectName = convertSubjectToString(subject);
        String record = studentID + " " + totalTime + " " + subjectName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 정수로 된 과목 코드를 문자열로 변환하는 메소드
     * <p> 각 과목 코드에 해당하는 과목 이름을 문자열로 반환합니다. </p>
     * @param subject 과목
     * @return 각 퀴즈 숫자에 따른 과목명 반환
     */

    public String convertSubjectToString(int subject) {
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
