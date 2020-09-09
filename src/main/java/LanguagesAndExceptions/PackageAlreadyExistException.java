package LanguagesAndExceptions;

public class PackageAlreadyExistException extends RuntimeException{
    public PackageAlreadyExistException(Language language){
        super(language.PackageAlreadyExistExceptionMessage());
    }
}
