import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

class Taskas {
    public double x, y;

    public Taskas(double _x, double _y) {
        x = _x;
        y = _y;
    }

    public String spausdinti() {
        return "(" + x + "; " + y + ") ";
    }
}

class Atkarpa {
    public Taskas t1, t2;

    public Atkarpa(Taskas _t1, Taskas _t2) {
        t1 = _t1;
        t2 = _t2;
    }

    public void spausdinti(String kelint) {
        System.out.println(kelint + " atkarpa:");
        System.out.println(" Pirmas atkarpos taskas: " + t1.spausdinti());
        System.out.println(" Antras atkarpos taskas: " + t2.spausdinti());
    }
}

// https://www.quora.com/Given-four-Cartesian-coordinates-how-do-I-check-whether-these-two-segments-intersect-or-not-using-C-C++
public class SankirtaV2 {
    private static Boolean lygu(double a, double b) {
        // 10^-13
        if (Math.abs(a - b) <= 0.0000000000001) {
            return true;
        } else {
            return false;
        }
    }

    // U, V = U × V = U.x * V.y - V.x * U.y
    // AB x BC
    private static double cross(Taskas A, Taskas B, Taskas C) {
        Taskas vec1 = new Taskas(B.x - A.x, B.y - A.y), vec2 = new Taskas(C.x - B.x, C.y - B.y);
        return vec1.x * vec2.y - vec2.x * vec1.y;
    }

    private static Boolean arPriklausoAtkarpai(Taskas A, Taskas B, Taskas tikr) {
        // atkarpos ilgis
        double ilgis = Math.sqrt(Math.pow(B.x - A.x, 2) + Math.pow(B.y - A.y, 2));
        // atkarpa1 = |A - Tikr|, atkarpa2 = |B - Tikr|
        double atk1 = Math.sqrt(Math.pow(A.x - tikr.x, 2) + Math.pow(A.y - tikr.y, 2));
        double atk2 = Math.sqrt(Math.pow(B.x - tikr.x, 2) + Math.pow(B.y - tikr.y, 2));
        if (lygu(atk1 + atk2, ilgis)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * if (AB × BC) * (AB × BD) < 0 and (CD × DA) * (CD × DB) < 0: return True
     */
    private static Boolean arKertasi(Atkarpa pirma, Atkarpa antra) {
        if (cross(pirma.t1, pirma.t2, antra.t1) * cross(pirma.t1, pirma.t2, antra.t2) < 0
                && cross(antra.t1, antra.t2, pirma.t1) * cross(antra.t1, antra.t2, pirma.t2) < 0) {
            return true;
        }

        if (arPriklausoAtkarpai(pirma.t1, pirma.t2, antra.t1) || arPriklausoAtkarpai(pirma.t1, pirma.t2, antra.t2)
                || arPriklausoAtkarpai(antra.t1, antra.t2, pirma.t1)
                || arPriklausoAtkarpai(antra.t1, antra.t2, pirma.t2)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "duom1.txt");
        System.out.println(filePath.toString());
        File file = new File(filePath.toString());
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            Atkarpa pirma = new Atkarpa(new Taskas(in.nextInt(), in.nextInt()), new Taskas(in.nextInt(), in.nextInt()));
            pirma.spausdinti("Pirma");
            Atkarpa antra = new Atkarpa(new Taskas(in.nextInt(), in.nextInt()), new Taskas(in.nextInt(), in.nextInt()));
            antra.spausdinti("Antra");

            Boolean kertasi = in.nextBoolean();
            if (arKertasi(pirma, antra)) {
                System.out.println("+ Atkarpos kertasi. ");
            } else {
                System.out.println("- Atkarpos nesikerta. ");
            }
            if (kertasi) {
                System.out.println("+ Pagal duomenis atkarpos kertasi.\n----------------------------------------");
            } else {
                System.out.println("- Pagal duomenis atkarpos nesikerta.\n----------------------------------------");
            }
        }
        in.close();
    }
}