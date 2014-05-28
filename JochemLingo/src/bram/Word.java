package bram;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {

	private final String c_wordString;
	private Letter[] letters;
	
	public Word(String wordString) {
		c_wordString = wordString.trim();
	}
	
	
	public Word(Word word) {
		this(word.toString());
	}


	public String toString() {
		return c_wordString;
	}

	public boolean equals(Object o) {
		if (o instanceof Word) {
			Word w = (Word) o;
			if (w.toString().equals(this.toString())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean isCorrectLingoWord() {
		String correctLingoWordRegex = "[a-z]+";
		return c_wordString.matches(correctLingoWordRegex);
	}
	
	
	public int length() {
		int stringLength = c_wordString.length();
		Pattern ijRegex = Pattern.compile("[Ii][Jj]");
		Matcher ijMatcher = ijRegex.matcher(c_wordString);
		int occurrencesOfIJ = 0;
		while (ijMatcher.find()){
			occurrencesOfIJ ++;
	    }
		return stringLength - occurrencesOfIJ;
	}


	public boolean startsWith(Letter firstLetter) {
		return firstLetter == getLetter(0);
	}


	public Letter[] toLetters() {
		if (letters == null) {
			letters = LetterUtils.wordToLetters(c_wordString, length());
		}
		return letters;
	}
	
	public Letter getLetter(int i) {
		return toLetters()[i];
	}
	
	public boolean containsLetter(Letter letter) {
		for (Letter letterInWord : toLetters()) {
			if (letterInWord == letter) {
				return true;
			}
		}
		return false;
	}

}
