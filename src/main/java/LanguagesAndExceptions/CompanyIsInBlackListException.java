package LanguagesAndExceptions;


public class CompanyIsInBlackListException extends RuntimeException {
    public CompanyIsInBlackListException(Language language) {
        super(language.CompanyIsInBlackListExceptionMessage());
    }
}
