package pl.konrad.swierszcz.day10.part1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTest {

    @Test
    void shouldReturnDifferenceEqualToAoCAnswer() {
        //given
        //when//then
        assertThat(Solution.modifyStringByElvesRules(20, "1321131112"))
                .isEqualTo("11131221133112");
    }

    @ParameterizedTest
    @MethodSource("aocExamplesData")
    void shouldReturnDifferenceShouldBeCorrectForTestData(String input, String result) {
        //given
        //when//then
        assertThat(Solution.modifyStringByElvesRules(1, input))
                .isEqualTo(result);
    }

    private static Stream<Arguments> aocExamplesData() {
        return Stream.of(
                Arguments.of("1", "11"),
                Arguments.of("11", "21"),
                Arguments.of("21", "1211"),
                Arguments.of("1211", "111221"),
                Arguments.of("111221", "312211")
        );
    }
}
