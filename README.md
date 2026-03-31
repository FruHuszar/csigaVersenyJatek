# Java Csigaverseny Játék

## Tanultak
### Enumok használata értékkel
Az enum egy típus, ami előre definiált*, állandó értékek halmazát jelenti (pl. Piros, Kek, Zold).
Lehetnek benne konstruktorok és getterek, így minden enum elemhez lehet értéket/tulajdonságot társítani.

*minden lehetséges értékét a kód írásakor már meg kell adni, és futás közben nem lehet új elemeket hozzáadni.

```java
public enum Szinek {
    PIROS("\u001B[31m"),
    KEK("\u001B[34m"),
    ZOLD("\u001B[32m");

    private final String szinkod;

    Szinek(String szinkod) {
        this.szinkod = szinkod;
    }

    public String getSzinkod() {
        return szinkod;
    }
}

//használat:
System.out.println(Szinek.PIROS.getSzinkod() + "●" + "\u001B[0m");
```
