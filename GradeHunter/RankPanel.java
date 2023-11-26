package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/***
 * 게임 랭킹 전체를 담당하는 클래스입니다.
 * @author 김봄
 */

public class RankPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Grade Hunter");
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Toolkit kit = Toolkit.getDefaultToolkit();

        Dimension screenSize = kit.getScreenSize();

        Image img = kit.getImage("images\\Gradcap.png");
        frame.setIconImage(img);

        ImagePanel imgpanel = new ImagePanel(new ImageIcon("images\\bg_rank.png").getImage());
        frame.setContentPane(imgpanel);

        ImageIcon imgIcon = new ImageIcon("images\\bt_home.png");
        JButton button = new JButton(imgIcon);
        button.setBounds(40, 35, imgIcon.getIconWidth(), imgIcon.getIconHeight());
        imgpanel.add(button);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        SortedDataFileReader fileReader = new SortedDataFileReader();
        List<SortedDataFileReader.Record> records = fileReader.readFileAndSort("data.txt");



// 랭킹 데이터 표시
        int yPosition = 60; // 랭킹 시작 위치 조정
        for (int i = 0; i < Math.min(records.size(), 10); i++) {
            //String formattedRank = (i + 1 < 10) ? "0" + (i + 1) : Integer.toString(i + 1); // 한 자리 숫자에 공백 추가
            JLabel rankingLabel = new JLabel("            " + records.get(i).toString(), SwingConstants.LEFT);
            rankingLabel.setForeground(Color.WHITE);
            rankingLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 23));
            rankingLabel.setBounds(130, yPosition, 1000, 378); // y
            yPosition += 34; // 다음 레코드의 y 위치 증가 위치와 높이 조정
            imgpanel.add(rankingLabel);
        }

        frame.setVisible(true);
    }
}

class SortedDataFileReader {

    static class Record {
        String id;
        String time;
        String subject;

        public Record(String id, String time, String subject) {
            this.id = id;
            this.time = time;
            this.subject = subject;
        }

        @Override
        public String toString() {
            return id + "                       " + time + "                           " + subject;
        }
    }

    public List<Record> readFileAndSort(String filePath) {
        List<Record> records = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    records.add(new Record(parts[0], parts[1], parts[2]));
                }
            }

            Collections.sort(records, Comparator.comparing(r -> r.time));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return records; // 정렬된 레코드 목록을 반환합니다.
    }
}
