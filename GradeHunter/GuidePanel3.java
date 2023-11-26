package GradeHunter;

import javax.swing.*;
import java.awt.*;

/***
 * 게임설명서(입학안내서)3 클래스입니다.
 * @author 김봄
 */
public class GuidePanel3 extends JPanel {
    public GuidePanel3(GuideFrame frame) {
        setLayout(new BorderLayout());
        ImagePanel imgPanel = new ImagePanel(new ImageIcon("images\\bg_gp_3.png").getImage());
        add(imgPanel, BorderLayout.CENTER);

        ImageIcon leftIcon = new ImageIcon("images\\bt_start.png");
        JButton startButton = new JButton(leftIcon);



        startButton.setBounds(860, 390, leftIcon.getIconWidth(), leftIcon.getIconHeight());
        imgPanel.add(startButton);


        //startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        // 이벤트 리스너 설정
        startButton.addActionListener(e -> frame.showPanel("Home"));
    }
}