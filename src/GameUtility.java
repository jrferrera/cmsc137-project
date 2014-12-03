import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;


public class GameUtility implements Constants{	
	public static Image getImage(String filename) {
		try{
			return ImageIO.read(new File(filename));
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<String, String> parser(String str){
		StringTokenizer st = new StringTokenizer(str, "|");
		HashMap<String, String> hm = new HashMap<String, String>();
		
		//Remove the flag
		st.nextToken();
		
		while(st.countTokens() != 0){
			String token = st.nextToken();
			String attribute = token.substring(0, token.indexOf("="));
			String value=token.substring(token.indexOf("=")+1);
			hm.put(attribute, value.trim());
		}
	
		return hm;
	}
}
