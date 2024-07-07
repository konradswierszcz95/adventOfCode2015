package pl.konrad.swierszcz.day4.part2;

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
        assertThat(Solution.getLowestHashProducingSixZeros(input))
                .isEqualTo(result);
    }

    private static Stream<Arguments> shouldReturnExpectedResultSource() {
        return Stream.of(
                Arguments.of("abcdef", 6742839),
                Arguments.of("pqrstuv", 5714438)
        );
    }

    @Test
    void shouldReturnExpectedResultForAOCInput() {
        //given
        //when//then
        assertThat(Solution.getLowestHashProducingSixZeros(InputReader.readInput("day4/input.txt").getFirst()))
                .isEqualTo(9962624);
    }
}
