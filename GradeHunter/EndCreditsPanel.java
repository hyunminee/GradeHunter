package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 엔딩크레딧을 담당하는 클래스
 * @author 서보경
 */

public class EndCreditsPanel extends JPanel{

    private Image backgroundImage;

    /**
     * EndCreditsPanel의 생성자 함수 : EndCredits와 클릭 한 번 인식 시, 엔딩크레딧을 패스하고, 랭킹패널로 전환한다.
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     */

    public EndCreditsPanel(MainPanel mainPanel) {
        setLayout(null); // 널 레이아웃 사용
        backgroundImage = new ImageIcon("images/ecp.png").getImage();

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
