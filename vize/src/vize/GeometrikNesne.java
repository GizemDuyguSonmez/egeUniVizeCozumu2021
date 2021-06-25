package vize;

public abstract class GeometrikNesne implements Comparable {  // Abstract olan GeometrikNesne sinifi. Comparable interface'ini implement eder.

    private String etiket;                    // nesnelere verilen isimleri tutmak icin olusturuldu.
    private Date seklinOlusturulmaTarihi;     // Sekin olusturulma tarihini tutmak icin olusturuldu.
    public static final double PI = Math.PI;  // Pi sayısı sabit olarak olusturuldu.

    // abstract methodlar
    public abstract double alanHesapla();

    public abstract double cevreHesapla();

    @Override
    public abstract int compareTo(Object o);
    /*Bu sinif comparable interface'si icerisindeki compareTo methodunu override edip icini doldurmadigi icin compareTo abstract bir method olarak yazilir.
    Bu sinifin tum alt siniflari bu methodu override etmek zorundadir.
     */

    // parametresiz constructor 
    public GeometrikNesne() {
        etiket = "no-name yet";                    // Parametresiz contructor'in olusturulmasi durumunda etiket adi "no-name yet" olarak atanir.
        this.seklinOlusturulmaTarihi = new Date(); // Date tipinde bir nesne olusturulur ve seklinOlusturmaTarihi degiskenine atanir.
    }

    // parametreli constructor
    public GeometrikNesne(String etiket, Date seklinOlusturulmaTarihi) {
        setEtiket(etiket);  // set methodu ile atama yapilir.
        this.seklinOlusturulmaTarihi = new Date(seklinOlusturulmaTarihi); // privacy leak engellenir. (this.seklinOlusturulmaTarihi = seklinOlusturulmaTarihi demek bu nesneleri birbirine baglayacagi icin yeni bir date nesnesi olusturup atamak en dogrusu)
    }

    // copy constructor
    public GeometrikNesne(GeometrikNesne originalObject) {
        etiket = originalObject.getEtiket();
        this.seklinOlusturulmaTarihi = new Date(originalObject.getSeklinOlusturulmaTarihi()); // privacy leak  engellenir.
        /*
        Parametre olarak gelen nesnenin get methodu ile seklinOlusturulmaTarihi degiskeninin degeri alinir ve privacy leak engellemek amaci ile 
        date tipinde yeni bir nesne olusturup degiskene atanir.
        */
    }

    @Override
    public String toString() {  // toString methodu override edilir. Alt siniflar bu sinifin toString methodunu super.toString ile kullanabilirler.
        return ("Etiket: " + etiket + " Seklin Olusturulma Tarihi: " + getSeklinOlusturulmaTarihi().toString()); 
    }

    // getter ve setter methodlar olusturuldu.
    public String getEtiket() {
        return etiket;  // etiket degeri dondurulur.
    }

    public void setEtiket(String etiket) {  // Hata kontrolleri yapilir.
        if (etiket == null) {  // Nesnelerin etiketinin null olmasi engelleniyor ve bir hata mesaji yazdiriliyor. Her nesne bir etikete sahip olmali.
            System.out.println("Bir sorun olustu! Seklin adı boş birakilamaz...");
            System.exit(0);
        }
        this.etiket = etiket;
    }

    public Date getSeklinOlusturulmaTarihi() {
        return new Date(seklinOlusturulmaTarihi);   // privacy leak engellenir. 
    }

    public void setSeklinOlusturulmaTarihi(Date seklinOlusturulmaTarihi) {
        if (seklinOlusturulmaTarihi == null) {  // Nesnelerin olusturulma tarihinin null olması engelleniyor ve bir hata mesaji yazdiriliyor. Her nesnenin olusturulma tarihi olmali.
            System.out.println("Bir sorun olustu! Seklin olusturulma tarihi bos birakilamaz ...");
            System.exit(0);
        } else {
            seklinOlusturulmaTarihi = new Date(seklinOlusturulmaTarihi);  // privacy leak engellenir.
        }
    }
}
