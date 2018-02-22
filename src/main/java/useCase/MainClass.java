package useCase;
import java.io.IOException;
import java.util.Scanner;

import interfaceGraphique.Appli;

import interfaceGraphique.IgTest;
import useCase.alice.*;
public class MainClass {
/** Main Principal qui appelle l'application graphique **/
	public static void main(String[] args) throws Exception {
		/*Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a title : ");
		String titre = reader.next();
		
		System.out.println("Enter a year : ");
		String annee = reader.next();
		System.out.println("Enter a chapter : ");
		String chapitre = reader.next();
		reader.close(); 
		System.out.println(titre+" "+ annee + " "+  chapitre);*/
		//PersonnageMarvel perso = new PersonnageMarvel();
		//perso.getInfo("Spider-Man");
		//perso.getInfo("batman");
		///infoComics inf = new infoComics();

		//Appli applet = new Appli();
		IgTest test = new IgTest();
		//inf.GetComics("Spider-Man","2016","6","");
		//perso.getInfo("spider-man");
		//MainClassInformationPerso info = new InformationPerso();
		//info.getNomPerso();
		//info.getWiki("Absorbing Man");
		/*Requete request = new Requete();
		try {
			System.out.println(request.getInfo("/v1/public/characters", "","&ts=1&apikey=3560ed6e3f1152119172d09d56535ffe&hash=0d7c00977c36d82b6858c8fe6b3fe53b" )); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//?ts=1&apikey=1234&hash=0d7c00977c36d82b6858c8fe6b3fe53b*/
	}

}
