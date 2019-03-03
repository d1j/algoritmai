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

class Tiese {
    public double k, b;
    public Taskas t1, t2;
    public Boolean vertikali = false;
    public Boolean horizontali = false;

    public Tiese(Taskas _t1, Taskas _t2) {
        t1 = _t1;
        t2 = _t2;
    }

    public void skaiciuotiLygti() {
        if (t1.x == t2.x) {
            vertikali = true;
        }
        if (t1.y == t2.y) {
            horizontali = true;
        }
        if (!vertikali && !horizontali) {
            k = (t2.y - t1.y) / (t2.x - t1.x); // k=(y2-y1)/(x2-x1)
            b = t1.y - k * t1.x; // b = y - kx
        }
    }

    public void spausdinti(String kelint) {
        System.out.println(kelint + " atkarpa:");
        System.out.println(" Pirmas atkarpos taskas: " + t1.spausdinti());
        System.out.println(" Antras atkarpos taskas: " + t2.spausdinti());
        System.out.printf(" Tieses lygtis: y = %.2fx + %.2f\n", k, b);
    }
}

public class Sankirta_old {
    private static Boolean lygu(double a, double b) {
        if (Math.abs(a - b) <= 0.000000000000001) {
            return true;
        } else {
            return false;
        }
    }

    private static Boolean arPriklausoAtkarpai(Taskas tikr, Taskas t1, Taskas t2) {
        double atst1 = Math.abs(tikr.x - t1.x), atst2 = Math.abs(tikr.x - t2.x);
        if (lygu(atst1 + atst2, Math.abs(t1.x - t2.x))) {
            return true;
        } else {
            return false;
        }
    }

    private static Boolean arKertasi(Tiese _pirma, Tiese _antra) {
        Boolean kertasi = false;
        // Patikrinam, ar atkarpos ne vertikalios ir ne horizontalios
        if (!_pirma.vertikali && !_antra.vertikali && !_pirma.horizontali && !_antra.horizontali) {
            // Patikrinam, ar taškai sutampa.
            if (_pirma.t1 == _antra.t1 || _pirma.t2 == _antra.t2 || _pirma.t1 == _antra.t2) {
                kertasi = true;
            } else {
                // Randamas tiesių kirtimosi x
                double kirtX = (_antra.b - _pirma.b) / (_pirma.k - _antra.k);
                // Toliau tikrinama, ar kirtimosi x priklauso abiejoms atkarpoms.
                // Jei taip - tiesės kertasi.
                double atst1 = Math.abs(kirtX - _pirma.t1.x), atst2 = Math.abs(kirtX - _pirma.t2.x);
                if (lygu(atst1 + atst2, Math.abs(_pirma.t1.x - _pirma.t2.x))) {
                    double atst3 = Math.abs(kirtX - _antra.t1.x), atst4 = Math.abs(kirtX - _antra.t2.x);
                    if (lygu(atst3 + atst4, Math.abs(_antra.t1.x - _antra.t2.x))) {
                        kertasi = true;
                    }
                }

            }

        } else {
            // Tikriname atkarpų vertikalumą/horizontalumą
            if (_pirma.vertikali && !_antra.vertikali) {

            }

        }
        return kertasi;
    }

    public static void main(String[] args) throws Exception {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "duom.txt");
        System.out.println(filePath.toString());
        File file = new File(filePath.toString());
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            Tiese pirma = new Tiese(new Taskas(in.nextInt(), in.nextInt()), new Taskas(in.nextInt(), in.nextInt()));
            pirma.skaiciuotiLygti();
            pirma.spausdinti("Pirma");
            Tiese antra = new Tiese(new Taskas(in.nextInt(), in.nextInt()), new Taskas(in.nextInt(), in.nextInt()));
            antra.skaiciuotiLygti();
            antra.spausdinti("Antra");
            Boolean kertasi = in.nextBoolean();
            if (arKertasi(pirma, antra)) {
                System.out.println("Atkarpos kertasi plokstumoje.\n");
            } else {
                System.out.println("Atkarpos nesikerta plokstumoje.\n");
            }
        }

    }
}