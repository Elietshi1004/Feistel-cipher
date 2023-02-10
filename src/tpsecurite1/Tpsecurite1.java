/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpsecurite1;

import java.util.Scanner;

/**
 *
 * @author Elie tshi
 */
public class Tpsecurite1 {

    /**
     * @param val
     * @param args the command line arguments
     */
    //On entre et la valeur et la clé de permutation
    public static String permutation(String val, String k) {
        String res = "";
        char[] tabk = new char[val.length()];

        for (int i = 0; i < val.length(); i++) {
            String id = k.substring(i, i + 1);
            int vid = Integer.valueOf(id);
            tabk[i] = val.charAt(vid);
            res = res + tabk[i];
        }
        System.out.println("res permut " + res);
        return res;
    }

    //Recupère une clé de permutation et renvoie son inverse
    public static String InversePermutation(String k) {
        String res = "";
        String[] tabk = new String[k.length()];

        for (int i = 0; i < k.length(); i++) {
            String id = k.substring(i, i + 1);
            int vid = Integer.valueOf(id);
            tabk[vid] = String.valueOf(i);

        }
        for (String tabk1 : tabk) {
            res = res + tabk1;
        }
        System.out.println("res inverse " + res);
        return res;
    }

    //fais le Ou exclusif entre deux entrée
    public static String OuExcusif(String val1, String val2) {
        String res = "";
        String[] tabk = new String[val1.length()];
        for (int i = 0; i < val1.length(); i++) {
            String v1 = val1.substring(i, i + 1);
            String v2 = val2.substring(i, i + 1);

            tabk[i] = (v1.equals(v2)) ? "0" : "1";

        }
        for (String tabk1 : tabk) {
            res = res + tabk1;
        }
        return res;
    }

    //fais le Ou normal entre deux entrée
    public static String OuNormal(String val1, String val2) {
        String res = "";
        String[] tabk = new String[val1.length()];
        for (int i = 0; i < val1.length(); i++) {
            String v1 = val1.substring(i, i + 1);
            String v2 = val2.substring(i, i + 1);

            tabk[i] = (v1.equals("1") || v2.equals("1")) ? "1" : "0";

        }
        for (String tabk1 : tabk) {
            res = res + tabk1;
        }
        return res;
    }

    //fais le ET logique entre deux entrées
    public static String ET(String va11, String val2) {
        String res = "";
        String[] tabk = new String[va11.length()];
        for (int i = 0; i < va11.length(); i++) {
            String v1 = va11.substring(i, i + 1);
            String v2 = val2.substring(i, i + 1);

            tabk[i] = (v1.equals("1") && v2.equals("1")) ? "1" : "0";

        }
        for (String tabk1 : tabk) {
            res = res + tabk1;
        }
        return res;
    }

    //on a la valeur à decaler, l'ordre de decalage et la direction en boolean :: vrrai pour gauche et faux pour droite
    public static String decalage(String val, int ordre, boolean g) {
        String res = "";
        String[] tabk = new String[val.length()];
        int d = 0;
        for (int i = 0; i < val.length(); i++) {
            String v1 = val.substring(i, i + 1);
            int o = ordre;
            int j = i;
            int s = g ? -1 : 1;//celui qui sert à soustraire
            while (o > 0) {
                if (j + s < 0) {
                    j = val.length() - 1;
                } else if (j + s >= val.length()) {
                    j = 0;
                } else {
                    j = j + s;
                }
                o--;

            }

            tabk[j] = v1;

        }
        for (String tabk1 : tabk) {
            res = res + tabk1;
        }
        return res;
    }

    //Methode principale pour générer les deux sous clés séparées par une virgule
    public static String generationCle(String k, String pk, int gdecalage, int ddecalage) {
        String res = "";
        String nk = permutation(k, pk);
        String k1 = nk.substring(0, 4);
        String k2 = nk.substring(4, 8);
        String nk1 = OuExcusif(k1, k2);
        String nk2 = ET(k1, k2);
        String dnk1 = decalage(nk1, gdecalage, true);
        String dnk2 = decalage(nk2, ddecalage, false);
        res = dnk1 + "," + dnk2;
        System.out.println("res keygen " + res);
        return res;
    }

    //Divise une valeur en deux blocs de 4
    public static String divdeux(String val) {
        String res = "";
        return res;
    }

