package LanguagesAndExceptions;

public class NoAvailablePackageException extends RuntimeException {
    public NoAvailablePackageException(Language language){
        super(language.NoAvailablePackageException());
    }
}
