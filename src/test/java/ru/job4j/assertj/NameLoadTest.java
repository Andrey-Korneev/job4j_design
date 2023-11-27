package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyArray() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[0];
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("is empty");
    }

    @Test
    void ifDoesNotContainEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"name=Billy", "surname=Bons", "position:navigator"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("not contain the symbol '='")
                .hasMessageContaining("position:navigator");
    }

    @Test
    void ifDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"name=Billy", "=Bons", "position=navigator"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("not contain a key")
                .hasMessageContaining("=Bons");
    }

    @Test
    void ifDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[] {"name=", "surname=Bons", "position=navigator"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("not contain a value")
                .hasMessageContaining("name=");
    }
}