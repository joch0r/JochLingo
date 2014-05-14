package valuations;

//eigenlijk is een beoordeling meer een list van dit
public class CharValuation {
    // nu kan ik toSting op mn enum doen, maar is dit niet gaar
    public enum ECharValuation {
        GOED, FOUT, ZIT_ER_IN
    }

    private ECharValuation eCharValuation;

    public ECharValuation getCharValuation() {
        return eCharValuation;
    }

    private CharValuation(ECharValuation eCharValuation) {
        this.eCharValuation = eCharValuation;
    }

    public static final CharValuation GOED = new CharValuation(ECharValuation.GOED);
    public static final CharValuation FOUT = new CharValuation(ECharValuation.FOUT);
    public static final CharValuation ZIT_ER_IN = new CharValuation(ECharValuation.ZIT_ER_IN);

    public String toString() {
        switch (eCharValuation) {
            case GOED:
                return "X";
            case FOUT:
                return "_";
            case ZIT_ER_IN:
                return "?";
            default:
                return "";
        }
    }

    public int value() {
        switch (eCharValuation) {
            case GOED:
                return 0;
            case ZIT_ER_IN:
                return 1;
            case FOUT:
                return 2;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static CharValuation fromValue(int value) {
        switch (value) {
            case 0:
                return GOED;
            case 1:
                return ZIT_ER_IN;
            case 2:
                return FOUT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
