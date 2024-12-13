package pl.konrad.swierszcz.day13;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.optimalArrangementHappiness(InputReader.readInput("day13/input.txt")))
                .isEqualTo(709);
    }

    @Test
    void shouldReturnDifferenceEqualToAoCAnswerPart2() {
        //given
        //when//then
        assertThat(Solution.optimalArrangementHappiness(InputReader.readInput("day13/input2.txt")))
                .isEqualTo(668);
    }

    @Test
    void shouldReturnDifferenceShouldBeCorrectForTestData() {
        //given
        //when//then
        assertThat(Solution.optimalArrangementHappiness(InputReader.readInput("day13/ex.txt")))
                .isEqualTo(330);
    }
}
