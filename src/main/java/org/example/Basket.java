package org.example;

import java.util.Map;

public class Basket {

    protected Map<String, Integer> prices;
    protected Purchase[] purchases; // Согласно принципу SOLID о единственной ответственности, создание объекта корзины перенесено в отдельный класс Basket.

    public Basket(Map<String, Integer> prices) { // 4 - "Магическое" число. Поэтому, чтобы задать размер корзины мы используем Map с ассортиментом товаров из main.
        this.prices = prices;
        this.purchases = new Purchase[prices.size()];
    }

    public void addPurchase(String title, int count) { // Согласно принципу SOLID о единственной ответственности, метод добавления товаров в корзину переносим в отдельный класс Basket.
        for (int i = 0; i < purchases.length; i++) {
            if (purchases[i] == null) {
                purchases[i] = new Purchase(title, count);
                return;
            }
            if (purchases[i].title.equals(title)) {
                purchases[i].count += count;
                return;
            }
        }
    }

    public long sum(Map<String, Integer> prices) { // Согласно принципу SOLID о единственной ответственности, метод вывода содержимого корзины на экран переносим в отдельный класс Basket.

        long sum = 0;
        System.out.println("КОРЗИНА:");
        for (int i = 0; i < purchases.length; i++) {
            Purchase purchase = purchases[i];
            if (purchase == null) continue;
            System.out.println("\t" + purchase.title + " " + purchase.count + " шт. в сумме " + (purchase.count * prices.get(purchase.title)) + " руб.");
            sum += (long) purchase.count * prices.get(purchase.title); // Уточнили, что значения могут быть long.
        }
        return sum;
    }
}
