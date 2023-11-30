package GradeHunter;

import javax.swing.*;
import java.awt.*;

public class GuidePanel3 extends JPanel {
    private Image backgroundImage;

    public GuidePanel3(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("GradeHunter/images/bg_gp_3.png").getImage();

        ImageIcon startIcon = new ImageIcon("GradeHunter/images/bt_start.png");
        JButton startButton = new JButton(startIcon);
        startButton.setBounds(860, 390, startIcon.getIconWidth(), startIcon.getIconHeight());

        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        // 시작 버튼 이벤트 리스너
        startButton.addActionListener(e -> {
            ClearPanel clearPanel = new ClearPanel(mainPanel);
            mainPanel.switchPanel(clearPanel);
            //mainPanel.setShape((Shape) mainPanel.getRootPane()); // 메인 화면으로 돌아가기
        });

        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
