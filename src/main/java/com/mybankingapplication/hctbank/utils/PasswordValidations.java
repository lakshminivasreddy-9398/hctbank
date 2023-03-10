package com.mybankingapplication.hctbank.utils;
public  class PasswordValidations {

        public static boolean isValidPassword(String password) {
            if (password.length() < 8) {
                return false;
            }

            boolean hasUppercase = false;
            boolean hasLowercase = false;
            boolean hasNumber = false;
            boolean hasSpecialChar = false;

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isUpperCase(c)) {
                    hasUppercase = true;
                } else if (Character.isLowerCase(c)) {
                    hasLowercase = true;
                } else if (Character.isDigit(c)) {
                    hasNumber = true;
                } else if ("!@#$%^&*()_+{}[]|\\:;\"'<>,.?/".indexOf(c) != -1) {
                    hasSpecialChar = true;
                }
            }

            return hasUppercase && hasLowercase && hasNumber && hasSpecialChar;
        }
    }

