package org.nanotek.meta.identity.rdbms.test;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import static org.instancio.Select.*;
import static org.instancio.When.*;
import org.instancio.When;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import static org.instancio.Assign.*;
import org.instancio.Assign;
import schemacrawler.schema.Table;


//TODO: generate a more detailed class set to provide a full set of tests for the underlying model.
public class RdbmsMetaClassBuilder {

	public RdbmsMetaClassBuilder() {
	}
	
	public static RdbmsMetaClass buildSimpleRdbmsMetaClass() {
		Table schemaTable = new SchemaCrawlerSimpleTable();
		return
				new RdbmsMetaClass("Table_Name" , "Class_Name", schemaTable);
	}
	
	public static RdbmsMetaClass buildInstancioRdbmsMetaClass() {
		Model<RdbmsMetaClass> metaClassModel = Instancio.of(RdbmsMetaClass.class)
			    .ignore(field("rdbmsClass"))
			    .ignore(field(RdbmsMetaClass::getIdentity))
			    .ignore(field(RdbmsMetaClass::getId))
			    .ignore(field(RdbmsMetaClass::getMetaAttributes))
			    .toModel();
		
		return Instancio.of(metaClassModel)
				.generate(field(RdbmsMetaClass::getClassName), gen -> gen.oneOf("FirstClass", "SecondClass", "ThirdClass"))
				.assign(given(field(RdbmsMetaClass::getClassName), field(RdbmsMetaClass::getTableName))
				.set(When.is("FirstClass"), "first_class")
				.set(When.is("SecondClass"), "second_class")
				.set(When.is("ThirdClass"), "third_class"))
				.create();
	}
	
}
