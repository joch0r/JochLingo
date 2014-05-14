package valuations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordValuation {
    // waar moet dit, eigenlijk meer een functies van List<List<Beoordeling>
    // hier is vast een mooi patroon voor
    public static Set<List<CharValuation>> getAll(int size) {
        return myTools.MyCollectionTools.getAllListsOver(
                new HashSet<CharValuation>(Arrays.asList(CharValuation.GOED, CharValuation.FOUT, CharValuation.ZIT_ER_IN)), size);
    }

    public static List<CharValuation> fromInteger(int integer, int length) {
        List<CharValuation> result = new ArrayList<CharValuation>();
        for (int i = 0; i < length; i++) {
            result.add(CharValuation.fromValue((integer / (myTools.MyMath.pow(3, i))) % 3));
        }
        return result;
    }

    public static int toInteger(List<CharValuation> wordValuation) {
        int result = 0;
        for (int i = 0; i < wordValuation.size(); i++) {
            result += myTools.MyMath.pow(3, i) * wordValuation.get(i).value();
        }
        return result;
    }

    public static int count(int length) {
        return myTools.MyMath.pow(3, length);
    }

    public static Set<List<CharValuation>> getAllNonRecursive(int length) {
        Set<List<CharValuation>> beoordelingen = new HashSet<List<CharValuation>>();
        for (int i = 0; i < myTools.MyMath.pow(3, length); i++) {
            beoordelingen.add(fromInteger(i, length));
        }
        return beoordelingen;
    }

    public static void main(String[] args) throws Exception {
        long time = System.nanoTime();
        for (int i = 1; i < 80; i++)
            getAll(8);
        System.out.println(String.format("GetAllRecursive takes %s ms", (System.nanoTime() - time) / 1000000));

        time = System.nanoTime();
        for (int i = 1; i < 80; i++)
            getAllNonRecursive(8);
        System.out.println(String.format("GetAll takes %s ms", (System.nanoTime() - time) / 1000000));

        if (!new HashSet<List<CharValuation>>(getAllNonRecursive(5)).equals(new HashSet<List<CharValuation>>(getAll(5)))) {
            throw new Exception("GetAllRecursive en GetAll zouden het zelfde moeten doen maar doen dat niet");
        }

        for (List<CharValuation> wordValuation : getAll(5)) {
            if (!wordValuation.equals(fromInteger(toInteger(wordValuation), 5))) {
                throw new Exception("FromInteger en toInteger niet inverse");
            }
        }

        System.out.println(getAll(0));
    }
}
