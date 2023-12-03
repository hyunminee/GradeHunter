package GradeHunter;

import javax.swing.*;
import java.util.*;
import java.util.List;
/**
 * 게임의 주요 로직을 관리하는 클래스
 * <p>아이템 생성 및 관리, 플레이어와 아이템의 상호작용, 게임 타이머 및 스테이지 관리 등을 담당한다.</p>
 * @author 박현민
 * @author 정서윤
 * @author 김봄
 */
public class GameLogic {
    private List<Item> items; // 게임 아이템 리스트
    private Player character; // 게임 플레이어 캐릭터
    private TimerNum timerLabel; // 타이머 라벨
    public static int currentStage = 1; // 현재 게임의 스테이지 번호
    private final int MAX_STAGE; // 최대 스테이지 번호
    public static int gaugeValue = 0; // 현재 게이지 값
    protected static final int GAUGE_PER_STAGE = 50; // 스테이지 당 필요한 게이지 증가량
    public static int maxGaugeValue = currentStage * GAUGE_PER_STAGE; // 최대 게이지 값 (스테이지에 따라 변함)
    private double itemFallSpeed = 1; // 아이템 하강 속도
    private final int PANEL_WIDTH; // 게임 패널의 너비
    private final int ITEM_WIDTH;  // 아이템의 너비
    protected Random rand; // 랜덤 이벤트 및 아이템 위치 생성에 사용될 Random 객체
    private static int MAX_ITEMS = 10; // 화면에 표시될 수 있는 최대 아이템 수
    private GamePlayPanel gamePlayPanel; // GamePlayPanel 참조
    private int lastStage = 0; // 이전 스테이지 번호를 추적하기 위한 변수
    private MainPanel mainPanel;
    public static long gameStartTime;
    public static long gameEndTime;
    public static String totalTime;
    public static long totalTimeMillis = 0;

    /**
     * GameLogic 객체를 생성하는 생성자
     * <p>초기화에 필요한 변수들을 설정하고 게임의 기본 상태를 구성한다.</p>
     * @param character 게임 플레이어 캐릭터
     * @param items 게임 아이템 리스트
     * @param parentPanel 게임이 진행될 패널
     * @param initialTime 게임의 초기 타이머 시간
     * @param panelWidth 게임 패널의 너비
     * @param itemWidth 아이템의 너비
     * @param panel GamePlayPanel 인스턴스
     * @param mainPanel MainPanel 인스턴스
     */
    public GameLogic(Player character, List<Item> items, JPanel parentPanel, int initialTime, int panelWidth, int itemWidth, GamePlayPanel panel, MainPanel mainPanel) {
        this.character = character;
        this.items = items;
        this.mainPanel = mainPanel;
        this.MAX_STAGE=8;
        this.PANEL_WIDTH = panelWidth;
        this.ITEM_WIDTH = itemWidth;
        rand = new Random();
        this.gamePlayPanel = panel;
        // TimerNum 인스턴스 생성
        timerLabel = new TimerNum(initialTime);

    }
    /**
     * 게임 타이머를 시작하는 메소드
     */
    public void startGameTimer() {
        if (timerLabel != null){
            //gameStartTime = System.currentTimeMillis(); // 게임 시작 시간 기록
            timerLabel.resetAndStart(60 * 1000); // 60초를 밀리초로 변환
        }
    }

