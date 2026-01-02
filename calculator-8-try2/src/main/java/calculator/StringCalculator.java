package calculator;

public class StringCalculator {

    public int calculate(String input) {
        if (input == null || input.isEmpty()) { // 빈 문자열 방어(안하면 에러)
            return 0;
        }

        Parser parser = new Parser();
        String[] parts = parser.parse(input);

        return sum(parts);
    }


    private int sum(String[] parts) {
        int sum = 0;
        for (String part : parts) {
            if (part.trim().isEmpty()) { // "1, ,2" 같은 경우 빈 문자열 무시
                continue;
            }
            sum += NumberValidator.toInt(part);
        }
        return sum;
    }

}
