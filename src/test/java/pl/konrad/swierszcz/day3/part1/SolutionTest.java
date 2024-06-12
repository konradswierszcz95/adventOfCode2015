package pl.konrad.swierszcz.day3.part1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.konrad.swierszcz.InputReader;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("shouldReturnExpectedResultSource")
    void shouldReturnExpectedResult(String input, int result) {
        //given
        //when//then
        assertThat(Solution.countHousesWithSantaVisit(input))
                .isEqualTo(result);
    }

    private static Stream<Arguments> shouldReturnExpectedResultSource() {
        return Stream.of(
                Arguments.of(">", 2),
                Arguments.of("^>v<", 4),
                Arguments.of("^v^v^v^v^v", 2)
        );
    }

    @Test
    void shouldReturnExpectedResultForAOCInput() {
        //given
        //when//then
        assertThat(Solution.countHousesWithSantaVisit(InputReader.readInput("day3/input.txt").getFirst()))
                .isEqualTo(2081);
    }
}
