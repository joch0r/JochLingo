package valuators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AverageAmbiguousGroupSizeValuator extends PreComputingLingoValuator<Set<String>> {

    public AverageAmbiguousGroupSizeValuator(Set<String> words) {
        super(words);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double valuate(Set<String> chosenWords) {
        // TODO Auto-generated method stub
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

        int ambiguityCount = 0;
        for (ArrayList<Integer> valuations : ambiguity.keySet()) {
            ambiguityCount += ambiguity.get(valuations) * ambiguity.get(valuations);
        }

        return -(double) ambiguityCount / (double) wordList.size();
    }
}
