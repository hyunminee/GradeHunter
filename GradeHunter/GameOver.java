package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * 제한 시간 내에 게이지바를 채우지 못했을 시 나타나는 GameOver 클래스입니다.
 * @author 정서윤
 */


public class GameOver extends JPanel{

    // 이미지 아이콘 로드
    public ImageIcon stage1Image = new ImageIcon(GameOver.class.getResource("images/stage/s_1.png"));
    public ImageIcon stage2Image = new ImageIcon(GameOver.class.getResource("images/stage/s_2.png"));
    public ImageIcon stage3Image = new ImageIcon(GameOver.class.getResource("images/stage/s_3.png"));
    public ImageIcon stage4Image = new ImageIcon(GameOver.class.getResource("images/stage/s_4.png"));
    public ImageIcon stage5Image = new ImageIcon(GameOver.class.getResource("images/stage/s_5.png"));
    public ImageIcon stage6Image = new ImageIcon(GameOver.class.getResource("images/stage/s_6.png"));
    public ImageIcon stage7Image = new ImageIcon(GameOver.class.getResource("images/stage/s_7.png"));
    public ImageIcon stage8Image = new ImageIcon(GameOver.class.getResource("images/stage/s_8.png"));
    public ImageIcon gradeAImage = new ImageIcon(GameOver.class.getResource("images/grade/g_a.png"));
    public ImageIcon gradeBImage = new ImageIcon(GameOver.class.getResource("images/grade/g_b.png"));
    public ImageIcon gradeCImage = new ImageIcon(GameOver.class.getResource("images/grade/g_c.png"));
    public ImageIcon gradeDImage = new ImageIcon(GameOver.class.getResource("images/grade/g_d.png"));
    public ImageIcon gradeFImage = new ImageIcon(GameOver.class.getResource("images/grade/g_f.png"));
    public ImageIcon gameoverImage = new ImageIcon(GameOver.class.getResource("images/gameover.png"));
    public ImageIcon mainButtonImage = new ImageIcon(GameOver.class.getResource("images/bt_main.png"));
    public ImageIcon restartButtonImage = new ImageIcon(GameOver.class.getResource("images/bt_restart.png"));
    public ImageIcon mainButtonEnteredImage = new ImageIcon(GameOver.class.getResource("images/bt_main_entered.png"));
    public ImageIcon restartButtonEnteredImage = new ImageIcon(GameOver.class.getResource("images/bt_restart_entered.png"));
    public ImageIcon stageImage;
    public ImageIcon gradeImage;

    public GameOver(MainPanel mainPanel){

        /**
         * GameOver 생성자 함수 : 스테이지와 등급에 맞는 이미지 출력
         * <p>
         *     GameLogic 클래스에서 currentStage와 guageValue, maxGaugeValue를 받아와 게임오버된 스테이지와 등급에 맞는 이미지를 출력한다.
         *     처음으로, 다시하기 버튼로 사용자의 입력을 받아 그에 맞는 패널로 전환한다.
         * </p>
         * @param mainPanel switchPanel()을 사용하기 위한 parameter
         */

        setLayout(null);    // 레이아웃 관리자를 사용하지 않음
        setBackground(Color.BLACK);

        // 값에 맞는 stageImage와 gradeImage 지정
        checkStage();
        checkGrade();

        //gameoverimage 출력
        JLabel gameoverimage = new JLabel(gameoverImage);
        gameoverimage.setBounds(50, 460, gameoverImage.getIconWidth(), gameoverImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(gameoverimage);


// =============================================================================================================
        // 처음으로 선택 버튼
        JButton mainbutton = new JButton(mainButtonImage);
        mainbutton.setContentAreaFilled(false); // 배경을 투명하게 설정
        mainbutton.setBorderPainted(false); // 테두리를 그리지 않도록 설정
        mainbutton.setFocusPainted(false); // 포커스 테두리를 그리지 않도록 설정
        mainbutton.setBounds(640, 460, mainButtonImage.getIconWidth(), mainButtonImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        mainbutton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                mainbutton.setIcon(mainButtonEnteredImage);
                mainbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));   // 커서를 손 모양으로 변경
            }
            @Override
            public void mouseExited(MouseEvent e){
                mainbutton.setIcon(mainButtonImage);
                mainbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // MainPanel의 값 초기화 후 전환
                mainPanel.resetValues();
                // MainPanel의 메인 콘텐츠 패널로 돌아가기
                mainPanel.switchPanel(mainPanel.getMainContentPanel());
            }
        });
        add(mainbutton);

