package lotto;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchcount;
    private final int prize;
    private final boolean bonusMatch;

    Rank(int matchcount, boolean bonusMatch, int prize) {
        this.matchcount = matchcount;
        this.prize = prize;
        this.bonusMatch = bonusMatch;
    }

    public static Rank valueOf(int matchcount, boolean bonusMatch) {
        if (matchcount == 6) {
            return FIRST;
        }
        if (matchcount == 5 && bonusMatch) {
            return SECOND;
        }
        if (matchcount == 5 && !bonusMatch) {
            return THIRD;
        }
        if (matchcount == 4) {
            return FOURTH;
        }
        if (matchcount == 3) {
            return FIFTH;
        }
        return NONE;
    }
}
