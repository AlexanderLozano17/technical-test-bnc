package com.ecommerce.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.ecommerce.utils.LogHelper;

public class LogHelperTest {

	static class DummyClass {} // Clase ficticia para probar el nombre de clase

    private final Class<?> clazz = DummyClass.class;
    private final String method = "testMethod";
    private final String detail = "Test detail";

    @Test
    void testStart() {
        String expected = "→ START | DummyClass::testMethod()";
        String actual = LogHelper.start(clazz, method);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testEnd() {
        String expected = "← END | DummyClass::testMethod()";
        String actual = LogHelper.end(clazz, method);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testSuccess() {
        String expected = "✓ SUCCESS | DummyClass::testMethod() - Test detail";
        String actual = LogHelper.success(clazz, method, detail);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testWarn() {
        String expected = "⚠ WARNING | DummyClass::testMethod() - Test detail";
        String actual = LogHelper.warn(clazz, method, detail);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testError() {
        String expected = "✖ ERROR | DummyClass::testMethod() - Test detail";
        String actual = LogHelper.error(clazz, method, detail);
        assertThat(actual).isEqualTo(expected);
    }
}
