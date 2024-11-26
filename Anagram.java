/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String loweredStr1 = preProcess(str1);
		String loweredStr2 = preProcess(str2);
		for(int i = 0; i < loweredStr1.length(); i++) {
			boolean exist = false;
			for(int j = 0; j < loweredStr2.length(); j++) {
				if(loweredStr2.charAt(j) == loweredStr1.charAt(i)){
					exist = true;
				}
			}
			if(!exist) return false;
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String lowerCase = ""; 
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
	
			if (ch >= 65 && ch <= 90) {
				lowerCase += (char) (ch + 32);
			}
			else if (ch >= 97 && ch <= 122) {
				lowerCase += ch; 
			}
			else if (ch == 32) { 
				lowerCase += ch; 
			}
		}
		return lowerCase;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String anagram = "";
		while (str.length() > 0) {
			int random = (int)(Math.random() * str.length());
			char randomChar = str.charAt(random);
			anagram+= randomChar;
			str = str.substring(0, random) + str.substring(random + 1);		}
		return anagram;
	}
}
