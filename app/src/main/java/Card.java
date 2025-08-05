class Card {
    final boolean isHourglass;
    final int hour;

    Card(String type, int hour) {
        this.isHourglass = "HOURGLASS".equals(type);
        this.hour = hour;
    }
}
