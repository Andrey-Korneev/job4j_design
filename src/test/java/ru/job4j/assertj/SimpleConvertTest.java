package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkArrayElements() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).isNotEmpty()
                .allSatisfy(e -> {
                    assertThat(e).doesNotContainAnyWhitespaces();
                    assertThat(e).isLowerCase();
                })
                .allMatch(e -> e.length() > 3)
                .anyMatch(e -> e.startsWith("thr"));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("six", "seven", "eight", "nine", "ten");
        assertThat(list).hasSize(5)
                .containsOnly("seven", "six", "eight", "ten", "nine")
                .containsSequence("seven", "eight", "nine")
                .endsWith("ten")
                .filteredOn(e -> e.length() > 4)
                .last().isEqualTo("eight");
    }

    @Test
    void checkListElements() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("seven", "eight", "nine", "ten", "eleven");
        assertThat(list).isInstanceOf(List.class)
                .allSatisfy(e -> {
                    assertThat(e).isNotBlank();
                    assertThat(e).contains("e");
                })
                .noneMatch(e -> e.startsWith("si"))
                .element(2).isEqualTo("nine");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "three");
        assertThat(set).hasSize(3)
                .doesNotContainNull()
                .doesNotContainSequence("first", "first")
                .containsExactlyInAnyOrder("first", "second", "three")
                .doesNotHaveDuplicates();
    }

    @Test
    void checkSetElements() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "three");
        assertThat(set).isInstanceOf(Set.class)
                .anySatisfy(e -> {
                    assertThat(e).hasSizeGreaterThan(5);
                    assertThat(e).isEqualTo("second");
                })
                .first().isNotEqualTo("four");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSizeGreaterThan(4)
                .containsKeys("first", "four")
                .containsValues(2, 4)
                .doesNotContainEntry("six", 5);
    }

    @Test
    void checkMapElements() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).isInstanceOf(Map.class)
                .allSatisfy((k, v) -> {
                    assertThat(k).doesNotContain("abc");
                    assertThat(v).isNotNegative();
                })
                .anySatisfy((k, v) -> {
                    assertThat(k).isEqualTo("four");
                    assertThat(v).isOdd();
                });
    }
}