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
            sum += calculateOne(part);
        }
        return sum;
    }

    private int calculateOne(String part) {
        if (part.isBlank()) {
            return 0;
        }
        return NumberValidator.toInt(part);
    }
}
