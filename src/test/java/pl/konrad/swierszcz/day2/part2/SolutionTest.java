package pl.konrad.swierszcz.day2.part2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.konrad.swierszcz.InputReader;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("shouldReturnExpectedResultSource")
    void shouldReturnExpectedResult(List<String> input, long result) {
        //given
        //when//then
        assertThat(Solution.countRequiredRibbon(input))
                .isEqualTo(result);
    }

    private static Stream<Arguments> shouldReturnExpectedResultSource() {
        return Stream.of(
                Arguments.of(List.of("2x3x4"), 34),
                Arguments.of(List.of("1x1x10"), 14)
        );
    }

    @Test
    void shouldReturnExpectedResultForAOCInput() {
        //given
        //when//then
        assertThat(Solution.countRequiredRibbon(InputReader.readInput("day2/input.txt")))
                .isEqualTo(3737498);
    }
}
