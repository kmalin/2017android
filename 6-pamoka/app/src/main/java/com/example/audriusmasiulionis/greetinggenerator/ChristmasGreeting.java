package com.example.audriusmasiulionis.greetinggenerator;

public class ChristmasGreeting {
    private final String friendsName;
    private final String friendsPhoneNumber;
    private boolean includeNewYearGreet;
    private boolean includeBestWishesForFamily;
    private boolean includeWarmthGreet;

    private final String newYearGreeting = "Taip pat noriu pasveikinti tave su artėjančiais Naujaisais metais.";
    private final String warmthGreeting = "Norėčiau palinkėti šilumos.";
    private final String wishesForFamily = "Taip pat nepamiršk praleisti laiką su artimais žmonėmis";


    public ChristmasGreeting(String friendsName, String friendsPhoneNumber) {
        this.friendsName = friendsName;
        this.friendsPhoneNumber = friendsPhoneNumber;
    }

    public String getFriendsPhoneNumber() {
        return this.friendsPhoneNumber;
    }

    public void addNewYearsGreet(){
        this.includeNewYearGreet = true;
    }

    public void addWarmthGreet() {
        this.includeWarmthGreet = true;
    }

    public void addBestWishesForFamily() {
        this.includeBestWishesForFamily = true;
    }

    @Override
    public String toString() {
        String greeting = String.format("Labas %s, \nNoriu palinkėti gražių Šv.Kalėdų.", this.friendsName);

        if (includeNewYearGreet){
           greeting = greeting.concat("\n" + newYearGreeting);
        }

        if (includeWarmthGreet){
            greeting = greeting.concat("\n" + warmthGreeting);
        }

        if (includeBestWishesForFamily){
            greeting = greeting.concat("\n" + wishesForFamily);
        }

        return greeting;
    }
}
