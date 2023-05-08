package ca.jrvs.apps.twitter.util;

import java.util.Arrays;

@SuppressWarnings("unused")
public class StringUtil {
    public StringUtil() {
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isEmpty(String... s) {
        return Arrays.stream(s).anyMatch((str) -> str == null || str.isEmpty());
    }

    public static String capitalize(final String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            int firstCodepoint = str.codePointAt(0);
            int newCodePoint = Character.toTitleCase(firstCodepoint);
            if (firstCodepoint == newCodePoint) {
                return str;
            } else {
                int[] newCodePoints = new int[strLen];
                int outOffset = 0;
                newCodePoints[outOffset++] = newCodePoint;

                int codepoint;
                for(int inOffset = Character.charCount(firstCodepoint); inOffset < strLen; inOffset += Character.charCount(codepoint)) {
                    codepoint = str.codePointAt(inOffset);
                    newCodePoints[outOffset++] = codepoint;
                }

                return new String(newCodePoints, 0, outOffset);
            }
        } else {
            return str;
        }
    }
}
