package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GradeHunter 게임의 두 번째 안내 패널 클래스
 * @author 김봄
 */
public class GuidePanel2 extends JPanel {
    private Image backgroundImage;
    private ImageIcon leftEnteredIcon = new ImageIcon("images/key_left_entered.png");
    private ImageIcon rightEnteredIcon = new ImageIcon("images/key_right_entered.png");

    /**
     * GuidePanel2의 생성자 - 배경 이미지를 설정하고, 버튼을 초기화하며, 이벤트 리스너를 설정
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     */
    public GuidePanel2(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃을 사용하여 컴포넌트의 위치를 수동으로 설정합니다.
        backgroundImage = new ImageIcon("images/bg_gp_2.png").getImage(); // 배경 이미지 설정

        // 왼쪽 및 오른쪽 버튼 아이콘 초기화
        ImageIcon leftIcon = new ImageIcon("images/key_left.png");
        ImageIcon rightIcon = new ImageIcon("images/key_right.png");
        JButton leftButton = new JButton(leftIcon);
        JButton rightButton = new JButton(rightIcon);

        // 버튼 위치 및 크기 설정
        leftButton.setBounds(60, 550, leftIcon.getIconWidth(), leftIcon.getIconHeight());
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());

        // 버튼 배경 및 테두리 설정 해제
        leftButton.setContentAreaFilled(false);
        leftButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 오른쪽 버튼에 대한 이벤트 리스너 설정
        rightButton.addActionListener(e -> {
            GuidePanel3 guidePanel3 = new GuidePanel3(mainPanel);
            mainPanel.switchPanel(guidePanel3); // 다음 안내 패널로 전환
        });

        // 왼쪽 버튼에 대한 이벤트 리스너 설정
        leftButton.addActionListener(e -> {
            GuidePanel1 guidePanel1 = new GuidePanel1(mainPanel);
            mainPanel.switchPanel(guidePanel1); // 이전 안내 패널로 돌아가기
        });

        // 왼쪽 버튼에 마우스 이벤트 리스너 추가
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftEnteredIcon); // 마우스가 버튼 위에 있을 때 아이콘 변경
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftButton.setIcon(leftIcon); // 마우스가 버튼을 벗어났을 때 아이콘 복원
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 복원
            }
        });

        // 오른쪽 버튼에 마우스 이벤트 리스너 추가
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightEnteredIcon); // 마우스가 버튼 위에 있을 때 아이콘 변경
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 커서 변경
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rightButton.setIcon(rightIcon); // 마우스가 버튼을 벗어났을 때 아이콘 복원
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 커서 복원
            }
        });

        // 버튼을 패널에 추가
        add(leftButton);
        add(rightButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // 배경 이미지 그리기
    }
}
