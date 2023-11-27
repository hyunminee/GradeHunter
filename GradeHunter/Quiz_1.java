package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Quiz_1 extends JPanel implements Quiz{

    private List<TestItem> quizzes;
    private int currentQuizIndex;
    private JLabel imageLabel;
    private JTextField answerField;
    private Timer timer;
    private final JFrame quiz_1f;
    private Image backgroundImage;

    private JLabel timerLabel;

    public Quiz_1(JFrame frame) {

        this.quiz_1f = frame;
        quizzes = initializeQuizzes();
        currentQuizIndex = 0;

        quiz_1f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quiz_1f.setSize(1080, 720);

        setupUI();
        showNextQuiz();
    }

    private List<TestItem> initializeQuizzes() {
        List<TestItem> quizzes = new ArrayList<>();
        quizzes.add(new TestItem("images/quiz_4/1.png", "1"));
        quizzes.add(new TestItem("images/quiz_4/2.png", "1"));
        quizzes.add(new TestItem("images/quiz_4/3.png", "1"));
        quizzes.add(new TestItem("images/quiz_4/4.png", "2"));
        quizzes.add(new TestItem("images/quiz_4/5.png", "2"));
        quizzes.add(new TestItem("images/quiz_4/6.png", "2"));
        quizzes.add(new TestItem("images/quiz_4/7.png", "3"));
        quizzes.add(new TestItem("images/quiz_4/8.png", "3"));
        quizzes.add(new TestItem("images/quiz_4/9.png", "3"));
        quizzes.add(new TestItem("images/quiz_4/10.png", "3"));
        return quizzes;
    }

    @Override
    public void setupUI() {
        setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel();
        imageLabel = new JLabel();
        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.CENTER);

        answerField = new JTextField();
        add(answerField, BorderLayout.SOUTH);

        timerLabel = new JLabel("10"); // 초기값은 10초
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.WHITE); // 텍스트 색상을 흰색으로 설정
        timerLabel.setBounds(1000,20,timerLabel.getWidth(), timerLabel.getHeight());
        add(timerLabel, BorderLayout.NORTH);

        timer = new Timer(10000, new ActionListener() {
            int remainingSeconds = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;
                updateTimerLabel();

                if (remainingSeconds <= 0) {
                    timer.stop();
                    checkAnswer(answerField.getText());
                    showNextQuiz();
                }
            }
        });
        timer.setRepeats(true);

        startTimer(); // 시작 시 타이머 동작
    }

    // 타이머 레이블을 업데이트하는 메서드
    private void updateTimerLabel() {
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(Integer.toString(timer.getDelay() / 1000));
        });
    }

    private void displayImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(Main.class.getResource(imagePath));
        Image image = imageIcon.getImage();
        imageLabel.setIcon(new ImageIcon(image));
    }

    @Override
    public void delayImageAndShowNextQuiz(String imagePath, int delayMillis) {

        displayImage(imagePath);
        Timer delayTimer = new Timer(delayMillis, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                checkAnswer(answerField.getText());
                showNextQuiz();
            }
        });

        delayTimer.setRepeats(false);
        delayTimer.start();
    }

    public void showNextQuiz() {
        if (currentQuizIndex < quizzes.size()) {
            TestItem quiz = quizzes.get(currentQuizIndex);
            delayImageAndShowNextQuiz(quiz.getImagePath(), 10000);
            answerField.setText("");
            currentQuizIndex++;
            startTimer();
        } else {
            JOptionPane.showMessageDialog(this, "게임 종료!");
            System.exit(0);
        }
    }

    public void startTimer() {
        timer.restart();
    }

    public void checkAnswer(String userAnswer) {
        TestItem testItem = quizzes.get(currentQuizIndex - 1);
        String correctAnswer = testItem.getAnswer();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "정답입니다!");
        } else {
            JOptionPane.showMessageDialog(this, "틀렸습니다. 정답은 " + correctAnswer + "입니다.");
        }
    }

}

class TestItem {
    private String imagePath;
    private String answer;

    public TestItem(String imagePath, String answer) {
        this.imagePath = imagePath;
        this.answer = answer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAnswer() {
        return answer;
    }
}


