package com.example.assignment3.model;

public class BookData {
    public String bookName;
    public String authorName;
    public String bookGenre;
    public String bookType;
    public String launchDate;
    public String ageGrpOne;
    public String ageGrpTwo;
    public String ageGrpThree;
    public String ageGrpFour;

    public BookData(String bookName, String authorName, String bookGenre, String bookType, String launchDate, String ageGrpOne, String ageGrpTwo, String ageGrpThree, String ageGrpFour) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookGenre = bookGenre;
        this.bookType = bookType;
        this.launchDate = launchDate;
        this.ageGrpOne = ageGrpOne;
        this.ageGrpTwo = ageGrpTwo;
        this.ageGrpThree = ageGrpThree;
        this.ageGrpFour = ageGrpFour;
    }


    public String getBookName() {
        return bookName;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    /*public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getAgeGrpOne() {
        return ageGrpOne;
    }

    public void setAgeGrpOne(String ageGrpOne) {
        this.ageGrpOne = ageGrpOne;
    }

    public String getAgeGrpTwo() {
        return ageGrpTwo;
    }

    public void setAgeGrpTwo(String ageGrpTwo) {
        this.ageGrpTwo = ageGrpTwo;
    }

    public String getAgeGrpThree() {
        return ageGrpThree;
    }

    public void setAgeGrpThree(String ageGrpThree) {
        this.ageGrpThree = ageGrpThree;
    }

    public String getAgeGrpFour() {
        return ageGrpFour;
    }

    public void setAgeGrpFour(String ageGrpFour) {
        this.ageGrpFour = ageGrpFour;
    }*/
}
