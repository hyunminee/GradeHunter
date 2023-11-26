package GradeHunter;

import javax.swing.*;
import java.awt.*;

/***
 * 게임설명서(입학안내서)를 담당하는 클래스입니다.
 * @author 김봄
 */

public class GuideFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public GuideFrame() {
        super("Grade Hunter");
        initUI();

        Toolkit kit = Toolkit.getDefaultToolkit();

        Dimension screenSize = kit.getScreenSize();

        Image img = kit.getImage("GradeHunter/images/Gradcap.png");
        setIconImage(img);
    }

    private void initUI() {
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 각 가이드 패널을 카드 패널에 추가
        cardPanel.add(new GuidePanel1(this), "Guide1");
        cardPanel.add(new GuidePanel2(this), "Guide2");
        cardPanel.add(new GuidePanel3(this), "Guide3");

        setContentPane(cardPanel);
        setVisible(true);
    }

    class ImagePanel extends JPanel {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
            setLayout(new BorderLayout()); // BorderLayout을 사용하여 버튼을 북과 남에 배치할 수 있도록 합니다.
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // 이미지를 패널 크기에 맞춰 그립니다.
        }
    }

    // 특정 패널로 전환하는 메소드
    public void showPanel(String card) {
        cardLayout.show(cardPanel, card);
    }

    public static void main(String[] args) {
        new GuideFrame();
    }
}