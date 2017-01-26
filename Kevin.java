import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;


public class Kevin {

	void myfunc(String phoneNumber) throws ParseException
	{
		
		phoneNumber = phoneNumber.replaceAll("\\D+","");
		String phoneMask= "###-###-####";
		

		MaskFormatter maskFormatter= new MaskFormatter(phoneMask);
		maskFormatter.setValueContainsLiteralCharacters(false);
		String phoneNumber2 = maskFormatter.valueToString(phoneNumber) ;
		System.out.println(phoneNumber2);
	}
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Kevin k = new Kevin();
		k.myfunc("65.0555.1234");
		
	}

}
