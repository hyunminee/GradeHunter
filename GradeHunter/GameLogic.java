package GradeHunter;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class GameLogic {
    private List<Item> items; // 게임 아이템 리스트
    private Player character; // 게임 플레이어 캐릭터
    private TimerNum timerLabel; // 타이머 라벨
    private int currentStage; // 현재 게임의 스테이지 번호
    private final int MAX_STAGE; // 최대 스테이지 번호
    private double stageTime = 60000; // 각 스테이지의 지속 시간 (초)
    private int gaugeValue = 0; // 현재 게이지 값
    private final int GAUGE_PER_STAGE = 50; // 스테이지 당 필요한 게이지 증가량
    private int maxGaugeValue= currentStage * GAUGE_PER_STAGE; // 최대 게이지 값 (스테이지에 따라 변함)
    private int itemFallSpeed=2; // 아이템 하강 속도
    private final int PANEL_WIDTH; // 게임 패널의 너비
    private final int ITEM_WIDTH;  // 아이템의 너비
    protected Random rand; // 랜덤 이벤트 및 아이템 위치 생성에 사용될 Random 객체
    private static final int MAX_ITEMS = 10; // 화면에 표시될 수 있는 최대 아이템 수
    private GamePlayPanel gamePlayPanel; // GamePlayPanel 참조


    public GameLogic(Player character, List<Item> items, JPanel parentPanel, int initialTime, int panelWidth, int itemWidth, GamePlayPanel panel) {
        this.character = character;
        this.items = items;
        this.currentStage = 1; // 현재 스테이지
        this.MAX_STAGE=8;
        this.PANEL_WIDTH = panelWidth;
        this.ITEM_WIDTH = itemWidth;
        rand = new Random();
        this.gamePlayPanel = panel;

        // TimerNum 인스턴스 생성 및 스레드 시작
        timerLabel = new TimerNum(initialTime);
        Thread timerThread = new Thread(timerLabel);
        timerThread.start();
    }

    // 게임 업데이트 로직
    public void updateGame() {
        // 아이템과 플레이어의 충돌 처리, 게임 상태 업데이트 등
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
        // 아이템과 플레이어의 충돌 처리 및 상태 업데이트 후 GamePlayPanel의 화면을 갱신
        gamePlayPanel.repaint();
        // 캐릭터 상태 업데이트 (예: 이동, 애니메이션 상태 변경 등)
        // 매 프레임마다 캐릭터의 상태를 업데이트
        character.update();
        // 아이템 생성 및 관리
        manageItems();
        // 스테이지 관리 로직
        manageStages();
    }
    // 아이템 생성 및 관리 메소드
    private void manageItems() {
        if ((timerLabel.getMilliSeconds()>0)&&(items.size() < MAX_ITEMS)) { // MAX_ITEMS: 동시에 화면에 표시될 수 있는 최대 아이템 수
            // 아이템 생성 로직
            createRandomItem();
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

    private void createRandomItem() {

        int x; // 아이템이 화면 밖으로 나가지 않도록 계산
        int y = 0; // 화면 상단에서 시작

        boolean positionOverlap;
        do {
            x = rand.nextInt(PANEL_WIDTH - ITEM_WIDTH); // 아이템이 화면 밖으로 나가지 않도록 계산
            positionOverlap = checkPositionOverlap(x);
        } while (positionOverlap);


        // 아이템 타입 결정 로직
        ItemType[] types = ItemType.values();
        ItemType type;
        if (currentStage <= 4) {
            // 스테이지 1~4: BLUE, GREEN, YELLOW, RED 아이템만 나옴
            List<ItemType> allowedTypes = new ArrayList<>(Arrays.asList(types));
            allowedTypes.remove(ItemType.TARDY);
            allowedTypes.remove(ItemType.PRESENTATION);
            type = allowedTypes.get(rand.nextInt(allowedTypes.size()));
        } else {
            // 스테이지 5~8: 모든 아이템이 나옴
            type = types[rand.nextInt(types.length)];
        }

        // 아이템 생성
        Item newItem = new Item(type.getImagePath(), x, y, type, itemFallSpeed);
        items.add(newItem);
    }
    // 위치 겹침 확인 메소드
    private boolean checkPositionOverlap(int x) {
        for (Item existingItem : items) {
            if (Math.abs(existingItem.getX()-x)<ITEM_WIDTH) {
                return true; // 겹치는 경우
            }
        }
        return false; // 겹치지 않는 경우
    }


    // 아이템에 따른 추가 처리 (예: 점수 추가, 효과 적용 등)
    private void processItemEffect(Item item) {
        // 아이템의 효과를 적용하는 로직
        // TARDY 아이템의 경우 시간 감소, 그 외에는 게이지 증가/감소
        if (item.getType() == ItemType.TARDY) {
            stageTime = Math.max(0, stageTime - Math.abs(item.getEffectValue()));
        } else {
            // 아이템이 게이지를 증가시키는 경우 최대값을 초과하지 않도록 한다.
            if (item.getEffectValue() > 0) {
                gaugeValue = Math.min(maxGaugeValue, gaugeValue + item.getEffectValue());
            } else {
                // 아이템이 게이지를 감소시키는 경우 0 미만으로 내려가지 않도록 한다.
                gaugeValue = Math.max(0, gaugeValue + item.getEffectValue());
            }
        }
    }

    private void manageStages() {
        System.out.println("현재 스테이지="+currentStage);
        // 예: 스테이지 종료 조건 확인 및 다음 스테이지로 이동
        maxGaugeValue = currentStage * GAUGE_PER_STAGE; // 최대 게이지 값 업데이트

        // 스테이지 종료 조건 확인 및 다음 스테이지로 이동
        if (stageTime <= 0 && gaugeValue != maxGaugeValue) {
            gameOver();
        }
        if (currentStage == MAX_STAGE && gaugeValue >= maxGaugeValue) {
            gameClear();
        } else if (gaugeValue >= maxGaugeValue) {
            currentStage++;
            gaugeValue = 0; // 게이지 초기화
//            gamePlayPanel.showStagePopup();
            goToNextStage(currentStage);
        }


    }

    private void goToNextStage(int currentStage) {
        System.out.println("현재 스테이지의 아이템 하강속도="+itemFallSpeed);
        switch (currentStage) {
            case 1:
            case 2:
                itemFallSpeed = 2;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                itemFallSpeed = 4;
                break;
            case 7:
            case 8:
                itemFallSpeed = 5;
                break;
        }

//        ItemType itemType;
//        if (currentStage <= 4) {
//            // 스테이지 1~4: BLUE, GREEN, YELLOW, RED 아이템만 나옴
//            itemType = ItemType.values()[rand.nextInt(4)]; // BLUE, GREEN, YELLOW, RED 중 하나를 랜덤으로 선택
//        } else {
//            // 스테이지 5~8: 모든 아이템이 나옴
//            itemType = ItemType.values()[rand.nextInt(ItemType.values().length)]; // 모든 ItemType 중 하나를 랜덤으로 선택
//        }
    }

    // 타이머 시간 가져오기
    public long getTimeLeft() {
        return timerLabel.getMilliSeconds();
    }

    // TimerNum 클래스 정의
    class TimerNum implements Runnable {
        long milliseconds;

        public TimerNum(int seconds) {
            this.milliseconds = seconds*1000; // 초를 밀리초로 변환
        }

        @Override
        public void run() {
            while (milliseconds > 0) {
                try {
                    Thread.sleep(10); // 10 밀리초마다 갱신
                    milliseconds -= 10; // 시간 감소
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public long getMilliSeconds() {
            return milliseconds;
        }
    }
    /** 게임 오버 메소드 */
    private void gameOver() {
    }
    /** 게임 클리어 메소드 */
    private void gameClear() {
    }
}
