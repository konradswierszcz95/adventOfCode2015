package pl.konrad.swierszcz.day13.part1;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    private Solution() {}

    public static int optimalArrangementHappiness(List<String> inputs) {
        var relations = inputs.stream()
                .map(input -> input.split(" "))
                .map(splitted -> new Relation(
                        splitted[0],
                        splitted[splitted.length - 1].substring(0, splitted[splitted.length - 1].length() - 1),
                        splitted[2].equals("gain") ? Integer.valueOf(splitted[3]) : -1 * Integer.valueOf(splitted[3])
                ))
                .collect(Collectors.toSet());

        var neighborhoods = relations.stream()
                .map(r -> getNeighbourhoodForRelation(r, relations))
                .distinct()
                .toList();

        return relations.stream()
                .map(relation -> getBiggestHappiness(relation, relation, Map.of(relation, 0), neighborhoods, relations))
                .mapToInt(i -> i)
                .max().orElseThrow(NoSuchElementException::new);
    }

    private static Neighbourhood getNeighbourhoodForRelation(Relation relation, Set<Relation> allRelations) {
        return allRelations.stream()
                .filter(r -> r.neighbour().equals(relation.person()) && relation.neighbour().equals(r.person()))
                .map(r -> new Neighbourhood(List.of(relation.person(), r.person()), relation.happiness() + r.happiness()))
                .findAny().orElseThrow();
    }

    private static int getBiggestHappiness(Relation intialRelation, Relation actualRelation, Map<Relation, Integer> arrangement, List<Neighbourhood> neighbourhoods, Set<Relation> relations) {
        if (relations.size() <= arrangement.size()) {
            int lastNeighbourHappiness = neighbourhoods.stream()
                    .filter(n -> n.persons().containsAll(List.of(intialRelation.person(), actualRelation.person())))
                    .map(Neighbourhood::totalHappiness)
                    .findAny().orElseThrow(() ->
                            new RuntimeException("Missing relation beetween %s and %s not exsist".formatted(intialRelation.person(), actualRelation.person())));

            return arrangement.values().stream()
                    .mapToInt(i -> i)
                    .sum() + lastNeighbourHappiness;
        }

        var remainingRelations = relations.stream()
                .filter(relation -> !relation.equals(actualRelation))
                .collect(Collectors.toSet());

        var availableNeigborhoods = neighbourhoods.stream()
                .filter(neighbourhood -> neighbourhood.persons().contains(actualRelation.person()))
                .filter(neighbourhood -> remainingRelations.stream()
                        .anyMatch(relation -> neighbourhood.persons().contains(relation.person()))
                )
                .toList();

        //todo: cos jest pomieszane tutaj
        return availableNeigborhoods.stream()
                .map(neighbourhood -> getBiggestHappiness(
                        intialRelation,
                        actualRelation,
                        connectPeople(actualRelation, neighbourhood, arrangement),
                        neighbourhoods,
                        remainingRelations
                ))
                .mapToInt(i -> i)
                .max().orElseThrow(RuntimeException::new);
    }

    private static Map<Relation, Integer> connectPeople(Relation actualRelation, Neighbourhood usedNeighbourhood, Map<Relation, Integer> arrangement) {
        return Stream.concat(
                        arrangement.entrySet().stream(),
                        Stream.of(Map.entry(actualRelation, usedNeighbourhood.totalHappiness()))
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
