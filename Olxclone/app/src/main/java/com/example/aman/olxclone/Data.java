package com.example.aman.olxclone;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data extends Application{
    public static  List<User> users=new ArrayList<User>();
    public static List<Ads> all_ads=new ArrayList<Ads>();
    public static String my_id;

    public Integer getnewuser_id(){

        return users.size();
    }

    public Integer getnewad_id(){
        return all_ads.size();

    }

    public Data() {
        my_id=new String("0");
        users.add(new User("Abhay Gairola","8130728737",null,getnewuser_id().toString(),new ArrayList<String>(Arrays.asList("0", "1","2")),new ArrayList<String>(Arrays.asList("3", "8"))));
        users.add(new User("Pallav Garg","9999999999",null,getnewuser_id().toString(),new ArrayList<String>(Arrays.asList("3", "4")),new ArrayList<String>(Arrays.asList("0", "1"))));
        users.add(new User("Anil Ambani","8888888888",null,getnewuser_id().toString(),new ArrayList<String>(Arrays.asList("5")),new ArrayList<String>(Arrays.asList("0", "1"))));
        users.add(new User("Mukesh Ambani","0000000000",null,getnewuser_id().toString(),new ArrayList<String>(Arrays.asList("6","7")),new ArrayList<String>(Arrays.asList("0", "3","5"))));
        users.add(new User("Narendra Modi","2222222222",null,getnewuser_id().toString(),new ArrayList<String>(Arrays.asList("8")),new ArrayList<String>(Arrays.asList("0"))));

        all_ads.add(new Ads("Honda City Vx Cvt, 2015, Petrol",null,"Vehicle Specs:\n" +
                "\n" +
                "Make: Honda\n" +
                "Model: City\n" +
                "Variant: City VX CVT\n" +
                "Vehicle Type: Sedans\n" +
                "Mileage: 50,000 KMS\n" +
                "Make Year: 2015\n" +
                "Make Month: April\n" +
                "Fuel type: Petrol\n" +
                "Transmission type: Automatic\n" +
                "Condition: Used\n" +
                "Is the vehicle certified?: Yes\n" +
                "Is vehicle accidental?: No\n" +
                "Is vehicle flood affected?: No\n" +
                "Insurance Type: Comprehensive\n" +
                "No. of Owners: First\n" +
                "Colors: White\n" +
                "One Time Tax: Individual\n" +
                "Registration Place: TN\n" +
                "Plate: 75","Cars",null,"0","0","Rs.2,00,000",200,10));

        all_ads.add(new Ads("Free home delivery Office Cum computer table size 4x2",null,"Black Wooden 2-door Cabinet","Furniture",null,"0","1","Rs.10,000",100,10));
        all_ads.add(new Ads("Dell core i5 with warranty 8 gb ram ,1 tb hdd , 2 gb graphics",null,"9 8 2 6 8 1 1 1 6 9 has this dell laptop in A+ condition just like new and great backup backup . cam , dvd","Laptops",null,"0","2","Rs.20,000",10,2));





        all_ads.add(new Ads("Lg g5 dual camera sim 32gb memory with bill and jio",null,"Lg g5 dual camera sim 32gb memory with bill and jio support and volte Available one month sellrs warrnti shop se\n" +
                "\n","Mobiles",null,"1","3","Rs.1,000",300,10));
        all_ads.add(new Ads("Fastly sell dual camera 13+2 with front 13 flsh",null,"Fastly sell dual camera 13+2 with front 13 flsh\n" +
                "3/32gb full hd display \n" +
                "Fast fingerprint unlock \n" +
                "Octa core processer","Mobiles",null,"1","4","Rs.2,000",100,20));


        all_ads.add(new Ads("Arihant All in one books (PCM) 11th + 12th class",null,"Arihant All in one books (PCM)\n" +
                "11th + 12th class in 60%\n" +
                "All in excellent condition without any marks of any kind at any page (barely used) \n" +
                "•Almost brand new books at 60% of market price \n" +
                "•2017-2018 Edition\n" +
                "• Covered with transparent cover\n" +
                "• Guaranteed no marks of pen or pencil","Books",null,"2","5","Rs.3,000",500,10));

        all_ads.add(new Ads("Polo headlights aftermarket A4 style",null,"Polo headlights aftermarket A4 style","Cars",null,"3","6","Rs.4,000",23,2));
        all_ads.add(new Ads("Hyundai Eon Era +, 2014, Petrol",null,"EON ERA PLUS \n" +
                "2014 MODEL\n" +
                "FINANCE AVAILABLE\n" +
                "\n" +
                "Vehicle Specs:\n" +
                "\n" +
                "Make: Hyundai\n" +
                "Model: Eon\n" +
                "Variant: Eon Era +\n" +
                "Vehicle Type: Hatchback\n" +
                "Mileage: 39,900 KMS\n" +
                "Make Year: 2014\n" +
                "Make Month: November\n" +
                "Fuel type: Petrol\n" +
                "Transmission type: Manual\n" +
                "Condition: Used\n" +
                "Is the vehicle certified?: Yes\n" +
                "Is vehicle accidental?: No\n" +
                "Is vehicle flood affected?: No\n" +
                "Insurance Type: Comprehensive\n" +
                "Insurance Year: 2019\n" +
                "No. of Owners: Second\n" +
                "Colors: White\n" +
                "One Time Tax: Individual\n" +
                "Registration Place: KL","Cars",null,"3","7","Rs.10,000",120,10));

        all_ads.add(new Ads("Macbook Pro I5 Laptop with Original adaptor only just rs 29900/- warnt",null,"Macbook Pro Core I5 Laptop \n" +
                "\n" +
                "DDRIII ram 4gb \n" +
                "\n" +
                "Hdd 500gb \n" +
                "\n" +
                "web cam \n" +
                "\n" +
                "Blutooth\n" +
                "\n" +
                "3hr Battery back up \n" +
                "\n" +
                "Adaptor\n" +
                "\n" +
                "2gb Graphic card\n" +
                "\n" +
                "13.3inch screen \n" +
                "\n" +
                "HDMI Port\n" +
                "\n" +
                "2USB Port \n" +
                "\n" +
                "A++ Condition\n" +
                "\n" +
                "One Month Testing warranty \n" +
                "\n" +
                "More Info Call Me on 9.9 2 8 I 9 7 II 8\n" +
                "\n" +
                "Otherwise Visit on my address:-\n" +
                "Web IT World\n" +
                "84,85 Shyam Nagar\n" +
                "Nadi Ka Phatak\n" +
                "Benar Road \n" +
                "Jhotwara\n" +
                "Jaipur","Laptops",null,"4","8","Rs.30,000",200,12));

    }


}
