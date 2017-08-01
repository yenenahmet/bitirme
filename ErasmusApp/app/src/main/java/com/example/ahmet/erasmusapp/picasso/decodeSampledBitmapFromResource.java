package com.example.ahmet.erasmusapp.picasso;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Yenen on 30.4.2017.
 */
public class decodeSampledBitmapFromResource {

    public static int calculateInSampleSize(BitmapFactory.Options _Options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        // Bitmap kaynağının genişliği ya da yüksekliği, istenilen genişlik ya
        // da yükseklikten büyük olduğu sürece inSampleSize değeri
        // hesaplanıyor...
        if (_Options.outWidth > reqWidth || _Options.outHeight > reqHeight) {
            inSampleSize = 2;
            int calculatedWidth = _Options.outWidth / inSampleSize;
            int calculatedHeight = _Options.outHeight / inSampleSize;
            // inSampleSize değeri (2'nin kuvveti olarak) hesaplanır.
            // Hesaplanan çözünürlük, (reqWidth + (reqWidth / 2)) değerinden
            // büyük olduğu sürece 2'nin kuvveti artırılır.
            while (calculatedWidth > (reqWidth + (reqWidth / 2)) && calculatedHeight > (reqHeight + (reqHeight / 2))) {
                inSampleSize *= 2;
                calculatedWidth = _Options.outWidth / (inSampleSize / 2);
                calculatedHeight = _Options.outHeight / (inSampleSize / 2);
            }
            int estimatedWidth = _Options.outWidth / inSampleSize;
            int estimatedHeight = _Options.outHeight / inSampleSize;
            // Güncel inSampleSize değeri ile oluşturulacak olan yeni çözünürlük
            // değerleri halen yüksek ise inSampleSize değeri 2 ile çarpılır.
            // Bunun sebebi; Bitmap oluşturucusunun, yeni oluşturulacağı Bitmap
            // için orijinal Bitmap çözünürlüğünü, inSampleSize değerinin
            // yarısına bölüyor olmasıdır.
            if (reqWidth < (estimatedWidth + (estimatedWidth / 2)) && reqHeight < (estimatedHeight + (estimatedHeight / 2)))
                inSampleSize *= 2;
        }
        return inSampleSize;
    }
   public  static Bitmap decodeSampledBitmapFrom(Resources _Resources, int resID, int reqWidth, int reqHeight) {
        final BitmapFactory.Options _Options = new BitmapFactory.Options();
        // Sadece kaynağa ait temel bilgilerin alınması sağlanıyor...
        _Options.inJustDecodeBounds = true;
        // inJustDecodeBounds değeri true olarak ayarlandığı için aşağıdaki
        // Bitmap için kaynak çözücüsü sadece verilen kaynağın bilgilerini
        // oluşturur. Geriye Bitmap döndermez.
        BitmapFactory.decodeResource(_Resources, resID, _Options);
        // inSampleSize değeri hesaplanıyor...
        _Options.inSampleSize = calculateInSampleSize(_Options, reqWidth, reqHeight);
        // inSampleSize değeri artık bulunduğu için çözülecek olan kaynaktan
        // Bitmap nesnesi oluşturulması da sağlanıyor...
        _Options.inJustDecodeBounds = false;
        // Hesaplanmış yeni inSampleSize değeri ile verilen kaynaktan Bitmap
        // nesnesi oluşturuluyor...
        return BitmapFactory.decodeResource(_Resources, resID, _Options);
    }
}
