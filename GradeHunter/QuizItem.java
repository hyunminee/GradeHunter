package GradeHunter;

/**
 * 퀴즈 이미지를 관리하는 클래스
 * @author 서보경
 */
public class QuizItem{
    public String imagePath;
    public String answer;

    public QuizItem(String imagePath, String answer) {
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
