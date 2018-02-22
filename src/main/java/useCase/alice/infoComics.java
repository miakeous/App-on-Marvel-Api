package useCase.alice;
import requete.Requete;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**Classe pour l'UseCase 2 d'Alice la Recherche de comic **/
public class infoComics {
	private String contenu ;
	private JSONObject obj;
	String title = "";
	public infoComics(){}
	public infoComics(String nom,String annee, String chap,String var,int nbr ) throws java.io.IOException{
		String categorie = VerifieComics(nom,annee,chap,var,nbr);
		String midUrl = "";
		title = nom;
		String finUrl = "&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
		try {
			contenu = Requete.getInstance().getInfo(categorie, midUrl, finUrl);
			 obj = new JSONObject(contenu);
			System.out.println(contenu);

			obj = obj.getJSONObject("data");}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

		System.out.println("Erreur sur la requete");
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Erreur sur le Json");
	}
		catch (URISyntaxException t){
		t.printStackTrace();
	}


	}
	/** Constructeur qui prend une url en argument correspond a la recherche d'un seul comic**/
	public infoComics(String url){
		try {
			contenu = Requete.getInstance().getComicSql(url);

			try{
				obj = new JSONObject(contenu);
				obj = obj.getJSONObject("data");
				System.out.println(obj.get("total"));
			}catch (org.json.JSONException e){
				e.printStackTrace();
			}

		}
		catch(java.net.URISyntaxException e){
			e.printStackTrace();

		}catch (IOException e){
			e.printStackTrace();
		}
	}
	/** Renvois le titre d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public String getTitre() throws org.json.JSONException{
		System.out.println(obj.get("total").equals(1));
		String resultat= "";
		if(obj.get("total").equals(1)){
			System.out.println("Dans GetTitre() dans le if");
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String titre = new String();
			obj2 = itemsList.getJSONObject(0);
			titre =  obj2.get("title").toString();
			resultat += "<h2>"+titre+"</h2>";
		}
		System.out.println(resultat);
		return resultat;

	}
	/** Renvois le Nom d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url)(pour la bdd) **/
	public String getNom() throws org.json.JSONException{
		System.out.println(obj.get("total").equals(1));
		String resultat= "";
		if(obj.get("total").equals(1)){

			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String titre = new String();
			obj2 = itemsList.getJSONObject(0);
			titre =  obj2.get("title").toString();
			resultat += titre;
		}
		System.out.println(resultat);
		return resultat;

	}
	/** Renvois l'url de l'image d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url)(pour la bdd) **/
	public String getUrlImage() throws org.json.JSONException{
		System.out.println(obj.get("total").equals(1));
		String resultat= "";
		if(obj.get("total").equals(1)){

			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String titre = new String();
			obj2 = itemsList.getJSONObject(0);
			titre =  obj2.getJSONArray("images").getJSONObject(0).get("path").toString();
			resultat += titre;
		}
		System.out.println(resultat);
		return resultat;

	}
	/** Renvois le titre d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public int getAnnee() throws org.json.JSONException{
		System.out.println(obj.get("total").equals(1));
		int resultat= 0;
		if(obj.get("total").equals(1)){

			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String titre = new String();
			obj2 = itemsList.getJSONObject(0);
			titre =  obj2.getString("title");
			titre = titre.split("\\(")[1];
			titre = titre.split("\\)")[0];
			resultat +=Integer.parseInt(titre);
		}
		System.out.println(resultat);
		return resultat;

	}
	/** Renvois le titre d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public int getId() throws org.json.JSONException{
		System.out.println(obj.get("total").equals(1));
		int resultat= 0;
		if(obj.get("total").equals(1)){

			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			int titre = 0;
			obj2 = itemsList.getJSONObject(0);
			titre =  obj2.getInt("id");

			resultat = titre;
		}
		System.out.println(resultat);
		return resultat;

	}
	/** Renvois le titre d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public int getChap() throws org.json.JSONException{
		int titre = 0;
		if(obj.get("total").equals(1)){

			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();

			obj2 = itemsList.getJSONObject(0);
			titre =  obj2.getInt("issueNumber");


		}
		return titre;
	}
	/** Renvois le nombre total de comic **/
	public int getResu() {
		int i=0;
		try{
			 i=obj.getInt("total");
		}catch(org.json.JSONException e){
			e.printStackTrace();
		}
		return i;
	}
	/** Renvois la description d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public String getDesc() throws org.json.JSONException{
		String resultat= "<b><u> Description :</u></b><br>";
		if(obj.get("total").equals(1)){
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String titre = new String();
			obj2 = itemsList.getJSONObject(0);
			titre =  obj2.get("description").toString();
			resultat += titre;
		}
		if(resultat.equals(null)){
			return "Pas de description disponible";
		}else
			return resultat;

	}
	/** Renvois la date d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public String getDate() throws org.json.JSONException{
		String resultat= "<b><u> Date de parution :</u></b><br>";
		if(obj.get("total").equals(1)){
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String date = new String();

			obj2 = itemsList.getJSONObject(0);
			date = obj2.getJSONArray("dates").getJSONObject(0).get("date").toString();
			String[] f = date.split("-");
			String annee = f[0].toString();
			String mois = f[1].toString();
			String jj = f[2].toString();
			String[] fs= jj.split("T");
			jj = fs[0].toString();
			Date rDate = new Date(Integer.parseInt(annee),Integer.parseInt(mois),Integer.parseInt(jj) );
			System.out.println();

			resultat += rDate.toString();
		}
		return resultat;

	}
	/** Renvois l'image d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public String getImage() throws org.json.JSONException{
		String resultat= "";
		if(obj.get("total").equals(1)){
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String midimg = new String();
			obj2 = itemsList.getJSONObject(0);
			if(obj2.getJSONArray("images").length()==(0)){
				midimg="";
			}
			else{
				midimg = obj2.getJSONArray("images").getJSONObject(0).get("path").toString();
			}

			String img = "<IMG SRC=" +midimg+"/portrait_xlarge.jpg ";
			String finimg = "ALT='Thumbnails' TITLE='Test'>";
			img += finimg;
			resultat = img;
		}
		return resultat;

	}
	/** Renvois les auteurs d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public String getAut() throws org.json.JSONException{
		String resultat= "<b><u> Auteurs :</u></b><br>";
		if(obj.get("total").equals(1)){
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String titre = new String();
			obj2 = itemsList.getJSONObject(0);

			for(int j=0; j<obj2.getJSONObject("creators").getInt("available");j++){
				resultat +=  " "+(obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("role").toString());
				resultat +=" : " +(obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("name").toString())+"<br>" ;

			}
		}
		return resultat;

	}
	/** Renvois les personnages présent d'un comic cette méthode n'est appelé qu'apres le constructeur infoComics(url) **/
	public String getChar() throws org.json.JSONException{
		String resultat= "<b><u> Personnages présents :</u></b><br>";
		if(obj.get("total").equals(1)){
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String titre = new String();
			obj2 = itemsList.getJSONObject(0);

			for(int j=0; j<obj2.getJSONObject("characters").getInt("available");j++){
				resultat += "<a href =\""+ obj2.getJSONObject("characters").getJSONArray("items").getJSONObject(j).get("resourceURI").toString() + "\" >";
				resultat +=(obj2.getJSONObject("characters").getJSONArray("items").getJSONObject(j).get("name").toString())+ "</a><br>" ;

			}
		}
		return resultat;

	}

	public String getContent() throws org.json.JSONException{
		String resultat= "";
		if(obj.get("total") != (Object) 0){
			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			String titre = new String();
			String description = new String();
			String date = new String();
			String midimg = new String();
			for(int i = 0 ; i<itemsList.length();i++ ){

				obj2 = itemsList.getJSONObject(i);
				titre =  obj2.get("title").toString();
				description = obj2.get("description").toString();
				date = obj2.getJSONArray("dates").getJSONObject(0).get("date").toString();
				midimg = obj2.getJSONArray("images").getJSONObject(0).get("path").toString();
				System.out.println(titre+"\n");
				System.out.println(description+"\n");
				System.out.println(date+"\n");
				String creator ="";
				String img = "<IMG SRC=" +midimg+"/portrait_xlarge.jpg ";
				String finimg = "ALT='Thumbnails' TITLE='Test'>";
				img += finimg;
				for(int j=0; j<obj2.getJSONObject("creators").getInt("available");j++){
					creator = (obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("name").toString()) + "\n";
					creator = creator + "\n" +(obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("role").toString());
				}
				if(description.equals("null")){
					resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br><p>Pas de Description<p><br><br><u> Date :</u><br>" +date +"<br><br>"+img+"<br>";
				}else if (date.equals("null")){
					resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br>"+description + "<br><br><u> Date :</u><br><p> Pas de Date <p><br><br>"+img+"<br>";
				}else
					resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br>"+description + "<br><u> Date :</u><br>" +date +"<br><br>"+img+"<br><br>";
				resultat += "<hr>";
			}
		}
		else{
			System.out.println("Le personnage n'a pas été trouvé");
			resultat= "Pas de resultat";
		}
		return resultat;

	}
	
	/**Fonction qui appelle la requete qui recupere le contenu et le traite pour recuperer les bonnes informations **/
	public String GetComics(String nom,String annee, String chap,String var,int nbr) throws UnsupportedEncodingException{
		String categorie = VerifieComics(nom,annee,chap,var,nbr);
		String midUrl = "";
		String finUrl = "&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
		String resultat= new String();

		try {
			contenu =  Requete.getInstance().getInfo(categorie,midUrl,finUrl);
			obj = new JSONObject(contenu);
			obj = obj.getJSONObject("data");
			if(obj.get("total") != (Object) 0){
				JSONArray itemsList = obj.getJSONArray("results");
				JSONObject obj2 = new JSONObject();
				String titre = new String();
				String description = new String();
				String date = new String();
				String midimg = new String();
				for(int i = 0 ; i<itemsList.length();i++ ){
					
					obj2 = itemsList.getJSONObject(i);
					titre =  obj2.get("title").toString();
					description = obj2.get("description").toString();
					date = obj2.getJSONArray("dates").getJSONObject(0).get("date").toString();
					midimg = obj2.getJSONArray("images").getJSONObject(0).get("path").toString();
					System.out.println(titre+"\n");
					System.out.println(description+"\n");
					System.out.println(date+"\n");
					String creator ="";
					String charactere = "";
					String img = "<IMG SRC=" +midimg+"/portrait_xlarge.jpg ";
					String finimg = "ALT='Thumbnails' TITLE='Test'>";
					img += finimg;
					for(int j=0; j<obj2.getJSONObject("creators").getJSONArray("items").length();j++){
						creator += (obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("role").toString()) + " " ;
						//System.out.println(obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("role").toString());
						creator += (obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("name").toString()) + "<br>";
						//creator +=   "<br>" +(obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("role").toString());
					}
					for(int j=0; j<obj2.getJSONObject("characters").getJSONArray("items").length();j++){
						//charactere += (obj2.getJSONObject("characters").getJSONArray("items").getJSONObject(j).get("role").toString()) + " : " ;
						//System.out.println(obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("role").toString());
						charactere += (obj2.getJSONObject("characters").getJSONArray("items").getJSONObject(j).get("name").toString()) + "<br>";
						//charactere +=   "<br>" +(obj2.getJSONObject("creators").getJSONArray("items").getJSONObject(j).get("role").toString());
					}
					System.out.println(creator);
					if(description.equals("null")){
						resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br><p>Pas de Description<p><br><br><u> Date :</u><br>" +date +"<br><br> <b><u> Createur :</u></b><br>"+creator+"<br><br> <b><u> Characteres présent :</u></b><br>";
					}else if (date.equals("null")){
						resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br>"+description + "<br><br><u> Date :</u><br><p> Pas de Date <p><br><br> <b><u> Createur :</u></b><br>"+creator+"<br><br> <b><u> Characteres présent :</u></b><br>";
					}else
					resultat += "<b> "+titre +"</b><br><br><u> Description :</u><br>"+description + "<br><u> Date :</u><br>" +date +"<br><br> <b><u> Createur :</u></b><br>"+creator+"<br><br> <b><u> Characteres présent :</u></b><br>";
					if(obj2.getJSONObject("characters").getInt("available") != 0){
						resultat += charactere;
					}
					else{
						resultat += "pas d'autre characteres présents dans le comic";
					}
					resultat += "<br><br>"+img+"<br>";
					resultat += "<br> <a href='https://www.google.fr/mabite' >"+ img+"</a><br><br>";
					resultat += "<hr>";
				}
			}
			else{
				System.out.println("Le personnage n'a pas été trouvé");
			}


		}  catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur sur le Json");
		}
		catch (IOException x ){
			x.printStackTrace();
		}
		catch (java.net.URISyntaxException y){
			y.printStackTrace();
		}
		
		return resultat;
	}
	/**Fonction qui permet de preciser la recherche de Comic elle regarde quelle input sont vide  **/ 
	public String VerifieComics(String titre,String annee, String chap, String var, int nbr) throws UnsupportedEncodingException{
		int a,b =0;
		String url="";
		if(titre!=""){
			if(annee.equals("")){
				a=1;
			}else{
				a=0;
			}
			if(chap.equals("")){
				b=4;
			}
			else{
				b=2;
			}

			switch(a+b){
			case 2:System.out.println("2");
					url= "/v1/public/comics?noVariants="+var+"&title="+URLEncoder.encode(titre, "UTF-8")+"&startYear="+annee+"&issueNumber="+chap+"&limit="+nbr;
					break;
			case 3: System.out.println("3");
					url= "/v1/public/comics?noVariants="+var+"&title="+URLEncoder.encode(titre, "UTF-8")+"&issueNumber="+chap+"&limit="+nbr;
					break;
			case 4: System.out.println("4");
					url= "/v1/public/comics?noVariants="+var+"&title="+URLEncoder.encode(titre, "UTF-8")+"&startYear="+annee+"&limit="+nbr;
					break;
			case 5: System.out.println("5");
					url= "/v1/public/comics?noVariants="+var+"&title="+URLEncoder.encode(titre, "UTF-8")+"&limit="+nbr;
					break;
			}
		}else url= "";
		return url;
	}

	/** Renvois un html table de tout les comics présent de le resultat de la requete **/
	public String getTableau() throws org.json.JSONException{

		String resultat= new String();
		String contenu;

			if(obj.get("total") != (Object) 0) {

				resultat ="<p> Il y a <a href=http://marvel.wikia.com/wiki/"+title+"_Comic_Books>" + obj.getInt("total") +"</a> comics en tout. </p><br>";
				JSONArray itemsList = obj.getJSONArray("results");
				JSONObject obj2 = new JSONObject();
				String titre = new String();

				String liens = new String();
				String midimg = new String();

				resultat += "<table>" +
							" <tr>";
				int j = 0;
				for (int i = 0; i < itemsList.length(); i++) {

					obj2 = itemsList.getJSONObject(i);
					titre =  obj2.get("title").toString();
					liens = obj2.get("resourceURI").toString();
					if(obj2.getJSONArray("images").length()==0){
						midimg="";
					}else{
						midimg = obj2.getJSONArray("images").getJSONObject(0).get("path").toString();

					}
					String img = "<IMG SRC=" +midimg+"/portrait_xlarge.jpg ";
					String finimg = "ALT='Thumbnails' TITLE='Test'>";
					img += finimg;



					if(j%4 != 0){
						resultat+=	"<td><a href =\" "+liens +"\">"+img+"</a><br> "+titre+"  </td>  " ;
						j+=1;
					}else{
						resultat += 	" \n<br>  </tr>" +
								"" +
								"   <tr>" +" <td><a href =" +liens +">"+img+"</a><br> "+titre+"  </td>  ";
						j+=1;
					}



				}
				resultat += "   </tr>\n" +
						"\n" +
						"</table>";

			}

		return resultat;
	}


	public int getDigitalId() throws org.json.JSONException {
		System.out.println(obj.get("total").equals(1));
		int resultat= 0;
		if(obj.get("total").equals(1)){

			JSONArray itemsList = obj.getJSONArray("results");
			JSONObject obj2 = new JSONObject();
			int titre = 0;
			obj2 = itemsList.getJSONObject(0);
			titre =  obj2.getInt("digitalId");

			resultat = titre;
		}
		System.out.println(resultat);
		return resultat;
	}
}
