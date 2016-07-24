package generate;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.Console;
import java.util.Scanner;

public class Pwd {

	private static boolean isKitten = false, isPony = false, isSpider = false, isDragon = false, isPuppy = false, isBear = false, isFlagged = false;

	/****
	 * Generates a password based on the input parameters
	 * @param input - numerical input
	 * @param animal - desired animal algorithm
	 * @param adjective - input adjective for the animal
	 * @param flag - flag indicates whether to use the alternative ascii set
	 * **********/
public static String generate(String input, String animal, String adjective, boolean flag) {
	switch( animal.toUpperCase() ) {
	case "KITTEN":
	case "PONY":
		return generate(input, animal, flag) + rot13(adjective).toLowerCase();
	case "SPIDER":
	case "DRAGON":
		return rot13(adjective).toUpperCase() + generate(input, animal, flag);
	case "PUPPY":
	case "BEAR":
		return toAltCase( rot13( adjective) ) + generate(input, animal, flag);
	default:
			return "";
	}
}

/****
 * Generates a password based on the input parameters
 * @param input - numerical input
 * @param animal - desired animal algorithm
 * @param flag - flag indicates whether to use the alternative ascii set
 * **********/
public static String generate(String input, String animal, boolean flag) {
	isFlagged = flag;
	switch( animal.toUpperCase() ) {
	case "KITTEN":
		isKitten = true;
		isPony = false;
		isSpider = false;
		isDragon = false;
		isPuppy = false;
		isBear = false;
		break;
	case "PONY":
		isKitten = false;
		isPony = true;
		isSpider = false;
		isDragon = false;
		isPuppy = false;
		isBear = false;
		break;
	case "SPIDER":
		isKitten = false;
		isPony = false;
		isSpider = true;
		isDragon = false;
		isPuppy = false;
		isBear = false;
		break;
	case "DRAGON":
		isKitten = false;
		isPony = false;
		isSpider = false;
		isDragon = true;
		isPuppy = false;
		isBear = false;
		break;
	case "PUPPY":
		isKitten = false;
		isPony = false;
		isSpider = false;
		isDragon = false;
		isPuppy = true;
		isBear = false;
		break;
	case "BEAR":
		isKitten = false;
		isPony = false;
		isSpider = false;
		isDragon = false;
		isPuppy = false;
		isBear = true;
		break;
	default:
		isKitten = false;
		isPony = false;
		isSpider = false;
		isDragon = false;
		isPuppy = false;
		isBear = false;
		break;
	}
	
	String symbolPart="", wordPart="", wordPartUpperCase="";
	
	if(input.length() == 1) {
		if(isKitten) return getSymbol(input)+input+getWord(input);
		else if(isPony) return getSymbol(input)+getWord(input)+input;
		else if (isSpider) return getWord(input).toUpperCase()+input+getSymbol(input);
		else if(isDragon) return getWord(input).toUpperCase()+getSymbol(input)+input;
		else if (isPuppy) return getSymbol(input)+input+toAltCase( rot13( getWord(input) ) );
		else if (isBear) return toAltCase( rot13( getWord(input) ) )+getSymbol(input)+input;
		else return null;
	}
	
	else if(input.length() % 2 == 0 ) {
		String temp ="";
		//build symbol part
		for(int i=0; i<input.length(); i++) {
			temp += input.charAt(i);
			symbolPart += getSymbol(temp);
			temp = "";
			}

		//build word part
		for(int i=0; i<input.length(); i++) {
			temp += input.charAt(i);
			if( (isKitten) || (isPony) ){
				if(i < input.length() / 2) wordPartUpperCase += getWord( temp ).toUpperCase();
				else wordPart += getWord( temp );
			}
			else if( (isSpider) || (isDragon) ){
				if(i < input.length() / 2) wordPart += getWord( temp );
				else wordPartUpperCase += getWord( temp ).toUpperCase();
			}
			else {
				wordPart += getWord(temp);
			}
			
			temp = "";
			} // end for loop
		
		if(isKitten) return symbolPart+wordPartUpperCase+wordPart+input;		
		else if(isPony) return symbolPart+input+wordPartUpperCase+wordPart;
		else if(isSpider) return input+wordPart+wordPartUpperCase+symbolPart;
		else if(isDragon) return input+symbolPart+wordPart+wordPartUpperCase;
		else if(isPuppy) return symbolPart+toAltCase( rot13(wordPart) )+input;
		else if(isBear) return input+symbolPart+toAltCase( rot13(wordPart) );
		else return null;
		
		} // end if input length is even
	
	else { 
		String temp = "";
		for(int i=0; i<input.length(); i++) {
			temp += input.charAt(i);
			symbolPart += getSymbol(temp);
			temp = "";
			}
		for(int i=0; i<input.length(); i++) {
			temp += input.charAt(i);
			if( (isSpider) || (isDragon) )wordPart += (i % 2 == 0) ? getWord(temp) : getWord(temp).toUpperCase();
			else if( (isKitten) || (isPony) )wordPart += (i % 2 == 0) ? getWord(temp).toUpperCase() : getWord(temp);
			else wordPart += getWord(temp);
			temp = "";
			}
		if(isKitten) return symbolPart+wordPart+input;
		else if(isPony) return symbolPart+input+wordPart;
		else if(isSpider) return input+wordPart+symbolPart;
		else if(isDragon) return input+symbolPart+wordPart;
		else if (isPuppy) return symbolPart+toAltCase( rot13( wordPart ) )+input;
		else if(isBear) return input+toAltCase( rot13( wordPart ) )+symbolPart;
		else return null;
		} //end else (i.e., input length is odd)
	} // end generate method


/**
 * Generates the word part of the password
 * @return the input number as a spelled out word
 * **/
private static String getWord(String s) {
	switch(s) {
		case "0": return "zero";
		case "1": return "one";
		case "2": return "two";
		case "3": return "three";
		case "4": return "four";
		case "5": return "five";
		case "6": return "six";
		case "7": return "seven";
		case "8": return "eight";
		case "9": return "nine";
		default: return null;
		} // end switch
	} // end getWord method


/**
 * Generates the special character part of the password
 * @return the input number's corresponding special character according to USA keyboards
 * **/
private static String getSymbol(String s){
	//alternative special character list
	if(isFlagged) {
		switch(s) {
		case "0": return ")";
		case "1": return "!";
		case "2": return "@";
		case "3": return Character.toString( (char) 128);
		case "4": return "$";
		case "5": return "'";
		case "6": return "\\";
		case "7": return "/";
		case "8": return ".";
		case "9": return "(";
		default: return null;
		}
	}
	
	else{
		
		switch(s) {
		case "0": return ")";
		case "1": return "!";
		case "2": return "@";
		case "3": return "#";
		case "4": return "$";		
		case "5": return "%";
		case "6": return "^";
		case "7": return "&";
		case "8": return "*";
		case "9": return "(";
		default: return null;
			
		}
	}
	
	
	} // end getSymbol method


