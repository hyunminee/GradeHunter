package GradeHunter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * 게임 플레이 화면을 관리하는 클래스
 * @author 박현민, 정서윤
 * */
public class GamePlayPanel extends JPanel implements ActionListener, KeyListener {
    private GameLogic gameLogic;
    private List<Item> items;
    private Player character;
    private static MainPanel mainPanel;

    private BufferedImage backgroundImage;
    public static Timer gameUpdateTimer;
    private final int DELAY = 16; // 약 60 FPS에 해당하는 간격
    private boolean isPopupActive; // 팝업이 활성화되어 있는지 여부
    private int currentStage = GameLogic.currentStage; // 현재 스테이지
    private JProgressBar gaugeBar;
    public ImageIcon stageImage;

    public ImageIcon stage1Popup = new ImageIcon(("images/popup/popup_1.png"));
    public ImageIcon stage2Popup = new ImageIcon(("images/popup/popup_2.png"));
    public ImageIcon stage3Popup = new ImageIcon(("images/popup/popup_3.png"));
    public ImageIcon stage4Popup = new ImageIcon(("images/popup/popup_4.png"));
    public ImageIcon stage5Popup = new ImageIcon(("images/popup/popup_5.png"));
    public ImageIcon stage6Popup = new ImageIcon(("images/popup/popup_6.png"));
    public ImageIcon stage7Popup = new ImageIcon(("images/popup/popup_7.png"));
    public ImageIcon stage8Popup = new ImageIcon(("images/popup/popup_8.png"));

    /**
     * GamePlayPanel의 생성자
     * <p>패널의 레이아웃 설정, 배경 이미지 로드, 포커스 리스너 추가, 게임 로직 및 캐릭터 초기화 등이 있다.</p>
     * @param mainPanel switchPanel()을 사용하기 위한 parameter
     * */
    public GamePlayPanel(MainPanel mainPanel) {

        GameLogic.totalTimeMillis = 0;
        GameLogic.currentStage = 1;
        this.mainPanel = mainPanel;

        setLayout(null); // 레이아웃 관리자 비활성화
        showStagePopup();

        // 배경 이미지 로드
        try {
            backgroundImage = ImageIO.read(new File("images/bg_playing.png")); // 이미지 파일 경로 지정
        } catch (IOException e) {
            e.printStackTrace();
            backgroundImage = null;
        }

        addKeyListener(this);
        setFocusable(true);
        setPreferredSize((new Dimension(1080, 720))); // 화면 크기 설정

        // 포커스 리스너
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 포커스를 얻었을 때 수행할 작업 (필요한 경우)
            }

