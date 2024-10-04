package pl.konrad.swierszcz.day6.part1;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnCorrectResultForAoCInput() {
        //given
        //when//then
        assertThat(Solution.countOnLightsAfterInstructions(InputReader.readInput("day6/input.txt")))
                .isEqualTo(543903);
    }
}
