package valuators;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import myTools.Valuator;
import tools.Parsers;
import valuations.CharValuation;
import valuations.WordValuation;
import wordChoiceFinders.ExhaustiveWordChoiceFinder;
import wordChoiceFinders.MonteCarloWordChoiceFinder;

public class AverageAmbiguityValuatorBram extends PreComputingLingoValuator {

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
        
        return -(double) wordList.size() / (double) ambiguity.size();
    }

    public static void main(String[] args) {
        File f = new File("data\\OpenTaal-210G-basis-gekeurd.txt");
        Set<String> words = Parsers.parse(f, 5, 'o');

        Valuator<Set<String>> nonAmbiguityValuator = new AverageAmbiguityValuatorBram(words);
        ExhaustiveWordChoiceFinder finder = new ExhaustiveWordChoiceFinder(words,3, nonAmbiguityValuator);
        //Set<Set<String>> choice = finder.getWords();
        //System.out.println(choice + ", score=" + nonAmbiguityValuator.valuate(choice.iterator().next()));
        System.out.println(nonAmbiguityValuator.valuate(new HashSet<String>(Arrays.asList(new String[]{ "omkat","opeen","orgel"}))));
        System.out.println(nonAmbiguityValuator.valuate(new HashSet<String>(Arrays.asList(new String[]{ "omdat","opeen","orgel"}))));
    }
}
