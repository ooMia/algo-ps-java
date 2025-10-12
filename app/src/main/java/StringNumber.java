public enum StringNumber {
    zero, one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen,
    sixteen, seventeen, eighteen, nineteen, twenty, twenty_one, twenty_two, twenty_three, twenty_four, twenty_five,
    twenty_six, twenty_seven, twenty_eight, twenty_nine, thirty;

    static String of(int n) {
        for (var e : StringNumber.values())
            if (e.ordinal() == n)
                return e.name().replace('_', ' ');
        return null;
    }
}
