package controllers;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

import play.mvc.Controller;
import play.mvc.Result;

public class CouchBaseController extends Controller {

	public Result views() {
		return ok(views.html.employee.render());
	}

	public Result employeeInfo(String id) {

		// For openinig the connection
		Cluster cluster = CouchbaseCluster.create("localhost:8091");
		// For authentication
		cluster.authenticate("admin", "admin123");
		// Which bucket we want to open
		Bucket bucket = cluster.openBucket("employee");
		// To retrieve the bucket
		 JsonDocument doc = bucket.get(id);

		// Insert the data into the bucket
		//JsonObject user = JsonObject.empty().put("id", 6).put("name", "mohit").put("phone", "9882140698").put("dob",
		//		"07/07/1919");

		// Creation of id for the Document
		//JsonDocument doc = JsonDocument.create("emp_6", user);
		//JsonDocument response = bucket.upsert(doc);

		return ok(doc.content().toString());
	}

}