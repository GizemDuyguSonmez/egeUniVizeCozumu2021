package vize;

public class Silindir extends GeometrikNesne {  // Silindir sinifi GeometrikNesne sinifini extend eder. (Inheritance = Kalitim)

    private double yaricap; // Nesnenin yaricap degerini tutar.
    private double uzunluk; // Nesnenin uzunluk degerini tutar.
    public static int toplamSilindirSay = 0; // Olusturulan toplam silindir nesnesi sayisini tutar.
    public static double hacimToplami = 0;   // Olusturulan silindir nesnelerinin hacimleri toplamini tutar.
    /*
    Silindir nesnelerinin hacimleri toplami kullanilarak hacim ortalamasi yazdirilacagi icin static, toplamSilindirSay adli bir degisken tanimlandi.
    Her yeni silindir nesnesi olusturuldugunda nesnenin hacim degeri bu degisken uzerine eklenir.
    Ayni sekilde silindir nesnelerinin sayisi hacim ortalamasi icin gerekli olacagi icin bu deger de static bir degisken icinde tutulur.
    */
   
    // Parametresiz Constructor 
    public Silindir() {
        super();        // Kalitimla alinan sinifin parametresiz constructor' i cagirilir. (GeometrikNesne)
        yaricap = 1.0;  //  Parametre verilmedigi durumda yaricap degerine 1 degeri atanir.
        uzunluk = 1.0;  //  Parametre verilmedigi durumda uzunluk degerine 1 degeri atanir.
        toplamSilindirSay++;  // Bir silindir nesnesi olsuturuldugu icin toplamSilindirSay bir arttirilir.
        hacimToplami += hacimHesapla();  // Her silindir nesnesinin hacim degeri silindir nesnelerinin hacimleri toplamini tutan hacimToplami degiskenine eklenir.
    }

    // Parametreli Constructor     
    public Silindir(String etiket, double yaricap, double uzunluk, Date theDate) {
        super(etiket, theDate);  //  Kalitimla alinan sinifin parametreli constructor' i cagirilir. (GeometrikNesne)
        setUzunluk(uzunluk);     // Uzunluk degeri kontrol edilir ve ona gore atama yapilir.
        setYaricap(yaricap);     // Yaricap degeri kontrol edilir ve ona gore atama yapilir.
        toplamSilindirSay++;     // Bir silindir nesnesi olsuturuldugu icin toplamSilindirSay bir arttirilir.
        hacimToplami += hacimHesapla();  // Her silindir nesnesinin hacim degeri silindir nesnelerinin hacimleri toplamini tutan hacimToplami degiskenine eklenir.
    }

    // Copy Constructor
    public Silindir(Silindir originalObject) {
        super(originalObject);                   //  Kalitimla alinan sinifin copy constructor' i cagirilir. (GeometrikNesne)
        yaricap = originalObject.getYaricap();   // Parametre olarak gelen nesnenin yaricap degeri yaricap degiskenine atanir.
        uzunluk = originalObject.getUzunluk();   // Parametre olarka gelen nesnenin uzunluk degeri uzunluk degiskenine atanir.
        toplamSilindirSay++;  // Bir silindir nesnesi olsuturuldugu icin toplamSilindirSay bir arttirilir.
        hacimToplami += hacimHesapla();  // Her silindir nesnesinin hacim degeri silindir nesnelerinin hacimleri toplamini tutan hacimToplami degiskenine eklenir.
    }

    @Override
    public double cevreHesapla() {  // Abstrat olan cevre hesapla methodunu silindir sinifi kendine gore yazar. (Override)
        return (2 * 2 * getYaricap() * PI) + (2 * getUzunluk());
    }

    @Override
    public double alanHesapla() {  // Abstrat olan alan hesapla methodunu silindir sinifi kendine gore yazar. (Override)
        return (2 * getYaricap() * PI * (getYaricap() + getUzunluk()));
    }

    // Silindir sinifina özel bir method.
    public double hacimHesapla() { // Silindir nesnelerinin hacimlerini hesaplamayi saglar.
        return PI * getYaricap() * getYaricap() * getUzunluk();
    }

    @Override
    public String toString() {  // Silindir sinifi toSting methodunu override eder.
        return super.toString() + String.format(" %s: %.2f %s: %.2f ",
                "Yaricap", getYaricap(), "Uzunluk", getUzunluk());
        /*
        super.toString ile ust sinif olan geometrikNesne sinifinin toString methodu cagirilir.
        */
    }

    @Override
    public int compareTo(Object object) {          // Abstract olan compare to methodunu silindir sinifi kendine gore yazar. (Override) 
        Silindir otherObject = (Silindir) object;  // Downcast yapilir.
        if (hacimHesapla() > otherObject.hacimHesapla()) { // Eger methodun uzerinde cagirildi silindir nesnesinin hacmi parametre olarak verilen silindir nesnesinin hacminden buyukse 1 dondurulur.
            return 1;
        } else if (hacimHesapla() < otherObject.hacimHesapla()) { // Eger methodun uzerinde cagirildi silindir nesnesinin hacmi parametre olarak verilen silindir nesnesinin yaricapindan küucukse -1 dondurulur.
            return -1;
        } else {  // esitlik durumdan 0 dondurulur.
            return 0;
        }
    }
    
    public static int silindirNesneToplami(){ // Toplam silindir nesnesi sayisini dondurmeyi saglayan static bir method.
        return toplamSilindirSay;
    }  
    
    public static double hacimlerToplamiBul(){ // Silindir nesnelerinin haacimlerinin toplamini dondurmeyi saglayan static bir method.
        return hacimToplami;
    }
 
    // getter ve setter methodlar olusturuldu.
    public double getYaricap() { // Yaricap degerini dondurulur.
        return yaricap;
    }

    public void setYaricap(double yaricap) {  // Hata kontrollleri yapilir.
        if (yaricap < 0) {                    // Yaricap degerinin negatif olmasi engellenir.
            System.out.println("Bir sorun olustu! Yaricap degeri sıfırdan küçük bir deger olamaz...");
            System.exit(0);
        }
        this.yaricap = yaricap; // Eger bir sorun yok ise yaricap degiskenine parametre olarak gelen yaricap degeri atanir.
    }

    public double getUzunluk() {  // Uzunluk degeri dondururulur.
        return uzunluk;
    }

        public void setUzunluk(double uzunluk) {  // Hata kontrolleri yapilir.
        if (uzunluk < 0) {                        // Uzunluk degerinin negatif olmasi engellenir.
            System.out.println("Bir sorun olustu! Uzunluk degeri sıfırdan küçük bir deger olamaz..");
            System.exit(0);
        }
        this.uzunluk = uzunluk; // Eger bir sorun yok ise uzunluk degiskenine parametre olarak gelen uzunluk degeri atanir.
    }
}
