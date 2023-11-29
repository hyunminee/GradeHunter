package GradeHunter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * 모든 스테이지를 클리어했을 때 진행되는 스토리를 담당하는 클래스입니다.
 * @author 서보경
 */


/*
public class ClearPanel extends JPanel {

    public static JFrame frame;
    private Image backgroundImage;

    public ClearPanel(JFrame frame){
        this.frame = frame;
        try {
            backgroundImage = ImageIO.read(getClass().getResource("images/end_m.png")); // 이미지 파일 경로에 따라 수정
        } catch (IOException e) {
            e.printStackTrace();
        }


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.getContentPane().removeAll();
                frame.add(new EndCreditsPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
*/


public class ClearPanel extends JPanel {
    private Image backgroundImage;

    public ClearPanel(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("GradeHunter/images/bg_gp_1.png").getImage();

        ImageIcon leftIcon = new ImageIcon("GradeHunter/images/key_left.png");
        ImageIcon rightIcon = new ImageIcon("GradeHunter/images/key_right.png");
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
            QuizSelectPanel quizSelectPanel = new QuizSelectPanel(mainPanel);
            mainPanel.switchPanel(quizSelectPanel);
        });

        // 왼쪽 버튼 이벤트 리스너
        leftButton.addActionListener(e -> {

            // MainPanel의 값 초기화 후 전환
            mainPanel.resetValues();

            // MainPanel의 메인 콘텐츠 패널로 돌아가기
            mainPanel.switchPanel(mainPanel.getMainContentPanel());
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
