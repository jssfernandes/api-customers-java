package br.com.customers.utils;

import java.util.InputMismatchException;
import org.springframework.stereotype.Component;


@Component
public class CpfCnpjValidation {
    private CpfCnpjValidation() {}

    public static boolean isValid(String document) {
        if (document != null && !document.isEmpty()) {
            return isCnpj(document) || isCPF(document);
        }
        return true;
    }

    public static boolean isCPF(String documentCPF) {
        documentCPF = removeDots(documentCPF);
        if (documentCPF.equals("00000000000") || documentCPF.equals("11111111111") || documentCPF.equals("22222222222")
                || documentCPF.equals("33333333333") || documentCPF.equals("44444444444")
                || documentCPF.equals("55555555555") || documentCPF.equals("66666666666")
                || documentCPF.equals("77777777777") || documentCPF.equals("88888888888")
                || documentCPF.equals("99999999999") || (documentCPF.length() != 11)) {
            return (false);
        }

        char dig10;
        char dig11;
        int sm;
        int i;
        int r;
        int num;
        int weight;

        try {
            sm = 0;
            weight = 10;
            for (i = 0; i < 9; i++) {
                num = (documentCPF.charAt(i) - 48);
                sm = sm + (num * weight);
                weight = weight - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }
            sm = 0;
            weight = 11;
            for (i = 0; i < 10; i++) {
                num = (documentCPF.charAt(i) - 48);
                sm = sm + (num * weight);
                weight = weight - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == documentCPF.charAt(9)) && (dig11 == documentCPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException ex) {
            return (false);
        }
    }

    public static boolean isCnpj(String documentCNPJ) {
        documentCNPJ = removeDots(documentCNPJ);
        if (documentCNPJ.equals("00000000000000") || documentCNPJ.equals("11111111111111")
                || documentCNPJ.equals("22222222222222") || documentCNPJ.equals("33333333333333")
                || documentCNPJ.equals("44444444444444") || documentCNPJ.equals("55555555555555")
                || documentCNPJ.equals("66666666666666") || documentCNPJ.equals("77777777777777")
                || documentCNPJ.equals("88888888888888") || documentCNPJ.equals("99999999999999")
                || (documentCNPJ.length() != 14)) {
            return (false);
        }
        int sm;
        int i;
        int num;
        int weight;
        try {
            sm = 0;
            weight = 2;
            for (i = 11; i >= 0; i--) {
                num = (documentCNPJ.charAt(i) - 48);
                sm = sm + (num * weight);
                weight = weight + 1;
                if (weight == 10) {
                    weight = 2;
                }
            }

            return isCnpjPart2(documentCNPJ, sm);
        } catch (InputMismatchException ex) {
            return (false);
        }
    }

    private static boolean isCnpjPart2(String documentCNPJ, int sm) {
        int weight;
        char dig14;
        int num;
        char dig13;
        int r;
        int i;
        r = sm % 11;
        if ((r == 0) || (r == 1)) {
            dig13 = '0';
        } else {
            dig13 = (char) ((11 - r) + 48);
        }

        sm = 0;
        weight = 2;
        for (i = 12; i >= 0; i--) {
            num = (documentCNPJ.charAt(i) - 48);
            sm = sm + (num * weight);
            weight = weight + 1;
            if (weight == 10) {
                weight = 2;
            }
        }

        r = sm % 11;
        if ((r == 0) || (r == 1)) {
            dig14 = '0';
        } else {
            dig14 = (char) ((11 - r) + 48);
        }

        if ((dig13 == documentCNPJ.charAt(12)) && (dig14 == documentCNPJ.charAt(13))) {
            return (true);
        } else {
            return (false);
        }
    }

    public static String removeDots(String document) {
        if (document.contains(".")) {
            document = document.replace(".", "");
        }
        if (document.contains("-")) {
            document = document.replace("-", "");
        }
        if (document.contains("/")) {
            document = document.replace("/", "");
        }
        return document;
    }
    
}