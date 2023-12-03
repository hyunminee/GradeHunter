package GradeHunter;

/**
 * 퀴즈에 동일하게 해당하는 기능을 모아놓은 인터페이스
 * @author 서보경
 * @author 정서윤
 */

public interface Quiz {

    /**
     *  팝업, 이미지 패널, 답을 입력하는 텍스트 필드, 타이머 라벨 UI 배치하는 메소드
     */
    void setupUI();

    /**
     * 타이머의 리셋 등 타이머를 조절하는 메소드
     */
    void startTimer();

    /**
     * UI에 그려지는 타이머를 업데이트 하는 메소드
     */
    void updateTimerLabel();

    /**
     * 랜덤으로 선택된 이미지 경로를 parameter로 받아 이미지를 보여주는 메소드
     * @param imagePath List로부터의 이미지 경로
     */
    void displayImage(String imagePath);

    /**
     * 다음 퀴즈 이미지를 준비하는 메소드
     */
    void showNextQuiz();

    /**
     * 답을 확인하는 메소드
     * @param userAnswer 텍스트 필드에 입력받은 값
     */
    void checkAnswer(String userAnswer);

    /**
     * 답을 확인하고, 다음 퀴즈 이미지를 보여주는 메소드를 동시에 관리하는 메소드
     */
    void checkAnswerAndShowNextQuiz();


}
