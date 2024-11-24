package pl.konrad.swierszcz.day10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswerPartOne() {
        //given
        //when//then
        assertThat(Solution.getSizeOfInputModifiedByElvesRules(40, "1321131112"))
                .isEqualTo(492982);
    }

    @Test
    void shouldReturnDifferenceEqualToAoCAnswerPartTwo() {
        //given
        //when//then
        assertThat(Solution.getSizeOfInputModifiedByElvesRules(50, "1321131112"))
                .isEqualTo(6989950);
    }

    @ParameterizedTest
    @MethodSource("aocExamplesData")
    void shouldReturnDifferenceShouldBeCorrectForTestData(String input, Integer result) {
        //given
        //when//then
        assertThat(Solution.getSizeOfInputModifiedByElvesRules(1, input))
                .isEqualTo(result);
    }

    private static Stream<Arguments> aocExamplesData() {
        return Stream.of(
                Arguments.of("1", 2),
                Arguments.of("11", 2),
                Arguments.of("21", 4),
                Arguments.of("1211", 6),
                Arguments.of("111221", 6)
        );
    }
}
