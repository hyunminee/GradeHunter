package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * GradeHunter 게임의 세 번째 안내 패널 클래스
 * <p>이 클래스는 사용자에게 게임 시작 전 마지막 정보를 제공합니다.</p>
 * @author 김봄
 */


public class GuidePanel3 extends JPanel {
    private Image backgroundImage;
    private ImageIcon startIcon;
    private ImageIcon startIconEntered;

    /**
     * GuidePanel3의 생성자 - 배경 이미지를 설정하고, 버튼을 초기화하며, 이벤트 리스너를 설정
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     */
    public GuidePanel3(MainPanel mainPanel) {
        setLayout(null); // 레이아웃을 null로 설정하여 컴포넌트의 위치를 수동으로 조정합니다.
        backgroundImage = new ImageIcon("images/bg_gp_3.png").getImage(); // 배경 이미지 설정

        // 시작 버튼에 사용될 아이콘 초기화
        startIcon = new ImageIcon("images/bt_start.png"); // 기본 아이콘
        startIconEntered = new ImageIcon("images/bt_start_entered.png"); // 마우스 오버 시 아이콘

        JButton startButton = new JButton(startIcon);
        startButton.setBounds(860, 390, startIcon.getIconWidth(), startIcon.getIconHeight());

        // 버튼 배경 및 테두리 설정 해제
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
            GamePlayPanel gameplayPanel = new GamePlayPanel(mainPanel); // 게임 플레이 패널로 전환
            mainPanel.switchPanel(gameplayPanel);
        });

        add(startButton); // 버튼을 패널에 추가
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // 배경 이미지 그리기
    }
}