package vize;

public class Daire extends GeometrikNesne { // Daire sinifi GeometrikNesne sinifini extend eder. (Inheritance = kalitim)

    private double yaricap;  // Dairenin yaricapini tuatacak degisken.
    public static int toplamDaireSay = 0;  // Bir daire nesnesi olsuturuldugu icin toplamDaireSay bir arttirilir.
      /*
    Daire nesnelerinin sayisinin nesne uretilmeden ogrenilebilmesi icin bu degere ulasimin class uzerinden kolayca yapilabilmesi dusunulmustur.
    Bu nedenle toplamDaireSay adli static bir degisken olusturulmustur.
    */
   
    // Parametresiz Construtor
    public Daire() {
        super();           // Kalitimla alinan sinifin parametresiz constructor'i cagirilir.
        yaricap = 1.0;     // Parametre verilmedigi durumda yaricap degerine otomatik olarak 1 atanir.
        toplamDaireSay++;  // Bir daire nesnesi olusturuldugu icin toplamDaireSay bir arttirilir.
    }

    // Parametreli Constructor
    public Daire(String etiket, double yaricap, Date theDate) {
        super(etiket, theDate);  // Kalitimla alinan sinifin parametreli constructor'i cagirilir.
        setYaricap(yaricap);     // yaricap değeri kontrol edilir ve ona gore atama yapilir.
        toplamDaireSay++;        // Bir daire nesnesi olusturuldugu icin toplamDaireSay bir arttirilir.
    }

    // Copy Constructor
    public Daire(Daire originalObject) {
        super(originalObject);   // Kalitimla alinan sinifin copy constructor'ı cagirilir.
        yaricap = originalObject.getYaricap(); // Parametre olarak gelen nesnenin yaricapi, yaricap degiskenine atanir.
        toplamDaireSay++;        // Bir daire nesnesi olusturuldugu icin toplamDaireSay bir arttirilir.
    }

    @Override
    public double cevreHesapla() {  // Abstract olan cevre hesapla methodunu daire sinifi kendine gore yazar. (Override)
        return 2 * PI * getYaricap();  
    }

    @Override
    public double alanHesapla() {  // Abstract olan alan hesapla methodunu daire sinifi kendine gore yazar. (Override)
        return PI * getYaricap() * getYaricap();
    }

    @Override
    public int compareTo(Object object) {     // Abstract olan compare to methodunu daire sinifi kendine gore yazar. (Override) 
        Daire otherObject = (Daire) object;   // downcasting yapilir.
        if (yaricap > otherObject.getYaricap()) { // Eger methodun uzerinde cagirildi daire nesnesinin yaricapi parametre olarak verilen daire nesnesinin yaricapindan buyukse 1 dondurulur.
            return 1;
        } else if (yaricap < otherObject.getYaricap()) { //  Eger methodun uzerinde cagirildi daire nesnesinin yaricapi parametre olarak verilen daire nesnesinin yaricapindan kucukse -1 dondurulur.
            return -1;
        } else { // esitlik durumunda 0 dondurulur.
            return 0;
        }
    }

    @Override
    public String toString() {   // Daire sinifi toString methodunu override eder.
        return super.toString() + String.format(" %s: %.2f ",
                "Yaricap", getYaricap());
        /*
        super.toString ile ust sinif olan GeometrikNesne sinifinin toString methodu cagirilir.
        */
    }

    public static int daireNesneToplami(){  // Toplam daire nesnesi sayisini dondurmeyi saglayan static bir method.
        return toplamDaireSay;
    }

    // getter ve setter methotlar olusturuldu.
    public double getYaricap() {
        return yaricap;  // yaricap degeri dondurulur.
    }

    public void setYaricap(double yaricap) {  // Hata kontrolleri  yapilir. Yaricap degerinin negatif girilmesi engellenir.
        if (yaricap < 0.0) {
            System.out.println("Bir sorun olustu! Yaricap degeri sıfırdan küçük bir deger olamaz...");
            System.exit(0);
        }
        this.yaricap = yaricap;  // Eger bir sorun yok ise yaricap degiskenine parametre olarak gelen yaricap degeri atanir.
    }
}
