<template>
    <div>
        <h2><strong>ORDERS</strong></h2>

        <table class="table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th v-if="$store.state.designation !== 'student'">Student Name</th>
                    <th>Order Status</th>
                    <th>Total Amount</th>
                    <th>Date</th>
                    <th>Order Items</th>
                    <th v-if="$store.state.designation !== 'student'">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(order, index) in displayedOrders" :key="index">
                    <td>{{ order.id }}</td>
                    <td v-if="$store.state.designation !== 'student'">{{ order.createdBy }}</td>
                    <td>{{ order.ordersStatus.replaceAll("'", "") }}</td>
                    <td>{{ order.totalAmount }}</td>
                    <td>{{ order.createdOn }}</td>
                    <td >
                        <button class="btn btn-primary" @click="openModal(order)">
                            Order Details
                        </button>
                    </td>
                    <td v-if="$store.state.designation !== 'student'">
                        <button class="btn btn-primary" v-if="order.ordersStatus === 'in preperation'"
                            @click="updateOrderStatus(order, 'packing')">Pack Order</button>
                        <button class="btn btn-primary" v-else-if="order.ordersStatus === 'packing'"
                            @click="updateOrderStatus(order, 'ready to pick up')">Ready to Pickup</button>
                        <button class="btn btn-primary" v-else-if="order.ordersStatus === 'ready to pick up'"
                            @click="updateOrderStatus(order, 'delivered')">Delivered</button>
                    </td>
                    
                </tr>
            </tbody>
        </table>

        <nav>
            <ul class="pagination">
                <li class="page-item" :class="{ disabled: currentPage === 1 }">
                    <button @click="prevPage" class="page-link">Previous</button>
                </li>
                <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: currentPage === page }">
                    <button @click="goToPage(page)" class="page-link">{{ page }}</button>
                </li>
                <li class="page-item" :class="{ disabled: currentPage === totalPages }">
                    <button @click="nextPage" class="page-link">Next</button>
                </li>
            </ul>
        </nav>

        <div v-if="orderDetails" class="modal fade" id="orderDetails" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="orderDetailsLabel">Order Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="closeModal">
                            <span>&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(detail, detailIndex) in orderDetails" :key="detailIndex">
                                    <td>{{ detail.menu.itemName }}</td>
                                    <td>{{ detail.quantity }}</td>
                                    <td>{{ detail.price }}</td>
                                    <td>{{ or }}</td>
                                </tr>
                            </tbody>
                        </table>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                            @click="closeModal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>  

<script>
import OrderService from '../services/OrderService';

/* eslint-disable */
export default {
    data() {
        return {
            orders: [],
            pageSize: 8,
            currentPage: 1,
            orderDetails: null
        };
    },
    computed: {
        totalPages() {
            return Math.ceil(this.orders.length / this.pageSize);
        },
        displayedOrders() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.orders.slice(start, end);
        },
    },
    methods: {
        async getOrders(forceRefresh) {
            try {
                if (localStorage.getItem('designation') === 'student') {
                    const response = await OrderService.getOrdersByStudentId(localStorage.getItem('id'));
                    this.orders = response.data;
                } else {
                    const response = await OrderService.getOrders({ forceRefresh });
                    this.orders = response.data;
                }
            } catch (error) {
                console.error('Error fetching orders:', error);
                // Handle the error, e.g., show a user-friendly message
            }

        },
        prevPage() {
            if (this.currentPage > 1) {
                this.currentPage--;
            }
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.currentPage++;
            }
        },
        goToPage(page) {
            this.currentPage = page;
        },
        async updateOrderStatus(order, newStatus) {

            order.loading = true; // Set loading state to true
            try {
                await OrderService.updateOrderStatus(order.id, newStatus);
                this.orders = []; // Clear orders array
                await this.getOrders(); // Fetch orders after successful update
            } catch (error) {
                console.error('Error updating order status:', error);
                // Handle the error, e.g., show a user-friendly message
            } finally {
                order.loading = false; // Set loading state to false after update completes or fails
            }
        },
        openModal(order) {
            this.orderDetails = order.ordersDetails
            $('#orderDetails').modal('show');
        },
        closeModal() {
            this.orderDetails = null;
            $('#orderDetails').modal('hide');
        },
    },
    mounted() {
        this.getOrders();
    },
};
</script>
  
<style scoped>
h2 {
    margin: 20px;
}

.table {
    width: 100%;
    margin-bottom: 1rem;
    color: #212529;
    border-collapse: collapse;
}

.table th,
.table td {
    padding: 0.75rem;
    vertical-align: top;
    border-top: 1px solid #dee2e6;
}

.table thead th {
    vertical-align: bottom;
    border-bottom: 2px solid #dee2e6;
    background-color: #f8f9fa;
}

.pagination {
    display: flex;
    list-style: none;
    padding: 0;
    justify-content: center;
    /* Center the pagination */
}

.page-item {
    margin-right: 5px;
}

.page-item button {
    background-color: gold !important;
    color: black;
}

.page-link {
    display: block;
    padding: 0.5rem 0.75rem;
    color: #007bff;
    background-color: #fff;
    border: 1px solid #dee2e6;
    cursor: pointer;
}

.page-link:hover {
    z-index: 2;
    color: #0056b3;
    background-color: #e9ecef;
    border-color: #dee2e6;
}

.page-link:focus {
    z-index: 2;
    outline: 0;
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}
</style>