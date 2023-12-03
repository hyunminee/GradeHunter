package GradeHunter;

import javax.swing.ImageIcon;
import java.awt.*;
import java.net.URL;
/**
 * 게임 내 아이템을 나타내는 클래스
 * @author 박현민
 * */
public class Item {
    private Image image; // 아이템의 이미지를 나타내는 객체
    private int x, y; // 아이템의 화면 내 위치 (x, y 좌표)
    private double deltaY; // 아이템의 Y축 이동 속도 (하강 속도)
    private int effectValue; // 아이템의 효과 값 (게이지 증가/감소, 시간 조절 등)
    private final ItemType type; // 아이템의 타입 (BLUE, GREEN 등)

    /**
     * 아이템 객체를 생성하는 생성자
     * <p>아이템의 이미지, 위치, 타입, 이동 속도를 초기화한다.</p>
     * @param imagePath 아이템의 이미지 경로
     * @param x 아이템의 초기 x 좌표
     * @param y 아이템의 초기 y 좌표
     * @param type 아이템의 타입
     * @param speed 아이템의 이동 속도
     */
    public Item(String imagePath, int x, int y, ItemType type, double speed) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.effectValue = type.getEffectValue(); // 아이템 효과 값 반환
        this.deltaY = speed; // 하강 속도 초기화, 스테이지별로 조정 가능
        loadImage(imagePath); // 이미지 로딩
    }

    /**
     * 아이템의 이미지를 로드하는 메소드
     * @param imagePath 로드할 이미지의 경로
     * */
    private void loadImage(String imagePath) {
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            ImageIcon ii = new ImageIcon(imageUrl);
            image = ii.getImage(); // 이미지 로드하고 저장
        } else {
            // 이미지를 찾을 수 없을 경우의 처리
            System.err.println("Image not found: " + imagePath);
            // 예외 처리 또는 기본 이미지 설정 등의 로직
        }

        ImageIcon ii = new ImageIcon(getClass().getResource(imagePath));
        image = ii.getImage(); // 이미지 로드하고 저장
    }

    /**
     * 아이템의 위치를 업데이트하는 메소드
     * <p>아이템을 지정된 속도로 하강시킨다.</p>
     */
    public void move() {
        y += deltaY; // 아이템을 하강시킴
        // 화면 밖으로 나가는 처리는 actionPerformed에서 수행
    }
    /** 아이템을 화면에 그리는 메소드 */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
    /**
     * 아이템의 x 좌표를 반환하는 메소드
     * @return 아이템의 x 좌표
     */
    public int getX() {
        return x;
    }
    /**
     * 아이템의 y 좌표를 반환하는 메소드
     * @return 아이템의 y 좌표
     */
    public int getY() {
        return y;
    }
    /**
     * 아이템의 효과 값을 반환하는 메소드
     * @return 아이템의 효과 값
     */
    public int getEffectValue() {
        return effectValue;
    }

    /**
     * 아이템의 타입을 반환하는 메소드
     * @return 아이템의 타입
     */
    public ItemType getType() {
        return type;
    }

    /**
     * 아이템이 화면 내에 있는지 확인하는 메소드
     * @return 아이템이 화면 내에 있으면 true, 아니면 false
     */
    public boolean isOnScreen() {
        final int screenHeight = 720; // 화면 높이
        return y >= 0 && y < screenHeight; // 아이템이 화면 안에 있는지 확인

    }
    /**
     * 아이템의 너비를 반환하는 메소드
     * <p>아이템 타입에 따라 너비가 달라진다.</p>
     * @return 아이템의 너비
     */
    public int getWidth() {
        if (getType() == GradeHunter.ItemType.BLUE ||
                getType() == GradeHunter.ItemType.GREEN ||
                getType() == GradeHunter.ItemType.YELLOW ||
                getType() == GradeHunter.ItemType.RED) {
            return 48;
        } else if (getType() == GradeHunter.ItemType.TARDY) {
            return 39;
        } else if (getType() == ItemType.PRESENTATION){
            return 46;
        }
        return 0;
    }
    /**
     * 아이템의 높이를 반환하는 메소드
     * <p>아이템 타입에 따라 높이가 달라진다.</p>
     * @return 아이템의 높이
     */
    public int getHeight() {
        if (getType() == GradeHunter.ItemType.BLUE ||
                getType() == GradeHunter.ItemType.GREEN ||
                getType() == GradeHunter.ItemType.YELLOW ||
                getType() == GradeHunter.ItemType.RED) {
            return 62;
        } else if (getType() == GradeHunter.ItemType.TARDY) {
            return 49;
        } else if (getType() == ItemType.PRESENTATION){
            return 50;
        }
        return 0;
    }
}