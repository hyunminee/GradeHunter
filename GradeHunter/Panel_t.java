package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 엔딩크레딧장면에서 랭크패널로 이동하는 역할을 담당하는 클래스입니다.
 * @author 서보경
 */
public class Panel_t{
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Toolkit kit = Toolkit.getDefaultToolkit();
            JFrame frame = new JFrame("Grade Hunter");
            frame.setSize(1080, 720);

            Image img = kit.getImage("images\\Gradcap.png");
            frame.setIconImage(img);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 첫 번째 패널
            EndingPanel endingPanel = new EndingPanel(frame);
            frame.add(endingPanel);

            // 두 번째 패널
            EndCreditsPanel endCreditsPanel = new EndCreditsPanel(frame);

            frame.setVisible(true);
        });
    }
}
