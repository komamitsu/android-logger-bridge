package org.komamitsu.android.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static final Method V;
    private static final Method D;
    private static final Method I;
    private static final Method W;
    private static final Method W2;
    private static final Method E;
    private static final Method E2;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SS");
    static {
        Method tmpV = null;
        Method tmpD = null;
        Method tmpI = null;
        Method tmpW = null;
        Method tmpW2 = null;
        Method tmpE = null;
        Method tmpE2 = null;
        if (System.getProperty("java.vm.name").equals("Dalvik")) {
            try {
                tmpV = android.util.Log.class.getMethod("v", String.class, String.class);
                tmpD = android.util.Log.class.getMethod("d", String.class, String.class);
                tmpI = android.util.Log.class.getMethod("i", String.class, String.class);
                tmpW = android.util.Log.class.getMethod("w", String.class, String.class);
                tmpW2 = android.util.Log.class.getMethod("w", String.class, String.class, Throwable.class);
                tmpE = android.util.Log.class.getMethod("e", String.class, String.class);
                tmpE2 = android.util.Log.class.getMethod("e", String.class, String.class, Throwable.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        V = tmpV;
        D = tmpD;
        I = tmpI;
        W = tmpW;
        W2 = tmpW2;
        E = tmpE;
        E2 = tmpE2;
    }

    public static void v(String tag, String msg) {
        log(V, "V", tag, msg, null);
    }

    public static void d(String tag, String msg) {
        log(D, "D", tag, msg, null);
    }

    public static void i(String tag, String msg) {
        log(I, "I", tag, msg, null);
    }

    public static void w(String tag, String msg) {
        log(W, "W", tag, msg, null);
    }

    public static void w(String tag, String msg, Throwable e) {
        log(W2, "W", tag, msg, e);
    }

    public static void e(String tag, String msg) {
        log(E, "E", tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable e) {
        log(E2, "E", tag, msg, e);
    }

    private static void log(Method m, String level, String tag, String msg, Throwable ex) {
        if (m != null) {
            try {
                if (ex != null) {
                    m.invoke(null, tag, msg, ex);
                }
                else {
                    m.invoke(null, tag, msg);
                }
                return;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        fallbackLog(level, tag, msg);
        if (ex != null) {
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            fallbackLog(level, tag, stringWriter.toString());

            /*
             * fallbackLog(level, tag, ex.getMessage()); for (StackTraceElement elm : ex.getStackTrace()) { fallbackLog(level, tag, elm.toString()); }
             */
        }
    }

    private static void fallbackLog(String level, String tag, String msg) {
        System.out.println(String.format("%s %s %s %s", level, getCalenderString(), tag, msg));
    }

    private static String getCalenderString() {
        synchronized (SimpleDateFormat.class) {
            return dateFormat.format(new Date());
        }
    }
}
