package GradeHunter;

// ItemType.java

/** 각 아이템 타입과 이에 해당하는 이미지 경로 및 효과 값을 정의
 * @author 박현민
 * */
public enum ItemType {
    BLUE("images/hw_b.png", 10),
    GREEN("images/hw_g.png", 5),
    YELLOW("images/hw_y.png", -3),
    RED("images/hw_r.png", -5),
    TARDY("images/timer.png", -3), // 시간 감소 효과
    PRESENTATION("images/ppt.png", 15);

    private final String imagePath; // 아이템 이미지 경로
    private final int effectValue; // 아이템의 효과 값

    /** ItemType 생성자
     * <p>각 아이템 타입에 해당하는 이미지 경로와 효과 값을 설정한다.</p>
     * @param imagePath 아이템 이미지 경로
     * @param effectValue 아이템의 효과 값
     * */
    ItemType(String imagePath, int effectValue) {
        this.imagePath = imagePath;
        this.effectValue = effectValue;
    }

    /**
     * 아이템 이미지의 경로를 반환하는 메소드
     * @return 아이템 이미지의 파일 경로
     * */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * 아이템의 효과 값을 반환하는 메소드
     * @return 아이템의 게임 내 효과 값
     * */
    public int getEffectValue() {
        return effectValue;
    }
}
