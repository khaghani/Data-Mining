/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Text;

import DataMining.Data.DataStore;
import DataMining.Data.DataStore.CharacterType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amir
 */
public class TextAnalysis {

    public TextAnalysis() {
        this(null, false);
    }

    public TextAnalysis(String text) {
        this(text, false);
    }

    public TextAnalysis(String text, boolean showDialog) {
        setText(text);
        setShowDialog(showDialog);
    }
    private String _text;

    //Region Numbers
    private int _en_digitsCount, _pe_digitsCount;
    private int _en_maxDigit, _pe_maxDigit;
    private boolean _allowDuplicateDigits;
    //Region Chars
    private int _en_lettersCount, _pe_lettersCount;
    private int _en_maxLettersCount, _pe_maxLettersCount;

    //Region Words
    public List<String> _pe_Words;
    public List<String> _en_Words;
    public List<String> _en_Digits;
    public List<String> _pe_Digits;
    public List<String> _sentence;
    private boolean _allowDuplicateWords;
    //Region Marks
    private int _questionMarkCount;
    //Region Other
    private int _otherCount;
    private int _dotCount;
    private int _spaceCount;

    private String _otherChars;

    //Region Others
    private boolean _showDialog = false;

    public List<String> getEnWords() {
        return this._en_Words;
    }

    private void setEnMaxDigit(int value) {
        this._en_maxDigit = value;
    }

    private void setPeMaxDigit(int value) {
        this._pe_maxDigit = value;
    }

    private void setEnMaxlettersCount(int value) {
        this._en_maxLettersCount = value;
    }

    private void setPeMaxlettersCount(int value) {
        this._pe_maxLettersCount = value;
    }

    public int getQuestionMarkCount() {
        return this._questionMarkCount;
    }

    public int getEnWordsCount() {
        return this._en_Words.size();
    }

    public int getPeWordsCount() {
        return this._pe_Words.size();
    }

    public int getEnDigitsCount() {
        return this._en_Digits.size();
    }

    public int getPeDigitsCount() {
        return this._pe_Digits.size();
    }

    public List<String> getSentence() {
        return this._sentence;
    }

    public int getEnMaxDigit() {
        if (this._en_maxDigit > -1) {
            return this._en_maxDigit;
        }
        for (String item : this._en_Digits) {
            if (this._en_maxDigit < item.length()) {
                this._en_maxDigit = item.length();
            }
        }
        return this._en_maxDigit;
    }

    public int getPeMaDigit() {
        return this._pe_maxDigit;
    }

    public int getEnMaxlettersCount() {

        if (this._en_maxLettersCount > -1) {
            return this._en_maxLettersCount;
        }
        for (String item : this._en_Words) {
            if (this._en_maxLettersCount < item.length()) {
                this._en_maxLettersCount = item.length();
            }
        }
        return this._en_maxLettersCount;
    }

    public int getPeMaxlettersCount() {
        if (this._pe_maxLettersCount > -1) {
            return this._pe_maxLettersCount;
        }
        for (String item : this._pe_Words) {
            if (this._pe_maxLettersCount < item.length()) {
                this._pe_maxLettersCount = item.length();
            }
        }
        return this._pe_maxLettersCount;
    }

    public int getEnLettersCount() {
        return this._en_lettersCount;
    }

    public int getEnDigitCharCount() {
        return this._en_digitsCount;
    }

    public String getOtherChars() {
        return this._otherChars;
    }

    public int getPeLettersCount() {
        return this._pe_lettersCount;
    }

    public int getPeDigitCharCount() {
        return this._pe_digitsCount;
    }

    public int getPeMaxDigit() {
        if (this._pe_maxDigit > -1) {
            return this._pe_maxDigit;
        }
        for (String item : this._pe_Digits) {
            if (this._pe_maxDigit < item.length()) {
                this._pe_maxDigit = item.length();
            }
        }
        return this._pe_maxDigit;
    }

    public int getSpaceCount() {
        return this._spaceCount;
    }

    public int getOtherCount() {
        return this._otherCount;
    }

    public int getDots() {
        return this._dotCount;
    }

    public String getText() {
        return this._text;
    }

