package pl.konrad.swierszcz.day1.part1;

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
    void shouldReturnExpectedResult(String input, long result) {
        //given
        //when//then
        assertThat(Solution.countFloor(input))
                .isEqualTo(result);
    }

    private static Stream<Arguments> shouldReturnExpectedResultSource() {
        return Stream.of(
                Arguments.of("(())", 0L),
                Arguments.of("()()", 0L),
                Arguments.of("(((", 3L),
                Arguments.of("(()(()(", 3L),
                Arguments.of("))(((((", 3L),
                Arguments.of("())", -1L),
                Arguments.of("))(", -1L),
                Arguments.of(")))", -3L),
                Arguments.of(")())())", -3L)
        );
    }

    @Test
    void shouldReturnExpectedResultForAOCInput() {
        //given
        //when//then
        assertThat(Solution.countFloor(InputReader.readInput("day1/input.txt").getFirst()))
                .isEqualTo(138L);
    }
}
