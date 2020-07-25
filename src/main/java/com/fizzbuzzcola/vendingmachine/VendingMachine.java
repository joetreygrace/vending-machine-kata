package com.fizzbuzzcola.vendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private BigDecimal coinSlotBalance = BigDecimal.ZERO;
    private List<String> coinReturn = new ArrayList<>();
    private String displayMessage;

    public String displayStatus() {
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
        }
        if (coin.equalsIgnoreCase("Dime")) {
            coinSlotBalance = coinSlotBalance.add(BigDecimal.valueOf(0.10));
        }
        if (coin.equalsIgnoreCase("Quarter")) {
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
            if (coinSlotBalance.doubleValue() < 1.00) {
                displayMessage = "Price: $1.00";
            }
        }
        if (product.equalsIgnoreCase("Chips")) {
            if (coinSlotBalance.doubleValue() < 0.50) {
                displayMessage = "Price: $0.50";
            }
        }
        if (product.equalsIgnoreCase("Candy")) {
            if (coinSlotBalance.doubleValue() < 0.65) {
                displayMessage = "Price: $0.65";
            }
        }
    }
}
