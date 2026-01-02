package calculator;

import java.util.regex.Pattern;

public class Parser {
    public String[] parse(String input) {
        String delimiter = ",|:";

        if (!input.startsWith("//")) {
            return input.split(delimiter);
        }

        int delimiterIndex = input.indexOf("\\n");
        if (delimiterIndex == -1) {
            return input.split(delimiter);
        }

        String customDelimiter = input.substring(2, delimiterIndex);
        delimiter = Pattern.quote(customDelimiter); // 특수문자 구분자 방어
        input = input.substring(delimiterIndex + 2);

        return input.split(delimiter);
    }
}
