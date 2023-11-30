package GradeHunter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 퀴즈 분야 - 국어 를 담당하는 클래스입니다.
 * @author 서보경
 */

public class Quiz_1 extends JPanel{

    private List<QuizItem> quizzes;
    private int currentQuizIndex;
    private JLabel imageLabel;
    private JTextField answerField;
    private Timer timer;
    private JLabel timerLabel;
    private int remainingSeconds = 10;

    public Quiz_1(MainPanel mainPanel) {

        quizzes = initializeQuizzes();
        currentQuizIndex = 0;

        setupUI();
        showNextQuiz();
    }

    private List<QuizItem> initializeQuizzes() {
        List<QuizItem> quizzes = new ArrayList<>();
        quizzes.add(new QuizItem("images/quiz_1/1.png", "3", 1080,720));
        quizzes.add(new QuizItem("images/quiz_1/2.png", "2",1080,720));
        quizzes.add(new QuizItem("images/quiz_1/3.png", "2",1080,720));
        quizzes.add(new QuizItem("images/quiz_1/4.png", "3",1080,720));
        quizzes.add(new QuizItem("images/quiz_1/5.png", "3",1080,720));
        quizzes.add(new QuizItem("images/quiz_1/6.png", "2",1080,720));
        quizzes.add(new QuizItem("images/quiz_1/7.png", "3",1080,720));
        quizzes.add(new QuizItem("images/quiz_1/8.png", "1",1080,720));
        quizzes.add(new QuizItem("images/quiz_1/9.png", "1",1080,720));
        quizzes.add(new QuizItem("images/quiz_1/10.png", "1",1080,720));

        // 랜덤으로 5개 선택
        Collections.shuffle(quizzes);
        return quizzes.subList(0,5);
    }


    public void setupUI() {
        setLayout(null); // 널 레이아웃 사용

        JPanel imagePanel = new JPanel();
        imageLabel = new JLabel();
        QuizItem firstQuiz = quizzes.get(0);
        imagePanel.setBounds(0,0, firstQuiz.getImageWidth(), firstQuiz.getImageHeight());
        imageLabel.setBounds(0,0, firstQuiz.getImageWidth(), firstQuiz.getImageHeight());
        add(imagePanel);
        imagePanel.add(imageLabel);


        answerField = new JTextField();
        answerField.setBounds(450,560,200,50);
        answerField.setOpaque(false);
        answerField.setBorder(null);
        answerField.setForeground(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 50);
        answerField.setFont(font);
        imageLabel.add(answerField);
        answerField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 엔터 키가 눌렸을 때의 동작
                if (!answerField.getText().isEmpty()) {
                    timer.stop();
                    checkAnswer(answerField.getText());
                    showNextQuiz();
                }
            }
        });

        timerLabel = new JLabel("10"); // 초기값은 10초
        timerLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timerLabel.setForeground(Color.WHITE); // 텍스트 색상을 흰색으로 설정
        timerLabel.setBounds(960,40,100, 100);
        imageLabel.add(timerLabel);

        timer = new Timer(10000, new ActionListener() {

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
            timerLabel.setText(Integer.toString(remainingSeconds)); // 초 단위로 표시
        });
    }

    private void displayImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(Main.class.getResource(imagePath));
        Image image = imageIcon.getImage();
        imageLabel.setIcon(new ImageIcon(image));
    }


    public void delayImageAndShowNextQuiz(String imagePath, int delayMillis) {

        //timer.stop();

        displayImage(imagePath);
        Timer delayTimer = new Timer(delayMillis, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //timer.stop();
                checkAnswer(answerField.getText());
                showNextQuiz();
            }
        });

        delayTimer.setRepeats(false);
        delayTimer.start();
    }

    public void showNextQuiz() {
        if (currentQuizIndex < quizzes.size()) {
            QuizItem quiz = quizzes.get(currentQuizIndex);
            timer.stop();
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
        timer.stop();
        timer.setInitialDelay(0);
        timer.restart();
        timer.setDelay(1000);
        remainingSeconds = 10;
    }

    public void checkAnswer(String userAnswer) {
        QuizItem quizItem = quizzes.get(currentQuizIndex - 1);
        String correctAnswer = quizItem.getAnswer();

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "정답입니다!");
        } else {
            JOptionPane.showMessageDialog(this, "틀렸습니다. 정답은 " + correctAnswer + "입니다.");
        }
    }

}


