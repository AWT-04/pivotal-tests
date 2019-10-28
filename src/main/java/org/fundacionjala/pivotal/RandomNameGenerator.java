package org.fundacionjala.pivotal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


/**
 * @author lazaro on 10/24/2019.
 * @version AWT-04_pivotal-selenium-tests.
 */
public final class RandomNameGenerator {
    // class variable
    private static final String LEXICON = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    private static final String PREFIX = "AT_";
    private static final java.util.Random RAND = new java.util.Random();
    private static final Set<String> IDENTIFIERS = new HashSet<>();
    private static final int NUM_CHARS = 5;
    private static final String RANDOM = "\\{RANDOM}";

    private RandomNameGenerator() { }

    private static String getPREFIX() {
        return PREFIX;
    }

    private static String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = RAND.nextInt(NUM_CHARS) + NUM_CHARS;
            for (int i = 0; i < length; i++) {
                builder.append(LEXICON.charAt(RAND.nextInt(LEXICON.length())));
            }
            if (IDENTIFIERS.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return getPREFIX() + builder.toString() + "-" + dtf.format(now);
    }
    public static String replaceRandom(final String json) {
        return json.replaceAll(RANDOM, randomIdentifier());
    }
}
