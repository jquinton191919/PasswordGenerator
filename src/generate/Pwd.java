package generate;

public class Pwd {

	private static boolean isKitten = false, isPony = false, isSpider = false, isDragon = false, isPuppy = false, isBear = false, isFlagged = false;

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
	
	public static String asciiRot13(String input) {
		String output = rot13( input.toUpperCase() ), number ="";
		number += Integer.toString( output.getBytes()[0] ).charAt(0);
		return number+getWord(number);
	}
	
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
	
	public static void main(String [] args) {
		System.out.println( generate("333", "dragon","godless", true) );
	}


} // end class