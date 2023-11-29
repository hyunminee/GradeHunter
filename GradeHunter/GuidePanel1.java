package GradeHunter;

import javax.swing.*;
import java.awt.*;

public class GuidePanel1 extends JPanel {
    private Image backgroundImage;

    public GuidePanel1(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("GradeHunter/images/bg_gp_1.png").getImage();

        ImageIcon leftIcon = new ImageIcon("GradeHunter/images/key_left.png");
        ImageIcon rightIcon = new ImageIcon("GradeHunter/images/key_right.png");
        JButton leftButton = new JButton(leftIcon);
        JButton rightButton = new JButton(rightIcon);

        leftButton.setBounds(60, 550, leftIcon.getIconWidth(), leftIcon.getIconHeight());
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());

        leftButton.setContentAreaFilled(false);
        leftButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 오른쪽 버튼 이벤트 리스너
        rightButton.addActionListener(e -> {
            GuidePanel2 guidePanel2 = new GuidePanel2(mainPanel);
            mainPanel.switchPanel(guidePanel2);
        });

        // 왼쪽 버튼 이벤트 리스너
        leftButton.addActionListener(e -> {
            // MainPanel의 메인 콘텐츠 패널로 돌아가기
            mainPanel.switchPanel(mainPanel.getMainContentPanel());
        });


        add(leftButton);
        add(rightButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
