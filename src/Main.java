import java.util.*;

public class Main {

    public static void main(String[] args) {
	    // write your code here
	    System.out.println("Hello World!");
	    
	    Main main = new Main();
	    
	    List<Integer> wordList = new ArrayList<Integer>();
	    wordList.add(1);
	    wordList.add(1);
	    wordList.add(2);
	    wordList.add(3);
	  	wordList.add(2);
	    
	    main.duplicateWords(wordList);
    }
    
    private void duplicateWords(List<Integer> wordList){
        
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> filteredSet = new HashSet<Integer>();
        
        for(Integer count : wordList){
            if(!set.add(count)){
                filteredSet.add(count);
                System.out.println(">>>"+count);
            }
        }
    }
}
