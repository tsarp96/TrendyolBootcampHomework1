package LanguagesAndExceptions;

import LanguagesAndExceptions.Language;

public class English extends Language {

    @Override
    public String PackageAlreadyExistExceptionMessage() {
        return "Package Already Exist !";
    }

    @Override
    public String CompanyIsInBlackListExceptionMessage() {
        return "Company is in Blacklist !";
    }

    @Override
    public String NoAvailablePackageException() {
        return "No Available Package";
    }
}
