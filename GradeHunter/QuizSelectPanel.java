package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 퀴즈 분야 (논문 주제)를 선택할 수 있는 클래스
 * @author 서보경
 */

public class QuizSelectPanel extends JPanel {
    private Image backgroundImage;
    private ImageIcon quiz_1eIcon = new ImageIcon("images/bt_q1_e.png");
    private ImageIcon quiz_2eIcon = new ImageIcon("images/bt_q2_e.png");
    private ImageIcon quiz_3eIcon = new ImageIcon("images/bt_q3_e.png");
    private ImageIcon quiz_4eIcon = new ImageIcon("images/bt_q4_e.png");
    private ImageIcon quiz_5eIcon = new ImageIcon("images/bt_q5_e.png");
    private ImageIcon quiz_6eIcon = new ImageIcon("images/bt_q6_e.png");

    /**
     * QuizSelectPanel의 생성자 함수 : UI배치와 버튼에 대한 상호작용 (+ subject 변수 처리)
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     */

    public QuizSelectPanel(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("images/bg_qs.png").getImage();

        ImageIcon quiz_1Icon = new ImageIcon("images/bt_q1.png");
        ImageIcon quiz_2Icon = new ImageIcon("images/bt_q2.png");
        ImageIcon quiz_3Icon = new ImageIcon("images/bt_q3.png");
        ImageIcon quiz_4Icon = new ImageIcon("images/bt_q4.png");
        ImageIcon quiz_5Icon = new ImageIcon("images/bt_q5.png");
        ImageIcon quiz_6Icon = new ImageIcon("images/bt_q6.png");

        JButton btn1 = new JButton(quiz_1Icon);
        JButton btn2 = new JButton(quiz_2Icon);
        JButton btn3 = new JButton(quiz_3Icon);
        JButton btn4 = new JButton(quiz_4Icon);
        JButton btn5 = new JButton(quiz_5Icon);
        JButton btn6 = new JButton(quiz_6Icon);

        btn1.setBounds(45, 200, quiz_1Icon.getIconWidth(), quiz_1Icon.getIconHeight());
        btn2.setBounds(385, 200, quiz_2Icon.getIconWidth(), quiz_2Icon.getIconHeight());
        btn3.setBounds(725, 200, quiz_3Icon.getIconWidth(), quiz_3Icon.getIconHeight());
        btn4.setBounds(45, 420, quiz_4Icon.getIconWidth(), quiz_4Icon.getIconHeight());
        btn5.setBounds(385, 420, quiz_5Icon.getIconWidth(), quiz_5Icon.getIconHeight());
        btn6.setBounds(725, 420, quiz_6Icon.getIconWidth(), quiz_6Icon.getIconHeight());

        btn1.setContentAreaFilled(false);
        btn1.setBorderPainted(false);
        btn2.setContentAreaFilled(false);
        btn2.setBorderPainted(false);
        btn3.setContentAreaFilled(false);
        btn3.setBorderPainted(false);
        btn4.setContentAreaFilled(false);
        btn4.setBorderPainted(false);
        btn5.setContentAreaFilled(false);
        btn5.setBorderPainted(false);
        btn6.setContentAreaFilled(false);
        btn6.setBorderPainted(false);

        btn1.addActionListener(e -> {
/*            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(QuizSelectPanel.this);
            topFrame.setContentPane(new Quiz_1(mainPanel));
            topFrame.revalidate();
            topFrame.repaint();*/
            MainPanel.subject = 1;
            Quiz_1 quiz_1 = new Quiz_1(mainPanel);
            mainPanel.switchPanel(quiz_1); // Quiz_1 생성
        });

        btn1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn1.setIcon(quiz_1eIcon);
                btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn1.setIcon(quiz_1Icon);
                btn1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        btn2.addActionListener(e -> {
            MainPanel.subject = 2;
            Quiz_2 quiz_2 = new Quiz_2(mainPanel);
            mainPanel.switchPanel(quiz_2); // Quiz_2 생성
        });

        btn2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn2.setIcon(quiz_2eIcon);
                btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn2.setIcon(quiz_2Icon);
                btn2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        btn3.addActionListener(e -> {
            MainPanel.subject = 3;
            Quiz_3 quiz_3 = new Quiz_3(mainPanel);
            mainPanel.switchPanel(quiz_3); // Quiz_3 생성
        });

        btn3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn3.setIcon(quiz_3eIcon);
                btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn3.setIcon(quiz_3Icon);
                btn3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        btn4.addActionListener(e -> {
            MainPanel.subject = 4;
            Quiz_4 quiz_4 = new Quiz_4(mainPanel);
            mainPanel.switchPanel(quiz_4); // Quiz_4 생성
        });
        btn4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn4.setIcon(quiz_4eIcon);
                btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn4.setIcon(quiz_4Icon);
                btn4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });


        btn5.addActionListener(e -> {
            MainPanel.subject = 5;
            SwingUtilities.invokeLater(() -> {
                Quiz_5 quiz_5 = new Quiz_5(mainPanel);
                mainPanel.switchPanel(quiz_5);
                quiz_5.setVisible(true);
            });
        });

        btn5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn5.setIcon(quiz_5eIcon);
                btn5.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn5.setIcon(quiz_5Icon);
                btn5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        btn6.addActionListener(e -> {
            MainPanel.subject = 6;
            Quiz_6 quiz_6 = new Quiz_6(mainPanel);
            mainPanel.switchPanel(quiz_6); // Quiz_ 6
        });

        btn6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn6.setIcon(quiz_6eIcon);
                btn6.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn6.setIcon(quiz_6Icon);
                btn6.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
