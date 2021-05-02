package com.labforward.notebook.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceUtils {

	public static List<String> findSimilarWords(String words, String requestedWord) {
		List<String> similarWords = new ArrayList<>();
		Arrays.asList(words.split("\\s+")).forEach((word) -> {
			getDistance(requestedWord, similarWords, null, word);
		});
		return similarWords;
	}

	private static List<String> getDistance(String requestedWord, List<String> similarWords, List<String> frequentWords,
			String word) {
		int distance = calculate(requestedWord, word);
		if (null!=similarWords && distance == 1) {
			similarWords.add(word);
		} else if ( null!=frequentWords && distance < 1) {
			frequentWords.add(word);
		}
		return null != similarWords ? similarWords : frequentWords;
	}

	static int calculate(String x, String y) {
		// System.out.println("\n X "+x+"\t Y "+y);
		int[][] dp = new int[x.length() + 1][y.length() + 1];

		for (int i = 0; i <= x.length(); i++) {

			for (int j = 0; j <= y.length(); j++) {
				if (i == 0) {
					dp[i][j] = j;
					// System.out.print(" "+dp[i][j]);
				} else if (j == 0) {
					dp[i][j] = i;

					// System.out.print(" "+dp[i][j]);

				} else {
					dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
							dp[i - 1][j] + 1, dp[i][j - 1] + 1);
					// System.out.print(" "+dp[i][j]);
				}
			}
			// System.out.println("\t");
		}

		return dp[x.length()][y.length()];
	}

	public static int costOfSubstitution(char a, char b) {
		// System.out.print(""+a+" "+b);
		return a == b ? 0 : 1;
	}

	public static int min(int... numbers) {
		return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
	}

	public static int frequencyOfWord(String words, String frequentWord) {

		List<String> frequentWords = new ArrayList<>();
		Arrays.asList(words.split("\\s+")).forEach((word) -> {
			getDistance(frequentWord, null, frequentWords, word);
		});
		return frequentWords.size();
	}

	/*public static void main(String[] args) {
		String words1 = "Word Word word Wor";
		String requestedWord = "Word";
		List<String> similarWords1 = findSimilarWords(words1, requestedWord);
		System.out.println("Similar Words A : " + similarWords1);
		System.out.println("Frequency of word : " + frequencyOfWord(words1, requestedWord));

		words1 = "Word Word Word word";
		requestedWord = "Word";
		List<String> similarWords2 = findSimilarWords(words1, requestedWord);
		System.out.println("Similar Words B : " + similarWords2);
		System.out.println("Frequency of word : " + frequencyOfWord(words1, requestedWord));

	}*/
}
