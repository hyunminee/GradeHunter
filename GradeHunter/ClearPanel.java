package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 모든 스테이지를 클리어했을 때 진행되는 스토리를 담당하는 클래스
 * @author 서보경
 */

public class ClearPanel extends JPanel {
    private Image backgroundImage;
    private ImageIcon rightEnteredIcon = new ImageIcon("images/key_right_entered.png");

    /**
     * ClearPanel의 생성자 함수 : UI배치와 버튼에 대한 상호작용
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     */

    public ClearPanel(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("images/pf_c.png").getImage(); // 교수님과의 대화장면

        ImageIcon rightIcon = new ImageIcon("images/key_right.png");
        JButton rightButton = new JButton(rightIcon);
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 오른쪽 버튼 클릭 시, 화면전환
        rightButton.addActionListener(e -> {
            QuizSelectPanel quizSelectPanel = new QuizSelectPanel(mainPanel);
            mainPanel.switchPanel(quizSelectPanel);
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

        add(rightButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
