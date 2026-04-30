package com.example.cocovoitte;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cocovoitte.Utils.ValidationUtils;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


//Test unitaire pour perso (Mathis)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void emailValide_RetourneTrue() {
        assertTrue(ValidationUtils.isEmailValide("mathis@gmail.com"));
        assertTrue(ValidationUtils.isEmailValide("test.pro@isnum.fr"));
    }

    @Test
    public void emailInvalide_RetourneFalse() {
        assertFalse(ValidationUtils.isEmailValide("mathis-at-gmail.com"));
        assertFalse(ValidationUtils.isEmailValide("mathis@"));
        assertFalse(ValidationUtils.isEmailValide(null));
    }
}