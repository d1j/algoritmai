const g_duom = require("./duom").duom;
const g_sk = require("./duom").sk;

function main() {
  for (let i = 0; i < g_sk; i++) {
    //duomenų masyvas.
    let duom = g_duom[i];
    console.log(`Apdorojamas ${+i + 1} medis:`);
    //viršūnių skaičius.
    let virsSk = 0;
    for (let j in duom) {
      if (duom[j][0] > duom[j][1]) {
        if (duom[j][0] > virsSk) virsSk = duom[j][0];
      } else {
        if (duom[j][1] > virsSk) virsSk = duom[j][1];
      }
    }
    //masyvas, kuriame bus saugoma, su kokiomis kitomis viršūnėmis kiekviena viršūnė sudaro briaunas.
    //masyvo 0 elementas atitinka pirmą viršūnę, 1 elementas - antrą ir t.t.
    let medis = [];
    for (let j = 0; j < virsSk; j++) {
      medis.push([]);
    }

    //į `medis` suvedama, su kokiomis viršūnėmis susidaro briaunos.
    for (let j in duom) {
      medis[duom[j][0] - 1].push(duom[j][1]);
      medis[duom[j][1] - 1].push(duom[j][0]);
    }

    //priuferio kodo masyvas
    let priuf = [];
    //priuferio kodo skaiciavimas
    while (priuf.length < virsSk - 2) {
      let j = 0;
      while (medis[j].length != 1) j++; //randama pirma masyve medžio viršūnė, kuri turi tik vieną briauną
      //šioje vietoje rasta viršūnė yra (j+1)
      let virs = medis[j][0]; //išsaugojama viršūnė, su kuria (j+1) turi briauną
      //ta viršūnė - sekantis priuferio kodo elementas
      priuf.push(virs);
      medis[virs - 1].splice(medis[virs - 1].indexOf(j + 1), 1); //pašalinama `virs` viršūnės sąsaja su (j+1) viršūne.
      medis[j] = []; //(j+1) viršūnė pašalinama iš medžio
    }
    console.log(`${+i + 1} medzio priuferio kodas yra: ${priuf}\n`);
  }
}

main();
