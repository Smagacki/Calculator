public class Calculate {
    private double liczba1;
    private double liczba2;
    private String dzialanie;
    private double wynik;

    private boolean isLiczba1;
    private boolean isLiczba2;
    private boolean isWynik;

    private int wprowadzanaLiczba;  // 1 - pierwsza liczba,   2 - druga liczba
    private String wynikWyswietlany;

    public Calculate() {
        resetujWartosci();
    }

    String updateLabel(String klawisz) {
        if (wynikWyswietlany.startsWith("b") && !klawisz.startsWith("C"))
            return wynikWyswietlany;
        else {
            switch (klawisz) {
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                    if (wprowadzanaLiczba == 1) {
                        liczba1 = liczba1 * 10 + Double.parseDouble(klawisz);
                        isLiczba1 = true;
                    } else {
                        liczba2 = liczba2 * 10 + Double.parseDouble(klawisz);
                        isLiczba2 = true;
                    }
                    ustalWynikWyswietlany();
                }
                case "+", "-", "*", "/" -> {
                    wykonajObliczenia();
                    dzialanie = klawisz;
                    wprowadzanaLiczba = 2;
                }
                case "=" -> wykonajObliczenia();
                case "%" -> System.out.println(klawisz);
                case "," -> System.out.println(klawisz);
//            case "+/-" -> {
//                if (!isWynik)
//                    if (wprowadzanaLiczba == 1)
//                        wynik = -liczba1;
//                    else
//                        wynik = -liczba2;
//                else
//                    wynik = -wynik;
//
//                ustalWynikWyswietlany();
//            }
                case "CE", "C" -> resetujWartosci();
            }
            return wynikWyswietlany;
        }
    }

    void wykonajObliczenia() {
        if (isLiczba1 && dzialanie != null && isLiczba2) {
            if (dzialanie.equals("/") && liczba2 == 0)
                wynikWyswietlany = "błąd: dzielenie przez 0!";
            else {
                wynik = switch (dzialanie) {
                    case "+" -> liczba1 + liczba2;
                    case "-" -> liczba1 - liczba2;
                    case "*" -> liczba1 * liczba2;
                    case "/" -> liczba1 / liczba2;
                    default -> throw new IllegalStateException("Unexpected value: " + dzialanie);
                };
                isWynik = true;
                ustalWynikWyswietlany();

                liczba1 = wynik;
                isLiczba1 = true;
                liczba2 = 0;
                isLiczba2 = false;
                wprowadzanaLiczba = 2;
                isWynik = false;
            }
        }
    }

    void ustalWynikWyswietlany() {
        if (!isWynik)
            if (wprowadzanaLiczba == 1)
                wynik = liczba1;
            else
                wynik = liczba2;

        if ((int) wynik == wynik) {
            wynikWyswietlany = String.format("%,.0f", wynik);
        } else
            wynikWyswietlany = String.format("%,.2f", wynik);
    }

    void resetujWartosci() {
        liczba1 = liczba2 = wynik = 0;
        dzialanie = null;
        isLiczba1 = isLiczba2 = isWynik = false;
        wprowadzanaLiczba = 1;
        wynikWyswietlany = String.valueOf(0);
    }
}