package GradeHunter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * 메인 화면을 담당하는 클래스
 * <p>
 *     0.1.0 -> 프로그램 완성
 *     0.1.1 -> 게이지 바 초기화 오류 수정  by 현민
 *     0.1.2 -> 클리어 시간 기록 오류 수정  by 보경
 *     0.2.2 -> 퀴즈 전환 부분 동일하게 진행되게 로직 수정  by 보경
 *     0.2.3 -> 어려운 난이도 생성 by 보경
 * </p>
 * @author 서보경
 * @version 0.2.3
 */

public class MainPanel extends JFrame{
    public JPanel mainpanel;

    // 각 버튼 이미지아이콘 변수 생성
    public ImageIcon startButtonImage = new ImageIcon("images/bt_guide.png");
    public ImageIcon startButtonEnteredImage = new ImageIcon("images/bt_guide_entered.png");
    public ImageIcon rankButtonImage = new ImageIcon("images/bt_rank.png");
    public ImageIcon rankButtonEnteredImage = new ImageIcon("images/bt_rank_entered.png");
    public ImageIcon mCheckImage = new ImageIcon("images/check_m.png");
    public ImageIcon fCheckImage = new ImageIcon("images/check_f.png");
    public ImageIcon mCheckEnteredImage = new ImageIcon("images/check_entered_m.png");
    public ImageIcon fCheckEnteredImage = new ImageIcon("images/check_entered_f.png");

    // 각 버튼 JButton 변수 생성
    public JButton startButton = new JButton(startButtonImage);
    public JButton rankButton = new JButton(rankButtonImage);
    public JButton mCheckButton = new JButton(mCheckImage);
    public JButton fCheckButton = new JButton(fCheckImage);

    // Screen 가로, 세로 길이
    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;

    // MainPanel 배경 이미지
    public static Image background = new ImageIcon("images/bg_main.png").getImage();

    public Container cPane;
    public JTextField studentID;  // 학번을 입력받는 텍스트 필드

    // 다른 클래스에서 이용되는 학번, 성별, 엔딩, 과목 변수
    public static String savedText;
    public static int gen = -1;  // 0이면 남자, 1이면 여자
    public static int ending = -1;  // 퀴즈 성공/실패에 따른 교수님 이미지 출력

    public static int subject = 0;  // 과목 체크


    /**
     *  학번과 성별 값, 성별 체크 버튼을 초기화 해주는 메소드
     */
    public void resetValues(){
        studentID.setText("");
        gen = -1;
        mCheckButton.setIcon(mCheckImage);
        fCheckButton.setIcon(fCheckImage);

    }

    /**
     * 각 클래스 간의 패널 전환을 해주는 메소드
     * @param panel : mainPanel을 parameter로 이용 / 모든 클래스의 생성자가 mainPanel을 parameter로 가짐
     */
    public void switchPanel(JPanel panel) {
        getContentPane().removeAll(); // 기존 패널 제거
        getContentPane().add(panel); // 새로운 패널 추가
        getContentPane().revalidate(); // 레이아웃 관리자에게 레이아웃을 다시 계산하도록 지시
        getContentPane().repaint(); // 화면을 다시 그리도록 지시
    }

    public JPanel getMainContentPanel() { // 가이드 -> main으로 올 떄 필요함
        return mainpanel;
    }

    /**
     * 성별 입력 후, 입학 버튼 클릭 시, 뜨는 알림창에 출력되는 성별 한글화 메소드 (saveText()에 이용)
     * @param gen 체크한 성별 값
     * @return 각 성별 값에 따른 한글
     */

    public String genToString(int gen){
        if(gen == 0){
            return "남자";
        } else if (gen == 1) {
            return "여자";
        }
        else
            return "오류: 초기값";
    }

    /**
     *  학번을 저장하고, 입학 버튼 클릭 시, 입력된 정보를 다시 확인시켜주는 메소드
     */
    public void saveText() {
        savedText = studentID.getText();

        JOptionPane.showMessageDialog(MainPanel.this, "저장되었습니다. \n 학번 : " + savedText + "\n 성별 : " + genToString(gen));
    }


    /**
     *  MainPanel의 생성자 함수
     *  <p>
     *      Frame의 기본적인 것들을 설정하고, 게임 아이콘을 설정한다.
     *      MainPanel의 배경을 그리고, 입학버튼, 역대졸업생버튼, 학번 텍스트 필드, 성별 체크 버튼의 설정 후, 패널에 그린다.
     *      그리고 텍스트 필드에 대한 제한 사항이나 각 버튼의 상호작용을 다룬다.
     *  </p>
     */

    public MainPanel(){

        cPane = getContentPane();
        setTitle("Grade Hunter");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Toolkit kit = Toolkit.getDefaultToolkit();

        Image img = kit.getImage("images/Gradcap.png");
        setIconImage(img);

// =============================================================================================================

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

        // 성별을 선택받아 저장 (그룹으로 묶어 두 개 선택 불가)
        ButtonGroup checkGroup = new ButtonGroup();
        checkGroup.add(mCheckButton);
        checkGroup.add(fCheckButton);

// =============================================================================================================
        // 남 선택 버튼
        mCheckButton.setBounds(370,405,50,60);
        mCheckButton.setBorderPainted(false);
        mCheckButton.setContentAreaFilled(false);
        mCheckButton.setFocusPainted(false);
        mCheckButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                mCheckButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                mCheckButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                mCheckButton.setIcon(mCheckEnteredImage);
                fCheckButton.setIcon(fCheckImage);
                gen = 0;
            }
        });

// =============================================================================================================
        // 여 선택 버튼
        fCheckButton.setBounds(570,405,50,60);
        fCheckButton.setBorderPainted(false);
        fCheckButton.setContentAreaFilled(false);
        fCheckButton.setFocusPainted(false);
        fCheckButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                fCheckButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                fCheckButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                fCheckButton.setIcon(fCheckEnteredImage);
                mCheckButton.setIcon(mCheckImage);
                gen = 1;

            }
        });

// =============================================================================================================
        // 입학 버튼
        startButton.setBounds(360,500,360,80);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(true);
        startButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                startButton.setIcon(startButtonEnteredImage);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                startButton.setIcon(startButtonImage);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
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

// =============================================================================================================
        // 역대 졸업생 버튼
        rankButton.setBounds(880,500,140,140);
        rankButton.setBorderPainted(false);
        rankButton.setContentAreaFilled(false);
        rankButton.setFocusPainted(false);
        rankButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                rankButton.setIcon(rankButtonEnteredImage);
                rankButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e){
                rankButton.setIcon(rankButtonImage);
                rankButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                RankPanel rankPanel = new RankPanel(MainPanel.this); // MainPanel의 인스턴스를 인자로 전달
                getContentPane().removeAll(); // 기존 패널 제거
                getContentPane().add(rankPanel); // 새로운 랭킹 패널 추가
                revalidate();
                repaint();
            }
        });

// =============================================================================================================

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

    /**
     * MainPanel의 innerclass : drawback - 배경 이미지를 그려준다.
     */

    class drawback extends JPanel{
        @Override
        public void paintComponent(Graphics graphics){

            super.paintComponent(graphics);

            graphics.drawImage(background,0,0, getWidth(), getHeight(),this );
        }

    }

    /**
     * MainPanel의 innerclass : JTextFieldLimit - 학번 입력을 숫자입력과 9자리로 제한한다.
     */

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