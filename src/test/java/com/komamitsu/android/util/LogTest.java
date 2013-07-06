package com.komamitsu.android.util;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogTest {
    private static final String TAG = LogTest.class.getSimpleName();
    private static final PrintStream ORIG_STDOUT = System.out;
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream printStream;

    @Before
    public void setUp() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
    }

    @After
    public void tearDown() throws Exception {
        printStream.close();
    }

    private void checkStdout(Pattern pattern) {
        System.setOut(ORIG_STDOUT);
        // System.out.println(new String(byteArrayOutputStream.toByteArray()));
        Matcher matcher = pattern.matcher(new String(byteArrayOutputStream.toByteArray()));
        assertTrue(matcher.find());
    }

    @Test
    public void testV() {
        Log.v(TAG, "test V");
        checkStdout(Pattern.compile("^V \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest test V$"));
    }

    @Test
    public void testD() {
        Log.d(TAG, "test D");
        checkStdout(Pattern.compile("^D \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest test D$"));
    }

    @Test
    public void testI() {
        Log.i(TAG, "test I");
        checkStdout(Pattern.compile("^I \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest test I$"));
    }

    @Test
    public void testW() {
        Log.w(TAG, "test W");
        checkStdout(Pattern.compile("^W \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest test W$"));
    }

    @Test
    public void testWWithException() {
        Log.w(TAG, "test W", new RuntimeException("WWWW"));
        checkStdout(Pattern.compile(
                "^W \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest test W\n" +
                        "W \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest java.lang.RuntimeException: WWWW\n" +
                        "\\sat com.komamitsu.android.util.LogTest.testWWithException\\(LogTest.java:\\d+\\)"
                ));
    }

    @Test
    public void testE() {
        Log.e(TAG, "test E");
        checkStdout(Pattern.compile("^E \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest test E$"));
    }

    @Test
    public void testEWithException() {
        Log.e(TAG, "test E", new RuntimeException("EEEE"));
        checkStdout(Pattern.compile(
                "^E \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest test E\n" +
                        "E \\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3} LogTest java.lang.RuntimeException: EEEE\n" +
                        "\\sat com.komamitsu.android.util.LogTest.testEWithException\\(LogTest.java:\\d+\\)"
                ));
    }
}
