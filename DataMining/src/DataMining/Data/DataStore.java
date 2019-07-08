/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Data;

/**
 *
 * @author amir
 */
public class DataStore {

    public enum CharacterType {
        None,
        PeChar,
        EnChar,
        PeDigit,
        EnDigit,
        Dot,
        Space,
        QuestionMark,
        PeCama,
        Hashtag,
        AtSin,
        Other;

        public static int fromEnum(CharacterType type) {
            switch (type) {
                case Other:
                    return 0;
                case PeChar:
                    return 1;
                case EnChar:
                    return 2;
                case PeDigit:
                    return 3;
                case EnDigit:
                    return 4;
                case Dot:
                    return 5;
                case Space:
                    return 6;
                case QuestionMark:
                    return 7;
                case PeCama:
                    return 8;
                case Hashtag:
                    return 9;
                case AtSin:
                    return 10;

                default:
                    return -1;

            }

        }

        public static CharacterType fromNumber(int index) {
            switch (index) {
                case 0:
                    return Other;
                case 1:
                    return PeChar;
                case 2:
                    return EnChar;
                case 3:
                    return PeDigit;
                case 4:
                    return EnDigit;
                case 5:
                    return Dot;
                case 6:
                    return Space;
                case 7:
                    return QuestionMark;
                case 8:
                    return PeCama;
                case 9:
                    return Hashtag;
                case 10:
                    return AtSin;
                default:
                    return None;
            }

        }
    }
    private final static char[] persianNumbers = {
        //Arabic Numbers
        '٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩',
        //Persian Numbers
        '۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹'
    };
    private final static char[] persianCharacters = {
        'آ', 'ا', 'ء', 'أ', 'ئ', 'ؤ', 'ا', 'ـ',
        'ب', 'پ', 'ت', 'ث', 'ج', 'چ', 'ح', 'خ',
        'د', 'ذ', 'ر', 'ز', 'ژ',
        'س', 'ش', 'ص', 'ض', 'ط', 'ظ', 'ع', 'غ', 'ف', 'ق',
        'ک', 'گ', 'ل', 'م', 'ن', 'و', 'ه', 'ی', 'ي', 'ك',
        'إ', 'ة'

    };
    private final static char[] questionMarkCharacters = {'?', '؟'};

    private final static char[] dot = {':', '؛', '،', '.', ',', '!'};

    public static class Persian {

        public static boolean isNumber(char character) {
            return isNumber((int) character);
        }

        public static boolean isNumber(int ascii) {
            for (int index = 0; index < persianNumbers.length; index++) {
                if (persianNumbers[index] == ascii) {
                    return true;
                }
            }
            return false;

        }

        public static boolean isAlphabet(int ascii) {
            return isAlphabet((char) ascii);
        }

        public static boolean isAlphabet(char character) {
            for (int index = 0; index < persianCharacters.length; index++) {
                if (persianCharacters[index] == character) {
                    return true;
                }
            }
            return false;

        }
    }

    public static class English {

        public static boolean isNumber(int ascii) {
            return isNumber((char) ascii);
        }

        public static boolean isNumber(char character) {
            if (character >= '0' && character <= '9') {
                return true;
            }
            return false;

        }

        public static boolean isAlphabet(int ascii) {
            return isAlphabet((char) ascii);
        }

        public static boolean isAlphabet(char character) {
            if ((character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z')) {
                return true;
            }
            return false;
        }

    }

    public static boolean isSpace(char character) {
        if (character == '\n' || character == ' ' || character == '\t') {
            return true;
        }
        return false;
    }

    public static boolean isSpace(int ascii) {
        return isSpace((char) ascii);
    }

    public static boolean isDot(int ascii) {
        return isDot((char) ascii);
    }

    public static boolean isDot(char character) {
        for (int index = 0; index < dot.length; index++) {
            if (character == dot[index]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPeCama(char character) {
        return false;
    }

    public static boolean isQuestionMark(int ascii) {
        return isQuestionMark((char) ascii);
    }

    public static boolean isQuestionMark(char character) {
        for (int index = 0; index < questionMarkCharacters.length; index++) {
            if (character == questionMarkCharacters[index]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHashtag(int ascii) {
        return isHashtag((char) ascii);
    }

    public static boolean isHashtag(char character) {
        if (character == '#') {
            return true;
        }
        return false;
    }

    public static boolean isAtSin(int ascii) {
        return isAtSin((char) ascii);
    }

    public static boolean isAtSin(char character) {
        if (character == '@') {
            return true;
        }
        return false;
    }

    public static CharacterType getCharacterType(char character) {
        if (isQuestionMark(character)) {
            return CharacterType.QuestionMark;
        }
        if (isDot(character)) {
            return CharacterType.Dot;
        }
        if (isSpace(character)) {
            return CharacterType.Space;
        }
        if (English.isAlphabet(character)) {
            return CharacterType.EnChar;
        }
        if (English.isNumber(character)) {
            return CharacterType.EnDigit;
        }
        if (Persian.isAlphabet(character)) {
            return CharacterType.PeChar;
        }
        if (Persian.isNumber(character)) {
            return CharacterType.PeDigit;
        }
        if (isPeCama(character)) {
            return CharacterType.PeCama;
        }
        if (isAtSin(character)) {
            return CharacterType.AtSin;
        }
        if (isHashtag(character)) {
            return CharacterType.Hashtag;
        }
        return CharacterType.Other;
    }

    public static CharacterType getCharacterType(int ascii) {
        return getCharacterType((char) ascii);
    }
}
