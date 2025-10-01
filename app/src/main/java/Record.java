public class Record {
    int entranceCount;
    int exitCount;

    public Record(int entranceCount, int exitCount) {
        this.entranceCount = entranceCount;
        this.exitCount = exitCount;
    }

    int getTunnelCount(int initialTunnelCount) {
        return initialTunnelCount + entranceCount - exitCount;
    }
}
