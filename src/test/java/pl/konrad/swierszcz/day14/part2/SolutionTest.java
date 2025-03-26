package pl.konrad.swierszcz.day14.part2;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCTestData() {
        //given
        //when//then
        assertThat(Solution.maxReindeerDistance(InputReader.readInput("day14/ex.txt"), 1000))
                .isEqualTo(689);
    }

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.maxReindeerDistance(InputReader.readInput("day14/input.txt"), 2503))
                .isEqualTo(1102);
    }
}
