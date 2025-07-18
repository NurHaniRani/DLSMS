package BackendCustomer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;
import org.json.*;

public class Database {

    private static final String API_URL = "http://localhost/laundry_api.php";

    // Register Customer
    public static void registerCustomer(String name, String email, String password) {
        new Thread(() -> {
            try {
                JSONObject jsonInput = new JSONObject();
                jsonInput.put("name", name);
                jsonInput.put("email", email);
                jsonInput.put("password", password);

                String response = sendPost(API_URL + "?action=register", jsonInput.toString());
                System.out.println("Register Response: " + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Customer Login
    public static int loginCustomer(String email, String password) {
        try {
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("email", email);
            jsonInput.put("password", password);

            String response = sendPost(API_URL + "?action=login", jsonInput.toString());
            JSONObject json = new JSONObject(response);

            return json.getInt("customer_id");

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Create Order
    public static void createOrder(int customerId, String serviceType, String clothingType, double weight, double price) {
        new Thread(() -> {
            try {
                JSONObject jsonInput = new JSONObject();
                jsonInput.put("customer_id", customerId);
                jsonInput.put("service_type", serviceType);
                jsonInput.put("clothing_type", clothingType);
                jsonInput.put("weight", weight);
                jsonInput.put("price", price);

                String response = sendPost(API_URL + "?action=create_order", jsonInput.toString());
                System.out.println("Create Order Response: " + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void fetchOrders(int customerId, Consumer<Vector<Vector<Object>>> callback) {
        new Thread(() -> {
            try {
                String response = sendGet(API_URL + "?action=view_order&customer_id=" + customerId);

                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                Vector<Vector<Object>> data = new Vector<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Vector<Object> row = new Vector<>();
                    row.add(obj.getString("service_type"));
                    row.add(obj.getString("clothing_type"));
                    row.add(obj.getDouble("weight"));
                    row.add(obj.getDouble("price"));
                    row.add(obj.getString("order_date"));
                    row.add(obj.getString("status"));
                    data.add(row);
                }

                SwingUtilities.invokeLater(() -> callback.accept(data));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    private static String sendPost(String urlStr, String jsonInput) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonInput.getBytes());
        }

        try (InputStream is = conn.getInputStream()) {
            return new String(is.readAllBytes());
        }
    }

    private static String sendGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (InputStream is = conn.getInputStream()) {
            return new String(is.readAllBytes());
        }
    }
    

}

