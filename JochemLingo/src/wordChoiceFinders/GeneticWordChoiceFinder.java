package wordChoiceFinders;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tools.Parsers;
import valuators.ListValuatorFromSetValuator;
import valuators.NonAmbiguityValuator;
import myAI.GeneticAlgorithm;
import myTools.Valuator;

public class GeneticWordChoiceFinder implements WordChoiceFinder {

    GeneticAlgorithm<String> algorithm;
    private int generations;

    public GeneticWordChoiceFinder(Set<String> words, int nrOfWords, Valuator<Set<String>> valuator, int populationSize, int generations,
            double selectionSize, double mutationChance) {
        this.algorithm = new GeneticAlgorithm<String>(words, new ListValuatorFromSetValuator<String>(valuator), populationSize, nrOfWords,
                selectionSize, mutationChance);
        this.generations = generations;
    }

    @Override
    public Set<Set<String>> getWords() {
        Set<Set<String>> result = new HashSet<Set<String>>();

        algorithm.run(generations);

        for (List<String> wordList : algorithm.getPopulation()) {
            Set<String> wordSet = new HashSet<String>();
            for (String word : wordList) {
                wordSet.add(word);
            }
            result.add(wordSet);
        }

        return result;
    }

    public static void main(String[] args) {
        File f = new File("data\\OpenTaal-210G-basis-gekeurd.txt");
        Set<String> words = Parsers.parse(f, 8, 'a');

        Valuator<Set<String>> nonAmbiguityValuator = new NonAmbiguityValuator(words);
        Iterable<Set<String>> bestAmbiguity = new GeneticWordChoiceFinder(words, 3, nonAmbiguityValuator, 100, 1000, 0.1, 0.005).getWords();
        for (Set<String> choice : bestAmbiguity) {
            System.out.println(choice + ", score=" + nonAmbiguityValuator.valuate(choice));
        }
        System.out.println(bestAmbiguity);
    }

}
