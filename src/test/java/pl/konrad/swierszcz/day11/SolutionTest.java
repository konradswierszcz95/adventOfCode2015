package pl.konrad.swierszcz.day11;

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
        assertThat(MapSolution.initGettingNextCorrectPassword("vzbxkghb"))
                .isEqualTo("vzbxxyzz");
    }

    @Test
    void shouldReturnDifferenceEqualToAoCAnswerPartTwo() {
        //given
        //when//then
        assertThat(MapSolution.initGettingNextCorrectPassword("vzbxxyzz"))
                .isEqualTo("vzcaabcc");
    }

    @ParameterizedTest
    @MethodSource("aocExamplesData")
    void shouldReturnDifferenceShouldBeCorrectForTestData(String input, String result) {
        //given
        //when//then
        assertThat(MapSolution.initGettingNextCorrectPassword(input))
                .isEqualTo(result);
    }

    private static Stream<Arguments> aocExamplesData() {
        return Stream.of(
                Arguments.of("abcdefgh", "abcdffaa"),
                Arguments.of("ghijklmn", "ghjaabcc")
        );
    }
}
