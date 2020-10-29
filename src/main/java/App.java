import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class App {

    App() {
        JFrame ramka = new JFrame("Kalkulator");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setLayout(null);
        ramka.setResizable(false);
        ramka.setLocation(800, 300);
        ramka.setSize(400, 420);
        ramka.setVisible(true);

        Calculate calc = new Calculate();

        int marginesX = 20;
        int marginesY = 20;
        int odstep = 8;
        Dimension rozmiarBtn = new Dimension(80, 50);
        Dimension rozmiarLbl = new Dimension(4 * rozmiarBtn.width + 3 * odstep, 50);

        String[][] oznaczeniaKlawiszy = {{"%", "CE", "C", "/"},
                {"7", "8", "9", "*"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"+/-", "0", ",", "="}};

        // utworzenie etykiety z wynikiem
        final JLabel lblWynik = new JLabel("0");
        lblWynik.setSize(rozmiarLbl); // romiar etykiety
        lblWynik.setLocation(marginesX, marginesY); // położenie etykiety
        lblWynik.setHorizontalAlignment(SwingConstants.RIGHT); // wyrównanie tekstu etykiety
        lblWynik.setOpaque(true); // ustawienie komponentu na nieprzezroczysty
        lblWynik.setBackground(Color.lightGray);
        Font bigFont = new Font(Font.DIALOG, Font.PLAIN, 40);
        Font smallFont = new Font(Font.DIALOG, Font.PLAIN, 28);
        lblWynik.setFont(bigFont);
        ramka.add(lblWynik);

        // utworzenie przycisków i włożenie ich do mapy oraz na ramkę
        LinkedHashMap<String, JButton> przyciski = new LinkedHashMap<>();
        JButton b;
        int[] wiersz = new int[oznaczeniaKlawiszy.length];
        int[] kolumna = new int[oznaczeniaKlawiszy[0].length];

        for (int i = 0; i < oznaczeniaKlawiszy.length; i++) {
            wiersz[i] = marginesY + rozmiarLbl.height + odstep + i * (rozmiarBtn.height + odstep);
            for (int j = 0; j < oznaczeniaKlawiszy[i].length; j++) {
                kolumna[j] = marginesX + j * (rozmiarBtn.width + odstep);
                b = new JButton(oznaczeniaKlawiszy[i][j]);
                b.setSize(rozmiarBtn);
                b.setLocation(kolumna[j], wiersz[i]);
                b.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
                b.setFocusable(false);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String wyswietlaczTekst = calc.updateLabel(e.getActionCommand());
                        if(wyswietlaczTekst.startsWith("b"))
                            lblWynik.setFont(smallFont);
                        else
                            lblWynik.setFont(bigFont);
                        lblWynik.setText(wyswietlaczTekst);
                    }
                });
                przyciski.put(oznaczeniaKlawiszy[i][j], b);
                ramka.add(b);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}