package com.fizzbuzzcola.vendingmachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineTest {

    private VendingMachine underTest;

    @BeforeEach
    void setup() {
        underTest = new VendingMachine();
    }

    @Test
    public void noCoinsInMachineShouldReturnInsertCoin() {
        String displayText = underTest.displayStatus();
        assertThat(displayText).isEqualTo("Insert Coin");
    }

    @Test
    public void whenNickelInsertedDisplayUpdate() {
        underTest.insertCoin("Nickel");
        assertThat(underTest.displayStatus()).isEqualTo("0.05");
    }

    @Test
    public void whenDimeInsertedDisplayUpdate() {
        underTest.insertCoin("Dime");
        assertThat(underTest.displayStatus()).isEqualTo("0.10");
    }

    @Test
    public void whenQuarterInsertedDisplayUpdate() {
        underTest.insertCoin("Quarter");
        assertThat(underTest.displayStatus()).isEqualTo("0.25");
    }

    @Test
    public void shouldRejectPennyWhenInserted() {
        underTest.insertCoin("Penny");
        assertThat(underTest.displayStatus()).isEqualTo("Insert Coin");
        List<String> returnedCoins = underTest.emptyCoinReturn();
        assertThat(returnedCoins).containsExactly("Penny");
    }

    @Test
    public void ifColaIsSelectedBeforeEnoughMoneyInsertedDisplayShouldReturnPrice() {
        underTest.selectProduct("Cola");
        assertThat(underTest.displayStatus()).isEqualTo("Price: $1.00");

    }

    @Test
    public void ifChipsAreSelectedBeforeEnoughMoneyInsertedDisplayShouldReturnPrice() {
        underTest.selectProduct("Chips");
        assertThat(underTest.displayStatus()).isEqualTo("Price: $0.50");
    }

    @Test
    public void ifCandyIsSelectedBeforeEnoughMoneyInsertedDisplayShouldReturnPrice() {
        underTest.selectProduct("Candy");
        assertThat(underTest.displayStatus()).isEqualTo("Price: $0.65");
    }

    @Test
    public void whenColaSelectedAndEnoughMoneyInsertedDisplayReturnsThankYou() {
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.selectProduct("Cola");
        assertThat(underTest.displayStatus()).isEqualTo("Thank You");
    }

    @Test
    public void whenChipsSelectedAndEnoughMoneyInsertedDisplayReturnsThankYou() {
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.selectProduct("chips");
        assertThat(underTest.displayStatus()).isEqualTo("Thank You");
    }

    @Test
    public void whenCandySelectedAndEnoughMoneyInsertedDisplayReturnsThankYou() {
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("dime");
        underTest.insertCoin("nickel");
        underTest.selectProduct("candy");
        assertThat(underTest.displayStatus()).isEqualTo("Thank You");
    }

    @Test
    public void machineShouldMakeChangeWhenMoneyInsertedIsMoreThanCostOfSoda() {
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Nickel");
        underTest.insertCoin("Dime");
        underTest.selectProduct("Cola");
        List<String> returnedCoins = underTest.emptyCoinReturn();
        assertThat(returnedCoins).containsExactlyInAnyOrder("Quarter", "Nickel", "Dime");
    }

    @Test
    public void machineShouldMakeChangeWhenMoneyInsertedIsMoreThanCostOfChips() {
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Nickel");
        underTest.insertCoin("Dime");
        underTest.selectProduct("Chips");
        List<String> returnedCoins = underTest.emptyCoinReturn();
        assertThat(returnedCoins).containsExactlyInAnyOrder("Quarter", "Nickel", "Dime");
    }

    @Test
    public void machineShouldMakeChangeWhenMoneyInsertedIsMoreThanCostOfCandy() {
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Nickel");
        underTest.insertCoin("Dime");
        underTest.insertCoin("Nickel");
        underTest.insertCoin("Dime");
        underTest.selectProduct("Candy");
        List<String> returnedCoins = underTest.emptyCoinReturn();
        assertThat(returnedCoins).containsExactlyInAnyOrder("Quarter", "Nickel", "Dime");
    }

    @Test
    public void returnCoinsShouldReturnCustomerTheirMoney() {
        underTest.insertCoin("Quarter");
        underTest.insertCoin("Nickel");
        underTest.insertCoin("Dime");
        underTest.returnCoins();
        List<String> returnedCoins = underTest.emptyCoinReturn();
        assertThat(returnedCoins).containsExactlyInAnyOrder("Quarter", "Nickel", "Dime");
    }
}
