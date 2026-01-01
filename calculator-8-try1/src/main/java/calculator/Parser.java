package calculator;

import java.util.regex.Pattern;

public class Parser {
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String CUSTOM_PREFIX = "//";
    private static final String CUSTOM_SUFFIX = "\\n";
    private static final int CUSTOM_PREFIX_LENGTH = 2;

    public String[] parse(String input) {
        String delimiter = DEFAULT_DELIMITER;
        String targetString = input;

        if (input.startsWith(CUSTOM_PREFIX)) {
            int delimiterIndex = input.indexOf(CUSTOM_SUFFIX);
            if (delimiterIndex != -1) {
                String customDelimiter = input.substring(CUSTOM_PREFIX_LENGTH, delimiterIndex);
                delimiter = Pattern.quote(customDelimiter);
                targetString = input.substring(delimiterIndex + CUSTOM_PREFIX_LENGTH);
            }
        }
        return targetString.split(delimiter);
    }

}
