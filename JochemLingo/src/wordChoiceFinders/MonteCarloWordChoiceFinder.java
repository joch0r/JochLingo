package wordChoiceFinders;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import valuators.NonAmbiguityValuator;
import main.Parsers;
import myTools.LazySetOfSizedSubsets;
import myTools.Valuator;

public class MonteCarloWordChoiceFinder implements WordChoiceFinder {

    private LazySetOfSizedSubsets<String> possibilities;
    private Valuator<Set<String>> valuator;
    private static final DecimalFormat df = new DecimalFormat("#.#");
    private static final int PROGRESS_REPORT_INTERVAL = 1000;
    private int times;

    public MonteCarloWordChoiceFinder(Set<String> words, int nrOfWords, Valuator<Set<String>> valuator, int times) {
        this(new LazySetOfSizedSubsets<String>(new ArrayList<String>(words), nrOfWords), valuator, times);
    }

    public MonteCarloWordChoiceFinder(LazySetOfSizedSubsets<String> possibilities, Valuator<Set<String>> valuator, int times) {
        this.possibilities = possibilities;
        this.valuator = valuator;
        this.times = times;
    }

    public Set<Set<String>> getWords() {
        long nano = System.nanoTime();
        double bestScore = Double.NEGATIVE_INFINITY;
        Set<Set<String>> bestChoices = new HashSet<Set<String>>();

        int count = 0;
        for (int i = 0; i < times; i++) {
            Set<String> chosenWords = possibilities.randomElement();
            double score = valuator.valuate(chosenWords);

            if (score == bestScore) {
                bestChoices.add(chosenWords);
            }
            if (score > bestScore) {
                bestScore = score;
                bestChoices.clear();
                bestChoices.add(chosenWords);
            }
            count++;
            if (times >= PROGRESS_REPORT_INTERVAL && count % (times / PROGRESS_REPORT_INTERVAL) == 0) {
                System.out.println(String.format("%s%% (%s - %s): %sms, est. %ss", df.format((((double) count) / (double) times) * 100),
                        count, times, (System.nanoTime() - nano) / 1000000, (System.nanoTime() - nano) / 1000000000 * times / count));
            }
        }

        return bestChoices;
    }

    public static void main(String[] args) {
        File f = new File("data\\OpenTaal-210G-basis-gekeurd.txt");
        Set<String> words = Parsers.parse(f, 8, 'a');

        Valuator<Set<String>> nonAmbiguityValuator = new NonAmbiguityValuator(words);
        Iterable<Set<String>> bestAmbiguity = new MonteCarloWordChoiceFinder(words, 3, nonAmbiguityValuator, 100000).getWords();
        for (Set<String> choice : bestAmbiguity) {
            System.out.println(choice + ", score=" + nonAmbiguityValuator.valuate(choice));
        }
        System.out.println(bestAmbiguity);
    }

}
