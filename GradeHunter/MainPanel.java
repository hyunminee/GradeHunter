package GradeHunter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * 메인 화면을 담당하는 클래스입니다.
 * @author 서보경
 */
public class MainPanel extends JFrame{
    public JPanel mainpanel;
    RankPanel rankPanel = new RankPanel(this); // MainPanel 인스턴스를 RankPanel에 전달
    public ImageIcon startButtonImage = new ImageIcon(Main.class.getResource("images/bt_guide.png"));
    public ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("images/bt_guide_entered.png"));
    public ImageIcon rankButtonImage = new ImageIcon(Main.class.getResource("images/bt_rank.png"));
    public ImageIcon rankButtonEnteredImage = new ImageIcon(Main.class.getResource("images/bt_rank_entered.png"));

    public ImageIcon mCheckImage = new ImageIcon("GradeHunter/images/check_m.png");
    public ImageIcon fCheckImage = new ImageIcon("GradeHunter/images/check_f.png");
    public ImageIcon mCheckEnteredImage = new ImageIcon("GradeHunter/images/check_entered_m.png");
    public ImageIcon fCheckEnteredImage = new ImageIcon("GradeHunter/images/check_entered_f.png");

    public JButton startButton = new JButton(startButtonImage);
    public JButton rankButton = new JButton(rankButtonImage);

    public JButton mCheckButton = new JButton(mCheckImage);

    public JButton fCheckButton = new JButton(fCheckImage);

    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;

    public static Image background = new ImageIcon(Main.class.getResource("images/bg_main.png")).getImage();

    public Container cPane;
    public JTextField studentID;
    public JButton saveButton;
    public static String savedText;
    public static int gen = -1;

    public void resetValues(){
        studentID.setText("");
        gen = -1;
        mCheckButton.setIcon(mCheckImage);
        fCheckButton.setIcon(fCheckImage);

    }

    public void switchPanel(JPanel panel) {
        getContentPane().removeAll(); // 기존 패널 제거
        getContentPane().add(panel); // 새로운 패널 추가
        getContentPane().revalidate(); // 레이아웃 관리자에게 레이아웃을 다시 계산하도록 지시
        getContentPane().repaint(); // 화면을 다시 그리도록 지시
    }

    public JPanel getMainContentPanel() { // 가이드 -> main으로 올 떄 필요함
        return mainpanel;
    }

    public String genToString(int gen){
        if(gen == 0){
           return "남자";
        } else if (gen == 1) {
            return "여자";
        }
        else
            return "오류: 초기값";
    }

    public void saveText() {
        savedText = studentID.getText();

        JOptionPane.showMessageDialog(MainPanel.this, "저장되었습니다. \n 학번 : " + savedText + "\n 성별 : " + genToString(gen));
    }


    public MainPanel(){

        cPane = getContentPane();
        setTitle("Grade Hunter");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Toolkit kit = Toolkit.getDefaultToolkit();

        Image img = kit.getImage(Main.class.getResource("images/Gradcap.png"));
        setIconImage(img);

        //=========================================================================================
        //drawback mainpanel = new drawback(); ----->  mainpanel = new drawback();

        mainpanel = new drawback();

        // 학번 입력받아 저장
        studentID = new JTextField();
        studentID.setBounds(450,350,200,50);
        studentID.setOpaque(false);
        studentID.setBorder(null);
        studentID.setForeground(Color.WHITE);
        Font font = new Font("SansSerif", Font.PLAIN, 30);
        studentID.setFont(font);

        // PlainDocument를 사용하여 입력 제한
        studentID.setDocument(new JTextFieldLimit(9));

        // 성별을 선택받아 저장
        ButtonGroup checkGroup = new ButtonGroup();
        checkGroup.add(mCheckButton);
        checkGroup.add(fCheckButton);

        // 남 선택 버튼
        mCheckButton.setBounds(370,405,50,60);
        mCheckButton.setBorderPainted(false);
        mCheckButton.setContentAreaFilled(false);
        mCheckButton.setFocusPainted(false);
        mCheckButton.addMouseListener(new MouseAdapter() {
            /**
             * 마우스가 남자 체크 버튼에 올라갈 경우 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                mCheckButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 남자 체크 버튼 범위에서 벗어났을 경우 원래 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                mCheckButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            /**
             * 마우스로 클릭했을 때, 체크 되었다는 표시와 성별 정보를 저장합니다.
             */
            public void mousePressed(MouseEvent e){
                mCheckButton.setIcon(mCheckEnteredImage);
                fCheckButton.setIcon(fCheckImage);
                gen = 0;
            }
        });

        // 여 선택 버튼
        fCheckButton.setBounds(570,405,50,60);
        fCheckButton.setBorderPainted(false);
        fCheckButton.setContentAreaFilled(false);
        fCheckButton.setFocusPainted(false);
        fCheckButton.addMouseListener(new MouseAdapter() {
            /**
             * 마우스가 여자 체크 버튼에 올라갈 경우 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                fCheckButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 여자 체크 버튼 범위에서 벗어났을 경우 원래 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                fCheckButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            /**
             * 마우스로 클릭했을 때, 체크 되었다는 표시와 성별 정보를 저장합니다.
             */
            public void mousePressed(MouseEvent e){
                fCheckButton.setIcon(fCheckEnteredImage);
                mCheckButton.setIcon(mCheckImage);
                gen = 1;

            }
        });

        // 입학 버튼
        startButton.setBounds(360,500,360,80);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(true);
        startButton.addMouseListener(new MouseAdapter() {

            /**
             * 마우스가 입학 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                startButton.setIcon(startButtonEnteredImage);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 입학 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                startButton.setIcon(startButtonImage);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            /**
             * 입학 버튼 클릭 시, 학번과 성별 정보를 저장하고, 입학 안내서 화면으로 전환됩니다.
             */

            public void mousePressed(MouseEvent e) {
                if (studentID.getText().isEmpty() || gen == -1) {
                    JOptionPane.showMessageDialog(MainPanel.this, "학번과 성별을 확인할 수 없습니다.", "", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    // 학번과 성별 저장
                    saveText();

                    // GuidePanel1 인스턴스 생성
                    GuidePanel1 guidePanel = new GuidePanel1(MainPanel.this);

                    // 현재 컨텐츠를 GuidePanel1로 교체
                    getContentPane().removeAll();
                    getContentPane().add(guidePanel);
                    getContentPane().revalidate();
                    getContentPane().repaint();
                }
            }

        });

        // 역대 졸업생 버튼
        rankButton.setBounds(880,500,140,140);
        rankButton.setBorderPainted(false);
        rankButton.setContentAreaFilled(false);
        rankButton.setFocusPainted(false);
        rankButton.addMouseListener(new MouseAdapter() {
            /**
             * 마우스가 역대졸업생 버튼에 올라갈 경우 이미지와 커서모양을 바꿔주는 메소드입니다.
             */
            @Override
            public void mouseEntered(MouseEvent e){
                rankButton.setIcon(rankButtonEnteredImage);
                rankButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 마우스가 역대졸업생 버튼 범위에서 벗어났을 경우 원래 이미지와 커서로 바꿔주는 메소드입니다.
             */

            public void mouseExited(MouseEvent e){
                rankButton.setIcon(rankButtonImage);
                rankButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            /**
             * 역대졸업생 버튼 클릭 시, RankPanel로 화면이 전환됩니다.
             */
            public void mousePressed(MouseEvent e) {
                RankPanel rankPanel = new RankPanel(MainPanel.this); // MainPanel의 인스턴스를 인자로 전달
                getContentPane().removeAll(); // 기존 패널 제거
                getContentPane().add(rankPanel); // 새로운 랭킹 패널 추가
                revalidate();
                repaint();
            }
        });



        // mainpanel UI 그리기
        cPane.add(mainpanel);
        mainpanel.setLayout(null);
        mainpanel.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        mainpanel.add(startButton);
        mainpanel.add(rankButton);
        mainpanel.add(mCheckButton);
        mainpanel.add(fCheckButton);
        mainpanel.add(studentID);
        setVisible(true);

    }

    class drawback extends JPanel{
        /**
         * cPane에  이미지를 그려주는 메소드 입니다.
         */
        @Override
        public void paintComponent(Graphics graphics){

            super.paintComponent(graphics);

            graphics.drawImage(background,0,0, getWidth(), getHeight(),this );
        }

    }

    class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            // 숫자만 입력 가능하도록 함
            if (!str.matches("\\d+")) return;

            // 최대 길이 제한
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

}
