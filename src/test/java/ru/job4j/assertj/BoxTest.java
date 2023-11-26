package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .startsWith("Sph")
                .endsWith("ere")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .contains("rahed")
                .isMixedCase()
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .doesNotContainAnyWhitespaces()
                .hasSize(4)
                .isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(6, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("unknown")
                .containsWhitespaces()
                .isEqualTo("Unknown object");
    }

    @Test
    void numberOfVerticesIfSphere() {
        Box box = new Box(0, 10);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isGreaterThan(-1)
                .isLessThan(1)
                .isEven()
                .isNotPositive()
                .isNotNegative()
                .isZero();
    }

    @Test
    void numberOfVerticesIfTetrahedron() {
        Box box = new Box(4, 10);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isNotZero()
                .isPositive()
                .isGreaterThan(3)
                .isLessThan(5)
                .isEven()
                .isEqualTo(4);
    }

    @Test
    void numberOfVerticesIfCube() {
        Box box = new Box(8, 10);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isNotZero()
                .isPositive()
                .isStrictlyBetween(7, 9)
                .isEven()
                .isEqualTo(8);
    }

    @Test
    void numberOfVerticesIfUnknown() {
        Box box = new Box(6, 10);
        int vertexNumber = box.getNumberOfVertices();
        assertThat(vertexNumber).isNotZero()
                .isNegative()
                .isOdd()
                .isEqualTo(-1);
    }

    @Test
    void existsIfSphere() {
        Box box = new Box(0, 10);
        boolean exists = box.isExist();
        assertThat(exists).isNotEqualTo(false)
                .isTrue();
    }

    @Test
    void existsIfTetrahedron() {
        Box box = new Box(4, 10);
        boolean exists = box.isExist();
        assertThat(exists).isNotEqualTo(false)
                .isTrue();
    }

    @Test
    void existsIfCube() {
        Box box = new Box(8, 10);
        boolean exists = box.isExist();
        assertThat(exists).isNotEqualTo(false)
                .isTrue();
    }

    @Test
    void doesNotExistIfUnknown() {
        Box box = new Box(6, 10);
        boolean exists = box.isExist();
        assertThat(exists).isNotEqualTo(true)
                .isFalse();
    }

    @Test
    void checkAreaForSphere() {
        int edge = 10;
        Box box = new Box(0, edge);
        double area = box.getArea();
        assertThat(area).isNotZero()
                .isPositive()
                .isCloseTo(4 * Math.PI * (edge * edge), withPrecision(0.001d));
    }

    @Test
    void checkAreaForTetrahedron() {
        int edge = 10;
        Box box = new Box(4, edge);
        double area = box.getArea();
        assertThat(area).isNotZero()
                .isPositive()
                .isCloseTo(Math.sqrt(3) * (edge * edge), withPrecision(0.001d));
    }

    @Test
    void checkAreaForCube() {
        int edge = 10;
        Box box = new Box(8, edge);
        double area = box.getArea();
        assertThat(area).isNotZero()
                .isPositive()
                .isCloseTo(6 * (edge * edge), withPrecision(0.001d));
    }

    @Test
    void checkAreaForUnknown() {
        Box box = new Box(6, 10);
        double area = box.getArea();
        assertThat(area).isCloseTo(0, withPrecision(0.001d));
    }
}