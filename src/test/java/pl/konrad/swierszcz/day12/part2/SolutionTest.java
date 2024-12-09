package pl.konrad.swierszcz.day12.part2;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.getSumOfNumbers(InputReader.readInput("day12/input.txt")))
                .isEqualTo(65402);
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

    @Test
    void shouldReturnDifferenceShouldBeCorrectForRedGroupTestData() {
        //given
        //when//then
        assertThat(Solution.getSumOfNumbers(InputReader.readInput("day12/redGroup.txt")))
                .isEqualTo(22);
    }
}
