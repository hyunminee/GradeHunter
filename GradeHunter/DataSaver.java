package GradeHunter;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * GradeHunter 게임의 게임 결과 데이터를 저장하는 클래스
 * <p>이 클래스는 파일에 결과를 기록하는 역할을 합니다.</p>
 */
public class DataSaver {

    /**
     * 게임 결과를 파일에 저장하는 메소드
     * <p>학생의 ID, 과목 번호, 총 걸린 시간을 받아 파일에 저장합니다.</p>
     *
     * @param studentID 학생의 식별 번호
     * @param subject   과목 번호
     * @param totalTime 게임을 클리어하는 데 걸린 총 시간
     */
    public void saveGameResult(String studentID, int subject, String totalTime) {
        // 과목 번호를 문자열 표현으로 변환합니다.
        String subjectName = convertSubjectToString(subject);
        // 기록 문자열을 생성합니다. 형식: studentID totalTime subjectName
        String record = studentID + " " + totalTime + " " + subjectName;

        // Try-with-resources를 사용하여 작성자가 사용 후 닫히도록 합니다.
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.txt", true), StandardCharsets.UTF_8))) {
            writer.write(record);  // 파일에 기록을 작성합니다.
            writer.newLine();  // 다음 기록을 위해 새 줄을 추가합니다.
            } catch(IOException e){
                e.printStackTrace(); // IOException이 발생하면 오류를 출력합니다.
            }
        }

        /**
         * 정수로 된 과목 코드를 문자열로 변환하는 메소드
         * <p> 각 과목 코드에 해당하는 과목 이름을 문자열로 반환합니다. </p>
         * @param subject 과목 코드
         * @return 해당 과목 코드에 따른 과목명
         */
        public String convertSubjectToString (int subject ){
            // 과목 코드에 따라 과목 이름을 매핑하는 switch 문입니다.
            return switch (subject) {
                case 1 -> "국어";  // 1번 경우: 국어
                case 2 -> "수학";  // 2번 경우: 수학
                case 3 -> "영어";  // 3번 경우: 영어
                case 4 -> "지리";  // 4번 경우: 지리
                case 5 -> "역사";  // 5번 경우: 역사
                case 6 -> "과학";  // 6번 경우: 과학
                default -> "알 수 없음"; // 기본 경우: 알 수 없음
            };
        }

    }
}