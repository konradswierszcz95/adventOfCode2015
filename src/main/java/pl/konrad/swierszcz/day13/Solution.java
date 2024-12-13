package pl.konrad.swierszcz.day13;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

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
                .map(relation -> {
                    var calculatedHappiness = maxHappiness(relation.person(), neighborhoods, relations);
                    int additionalHappiness = getHappinessForPair(neighborhoods, List.of(relation.person(), calculatedHappiness.person()));
                    return calculatedHappiness.happiness() + additionalHappiness;
                })
                .mapToInt(i -> i)
                .max().orElseThrow(NoSuchElementException::new);
    }

    private static Neighbourhood getNeighbourhoodForRelation(Relation relation, Set<Relation> allRelations) {
        return allRelations.stream()
                .filter(r -> r.neighbour().equals(relation.person()) && relation.neighbour().equals(r.person()))
                .map(r -> new Neighbourhood(List.of(relation.person(), r.person()), relation.happiness() + r.happiness()))
                .findAny().orElseThrow();
    }

    private static int getHappinessForPair(List<Neighbourhood> neighbourhoods, List<String> persons) {
        return neighbourhoods.stream()
                .filter(n -> n.persons().containsAll(persons))
                .map(n -> n.totalHappiness())
                .findAny().orElseThrow();
    }

    private static Happiness maxHappiness(String person, List<Neighbourhood> neighbourhoods, Set<Relation> relations) {
        if (relations.isEmpty()) {
            return new Happiness(person, 0);
        }

        var personRelations = relations.stream()
                .filter(r -> r.person().equals(person))
                .collect(Collectors.toSet());

        var remainingRelations = relations.stream()
                .filter(r -> !r.person().equals(person) && !r.neighbour().equals(person))
                .collect(Collectors.toSet());


        return personRelations.stream()
                .map(r -> {
                    var actualHappiness = getHappinessForPair(neighbourhoods, List.of(r.person(), r.neighbour()));
                    var calculatedHappiness = maxHappiness(r.neighbour(), neighbourhoods, remainingRelations);

                    return new Happiness(
                            calculatedHappiness.person(),
                            calculatedHappiness.happiness() + actualHappiness
                    );
                })
                .max(Comparator.comparing(Happiness::happiness)).orElseThrow();
    }
}
