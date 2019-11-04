package org.fundacionjala.core.utils;

import org.fundacionjala.pivotal.ScenarioContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author lazaro on 10/24/2019.
 * @version AWT-04_pivotal-selenium-tests.
 */
public final class VariableNameHandler {
    // class variable
    private static final String LEXICON = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    private static final String PREFIX = "AT_";
    private static final java.util.Random RAND = new java.util.Random();
    private static final Set<String> IDENTIFIERS = new HashSet<>();
    private static final int NUM_CHARS = 5;
    private static final String RANDOM = "RANDOM";

    private VariableNameHandler() {  }

    private static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm/dd/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private static String generateUUID() {

        return UUID.randomUUID().toString();
    }

    private static String getPREFIX() {
        return PREFIX;
    }

    private static String processVariable(final String var) {
        String c = null;
        switch (var) {
            case RANDOM:
                c = randomIdentifier();
                break;
            case "DATE":
                c = getDate();
                break;
            case "UUI":
                c = generateUUID();
                break;
            default:
                c = var;
                break;
        }
        return c;
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

    public static String replaceRandom(final String json, final ScenarioContext context) {

        String[] linesSplit = json.split("\n");
        for (int i = 1; i < (linesSplit.length - 1); i++) {
            Pattern pattern = Pattern.compile("(?<=\\{)(.*?)(?=\\})");
            Matcher matcher = pattern.matcher(linesSplit[i]);
            if (matcher.find()) {
                String var = processVariable(matcher.group());

                if (var.contains(".")) {
                    String[] keyValue = matcher.group().split("\\.");
                    String valueToReplace = context.getContext(keyValue[0]).jsonPath().getString(keyValue[1]);
                    linesSplit[i] = linesSplit[i].replaceAll("\\{" + matcher.group() + "}", valueToReplace);

                } else {
                    linesSplit[i] = linesSplit[i].replaceAll("\\{" + matcher.group() + "}", var);
                }
            }
        }
        return String.join("\n", linesSplit);
    }
}
