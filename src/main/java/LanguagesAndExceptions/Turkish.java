package LanguagesAndExceptions;

public class Turkish extends Language {

    @Override
    public String PackageAlreadyExistExceptionMessage() {
        return "Bu paket zaten kullanımda !";
    }

    @Override
    public String CompanyIsInBlackListExceptionMessage() {
        return "Şirket kara listede !";
    }

    @Override
    public String NoAvailablePackageException() {
        return "Uygun paket bulunamadı !";
    }
}
