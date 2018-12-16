import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


    public class HolaMundo {
     
    	public static void main(String[] args) {		
    		System.out.println("Hola soy el doctor");
    		JSONParser parser = new JSONParser();
    		Object obj = parser.parse(new FileReader(
                    "pacientes.json"));





    	}
     
    }