	/*****
	 * Shifts, or rotates, the input string's letters by 13 letters
	 * *******/
	public static String rot13(String input) {
		
		String output = "";
		byte [] b = input.getBytes();
		
		for(int i=0; i<b.length; i++) {
			if( (b[i] >= 65 && b[i] <= 77) || (b[i] >= 97 && b[i] <= 109) ){
				b[i] += 13;
			}
			
			else if ( (b[i] >= 78 && b[i] <= 90) || (b[i] >= 110 && b[i] <= 122) ) {
				b[i] -=13;
			}
			
			else {
				//nope
			}
			
			output += (char) b[i];
		}
		return output;
	}
	
	/***
	 * Shifts ascii characters by 13
	 * *****/
	public static String asciiRot13(String input) {
		String output = rot13( input.toUpperCase() ), number ="";
		number += Integer.toString( output.getBytes()[0] ).charAt(0);
		return number+getWord(number);
	}
	
	/*****
	 * Camel cases the input string
	 * @return String as StRiNg
	 * *****/
	public static String toAltCase(String input) {
		String min ="", maj ="", output = "";
		for(int i=0; i<input.length(); i++) {
			if(i % 2 == 0) {
				min += input.charAt(i);
				output += min.toLowerCase();
				min = "";
			}
			else {
				maj += input.charAt(i);
				output += maj.toUpperCase();
				maj = "";
			}
			
		}
		
		return output;
	}
	
	/******
	 * Entry point for command line password generation
	 * @param args - not intended for usage, actual command line argument uses System.console. But if System.console is null, then this arg will be
	 * used to generate a password
	 * ********/
	public static void main(String [] args) {
		if(System.console() == null){
			PwdGUI.main(args);
		}
		else{
			String number="", animal="";
			Console console = System.console();
			number = validateNumber(console.readPassword("Enter number: "), console);
			char animalChar [] = console.readPassword("Enter animal (k=kitten, p=pony, s=spider, d=dragon, b=bear, no/invalid input=puppy): ");
			animal += (animalChar.length > 0) ? animalChar[0] : "foo";
			
			switch(animal.toLowerCase()){
			case "k": animal = "kitten";
			break;
			case "p": animal = "pony";
			break;
			case "s": animal = "spider";
			break;
			case "d": animal = "dragon";
			break;
			case "b": animal = "bear";
			break;
			default: animal = "puppy";
			break;
			}//
			StringSelection selection = new StringSelection(generate(number, animal, false));
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard.setContents(selection, selection);
		    System.out.println("Password copied to clipboard");
		}
		
	}
	/*****
	 * Ensures that the input is actually a number
	 * @param numberChar - array of chars that represents the user's input
	 * @param console - console object used to grab user input
	 * ********/
	public static String validateNumber(char [] numberChar, Console console) {
		String number = "";
		for(int i=0; i < (numberChar.length <= 4 ? numberChar.length : 4); i++) {
			number += numberChar[i];
		}
		while( !isNumber(number) ){
			System.err.println(number + " is not a number.");
			number = validateNumber(console.readPassword("Enter number: "), console);
		}
		return number;
		
	}
	
	/******
	 * Checks to see if the input is a number
	 * @param number - test string
	 * *****/
	public static boolean isNumber(String number) {
		try{
			Integer.parseInt(number);
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
	}


} // end class