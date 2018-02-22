package useCase.alice;
import java.awt.image.BufferedImage;
import requete.Requete;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**Classe qui correspond a Alice 1 elle doit interroger l'api est afficher les informations d'un personnage **/
public class PersonnageMarvel {

	//private String nomPerso;
	private JSONObject obj ;
	private String contenu;
	private String nomPerso;




	public String getImage() throws JSONException {
		if(obj.get("total").equals(1)) {
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			if(obj2.getJSONObject("thumbnail").length()==0){
				return "pas d'image";
			}
			else{
				return obj2.getJSONObject("thumbnail").getString("path")+"/portrait_xlarge.jpg";
			}
		}
		else{
			return "pas d'image";
		}
	}

	/**
	 * @return name of a comic
	 */
	public String getNom() throws JSONException{
		if(obj.get("total").equals(1)) {
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			return  "<h2 style = 'text-align : center;'><u >"+ obj2.get("name").toString()+"</u></h2>";
		}
		else{
			return "pas d'image";
		}



	}

	/**
	 * @return Description of a comic
	 */
	public String getDescription() throws JSONException{
		String resultat = new String();
		resultat = "<b><u> Description :</u></b><br>";
		if(obj.get("total").equals(1)) {
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			resultat += "<p>";
			resultat += obj2.get("description").toString();
			if(!resultat.equals("null"))
				return   resultat+"</p>";
			else
				return "Pas de description disponible";
		}
		else{
			return "pas d'image";
		}

	}

	/**
	 * @return Link to Wikidata and Wikipedia
	 */
	public String getLiens() throws JSONException{
		String resultat = "<b><u>Liens Utiles :</u></b> <br><br>";
		if(obj.get("total").equals(1)) {
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			String url1 = obj2.getJSONArray("urls").getJSONObject(0).getString("url");
			resultat += "<a href  =\"" + url1 +"\"> Wikidata</a><br> ";
			if(nomPerso == null){
				nomPerso = obj2.getString("name");
			}
			resultat += "<a href='" + "https://en.wikipedia.org/wiki/"+nomPerso.replaceAll(" ","_")+"'>"+"https://en.wikipedia.org/wiki/"+nomPerso.replaceAll(" ","_") +"</a>.";
			return   resultat;
		}
		else{
			return "pas d'image";
		}

	}

	/**
	 * @return Date of a comic
	 */
	public String getDate() throws JSONException{
		String resultat = "<p> Il y a ";
		if(obj.get("total").equals(1)) {
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			resultat += obj2.getJSONObject("comics").getInt("available");
			resultat += " comics disponible avec ce Personnage <br>";
			resultat += " Il y a ";
			resultat += obj2.getJSONObject("series").getInt("available");
			resultat += " séries disponible avec ce Personnage <br>";
			resultat += " Il y a ";
			resultat += obj2.getJSONObject("stories").getInt("available");
			resultat += " histoires disponible avec ce Personnage <br></p>";
			System.out.println(resultat);
			return resultat;
		}
		else{
			return "pas d'image";
		}

	}

	/**
	 * @return Title of a comic
	 */
	public String getComic() throws JSONException{
		String resultat = new String();
		if(obj.get("total").equals(1)) {
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			for(int i = 0 ; i<obj2.getJSONObject("comics").getJSONArray("items").length();i++ ){
				String titre = new String();
				titre = (String) obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(i).get("name");
				resultat += "<p> Titre du Comic numero " + i +"</p> <a href = "+obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(i).getString("resourceURI") + "> " + titre + "</a><br>";
			}

			return resultat;
		}
		else{
			return "pas d'image";
		}

	}

	/**
	 * @return Name of the serial of a comic
	 */
	public String getSerie() throws JSONException{
		String resultat = new String();
		if(obj.get("total").equals(1)) {
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			for(int i = 0 ; i<obj2.getJSONObject("series").getJSONArray("items").length();i++ ){
				String titre = new String();
				titre = (String) obj2.getJSONObject("series").getJSONArray("items").getJSONObject(i).get("name");
				resultat += "<p> Titre de la Série numero " + i +"</p> <a href = "+obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(i).getString("resourceURI") + "> " + titre + "</a><br>";
			}
			return resultat;
		}
		else{
			return "pas d'image";
		}

	}

	/**
	 * @return Name of a story
	 */
	public String getHistoire() throws JSONException{
		String resultat = new String();
		if(obj.get("total").equals(1)) {
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			obj2 = itemsList.getJSONObject(0);
			for(int i = 0 ; i<obj2.getJSONObject("stories").getJSONArray("items").length();i++ ){
				String titre = new String();
				titre = (String) obj2.getJSONObject("stories").getJSONArray("items").getJSONObject(i).get("name");
				resultat += "<p> Titre de l'histoire numero " + i +"</p> <a href = "+obj2.getJSONObject("stories").getJSONArray("items").getJSONObject(i).getString("resourceURI") + "> " + titre + "</a><br>";
			}
			return resultat;
		}
		else{
			return "pas d'image";
		}

	}

	/**
	 * @param nomperso
	 */
	public PersonnageMarvel(String nomperso) throws IOException, JSONException {
		if(nomperso.startsWith("http")){
			try{
				contenu = Requete.getInstance().getComic(nomperso);
				obj = new JSONObject(contenu);
				obj = obj.getJSONObject("data");
			}
			catch(java.net.URISyntaxException e){
				e.printStackTrace();
			}
		}
		else{
			nomPerso = nomperso;
			String categorie = "/v1/public/characters?name=";
			String midUrl = nomPerso;
			String finUrl = "&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
			try{
				contenu = Requete.getInstance().getInfo(categorie, midUrl, finUrl);
				obj = new JSONObject(contenu);
				obj = obj.getJSONObject("data");
			}
			catch(java.net.URISyntaxException e){
				e.printStackTrace();

			}
		}
	}

}