    public int getTotlaLeterCount() {
        return this._en_lettersCount + this._pe_lettersCount;
    }

    public int getTotalDigitsCount() {
        return this._en_digitsCount + this._pe_digitsCount;
    }

    public void setText(String text) {
        this._text = text;
        this.Initialize();
    }

    public void setAllowDuplicateDigits(boolean value) {
        this._allowDuplicateDigits = value;
    }

    public boolean getAllowDuplicateDigits() {
        return this._allowDuplicateDigits;
    }

    public void setAllowDuplicateWords(boolean value) {
        this._allowDuplicateWords = value;
    }

    public boolean getAllowDuplicateWords() {
        return this._allowDuplicateWords;
    }

    public void setShowDialog(boolean value) {
        this._showDialog = value;
    }

    public boolean getShowDialog() {
        return this._showDialog;
    }

    public List<String> getPeWords() {
        return this._pe_Words;
    }

    public List<String> getPeDigits() {
        return this._pe_Digits;
    }

    public List<String> getEnDigits() {
        return this._en_Digits;
    }

    public boolean StartAnalysis() {
        if (this._text == null) {
            return false;
        }
        

        CharacterType last = CharacterType.None;
        int enChar = 0, peChar = 0, enDigit = 0, peDigit = 0;
        char c;
        StringBuilder temp = new StringBuilder();
        StringBuilder tempSentence = new StringBuilder();
        for (int index = 0; index < this._text.length(); index++) {
            c = this._text.charAt(index);

            if (DataStore.English.isAlphabet(c)) {
                this._en_lettersCount++;
                enChar++;

                if (last != CharacterType.EnChar) {
                    enChar = 1;
                    setWords(last, temp.toString());
                    temp=new StringBuilder();
                }

                temp.append(c);
                tempSentence.append(c);
                last = CharacterType.EnChar;
            } else if (DataStore.English.isNumber(c)) {
                this._en_digitsCount++;
                enDigit++;
                if (last != CharacterType.EnDigit) {
                    enDigit = 1;
                    setWords(last, temp.toString());
                    temp =new StringBuilder();

                }
                temp.append(c);
                tempSentence.append(c);
                last = CharacterType.EnDigit;
            } else if (DataStore.Persian.isNumber(c)) {
                this._pe_digitsCount++;

                peDigit++;
                if (last != CharacterType.PeDigit) {
                    peDigit = 1;
                    setWords(last, temp.toString());
                    temp = new StringBuilder();
                }

                tempSentence.append(c);
                temp.append(c);
                last = CharacterType.PeDigit;
            } else if (DataStore.Persian.isAlphabet(c)) {
                this._pe_lettersCount++;

                peChar++;
                if (last != CharacterType.PeChar) {
                    peChar = 1;
                    setWords(last, temp.toString());
                    temp = new StringBuilder();
                }
                tempSentence.append(c);
                temp.append(c);
                last = CharacterType.PeChar;
            } else if (DataStore.isDot(c)) {
                this._dotCount++;
                setWords(last, temp.toString());
                setSentence(tempSentence.toString());
                temp = new StringBuilder();
                tempSentence = new StringBuilder();
                last = CharacterType.Dot;
            } else if (DataStore.isQuestionMark(c)) {
                this._questionMarkCount++;
                setWords(last, temp.toString());
                setSentence(tempSentence.toString());
                tempSentence = new StringBuilder();
                temp = new StringBuilder();
                last = CharacterType.QuestionMark;
            } else if (DataStore.isSpace(c)) {
                this._spaceCount++;
                setWords(last, temp.toString());
                tempSentence.append(c);
                temp = new StringBuilder();
                last = CharacterType.Space;
            } else {
                this._otherCount++;
                this._otherChars += c;
                setWords(last, temp.toString());
                tempSentence.append(c);
                temp = new StringBuilder();
                last = CharacterType.Other;
            }

        }
        setWords(last, temp.toString());
        setSentence(tempSentence.toString());
        if (getShowDialog()) {
            showDialog();
        }

        return true;
    }

    private void setSentence(String item) {
        String temp = item.trim();
        if (temp == null || "".equals(temp)) {
            return;
        }
        this._sentence.add(temp);

    }

