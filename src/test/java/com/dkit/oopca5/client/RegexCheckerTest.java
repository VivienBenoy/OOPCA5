package com.dkit.oopca5.client;

import com.dkit.oopca5.core.dto.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegexCheckerTest {

    @Test
    public void verifyCAONumber() {
        RegexChecker reg=new RegexChecker();
        int caoNumber=12345678;
        assertEquals(true,reg.verifyCAONumber(caoNumber));
    }

    @Test
    public void verifyDateOfBirth() {
        RegexChecker reg=new RegexChecker();
        String dateOfBirth="2000-12-12";
        assertEquals(true,reg.verifyDateOfBirth(dateOfBirth));
    }

    @Test
    public void verifyPasswrd() {
        RegexChecker reg=new RegexChecker();
       String password="1231241wfaf";
        assertEquals(true,reg.verifyPasswrd(password));
    }
}