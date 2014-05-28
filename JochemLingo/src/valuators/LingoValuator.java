package valuators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import valuations.CharValuation;
import myTools.Valuator;

public abstract class LingoValuator implements Valuator<Set<String>> {
    public static List<CharValuation> getWordValuation(String chosenWord, String correctWord) {
        boolean[] correctWordLetterFoundInChosenWord = new boolean[correctWord.length()];
        CharValuation[] wordValuation = new CharValuation[correctWord.length()];

        for (int i = 0; i < wordValuation.length; i++)
            if (chosenWord.charAt(i) == correctWord.charAt(i)) {
                wordValuation[i] = CharValuation.GOED;
                correctWordLetterFoundInChosenWord[i] = true;
            } else {
                wordValuation[i] = CharValuation.FOUT;
            }

        for (int i = 0; i < chosenWord.length(); i++) {
            if (wordValuation[i] != CharValuation.GOED) {
                boolean chosenWordLetterFound = false;
                for (int j = 0; j < correctWord.length(); j++) {
                    if (correctWord.charAt(j) == chosenWord.charAt(i) && !correctWordLetterFoundInChosenWord[j]) {
                        chosenWordLetterFound = true;
                        correctWordLetterFoundInChosenWord[j] = true;
                        break;
                    }
                }
                if (chosenWordLetterFound) {
                    wordValuation[i] = CharValuation.ZIT_ER_IN;
                } else {
                    wordValuation[i] = CharValuation.FOUT;
                }
            }

        }
        return Arrays.asList(wordValuation);
    }

    public static void main(String[] args) {
        System.out.println(getWordValuation("oehoe", "opeen"));
    }
}
