package addtocartapiautomation;



	import io.restassured.RestAssured;
	import io.restassured.http.ContentType;
	import io.restassured.response.Response;
	import org.json.JSONObject;
	import org.testng.Assert;
	import org.testng.annotations.Test;

	import static io.restassured.RestAssured.given;

	public class ApiAddToCart {

	    String baseURL = "https://api.flipkart.com"; // Replace with actual API URL

	    @Test(priority = 1)
	    public void getProductDetails() {
	        RestAssured.baseURI = baseURL;

	        Response response = given()
	                .header("Authorization", "Bearer YOUR_ACCESS_TOKEN")
	                .contentType(ContentType.JSON)
	                .when()
	                .get("/products/iphone14");

	        response.then().log().all();
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Assert.assertEquals(response.jsonPath().getString("name"), "iPhone 14");
	    }

	    @Test(priority = 2)
	    public void addToCartAPI() {
	        RestAssured.baseURI = baseURL;

	        JSONObject requestParams = new JSONObject();
	        requestParams.put("product_id", "iphone14");
	        requestParams.put("quantity", 1);

	        Response response = given()
	                .header("Authorization", "Bearer YOUR_ACCESS_TOKEN")
	                .contentType(ContentType.JSON)
	                .body(requestParams.toString())
	                .when()
	                .post("/cart/add");

	        response.then().log().all();
	        Assert.assertEquals(response.getStatusCode(), 201);
	        Assert.assertEquals(response.jsonPath().getString("message"), "Product added to cart");
	    }

	    @Test(priority = 3)
	    public void verifyCartContents() {
	        RestAssured.baseURI = baseURL;

	        Response response = given()
	                .header("Authorization", "Bearer YOUR_ACCESS_TOKEN")
	                .contentType(ContentType.JSON)
	                .when()
	                .get("/cart");

	        response.then().log().all();
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Assert.assertTrue(response.jsonPath().getList("cart_items").size() > 0, "Cart is empty!");
	    }
	}


