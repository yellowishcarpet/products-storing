import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
public class Products {
	
	public static void main(String[] args) throws IOException{
		String failas = "duomenys.txt";
		String nesutvarkyti_duomenys = is_Failo(failas);
		String[] duomenys = new String[gaut_token(nesutvarkyti_duomenys).size()];
		gaut_token(nesutvarkyti_duomenys).toArray(duomenys);
		isvedimas_i_ekrana(duomenys);
		int programa = 0;
		while(programa == 0 ){
			pasirinkimai();
			int pasirinkimas = ivedimas_int("Pasirinkite kuria funkcija norite atlikti: ");
			switch(pasirinkimas){
				case 1:
					isvedimas_i_ekrana(duomenys);
					break;
				case 2: 
					isvedimas_i_faila(duomenys);
					break;
				case 3:
					duomenys = istrinti(duomenys);
					break;	
				case 4:
					duomenys = iterpti(duomenys);
					break;
				case 6:
					programa = 1;
					break;
				default:
					System.out.println("Neteisingas veiksmo nr. Pakartokite!");
					break;
			}
		}
		
	}
	public static String is_Failo(String failas_is) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(failas_is));
		StringBuilder sb = new StringBuilder();
		while(in.hasNext()) {
			sb.append(in.next());
		}
		in.close();
		String outString = sb.toString();
		return outString;
	}
	public static List<String> gaut_token(String str) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, ",");
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}
	public static void isvedimas_i_faila(String[] MASI) throws IOException{
		PrintWriter writer = new PrintWriter("rezultatas.txt", "UTF-8");
		writer.println(virsus());
		for(int i = 0; i<MASI.length/4;i++){
			writer.println(String.format("%-2s",Integer.toString(i+1))+String.format("%-15s| %-10s|",MASI[0+4*i],MASI[1+4*i])+
			String.format("%-10s| %-10s",MASI[2+4*i],MASI[3+4*i]));
		}
		writer.close();
	}
	public static void isvedimas_i_ekrana(String[] MASI){
		System.out.println(virsus());
		for(int i = 0; i<MASI.length/4;i++){
			System.out.println(String.format("%-2s",Integer.toString(i+1))+String.format("%-15s| %-10s|",MASI[0+4*i],MASI[1+4*i])+
			String.format("%-10s| %-10s",MASI[2+4*i],MASI[3+4*i]));
		}
	}
	public static String virsus() {
		return String.format("%-2s", "")+String.format("%-15s| %-10s|","Pavadinimas","Kilme")+
		String.format("%-10s| %-10s","Kiekis(kg)","Kaina(Eur)\n") + "-----------------------------------------------";
	}
	public static int ivedimas_int(String t){
		int atsakymas;
		System.out.println(t);
		while(true){
			try{	
				String skaito = System.console().readLine();
				atsakymas = Integer.parseInt(skaito);
				break;
			}
		catch (InputMismatchException exception){
			System.out.println("Ivedimas nera sveikasis skaicius, iveskite per naujo");
			continue;}
		catch (NumberFormatException exception){
			System.out.println("Ivedimas nera sveikasis skaicius, iveskite per naujo");
			continue;}
		catch (ArrayIndexOutOfBoundsException exception){
			System.out.println("Ivedimas nera sveikasis skaicius arba jis yra per didelis, iveskite per naujo");
			continue;}
		}
		return atsakymas;
	}
	public static String ivedimas_string(String t){
		System.out.println(t);	
		String atsakymas = System.console().readLine();
		return atsakymas;
	}
	public static String[] istrinti(String[] MASI){

		int numeris = 0;
		while(numeris<1 || numeris>MASI.length/4){
			numeris = ivedimas_int("Iveskit prekes numeri, kuria norite istrinti: ");
		}

		String[] naujas = new String[MASI.length-2];
		for (int i = (numeris-1)*4; i <MASI.length-4;i++){
			MASI[i] = MASI[i+4]; 
		}
		for(int g = 0; g<MASI.length-2;g++){
			naujas[g] = MASI[g];
		}
		return naujas;
	}
	public static String[] rasti(String[] MASI, String pavadinimas){
		int pozicija = 0;
		String[] naujas = new String[MASI.length];
		for(int i=0; i<(MASI.length+1)/4;i++){
			if(MASI[i*4].equals(pavadinimas)){
				naujas[4*pozicija	 ] = MASI[i*4];
				naujas[4*pozicija + 1] = MASI[i*4 + 1];
				naujas[4*pozicija + 2] = MASI[i*4 + 2];
				naujas[4*pozicija + 3] = MASI[i*4 + 3];
				pozicija++;	
			}
		}
		String[] naujasNaujas = new String[pozicija*4];
		for(int j = 0; j <pozicija*4 ; j++){
			naujasNaujas[j] = naujas[j];
		}
		return naujasNaujas;
	}
	public static String[] iterpti(String[] MASI){
	
		String[] naujas = new String[MASI.length+4];
		int pozicija = ivedimas_int("Iveskite i kuria vieta norite iterpti" + " (1-" +  Integer.toString(MASI.length/4+1) +  "):");
		while(true){
			if(pozicija>=1 & pozicija<=(MASI.length/4+1)){
				break;
			}
			else{
				System.out.print("Ivedet per dideli arba per maza skaiciu\n");
				pozicija = ivedimas_int("Iveskite i kuria vieta norite iterpti" + " (1-" +  Integer.toString(MASI.length/4+1) +  "): ");
			}
		}
		String pavadinimas = ivedimas_string("Iveskite prekes pavadinima: ");
		String kilme = ivedimas_string("Iveskite prekes kilmes sali: ");
		String svoris = ivedimas_string("Iveskite prekes svori: ");
		String kaina = ivedimas_string("Iveskite prekes kaina: ");
		for(int g = 0; g<pozicija*4 - 4;g++){
			naujas[g] = MASI[g];
		}

		for(int i=MASI.length-1;i>(pozicija-1)*4-1; i--){
			naujas[i+4] = MASI[i];
		}
		naujas[(pozicija-1)*4     ] = pavadinimas;
		naujas[(pozicija-1)*4 + 1 ] = kilme;
		naujas[(pozicija-1)*4 + 2 ] = svoris;
		naujas[(pozicija-1)*4 + 3 ] = kaina; 
		return naujas;
	}
	public static void pasirinkimai(){
		System.out.println("1 - Spausdinti prekes");
		System.out.println("2 - Spausdinti prekes i faila");
		System.out.println("3 - Panaikinti preke");
		System.out.println("4 - Iterpti preke");
		System.out.println("5 - Rasti prekes pagal pavadinima");
		System.out.println("6 - Iseiti");
	}
	public static void deff(){
		System.out.println(" ");
		System.out.println("Funkcijos rezultatas:");
	}
}