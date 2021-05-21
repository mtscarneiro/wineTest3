# Criação da API com os 2(3) servições. 

Eu tenho os [CEPs](https://github.com/mtscarneiro/wineTest3/blob/master/src/main/java/wine/mtscarneiro/oke/entities/Zip.java),
tenho as [Lojas](https://github.com/mtscarneiro/wineTest3/blob/master/src/main/java/wine/mtscarneiro/oke/entities/Store.java)
e tenho as [requisições das lojas mais próximas por CEP](https://github.com/mtscarneiro/wineTest3/blob/master/src/main/java/wine/mtscarneiro/oke/entities/NearestStore.java).

No arquivo [TestConfig](https://github.com/mtscarneiro/wineTest3/blob/master/src/main/java/wine/mtscarneiro/oke/entities/NearestStore.java) fiz o mock dos dados passados no teste,
e tive que fazer a setagem das lojas com as informações do mock, além de ter simulado a lógica de proximidade assim:

```java

 protected void findIt(Store s) {
        Integer start = s.getDeliverRange().get(0).getStartZip();
        Integer end = s.getDeliverRange().get(0).getEndZip();

        if (s.getDeliverRange() != null) {
            if (getOwnZip() >= start) {
                if (getOwnZip() <= end) {
                    setNearStore(s);
                } else {
                    setNearStore(null);
                }
            }
        }
    }
```

##### com teste mockado, assim: 

```java

NearestStore ns = new NearestStore(null, 105000, s1);

NearestStore ns1 = new NearestStore(null, 100000,   s1);

```
##### Obtive um retorno, em json, dessa forma: 

```json
[
  {
    id: 1,
    ownZip: 105000,
    nearStore: {
      id: 1,
      storeCode: "LOJA_PINHEIROS",
      },
  },
  {
  id: 2,
  ownZip: 100000,
  nearStore: {
    id: 1,
    storeCode: "LOJA_PINHEIROS",
    },
  },
]
```

