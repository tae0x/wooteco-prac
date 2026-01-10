package menu;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        System.out.println("점심 메뉴 추천을 시작합니다.");
        System.out.println();

        // 1. 코치 이름들 입력
        List<String> coaches;
        while (true) {
            System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
            String input = Console.readLine();

            try {
                String[] names = input.split(",");
                coaches = new ArrayList<>();

                for (String name : names) {
                    String trimmed = name.trim();

                    // 2~4글자 검증
                    if (trimmed.length() < 2 || trimmed.length() > 4) {
                        throw new IllegalArgumentException("[ERROR] 코치 이름은 2~4글자여야 합니다.");
                    }

                    coaches.add(trimmed);
                }

                // 2~5명 검증
                if (coaches.size() < 2 || coaches.size() > 5) {
                    throw new IllegalArgumentException("[ERROR] 코치는 최소 2명 이상 입력해야 합니다.");
                }

                break;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 2. 각 코치별 못 먹는 메뉴 입력 (0~2개)
        Map<String, List<String>> cannotEat = new HashMap<>();

        for (String coach : coaches) {
            while (true) {
                System.out.println();
                System.out.println(coach + "(이)가 못 먹는 메뉴를 입력해 주세요.");
                String input = Console.readLine();

                try {
                    List<String> dislikedMenus = new ArrayList<>();

                    // 빈 값이면 빈 리스트
                    if (!input.trim().isEmpty()) {
                        String[] menus = input.split(",");

                        for (String menu : menus) {
                            dislikedMenus.add(menu.trim());
                        }

                        // 0~2개 검증
                        if (dislikedMenus.size() > 2) {
                            throw new IllegalArgumentException("[ERROR] 못 먹는 메뉴는 최대 2개까지 입력 가능합니다.");
                        }
                    }

                    cannotEat.put(coach, dislikedMenus);
                    break;

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // 3. 5일간 반복:
        //    - 카테고리 랜덤 선택 (같은 카테고리 2회 초과 방지)
        //    - 각 코치별 메뉴 추천 (중복 방지, 못 먹는 메뉴 제외)
        // 3. 5일간 메뉴 추천
        List<Category> weekCategories = new ArrayList<>();  // 5일간 카테고리
        Map<String, List<String>> coachMenus = new HashMap<>();  // 코치별 추천 메뉴

        // 각 코치별 빈 리스트 초기화
        for (String coach : coaches) {
            coachMenus.put(coach, new ArrayList<>());
        }

        // 카테고리 사용 횟수 추적
        Map<Category, Integer> categoryCount = new HashMap<>();
        for (Category category : Category.values()) {
            categoryCount.put(category, 0);
        }

        // 5일간 반복
        for (int day = 0; day < 5; day++) {

            // 카테고리 선택 (같은 카테고리 최대 2회)
            Category selectedCategory = null;
            while (true) {
                int number = Randoms.pickNumberInRange(1, 5);
                Category category = Category.findByNumber(number);

                if (categoryCount.get(category) < 2) {
                    selectedCategory = category;
                    break;
                }
            }

            weekCategories.add(selectedCategory);
            categoryCount.put(selectedCategory, categoryCount.get(selectedCategory) + 1);

            // 각 코치별 메뉴 추천
            for (String coach : coaches) {
                List<String> alreadyRecommended = coachMenus.get(coach);
                List<String> cannotEatList = cannotEat.get(coach);
                List<String> menus = selectedCategory.getMenus();

                // 메뉴 선택 (중복 X, 못 먹는 메뉴 제외)
                String selectedMenu = null;
                while (true) {
                    String menu = Randoms.shuffle(menus).get(0);

                    if (!alreadyRecommended.contains(menu) && !cannotEatList.contains(menu)) {
                        selectedMenu = menu;
                        break;
                    }
                }

                coachMenus.get(coach).add(selectedMenu);
            }
        }

        // 4. 결과 출력

        System.out.println();
        System.out.println("메뉴 추천 결과입니다.");

        // 첫 번째 줄: 요일
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");

        // 두 번째 줄: 카테고리
        System.out.print("[ 카테고리");
        for (Category category : weekCategories) {
            System.out.print(" | " + category.getName());
        }
        System.out.println(" ]");

        // 각 코치별 메뉴
        for (String coach : coaches) {
            System.out.print("[ " + coach);
            List<String> menus = coachMenus.get(coach);
            for (String menu : menus) {
                System.out.print(" | " + menu);
            }
            System.out.println(" ]");
        }


        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }
}
