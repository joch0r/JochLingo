package valuators;

import java.util.Set;

public class MinCorrectCountValuator extends PreComputingLingoValuator {

    public MinCorrectCountValuator(Set<String> w) {
        super(w);
    }

    @Override
    public double valuate(Set<String> chosenWords) {
        int worstCase = Integer.MAX_VALUE;
        for (String woord : wordList) {
            int correct = 0;
            for (int i = 0; i < woord.length(); i++) {
                for (String chosenWord : chosenWords) {
                    if (woord.charAt(i) == chosenWord.charAt(i)) {
                        correct++;
                        break;
                    }
                }
            }
            if (correct < worstCase) {
                worstCase = correct;
            }
        }
        return (double) worstCase;
    }
}
