package pl.konrad.swierszcz.day5.part1;

import org.junit.jupiter.api.Test;
import pl.konrad.swierszcz.InputReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnExpectedResult() {
        //given
        //when//then
        assertThat(
                Solution.countNiceStrings(List.of(
                        "aeiouaeiouaeiou",
                        "ugknbfddgicrmopn",
                        "aaa",
                        "jchzalrnumimnmhp",
                        "haegwjzuvuyypxyu",
                        "dvszwmarrgswjxmb"
                ))
        ).isEqualTo(2L);
    }

    @Test
    void shouldReturnExpectedResultForAoCInput() {
        //given
        //when//then
        assertThat(
                Solution.countNiceStrings(InputReader.readInput("day5/input.txt"))
        ).isEqualTo(238L);
    }
}
