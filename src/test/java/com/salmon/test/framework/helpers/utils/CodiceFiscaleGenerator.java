package com.salmon.test.framework.helpers.utils;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class CodiceFiscaleGenerator {

    private LocalDate birthDate;
    private Boolean isMale;

    /**
     * @param birthDate
     * @param isMale
     */
    public CodiceFiscaleGenerator(LocalDate birthDate, Boolean isMale) {
        this.isMale = isMale;
        this.birthDate = birthDate;
    }

    /**
     * Generate a random number between 100000 and 1000000
     * @return
     */
    private int getRandomCodeNumber() {
        int i = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return i;
    }

    /**
     * Get the corresponding letter of a month
     * @param d
     * @return
     */
    private String codeMonth(LocalDate d){
        int m = d.getMonthValue();
        String[] corr = new String[12];
        corr[0] = "A";
        corr[1] = "B";
        corr[2] = "C";
        corr[3] = "D";
        corr[4] = "E";
        corr[5] = "H";
        corr[6] = "L";
        corr[7] = "M";
        corr[8] = "P";
        corr[9] = "R";
        corr[10] = "S";
        corr[11] = "T";

        return corr[m - 1];
    }

    /**
     * @return
     */
    private String getDateOfBirthCode() {
        StringBuilder sb = new StringBuilder();
        String anno = String.valueOf(birthDate.getYear()).substring(2);

        sb.append(anno);

        int giorno = birthDate.getDayOfMonth();

        if (!isMale) {
            giorno += 40;
        }

        sb.append(codeMonth(birthDate));

        String giornoString = String.valueOf(giorno);
        if (giornoString.length() == 1) {
            sb.append("0");
        }
        sb.append(giornoString);

        return sb.toString();
    }

    /**
     * @return
     */
    public String getFiscalCode() {
        return getRandomCodeNumber() + getDateOfBirthCode() + ThreadLocalRandom.current().nextInt(10000, 100000);
    }


}
