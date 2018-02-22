package useCase.bob;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import requete.Requete;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLData;
import java.util.ArrayList;

public class gereComic {
    private int digitalId = 0;
    public gereComic(int id){
        digitalId = id ;
    }
    public gereComic(){

    }
    public String recommenderLivreSuivivant(int idComic) throws IOException, URISyntaxException, JSONException {

        String debutUrl = "https://gateway.marvel.com/v1/public/comics/";
        String midUrl = String.valueOf(idComic);
        String finUrl = "?&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
        String resultat= new String();
        String contenu ;
        String resu = "";
        contenu = Requete.getInstance().getComic(debutUrl+midUrl+ finUrl);

        JSONObject obj = new JSONObject(contenu);

        obj = obj.getJSONObject("data");
        if(obj.get("total") != (Object) 0) {
            JSONArray itemsList = obj.getJSONArray("results");
            JSONObject obj2 ;
            obj2 = itemsList.getJSONObject(0);
            ArrayList<String> character = new ArrayList<String>();
            ArrayList<String> resUrlCharacter = new ArrayList<String>();
            if(obj2.getJSONObject("characters").getInt("available") != 0 ){
                    for (int j = 0; j < obj2.getJSONObject("characters").getInt("available"); j++) {

                        character.add(obj2.getJSONObject("characters").getJSONArray("items").getJSONObject(j).get("name").toString());
                        resUrlCharacter.add(obj2.getJSONObject("characters").getJSONArray("items").getJSONObject(j).get("resourceURI").toString());
                    }

                    String nomCharacter = "";


                    for (int k = 0; k < character.size(); k++) {
                        nomCharacter += character.get(k);
                    }
                    //System.out.println(resUrlCharacter);

                    if (resUrlCharacter.size() >= 1) {
                        resultat = Requete.getInstance().getComicSql(resUrlCharacter.get(1) + finUrl);
                        obj = new JSONObject(resultat);
                        obj2 = new JSONObject();

                        obj = obj.getJSONObject("data");
                        itemsList = obj.getJSONArray("results");
                        obj2 = itemsList.getJSONObject(0);
                        //System.out.println(" ItemListe  " + itemsList);

                        for (int p = 0; p < 3; p++) {
                            resu += "<a href = \""+  obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(p).getString("resourceURI")+"\">"+ obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(p).getString("name") + "</a><br>  ";
                            //resu += obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(p).getString("resourceURI")+"  ";
                        }
                    } else {

                        resultat = Requete.getInstance().getComicSql(resUrlCharacter.get(0) + finUrl);
                        obj = new JSONObject(resultat);
                        obj2 = new JSONObject();

                        obj = obj.getJSONObject("data");
                        itemsList = obj.getJSONArray("results");
                        obj2 = itemsList.getJSONObject(0);
                        //System.out.println(" ItemListe  " + itemsList);

                        for (int p = 0; p < 3; p++) {
                            resu += "<a href = \""+  obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(p).getString("resourceURI")+"\">"+ obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(p).getString("name") + "</a><br>  ";
                            //resu += obj2.getJSONObject("comics").getJSONArray("items").getJSONObject(p).getString("resourceURI")+"  ";
                        }
                    }
                }else{
                resu = "pas de recommandation possible";
            }


        }
        else{
            System.out.println("La liste recommandee n'a pas été trouvé");
        }

        return resu;



}}
