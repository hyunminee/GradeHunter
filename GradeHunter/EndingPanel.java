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

    private Image backgroundImage;

    public EndingPanel(MainPanel mainPanel) {

        setLayout(null); // 널 레이아웃 사용

        System.out.print(MainPanel.ending);

        if(MainPanel.ending == 1) backgroundImage = new ImageIcon("GradeHunter/images/pf_re.png").getImage();
        else backgroundImage = new ImageIcon("GradeHunter/images/pf_be.png").getImage();

        ImageIcon rightIcon = new ImageIcon("GradeHunter/images/key_right.png");
        JButton rightButton = new JButton(rightIcon);
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 오른쪽 버튼 이벤트 리스너
        rightButton.addActionListener(e -> {
            if(MainPanel.ending == 1){
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