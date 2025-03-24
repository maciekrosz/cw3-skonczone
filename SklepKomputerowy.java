public class SklepKomputerowy {
    public Zamowienie[] getZamowienia;
    private Produkt[] produkty;
    private Klient[] klienci;
    public Zamowienie[] zamowienia;
    private int liczbaProduktow;
    private int liczbaKlientow;
    private int liczbaZamowien;


    public SklepKomputerowy() {
        produkty = new Produkt[100];
        klienci = new Klient[100];
        zamowienia = new Zamowienie[100];
        liczbaProduktow = 0;
        liczbaKlientow = 0;
        liczbaZamowien = 0;
    }


    public void dodajProdukt(Produkt produkt) {
        if (liczbaProduktow < produkty.length) {
            produkty[liczbaProduktow++] = produkt;
            System.out.println("Dodano produkt: " + produkt.getNazwa());
        } else {
            System.out.println("Brak miejsca na dodatkowe produkty.");
        }
    }


    public void dodajKlienta(Klient klient) {
        if (liczbaKlientow < klienci.length) {
            klienci[liczbaKlientow++] = klient;
            System.out.println("Dodano klienta: " + klient.getImie() + " " + klient.getNazwisko());
        } else {
            System.out.println("Brak miejsca na dodatkowych klientów.");
        }
    }


    public Zamowienie utworzZamowienie(Klient klient, Produkt[] produkty, int[] ilosci) {
        if (liczbaZamowien < zamowienia.length) {
            Zamowienie noweZamowienie = new Zamowienie(liczbaZamowien + 1, klient, produkty, ilosci, "2025-03-20", "Nowe");
            zamowienia[liczbaZamowien++] = noweZamowienie;
            System.out.println("Utworzono zamówienie o ID: " + noweZamowienie.getId());
        } else {
            System.out.println("Brak miejsca na dodatkowe zamówienia.");
        }
        return null;
    }
    public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
        Produkt[] produkty = zamowienie.getProdukty();
        int[] ilosci = zamowienie.getIlosci();

        for (int i = 0; i < produkty.length; i++) {
            for (int j = 0; j < liczbaProduktow; j++) {
                if (produkty[i].getId() == produkty[j].getId()) {
                    produkty[j].setIloscWMagazynie(produkty[j].getIloscWMagazynie() - ilosci[i]);
                    System.out.println("Zaktualizowano stan magazynowy produktu: " + produkty[i].getNazwa());
                }
            }
        }
    }
    public void zmienStatusZamowienia(int idZamowienia, String nowyStatus) {
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getId() == idZamowienia) {
                zamowienia[i].setStatus(nowyStatus);
                System.out.println("Zmieniono status zamówienia ID: " + idZamowienia + " na: " + nowyStatus);
                return;
            }
        }
        System.out.println("Nie znaleziono zamówienia o ID: " + idZamowienia);
    }
    public void wyswietlProduktyWKategorii(String kategoria) {
        System.out.println("Produkty w kategorii: " + kategoria);
        for (int i = 0; i < liczbaProduktow; i++) {
            if (produkty[i].getKategoria().equalsIgnoreCase(kategoria)) {
                System.out.println("- " + produkty[i].getNazwa() + " (Cena: " + produkty[i].getCena() + " zł, Ilość: " + produkty[i].getIloscWMagazynie() + ")");
            }
        }
    }
    public void wyswietlZamowieniaKlienta(int idKlienta) {
        System.out.println("Zamówienia klienta o ID: " + idKlienta);
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getKlient().getId() == idKlienta) {
                System.out.println("Zamówienie ID: " + zamowienia[i].getId() + ", Data: " + zamowienia[i].getDataZamowienia() + ", Status: " + zamowienia[i].getStatus());
            }
        }
    }
    public static void main(String[] args) {

        SklepKomputerowy sklep = new SklepKomputerowy();
        Klient klient1 = new Klient();
        Klient klient2 = new Klient();
        Produkt laptop = new Produkt();
        Produkt mysz = new Produkt();
        sklep.dodajKlienta(klient1);
        sklep.dodajKlienta(klient2);
        sklep.dodajProdukt(laptop);
        sklep.dodajProdukt(mysz);
        Produkt[] produkty = {laptop, mysz};
        int[] ilosci = {1, 2};
        sklep.utworzZamowienie(klient1, produkty, ilosci);
        Zamowienie zamowienie = sklep.zamowienia[0]; // Pierwsze zamówienie
        sklep.aktualizujStanMagazynowy(zamowienie);
        sklep.zmienStatusZamowienia(1, "W realizacji");
        sklep.wyswietlProduktyWKategorii("Laptop");
        sklep.wyswietlZamowieniaKlienta(1);
    }
}
