package GradeHunter;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * 퀴즈 분야 선택을 담당하는 클래스입니다.
 * @author 서보경
 */
public class QuizSelectPanel extends JPanel {

    public ImageIcon q1ButtonImage = new ImageIcon(getClass().getResource("images/bt_q1.png"));
    public ImageIcon q2ButtonImage = new ImageIcon(getClass().getResource("images/bt_q2.png"));
    public ImageIcon q3ButtonImage = new ImageIcon(getClass().getResource("images/bt_q3.png"));
    public ImageIcon q4ButtonImage = new ImageIcon(getClass().getResource("images/bt_q4.png"));
    public ImageIcon q5ButtonImage = new ImageIcon("GradeHunter/images/bt_q5.png");
    public ImageIcon q6ButtonImage = new ImageIcon("GradeHunter/images/bt_q6.png");

    public JButton btn1 = new JButton(q1ButtonImage);
    public JButton btn2 = new JButton(q2ButtonImage);
    public JButton btn3 = new JButton(q3ButtonImage);
    public JButton btn4 = new JButton(q4ButtonImage);
    public JButton btn5 = new JButton(q5ButtonImage);
    public JButton btn6 = new JButton(q6ButtonImage);

    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;

    public static Image background = new ImageIcon("GradeHunter/images/bg_qs.png").getImage();
    public Container cPane;

    public static JFrame quiz_1;
    public static JFrame quiz_2;
    public static JFrame quiz_3;
    public static JFrame quiz_4;
    public static JFrame quiz_5;
    public static JFrame quiz_6;



    public QuizSelectPanel(){

        JFrame frame = new JFrame();
        cPane = frame.getContentPane();
        frame.setTitle("Grade Hunter");
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Toolkit kit = Toolkit.getDefaultToolkit();

        Image img = kit.getImage(Main.class.getResource("images/Gradcap.png"));
        frame.setIconImage(img);
        frame.setVisible(true);

        drawback quizselectpanel = new drawback();
        btn1.setBounds(45, 200, 295, 165);
        btn1.setBorderPainted(false);
        btn1.setContentAreaFilled(false);
        btn1.setFocusPainted(true);
        btn1.addMouseListener(new MouseAdapter() {

            /**
             * 마우스가 입학 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                btn1.setIcon(q1ButtonImage);
                btn1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 입학 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                btn1.setIcon(q1ButtonImage);
                btn1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e){
                SwingUtilities.invokeLater(() -> {
                    remove(quizselectpanel);
                    repaint();
                    JFrame quiz_1frame = new JFrame();
                    Quiz_1 quiz_1panel = new Quiz_1(quiz_1frame);
                    quiz_1panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                    cPane.add(quiz_1panel);
                    quiz_1panel.setVisible(true);
                    quiz_1panel.revalidate();
                    quiz_1panel.repaint();
                });
            }

        });

        btn2.setBounds(385, 200, 295, 165);
        btn2.setBorderPainted(false);
        btn2.setContentAreaFilled(false);
        btn2.setFocusPainted(true);

        btn2.addMouseListener(new MouseAdapter() {

            /**
             * 마우스가 입학 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 입학 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                btn2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e){
                remove(quizselectpanel);
                repaint();
                /*JFrame quiz_2panel = new Quiz_2();
                quiz_2panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                cPane.add(quiz_2panel);
                quiz_2panel.setVisible(true);
                quiz_2panel.repaint();*/
            }

        });

        btn3.setBounds(725, 200, 295, 165);
        btn3.setBorderPainted(false);
        btn3.setContentAreaFilled(false);
        btn3.setFocusPainted(true);

        btn3.addMouseListener(new MouseAdapter() {

            /**
             * 마우스가 입학 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 입학 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                btn3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e){
                remove(quizselectpanel);
                repaint();
                /*JFrame quiz_3panel = new Quiz_3();
                quiz_3panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                cPane.add(quiz_3panel);
                quiz_3panel.setVisible(true);
                quiz_3panel.repaint();*/
            }

        });

        btn4.setBounds(45, 420,295, 165);
        btn4.setBorderPainted(false);
        btn4.setContentAreaFilled(false);
        btn4.setFocusPainted(true);

        btn4.addMouseListener(new MouseAdapter() {

            /**
             * 마우스가 입학 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 입학 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                btn4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e){
                remove(quizselectpanel);
                repaint();
                /*JFrame quiz_4panel = new Quiz_4();
                quiz_4panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                cPane.add(quiz_4panel);
                quiz_4panel.setVisible(true);
                quiz_4panel.repaint();*/
            }

        });

        btn5.setBounds(385, 420,295, 165);
        btn5.setBorderPainted(false);
        btn5.setContentAreaFilled(false);
        btn5.setFocusPainted(true);

        btn5.addMouseListener(new MouseAdapter() {

            /**
             * 마우스가 입학 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                btn5.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 입학 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                btn5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e){
                remove(quizselectpanel);
                repaint();
                /*JFrame quiz_5panel = new Quiz_5();
                quiz_5panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                cPane.add(quiz_5panel);
                quiz_5panel.setVisible(true);
                quiz_5panel.repaint();*/
            }

        });

        btn6.setBounds(725, 420,295, 165);
        btn6.setBorderPainted(false);
        btn6.setContentAreaFilled(false);
        btn6.setFocusPainted(true);

        btn6.addMouseListener(new MouseAdapter() {

            /**
             * 마우스가 입학 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                btn6.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 입학 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                btn6.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e){
                remove(quizselectpanel);
                repaint();
                /*JFrame quiz_6panel = new Quiz_6();
                quiz_6panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
                cPane.add(quiz_6panel);
                quiz_6panel.setVisible(true);
                quiz_6panel.repaint();*/
            }

        });


        cPane.add(quizselectpanel);
        quizselectpanel.setLayout(null);
        quizselectpanel.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        quizselectpanel.add(btn1);
        quizselectpanel.add(btn2);
        quizselectpanel.add(btn3);
        quizselectpanel.add(btn4);
        quizselectpanel.add(btn5);
        quizselectpanel.add(btn6);
        quizselectpanel.setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new QuizSelectPanel();
        });
    }

    class drawback extends JPanel{
        @Override
        public void paintComponent(Graphics graphics){
            /**
             * cPane에 시작화면을 그려주는 메소드 입니다.
             * @param graphics
             */
            super.paintComponent(graphics);

            graphics.drawImage(background,0,0, getWidth(), getHeight(),this );
        }

    }

}




