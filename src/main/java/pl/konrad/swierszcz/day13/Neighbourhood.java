package pl.konrad.swierszcz.day13;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public record Neighbourhood(List<String> persons, int totalHappiness) {
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Neighbourhood)) {
            return false;
        }

        Neighbourhood c = (Neighbourhood) o;

        return c.totalHappiness == totalHappiness && new HashSet<>(c.persons).containsAll(((Neighbourhood) o).persons);
    }

    @Override
    public int hashCode() {
        int personsHash = persons.stream()
                .mapToInt(Objects::hashCode)
                .sum();
        return Objects.hashCode(totalHappiness + personsHash);
    }
}
