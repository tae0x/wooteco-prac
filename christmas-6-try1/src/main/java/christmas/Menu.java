package christmas;

public enum Menu {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6000, "APPETIZER"),
    TAPAS("타파스", 5500, "APPETIZER"),
    CAESAR_SALAD("시저샐러드", 8000, "APPETIZER"),

    // 메인
    T_BONE_STEAK("티본스테이크", 55000, "MAIN"),
    BBQ_RIBS("바비큐립", 54000, "MAIN"),
    SEAFOOD_PASTA("해산물파스타", 35000, "MAIN"),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, "MAIN"),

    // 디저트
    CHOCO_CAKE("초코케이크", 15000, "DESSERT"),
    ICE_CREAM("아이스크림", 5000, "DESSERT"),

    // 음료
    ZERO_COLA("제로콜라", 3000, "BEVERAGE"),
    RED_WINE("레드와인", 60000, "BEVERAGE"),
    CHAMPAGNE("샴페인", 25000, "BEVERAGE");

    private final String name;
    private final int price;
    private final String category;

    Menu(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    // 이름으로 메뉴 찾기
    public static Menu findByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
