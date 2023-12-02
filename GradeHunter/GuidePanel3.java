package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuidePanel3 extends JPanel {
    private Image backgroundImage;
    private ImageIcon startIcon;
    private ImageIcon startIconEntered;

    public GuidePanel3(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("GradeHunter/images/bg_gp_3.png").getImage();

        // 시작 버튼의 기본 아이콘
        startIcon = new ImageIcon("GradeHunter/images/bt_start.png");
        // 마우스가 버튼 위에 있을 때의 아이콘
        startIconEntered = new ImageIcon("GradeHunter/images/bt_start_entered.png");

        JButton startButton = new JButton(startIcon);
        startButton.setBounds(860, 390, startIcon.getIconWidth(), startIcon.getIconHeight());

        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        // 마우스 리스너 추가
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 마우스가 버튼 위에 있을 때 이미지와 커서 변경
                startButton.setIcon(startIconEntered);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 마우스가 버튼에서 벗어났을 때 이미지와 커서 복원
                startButton.setIcon(startIcon);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // 시작 버튼 이벤트 리스너
        startButton.addActionListener(e -> {
            GamePlayPanel gameplayPanel = new GamePlayPanel(mainPanel); // GamePlayPanel의 새 인스턴스를 생성
            mainPanel.switchPanel(gameplayPanel);
        });

        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
