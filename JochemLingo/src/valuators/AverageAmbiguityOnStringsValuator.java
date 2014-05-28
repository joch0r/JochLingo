package valuators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import valuations.WordValuation;
import valuations.CharValuation;

public class AverageAmbiguityOnStringsValuator extends PreComputingLingoValuator {

    public AverageAmbiguityOnStringsValuator(Set<String> words) {
        super(words);
    }

    @Override
    public double valuate(Set<String> chosenWords) {
        long ambiguitySummed = 0;
        for (String correctWord : wordList) {
            ambiguitySummed += ambiguity(chosenWords, correctWord);
        }

        return -(double) ambiguitySummed / (double) chosenWords.size();
    }

    private long ambiguity(Set<String> chosenWords, String correctWord) {
        HashMap<Character, Set<Integer>> charCanOccurOnPosition = new HashMap<Character, Set<Integer>>();
        for (char c = 'a'; c <= '{'; c++) {
            Set<Integer> allPositions = new HashSet<Integer>();
            for (int p = 0; p < correctWord.length(); p++) {
                allPositions.add(p);
            }
            charCanOccurOnPosition.put(c, allPositions);
        }
        for (String chosenWord : chosenWords) {
            List<CharValuation> wordValuation = WordValuation
                    .fromInteger(valuations.get(chosenWord).get(correctWord), correctWord.length());
            for (int i = 0; i < wordValuation.size(); i++) {
                switch (wordValuation.get(i).getCharValuation()) {
                    case GOED:
                        for (char c = 'a'; c <= '{'; c++) {
                            if (c != chosenWord.charAt(i)) {
                                charCanOccurOnPosition.get(c).remove(i);
                            }
                        }
                        break;
                    case FOUT:
                        charCanOccurOnPosition.get(chosenWord.charAt(i)).remove(i);
                        break;
                    case ZIT_ER_IN:
                        charCanOccurOnPosition.get(chosenWord.charAt(i)).remove(i);
                        break;
                }
            }
        }
        return 0;
    }
}
