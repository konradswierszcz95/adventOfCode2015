package pl.konrad.swierszcz.day8.part2;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

public class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.getCharactersDifference(InputReader.readInput("day8/input.txt")))
                .isEqualTo(2074);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForTestData() {
        //given
        //when//then
        assertThat(Solution.getCharactersDifference(InputReader.readInput("day8/testInput.txt")))
                .isEqualTo(19);
    }
}
