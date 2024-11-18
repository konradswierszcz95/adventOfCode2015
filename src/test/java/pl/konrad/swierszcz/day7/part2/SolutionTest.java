package pl.konrad.swierszcz.day7.part2;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectValueForAoCInput() {
        //given
        //when//then
        assertThat(Solution.getASignalValue(InputReader.readInput("day7/input.txt")))
                .isEqualTo(40149);
    }
}
