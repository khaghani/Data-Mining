/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Data;

import static DataMining.Data.DataStore.getCharacterType;
import java.security.*;

/**
 *
 * @author amir
 */
public class Pattern {

    private String _text;

    //Region Email
    private final static String[] emailPatterns = {
        "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"
    };

    private final static String[] hashtagPatterns = {
        "(?<![a-zA-Z0-9_])#(?=.*[a-zA-Z])[a-zA-Z0-9_]+"
    };

    private final static String[] phoneNumber = {
        "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",
        "([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})",
        "(?:\\\\(\\\\d{3}\\\\)|\\\\d{3}[-]*)\\\\d{3}[-]*\\\\d{4}",
        "^((\\+|00)359|0)(\\-|\\s)?8[7-9][2-9](\\-|\\s)?\\d{3}(\\s|\\-)?\\d{3}$"

    };
    private final static String[] persianChars = {"[\\p{InArabic}&&[^۰-۹]]"};

    private int[] _arrayPattern;

    public int[] getArrayPattern() {
        return this._arrayPattern;
    }

    public String getArrayPatternForStore() {
        StringBuilder sb = new StringBuilder();
        int arrayLength = this._arrayPattern.length - 1;
        for (int index = 0; index < arrayLength; index++) {
            sb.append(this._arrayPattern[index] + " ");
        }
        sb.append(this._arrayPattern[arrayLength]);
        return sb.toString();
    }

    public boolean createArrayPattern() {
        if (this._text == null) {
            return false;
        }
        int maxIndex = this._text.length();
        this._arrayPattern = new int[maxIndex];
        for (int index = 0; index < this._text.length(); index++) {
            this._arrayPattern[index]
                    = DataStore.CharacterType.fromEnum(
                            getCharacterType(this._text.charAt(index)));
        }
        return true;
    }

    public Pattern(String text) {
        setText(text);
    }

    public boolean isPhoneNumber() {
        return isPhoneNumber(this._text);
    }

    public boolean isPhoneNumber(String text) {

        for (String item : Pattern.phoneNumber) {
            if (text.matches(item)) {
                return true;
            }
        }
        return false;
    }

    public void setText(String text) {
        this._text = text;
    }

    public boolean isEmail() {
        return isEmail(this._text);
    }

    public boolean isEmail(String text) {
        if (text == null) {
            return false;
        }
        for (String item : this.emailPatterns) {
            if (text.matches(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHashtag() {
        return this.isHashtag(this._text);
    }

    public boolean isHashtag(String text) {
        if (text == null) {
            return false;
        }
        for (String item : this.hashtagPatterns) {
            if (text.matches(item)) {
                return true;
            }
        }
        return false;
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < b.length; index++) {
            result.append(Integer.toString((b[index] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    public static String toSHA1(String text) {
        return toSHA1(text.getBytes());
    }

    public static String toSHA1(byte[] b) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            System.err.print(
                    System.getProperty("user.dir")
                    + " -> Pattern type Java Class toSHA1() function->  type func : "
                    + ex.getMessage());
        }
        return new String(md.digest(b));

    }
}
