export const checkCodFisc = {
    data() {
    return {
      codFiscOk: null,
      codFiscMessage: null
    };
  },
    methods: {
        checkCodFisc() {
            let TAX_CODE_LENGTH = 16;
            let REGEXP_STRING_FOR_LASTNAME = "[A-Za-z]{3}";
            let REGEXP_STRING_FOR_FIRSTNAME = "[A-Za-z]{3}";
            let REGEXP_STRING_FOR_BIRTHDATE_YEAR = "[0-9LlMmNnPpQqRrSsTtUuVv]{2}";
            let REGEXP_STRING_FOR_BIRTHDATE_MONTH = "[AaBbCcDdEeHhLlMmPpRrSsTt]{1}";
            let REGEXP_STRING_FOR_BIRTHDATE_DAY_GENDER_PART_1 =
              "[0-7LlMmNnPpQqRrSsTtUuVv]{1}";
            let REGEXP_STRING_FOR_BIRTHDATE_DAY_GENDER_PART_2 =
              "[0-9LlMmNnPpQqRrSsTtUuVv]{1}";
            let REGEXP_STRING_FOR_BIRTHTOWN_PART_1 = "[A-Za-z]{1}";
            let REGEXP_STRING_FOR_BIRTHTOWN_PART_2 = "[0-9LlMmNnPpQqRrSsTtUuVv]{3}";
            let REGEXP_STRING_FOR_CIN = "[A-Za-z]{1}";
            let REGEXP = new RegExp(
              "^" +
                REGEXP_STRING_FOR_LASTNAME +
                REGEXP_STRING_FOR_FIRSTNAME +
                REGEXP_STRING_FOR_BIRTHDATE_YEAR +
                REGEXP_STRING_FOR_BIRTHDATE_MONTH +
                REGEXP_STRING_FOR_BIRTHDATE_DAY_GENDER_PART_1 +
                REGEXP_STRING_FOR_BIRTHDATE_DAY_GENDER_PART_2 +
                REGEXP_STRING_FOR_BIRTHTOWN_PART_1 +
                REGEXP_STRING_FOR_BIRTHTOWN_PART_2 +
                REGEXP_STRING_FOR_CIN +
                "$"
            );
            let codFisc = this.user.cf;
            if (codFisc.length === 16 && REGEXP.test(codFisc)) {
              this.codFiscOk = true;
              this.codFiscMessage = null;
            } else {
              this.codFiscOk = false;
              this.codFiscMessage = "Codice fiscale non valido";
            }
          },
          reCheckCodFisc() {
            if (this.codFiscOk === false) {
              this.checkCodFisc();
            }
          }
    }
}