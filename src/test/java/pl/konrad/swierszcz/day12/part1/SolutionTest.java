package pl.konrad.swierszcz.day12.part1;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.getSumOfNumbers(InputReader.readInput("day12/input.txt")))
                .isEqualTo(111754);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForTestData() {
        //given
        //when//then
        assertThat(Solution.getSumOfNumbers(InputReader.readInput("day12/example.txt")))
                .isEqualTo(18);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForSingleElementTestData() {
        //given
        //when//then
        assertThat(Solution.getSumOfNumbers(InputReader.readInput("day12/singleElement.txt")))
                .isEqualTo(6);
    }
}
