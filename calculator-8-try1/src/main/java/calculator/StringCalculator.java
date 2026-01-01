package calculator;

public class StringCalculator {

    public int calculate(String input) {
        if ((input == null) || input.isEmpty()) {
            return 0;
        }

        Parser parser = new Parser();
        String[] parts = parser.parse(input);

        return sum(parts);
    }

    private int sum(String[] parts) {
        int sum = 0;
        for (String part : parts) {
            if (part.isBlank()) {
                continue;
            }
            sum += NumberValidator.toInt(part);
        }
        return sum;
    }
}
