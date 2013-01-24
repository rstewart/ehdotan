package com.shopwiki.vaadin;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * @owner rstewart
 */
public class Utils {

    public static final Charset UTF_8 = Charset.forName("utf-8");

    public static String stackTraceAsString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}
