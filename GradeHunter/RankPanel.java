package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;


/**
 * 게임 랭킹 전체를 담당하는 클래스
 * @author 김봄
 */
public class RankPanel extends JPanel {

    private Image backgroundImage;
    private MainPanel mainPanel;
    private Font customFont; // 사용자 정의 폰트 변수 추가

    public RankPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(null); // 널 레이아웃 사용
        setBackground(Color.BLACK); // 배경색 설정

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage("images/Gradcap.png");
        mainPanel.setIconImage(img);

        // 폰트 로드
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("THE_Syabeteu.ttf"))
                    .deriveFont(28f); // BOLD 스타일과 30 픽셀 크기로 설정
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("Malgun Gothic", Font.BOLD, 30); // 로드 실패 시 대체 폰트 사용
        }


        // 배경 이미지 로드
        backgroundImage = new ImageIcon("images/bg_rank.png").getImage();
        setBounds(0, 0, mainPanel.getWidth(), mainPanel.getHeight());

        ImageIcon imgIcon = new ImageIcon("images/bt_home.png");
        JButton button = new JButton(imgIcon);
        button.setBounds(40, 35, imgIcon.getIconWidth(), imgIcon.getIconHeight());
        add(button);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);



        ImageIcon imgIconEntered = new ImageIcon("images/bt_home_entered.png");

        // 버튼 위에 마우스가 올라갔을 때 커서를 손가락 모양으로 변경
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // 마우스 호버 시 버튼 이미지 변경
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(imgIconEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(imgIcon);
            }
        });

        button.addActionListener(e -> {
            // MainPanel에서 값 초기화 후 전환
            this.mainPanel.resetValues();
            // MainPanel의 메인 콘텐츠 패널로 돌아가기
            this.mainPanel.switchPanel(this.mainPanel.getMainContentPanel());
        });



        SortedDataFileReader fileReader = new SortedDataFileReader();
        List<SortedDataFileReader.Record> records = fileReader.readFileAndSort("data.txt");

        // 랭킹 데이터 표시
        // 랭킹 데이터 화면에 표시
        int yPosition = 203; // 첫 번째 랭킹 레이블의 시작 y 좌표
        int xPositionId = 168; // 학번 레이블의 시작 x 좌표
        int xPositionTime = xPositionId + 265; // 클리어 시간 레이블의 시작 x 좌표
        int xPositionSubject = xPositionTime + 110; // 과목명 레이블의 시작 x 좌표
        int labelHeight = 100; // 레이블의 높이
        int labelWidthId = 200; // 학번 레이블의 너비
        int labelWidthTime = 200; // 클리어 시간 레이블의 너비
        int labelWidthSubject = 300; // 과목명 레이블의 너비

        for (int i = 0; i < Math.min(records.size(), 10); i++) {
            SortedDataFileReader.Record record = records.get(i);

            // 학번 레이블
            JLabel idLabel = new JLabel(record.id, SwingConstants.CENTER); // 가운데 정렬로 변경
            idLabel.setForeground(Color.WHITE);
            idLabel.setFont(customFont); // 폰트 설정을 customFont로 변경
            idLabel.setBounds(xPositionId, yPosition, labelWidthId, labelHeight);
            add(idLabel);

            // 클리어 시간을 표시하는 레이블 설정
            JLabel timeLabel = new JLabel(record.time, SwingConstants.CENTER);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setFont(customFont);
            timeLabel.setBounds(xPositionTime, yPosition, labelWidthTime, labelHeight);
            add(timeLabel);


            // 과목명 레이블
            JLabel subjectLabel = new JLabel(record.subject, SwingConstants.RIGHT);
            subjectLabel.setForeground(Color.WHITE);
            subjectLabel.setFont(customFont); // 폰트 설정을 customFont로 변경
            subjectLabel.setBounds(xPositionSubject, yPosition, labelWidthSubject, labelHeight);
            add(subjectLabel);

            yPosition += 34; // 다음 레코드의 y 위치 증가
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private class Record implements Comparable<Record> {
        String id;
        String time;
        String subject;

        public Record(String id, String time, String subject) {
            this.id = id;
            this.time = time;
            this.subject = subject;
        }

        @Override
        public int compareTo(Record other) {
            return this.time.compareTo(other.time);
        }


    }

    private class SortedDataFileReader {
        public List<Record> readFileAndSort(String filePath) {
            Map<String, Record> fastestRecords = new HashMap<>();
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts.length >= 3) {
                        String id = parts[0];
                        String time = parts[1];
                        String subject = parts[2];
                        Record newRecord = new Record(id, time, subject);

                        // 최소 시간으로 레코드 업데이트
                        fastestRecords.compute(id, (k, v) -> (v == null || newRecord.compareTo(v) < 0) ? newRecord : v);
                    }
                }

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

            List<Record> sortedRecords = new ArrayList<>(fastestRecords.values());
            sortedRecords.sort(Comparator.comparing(r -> r.time));
            saveSortedDataToFile(sortedRecords, filePath);

            return sortedRecords;
        }

        // 정렬된 데이터를 파일에 저장하는 메서드
        private void saveSortedDataToFile(List<Record> records, String filePath) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
                for (Record record : records) {
                    writer.write(record.id + " " + record.time + " " + record.subject);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private class Record implements Comparable<Record> {
            String id;
            String time;
            String subject;

            public Record(String id, String time, String subject) {
                this.id = id;
                this.time = time;
                this.subject = subject;
            }

            @Override
            public int compareTo(Record other) {
                return this.time.compareTo(other.time);
            }
        }
    }



}