package ua.nure.kn_15_6.dudar.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Message {
    private static final String BUNDLE_NAME = "messages";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public Message() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
