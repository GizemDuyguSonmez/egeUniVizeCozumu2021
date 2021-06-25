package vize;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Deneme {  // Deneme sinifi 

    public static void main(String[] args) {  // main methodu
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);  // input.txt dosyasi acilir.
            /*
            useLocale(Locale.US) kodu double veriler icin programin virgul yerine noktayi kabul etmesi icin yazilmistir!!!
            */
        } catch (FileNotFoundException e) {                          // Dosya bulunamazsa hata mesaji ekrana yazdirilir ve programdan cikilir.
            System.out.println("Dosya ile ilgili bir hata olustu....");
            System.exit(0);
        }

        GeometrikNesne[] nesnelerDizisi = new GeometrikNesne[50];  // 50 uzunluklu geometrik nesne tipinde "nesnelerDizisi" adli bir dizi olusturuldu.

        int i = 0;  // "nesnelerDizisi" isimli diziye dosyadan okdugumuz bilgilerle olusturdugumuz nesneleri eklemek icin gerekli olacak index degeridir.
        int daireIndexSon = 0, dikdortgenIndexSon = 0, silindirIndexSon = 0; // Dizide bulunan son dairenin indeksini "daireIndexSon" adli degsiken tutar. "DikdortgenIndexSon" ve "SilindirIndexSon" degiskenleri de ayni gorevi gorur. 
        int daireIndexIlk = 0, dikdortgenIndexIlk = 0, silindirIndexIlk = 0; // "daireIndexIlk" dizideki ilk daire nesnesinin indeksini tutar." dikdortgenIndexIlk" ve "silindirIndexIlk" degiskenleri de ayni gorevi gorur.

        double enBuyukHacim = 0;  // En buyuk hacim degeri tanimlanir ve sifir olarak atanir.
        double enKucukHacim = 0; // En kucuk hacim sifir olarak tanimlanir.( ancak ilk silindir nesnesinin hacim degeri while dongusu icinde enKucukHacim ddegerine atanacaktir. Daha sonraki silindir nesnelerinin hacim degerleri bu sekilde karsilasitirilacak ve en kucuk hacim bulunacaktir.)
        
        boolean silindirOlusturuldu = false, dikdortgenOlusturuldu = false, daireOlusturuldu = false; // Silindir, dikdortgen ve daire nesnelerinin dizideki ilk indexini bulmak icin gerekli olacak degiskenler.
        
        while (fileIn.hasNext()) {  // Dosyada eleman olduğu surece donecek.
            int ay, gun, yil;       // int tipinde ay, gun, yil degiskenleri Date sinifindan nesne olustururken parametre olarak kullanilacak.
            String nesneSinifiAdi = fileIn.next();   // String tipinde olusturulacak nesnenin sinifinin adini tutacak "nesneSinifiAdi" adli degisken.( Nesnenin sekli)
            String etiket = fileIn.next();           // Nesnenin adini string tipinde tutacak "etiket" adli degisken.

            if ("daire".equalsIgnoreCase(nesneSinifiAdi)) {  // Nesnenin sinifinin adi eger Daire ise o zaman if blogu calisir.

                double yaricap = fileIn.nextDouble();   // Daire nesneleri icin bir yaricap degeri okunur.
                fileIn.next();                          // "Date" yazisi dosyadan okunur.
                ay = fileIn.nextInt();                  // int tipinde ay degeri dosyadan okunur.
                gun = fileIn.nextInt();                 // int tipinde gun degeri dosyadan okunur.
                yil = fileIn.nextInt();                 // int tipinde yil degeri dosyadan okunur.
                Date tarih = new Date(ay, gun, yil);    // Date tipinde bir tarih nesnesi olusturulur. Ay, gun, yil parametreleri verilir.
                nesnelerDizisi[i] = new Daire(etiket, yaricap, tarih);  // Daire nesnesi okunan degerlerle olusturulur ve "nesnelerDizisi" adli dizinin o andaki indexine eklenir.

                if (daireOlusturuldu == false) { // Eger daha once bir daire nesnesi olusturulmamissa daireOlusturuldu false kalacak ve daireIndexIlk degiskenine ilk daire nesnesinin indexi atanmis olacaktir. Bu ilk daire nesnesinin indexi olur.
                    daireOlusturuldu = true;     // Daire nesnesi olusturuldugu icin daireOlusturuldu degiskenin degeri true degerine doner.
                    daireIndexIlk = i;    // İlk daire nesnesinin indexi
                }
                daireIndexSon = i;  // Son olusturulan daire nesnesinin indexini daireIndexSon adli degisken tutar.

            } else if ("dikdortgen".equalsIgnoreCase(nesneSinifiAdi)) {  //Nesnenin sinifinin adi eger Dikdortgen ise o zaman bu blok calisir.

                double en = fileIn.nextDouble();   // Double tipinde en degeri dosyadan okunur ve "en" degiskenine atanir..
                double boy = fileIn.nextDouble();  // Double tipinde boy  degeri dosyadan okunur ve "boy" degiskenine atanir.
                fileIn.next();           // "Date" yazisi dosyadan okunur.
                ay = fileIn.nextInt();   // int tipinde ay degeri dosyadan okunur.
                gun = fileIn.nextInt();  // int tipinde gun degeri dosyadan okunur.
                yil = fileIn.nextInt();  // int tipinde yil degeri dosyadan okunur.
                Date tarih = new Date(ay, gun, yil);  // Date tipinde bir tarih nesnesi olusturulur. Ay, gun, yil parametreleri verilir.
                nesnelerDizisi[i] = new Dikdortgen(etiket, en, boy, tarih);  // Dikdortgen nenesi olusturulur ve "nesnelerDizisi" adli diziye eklenir.
    
                if (dikdortgenOlusturuldu == false) {  // Eger daha once bir dikdortgen nesnesi olusturulmamissa dikdortgenOlusturuldu false kalacak ve dikdortgenIndexIlk degiskenine ilk dikdortgen nesnesinin indexi atanmis olacaktir. Bu ilk dikdortgen nesnesinin indexi olur.
                    dikdortgenOlusturuldu = true;
                    dikdortgenIndexIlk = i;     // İlk dikdortgen nesnesinin indexini tutar.
                }
                dikdortgenIndexSon = i;  // Son olusturulan dikdortgen nesnesinin indexini dikdortgenIndexSon adli degisken tutar.

            } else if ("silindir".equalsIgnoreCase(nesneSinifiAdi)) {  // Nesnenin sinifinin adi eger Silindir ise o zaman bu blok calisir. 

                double yaricap = fileIn.nextDouble();  //Double tipinde yaricap degeri dosyadan okunur ve "yaricap" adli degiskene atanir.
                double uzunluk = fileIn.nextDouble();  // Double tipinde uzunluk degeri dosyadan okunur ve "uzunluk" adli degiskene atanir.
                fileIn.next();                         // "Date" yazisi dosyadan okunur.
                ay = fileIn.nextInt();                 // int tipinde ay degeri dosyadan okunur.
                gun = fileIn.nextInt();                // int tipinde gun degeri dosyadan okunur.
                yil = fileIn.nextInt();                // int tipinde yil degeri dosyadan okunur.
                Date tarih = new Date(ay, gun, yil);   // Date tipinde bir tarih nesnesi olusturulur. Ay, gun, yil parametreleri verilir.
                Silindir silindirNesnesi = new Silindir(etiket, yaricap, uzunluk, tarih); // Silindir nesnesi olusturulur.
                nesnelerDizisi[i] = silindirNesnesi;   // Silindir nesnesi "nesnelerDizisi" adli diziye eklenir.
                
                if (silindirOlusturuldu == false) { // Eger daha once bir silindir nesnesi olusturulmamissa silindirOlusturuldu false kalacak ve silindirIndexIlk degiskenine ilk silindir nesnesinin indexi atanmis olacaktir. Bu ilk silindir nesnesinin indexi olur.
                    silindirOlusturuldu = true;
                    silindirIndexIlk = i;    // İlk silindir nesnesinin indexi atanir.
                    enKucukHacim = silindirNesnesi.hacimHesapla();   // Suanki nesne ilk silindir nesnesi ise en kucuk hacim degeri bu nesnenin hacim degerine atanir.
                    /* En kucuk hacim degeri sifir olarak atanmisti ancak bir silindir nesnesi olusturulmasi durumunda bu nesnenin sifirdan kucuk bir hacim degerine
                    sahip olamayacagi  goz onunde bulundurulursa  enKucukHacim degeri sifir olarak kalir ve hatali sonuc alinir. 
                    Bu nedenle ilk silindir nesnesinin hacim degeri en kucuk hacim olarak atanir ve diger silindir nesnelerinin hacim degerleri ile karsilastirilarak
                    silindir nesnelerinden en kucuk hacim degerine sahip olan bulunabilir.
                    */
                }
               silindirIndexSon = i;
            } else { // Nesnenin sinifinin adi Dikdortgen , Daire ya da Silindir degilse bu blok calisir.(Daire , Dikdortgen ve Silindir tipinde en az bir tane nesne oldugu varsayilir.)
                System.out.println("Geçersiz şekil girişi yapildi. Geometrik şekiller daire, silindir ya da dikdortgen olmalidir..."); // Mesaj ekrana yazdirilir. 
                System.exit(0);
            }
            i++; // While dongusu her calistiginda i degeri bir arttirilir. (Bu deger diziye indexle ulasmamizi saglar.)
        }

        fileIn.close();  // Dosya okuma islemi bitti bu nedenle dosya kapatilir.

        Daire sonDaire = (Daire) nesnelerDizisi[daireIndexSon];  // Downcasting yapilir. Geometrik nesne tipindeki dizide (nesnelerinDizisi) daireIndexSon indexindeki nesne daire tipine cevrilir.
         Daire daiCopy = new Daire(sonDaire); // Son daire nesnesi copy construtor ile kopyalanir.
        add(nesnelerDizisi, daiCopy); // Kopya olarak olusturulan nesne "add" adli method ile nesnelerinDizisi adli diziye eklenir.

        Dikdortgen sonDik = (Dikdortgen) nesnelerDizisi[dikdortgenIndexSon];  // Downcasting yapilir. Geometrik nesne tipindeki dizide dikdortgenIndexSon indexindeki nesne dikdortgen tipine cevrilir.
        Dikdortgen dikCopy = new Dikdortgen(sonDik);  // Son dikdortgen nesnesi copy constructor ile kopyalanir.
        add(nesnelerDizisi, dikCopy); //  Kopya olarak olusturulan nesne "add" adli method ile nesnelerinDizisi adli diziye eklenir.

        Silindir sonSil = (Silindir) nesnelerDizisi[silindirIndexSon]; // Downcasting yapilir. Geometrik nesne tipindeki dizide silindirIndexSon indexindeki nesne silindir tipine cevrilir.
        Silindir silCopy = new Silindir(sonSil);  // Son silindir nesnesi copy constructor ile kopyalanir.
        add(nesnelerDizisi, silCopy); //  Kopya olarak olusturulan nesne "add" adli method ile nesnelerinDizisi adli diziye eklenir.

        double alanToplamı = 0, cevreToplami = 0;  // Alan ve cevre ortalamalari icin lazim olacak degiskenler. Tüm nesnelerin alanlarinin ve cevrelerinin toplamini tutacak degiskenlerdir.
       
        /*
        En kucuk cevre, en buyuk cevre, en kucuk alan, en buyuk alan degiskenlerine nesnelerDizisi'nin ilk elemanın cevre ve alan degerleri atanir.
        Bu sekilde ilk nesneden baslanarak diger nesnelerin cevre ve alan degerleri karsilastirilarak sonuca ulasilir.
        */
        double enKucukCevre = nesnelerDizisi[0].cevreHesapla(), enBuyukCevre = nesnelerDizisi[0].cevreHesapla(),
                enKucukAlan = nesnelerDizisi[0].alanHesapla(), enBuyukAlan = nesnelerDizisi[0].alanHesapla();

        int j = 0;  // Dizinin elemanlarina ulasabilmek icin olusturulan degisken. ( index)
        for (GeometrikNesne geometrikNesne : nesnelerDizisi) { // For dongusu dizideki her bir nesneyi gezer.

            if (nesnelerDizisi[j] != null) {  // Nesne null degilse yani o index bos degilse if bloguna girilir.
                polymorphicYazdir(geometrikNesne);  // Nesneleri ekrana yazdiran method cagirilir.

                alanToplamı += geometrikNesne.alanHesapla();    // Alanlar toplami bulunur.
                cevreToplami += geometrikNesne.cevreHesapla();  // Cevreler toplami bulunur.

                if (geometrikNesne instanceof Silindir) {  // Nesne silindir mi kontrol edilir. ( hacim hesabı icin silindir nesneleri ayri olarak degerlendirilmelidir.)
                    Silindir silindirNesnesi = (Silindir) geometrikNesne;  // Eger nesne silidnir ise downcasting yapilir. Geometrik nesne tipindeki nesne Silindir tipine cevrilir.

                    if (silindirNesnesi.hacimHesapla() < enKucukHacim) {  // Bu nesnenin hacim degeri enKucukHacim degiskenindeki degerden kucuk mu kontrol edilir. 
                        /*
                        En kucuk hacim degeri dosya okunurken ilk silindir nesnesinin degeri olarak atanmisti. (85. satir)
                        Buraya gelindiginde enKucukHacim degiskeninin degerinin ilk silindir nesnesinin hacim degerine esit oldugu gorulur.
                        */
                        enKucukHacim = silindirNesnesi.hacimHesapla();    // Bu nesnein hacim degeri enKucukHacim degiskeninin degerinden kucukse enKucukHacim degiskenine bu nesnenin hacim degeri atanir.
                    } else if (silindirNesnesi.hacimHesapla() > enBuyukHacim) {  // Bu nesnenin hacim degeri en buyuk hacim degiskenindeki degerden buyuk mu kontrol edilir. 
                        enBuyukHacim = silindirNesnesi.hacimHesapla();           // Buyukse enBuyukkHacim degiskenine bu nesnenin hacim degeri atanir.
                    }
                }

                if (geometrikNesne.cevreHesapla() < enKucukCevre) {  // Nesnenin cevre degeri enKucukCevre degiskeninin degerinden kucuk mu kontrol edilir.
                    enKucukCevre = geometrikNesne.cevreHesapla();    // Kucukse enKucukCevre degiskenine bu deger atanir.
                } else if (geometrikNesne.cevreHesapla() > enBuyukCevre) { // Nesnenin cevre degeri enBuyukCevre degiskeninin degerinden buyuk mu kontrol edilir.
                    enBuyukCevre = geometrikNesne.cevreHesapla();          // Buyukse enBuyukCevre degiskenine bu deger atanir.
                }
                if (geometrikNesne.alanHesapla() < enKucukAlan) { // Nesnenin alan degeri enKucukAlan degiskeninin degerinden kucuk mu kontrol edilir.
                    enKucukAlan = geometrikNesne.alanHesapla();   // Kucukse enKucukAlan degerine bu deger atanir.
                } else if (geometrikNesne.alanHesapla() > enBuyukAlan) { // Nesnenin alan degeri enBuyukAlan degiskeninin degerinden buyuk mu kontrol edilir.
                    enBuyukAlan = geometrikNesne.alanHesapla();          // Buyukse enBuyukAlan degiskenine bu deger atanir.
                }
            }
            j++; // Index bir arttirilir.
        }

        System.out.println("***********************************************************"); 

        /*
        "karsilastir" adindaki static method compareTo adli methodu kullanarak karsilastirma yapar.
        */
        karsilastir(nesnelerDizisi[daireIndexIlk], daiCopy);  // Ilk ve son daire nesnesi karsilastirilir.(Son daire nesnesi = Copy constructor ile olusturulan daire nesnesi )
        karsilastir(nesnelerDizisi[daireIndexSon], daiCopy);  // Sondan bir onceki daire nesnesi ile copy constructor ile olusturulan daire nesnesi karsilasitirilir.

        karsilastir(nesnelerDizisi[dikdortgenIndexIlk], dikCopy); // Ilk ve son dikdortgen nesnesi karsilastirilir.(Son dikdortgen nesnesi = Copy constructor ile olusturulan dikdortgen nesnesi )
        karsilastir(nesnelerDizisi[dikdortgenIndexSon], dikCopy); // Sondan bir onceki dikdortgen nesnesi ile copy constructor ile olusturulan dikdortgen nesnesi karsilasitirilir.

        karsilastir(nesnelerDizisi[silindirIndexIlk], silCopy);  // Ilk ve son silindir nesnesi karsilastirilir.(Son silindir nesnesi = Copy constructor ile olusturulan silindir nesnesi )
        karsilastir(nesnelerDizisi[silindirIndexSon], silCopy);  // Sondan bir onceki silindir nesnesi ile copy constructor ile olusturulan silindir nesnesi karsilasitirilir.

        /*
         toplamDikdortgenSay degiskeni static olarak dikdortgen, toplamSilinidirSay degiskeni static olarak silindir ve toplamDaireSay degiskeni static olarak daire sinifinda olusturulmus degiskenlerdir.
        Constructorlarin her cagirimi bir nesne olusturulmasi olarak dusunulurse bu degiskenlerin degerlerinin constructorlar icerisinde bir arttirilmasi ile nesne sayilarina ulasilabilir.
        Yani ornegin daire sinifindan 3 daire nesnesi olusturulmasi demek 3 kez (parametreli, parametresiz ya da copy ) constructor cagirimi demektir.
        Bu sekilde olusturulan silindir, daire ve dikdortgen sayilari kolayca hesaplanabilir.
        */
        int toplamSilindirSay = Silindir.toplamSilindirSay;  // Toplam silindir nesnesi sayisi tutulur.
        int nesneSay = Dikdortgen.toplamDikdortgenSay + Silindir.toplamSilindirSay + Daire.toplamDaireSay;  // Toplam nesne sayisi tutulur.
        
        double[] istatistikler = new double[9];  // Nesnelerle ilgili son istatistikleri tutacak 9 uzunluklu "istatistikler" adli bir dizi olusturulur.
        /*
        Yukaridaki nesneleri yazdirmaya yarayan ve ayrica onlarin cevre, alan, hacim gibi degerlerini karsilastirmaya yarayan for dongusu bitiminde 
        en kucuk hcevre, alan, hacim ve en ebuyuk cevre, alan, hacim degerleri bulunmus olacaktir.
        Bu dongu ile cevre toplami, alan toplami ve hacim toplami degerleri de bulunur.
        */
        istatistikler[0] = cevreToplami / nesneSay;  // Cevre ortalamasi ( tum nesneler icin) 
        istatistikler[1] = alanToplamı / nesneSay;   // Alan ortlamasi (tum nesneler icin)
        istatistikler[2] = Silindir.hacimlerToplamiBul() / toplamSilindirSay; // Hacim ortlamasi (silindir nesneleri icin)
        istatistikler[3] = enKucukCevre; // En kucuk cevre degeri 
        istatistikler[4] = enBuyukCevre; // En buyuk cevre degeri
        istatistikler[5] = enKucukAlan;  // En kucuk alan degeri
        istatistikler[6] = enBuyukAlan;  // En buyuk alan degeri
        istatistikler[7] = enKucukHacim; // En kucuk hacim degeri
        istatistikler[8] = enBuyukHacim; // En buyuk alan degeri

        System.out.println("***********************************************************");
        System.out.println("Tüm nesnelerin bilgileri sonucu elde edilen istatistikler: "); // Ekrana yazdirilacak bir mesaj.

        String[] mesajlar = {"Cevre Ortalamasi", "Alan Ortalamasi", "Hacim Ortalamasi", "En Kucuk Cevre Degeri",
            "En Buyuk Cevre Degeri", "En Kucuk Alan Degeri", "En Buyuk Alan Degeri", "En Kucuk Hacim Degeri", "En Buyuk Hacim Degeri"}; 
        /*
        istatistikler listesini ekrana yazdirirken her elemaninin ne oldugunu aciiklayacak mesajlarin bulundugu liste.
        istatistikler listesi for ile gezilirken iki listeninde esit gelen indexleri ekrana yan yana System.out.printf ile yazdirilir.
        */

        for (int k = 0; k < istatistikler.length; k++) { // Son istatistikleri yazdirma islemi yapilir.
            String mesaj = mesajlar[k];                  // Mesajlar listesinin k. indexindeki elemani mesaj degiskenine atanir. 
            System.out.printf("%s: %,.2f %n", mesaj, istatistikler[k]); // Bu mesaj ve istatistikler listesinin k. elemanı ekrana yazdirilir.
        }
    }

    public static void polymorphicYazdir(GeometrikNesne object) {          // Bir sey dondurmeyen, static polymorphicYazdir adli method. Bir tane geometrik nesne tipinde parametre alir.
        if ((object instanceof Daire) || (object instanceof Dikdortgen)) { // Parametre olarak gelen geometrik nesne tipindeki object, daire ya da dikdortgen tipinde ise bu bloga girilir.
            System.out.print("Nesnenin Bilgileri:   " + object.toString());  // object nesnesinin toString methodu ile bilgileri ekrana yazdirilir.
            System.out.printf("%s: %,.2f %s: %,.2f %n", "Cevre", object.cevreHesapla(), "Alan", object.alanHesapla()); // Ek olarak cevre ve alan degerleri de ekrana yazdirilir.
        } else if (object instanceof Silindir) { // Parametre olarak gelen geometrik nesne tipindeki object, silindir tipinde ise bu bloga girilir.
            System.out.print("Nesnenin bilgileri:   " + object.toString());  // object nesnesinin toString methodu ile bilgileri ekrana yazdirilir.
            System.out.printf("%s: %,.2f %s: %,.2f %s: %,.2f %n", "Cevre", object.cevreHesapla(), "Alan", object.alanHesapla(), "Hacim", ((Silindir) object).hacimHesapla());
            /* Ek olarak cevre, alan ve hacim degerleri de ekrana yazdirilir.
            Silindir icin farkli olarak hacim degeri yazdirilacagi icin once geometrik nesne tipindeki object downcasting yapilmalidir. Bu sekilde hacimHesapla() adli methoda ulasilabilir.
            */
        }
    }

    public static void karsilastir(GeometrikNesne object1, GeometrikNesne object2) {  // Bir sey dondurmeyen, static karsilastir adli method. İki tane geometrik nesne tipinde parametre alir.
        
            System.out.printf("%s %s %s %s: %d %n", object1.getEtiket(), "ve", object2.getEtiket(), "etikete sahip nesneler basariyla karsilastirildi", object1.compareTo(object2));
        /*
        Yukarida object1 olarak gonderilen nesne uzerinde compareTo methodu cagirilir ve parametre olarak gonderilen object2 nesnesi ile karsilastirilir.
        compareTo methodunun dondurdugu deger ekrana yazdirilir.
        (compareTo int tipinde bir deger dondurur.)
        */
    }

    public static void add(GeometrikNesne[] dizi, GeometrikNesne object) { // Bir sey dondurmeyen, static add methodu. Geometrik nesne tipinde bir dizi ve geometrik nesne tipinde bir nesneyi parametre olarak alir.
        /*
        Bu methodun amaci;
        Dosyada olmayip sonradan olusturulan nesneleri diziye eklemek icin her seferinde diziyi gezip kod tekrarini onlemek amaciyla olusturulmus bir methodtur.
        */
        for (int i = 0; i < dizi.length; i++) { //Gonderilen dizinin uzunlugu kadar donecek bir for dongusu.
      
            //Dizi gezilir ilk bos index bulunmaya calisilir.
            if (dizi[i] == null) { // Eger dizinin i. indexindeki elemanı bos ise if blogu calisir.
                dizi[i] = object;  // Dizinin bu indexine nesne eklenmis olur.
                break;             // Ve daha sonra islem tamamlandigi icin break komutu ile for dongusunden cikilir.
            }
        }
    }
}
