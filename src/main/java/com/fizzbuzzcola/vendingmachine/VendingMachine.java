package com.fizzbuzzcola.vendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private BigDecimal coinSlotBalance = BigDecimal.ZERO;
    private List<String> coinReturn = new ArrayList<>();
    private String displayMessage;
    private int colaStock = 1;
    private int chipsStock = 1;
    private int candyStock = 1;
    private int quartersForChange = 1;
    private int nickelsForChange = 1;
    private int dimesForChange = 1;

    public String displayStatus() {
        if (displayMessage == "Thank You") {
            String updatedMessage = displayMessage;
            displayMessage = "";
            return updatedMessage;
        }
        if (quartersForChange == 0 || nickelsForChange == 0 || dimesForChange == 0) {
            return displayMessage = "Exact Change Only";
        }
        if (displayMessage == "Sold Out") {
            String updatedMessage = displayMessage;
            displayMessage = "";
            return updatedMessage;
        }
        if (displayMessage == "Price: $1.00") {
            String updatedMessage = displayMessage;
            displayMessage = "";
            return updatedMessage;
        }
        if (displayMessage == "Price: $0.50") {
            String updatedMessage = displayMessage;
            displayMessage = "";
            return updatedMessage;
        }
        if (displayMessage == "Price: $0.65") {
            String updatedMessage = displayMessage;
            displayMessage = "";
            return updatedMessage;
        }
        if (coinSlotBalance.doubleValue() == 0) {
            displayMessage = "Insert Coin";
            return displayMessage;
        }
        displayMessage = coinSlotBalance.setScale(2).toString();
        return displayMessage;

    }

    public void insertCoin(String coin) {
        if (coin.equalsIgnoreCase("Nickel")) {
            coinSlotBalance = coinSlotBalance.add(BigDecimal.valueOf(0.05));
        } else if (coin.equalsIgnoreCase("Dime")) {
            coinSlotBalance = coinSlotBalance.add(BigDecimal.valueOf(0.10));
        } else if (coin.equalsIgnoreCase("Quarter")) {
            coinSlotBalance = coinSlotBalance.add(BigDecimal.valueOf(0.25));
        } else {
            coinReturn.add(coin);
        }
    }

    public List<String> emptyCoinReturn() {
        List<String> coinReturnContents = List.copyOf(coinReturn);
        coinReturn.clear();
        return coinReturnContents;
    }

    public void selectProduct(String product) {
        if (product.equalsIgnoreCase("Cola")) {
            if (colaStock == 0) {
                displayMessage = "Sold Out";
            } else if (coinSlotBalance.doubleValue() < 1.00) {
                displayMessage = "Price: $1.00";
            } else if (coinSlotBalance.doubleValue() >= 1.00) {
                displayMessage = "Thank You";
                colaStock -= 1;
                coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(1.00));
                makeChange();
            }
        }
        if (product.equalsIgnoreCase("Chips")) {
            if (chipsStock == 0) {
                displayMessage = "Sold Out";
            } else if (coinSlotBalance.doubleValue() < 0.50) {
                displayMessage = "Price: $0.50";
            } else if (coinSlotBalance.doubleValue() >= 0.50) {
                displayMessage = "Thank You";
                chipsStock -= 1;
                coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(0.50));
                makeChange();
            }
        }
        if (product.equalsIgnoreCase("Candy")) {
            if (candyStock == 0) {
                displayMessage = "Sold Out";
            } else if (coinSlotBalance.doubleValue() < 0.65) {
                displayMessage = "Price: $0.65";
            } else if (coinSlotBalance.doubleValue() >= 0.65) {
                displayMessage = "Thank You";
                candyStock -= 1;
                coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(0.65));
                makeChange();
            }
        }
    }

    private void makeChange() {
        while (coinSlotBalance.doubleValue() > 0) {
            if (coinSlotBalance.doubleValue() >= 0.25) {
                coinReturn.add("Quarter");
                quartersForChange -= 1;
                coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(0.25));
            }
            if (coinSlotBalance.doubleValue() >= 0.10) {
                coinReturn.add("Dime");
                dimesForChange -= 1;
                coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(0.10));
            }
            if (coinSlotBalance.doubleValue() >= 0.05) {
                coinReturn.add("Nickel");
                nickelsForChange -= 1;
                coinSlotBalance = coinSlotBalance.subtract(BigDecimal.valueOf(0.05));
            }
        }
    }

    public void returnCoins() {
        makeChange();
    }

    public BigDecimal getCoinSlotBalance() {
        return coinSlotBalance;
    }

    public List<String> getCoinReturn() {
        return coinReturn;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getColaStock() {
        return colaStock;
    }

    public int getChipsStock() {
        return chipsStock;
    }

    public int getCandyStock() {
        return candyStock;
    }
}

