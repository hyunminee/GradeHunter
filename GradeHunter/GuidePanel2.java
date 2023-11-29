package GradeHunter;

import javax.swing.*;
import java.awt.*;

public class GuidePanel2 extends JPanel {
    private Image backgroundImage;

    public GuidePanel2(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("GradeHunter/images/bg_gp_2.png").getImage();

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



        rightButton.addActionListener(e -> {
            GuidePanel3 guidePanel3 = new GuidePanel3(mainPanel);
            mainPanel.switchPanel(guidePanel3); // 메인 화면으로 돌아가기
        });

        // 왼쪽 버튼 이벤트 리스너
        leftButton.addActionListener(e -> {
            GuidePanel1 guidePanel1 = new GuidePanel1(mainPanel);
            mainPanel.switchPanel(guidePanel1); // 메인 화면으로 돌아가기
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
