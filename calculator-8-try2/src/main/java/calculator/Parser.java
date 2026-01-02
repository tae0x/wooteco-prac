package calculator;

import java.util.regex.Pattern;

public class Parser {
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String CUSTOM_PREFIX = "//";
    private static final String CUSTOM_SUFFIX = "\\n";
    private static final int CUSTOM_PREFIX_LENGTH = 2;

    public String[] parse(String input) {
        String delimiter = DEFAULT_DELIMITER;

        if (!input.startsWith(CUSTOM_PREFIX)) {
            return input.split(delimiter);
        }

        int delimiterIndex = input.indexOf(CUSTOM_SUFFIX);
        if (delimiterIndex == -1) {
            return input.split(delimiter);
        }

        String customDelimiter = input.substring(CUSTOM_PREFIX_LENGTH, delimiterIndex);
        delimiter = Pattern.quote(customDelimiter); // 특수문자 구분자 방어
        input = input.substring(delimiterIndex + CUSTOM_PREFIX_LENGTH);

        return input.split(delimiter);
    }
}
