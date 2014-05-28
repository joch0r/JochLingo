package bram;
// TODO Move these
public class LetterUtils {
	public static Letter[] wordToLetters(String word, int length) {
		Letter[] letters = new Letter[length];
		int index = 0;
		while (word.length() > 0) {
			if (word.startsWith("ij")) {
				letters[index] = Letter.ij;
				word = word.substring(2);
			} else {
				String firstLetter = word.substring(0,1);
				letters[index] = StringToLetter(firstLetter);
				word = word.substring(1);
			}
			index++;
		}
		return letters;
	}

	public static Letter StringToLetter(String letterString) {
		switch(letterString) {
		case "a": return Letter.a; 
		case "b": return Letter.b; 
		case "c": return Letter.c; 
		case "d": return Letter.d; 
		case "e": return Letter.e; 
		case "f": return Letter.f; 
		case "g": return Letter.g; 
		case "h": return Letter.h; 
		case "i": return Letter.i; 
		case "j": return Letter.j; 
		case "k": return Letter.k; 
		case "l": return Letter.l; 
		case "m": return Letter.m; 
		case "n": return Letter.n; 
		case "o": return Letter.o; 
		case "p": return Letter.p; 
		case "q": return Letter.q; 
		case "r": return Letter.r; 
		case "s": return Letter.s; 
		case "t": return Letter.t; 
		case "u": return Letter.u; 
		case "v": return Letter.v; 
		case "w": return Letter.w; 
		case "x": return Letter.x; 
		case "y": return Letter.y; 
		case "z": return Letter.z; 
		case "ij": return Letter.ij;
		default : return null;
		}
	}
	
}
