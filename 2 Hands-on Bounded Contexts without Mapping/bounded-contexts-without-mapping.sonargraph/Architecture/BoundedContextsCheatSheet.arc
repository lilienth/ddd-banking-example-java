artifact HandsOnBoundedContextsWithoutMapping
{
    // Make sure that we do not fetch external classes
    exclude "External [Java]/**"
    
    artifact Accounting
    {
        include "**/accounting/**"
    }
    
    artifact Credit
    {
        include "**/credit/**"
    }
}
