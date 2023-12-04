package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 퀴즈 분야 - 영어 를 담당하는 클래스
 * @author 서보경
 * @author 정서윤
 */

public class Quiz_3 extends JPanel implements Quiz{

    // 퀴즈 이미지 출력 관련 변수
    private List<QuizItem> quizzes;
    private int currentQuizIndex;
    private JLabel imageLabel;

    // 타이머 관련 변수
    private Timer timer;
    private JLabel timerLabel;
    private int remainingSeconds = 10;

    // 텍스트 필드 및 정답 개수 카운트
    private JTextField answerField;
    private int cnt = 0;

    // o/x 판단 팝업
    public ImageIcon oImage = new ImageIcon("images/o.png");
    public ImageIcon xImage = new ImageIcon("images/x.png");
    public JLabel oPopup = new JLabel(oImage);
    public JLabel xPopup = new JLabel(xImage);

    private MainPanel mainPanel;

    /**
     * Quiz_3 생성자 함수 : 퀴즈 이미지 리스트 초기화, UI 구성, 퀴즈 이미지 출력
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     */

    public Quiz_3(MainPanel mainPanel) {

        this.mainPanel = mainPanel;

        quizzes = initializeQuizzes();
        currentQuizIndex = 0;

        setupUI();
        showNextQuiz();
    }

    /**
     * 퀴즈 이미지 10개를 리스트에 추가하고, 랜덤으로 5개를 선택하는 메소드
     * @return 선택된 5개의 이미지가 담은 리스트
     */

    private List<QuizItem> initializeQuizzes() {
        List<QuizItem> quizzes = new ArrayList<>();
        quizzes.add(new QuizItem("images/quiz_3/1.png", "1"));
        quizzes.add(new QuizItem("images/quiz_3/2.png", "1"));
        quizzes.add(new QuizItem("images/quiz_3/3.png", "1"));
        quizzes.add(new QuizItem("images/quiz_3/4.png", "2"));
        quizzes.add(new QuizItem("images/quiz_3/5.png", "3"));
        quizzes.add(new QuizItem("images/quiz_3/6.png", "2"));
        quizzes.add(new QuizItem("images/quiz_3/7.png", "3"));
        quizzes.add(new QuizItem("images/quiz_3/8.png", "3"));
        quizzes.add(new QuizItem("images/quiz_3/9.png", "2"));
        quizzes.add(new QuizItem("images/quiz_3/10.png", "1"));

        // 랜덤으로 5개 선택
        Collections.shuffle(quizzes);
        return quizzes.subList(0,5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setupUI() {

        // O,X 팝업 (여기 위치여야만 화면에 보임)
        oPopup.setBounds(320, 150, 400, 400);
        oPopup.setVisible(false);
        add(oPopup);
        xPopup.setBounds(320, 150, 400, 400);
        xPopup.setVisible(false);
        add(xPopup);

// =============================================================================================================
        setLayout(new BorderLayout());

        // 퀴즈 이미지 패널
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imageLabel = new JLabel();
        QuizItem firstQuiz = quizzes.get(0);
        imageLabel.setIcon(new ImageIcon(firstQuiz.getImagePath()));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imagePanel, BorderLayout.NORTH);
        imagePanel.add(imageLabel, BorderLayout.NORTH);

// =============================================================================================================
        // 정답 입력받는 텍스트 필드
        answerField = new JTextField();
        answerField.setBounds(450, 560, 200, 50);
        answerField.setOpaque(false);
        answerField.setBorder(null);
        answerField.setForeground(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 50);
        answerField.setFont(font);
        imageLabel.add(answerField);

        // KeyListener 추가
        answerField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c != '1' && c != '2' && c != '3') {
                    e.consume(); // 1, 2, 3 이외의 입력은 무시
                }
                // 현재 입력된 텍스트의 길이가 1을 넘어가면 입력 무시
                if (answerField.getText().length() >= 1) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!answerField.getText().isEmpty()) {
                        checkAnswerAndShowNextQuiz();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // 필요 없음
            }
        });

