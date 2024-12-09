package pl.konrad.swierszcz.day12.part2;

import java.util.Objects;

public class Exclusion {
    private final int begin;
    private final int end;
    private final int size;

    public Exclusion(int begin, int end, int size) {
        this.begin = begin;
        this.end = end;
        this.size = size;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public int getSize() {
        return size;
    }

    public boolean isOverlapping(Exclusion other) {
        boolean isInternal = (other.begin >= begin && other.end <= end) || (begin >= other.begin && end <= other.end);
        if (isInternal) {
            return true;
        }

        return (begin < other.begin && end > other.begin) || (other.begin < begin && other.end > begin);
    }

    public Exclusion merge(Exclusion other) {
        if (!isOverlapping(other)) {
            throw new RuntimeException("Exclusions are not overlapping and cannot be merged.");
        }
        int newBegin = Integer.min(this.begin, other.getBegin());
        int newEnd = Integer.max(this.end, other.getEnd());
        return new Exclusion(newBegin, newEnd, newEnd - newBegin);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Exclusion)) {
            return false;
        }

        Exclusion c = (Exclusion) o;

        return c.begin == begin && c.end == end;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(begin + end);
    }
}
