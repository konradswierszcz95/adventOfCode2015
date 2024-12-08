package pl.konrad.swierszcz.day12.part2;

public class Exclusion {
    private final int begin;
    private final int end;

    public Exclusion(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public boolean isOverlapping(Exclusion anotherExclusion) {
        return (this.begin >= anotherExclusion.getBegin() && this.begin <= anotherExclusion.getEnd()) ||
                (anotherExclusion.getBegin() >= this.begin && anotherExclusion.getEnd() <= this.end);
    }

    public Exclusion merge(Exclusion anotherExclusion) {
        if (!isOverlapping(anotherExclusion)) {
            throw new RuntimeException("Exclusions are not overlapping and cannot be merged.");
        }
        return new Exclusion(Integer.min(this.begin, anotherExclusion.getBegin()), Integer.max(this.end, anotherExclusion.getEnd()));
    }
}
