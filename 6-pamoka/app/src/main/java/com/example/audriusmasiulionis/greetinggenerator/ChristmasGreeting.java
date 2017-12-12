package com.example.audriusmasiulionis.greetinggenerator;

public class ChristmasGreeting {
    private final String friendsName;
    private final String friendsPhoneNumber;
    private String greeting;

    public ChristmasGreeting(String friendsName, String friendsPhoneNumber) {
        this.friendsName = friendsName;
        this.friendsPhoneNumber = friendsPhoneNumber;
        this.greeting = formatInitialGreeting();
    }

    public String getFriendsPhoneNumber() {
        return this.friendsPhoneNumber;
    }

    public void addNewYearsGreet(){
        this.greeting = this.greeting.concat("\nTaip pat noriu pasveikinti tave su artėjančiais Naujaisais metais.");
    }

    public void addWarmthGreet() {
        this.greeting = this.greeting.concat("\nNorėčiau palinkėti šilumos.");
    }

    public void addBestWishesForFamily() {
        this.greeting = this.greeting.concat("\nTaip pat nepamiršk praleisti laiką su artimais žmonėmis");
    }


    private String formatInitialGreeting() {
        return String.format("Labas, %s, \n Noriu pasveikinti tave su Šv.Kalėdomis", this.friendsName);
    }

}
