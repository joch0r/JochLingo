package bram;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tools.Parsers;
import valuations.CharValuation;
import valuations.CharValuation.ECharValuation;
import valuators.LingoValuator;

public class BramVsJochTest {

    public static void main(String[] args) {
        File f = new File("data\\OpenTaal-210G-basis-gekeurd.txt");
        List<String> jochLijst = new ArrayList<String>(Parsers.parse(f, 5));
        List<Word> bramLijst = new ArrayList<Word>();
        for (String word : jochLijst) {
            bramLijst.add(new Word(word.replace("{", "ij")));
        }

        for (int correct = 0; correct < jochLijst.size(); correct++) {
            LingoComparator c = new LingoComparator(bramLijst.get(correct));
            for (int guessed = 0; guessed < jochLijst.size(); guessed++) {
                LingoCompareValue[] bramCompare = c.compare(bramLijst.get(guessed));
                List<CharValuation> jochCompare = LingoValuator.getWordValuation(jochLijst.get(guessed), jochLijst.get(correct));
                if (!areEqual(bramCompare, jochCompare))
                    System.out.println(String.format("gekozen: %s - correct: %s - jochem: %s bram - %s", jochLijst.get(correct),
                            jochLijst.get(guessed), jochCompare, Arrays.deepToString(bramCompare)));
            }
        }
    }

    private static boolean areEqual(LingoCompareValue[] bramCompare, List<CharValuation> jochCompare) {
        if (bramCompare.length != jochCompare.size()) {
            return false;
        } else {
            for (int i = 0; i < bramCompare.length; i++) {
                switch (bramCompare[i]) {
                    case Correct:
                        if (jochCompare.get(i).getCharValuation() != ECharValuation.GOED)
                            return false;
                        break;
                    case Incorrect:
                        if (jochCompare.get(i).getCharValuation() != ECharValuation.FOUT)
                            return false;
                        break;
                    case Miss:
                        if (jochCompare.get(i).getCharValuation() != ECharValuation.ZIT_ER_IN)
                            return false;
                        break;
                }
            }
        }
        return true;
    }

}
