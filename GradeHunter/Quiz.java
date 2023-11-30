package GradeHunter;

/**
 * 각 퀴즈 분야에 해당하는 기능을 모아놓은 인터페이스입니다.
 * @author 서보경 정서윤
 */

public interface Quiz {
    void setupUI();
    void updateTimerLabel();
    void showNextQuiz();
    void displayImage(String imagePath);
    void startTimer();
    void checkAnswerAndShowNextQuiz();
    void checkAnswer(String userAnswer);
}
