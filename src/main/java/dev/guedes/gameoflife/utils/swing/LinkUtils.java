package dev.guedes.gameoflife.utils.swing;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import java.awt.Cursor;
import java.awt.Desktop;
import java.net.URI;

/*
 * Copyright (c) 2026 João Guedes
 * Project : Game of Life
 * File : LinkUtils.java
 * Created on : 2026-11-09 17:00
 * Last modified: 2026-11-09 17:00
 *
 * Author : João Guedes
 * GitHub : https://github.com/JoaoGuedesLuna
 *
 * Copyright (c) 2026 João Guedes. All Rights Reserved.
 * Licensed under the MIT License.
 *
 * https://opensource.org/licenses/MIT
 */
public final class LinkUtils {
    private LinkUtils() {
        throw new UnsupportedOperationException("LinkUtils is a utility class and cannot be instantiated.");
    }

    public static void apply(JEditorPane pane) {
        pane.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                open(e.getURL().toString());
            } else if (HyperlinkEvent.EventType.ENTERED.equals(e.getEventType())) {
                pane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else if (HyperlinkEvent.EventType.EXITED.equals(e.getEventType())) {
                pane.setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    private static void open(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
