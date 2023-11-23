import axios from 'axios'

const ORDER_API = 'http://localhost:8080/order/'

class OrderService {
    getOrders() {
        return axios.get(ORDER_API + 'all');
    }

    getOrdersByStudentId(id) {
        return axios.get(ORDER_API + 'student/' + id);
    }

    updateOrderStatus(id, orderStatus) {
        return axios.post(ORDER_API + 'update-status/' + id + '/' + orderStatus,{}); 
    }

    placeOrder(order) {
        return axios.post(ORDER_API + 'place-order', order);
    }
}
 
export default new OrderService()