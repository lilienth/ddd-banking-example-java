artifact HandsOnLegacyCode
{
    // Make sure that we do not fetch external classes
    exclude "External [Java]/**"

    artifact Accounting
    {
        include "**/application/AccountManagementService"
        include "**/models/Account"
        include "**/models/Customer"

        // Allow dependencies from Accounting to Credit
        connect to Credit
    }

    artifact Credit
    {
        include "**/application/CreditService"
        include "**/models/Credit"
        include "**/models/CreditAccount"
        

    }
}