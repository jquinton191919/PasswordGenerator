package generate;

import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class GUIcontrols {
	
	/***
	 * Restricts input types to numbers and to four digits
	 * @param <b>document</b>: Document of the desired GUI input; e.g., if you want your <code>JTextArea</code> object to only be numbers, type this line of code: <code>installNumberCharacters( (AbstractDocument) JTextArea.getDocument() );</code> 
	 * @author some guy on StackOverflow
	 * **/
	public static void restrictToNumberCharacters(AbstractDocument document) {
		final int maxCharacters = 4;
        document.setDocumentFilter(new DocumentFilter() {
            public void insertString(FilterBypass fb, int offset,
                    String string, AttributeSet attr)
                    throws BadLocationException {
                try {
                    if(string.length() > 0 ) Integer.parseInt(string);
                    if ( (fb.getDocument().getLength() + string.length()) <= maxCharacters ) super.insertString(fb, offset, string, attr);
                    else Toolkit.getDefaultToolkit().beep();
                } catch (Exception e) {
                    Toolkit.getDefaultToolkit().beep();
                }

            }

            public void replace(FilterBypass fb, int offset, int length,
                    String text, AttributeSet attrs)
                    throws BadLocationException {
                try {                    
                	if(text.length() > 0) Integer.parseInt(text);
                	if( (fb.getDocument().getLength() + text.length()
       		             - length) <= maxCharacters ) super.replace(fb, offset, length, text, attrs);
                	else Toolkit.getDefaultToolkit().beep();
                } catch (Exception e) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
                
    }
	
	/***
	 * Restricts input types to 26 character alphabet
	 * @param <b>document</b>: Document of the desired GUI input; e.g., if you want your <code>JTextArea</code> object to only be letters, type this line of code: <code>installNumberCharacters( (AbstractDocument) JTextArea.getDocument() );</code>
	 * **/
	public static void restrictToAlphabetCharacters(AbstractDocument document) {
		document.setDocumentFilter(new DocumentFilter() {
		    public void insertString(FilterBypass fb, int offs,
		                             String input, AttributeSet a)
		        throws BadLocationException {
		    	
		        if (isWord(input))
		            super.insertString(fb, offs, input, a);
		        else
		            Toolkit.getDefaultToolkit().beep();
		    }
		    
		    public void replace(FilterBypass fb, int offs,
		                        int length, 
		                        String input, AttributeSet a)
		        throws BadLocationException {
		        if (isWord(input))
		            super.replace(fb, offs, length, input, a);
		        else
		            Toolkit.getDefaultToolkit().beep();
		    }
		}); 
	}
	
	/**
	 * Determines if input is alphabet and not alphanumeric
	 * @param input - string to test
	 * **/
	private static boolean isWord(String input) {
		byte [] b = input.getBytes();
		boolean result = true;
    	for(int i=0; i<b.length; i++) {
    		if( (b[i] >= 65 && b[i] <= 77) || 
    			(b[i] >= 97 && b[i] <= 109) ||
    			(b[i] >= 78 && b[i] <= 90) || 
    			(b[i] >= 110 && b[i] <= 122)) {
				//do nothing
			}
    		else result = false;
    	}
    	return result;
	}

}
