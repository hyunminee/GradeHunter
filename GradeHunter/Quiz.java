package GradeHunter;

/**
 * 각 퀴즈 분야에 해당하는 기능을 모아놓은 인터페이스입니다.
 * @author 서보경 정서윤
 */

public interface Quiz {
    void setupUI();
    void delayImageAndShowNextQuiz(String imagePath, int delayMillis);
    void startTimer();
    void checkAnswer(String userAnswer);
}
