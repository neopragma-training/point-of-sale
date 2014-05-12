package pos.model;

import static pos.common.Utils.databaseHost;
import static pos.common.Utils.databaseName;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Encapsulates information about a product
 * 
 * @author neopragma
 */
public class Product extends BasicDBObject{
	
	private static final long serialVersionUID = 585718079673038770L;
	private static final String PRODUCTS = "products";
	private static final String ID = "_id";
	private static final String SKU = "sku";
	private static final String DESCRIPTION = "description";
	private static final String UNIT_PRICE = "unit_price";
	private static final String TAXABLE = "taxable";

	ObjectId id;
	String sku;
	String description;
	BigDecimal unitPrice;
	Boolean taxable;
	
	public Product(ObjectId id, String sku, String description, BigDecimal unitPrice, Boolean taxable) {
		this.id = id;
		this.sku = sku;
		this.description = description;
		setUnitPrice(unitPrice);
		this.taxable = taxable;
	}
	
	public static Product newProduct(String sku, String description, BigDecimal unitPrice, Boolean taxable) throws UnknownHostException, DuplicateSkuException {
		Product product = new Product(null, sku, description, unitPrice, taxable);
		if (exists(sku)) {
			throw new DuplicateSkuException();
		}
		product.put(SKU, sku);
		product.put(DESCRIPTION, description);
		product.put(UNIT_PRICE, unitPrice.doubleValue());
		product.put(TAXABLE, taxable);
		collection().insert(product);
		return product;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
		this.unitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public Boolean isTaxable() {
		return taxable;
	}
	
	public void setTaxable(Boolean taxable) {
		this.taxable = taxable;
	}
	
	public static boolean exists(String sku) throws UnknownHostException {
		DBObject searchBy = new BasicDBObject();
		searchBy.put(SKU, sku);
		DBObject returnFields = new BasicDBObject();
		returnFields.put(ID, 1 );
		return collection().findOne(searchBy, returnFields) != null;
	}
	
	public static List<Product> findAll() throws UnknownHostException {
		List<Product> products = new ArrayList<Product>();
		DBCollection documents = collection();
		DBCursor cursor = documents.find();
		for (Iterator<DBObject> iter = cursor.iterator() ; iter.hasNext() ; ) {
			DBObject dbObject = iter.next();
			Product product = new Product(
				(ObjectId) dbObject.get(ID),
				(String) dbObject.get(SKU),
				(String) dbObject.get(DESCRIPTION),
				new BigDecimal((Double) dbObject.get(UNIT_PRICE)),
				new Boolean((Boolean) dbObject.get(TAXABLE)));
			products.add(product);
		}
		return products;
	}

	private static DBCollection collection() throws UnknownHostException {
		MongoClient mongo = new MongoClient(databaseHost());
		DB db = mongo.getDB(databaseName());
		DBCollection collection = db.getCollection(PRODUCTS);
		return collection;
	}

}
