package GradeHunter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


/***
 * Ending 스토리 진행을 담당하는 클래스입니다.
 * @author 서보경
 */

public class EndingPanel extends JPanel {
    public static int ending;  // 퀴즈 성공/실패에 따른 교수님 이미지 출력
    private Image backgroundImage;

    public EndingPanel(MainPanel mainPanel) {

        if (QuizSelectPanel.btn == 1)
            ending = Quiz_1.ending;
        else if (QuizSelectPanel.btn == 2)
            ending = Quiz_2.ending;
        else if (QuizSelectPanel.btn == 3)
            ending = Quiz_3.ending;
        else if (QuizSelectPanel.btn == 4)
            ending = Quiz_4.ending;
        else if (QuizSelectPanel.btn == 5)
            ending = Quiz_5.ending;
        else if (QuizSelectPanel.btn == 6)
            ending = Quiz_6.ending;
        else
            return;

        setLayout(null); // 널 레이아웃 사용

        if(ending == 1) backgroundImage = new ImageIcon("GradeHunter/images/pf_re.png").getImage();
        else backgroundImage = new ImageIcon("GradeHunter/images/pf_be.png").getImage();

        ImageIcon rightIcon = new ImageIcon("GradeHunter/images/key_right.png");
        JButton rightButton = new JButton(rightIcon);
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 오른쪽 버튼 이벤트 리스너
        rightButton.addActionListener(e -> {
            if(ending == 1){
                EndCreditsPanel endCreditsPanel = new EndCreditsPanel(mainPanel);
                mainPanel.switchPanel(endCreditsPanel);
            }
            else{
               GameOver gameOver = new GameOver(mainPanel);
               mainPanel.switchPanel(gameOver);
            }
        });

        add(rightButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}