package Utils;

public  class Helper {

	public static String getIntFromText(String str){
		return  str.replaceAll("[^0-9]", "");
	}

	public static String getFirstLine(String str){
		return str.substring(0, str.lastIndexOf("\n"));
	}

	public static String getTextasOneLine(String str){
		return str.replaceAll("[\r\n]+", " ");
	}

	public static String getTextWithoutBrackets(String str){
		return str.replaceAll("[()]", "");
	}

	public static String getTextWithoutExtraSpacesBetweenWords(String str){
		return str.trim().replaceAll(" +", " ");
	}


	public static boolean textHasNewLine(String value){
		String newline = "\n";
		boolean hasNewline = value.contains(newline);
		if(hasNewline){
			return  true;
		}
		return false;
	}

	public static boolean textHasSpecialChar(String text){
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			if (!Character.isDigit(text.charAt(i))&& !Character.isLetter(text.charAt(i))&& !Character.isWhitespace(text.charAt(i))){
				count++;
			}
		}
		if (count > 0)
			return false;
		else{
			return true;
		}
	}

}
