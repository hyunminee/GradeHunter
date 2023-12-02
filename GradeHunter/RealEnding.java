package GradeHunter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/***
 * Real Ending 스토리 진행을 담당하는 클래스
 * @author 서보경
 */

public class RealEnding extends JPanel {

    private Image backgroundImage;
    private ImageIcon rightEnteredIcon = new ImageIcon("GradeHunter/images/key_right_entered.png");

    /**
     * RealEnding의 생성자 함수 : UI배치와 버튼에 대한 상호작용 (+ gen 변수에 따른 처리)
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     */

    public RealEnding(MainPanel mainPanel) {

        setLayout(null); // 널 레이아웃 사용

        System.out.print(MainPanel.ending);

        if(MainPanel.gen == 0) backgroundImage = new ImageIcon("GradeHunter/images/end_m.png").getImage();
        else backgroundImage = new ImageIcon("GradeHunter/images/end_f.png").getImage();

        ImageIcon rightIcon = new ImageIcon("GradeHunter/images/key_right.png");
        JButton rightButton = new JButton(rightIcon);
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 오른쪽 버튼 클릭 시, 엔딩크레딧
        rightButton.addActionListener(e -> {
            EndCreditsPanel endCreditsPanel = new EndCreditsPanel(mainPanel);
            mainPanel.switchPanel(endCreditsPanel);
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