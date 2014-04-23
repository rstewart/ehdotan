/*
 * Copyright [2013] [ShopWiki]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shopwiki.vaadin;

import java.io.IOException;
import java.io.ObjectInputStream;

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

    // Need this because deserialization is stupid and sets transient fields to null.
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        requests = new ThreadLocal<HttpServletRequest>();
        responses = new ThreadLocal<HttpServletResponse>();
    }

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

    protected void deleteCookie(String name) {
        Cookie cookie = new Cookie(name, "DELETE");
        cookie.setMaxAge(0);
        responses.get().addCookie(cookie);
    }
}
