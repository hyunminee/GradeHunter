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

            /*JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this); // 현재 패널의 부모 프레임을 가져옴
            GamePlayPanel gameplayPanel = new GamePlayPanel(); // GamePlayPanel의 새 인스턴스를 생성

            frame.setContentPane(gameplayPanel); // 새 패널을 내용 패널로 설정
            frame.validate(); // 프레임을 변경 후 유효성 검사
            frame.repaint(); // 프레임을 다시 그려 새 내용을 표시*/
        });

        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
