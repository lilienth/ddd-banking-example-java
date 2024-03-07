artifact HandsOnValueObjects
{
    // Make sure that we do not fetch external classes
    exclude "External [Java]/**"
    
    artifact Accounting
    {
        include "**/accounting/**"
        
        // Connector containing only classes with names ending with Service
        connector Services
        {
            include "**Service"
        }
        
        // Allow only dependencies from services of Accounting to services of Credit
        connect Services to Credit.Services
    }
    
    artifact Credit
    {
        include "**/credit/**"
        
        // Interface containing only classes with names ending with Service
        interface Services
        {
            include "**Service"
        }
    }
}
