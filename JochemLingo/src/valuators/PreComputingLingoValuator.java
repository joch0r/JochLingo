package valuators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import valuations.WordValuation;

public abstract class PreComputingLingoValuator extends LingoValuator {
    ArrayList<String> wordList;
    HashMap<String, HashMap<String, Integer>> valuations;

    public PreComputingLingoValuator(Set<String> w) {
        this.wordList = new ArrayList<String>(w);

        valuations = new HashMap<String, HashMap<String, Integer>>();
        for (String chosenWord : wordList) {
            HashMap<String, Integer> subBeoordelingen = new HashMap<String, Integer>();
            for (String correctWord : wordList) {
                subBeoordelingen.put(correctWord, WordValuation.toInteger(this.getWordValuation(chosenWord, correctWord)));
            }
            valuations.put(chosenWord, subBeoordelingen);
        }
    }
}
