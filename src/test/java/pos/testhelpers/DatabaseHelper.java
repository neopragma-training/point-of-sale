package pos.testhelpers;

import static pos.common.Utils.databaseHost;
import static pos.common.Utils.databaseName;

import java.io.IOException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DatabaseHelper {
	
	public void clearCollection(String collectionName) throws IOException {
		collection(collectionName).remove(new BasicDBObject());
	}
	
	public void loadProducts() throws IOException {
		
	}

	private DBCollection collection(String collectionName) throws IOException {
		MongoClient mongo = new MongoClient(databaseHost());
		DB db = mongo.getDB(databaseName());
		return db.getCollection(collectionName);		
	}
	
}
