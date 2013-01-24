package com.shopwiki.vaadin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;

/**
 * Hideous hack so Vaadin can use Cookies
 *
 * @owner rstewart
 */
public abstract class CookieApplication extends Application implements HttpServletRequestListener {

    private transient ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>();
    private transient ThreadLocal<HttpServletResponse> responses = new ThreadLocal<HttpServletResponse>();

    @Override
    public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
        requests.set(request);
        responses.set(response);
    }

    @Override
    public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {
        // do nothing
    }

    protected void addCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(Integer.MAX_VALUE);
        responses.get().addCookie(cookie);
    }

    protected String getCookie(String name) {
        HttpServletRequest request = requests.get();
        if (request == null) {
            return null;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    protected void deteteCookie(String name) {
        Cookie cookie = new Cookie(name, "DELETE");
        cookie.setMaxAge(0);
        responses.get().addCookie(cookie);
    }
}