// =============================================================================================================
        // 다시하기 선택 버튼
        JButton restartbutton = new JButton(restartButtonImage);
        restartbutton.setContentAreaFilled(false); // 배경을 투명하게 설정
        restartbutton.setBorderPainted(false); // 테두리를 그리지 않도록 설정
        restartbutton.setFocusPainted(false); // 포커스 테두리를 그리지 않도록 설정
        restartbutton.setBounds(640, 550, restartButtonImage.getIconWidth(), restartButtonImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        restartbutton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                restartbutton.setIcon(restartButtonEnteredImage);
                restartbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));   // 커서를 손 모양으로 변경
            }
            @Override
            public void mouseExited(MouseEvent e){
                restartbutton.setIcon(restartButtonImage);
                restartbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                GameLogic.currentStage = 1;
                GameLogic.gaugeValue = 0; // 게이지 값을 초기화합니다.
                GameLogic.maxGaugeValue = GameLogic.currentStage * GameLogic.GAUGE_PER_STAGE;
                GamePlayPanel gameplayPanel = new GamePlayPanel(mainPanel); // GamePlayPanel의 새 인스턴스를 생성
                mainPanel.switchPanel(gameplayPanel);
            }
        });
        add(restartbutton);
    }


    /**
     * GameLogic의 currentStage 값을 받아와 그에 맞는 stageImage를 지정하는 메소드
     */
    public void checkStage() {
        if (GameLogic.currentStage == 1)
            stageImage = stage1Image;
        else if (GameLogic.currentStage == 2)
            stageImage = stage2Image;
        else if (GameLogic.currentStage == 3)
            stageImage = stage3Image;
        else if (GameLogic.currentStage == 4)
            stageImage = stage4Image;
        else if (GameLogic.currentStage == 5)
            stageImage = stage5Image;
        else if (GameLogic.currentStage == 6)
            stageImage = stage6Image;
        else if (GameLogic.currentStage == 7)
            stageImage = stage7Image;
        else if (GameLogic.currentStage == 8)
            stageImage = stage8Image;
        else
            return;

        JLabel stageimage = new JLabel(stageImage);
        stageimage.setBounds(325, 125, stageImage.getIconWidth(), stageImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(stageimage);
    }


    /**
     * GameLogic의 guageValue와 maxGaugeValue를 받아와 등급을 산출한 뒤 그에 맞는 gradeImage를 지정하는 메소드
     */
    public void checkGrade() {
        int gradeScore = (GameLogic.gaugeValue * 100) / GameLogic.maxGaugeValue;
        System.out.println("GradeScore = " + gradeScore);
        if (gradeScore >= 80)
            gradeImage = gradeAImage;
        else if (gradeScore >= 60 && gradeScore < 80)
            gradeImage = gradeBImage;
        else if (gradeScore >= 40 && gradeScore < 60)
            gradeImage = gradeCImage;
        else if (gradeScore >= 20 && gradeScore < 40)
            gradeImage = gradeDImage;
        else
            gradeImage = gradeFImage;

        JLabel gradeimage = new JLabel(gradeImage);
        gradeimage.setBounds(170, 260, gradeAImage.getIconWidth(), gradeAImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(gradeimage);
    }

}