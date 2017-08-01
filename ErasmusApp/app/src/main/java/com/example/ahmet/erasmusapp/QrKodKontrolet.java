package com.example.ahmet.erasmusapp;

/**
 * Created by Yenen on 19.2.2017.
 */
public class QrKodKontrolet {
    public  static String Gorev1Qr ="SakaryaGorev1";
    public  static String Gorev2Qr ="SakaryaGorev2";
    public  static String Gorev3Qr ="SakaryaGorev3";
    public  static String Gorev4Qr ="SakaryaGorev4";
    public  static String Gorev5Qr ="SakaryaGorev5";
    public  static String Gorev6Qr ="SakaryaGorev6";
    public  static String Gorev7Qr ="SakaryaGorev7";
    public  static String Gorev8Qr ="SakaryaGorev8";
    public  static String Gorev9Qr ="SakaryaGorev9";
    public  static String Gorev10Qr ="SakaryaGorev10";
    public static String adimikontrolet(int GorevNo){
            if(GorevNo == 1){
                    return Gorev1Qr;
            }else if(GorevNo == 2){
                return Gorev2Qr;
            }else if( GorevNo ==3){
                return Gorev3Qr;
            }else if(GorevNo == 4){
                return Gorev4Qr;
            }
            else if(GorevNo == 5){
                return Gorev5Qr;
            }else if(GorevNo == 6){
                return Gorev6Qr;
            }else if(GorevNo == 7){
                return Gorev7Qr;
            }else if(GorevNo == 8){
                return Gorev8Qr;
            }else if(GorevNo == 9){
                return Gorev9Qr;
            }else if(GorevNo == 10){
                return Gorev10Qr;
            }
        return "Hata";
    }
}
