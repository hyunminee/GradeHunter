package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/***
 * Ending 스토리 진행 중 교수님과의 대화를 담당하는 클래스
 * @author 서보경
 */

public class EndingPanel extends JPanel {

    private Image backgroundImage;
    private ImageIcon rightEnteredIcon = new ImageIcon("images/key_right_entered.png");

    /**
     * EndingPanel의 생성자 함수 : UI배치와 버튼에 대한 상호작용 (+ ending 변수에 따른 처리)
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     */

    public EndingPanel(MainPanel mainPanel) {

        setLayout(null); // 널 레이아웃 사용

        System.out.print(MainPanel.ending);

        // 1이면 교수님께서 좋은 말씀을, 아니면 졸업을 못시켜주겠다는 말씀을 함
        if(MainPanel.ending == 1) backgroundImage = new ImageIcon("images/pf_re.png").getImage();
        else backgroundImage = new ImageIcon("images/pf_be.png").getImage();

        ImageIcon rightIcon = new ImageIcon("images/key_right.png");
        JButton rightButton = new JButton(rightIcon);
        rightButton.setBounds(955, 550, rightIcon.getIconWidth(), rightIcon.getIconHeight());
        rightButton.setContentAreaFilled(false);
        rightButton.setBorderPainted(false);

        // 오른쪽 버튼 클릭 시, 각각 화면에 따른 패널로 전환
        rightButton.addActionListener(e -> {
            if(MainPanel.ending == 1){
                RealEnding realEnding = new RealEnding(mainPanel);
                mainPanel.switchPanel(realEnding);
            }
            else{
               GameOver gameOver = new GameOver(mainPanel);
               mainPanel.switchPanel(gameOver);
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

        add(rightButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}