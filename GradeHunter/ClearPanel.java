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