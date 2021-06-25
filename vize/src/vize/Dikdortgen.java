package vize;

public class Dikdortgen extends GeometrikNesne {  // Dikdorgen sinifi GeometrikNesne sinifini extend eder. (Inheritance = kalitim ) 

    private double en;  // Dikdortgenin en degerini tutacak degisken.
    private double boy;  // Dikdortgenin boy degerini tutacak degisken.
    public static int toplamDikdortgenSay = 0; // Olusturulan toplam dikdortgen nesnesi sayisini tutar.
    /*
    Dikdortgen nesnelerinin sayisinin nesne olusturulmadan ogrenilebilmesi icin bu degere ulasimin class uzerinden kolayca yapilabilmesi dusunulmustur.
    Bu nedenle toplamDikdortgenSay adli static bir degisken olusturulmustur.
    */
   
    // Parametresiz Constructor 
    public Dikdortgen() {
        super();   // Kalitimla alinan sinifin parametresiz constructor' i cagirilir. (GeometrikNesne)
        en = 1.0;  // Parametre verilmedigi durumda en degerine 1 degeri atanir.
        boy = 1.0; // Parametre verilmedigi durumda boy degerine 1 degeri atanir.
        toplamDikdortgenSay++; // Bir dikdorgen nesnesi olsuturuldugu icin toplamDikdortgenSay bir arttirilir.
    }

    // Parametreli Constructor
    public Dikdortgen(String etiket, double en, double boy, Date theDate) {
        super(etiket, theDate);  // Kalitimla alinan sinifin parametreli constructor'i cagirilir. ( GeometrikNesne)
        setBoy(boy);             // boy degeri set edilir. ( Kontrol edilir.)
        setEn(en);               // en degeri set edilir. (Kontrol edilir.)
        toplamDikdortgenSay++;   // Bir dikdorgen nesnesi olsuturuldugu icin toplamDikdortgenSay bir arttirilir.
    }

    // Copy Constructor
    public Dikdortgen(Dikdortgen originalObject) {
        super(originalObject);         // Kalitimla alinan sinifin copy constructor' i cagirilir. (Geometrik Nesne)
        en = originalObject.getEn();   // Parametre olarak gelen nesnenin en değeri, en degiskenine atanir.
        boy = originalObject.getBoy(); // Parametre olarak gelen nesnenin boy degeri, boy egiskenine atanir.
        toplamDikdortgenSay++;         // Bir dikdorgen nesnesi olsuturuldugu icin toplamDikdortgenSay bir arttirilir.

    }

    @Override
    public double cevreHesapla() {     // Abstract olan cevre hesapla methodunu dikdortgen sinifi kendine gore yazar. (Override)
        return 2 * getEn() + 2 * getBoy(); 
    }

    @Override
    public double alanHesapla() {     // Abstract olan alan hesapla methodunu dikdortgen sinifi kendine gore yazar. (Override)
        return getBoy() * getEn();
    }

    @Override
    public int compareTo(Object object) {  // Abstract olan compare to methodunu dikdortgen sinifi kendien gore yazar. (Override)
        Dikdortgen otherObject = (Dikdortgen) object;   // Downcast yapildi.
        if (alanHesapla() > otherObject.alanHesapla()) { //Eger methodun uzerinde cagirildi dıkdortgen nesnesinin alani parametre olarak verilen dıkdortgen nesnesinin alanindan buyukse 1 dondurulur.
            return 1;
        } else if (alanHesapla() < otherObject.alanHesapla()) { //Eger methodun uzerinde cagirildi dıkdortgen nesnesinin alani parametre olarak verilen dıkdortgen nesnesinin alanindan kucukse -1 dondurulur.
            return -1;
        } else {  // Esitlik durumda 0 dondurulur.
            return 0;
        }
    }

    @Override
    public String toString() {  // Dikdortgen sinifi toString methodunu override eder.
        return super.toString() + String.format(" %s: %.2f %s: %.2f ",
                "En", getEn(), "Boy", getBoy());
        /*
        super.toString ile ust sinif olan GeometrikNesne sinifinin toString methodu yazdirilir.
        Ek olarak en ve boy degerleri de yazdirilir.
        */
    }
    
    public static int dikdortgenNesneToplami(){ // Toplam dikdortgen nesnesi sayisini dondurmeyi saglayan static bir method.
       return toplamDikdortgenSay;      
    }
    
    // getter ve setter methotlar olusturuldu.
    public double getEn() {
        return en;  // En degeri dondurulur.
    }

    public void setEn(double en) {  // Hata kontrolleri yapilir.
        if (en < 0) {               // en değerinin negatif verilmesi engellenir.
            System.out.println("Bir sorun olustu! En değeri sıfırdan küçük olamaz...");
            System.exit(0);
        }
        this.en = en; // Eger bir sorun yok ise en degiskenine parametre olarak gelen en degeri atanir.
    }

    public double getBoy() {
        return boy;   // Boy degeri dondurulur.
    }

    public void setBoy(double boy) {  // Hata kontrolleri yapilir.
        if (boy < 0) {                // boy degerinin negatif verilmesi engellenir.
            System.out.println("Bir sorun olustu! Boy değeri sıfırdan küçük olamaz...");
            System.exit(0);
        }
        this.boy = boy;  // Eger bir sorun yok ise boy degiskenine parametre olarak gelen boy degeri atanir.
    }
}
