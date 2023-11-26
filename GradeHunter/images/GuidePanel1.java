package GradeHunter.images;

import GradeHunter.GuideFrame;
import GradeHunter.ImagePanel;

import javax.swing.*;
import java.awt.*;

/***
 * 게임설명서(입학안내서)1 클래스입니다.
 * @author 김봄
 */
public class GuidePanel1 extends JPanel {
    public GuidePanel1(GuideFrame frame) {
        setLayout(new BorderLayout());
        ImagePanel imgPanel = new ImagePanel(new ImageIcon("images\\bg_gp_1.png").getImage());
        add(imgPanel, BorderLayout.CENTER);

        ImageIcon leftIcon = new ImageIcon("images\\key_left.png");
        ImageIcon rightIcon = new ImageIcon("images\\key_right.png");
        JButton leftButton = new JButton(leftIcon);
        JButton rightButton = new JButton(rightIcon);



        leftButton.setBounds(60, 550, leftIcon.getIconWidth(), leftIcon.getIconHeight());
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());
        imgPanel.add(leftButton);
        imgPanel.add(rightButton);


        //rightButton.setOpaque(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setBorderPainted(false);
        //rightButton.setOpaque(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 이벤트 리스너 설정
        rightButton.addActionListener(e -> frame.showPanel("Guide2"));
        leftButton.addActionListener(e -> frame.showPanel("Home")); // 'Home' 패널이 있다고 가정할 때
    }
}