package com.fizzbuzzcola.vendingmachine;

import org.junit.jupiter.api.Test;

public class CoinTest {
    @Test
    public void coinsShouldExist(){
        Coin underTest;
    }

    @Test
    public void penniesShouldBeACoin(){
        Coin underTest = new Penny();
    }

    @Test
    public void dimesShouldBeACoin(){
        Coin underTest = new Dime();
    }
}
