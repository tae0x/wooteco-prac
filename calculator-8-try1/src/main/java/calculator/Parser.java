package calculator;

public class Parser {
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String CUSTOM_PREFIX = "//";
    private static final String CUSTOM_SUFFIX = "\\n";
    private static final int CUSTOM_PREFIX_LENGTH = 2;

    public String[] parse(String input) {

        if (!input.startsWith(CUSTOM_PREFIX)) {
            return input.split(DEFAULT_DELIMITER);
        }

        int delimiterIndex = input.indexOf(CUSTOM_SUFFIX);
        if (delimiterIndex == -1) {
            return input.split(DEFAULT_DELIMITER);
        }

        String customDelimiter = input.substring(CUSTOM_PREFIX_LENGTH, delimiterIndex);
        String targetString = input.substring(delimiterIndex + CUSTOM_PREFIX_LENGTH);

        return targetString.split(customDelimiter);
    }

}
