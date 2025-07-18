<?php
header("Content-Type: application/json");

// Database Connection
$host = "localhost";
$user = "root";
$pass = "";
$db = "laundry";

$conn = new mysqli($host, $user, $pass, $db);

if ($conn->connect_error) {
    die(json_encode(["status" => "error", "message" => "Database connection failed."]));
}

// API Logic
$action = $_GET['action'] ?? '';

switch ($action) {
    case 'register':
        $data = json_decode(file_get_contents("php://input"));
        $name = $data->name;
        $email = $data->email;
        $password = $data->password;

        $sql = "INSERT INTO customer (name, email, password) VALUES (?, ?, ?)";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("sss", $name, $email, $password);

        if ($stmt->execute()) {
            echo json_encode(["status" => "success", "message" => "Customer registered successfully."]);
        } else {
            echo json_encode(["status" => "error", "message" => "Registration failed."]);
        }
        break;

    case 'login':
        $data = json_decode(file_get_contents("php://input"));
        $email = $data->email;
        $password = $data->password;

        $sql = "SELECT customer_id FROM customer WHERE email = ? AND password = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ss", $email, $password);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($row = $result->fetch_assoc()) {
            echo json_encode(["status" => "success", "customer_id" => $row['customer_id']]);
        } else {
            echo json_encode(["status" => "error", "customer_id" => -1]);
        }
        break;

    case 'create_order':
        $data = json_decode(file_get_contents("php://input"));
        $customer_id = $data->customer_id;
        $service_type = $data->service_type;
        $clothing_type = $data->clothing_type;
        $weight = $data->weight;
        $price = $data->price;

        $sql = "INSERT INTO `orders` (customer_id, service_type, clothing_type, weight, price, order_date, status) 
                VALUES (?, ?, ?, ?, ?, NOW(), 'Order Placed')";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("issdd", $customer_id, $service_type, $clothing_type, $weight, $price);

        if ($stmt->execute()) {
            echo json_encode(["status" => "success", "message" => "Order created successfully."]);
        } else {
            echo json_encode(["status" => "error", "message" => "Order creation failed."]);
        }
        break;

    case 'view_order':
        $customer_id = $_GET['customer_id'];

        $sql = "SELECT id AS order_id, service_type, clothing_type, weight, price, order_date, status 
                FROM `orders` WHERE customer_id = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $customer_id);
        $stmt->execute();
        $result = $stmt->get_result();

        $orders = [];
        while ($row = $result->fetch_assoc()) {
            $orders[] = $row;
        }

        echo json_encode([
            "status" => "success",
            "data" => $orders
        ]);
        break;

    case 'staff_login':
        $data = json_decode(file_get_contents("php://input"));
        $email = $data->email;
        $password = $data->password;

        $sql = "SELECT staff_id FROM staff WHERE email = ? AND password = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ss", $email, $password);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($row = $result->fetch_assoc()) {
            echo json_encode(["status" => "success", "staff_id" => $row['staff_id']]);
        } else {
            echo json_encode(["status" => "error", "staff_id" => -1]);
        }
        break;

    case 'view_all_orders':
    $sql = "SELECT o.id AS order_id, c.name AS customer_name, o.service_type, o.clothing_type, 
                   o.weight, o.price, DATE(o.order_date) AS order_date, o.status
            FROM orders o
            JOIN customer c ON o.customer_id = c.customer_id";
    $result = $conn->query($sql);

    $orders = [];
    while ($row = $result->fetch_assoc()) {
        $orders[] = $row;
    }

    echo json_encode([
        "status" => "success",
        "data" => $orders
    ]);
    break;


    case 'update_status':
    $data = json_decode(file_get_contents("php://input"));
    $id = $data->id;
    $status = $data->status;

    $sql = "UPDATE `orders` SET status = ? WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("si", $status, $id);

    if ($stmt->execute()) {
        echo json_encode(["status" => "success", "message" => "Order status updated."]);
    } else {
        echo json_encode(["status" => "error", "message" => "Failed to update order status."]);
    }
    break;


    default:
        echo json_encode(["status" => "error", "message" => "Invalid action."]);
}

$conn->close();
?>

