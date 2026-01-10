package menu;

import camp.nextstep.edu.missionutils.Console;
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

        // 4. 결과 출력

        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }
}
