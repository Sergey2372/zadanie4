import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int accsAmount; //переменная для подсчета количества договоров
        int cardsAmount; //переменная для подсчета количества карт
        int countOfDebits = 0; //счетчик для подсчета количества дебетовых карт
        int countOfCredits = 0; //счетчик для подсчета количества кредитных карт
        int countOfRubs = 0; //счетчик для фильтра с рублевыми картами
        int countOfUsds = 0; //счетчик для фильтра с долларовыми картами
        long intUcid1; //переменная для перевода ucid 1-й карты из стринг в лонг
        long intUcid2; //переменная для перевода ucid 2-й карты из стринг в лонг


        ACCOUNTS acc1 = new ACCOUNTS(); //договор 1 по условию
        acc1.accountNumber = "5000012345";
        acc1.currency = "RUB";
        acc1.productType = "Текущий счет";
        acc1.productName = "Расчетная карта. ТПС 3.0 RUB";
        acc1.multiSerno = 12345678;
        acc1.status = "NORM";
        acc1.customerId = "5-QWERTYUI";
        acc1.isEmoneyAccount = false;

        ACCOUNTS acc2 = new ACCOUNTS(); //договор 2 по условию
        acc2.accountNumber = "0000012345";
        acc2.currency = "RUB";
        acc2.productType = "Тариф";
        acc2.productName = "Кредитная карта. ТП 7.27 RUB";
        acc2.status = "NORM";
        acc2.customerId = "5-QWERTYUI";
        acc2.isEmoneyAccount = false;


        CARDS card1 = new CARDS(); //карта 1 по условию
        card1.ucid = "9876543210";
        card1.accountNumber = "5000012345";
        card1.status = "CLSN";
        card1.virtualCard = false;
        card1.lastFourDigits = "5678";
        card1.designCode = 169;
        card1.cardActive = true;
        card1.cardholder = "JOHNY DEPP";
        card1.paymentSystem = "MC";
        card1.customerId = "5-QWERTYUI";
        card1.frozenCard = false;
        card1.productType = "Текущий счет";
        card1.isNonameCard = false;
        card1.embossingName = "JOHNY DEPP";

        CARDS card2 = new CARDS(); //карта 2 по условию
        card2.ucid = "0123456789";
        card2.accountNumber = "0000012345";
        card2.status = "LOST";
        card2.virtualCard = false;
        card2.lastFourDigits = "4567";
        card2.designCode = 270;
        card2.cardActive = true;
        card2.cardholder = "JOHNY DEPP";
        card2.paymentSystem = "VI";
        card2.customerId = "5-QWERTYUI";
        card2.frozenCard = false;
        card2.productType = "Тариф";
        card2.isNonameCard = false;
        card2.embossingName = "JOHNY DEPP";

        ACCOUNTS[] ArrayAcc = {acc1, acc2};
        accsAmount = ArrayAcc.length; //подсчет количества договоров

        CARDS[] ArrayCards = {card1, card2};
        cardsAmount = ArrayCards.length; //подсчет количества карт

        for (ACCOUNTS acc:ArrayAcc) { //перебор в массиве всех элементов типа ACCOUNTS
            if (acc.productType.equals("Текущий счет")) { //подсчет количества дебетовых карт
                countOfDebits++;
            }
            if (acc.productType.equals("Тариф")) { //подсчет количества кредитных карт
                countOfCredits++;
            }
            if (acc.currency.equals("RUB")) { //счетчик для фильтра для след. блока
                countOfRubs++;
            }
            if (acc.currency.equals("USD")) { //счетчик для фильтра для след. блока
                countOfUsds++;
            }
        }

        String[] rubAccounts = new String[countOfRubs]; //фильтр rub-карт (массив таких карт)

        int i = 0;
        int j = 0;

        for (ACCOUNTS acc : ArrayAcc) {  //цикл для прохода по всем картам и поискам только рублевых, с последующим занесением их в rubAccounts, созданный выше
            if (i < rubAccounts.length) {
                if (acc.currency.equals("RUB")) {
                    rubAccounts[i++] = ArrayAcc[j].accountNumber;
                }
            }
            j++;
        }

        String[] usdAccounts = new String[countOfUsds]; //фильтр usd-карт (массив таких карт)
        
        i = 0;
        j = 0;

        for (ACCOUNTS acc : ArrayAcc) { //цикл для прохода по всем картам и поискам только долларовых, с последующим занесением их в usdAccounts, созданный выше
            if (i < usdAccounts.length) {
                if (acc.currency.equals("USD")) {
                    usdAccounts[i++] = ArrayAcc[j].accountNumber;
                }
            }
            j++;
        }

        intUcid1 = Long.parseLong(card1.ucid); //преобразование стринга в лонг (в инт не получается из-за длины номера договора)
        intUcid2 = Long.parseLong(card2.ucid);


    }
}

class ACCOUNTS {
    String accountNumber;
    String currency;
    String productType;
    String productName;
    int multiSerno;
    String status;
    String ciType;
    String linkedCuAccount;
    String customerId;
    boolean isEmoneyAccount;

}

class CARDS {
    String ucid;
    String accountNumber;
    String status;
    String supplementaryType;
    boolean virtualCard;
    String lastFourDigits;
    int designCode;
    boolean cardActive;
    String cardholder;
    String paymentSystem;
    String customerId;
    String previousCardUcid;
    String nextCardUcid;
    boolean frozenCard;
    String productType;
    boolean isNonameCard;
    String embossingName;

    String currency; //добавление поля для валюты
}
