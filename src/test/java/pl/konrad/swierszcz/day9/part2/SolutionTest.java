package pl.konrad.swierszcz.day9.part2;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.longestDistanceBetweenLocations(InputReader.readInput("day9/input.txt")))
                .isEqualTo(736);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForTestData() {
        //given
        //when//then
        assertThat(Solution.longestDistanceBetweenLocations(InputReader.readInput("day9/testInput.txt")))
                .isEqualTo(982);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForTestDataFromAnotherSource() {
        //http://algorytmy.ency.pl/artykul/algorytm_helda_karpa
        //given
        //when//then
        assertThat(Solution.longestDistanceBetweenLocations(InputReader.readInput("day9/testInput2.txt")))
                .isEqualTo(153);
    }
}
