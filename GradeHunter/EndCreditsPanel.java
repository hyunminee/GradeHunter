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

    private Image backgroundImage;

    public EndCreditsPanel(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("GradeHunter/images/ecp.png").getImage();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RankPanel rankPanel = new RankPanel(mainPanel);
                mainPanel.switchPanel(rankPanel);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