    /**
     * 게임의 주요 업데이트 로직을 처리하는 메소드
     * <p>아이템과 플레이어의 상호작용, 게임 상태 업데이트 등을 수행한다.</p>
     */
    public void updateGame() {
        // 아이템과 플레이어의 충돌 처리, 게임 상태 업데이트 등
        // 팝업이 활성화되어 있지 않을 때만 아이템 관리 및 업데이트 수행
        if (!gamePlayPanel.isPopupActive()) {
            Iterator<Item> itemIterator = items.iterator();
            while (itemIterator.hasNext()) {
                Item item = itemIterator.next();

                // 아이템과 플레이어의 충돌 처리
                if (character.collidesWith(item)) {
                    // 아이템에 따른 추가 처리 (예: 점수 추가, 효과 적용 등)
                    processItemEffect(item);

                    // 충돌 후 아이템 제거
                    itemIterator.remove();
                } else {
                    // 아이템이 충돌하지 않은 경우
                    // 아이템 상태 업데이트 (예: 위치 이동 등)
                    item.move();
                }
            }
            manageItems();
        }
        // 아이템과 플레이어의 충돌 처리 및 상태 업데이트 후 GamePlayPanel의 화면을 갱신
        gamePlayPanel.repaint();
        // 캐릭터 상태 업데이트 (예: 이동, 애니메이션 상태 변경 등)
        // 매 프레임마다 캐릭터의 상태를 업데이트
        character.update();
        // 팝업이 활성화되어 있지 않을 때만 아이템 관리 및 업데이트 수행
        if (!gamePlayPanel.isPopupActive()) {
            manageItems();
        }
        // 게이지 값이 변경될 때마다 GamePlayPanel의 게이지 바 업데이트
        SwingUtilities.invokeLater(()->{
            gamePlayPanel.updateGaugeBar(gaugeValue);
        });
        // 스테이지 관리 로직
        manageStages();
    }
    /**
     * 아이템을 생성하고 관리하는 메소드
     */
    public void manageItems() {
        if (!gamePlayPanel.isPopupActive()) {
            if (timerLabel.getMilliSeconds()>0 && items.size()<MAX_ITEMS) {
                // 아이템 생성 로직
                createRandomItem();
            }
        }

        // 아이템 하강 로직
        Iterator<Item> itemIterator = items.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            item.move();
            if (!item.isOnScreen()) { // 화면 밖으로 나간 아이템 제거
                itemIterator.remove();
            }
        }
    }
    /**
     * 랜덤 아이템을 생성하는 메소드
     */
    public void createRandomItem() {
        ItemType[] types = ItemType.values();
        List<ItemType> weightedList = new ArrayList<>(); // 가중치 리스트 생성
        List<ItemType> allowedTypes;

        if (currentStage <= 4) {
            // 1~4 stage : RED, YELLOW, GREEN, BLUE 아이템
            allowedTypes = Arrays.asList(ItemType.RED, ItemType.YELLOW, ItemType.GREEN, ItemType.BLUE);
        } else {
            // 5~8 stage : 모든 아이템 타입
            allowedTypes = Arrays.asList(types);
        }

        for (ItemType type : allowedTypes) {
            int weight = 1; // 기본 가중치

            // 파란색 아이템에 높은 가중치 부여
            if (type == ItemType.BLUE || type == ItemType.YELLOW) {
                weight = 3; // 1~8 stage : BLUE 가중치
            } else if ((currentStage >= 5 && currentStage<= 8) && (type==ItemType.GREEN || type==ItemType.PRESENTATION || type==ItemType.RED)) {
                weight = 3; // 5~8 stage : GREEN, PRESENTATION 가중치
            }

            // 가중치에 따라 아이템 목록에 추가
            for (int i=0; i<weight; i++) {
                weightedList.add(type);
            }
        }
        // 가중치 목록에서 무작위로 아이템 선택
        ItemType selectedType = weightedList.get(rand.nextInt(weightedList.size()));

        int x; // 아이템이 화면 밖으로 나가지 않도록 계산
        int y = 132; // 화면 상단에서 시작

        boolean positionOverlap;
        do {
            x = rand.nextInt(PANEL_WIDTH - ITEM_WIDTH); // 아이템이 화면 밖으로 나가지 않도록 계산
            positionOverlap = checkPositionOverlap(x);
        } while (positionOverlap);

        Item newItem = new Item(selectedType.getImagePath(), x, y, selectedType, itemFallSpeed);
        items.add(newItem);
    }

    /**
     * 아이템의 위치가 겹치는지 확인하는 메소드
     * @param x 검사할 아이템의 x 좌표
     * @return 위치가 겹치면 true, 아니면 false
     */
    public boolean checkPositionOverlap(int x) {
        for (Item existingItem : items) {
            if (Math.abs(existingItem.getX()-x)<ITEM_WIDTH) {
                return true; // 겹치는 경우
            }
        }
        return false; // 겹치지 않는 경우
    }


    /**
     * 아이템 효과를 처리하는 메소드
     * <p>아이템에 따라 게이지 조정, 타이머 조정 등의 효과를 적용합니다.</p>
     * @param item 처리할 아이템
     */
    public void processItemEffect(Item item) {
        // 아이템의 효과를 적용하는 로직
        // TARDY 아이템의 경우 시간 감소, 그 외에는 게이지 증가/감소
        if (item.getType() == ItemType.TARDY) {
            timerLabel.decreaseTime(Math.abs(item.getEffectValue())*1000); // TARDY 아이템의 effectValue만큼 시간 감소
        } else {
            // 아이템이 게이지를 증가시키는 경우 최대값을 초과하지 않도록 한다.
            if (item.getEffectValue() > 0) {
                gaugeValue = Math.min(maxGaugeValue, gaugeValue + item.getEffectValue());
            } else {
                // 아이템이 게이지를 감소시키는 경우 0 미만으로 내려가지 않도록 한다.1?
                gaugeValue = Math.max(0, gaugeValue + item.getEffectValue());
            }
        }
    }
    /**
     * 게임의 스테이지를 관리하는 메소드
     * <p>스테이지 종료 조건을 확인하고 다음 스테이지로 이동한다.</p>
     */
    public void manageStages() {
        // 예: 스테이지 종료 조건 확인 및 다음 스테이지로 이동
        boolean newStageStarted = currentStage != lastStage;

        // 새 스테이지가 시작될 때 게이지 바 최대값 및 현재 값 업데이트
        if (newStageStarted) {
            maxGaugeValue = currentStage*GAUGE_PER_STAGE;
            gamePlayPanel.setMaxGaugeValue(maxGaugeValue);
            gamePlayPanel.updateGaugeBar(0);
        }
        // 게이지 값이 최대치에 도달했을 경우 타이머 멈춤
        if (gaugeValue >= maxGaugeValue) {
            timerLabel.stopTimer();
            gameEndTime = System.currentTimeMillis(); // 게임 종료 시간 기록
            totalTimeMillis += (gameEndTime - gameStartTime);

        }
        // 스테이지 종료 조건 미달시 GameOver로 전환
        if (!timerLabel.isRunning() && gaugeValue != maxGaugeValue) {

            GamePlayPanel.gameUpdateTimer.stop();
            GameOver gameOver = new GameOver(mainPanel);
            mainPanel.switchPanel(gameOver);
        }
        // 스테이지8 종료 조건
        if (currentStage == MAX_STAGE && gaugeValue >= maxGaugeValue) {
            GamePlayPanel.gameUpdateTimer.stop();

            totalTime = calculateTotalTime(); // 랭킹에 문자열로 저장

            // 스테이지8 종료후 ClearPanel로 전환
            //currentStage = 1;
            ClearPanel clearPanel = new ClearPanel(mainPanel);
            mainPanel.switchPanel(clearPanel);

        }
        // 스테이지 종료 조건 확인 후 다음 스테이지 이동
        else if (gaugeValue >= maxGaugeValue) {
            currentStage++;
            gaugeValue = 0; // 게이지 초기화
            gamePlayPanel.showStagePopup();
            goToNextStage(currentStage);
        }



        //======================================================================================================
        //======================================================================================================
        //======================================================================================================
    }
    // 총 클리어 시간을 계산하는 메소드
    public String calculateTotalTime() {
        long minutes = (totalTimeMillis / (1000 * 60)) % 60;
        long seconds = (totalTimeMillis  / 1000) % 60;
        long millis = totalTimeMillis  % 1000;

        return String.format("%02d:%02d:%02d", minutes, seconds, millis/10);
    }
    /**
     * 다음 스테이지로 이동하는 메소드
     * @param currentStage 이동할 스테이지 번호
     */
    public void goToNextStage(int currentStage) {
        switch (currentStage) {
            case 1:
            case 2:
                itemFallSpeed=1.5;  // 1
                break;
            case 3:
            case 4:
                itemFallSpeed = 1.8;  // 1.5
                MAX_ITEMS = 13;
                break;
            case 5:
            case 6:
                itemFallSpeed = 2;  // 1.8
                MAX_ITEMS = 14;
                break;
            case 7:
            case 8:
                itemFallSpeed = 3;  // 2
                MAX_ITEMS = 14;
                break;

        }
        // 새 스테이지 팝업 표시
        gamePlayPanel.showStagePopup();
    }

    /**
     * 타이머가 남은 시간을 가져오는 메소드
     * @return 타이머의 남은 시간(밀리초 단위)
     */
    public long getTimeLeft() {
        return timerLabel.getMilliSeconds();
    }

    /**
     * 게임 타이머를 관리하는 내부 클래스
     * <p>타이머의 시작, 중지, 시간 감소 등을 관리한다.</p>
     */
    class TimerNum implements Runnable {
        long milliseconds; // 타이머의 밀리초 단위 시간
        boolean running = false; // 타이머 실행 상태
        /**
         * TimerNum 객체를 생성하는 생성자
         * <p>타이머의 초 단위 시간을 설정한다.</p>
         * @param seconds 타이머에 설정할 초 단위 시간
         */
        public TimerNum(int seconds) {
            this.milliseconds = seconds * 1000; // 초를 밀리초로 변환
        }
        /**
         * 타이머의 밀리초 단위 시간을 설정하는 메소드
         * @param millis 설정할 밀리초 단위 시간
         */
        public void setMilliseconds(long millis) {
            this.milliseconds = millis;
        }
        /**
         * 타이머를 리셋하고 시작하는 메소드
         * @param millis 타이머에 설정할 밀리초 단위 시간
         */
        public synchronized void resetAndStart(long millis) {
            this.milliseconds = millis;
            if (!running) {
                running = true;
                new Thread(this).start();
            }
        }
        /**
         * 타이머를 멈추는 메소드
         */
        public synchronized void stopTimer() {
            running = false;
        }
        /**
         * 타이머의 시간을 감소시키는 메소드
         * @param millis 감소시킬 밀리초 단위 시간
         */
        public void decreaseTime(long millis)
        {
            this.milliseconds = Math.max(0, this.milliseconds-millis); // 시간 감소
        }
        /**
         * 타이머가 실행 중인지 확인하는 메소드
         * @return 타이머가 실행 중이면 true, 아니면 false
         */
        public boolean isRunning() {
            return milliseconds > 0;
        }
        @Override
        public void run() {
            while (milliseconds > 0 && running) {
                try {
                    Thread.sleep(10); // 10 밀리초마다 갱신
                    milliseconds -= 10; // 시간 감소
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    running = false; // 인터럽트 발생 시 중단
                }
            }
            running = false; // 타이머 종료
        }
        /**
         * 타이머의 남은 시간을 밀리초 단위로 반환하는 메소드
         * @return 타이머의 남은 밀리초
         */
        public long getMilliSeconds() {
            return milliseconds;
        }
    }

}