// =============================================================================================================
        // 타이머 구현
        timerLabel = new JLabel("10"); // 초기값은 10초
        timerLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timerLabel.setForeground(Color.WHITE); // 텍스트 색상을 흰색으로 설정
        timerLabel.setBounds(980, 30, 100, 100);
        imageLabel.add(timerLabel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;
                updateTimerLabel();

                if (remainingSeconds == 0) {  // 남은 시간이 0이면, 자동으로 정답 체크 후, 다음 문제 출력
                    checkAnswerAndShowNextQuiz();
                }
            }
        });
        timer.setRepeats(true);

        startTimer(); // 시작 시 타이머 동작
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void startTimer() {
        timer.stop();
        timer.setInitialDelay(0);
        timer.restart();
        timer.setDelay(1000);
        remainingSeconds = 10;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void updateTimerLabel() {
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(Integer.toString(remainingSeconds)); // 초 단위로 표시
        });
    }

    /**
     * {@inheritDoc}
     * @param imagePath List로부터의 이미지 경로
     */
    @Override
    public void displayImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        imageLabel.setIcon(new ImageIcon(image));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showNextQuiz() {
        // quiz index가 리스트 크기보다 작다면 다음 이미지를 출력, 그렇지 않다면 퀴즈 정답 개수에 따른 화면 전환
        if (currentQuizIndex < quizzes.size()) {
            QuizItem quiz = quizzes.get(currentQuizIndex);
            timer.stop();
            displayImage(quiz.getImagePath());
            answerField.setEnabled(true); // 텍스트 필드 활성화
            answerField.setText(""); // 텍스트 필드 비우기
            answerField.requestFocusInWindow(); // 텍스트 필드에 포커스 주기
            startTimer(); // 다음 퀴즈를 보여주기 전에 타이머 재시작
            currentQuizIndex++;
        } else {
            String result = String.valueOf(cnt);
            if(cnt >= 3) {  // 정답개수 3개 이상이면, 논문 통과 출력 후, ending 변수 1에 따른 패널 전환, 랭킹 데이터 기록
                JOptionPane.showMessageDialog(this, "논문 통과! \n 결과 : " + result + " / 5");
                MainPanel.ending = 1;
                DataSaver dataSaver = new DataSaver();// DataSaver클래스 생성
                dataSaver.saveGameResult(MainPanel.savedText, MainPanel.subject, GameLogic.totalTime);// 학번, 총 클리어시간, 과목 명 저장

            }
            else {  // 미만이면 ending 변수 0으로 설정
                JOptionPane.showMessageDialog(this, "논문 실패! \n 결과 : " + result + " / 5");
                MainPanel.ending = 0;
            }
            SwingUtilities.invokeLater(() -> {
                EndingPanel endingPanel = new EndingPanel(mainPanel);
                mainPanel.switchPanel(endingPanel);
                endingPanel.setVisible(true);
            });
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void checkAnswerAndShowNextQuiz() {
        timer.stop();
        checkAnswer(answerField.getText());
        answerField.setEnabled(false); // 텍스트 필드 비활성화

        // 다음 문제로 넘어가기 전 팝업창에 맞춰 2초간 딜레이
        Timer delayTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextQuiz();
                answerField.requestFocusInWindow();
            }
        });
        delayTimer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
        delayTimer.start();
    }

    /**
     * {@inheritDoc}
     * @param userAnswer 텍스트 필드에 입력받은 값
     */

    @Override
    public void checkAnswer(String userAnswer) {
        QuizItem quizItem = quizzes.get(currentQuizIndex - 1);
        String correctAnswer = quizItem.getAnswer();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {  // user입력값과 quiz이미지에 저장된 값이 같은 경우 (맞은 경우)
            oPopup.setVisible(true);  // o 팝업 눈에 보이게 활성화
            cnt += 1;  // 정답 개수 카운팅
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    oPopup.setVisible(false);  // 딜레이 시간 후, 비활성화
                }
            });
            timer.setRepeats(false);
            timer.start();

        } else {  // 틀린 경우
            xPopup.setVisible(true);  // x 팝업 눈에 보이게 활성화
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xPopup.setVisible(false);  // 딜레이 시간 후, 비활성화
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}
