package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final ElementsCollection cards = $$(".list__item div");

    public DashboardPage() {
        SelenideElement heading = $("[data-test-id='dashboard']");
        heading.should(visible);
    }

    public int getCardBalance(String id) {
        String text = getCardText(id);
        return extractBalance(text);
    }

    private String getCardText(String id) {
        String selector = String.format("[data-test-id='%s']", id);
        return $(selector).getText();
    }

    private int extractBalance(String text) {
        String balanceStart = "баланс: ";
        int start = text.indexOf(balanceStart);
        String balanceFinish = " р.";
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id", cardInfo.getCardId())).$("button").click();
        return new TransferPage();
    }

}