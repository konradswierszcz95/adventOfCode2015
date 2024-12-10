package pl.konrad.swierszcz.day13.part1;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.optimalArrangementHappiness(InputReader.readInput("day13/input.txt")))
                .isEqualTo(111754);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForTestData() {
        //given
        //when//then
        assertThat(Solution.optimalArrangementHappiness(InputReader.readInput("day13/ex.txt")))
                .isEqualTo(330);
    }
}
