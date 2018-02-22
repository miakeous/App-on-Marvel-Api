package requete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import useCase.bob.gereComic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.*;
import java.sql.*;
import java.util.Random;

public class Connexion {

    private Connection connection = null;

    /**
     * Constructeur
     */
    public Connexion() {
        Statement statement = null;

        try {
            String userName = "root";
            String password = "root";
            String root="root";
            String url = "jdbc:mysql://localhost:3306/mydb";
            String url2 = "jdbc:derby:Ressource/MyDB;create=true;";
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:derby:MARVELBDD");
            statement = connection.createStatement();
            ResultSet resu = statement.executeQuery("Select * from COMIC");
            System.out.println("Connected to Database");
            while(resu.next()){
                System.out.println(resu.getInt("idComic"));
                System.out.println(resu.getString("nom"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the runing connexion
     */
    public void setClose()throws java.sql.SQLException{
        connection.close();
    }

    /**
     * @param  each column of the table comic in the database
     * @return true if adding succesfull, false if not
     */
    public Boolean addComic (int idComic, String etat_lecture, String etat_achete, String chapitre, String annee, String name, String urlImage,int num_lecture,int note, int digital) {
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement("insert into comic (idComic,NOM,annee,chapitre,etat_lecture,etat_achete,num_lecture,urlImage,note,bookmark,digitalId) values(?,?,?,?,?,?,?,?,?,0,?)");
            stat.setInt(1,idComic);
            stat.setString(2,name);
            stat.setString(3,annee);
            stat.setString(4,chapitre);
            stat.setString(5,etat_lecture);
            stat.setString(6,etat_achete);
            stat.setInt(7,num_lecture);
            stat.setString(8,urlImage);
            stat.setInt(9,note);
            stat.setInt(10,digital);
            stat.executeUpdate();
            stat.close();
            return true;
        }
        catch(java.sql.SQLException y){
            System.out.println("ici");
            y.printStackTrace();
            return false;
        }

    }

    /**
     * @return informations about every comic in the user db
     */
    public String comicBdd() {
        String resultat = "";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rst = statement.executeQuery("select * from comic");

            while (rst.next()) {
                resultat += rst.getInt("idcomic") + " ";
                resultat += rst.getString("annee") + " ";
                resultat += rst.getInt("chapitre") + " ";
                resultat += rst.getString("nom") + "\n";

            }
            statement.close();
        }
        catch (SQLException y){
            y.printStackTrace();

        }
        return resultat;
    }

    /**
     * @param id
     * @return information about a comic, depending on the user
     */
    public String getComic(int id){
        String resultat ="";
        PreparedStatement stat = null;
        try{
            String url = "Select annee, chapitre, num_lecture, etat_lecture, etat_achete,bookmark from Comic where idComic = ?";
            stat = connection.prepareStatement(url);
            stat.setInt(1,id);
            ResultSet rst = stat.executeQuery();
            while(rst.next()){
                resultat+= "<h4> Année : "+rst.getString("annee") + "</h4><br>";
                resultat+= "<h4> Chapitre : "+rst.getString("chapitre") + "</h4><br>";
                resultat+= "<h4> numéro de Lecture : "+rst.getInt("num_lecture") + "</h4><br>";
                System.out.println(rst.getString("etat_achete"));
                System.out.println(rst.getString("etat_lecture"));
                if(rst.getString("etat_achete").equals("achete")){
                    resultat+= "<h4> Possédé   </h4><br>";
                }
                else{
                    resultat+= "<h4> Non Possédé   </h4><br>";
                }
                resultat+= "<h4> Vous etiez à la page : "+rst.getInt("bookmark") + "</h4>";
            }
            stat.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultat;
    }

    /**
     * @return result of the query select * from comic
     */
    public String getTableau() {
        String resultat = "";
        Statement statement = null;
        try {
            statement= connection.createStatement();
            ResultSet rst = statement.executeQuery("select * from comic");
            int j = 0;
            resultat += "<table>" + " <tr>";
            while (rst.next()) {
                System.out.println(rst.getInt("idComic"));
                System.out.println(rst.getString("nom"));
                String titre = new String();
                String liens = new String();
                String midimg = "";

                titre =  rst.getString("nom").toString();
                liens = "https://www.google.com/"+rst.getInt("idComic");

                if(rst.getString("urlImage")==null){
                    midimg="";
                }
                else{
                    midimg = rst.getString("urlImage");
                }
                String img = "<IMG SRC=" +midimg+"/portrait_xlarge.jpg ";
                String finimg = "ALT='Thumbnails' TITLE='Test'>";
                img += finimg;
                if(j%5 != 0){
                    resultat+="<td><a href =\" "+liens +"\">"+img+"</a><br> "+titre+"  </td>  ";
                    j+=1;
                }
                else{
                    resultat +=" \n<br>  </tr>" + "" + "   <tr>" +" <td><a href =" +liens +">"+img+"</a><br> "+titre+"  </td>  ";
                    j+=1;
                }
            }
            resultat += "   </tr>\n" + "\n" + "</table>";
            statement.close();
        }
        catch (SQLException y){
            y.printStackTrace();
        }
        return resultat;
    }


    /**
     * @param idComic
     * @return name and url of the picture of a comic in the db
     */
    public String getNomBdd(int idComic) {
        String resultat ="";
        try {
            String url = "select nom,urlImage from comic where idComic = ?";
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idComic);
            ResultSet rst = query.executeQuery();
            while(rst.next()){
                resultat+= rst.getString(1);
            }
            query.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultat;
    }

    /**
     * @param idComic
     * @return url of the picture of a comic and the date of last reading
     */
    public String getUrlImage(int idComic) {
        String url = "select urlImage,datelecture from Comic where idComic = ?";
        String resultat ="";
        try {
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idComic);
            ResultSet rst = query.executeQuery();
            while(rst.next()){
                System.out.println(rst.getDate("datelecture") );
                resultat+="<img src=";
                resultat+= rst.getString(1);
                resultat += "/portrait_xlarge.jpg ></img><br>";
            }
            query.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(resultat);
        return resultat;
    }

    /**
     * @param id,note
     * Set note of a comic in the db
     */
    public void setNote(int id,int note){
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement("UPDATE Comic SET note = ? WHERE idComic=?");
            stat.setInt(1,note);
            stat.setInt(2,id);
            stat.executeUpdate();
            stat.close();
        }
        catch(java.sql.SQLException y){
            System.out.println("ici");
            y.printStackTrace();
        }
    }

    /**
     * @param idComic
     * Get note of a comic
     */
    public String getNote(int idComic) {
        String url = "select note from Comic where idComic = ?";
        String resultat ="";
        try {
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idComic);
            ResultSet rst = query.executeQuery();

            while(rst.next()){
                resultat+= rst.getString(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(resultat);
        return resultat;
    }

    /**
     * @param idComic
     * @return state of a comic if bought or not
     */
    public String getAchete(int idComic) {
        String url = "select etat_achete from Comic where idComic = ?";
        String resultat ="";
        try {
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idComic);
            ResultSet rst = query.executeQuery();
            while(rst.next()){
                resultat+= rst.getString(1);
            }
            query.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(resultat);
        return resultat;
    }

    /**
     * @param idComic
     * @return state a comic if readen or not
     */
    public String getLu(int idComic) {
        String url = "select etat_lecture from Comic where idComic = ?";
        String resultat ="";
        try {
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idComic);
            ResultSet rst = query.executeQuery();
            while(rst.next()){
                resultat+= rst.getString(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(resultat);
        return resultat;
    }


    public String getDigital(int idComic) {
        String url = "select digitalId from Comic where idComic = ?";
        String resultat ="";
        try {
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idComic);
            ResultSet rst = query.executeQuery();
            while(rst.next()){
                resultat+= rst.getString(1);
            }
            query.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(resultat);
        return resultat;
    }

    /**
     * @param idComic
     * @return chapter a of comic
     */
    public String getChapitre(int idComic) {
        String url = "select chapitre from Comic where idComic = ?";
        String resultat ="";
        try {
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idComic);
            ResultSet rst = query.executeQuery();
            while(rst.next()){
                resultat+= rst.getString(1);
            }
            query.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(resultat);
        return resultat;
    }

    /**
     * @param id,page
     * Set a bokkmark in a comic at a precise page
     */
    public void setBook(int id,int page){
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement("UPDATE Comic SET bookmark = ? WHERE idComic=?");
            stat.setInt(1,page);
            stat.setInt(2,id);
            stat.executeUpdate();
            stat.close();
        }
        catch(java.sql.SQLException y){
            y.printStackTrace();
        }
    }


    /**
     * param idSqlEncours
     * Delete a comic from the db
     */
    public void dell(int idSqlEncours) {
        PreparedStatement stat = null;
        try{
            String url = "delete  FROM Comic  WHERE idComic=?";
            stat = connection.prepareStatement(url);
            stat.setInt(1,idSqlEncours);
            stat.executeUpdate();
            stat.close();
        }
        catch(java.sql.SQLException y){
            System.out.println("ici");
            y.printStackTrace();
        }
    }

    /**
     * @param id,lecture
     * Set the state a comic to bought
     */
    public void setEtatAchete(int id,String lecture){
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement("UPDATE Comic SET etat_achete = ? WHERE idComic=?");
            stat.setString(1,lecture);
            stat.setInt(2,id);
            stat.executeUpdate();
            stat.close();
        }
        catch(java.sql.SQLException y){
            System.out.println("ici");
            y.printStackTrace();
        }
    }

    /**
     * @param id,lecture
     * Set the state of a comic to readen
     */
    public void setEtatLecture(int id,String lecture){
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement("UPDATE Comic SET etat_lecture = ? WHERE idComic=?");
            stat.setString(1,lecture);
            stat.setInt(2,id);
            stat.executeUpdate();
            String format = "dd-MM-yy";
            SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
            java.util.Date dateajd = new java.util.Date();
            java.sql.Date date = new java.sql.Date(dateajd.getYear(),dateajd.getMonth(),dateajd.getDay());
            stat = connection.prepareStatement("update Comic set datelecture = ? where idComic=?");
            stat.setDate(1,date);
            stat.setInt(2,id);
            stat.close();
        }
        catch(java.sql.SQLException y){
            System.out.println("ici");
            y.printStackTrace();
        }
    }


    /**
     * @param id
     * @return comment about a comic
     */
    public String getCommentaire(int id){
        String resultat="<h1> Commentaire </h1>";
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement("Select commentaire from Commentaire c join Comic co on c.idComic = co.idComic where co.idComic= ?");
            stat.setInt(1,id);
            ResultSet rst = stat.executeQuery();
            int i=0;
            while(rst.next()){
                resultat+= "<h4> "+ i+") </h4>";
                resultat += rst.getString("commentaire")+"<br>";
                i++;
            }
            stat.close();
        }
        catch(java.sql.SQLException y){
            System.out.println("ici");
            y.printStackTrace();
        }
        return resultat;
    }

    /**
     * @param id,com
     * Set a comment about a comic, insert in the db
     */
    public void setCommentaire(int id,String com){
        PreparedStatement stat = null;
        try{
            stat = connection.prepareStatement("insert into Commentaire (idCom , idComic,commentaire) values (?,?,?)");
            Random rand = new Random();
            int  n = rand.nextInt(1000) + 1;
            stat.setInt(1,n);
            stat.setInt(2,id);
            stat.setString(3,com);
            stat.executeUpdate();
            stat.close();
        }
        catch(java.sql.SQLException y){
            System.out.println("ici");
            y.printStackTrace();
        }
    }


    public String getLast(){
        Statement statement =null;
        String resultat = "<h1>Vos Recommandations  : </h1><br>";
        gereComic test = new gereComic();
        try{
            statement = connection.createStatement();
            String url = "Select datelecture ,idComic, nom from Comic order by datelecture asc";
            ResultSet rst = statement.executeQuery(url);
            int nombre = 0;
            while(rst.next() && nombre <5){
                nombre ++;
                resultat += "<h3>" + rst.getString("nom")+" :</h3>";
                resultat+= "<br>recommandations : <br>";
                try {
                    resultat += test.recommenderLivreSuivivant(rst.getInt("idComic"));
                    System.out.println(test.recommenderLivreSuivivant(rst.getInt("idComic")));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                resultat +="<br>";
            }
            setClose();
        }catch(SQLException r){
            r.printStackTrace();
        }
        return  resultat;
    }

    /**
     * @param idSqlEncours
     * @return name of a comic in the db
     */
    public String getNom(int idSqlEncours) {
        String url = "select nom from Comic where idComic = ?";
        String resultat ="";
        try {
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idSqlEncours);
            ResultSet rst = query.executeQuery();
            while(rst.next()){
                resultat+= rst.getString(1);
            }
            query.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(resultat);
        resultat = resultat.split("\\(")[0];
        resultat = resultat.split(" ")[0];
        return resultat;
    }

    /**
     * @param idSqlEncours
     * @return year of a comic
     */
    public String getAnnee(int idSqlEncours) {
        String url = "select annee from Comic where idComic = ?";
        String resultat ="";
        try {
            PreparedStatement query = connection.prepareStatement(url);
            query.setInt(1,idSqlEncours);
            ResultSet rst = query.executeQuery();
            while(rst.next()){
                resultat+= rst.getString(1);
            }
            query.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultat;
    }
    /**
     * @param idSqlEncours
     *
     */
    public void setDateLecture(int id){
        PreparedStatement stat = null;
        try{

            String format = "dd-MM-yy";

            SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
            java.util.Date dateajd = new java.util.Date();
            java.sql.Date dateajds = new java.sql.Date(dateajd.getYear(),dateajd.getMonth(),dateajd.getDay());
            System.out.println(formater.format(dateajds).toString());
            System.out.println((dateajds).toString());
            stat = connection.prepareStatement("update Comic set datelecture = ? where idComic=?");
            stat.setDate(1,(dateajds));
            stat.setInt(2,id);
            stat.executeUpdate();
            stat.close();
            System.out.println("DATE AJOUTE "+formater.format(dateajd).toString() );

        }
        catch(java.sql.SQLException y){
            System.out.println("ici");
            y.printStackTrace();
        }
    }
}
