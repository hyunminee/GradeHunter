package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankPanel {

    public static void showRankPanel() {
        JFrame frame = new JFrame("Grade Hunter");
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage("images/Gradcap.png");
        frame.setIconImage(img);

        ImagePanel imgPanel = new ImagePanel(new ImageIcon("images/bg_rank.png").getImage());
        frame.setContentPane(imgPanel);
/////asdfsadasfa
        // ... 기타 버튼 및 컴포넌트 초기화 코드 ...

        SortedDataFileReader fileReader = new SortedDataFileReader();
        List<SortedDataFileReader.Record> records = fileReader.readFileAndSort("data.txt");

        // ... 랭킹 데이터 표시 코드 ...

        frame.setVisible(true);
    }

    static class SortedDataFileReader {
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
                return id + " " + time + " " + subject;
            }
        }

        public List<Record> readFileAndSort(String filePath) {
            List<Record> records = new ArrayList<>();
            // ... 파일 읽기 및 정렬 로직 ...

            return records;
        }
    }

    static class ImagePanel extends JPanel {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // ... 기타 메서드 및 클래스 정의 ...
}
