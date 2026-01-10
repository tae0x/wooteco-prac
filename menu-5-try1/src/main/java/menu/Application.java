package menu;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

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

        // 2. 각 코치별 못 먹는 메뉴 입력

        // 3. 5일간 반복:
        //    - 카테고리 랜덤 선택 (같은 카테고리 2회 초과 방지)
        //    - 각 코치별 메뉴 추천 (중복 방지, 못 먹는 메뉴 제외)

        // 4. 결과 출력

        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }
}