            @Override
            public void focusLost(FocusEvent e) {
                // 포커스를 잃었을 때, 필요하다면 다시 포커스를 요청
                GamePlayPanel.this.requestFocusInWindow();
            }
        });

        // 캐릭터 및 아이템 초기화
        character = new Player(500,500);
        items = new ArrayList<>(); // 아이템 리스트 초기화

        // GameLogic 인스턴스 생성
        gameLogic = new GameLogic(character, items, this, 60, 1080, 50, this, mainPanel); // 60초로 초기화

        // JProgressBar 초기화
        gaugeBar = new JProgressBar();
        gaugeBar.setMinimum(0); // 최소값 설정
        gaugeBar.setMaximum(100); // 최대값 설정 (초기값)
        gaugeBar.setValue(0); // 초기 게이지 값 설정
        gaugeBar.setForeground(Color.ORANGE); // 게이지 바 색상 설정
        add(gaugeBar); // GamePlayPanel에 추가
        // 컴포넌트 리스너 추가
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // 패널 크기가 변경될 때마다 JProgressBar 위치 및 크기 재설정
                gaugeBar.setBounds(300, 88, getWidth() - 490, 25);
            }
        });

        isPopupActive = true; // 초기 상태에서 팝업 활성화

    }

    /**
     * 컴포넌트가 화면에 추가된 후에 호출될 메서드
     * <p>캐릭터의 실제 위치를 화면 크기에 맞게 설정하고 게임을 초기화한다.</p>
     * */
    @Override
    public void addNotify() {
        super.addNotify();
        // 화면 크기를 기반으로 캐릭터의 실제 위치 설정
        character = new Player(getWidth() / 2 - 60, getHeight() - 120); // 캐릭터 생성
        character.setScreenWidth(getWidth()); // 캐릭터가 화면 너비를 인식하도록 설정
        initGame(); // 스테이지 초기화
        requestFocusInWindow();
    }

    /***
     * 게임의 초기 상태를 설정하는 메소드
     * <p>캐릭터 위치, 아이템 리스트 초기화, 게임 로직 설정, 게임 타이머 설정 등 게임 시작 전 필요사항을 준비한다.</p>
     */
    public void initGame() {
        character = new Player(540,500); // 캐릭터 위치 설정 초기화
        items = new ArrayList<>();
        gameLogic = new GameLogic(character, items, this, 60, 1080, 50, this, mainPanel);
        // 게이지 값을 0으로 초기화
        GameLogic.gaugeValue = 0;
        updateGaugeBar(0); // GamePlayPanel의 게이지 바 업데이트 메서드 호출
        if (gameUpdateTimer != null) {
            gameUpdateTimer.stop(); // 이전 타이머가 있으면 정지
        }
        gameUpdateTimer = new Timer(DELAY, this); // 새 타이머 설정 및 시작
        gameUpdateTimer.start();
        gameLogic.updateGame();

    }

    /**
     * 스테이지 정보를 보여주는 팝업창을 표시하는 메소드
     * <p>현재 스테이지에 맞는 팝업 이미지를 화면에 표시하고 일정 시간 후에 제거한다.</p>
     * */
    public void showStagePopup() {
        isPopupActive = true; // 팝업 활성화됨
        // items 리스트가 초기화되었는지 확인하고 초기화된 경우에만 clear() 호출
        if (items != null) {
            items.clear();
        }

        // GameLogic의 현재 스테이지 값 다시 할당 (currentStage++이 일어났으므로)
        currentStage = GameLogic.currentStage;

        // currentStage 값에 맞는 stageImage 지정
        if (currentStage == 1)
            stageImage = stage1Popup;
        else if(currentStage == 2)
            stageImage = stage2Popup;
        else if(currentStage == 3)
            stageImage = stage3Popup;
        else if(currentStage == 4)
            stageImage = stage4Popup;
        else if(currentStage == 5)
            stageImage = stage5Popup;
        else if(currentStage == 6)
            stageImage = stage6Popup;
        else if(currentStage == 7)
            stageImage = stage7Popup;
        else if(currentStage == 8)
            stageImage = stage8Popup;
        else
            return;

        // 스테이지 팝업
        JLabel stagePopup = new JLabel(stageImage);
        stagePopup.setBounds(290, 160, stageImage.getIconWidth(), stageImage.getIconHeight()); // x, y 위치와 너비, 높이 설정
        add(stagePopup);



        //Timer를 설정하여 지정된 시간 후에 레이블을 패널에서 제거
        Timer timer = new Timer(3000, e-> {
            remove(stagePopup);
            revalidate();
            repaint();

            isPopupActive = false; // 팝업 비활성화

            // GameLogic의 타이머를 시작하는 로직
            gameLogic.startGameTimer();
            GameLogic.gameStartTime = System.currentTimeMillis();
        });
        timer.setRepeats(false);
        timer.start();

    }
    // 팝업 상태를 확인하는 메소드
    public boolean isPopupActive() {
        return isPopupActive;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경 이미지 그리기
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        // 팝업이 활성화되어 있지 않을 때만 아이템을 그림
        if (!isPopupActive) {
            for (Item item : items) {
                item.draw(g);
            }
        }
        // 플레이어 캐릭터 그리기
        character.draw(g);
        drawGameStatus(g);

        // 학번 그리기
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 28)); // 폰트 설정
        g.drawString(MainPanel.savedText, 150, 100); // 텍스트 그리기

        // 캐릭터 상태 이미지 그리기
        try {
            BufferedImage characterImage;
            if (MainPanel.gen == 0) {
                characterImage = ImageIO.read(new File("images/status_p_m.png"));
                g.drawImage(characterImage, 5, 3,120, 120, this);
            } else if (MainPanel.gen == 1){
                characterImage = ImageIO.read(new File("images/status_p_f.png"));
                g.drawImage(characterImage, 5, 3,120, 120, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 밀리초 단위 타이머 그리기
        g.setColor(Color.WHITE); // 타이머 텍스트 색상 설정
        g.setFont(new Font("SansSerif", Font.BOLD, 28)); // 폰트와 크기 설정
        String timeLeftFormatted = formatTime(gameLogic.getTimeLeft()); // 밀리초 단위 반환 가정
        g.drawString(timeLeftFormatted, getWidth() - 107, 105); // 위치 지정
    }

    /**
     * 게임 상태를 화면에 그리는 메소드
     * <p>배경, 캐릭터, 아이템 등 게임에 필요한 요소를 그린다.</p>
     * @param g Graphics 객체*/
    public void drawGameStatus(Graphics g) {
        // 부모 클래스의 paintComponent() 호출로 기본 패널 그리기를 수행
//        super.paintComponent(g);
        // 배경 이미지 그리기
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        // 캐릭터를 화면에 그림
        character.draw(g);
        // 모든 아이템을 화면에 그림
        for (Item item : items) {
            item.draw(g);
        }

    }
    /**
     * 게이지 값을 업데이트하는 메소드
     * @param value 업데이트할 게이지 값*/
    public void updateGaugeBar(int value)
    {
        gaugeBar.setValue(value);
    }
    /**
     * 최대 게이지 값을 설정하는 메소드
     * @param maxGaugeValue 설정할 최대 게이지 값*/
    public void setMaxGaugeValue(int maxGaugeValue)
    {
        gaugeBar.setMaximum(maxGaugeValue);
    }
    /**
     * 밀리초 단위의 시간을 포맷팅하여 문자열로 반환하는 메소드
     * <p>타이머에 표시될 시간 문자열을 생성한다.</p>
     * @param millis 밀리초 단위의 시간
     * @return formatting된 시간 문자열
     * */
    public String formatTime(long millis) {
        long seconds = millis / 1000;
        return String.format("%02d", seconds);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameLogic.updateGame();
        repaint();
    }

    // 키가 타이핑될 때 호출. 여기서는 사용하지 않으므로 비워둠
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // 사용자가 키를 눌렀을 때 호출
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            character.setMovingLeft(true); // 키가 눌렸을 때 캐릭터의 이동 상태를 true로 설정
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            character.setMovingRight(true);
        }
    }

    // 사용자가 키를 놓았을 때 호출.
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            character.setMovingLeft(false); // 키가 놓였을 때 이동 상태를 false로 설정
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            character.setMovingRight(false);
        }
    }

}