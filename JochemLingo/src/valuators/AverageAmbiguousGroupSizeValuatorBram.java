package valuators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AverageAmbiguousGroupSizeValuatorBram extends PreComputingLingoValuator<Set<String>> {

    public AverageAmbiguousGroupSizeValuatorBram(Set<String> words) {
        super(words);
    }

    @Override
    public double valuate(Set<String> chosenWords) {
        HashMap<ArrayList<Integer>, Integer> ambiguity = new HashMap<ArrayList<Integer>, Integer>();
        for (String word : wordList) {
            ArrayList<Integer> valuationsOfChosenWords = new ArrayList<Integer>();
            for (String chosenWord : chosenWords)
                valuationsOfChosenWords.add(valuations.get(chosenWord).get(word));

            if (!ambiguity.containsKey(valuationsOfChosenWords)) {
                ambiguity.put(valuationsOfChosenWords, 1);
            } else {
                ambiguity.put(valuationsOfChosenWords, ambiguity.get(valuationsOfChosenWords) + 1);
            }
        }

        int ambiguousGroups = 0;
        for (ArrayList<Integer> valuations : ambiguity.keySet()) {
            if (ambiguity.get(valuations) > 0)
                ambiguousGroups++;
        }

        return -(double) wordList.size() / (double) ambiguousGroups;
    }
}
