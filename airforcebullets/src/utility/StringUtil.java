package utility;

import java.util.ArrayList;
import java.util.Arrays;

public class StringUtil {

	//returns a string with every word capitalized
	public static String capitalize(String str){		
		final StringBuilder result = new StringBuilder(str.length());
		String[] words = str.split("\\s");
		for(int i=0,l=words.length;i<l;++i) {
		  if(i>0) result.append(" ");      
		  result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
		}
		return result.toString();
	}
	
	public static String makeInStatement(ArrayList<Integer>ins){
		String result = "";
		for(int i=0;i<ins.size();i++){
			result+="\'";
			result+=ins.get(i);
			result+="\'";
			if(i!=ins.size()-1)result+=",";
		}
		return result;
	}
	
	public static String makeInStatement(String ins){
		String result = "";
		if(ins.contains(",")){		
			result = ins.replaceAll(",", "','");
			result = "'"+result+"'";
		}else{
			result = "'"+ins+"'";
		}
		return result;
	}
	
	public static ArrayList getArrayListFromString(String str){
		String[] pieces = str.split(",");
		for (int i = pieces.length - 1; i >= 0; i--) {
			pieces[i] = pieces[i].trim();
		}
		return new ArrayList(Arrays.asList(pieces));	
	}
	
	public static ArrayList<Integer> getIntegerArrayListFromString(String str){
		ArrayList<Integer> arl = new ArrayList<Integer>();
		String[] pieces = str.split(",");
		for (int i = pieces.length - 1; i >= 0; i--) {
			arl.add(Integer.parseInt(pieces[i].trim()));
		}
		return arl;	
	}
	
}
