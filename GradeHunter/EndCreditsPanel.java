package GradeHunter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * 엔딩크레딧을 담당하는 클래스입니다.
 * @author 서보경
 */

public class EndCreditsPanel extends JPanel{
    private final JFrame frame;
    private Image backgroundImage;

    public EndCreditsPanel(JFrame frame) {
        this.frame = frame;
        try {
            backgroundImage = ImageIO.read(getClass().getResource("images/ecp.png")); // 이미지 파일 경로에 따라 수정
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(MainPanel.savedText);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.getContentPane().removeAll();
                frame.add(new EndingPanel(frame));
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
