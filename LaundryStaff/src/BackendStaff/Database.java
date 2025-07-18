package BackendStaff;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;
import org.json.*;

public class Database {

    private static final String API_URL = "http://localhost/laundry_api.php";

    // Staff Login
    public static int loginStaff(String email, String password) {
        try {
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("email", email);
            jsonInput.put("password", password);

            String response = sendPost(API_URL + "?action=staff_login", jsonInput.toString());
            JSONObject json = new JSONObject(response);

            return json.getInt("staff_id");

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

 // Fetch all orders for staff
    public static void fetchAllOrders(Consumer<Vector<Vector<Object>>> callback) {
        new Thread(() -> {
            try {
                String response = sendGet(API_URL + "?action=view_all_orders");
                JSONObject json = new JSONObject(response);

                Vector<Vector<Object>> data = new Vector<>();
                if (json.getString("status").equals("success")) {
                    JSONArray orders = json.getJSONArray("data");

                    for (int i = 0; i < orders.length(); i++) {
                        JSONObject obj = orders.getJSONObject(i);
                        Vector<Object> row = new Vector<>();
                        row.add(obj.getInt("order_id"));
                        row.add(obj.getString("customer_name"));
                        row.add(obj.getString("service_type"));
                        row.add(obj.getString("clothing_type"));
                        row.add(obj.getDouble("weight"));
                        row.add(obj.getDouble("price"));
                        row.add(obj.getString("order_date")); // already formatted by PHP
                        row.add(obj.getString("status"));
                        data.add(row);
                    }
                }

                SwingUtilities.invokeLater(() -> callback.accept(data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Staff: Update Order Status
    public static void updateOrderStatus(int orderId, String status) {
        new Thread(() -> {
            try {
                JSONObject jsonInput = new JSONObject();
                jsonInput.put("id", orderId);
                jsonInput.put("status", status);

                String response = sendPost(API_URL + "?action=update_status", jsonInput.toString());
                System.out.println("Update Status Response: " + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    // ======================== HTTP Helper Methods ========================

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
