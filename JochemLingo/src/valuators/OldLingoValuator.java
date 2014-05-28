package valuators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import valuations.CharValuation;
import myTools.Valuator;

public abstract class OldLingoValuator implements Valuator<Set<String>> {
    protected static List<CharValuation> getWordValuation(String chosenWord, String correctWord) {
        List<CharValuation> result = new ArrayList<CharValuation>();
        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == correctWord.charAt(i)) {
                result.add(CharValuation.GOED);
            } else {
                if (zitErInMinstensEenNietGoed(correctWord, chosenWord, chosenWord.charAt(i))) {
                    result.add(CharValuation.ZIT_ER_IN);
                } else {
                    result.add(CharValuation.FOUT);
                }
            }
        }
        return result;
    }

    private static boolean zitErInMinstensEenNietGoed(String echtWoord, String gekozenWoord, char c) {
        boolean zitErIn = false;
        boolean minstensEenNietGoed = false;
        for (int i = 0; i < echtWoord.length(); i++) {
            if (echtWoord.charAt(i) == c) {
                zitErIn = true;
                if (echtWoord.charAt(i) != gekozenWoord.charAt(i))
                    minstensEenNietGoed = true;
            }
        }
        return zitErIn && minstensEenNietGoed;
    }
    
    public static void main(String[] args) {
        System.out.println(getWordValuation("opeen", "oudje"));
    }
}
