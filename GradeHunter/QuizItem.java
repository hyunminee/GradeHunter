package GradeHunter;

public class QuizItem{
    public String imagePath;
    public String answer;

    public int imageWidth;
    public int imageHeight;

    public QuizItem(String imagePath, String answer, int imageWidth, int imageHeight) {
        this.imagePath = imagePath;
        this.answer = answer;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }
    public String getImagePath() {
        return imagePath;
    }

    public String getAnswer() {
        return answer;
    }

    public int getImageWidth(){
        return imageWidth;
    }
    public int getImageHeight(){
        return imageHeight;
    }
}
