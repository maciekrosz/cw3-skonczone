public class Zamowienie {
    private int id;
    private Klient klient;
    private Produkt[] produkty;
    private int[] ilosci;
    private String dataZamowienia;
    private String status;

    public Zamowienie(int id, Klient klient, Produkt[] produkty, int[] ilosci, String dataZamowienia, String status) {
        this.id = id;
        this.klient = klient;
        this.produkty = produkty;
        this.ilosci = ilosci;
        this.dataZamowienia = dataZamowienia;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Produkt[] getProdukty() {
        return produkty;
    }

    public void setProdukty(Produkt[] produkty) {
        this.produkty = produkty;
    }

    public int[] getIlosci() {
        return ilosci;
    }

    public void setIlosci(int[] ilosci) {
        this.ilosci = ilosci;
    }

    public String getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(String dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public double obliczWartoscZamowienia() {
        double wartosc = 0.0;


        for (int i = 0; i < produkty.length; i++) {
            wartosc += produkty[i].getCena() * ilosci[i];
        }

        return wartosc;
    }


    public void zastosujZnizke() {
        if (klient.isCzyStaly()) {
            double wartosc = obliczWartoscZamowienia();
            double znizka = wartosc * 0.10; // 10% zniżki
            System.out.println("Zastosowano zniżkę 10% dla stałego klienta.");
            System.out.println("Zniżka: " + znizka + " zł");
            System.out.println("Nowa wartość zamówienia: " + (wartosc - znizka) + " zł");
        } else {
            System.out.println("Klient nie jest stały, brak zniżki.");
        }
    }


    public void wyswietlSzczegoly() {
        System.out.println("Szczegóły zamówienia:");
        System.out.println("Identyfikator zamówienia: " + id);
        System.out.println("Klient: " + klient.getImie() + " " + klient.getNazwisko());
        System.out.println("Data zamówienia: " + dataZamowienia);
        System.out.println("Status zamówienia: " + status);
        System.out.println("Produkty w zamówieniu:");

        for (int i = 0; i < produkty.length; i++) {
            System.out.println("- " + produkty[i].getNazwa() + " (Ilość: " + ilosci[i] + ", Cena: " + produkty[i].getCena() + " zł)");
        }


        double wartosc = obliczWartoscZamowienia();
        System.out.println("Łączna wartość zamówienia (bez zniżki): " + wartosc + " zł");

        zastosujZnizke();
    }

    public static void main(String[] args) {

        Klient klient = new Klient();
        Produkt laptop = new Produkt();
        Produkt mysz = new Produkt();

        Produkt[] produkty = {laptop, mysz};
        int[] ilosci = {1, 2};


        Zamowienie zamowienie = new Zamowienie(1, klient, produkty, ilosci, "2025-03-20", "Nowe");


        zamowienie.wyswietlSzczegoly();
    }
}
