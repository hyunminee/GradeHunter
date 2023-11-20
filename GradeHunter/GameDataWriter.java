package GradeHunter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/***
 * 게임 데이터를 텍스트파일에 저장하는 클래스입니다.
 * @author 김봄
 */
public class GameDataWriter {

    private String filePath;

    public GameDataWriter(String filePath) {
        this.filePath = filePath;
    }

    public void updateGameData(String gameId, String newTime, String subject) throws IOException {
        // 기존 데이터를 맵에 저장
        Map<String, String> dataMap = readExistingData();

        // 동일한 ID가 있고 새 시간이 더 짧은 경우에만 업데이트
        if (dataMap.containsKey(gameId)) {
            String[] existingData = dataMap.get(gameId).split("\t");
            if (isShorterTime(newTime, existingData[0])) {
                dataMap.put(gameId, newTime + "\t" + subject);
            }
        } else {
            // 동일한 ID가 없는 경우 새로운 데이터 추가
            dataMap.put(gameId, newTime + "\t" + subject);
        }

        writeAllData(dataMap);
    }

    private Map<String, String> readExistingData() throws IOException {
        Map<String, String> dataMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    dataMap.put(parts[0], parts[1] + "\t" + parts[2]);
                }
            }
        }
        return dataMap;
    }

    private void writeAllData(Map<String, String> dataMap) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                writer.write(entry.getKey() + "\t" + entry.getValue());
                writer.newLine();
            }
        }
    }


    private boolean isShorterTime(String newTime, String existingTime) {
        return newTime.compareTo(existingTime) < 0;
    }

    public static void main(String[] args) throws IOException {
        // 테스트를 위한 메인 메소드
        GameDataWriter writer = new GameDataWriter("data.txt");
        writer.updateGameData("111111", "12:24:42", "과학");
        writer.updateGameData("111111", "11:24:42", "과학"); // 더 짧은 시간으로 업데이트
        // 파일에 데이터가 기록됩니다.
    }
}
