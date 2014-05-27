package valuators;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import myTools.Valuator;
import tools.Parsers;
import wordChoiceFinders.ExhaustiveWordChoiceFinder;
import wordChoiceFinders.MonteCarloWordChoiceFinder;

public class AverageAmbiguityValuatorBram extends PreComputingLingoValuator<Set<String>> {

    public AverageAmbiguityValuatorBram(Set<String> words) {
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
    
    public static void main(String[] args) {
        File f = new File("data\\OpenTaal-210G-basis-gekeurd.txt");
        Set<String> words = Parsers.parse(f, 5, 'o');

        Valuator<Set<String>> nonAmbiguityValuator = new AverageAmbiguityValuatorBram(words);
        ExhaustiveWordChoiceFinder finder = new ExhaustiveWordChoiceFinder(words,3, nonAmbiguityValuator);
        Set<Set<String>> choice = finder.getWords();
        System.out.println(choice + ", score=" + nonAmbiguityValuator.valuate(choice.iterator().next()));
    }
}
