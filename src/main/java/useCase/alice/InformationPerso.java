package useCase.alice;
import requete.Requete;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
public class InformationPerso  {
	public void getNomPerso(){
		String categorie = "/v1/public/characters";
		String midUrl = "";
		String finUrl = "?ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
		
		String contenu;
		try {
			try{
				contenu = Requete.getInstance().getInfo(categorie, midUrl, finUrl);

			JSONObject obj = new JSONObject(contenu);
			System.out.println(contenu);
			
			obj = obj.getJSONObject("data");
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			System.out.println("ici");
			for(int i =0 ; i< itemsList.length();i++){
				obj2 = itemsList.getJSONObject(i);
				
				System.out.println(obj2.get("name"));
			}
			System.out.println("la");
			}
			catch (java.net.URISyntaxException e){
				e.printStackTrace();
			}
			
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Erreur sur la requete");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Erreur sur le Json");
	}
}
	
	/*public void getWiki(String nom){
		String categorie = "/v1/public/characters?name=";
		String midUrl = nom;
		String finUrl = "&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
		
		String contenu;
		try {
			//contenu = Requete.getInstance().getInfo(categorie, midUrl, finUrl);
			//JSONObject obj = new JSONObject(contenu);
			System.out.println(contenu);
			
			obj = obj.getJSONObject("data");
			if(obj.get("total").equals(1)){
				JSONArray itemsList = obj.getJSONArray("results");
				JSONObject obj2 = new JSONObject();
				obj2 = itemsList.getJSONObject(0);
				System.out.println(obj2.get("name"));
				System.out.println(obj2.get("description"));
				System.out.println((obj2.getJSONArray("urls").getJSONObject(0)).get("url"));
				System.out.println((obj2.getJSONArray("urls").getJSONObject(1)).get("url"));
				System.out.println("https://en.wikipedia.org/wiki/"+nom.replaceAll(" ","_"));
				
			}
			else{
				System.out.println("Le personnage n'a pas été trouvé");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur sur la requete");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur sur le Json");
		}
		
		
	}*/
	

}
