artifact HandsOnLegacyCode
{
    // Make sure that we do not fetch external classes
    exclude "External [Java]/**"
    
    artifact Credit
    {
        include "**/application/CreditService"
        include "**/models/Credit"
        include "**/models/CreditAccount"
        
        // Allow dependencies from Credit to Accounting
        connect to Accounting
    }
    
    artifact Accounting
    {
        include "**/application/AccountManagementService"
        include "**/models/Account"
        include "**/models/Customer"
    }
}