    private void setWords(CharacterType type, String item) {
        String temp = item.trim();
        if (temp == null || "".equals(temp)) {
            return;
        }
        switch (type) {
            case EnChar:
                if (!this._allowDuplicateWords
                        && this._en_Words.indexOf(temp) >= 0) {
                    return;
                }
                this._en_Words.add(temp);
                break;
            case EnDigit:
                if (!this._allowDuplicateDigits
                        && this._en_Digits.indexOf(temp) >= 0) {
                    return;
                }
                this._en_Digits.add(temp);
                break;
            case PeChar:
                if (!this._allowDuplicateDigits
                        && this._pe_Words.indexOf(temp) >= 0) {
                    return;
                }
                this._pe_Words.add(temp);
                break;
            case PeDigit:
                if (!this._allowDuplicateWords
                        && this._pe_Digits.indexOf(temp) >= 0) {
                    return;
                }
                this._pe_Digits.add(temp);
                break;
            default:
                break;

        }
    }

    public boolean Clear() {
        return this.Initialize();
    }

    private boolean Initialize() {
        this._en_lettersCount = 0;
        this._en_digitsCount = 0;
        this._pe_lettersCount = 0;
        this._pe_digitsCount = 0;
        this._otherCount = 0;
        this._spaceCount = 0;
        this._dotCount = 0;
        this._en_maxDigit = -1;
        this._pe_maxDigit = -1;
        this._en_maxLettersCount = -1;
        this._pe_maxLettersCount = -1;
        this._questionMarkCount = 0;
        this._allowDuplicateDigits = false;
        this._allowDuplicateWords = false;
        this._pe_Words = new ArrayList<>();
        this._en_Words = new ArrayList<>();
        this._en_Digits = new ArrayList<>();
        this._pe_Digits = new ArrayList<>();
        this._sentence = new ArrayList<>();
        this._otherChars = "";
        return true;
    }

    public void showDialog() {

        System.out.println("---------------------Chars----------------------");
        System.out.println("English Digit(s) count:             " + getEnDigitCharCount());
        System.out.println("Persian Digit(s) count:             " + getPeDigitCharCount());
        System.out.println("                                ------------");
        System.out.println("total Digits                        " + getTotalDigitsCount());
        System.out.println();
        System.out.println();
        System.out.println("English Leter(s) count:             " + getEnLettersCount());
        System.out.println("Persian Leter(s) count:             " + getPeLettersCount());
        System.out.println("                                ------------");
        System.out.println("Totla Leters                        " + getTotlaLeterCount());
        System.out.println();
        System.out.println();
        System.out.println("Space count:                        " + getSpaceCount());
        System.out.println("Dot(s) count:                       " + getDots());
        System.out.println("Question mark(s) count:             " + getQuestionMarkCount());
        System.out.println("Other(s) count:                     " + getOtherCount());
        if (getOtherCount() > 0) {
            System.out.println("Unknown char(s):                    " + getOtherChars());
        }
        System.out.println();

        System.out.println("---------------------Words----------------------");
        System.out.println("Totla string length:                " + this._text.length());
        System.out.println();
        System.out.println("              <<English>>");
        System.out.println("Maximum Enlish alphabetic length:   " + getEnMaxlettersCount());
        System.out.println("Maximum Enlish number length:       " + getEnMaxDigit());
        System.out.println("Enlish word(s) count:               " + getEnWordsCount());
        System.out.println("Enlish Digit(s) count:              " + getEnDigitsCount());
        System.out.println();
        System.out.println("              <<Persian>>");
        System.out.println("Maximum Persian alphabetic length:  " + getPeMaxlettersCount());
        System.out.println("Maximum Persian number length:      " + getPeMaxDigit());
        System.out.println("Persian word(s) count:              " + getPeWordsCount());
        System.out.println("Persian Digit(s) count:             " + getPeDigitsCount());
        System.out.println();

        System.out.println("---------------------Sentence---------------------");
        System.out.println("Sentence count:                     " + getSentence().size());
        //System.out.println("pe Digit(s) count: " + getPeDigitsCount());
        //System.out.println("             ------------");

    }
}
