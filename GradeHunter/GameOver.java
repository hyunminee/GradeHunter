package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * 제한 시간 내에 게이지바를 채우지 못했을 시 나타나는 GameOver 패널을 담당하는 클래스입니다.
 * @author 정서윤
 */


public class GameOver extends JPanel{

    //테스트중
    public ImageIcon stage1Image = new ImageIcon(GameOver.class.getResource("images/stage/s_1.png")); // 이미지 아이콘 로드
    public ImageIcon gradeAImage = new ImageIcon(GameOver.class.getResource("images/grade/g_a.png"));
    public ImageIcon gameoverImage = new ImageIcon(GameOver.class.getResource("images/gameover.png"));
    public ImageIcon mainButtonImage = new ImageIcon(GameOver.class.getResource("images/bt_main.png"));
    public ImageIcon restartButtonImage = new ImageIcon(GameOver.class.getResource("images/bt_restart.png"));

    public GameOver(){

        /**
         * GameOver 클래스에 대한 기본 생성자입니다.
         */
        setLayout(null);    //레이아웃 관리자를 사용하지 않음
        setBackground(Color.BLACK);

        // image를 JLable로 추가

        JLabel stageimage = new JLabel(stage1Image);
        stageimage.setBounds(325, 125, stage1Image.getIconWidth(), stage1Image.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(stageimage);

        JLabel gradeimage = new JLabel(gradeAImage);
        gradeimage.setBounds(170, 260, gradeAImage.getIconWidth(), gradeAImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(gradeimage);

        JLabel gameoverimage = new JLabel(gameoverImage);
        gameoverimage.setBounds(50, 460, gameoverImage.getIconWidth(), gameoverImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(gameoverimage);

        JButton mainbutton = new JButton(mainButtonImage);
        mainbutton.setContentAreaFilled(false); // 배경을 투명하게 설정
        mainbutton.setBorderPainted(false); // 테두리를 그리지 않도록 설정
        mainbutton.setFocusPainted(false); // 포커스 테두리를 그리지 않도록 설정
        mainbutton.setBounds(640, 460, mainButtonImage.getIconWidth(), mainButtonImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        mainbutton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                mainbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));   // 커서를 손 모양으로 변경

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameOver.this);
                //topFrame.getContentPane().removeAll();
                topFrame.setContentPane(new MainPanel());
                topFrame.revalidate();
                topFrame.repaint();
            }
        });
        add(mainbutton);


        JButton restartbutton = new JButton(restartButtonImage);
        restartbutton.setContentAreaFilled(false); // 배경을 투명하게 설정
        restartbutton.setBorderPainted(false); // 테두리를 그리지 않도록 설정
        restartbutton.setFocusPainted(false); // 포커스 테두리를 그리지 않도록 설정
        restartbutton.setBounds(640, 550, restartButtonImage.getIconWidth(), restartButtonImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        restartbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                restartbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));   // 커서를 손 모양으로 변경

            }

            @Override
            public void mouseClicked(MouseEvent e) {

                // "처음으로" 버튼 클릭시 GamePlay 패널로 전환
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameOver.this);
                //topFrame.getContentPane().removeAll();
                topFrame.setContentPane(new GamePlayPanel());
                topFrame.revalidate();
                topFrame.repaint();


            }
        });
        add(restartbutton);

    }

}