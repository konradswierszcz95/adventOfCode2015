package pl.konrad.swierszcz.day9.part1;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.shortestDistanceBetweenLocations(InputReader.readInput("day9/input.txt")))
                .isEqualTo(1342);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForTestData() {
        //given
        //when//then
        assertThat(Solution.shortestDistanceBetweenLocations(InputReader.readInput("day9/testInput.txt")))
                .isEqualTo(605);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForTestDataFromAnotherSource() {
        //http://algorytmy.ency.pl/artykul/algorytm_helda_karpa
        //given
        //when//then
        assertThat(Solution.shortestDistanceBetweenLocations(InputReader.readInput("day9/testInput2.txt")))
                .isEqualTo(116);
    }
}
