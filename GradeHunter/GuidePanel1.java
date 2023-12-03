package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuidePanel1 extends JPanel {
    private Image backgroundImage;
    private ImageIcon leftEnteredIcon = new ImageIcon("images/key_left_entered.png");
    private ImageIcon rightEnteredIcon = new ImageIcon("images/key_right_entered.png");
    private ImageIcon leftIcon = new ImageIcon("images/key_left.png");
    private ImageIcon rightIcon = new ImageIcon("images/key_right.png");
    private JButton leftButton = new JButton(leftIcon);
    private JButton rightButton = new JButton(rightIcon);

    public GuidePanel1(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("images/bg_gp_1.png").getImage();

        ImageIcon leftIcon = new ImageIcon("images/key_left.png");
        ImageIcon rightIcon = new ImageIcon("images/key_right.png");
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

            // MainPanel의 값 초기화 후 전환
            mainPanel.resetValues();

            // MainPanel의 메인 콘텐츠 패널로 돌아가기
            mainPanel.switchPanel(mainPanel.getMainContentPanel());
        });


        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftEnteredIcon);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                leftButton.setIcon(leftIcon);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightEnteredIcon);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rightButton.setIcon(rightIcon);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
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
