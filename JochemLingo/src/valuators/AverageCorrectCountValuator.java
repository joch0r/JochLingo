package valuators;

import java.util.Set;

public class AverageCorrectCountValuator extends PreComputingLingoValuator<Set<String>> {

    public AverageCorrectCountValuator(Set<String> w) {
        super(w);
    }

    @Override
    public double valuate(Set<String> chosenWords) {
        int result = 0;
        for (String woord : wordList) {
            for (int i = 0; i < woord.length(); i++) {
                for (String chosenWord : chosenWords) {
                    if (woord.charAt(i) == chosenWord.charAt(i)) {
                        result++;
                        break;
                    }
                }
            }
        }

        return (double) result / (double) wordList.size();
    }
}
