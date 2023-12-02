package GradeHunter;


import javax.swing.*;
import java.awt.*;

/***
 * Real Ending 스토리 진행을 담당하는 클래스입니다.
 * @author 서보경
 */

public class RealEnding extends JPanel {

    private Image backgroundImage;

    public RealEnding(MainPanel mainPanel) {

        setLayout(null); // 널 레이아웃 사용

        System.out.print(MainPanel.ending);

        if(MainPanel.gen == 0) backgroundImage = new ImageIcon("GradeHunter/images/end_m.png").getImage();
        else backgroundImage = new ImageIcon("GradeHunter/images/end_f.png").getImage();

        ImageIcon rightIcon = new ImageIcon("GradeHunter/images/key_right.png");
        JButton rightButton = new JButton(rightIcon);
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 오른쪽 버튼 이벤트 리스너
        rightButton.addActionListener(e -> {
            EndCreditsPanel endCreditsPanel = new EndCreditsPanel(mainPanel);
            mainPanel.switchPanel(endCreditsPanel);
        });

        add(rightButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}