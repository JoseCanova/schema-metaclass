### Prepare for next development cycle, that  will be generate the metamodel to describe rdbms relations (fk`s relations). 
#### Then after generate the metamodel to describe such relations to provide the elements for the class generation algorithm. 

##### Reducing the scope of the component, which means remove experimental algorithms for table classification.
##### Experimental Table classification algorithms will be removed from the component,first because data scanning, second because it overhelm the component main objective that is mount a metamodel based on schemacrawler datasctructures.
##### Table classification will be moved to attic and in future (if possible) being implemented with an ai-powered component.
##### Need now refinement on metaClass ForeignKeys using Index to identify relationship type
##### Rationale if fk column has a unique index this probably will rely on a one-one relationship
##### Rationale if fk column does not have index associated or the index is non-unique will rely on a many-one relationship.
##### The many-many case will occur if there is a "join table" but it`s a particullar case that have also to handle with the relationships described above.

