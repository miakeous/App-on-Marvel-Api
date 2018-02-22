package interfaceGraphique;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.JSONException;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import requete.Connexion;
import useCase.alice.*;
import java.net.URI;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class IgTest extends JFrame {
    JFrame fen;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextField recherche;
    private JButton rechercheButton;

    private JEditorPane description;
    private JEditorPane Date;
    private JEditorPane sqlImageNom;
    private JPanel pan1;
    private JPanel infoComic;
    private JPanel infoPerso;
    private JPanel gererComic;
    private JPanel comic;
    private JPanel serie;
    private JPanel histoire;
    private JEditorPane resuComic;
    private JPanel pan;
    private JEditorPane img;
    private JEditorPane superhero;
    private JEditorPane liens;
    private JLabel nomduperso;
    private JEditorPane resuSerie;
    private JEditorPane resuHistoire;
    private JLabel annee;
    private JTextField rechercheTitre;
    private JLabel chapitre;
    private JButton valide;
    private JCheckBox variant;
    private JTextField chapi;
    private JTextField anne;
    private JScrollPane scroll;
    private JScrollPane scrollComic;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JPanel hide;
    private JTextPane imageComic;
    private JButton fermerButton;
    private JTextPane desc;
    private JTextPane auteur;
    private JTextPane caract;
    private JTextPane dateParu;
    private JEditorPane sqlNom;
    private JButton ajouterÀLaBaseButton;
    private JPanel resuSql;
    private JPanel resuComisql;
    private JEditorPane editorPane2;
    private JEditorPane editorPane3;
    private JButton addComment;
    private JButton fermerButton1;
    private JButton addBook;
    private JButton addNote;
    private JButton voirMesRecommmandationsButton;
    private JButton DelButton;
    private JLabel valueNote;
    private JButton buyButton;
    private JButton luButton;
    private JPanel recommandation;
    private JEditorPane resuReco;
    private JButton fermerButton2;
    private JButton suiteButton;
    private JComboBox nbrResu;
    private JButton button1;
    private JComboBox etat_lu;
    private JComboBox etat_achete;
    private PersonnageMarvel personnageMarvel;
    private infoComics infComic ;
    private infoComics inf;
    private int idSqlEncours=0;

        public IgTest()  {

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.setVisible(true);
            this.getContentPane().add(pan);
            this.setTitle("Test Projet Info ");

            try{
                URL url = new URL("http://www.iconninja.com/files/685/320/853/marvel-icon.png");
                Toolkit kit = Toolkit.getDefaultToolkit();
                Image img = kit.createImage(url);
                this.setIconImage(img);
            }
           catch (java.net.MalformedURLException t){
                t.printStackTrace();
           }



            this.setSize(1000, 800);
            Date.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            Date.setEditable(false);
            resuComic.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            resuComic.setEditable(false);
            resuSerie.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            resuSerie.setEditable(false);
            resuHistoire.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            resuHistoire.setEditable(false);
            caract.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            caract.setEditable(false);
            sqlNom.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            sqlNom.setEditable(false);
            sqlImageNom.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            sqlImageNom.setEditable(false);
            hide.setVisible(false);
            resuComisql.setVisible(false);
            textPane2.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            textPane2.setEditable(false);
            resuReco.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
            resuReco.setEditable(false);
            buyButton.setVisible(false);
            luButton.setVisible(false);
            recommandation.setVisible(false);
            nbrResu.addItem("20");
            nbrResu.addItem("30");
            nbrResu.addItem("40");
            nbrResu.addItem("50");
            nbrResu.addItem("60");
            nbrResu.addItem("70");
            nbrResu.addItem("80");
            nbrResu.addItem("90");
            nbrResu.addItem("99");
            nbrResu.setSelectedIndex(7);
            rechercheButton.addActionListener(new ActionListener() {
                /**
                 * Invoked when an action occurs.
                 *
                 * @param e
                 */

                public void actionPerformed(ActionEvent e) {
                    try {
                        if(recherche.getText().toString().equals("")){ superhero.setText("Pas de Personnage rentré");}
                        else{
                            try{ personnageMarvel = new PersonnageMarvel(recherche.getText().toString());
                                setInfoPerso(personnageMarvel);
                            }catch (IOException a) {
                                a.printStackTrace();
                            }
                            }

                    }  catch (JSONException e1) {
                        e1.printStackTrace();
                    } }});


            Date.addHyperlinkListener(new HyperlinkListener() {
                /**
                 * Called when a hypertext link is updated.
                 *
                 * @param e the event responsible for the update
                 */

                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        if (Desktop.isDesktopSupported()) {
                            try {
                                System.out.println("la");
                                Desktop.getDesktop().browse(e.getURL().toURI());
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (URISyntaxException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    }

                }
            });

            resuComic.addHyperlinkListener(new HyperlinkListener() {
                /**
                 * Called when a hypertext link is updated.
                 *
                 * @param e the event responsible for the update
                 */

                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        System.out.println(e.getURL().toString());
                        tabbedPane1.setSelectedIndex(1);
                        infComic = new infoComics(e.getURL().toString()+"?&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b");
                        changement(false);
                        changPanel(infComic); } }
            });


            recherche.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        if(recherche.getText().toString().equals("")){
                            superhero.setText("Pas de Personnage rentré");
                        }
                        else{
                            try{
                                personnageMarvel = new PersonnageMarvel(recherche.getText().toString());

                            }catch (IOException a){
                                a.printStackTrace();
                            }
                            String image = "<img style = \"float : right;\" src ="+(personnageMarvel.getImage())+" ALT='Thumbnails' TITLE='Test' >";
                            System.out.println(image);
                            img.setText(image);
                            superhero.setText(personnageMarvel.getNom());
                            description.setText(personnageMarvel.getDescription());

                            Date.setText(personnageMarvel.getLiens());
                            liens.setText(personnageMarvel.getDate().toString());
                            resuComic.setText(personnageMarvel.getComic().toString());
                            resuSerie.setText(personnageMarvel.getSerie().toString());
                            resuHistoire.setText(personnageMarvel.getHistoire().toString());
                            resuComic.setCaretPosition(20);
                            resuSerie.setCaretPosition(20);
                            resuHistoire.setCaretPosition(20);
                            scroll.getVerticalScrollBar().setValue(0);
                        }
                    }  catch (JSONException e1) {
                        e1.printStackTrace();
                    } }});

            rechercheTitre.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if(!rechercheTitre.getText().equals("")){

                        String boo ="" ;
                        if(variant.isSelected()){
                            boo = "true";
                        }
                        else{
                            boo = "false";
                        }
                        try{
                            System.out.println(boo);
                            infComic= new infoComics(rechercheTitre.getText().toString(),anne.getText().toString(),chapi.getText().toString(),boo,Integer.parseInt(nbrResu.getSelectedItem().toString()));
                            if(infComic.getResu()>1){
                                changement(true);
                                textPane2.setText(infComic.getTableau());
                            }
                            else{
                                changement(false);
                                changPanel(infComic);
                            }

                        }
                        catch (java.io.UnsupportedEncodingException a){
                            a.printStackTrace();
                        }
                        catch (org.json.JSONException y){
                            y.printStackTrace();
                        }
                        catch (IOException u){
                            u.printStackTrace();

                        }

                    }
                }
            });

            valide.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if(!rechercheTitre.getText().equals("")){
                        String boo ="" ;
                        if(variant.isSelected()){
                            boo = "true";
                        }
                        else{
                            boo = "false";
                        }
                        try{
                            System.out.println(boo);
                            System.out.println(nbrResu.getSelectedItem());
                            infComic= new infoComics(rechercheTitre.getText().toString(),anne.getText().toString(),chapi.getText().toString(),boo, Integer.parseInt(nbrResu.getSelectedItem().toString()));
                            if(infComic.getResu()>1){
                                changement(true);
                                textPane2.setText(infComic.getTableau());
                            }
                            else{
                                changement(false);
                                changPanel(infComic);
                            }

                        }
                        catch (java.io.UnsupportedEncodingException a){
                            a.printStackTrace();
                        }
                        catch (org.json.JSONException y){
                            y.printStackTrace();
                        }
                        catch (IOException u){
                            u.printStackTrace();

                        }

                    }
                }
            });

            textPane2.addHyperlinkListener(new HyperlinkListener() {

                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        System.out.println(e.getURL().toString());
                        String url = e.getURL().toString();
                        if(url.startsWith("http://marvel.wikia.com/wiki/")){

                            try {
                                if (Desktop.isDesktopSupported()) {
                                    Desktop.getDesktop().browse(new URI(url));
                                }
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            catch (URISyntaxException g){
                                g.printStackTrace();
                            }
                        }else{
                            inf = new infoComics(e.getURL().toString()+ "?&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b");
                            infComic=inf;
                            changement(false);
                            changPanel(inf);
                        } }
                }
            });

            fermerButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    changement(true);
                }
            });
            caract.addHyperlinkListener(new HyperlinkListener() {

                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        System.out.println(e.getURL().toString());
                        personnageMarvel = new PersonnageMarvel(e.getURL().toString()+ "?&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b");
                        tabbedPane1.setSelectedIndex(0);

                        setInfoPerso(personnageMarvel);
                    }catch (IOException a){
                        a.printStackTrace();
                    }catch (JSONException r){
                        r.printStackTrace();
                    }
                }}
            });
            ajouterÀLaBaseButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    Connexion conn = new Connexion();

                    try{
                        if(conn.addComic(infComic.getId(),"pas lu","pas acheter",String.valueOf(infComic.getChap()),String.valueOf(infComic.getAnnee()),infComic.getNom(),infComic.getUrlImage(),0,4,infComic.getDigitalId())==false){
                            new JOptionPane().showMessageDialog(new JTextField(),"Déja dans la base de donnée");
                        }
                        else
                            new JOptionPane().showMessageDialog(new JTextField(),"Le comic a bien été ajouté !");
                        System.out.println("ajouté");
                        conn.setClose();

                    }catch (java.sql.SQLException r){
                        //String input = JOptionPane.showInputDialog("Note [0,5] :");
                        new JOptionPane().showMessageDialog(new JTextField(),"Déja dans la base de donnée");
                        r.printStackTrace();
                }
                catch(JSONException t){
                        t.printStackTrace();
                }
            }});
            tabbedPane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    super.mouseClicked(e);
                    if(tabbedPane1.getSelectedIndex()==2){
                        Connexion conn = new Connexion();

                        try{
                            sqlNom.setText(conn.getTableau());
                            //sqlNom.setText(conn.comicBdd());
                            System.out.println("ajouté");
                            conn.setClose();

                        }catch (java.sql.SQLException r){
                            r.printStackTrace();
                        }
                    }



                }
            });

            sqlNom.addHyperlinkListener(new HyperlinkListener() {

                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        System.out.println((e.getURL().toString()).split("/")[3]);
                        String id = (e.getURL().toString()).split("/")[3];
                        int idComic = Integer.parseInt(id);
                        changementSql(true);
                        Connexion conn = new Connexion();
                        idSqlEncours = idComic;
                        try{
                            changementSqlPanel(idComic,conn);
                            conn.setClose();

                        }catch (java.sql.SQLException r){
                            r.printStackTrace();
                        }

                    }

                }
            });
            fermerButton1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    changementSql(false);
                }
            });
            addNote.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String note="";
                    String input = JOptionPane.showInputDialog("Note [0,5] :");
                    Connexion conn = new Connexion();
                    conn.setNote(idSqlEncours,Integer.parseInt(input));
                    valueNote.setText(afficheNote(input));



                }
            });
            addBook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String input = JOptionPane.showInputDialog("A quelle page etiez vous ? :");
                    Connexion conn = new Connexion();
                    conn.setBook(idSqlEncours,Integer.parseInt(input));

                    try {
                        changementSqlPanel(idSqlEncours,conn);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
            });
            DelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Etes vous sure de vouloir le supprimer ?","Warning",dialogButton);
                    System.out.println("present");
                    if(dialogResult == JOptionPane.YES_OPTION) {
                        // Saving code here
                        Connexion conn = new Connexion();
                        conn.dell(idSqlEncours);
                        changementSql(false);
                        sqlNom.setText(conn.getTableau());
                           }
                }
            });
            voirMesRecommmandationsButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("Affichage des dernier ajout");
                    Connexion connexion = new Connexion();
                    changementReco(false);
                    resuReco.setText(connexion.getLast());
                }
            });
            buyButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    buyButton.setVisible(false);
                    Connexion conn = new Connexion();

                    conn.setEtatAchete(idSqlEncours,"achete");
                    try{
                        changementSqlPanel(idSqlEncours,conn);
                    }catch(SQLException r){
                        r.printStackTrace();
                    }
                }
            });
            luButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    luButton.setVisible(false);
                    Connexion conn = new Connexion();

                    conn.setEtatLecture(idSqlEncours,"lu");
                    conn.setDateLecture(idSqlEncours);
                    System.out.println("passé a lu");
                    try{
                        changementSqlPanel(idSqlEncours,conn);
                    }catch(SQLException r){
                        r.printStackTrace();
                    }

                }
            });
            fermerButton2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    changementReco(true);
                }
            });
            addComment.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String input = JOptionPane.showInputDialog("Commentaire :");
                    Connexion conn = new Connexion();
                    if(!input.equals(null)) {
                        conn.setCommentaire(idSqlEncours, input);
                        editorPane2.setText(conn.getCommentaire(idSqlEncours));
                    }
                }
            });
            suiteButton.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    Connexion conn = new Connexion();
                    System.out.println("DIGITAL ID "+conn.getDigital(idSqlEncours));
                    String id =  conn.getDigital(idSqlEncours);
                    String titre = conn.getNom(idSqlEncours);
                    System.out.println(id);
                    if(id.equals("0")){
                        String chapitre = String.valueOf(Integer.parseInt(conn.getChapitre(idSqlEncours))+1);
                        String annee = conn.getAnnee(idSqlEncours);

                        System.out.println(titre);
                        try{
                            infComic = new infoComics(titre,annee,chapitre,"true",1);
                            changPanel(infComic);
                            changement(false);
                            tabbedPane1.setSelectedIndex(1);

                        }
                        catch (IOException r){
                            r.printStackTrace();
                        }


                    }else{
                        id = String.valueOf(Integer.parseInt(id)+1);
                        String url = "https://gateway.marvel.com/v1/public/comics?digitalId="+id+"&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b";
                        infComic = new infoComics(url);
                        try{
                            if(infComic.getNom().startsWith(titre)){
                                changPanel(infComic);
                                changement(false);
                                tabbedPane1.setSelectedIndex(1);
                            }
                        }catch (JSONException r){
                            r.printStackTrace();
                        }


                    }


                }
            });
            resuReco.addHyperlinkListener(new HyperlinkListener() {
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        System.out.println(e.getURL().toString());
                        tabbedPane1.setSelectedIndex(1);
                        infComic = new infoComics(e.getURL().toString()+"?&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b");
                        changement(false);
                        changPanel(infComic); }
                }
            });
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fen = new JFrame();
                    JEditorPane te = new JEditorPane();
                    te.setContentType("text/html");
                    te.setEditable(false);
                    te.setMargin(new Insets(1,10,0,0));
                    JScrollPane pan = new JScrollPane(te);
                    fen.getContentPane().add(pan);

                    JButton fermer = new JButton();
                    fen.getContentPane().add(fermer,BorderLayout.SOUTH);
                    fermer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            fen.setVisible(false);
                                }
                    });
                            fen.setVisible(true);
                            fen.setTitle("Doc Utilisateur");
                            fen.setSize(new Dimension(800,1000));

                            String resu = "";
                            try{
                                InputStream ips=new FileInputStream("truc2.txt");
                                InputStreamReader ipsr=new InputStreamReader(ips);
                                BufferedReader br=new BufferedReader(ipsr);
                                String ligne;
                                while ((ligne=br.readLine())!=null){
                                    System.out.println(ligne);
                                    resu+=ligne+"<br>";
                                }
                                br.close();
                            }
                            catch (Exception r){
                                System.out.println(r.toString());
                            }

                            te.setEditorKit(new HTMLEditorKit());
                            String filename="file:///Users/pierretardiveau/Desktop/2017PInfo88-Thunderbirds/onglet.png";
                            te.setText(resu);
                }});
        }

    private  void changement(Boolean f){

        //scrollComic.removeAll();
        textPane2.setVisible(f);
        hide.setVisible(!f);

        scrollComic.repaint();
    }
    private void changementSql(Boolean f){
        resuComisql.setVisible(f);
        resuSql.setVisible(!f);
    }
    private  void retourPerso(){



        scrollComic.repaint();
    }
    private void changPanel(infoComics info){
        System.out.println("present");
        try{
            System.out.println(info.getTitre().equals(""));
            textPane1.setText(info.getTitre());
            System.out.println(info.getTitre());
            desc.setText(info.getDesc());
            imageComic.setText(info.getImage());
            auteur.setText(info.getAut());
            caract.setText(info.getChar());
            dateParu.setText(info.getDate());

        }catch(org.json.JSONException r){
            r.printStackTrace();
        }
        hide.repaint();


    }

    private void setInfoPerso(PersonnageMarvel perso) throws org.json.JSONException{
        String image = "<img style = \"float : right;\" src ="+(perso.getImage())+" ALT='Thumbnails' TITLE='Test' >";
        //System.out.println(image);
        img.setText(image);
        superhero.setText(perso.getNom());
        description.setText(perso.getDescription());
        Date.setText(perso.getLiens());
        System.out.println(perso.getDate().toString());
        liens.setText(perso.getDate().toString());
        resuComic.setText(perso.getComic().toString());
        resuSerie.setText(perso.getSerie().toString());
        resuHistoire.setText(perso.getHistoire().toString());
        scroll.getVerticalScrollBar().setValue(0);
        resuSerie.setCaretPosition(20);
        resuHistoire.setCaretPosition(20);

    }

    private String afficheNote(String note){
        switch (Integer.parseInt(note)){
            case 0: return "";
            case 1: return "*" ;
            case 2: return "* *" ;
            case 3: return "* * *" ;
            case 4: return "* * * *" ;
            case 5: return "* * * * *" ;
             default : return "";
        }
    }

    public  void  changementSqlPanel(int idComic, Connexion conn) throws java.sql.SQLException{
        sqlImageNom.setText(conn.getNomBdd(idComic) + "<br><br><br>" + conn.getUrlImage(idComic));
        //sqlNom.setText(conn.comicBdd());
        valueNote.setText(afficheNote(conn.getNote(idComic)));
        editorPane3.setText(conn.getComic(idComic));
        System.out.println(conn.getAchete(idComic).equals("achete"));
        System.out.println(conn.getLu(idComic).equals("lu"));
        buyButton.setVisible(!conn.getAchete(idComic).equals("achete"));
        luButton.setVisible(!conn.getLu(idComic).equals( "lu"));
        editorPane2.setText(conn.getCommentaire(idComic));

        conn.setClose();

    }
    public void changementReco(boolean f){
        resuSql.setVisible(f);
        recommandation.setVisible(!f);
    }
    public static Image createImage() {
        BufferedImage img=new BufferedImage(100,50,BufferedImage.TYPE_INT_ARGB);
        Graphics g=img.getGraphics();
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,100,50);

        g.setColor(Color.YELLOW);
        g.fillOval(5,5,90,40);
        img.flush();

        return img;
    }
    public static void main(String arg[]){
        // new Connexion();
        new IgTest();
    }


}
