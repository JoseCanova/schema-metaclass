package org.nanotek.meta.integration.handler;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.SchemaTable;
import org.nanotek.meta.model.rdbms.classification.data.TableColumns;
import org.nanotek.meta.model.rdbms.classification.data.TableForeignKeys;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexes;
import org.nanotek.meta.model.rdbms.classification.data.TableKey;
import org.nanotek.meta.model.rdbms.classification.data.result.SecondNormalFormClassificationResult;
import org.nanotek.meta.model.rdbms.classification.task.SecondNormalFormClassificationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Table;

@Component
public class SecondNormalFormServiceActivator {

	@Autowired
	SecondNormalFormClassificationTask secondNormalFormTask;
	
	public SecondNormalFormServiceActivator() {
		// TODO Auto-generated constructor stub
	}
	
	@ServiceActivator(inputChannel = "secondNormalFormMessageChannel" , outputChannel = "secondNormalFormChannelResultChannel" )
	public Message<ClassificationData> secondNormalFormTask(Message<?> message) {
		Table theTable = Table.class.cast(message.getPayload());
		ClassificationData cd1 = buildClassificationData (theTable);
		Optional<SecondNormalFormClassificationResult> csf2 = secondNormalFormTask.evaluate(cd1);
		System.err.println("arrived table");
		return MessageBuilder.withPayload(cd1).setHeader("result", csf2).build();
	}


	private ClassificationData buildClassificationData(Table table) {
		return new ClassificationData(
				new SchemaTable(Optional.of(table)),
				new TableKey(Optional.ofNullable(table.getPrimaryKey())),
				new TableColumns(Optional.ofNullable(table.getColumns())),
				new TableForeignKeys(Optional.ofNullable(table.getForeignKeys())), 
				new TableIndexes(Optional.ofNullable(table.getIndexes()))
				);
	}
	
}
