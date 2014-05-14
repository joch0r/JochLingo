package valuators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import myTools.Valuator;
import valuations.CharValuation;
import valuations.WordValuation;

public abstract class PreComputingLingoValuator<T> implements Valuator<T> {
    ArrayList<String> wordList;
    HashMap<String, HashMap<String, Integer>> valuations;

    public PreComputingLingoValuator(Set<String> w) {
        this.wordList = new ArrayList<String>(w);

        valuations = new HashMap<String, HashMap<String, Integer>>();
        for (String chosenWord : wordList) {
            HashMap<String, Integer> subBeoordelingen = new HashMap<String, Integer>();
            for (String correctWord : wordList) {
                subBeoordelingen.put(correctWord, WordValuation.toInteger(getCharValuation(chosenWord, correctWord)));
            }
            valuations.put(chosenWord, subBeoordelingen);
        }
    }

    private List<CharValuation> getCharValuation(String chosenWord, String correctWord) {
        List<CharValuation> result = new ArrayList<CharValuation>();
        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == correctWord.charAt(i)) {
                result.add(CharValuation.GOED);
            } else {
                if (zitErInEnMinstensEenNietGoed(correctWord, chosenWord, chosenWord.charAt(i))) {
                    result.add(CharValuation.ZIT_ER_IN);
                } else {
                    result.add(CharValuation.FOUT);
                }
            }
        }
        return result;
    }

    private static boolean zitErInEnMinstensEenNietGoed(String echtWoord, String gekozenWoord, char c) {
        // TODO Auto-generated method stub
        boolean zitErIn = false;
        boolean minstensEenNietGoed = false;
        for (int i = 0; i < echtWoord.length(); i++) {
            if (echtWoord.charAt(i) == c) {
                zitErIn = true;
                if (echtWoord.charAt(i) != gekozenWoord.charAt(i))
                    minstensEenNietGoed = true;
            }
        }
        return zitErIn && minstensEenNietGoed;
    }

    public abstract double valuate(T chosenWords);

}
