artifact CycleFree
{
    // Make sure that we do not fetch external classes
    exclude "External [Java]/**"
    
    artifact Accounting
    {
        include "**/accounting/**"
        
        // Allow dependencies from Accounting to Credit
        connect to Credit
    }
    
    artifact Credit
    {
        include "**/credit/**"
    }
    
    public artifact SharedKernel
    {
        include "**/sharedKernel/**"
    }
}
