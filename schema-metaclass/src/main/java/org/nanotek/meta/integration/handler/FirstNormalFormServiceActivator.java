package org.nanotek.meta.integration.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.SchemaTable;
import org.nanotek.meta.model.rdbms.classification.data.TableColumns;
import org.nanotek.meta.model.rdbms.classification.data.TableForeignKeys;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexes;
import org.nanotek.meta.model.rdbms.classification.data.TableKey;
import org.nanotek.meta.model.rdbms.classification.task.FirstNormalFormClassificationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Table;

@Component
public class FirstNormalFormServiceActivator {

	@Autowired 
	private FirstNormalFormClassificationTask theTask; 

	public FirstNormalFormServiceActivator() {
	}


	@ServiceActivator(inputChannel = "firstNormalFormMessageChannel" , outputChannel = "secondNormalFormMessageChannel")
	public Message<List<Table>> firstNormalFormServiceActivator(Message<?> message) {
		Object oPayload = message.getPayload();
		Optional<?> optPayload = Optional.class.cast(oPayload);
		Collection <?> theCollection = optPayload
				.map(obj -> Collection.class.cast(obj)).get();
		List <Table> thetables = theCollection.stream().map(obj -> Table.class.cast(obj)).collect(Collectors.toList());
		int count = thetables.size();
		Table[] tary = thetables.toArray(new Table[thetables.size()]);
		List<Optional<?>> resultList = new ArrayList<>();
		for (int i = 0 ; i < count-1 ; i++) {
			for (int j = i + 1 ; j < count ; ++j) {
				System.err.println(" " + tary[i].getName() + "  " + tary[j].getName());
				ClassificationData cd1 = buildClassificationData (tary[i]);
				ClassificationData cd2 = buildClassificationData (tary[j]);
				ClassificationDataPair cdp = new ClassificationDataPair(Pair.of(cd1,cd2)) ;
				Optional<?> cr = theTask.evaluate(cdp);
				cr.ifPresent(c -> {
					resultList.add(Optional.of(c));
				});
			}
		}
		return MessageBuilder.withPayload(thetables).setHeader("firstNormalFormResults", resultList).build();
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
