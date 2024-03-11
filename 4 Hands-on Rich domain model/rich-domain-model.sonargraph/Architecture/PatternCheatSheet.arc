artifact ValueObjectsPattern
{
    exclude "External**"
    
    artifact Service
    {
        include "JavaHasAnnotation: org.jmolecules.ddd.annotation.Service"
        
        connect to Factory, Aggregate, Entity, ValueObject
    }
    
    artifact Factory
    {
        include "JavaHasAnnotation: org.jmolecules.ddd.annotation.Factory"
        
        connect to ValueObject
    }
    
    artifact Aggregate
    {
        include "JavaHasAnnotation: org.jmolecules.ddd.annotation.AggregateRoot"
        
        connect to Entity, ValueObject
    }
    
    artifact Entity
    {
        include "JavaHasAnnotation: org.jmolecules.ddd.annotation.Entity"
        
        connect to ValueObject
    }
    
    artifact ValueObject
    {
        include "JavaHasAnnotation: org.jmolecules.ddd.annotation.ValueObject"
    }
}