    //On fait la formule du premier Round concernant D avec kp comme clé de permutation et k comme clé de k1
    public static String roundD(String val, String kp, String k) {
        String res = "";
        String perm = permutation(val, kp);
        res = OuExcusif(perm, k);
        return res;
    }

    //On fait la formule du premier Round concernant G avec vald pour D0, valg pour G0, k pour k1
    public static String roundG(String vald, String valg, String k) {
        String res = "";
        String fc = OuNormal(valg, k);
        res = OuExcusif(vald, fc);
        return res;
    }

    //On fait la formule du premier Round concernant G avec kp comme clé de permutation et k comme clé de k1
    public static String roundGDechiffre(String val, String kp, String k) {
        String res = "";
        String nkp = InversePermutation(kp);
        String c = OuExcusif(val, k);
        res = permutation(c, nkp);
        return res;
    }

    //On fait la formule du premier Round concernant G avec vald pour G21, valg pour G10, k pour k1
    public static String roundDDechiffre(String vald, String valg, String k) {
        String res = "";
        String fc = OuNormal(valg, k);
        res = OuExcusif(vald, fc);
        return res;
    }

    public static void main(String[] args) {
        // TODO code application logic here
//        String rs = generationCle("01101101","65274130",2,1);
//        System.out.println("value " + rs );

        System.out.println("**********FREISNEL CIPHER************");
        System.out.println("Entrez la clé K de longueur 8");
        String Key = "";
        Scanner lire = new Scanner(System.in);
        while (Key.length() < 8) {
            System.out.println("La taille doit être de longueur 8");
            Key = lire.nextLine();
        }
        System.out.println("Entrez la fonction H de permutation");
        String H = "";
        while (H.length() < 8) {
            System.out.println("La taille doit être de longueur 8");
            H = lire.nextLine();
        }
        int decg = 0, decd = 0;//decalage à gauche et à droite
        System.out.println("Entrez l'ordre de decalage à gauche");
        while (decg <= 0) {
            System.out.println("L'ordre doit être positif");
            decg = lire.nextInt();
        }
        System.out.println("Entrez l'ordre de decalage à droite");
        while (decd <= 0) {
            System.out.println("L'ordre doit être positif");
            decd = lire.nextInt();
        }
        String kgen = generationCle(Key, H, decg, decd);
        System.out.println("Entrez la valeur à traiter");
        String N = "";
        while (N.length() < 8) {
            System.out.println("La taille doit être de longueur 8");
            N = lire.nextLine();
        }

        int choix = -1;//0 pour chiffrement, et 1 pour dechiffrement 
        while (choix != 1 && choix != 0) {
            System.out.println("Vous voulez déchiffrez ou chiffrez? (1 pour dechiffrer et 0 pour chiffrer)");
            choix = lire.nextInt();

        }
        System.out.println("Entrez la permutation P de 4 bits");
        String P = "";
        while (P.length() < 4) {
            System.out.println("La taille doit être de longueur 4");
            P = lire.nextLine();
        }
        System.out.println("Entrez la clé de permutation pour le chiffrement ou déchifrement");
        String KeyC = "";
        while (KeyC.length() < 8) {
            System.out.println("La taille doit être de longueur 8");
            KeyC = lire.nextLine();
        }
        String[] tabkey = kgen.split(",");
        if (choix == 0) {
            String pN = permutation(N, KeyC);
            String g0 = pN.substring(0, 4);
            String d0 = pN.substring(4, 8);
            String d1 = roundD(g0, P, tabkey[0]);
            String g1 = roundG(d0, g0, tabkey[0]);
            String d2 = roundD(g1, P, tabkey[1]);
            String g2 = roundG(d1, g1, tabkey[1]);
            String C = g2 + d2;
            String ikey = InversePermutation(KeyC);
            String res = permutation(C, ikey);
            System.err.println("La valeur chiffrée est : " + res);

        } else {
            String pN = permutation(N, KeyC);
            String g2 = pN.substring(0, 4);
            String d2 = pN.substring(4, 8);
            String g1 = roundGDechiffre(d2, P, tabkey[1]);
            String d1 = roundDDechiffre(g2, g1, tabkey[1]);
            String g0 = roundGDechiffre(d1, P, tabkey[0]);
            String d0 = roundDDechiffre(g1, g0, tabkey[0]);
            String Nd = g0 + d0;
            String ikey = InversePermutation(KeyC);
            String res = permutation(Nd, ikey);
            System.err.println("La valeur dechiffrée est : " + res);
        }
    }

}
