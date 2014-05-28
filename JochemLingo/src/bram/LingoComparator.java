package bram;


public class LingoComparator {

	
	private final Word c_correctWord;
	
	
	public LingoComparator(Word correctWord) {
		c_correctWord = correctWord;
	}
	
	public LingoCompareValue[] compare(Word guessedWord) {
		if (guessedWord.length() != c_correctWord.length()) {
			throw new IncorrectWordLengthException();
		}
		int wordLength = c_correctWord.length();
		Letter[] correctWordInLetters = c_correctWord.toLetters();
		Letter[] guessedWordInLetters = guessedWord.toLetters();
		LingoCompareValue[] values = new LingoCompareValue[wordLength];
		for (int i = 0 ; i < wordLength ; i++) {
			LingoCompareValue value = checkLetter(i, correctWordInLetters, guessedWordInLetters);
			values[i] = value;
		}
		return values;
	}

	private static LingoCompareValue checkLetter(
			int letterIndex, 
			Letter[] correctWordInLetters,
			Letter[] guessedWordInLetters) {
		if (isCorrect(letterIndex, guessedWordInLetters, correctWordInLetters)) {
			return LingoCompareValue.Correct;
		} else if (isMissed(letterIndex, guessedWordInLetters, correctWordInLetters)) {
			return LingoCompareValue.Miss;
		} else {
			return LingoCompareValue.Incorrect;
		}
	}

	

	private static boolean isCorrect(int letterIndex, 
			Letter[] guessedWordInLetters,
			Letter[] correctWordInLetters) {
		return correctWordInLetters[letterIndex] == guessedWordInLetters[letterIndex];
	}
	
	private static boolean isMissed(int letterIndex,
			Letter[] wordToCheck, Letter[] correctWord) {
		Letter letter = wordToCheck[letterIndex];
		int amountOfIncorrectOccurrencesInCorrectWord = countIncorrectOccurrencesBefore(letter, correctWord, wordToCheck, correctWord.length);
		int amountOfIncorrectOccurrencesInWordToCheckBeforeLetter = countIncorrectOccurrencesBefore(letter, wordToCheck, correctWord, letterIndex);
		if (amountOfIncorrectOccurrencesInWordToCheckBeforeLetter < amountOfIncorrectOccurrencesInCorrectWord) {
			return true;
		} else {
			return false;
		}
	}


	private static int countIncorrectOccurrencesBefore(Letter letter,
			Letter[] word, Letter[] otherWord, int before) {
		int occurrences = 0;
		for (int i = 0 ; i < before ; i++) {
			if (word[i] != otherWord[i]) {
				if (word[i] == letter) {
					occurrences++;
				}
			}
		}
		return occurrences;
	}
}

