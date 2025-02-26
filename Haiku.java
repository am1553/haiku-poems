import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;

/*
 * (1a) Generating random Haiku poems.
 *
 * @author Aryan Mehta
 * @version 23/02/2025
 */

class Haiku {
    public TreeMap<Integer, ArrayList<String>> words;
    public Random ran;
    
    // (1b) Initializes words and ran
    public Haiku() {
        words = new TreeMap<>();
        ran = new Random();
    }
    
    /**
     * Get the sample words in ArrayList<String> structure 
     * @param sampleWords an array of sample words
     * @return sample words in ArrayList<String> structure
     */
    private ArrayList<String> toArrayListString(String[] sampleWords) {
        ArrayList<String> lists = new ArrayList<String>();
        
        for(String word : sampleWords) {
            lists.add(word);
        }
        
        return lists;
    }
    
    // (1c) Populate with sample words
    public void populate() {
        // Sample words
        String[] oneSyllsSampleWords = {"sky", "breeze", "rain", "sun", "moon", "light", "dark", "stone", "grass", "cloud"};
        String[] twoSyllsSampleWords = {"river", "summer", "winter", "silent", "gentle", "falling", "morning", "flower", "whisper", "shadow"};
        String[] threeSyllsSampleWords = {"butterfly", "beautiful", "evergreen", "waterfall", "harmony", "wonderful", "delicate", "tomorrow", "melody", "whispering"};
        String[] fourSyllsSampleWords = {"magnolia", "serendipity", "illuminated", "appreciated", "emotionally", "imagination", "enchantedly", "mysterious"};
        String[] fiveSyllsSampleWords = {"electricity", "opportunity", "imagination", "community", "personality"};
        String[] sixSyllsSampleWords = {"discrimination", "responsibility", "superstition", "invisibility"};
        String[] sevenSyllsSampleWords = {"understandingly", "misunderstanding", "insurmountability", "individuality"};
        
        words.put(1, toArrayListString(oneSyllsSampleWords));
        words.put(2, toArrayListString(twoSyllsSampleWords));
        words.put(3, toArrayListString(threeSyllsSampleWords));
        words.put(4, toArrayListString(fourSyllsSampleWords));
        words.put(5, toArrayListString(fiveSyllsSampleWords));
        words.put(6, toArrayListString(sixSyllsSampleWords));
        words.put(7, toArrayListString(sevenSyllsSampleWords));
    }
    
    // (1d) Print the contents of the words map
    public void printWords() {
        
        words.forEach((key, value) -> {
            String joinWords = "";
            for(String word : value) {
                joinWords = joinWords + word + " ";
            }
            System.out.println(key + " -> " + joinWords.trim());
        });
    }
    
    /*
     * (1e) Adds word to the words map based on the syllables count
     * @param syllsCount number of syllables in the word
     * @param word a word to be added 
     */
    public void addWord(int syllsCount, String word) {
        String convertWord = word.trim().toLowerCase();
        ArrayList<String> arrWord = new ArrayList<String>();
        arrWord.add(word);
        
        if(syllsCount < 1 || syllsCount > 7) return; // return if word is out of the range
        if(words.get(syllsCount).contains(convertWord)) return; // return if word already exists
        
        // if the key does not exist
        if(!words.containsKey(syllsCount)) {
            words.put(syllsCount, arrWord);
            return;
        }
        
        words.get(syllsCount).add(convertWord);
    }
    
    /*
     * (1f) Creates a single line of a poem
     * @param totalSyllables the number of syllables the line should have in total
     */
    public String makeLine(int totalSyllables) {
        populate();
        
        String output = "";
        int count = totalSyllables;
    
        while(count > 0) {
            int randomNumber = ran.nextInt(7) + 1; // get a number between 1 and 7 representing syllables
            if(randomNumber > count) continue; // if the syllables is greater than the required syllables then re-run the loop
            
            ArrayList<String> wordsList = words.get(randomNumber);
            int randomWordIndex = ran.nextInt(wordsList.size());
            if(output.contains(wordsList.get(randomWordIndex))) continue; // if the word exists in output string then re-run the loop
            
            output = output + " " + wordsList.get(randomWordIndex);
            count -= randomNumber;
        }
        
        return output.trim();
    }
    
    /*
     * (1g) Generates and prints three lines of a Haiku
     */
    public void printHaiku() {
        System.out.println(makeLine(5));
        System.out.println(makeLine(7));
        System.out.println(makeLine(5));
    }
}

