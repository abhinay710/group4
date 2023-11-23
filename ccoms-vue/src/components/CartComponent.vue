<template>
  <div class="container mt-5">
    <h2>Your Shopping Cart</h2>
    <div v-if="cartItems.length === 0" class="alert bg-gold" role="alert">
      Your cart is empty. Start shopping now!
    </div>
    <div v-else>
      <table class="table">
        <thead>
          <tr>
            <th scope="col">Product</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Total</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(cartItem, index) in cartItems" :key="index">
            <td>{{ cartItem.item.itemName }}</td>
            <td>${{ cartItem.item.price.toFixed(2) }}</td>
            <td>
              <div class="input-group justify-content-center">
                <button class="btn btn-outline-secondary" type="button" @click="decreaseQuantity(index)">-</button>
                <input type="number" v-model="cartItem.quantity" min="1" class="form-control custom-input"
                  @change="updateQuantity(index)">
                <button class="btn btn-outline-secondary" type="button" @click="increaseQuantity(index)">+</button>
              </div>
            </td>
            <td>${{ (cartItem.item.price * cartItem.quantity).toFixed(2) }}</td>
            <td>
              <button class="btn btn-danger btn-sm" @click="removeFromCart(index)">Remove</button>
            </td>
          </tr>
          <tr>
            <td colspan="3"></td>
            <td class="text-right" colspan="2">
              <strong>Total: ${{ totalPrice }}</strong>
            </td>
          </tr>
        </tbody>
      </table>

      <button class="btn bg-gold mt-3 text-dark" @click="checkout" >Order Now</button>
    </div>
  </div>
</template>

  
<script>
import OrderService from '../services/OrderService';

export default {
  data() {
    return {
      cartItems: [],
      totalPrice: 0
    };
  },
  methods: {
    getCartItems() {
      this.cartItems = JSON.parse(localStorage.getItem('cartItems'));
    },
    removeFromCart(index) {

      this.cartItems.splice(index, 1);
      localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
      this.updateTotal()
    },
    increaseQuantity(index) {
      this.cartItems[index].quantity++;
      localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
      this.updateTotal()
    },
    decreaseQuantity(index) {
      if (this.cartItems[index].quantity > 1) {
        this.cartItems[index].quantity--;
        localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
        this.updateTotal()
      }
    },
    // updateQuantity() {
    //   this.updateTotal()
    //   localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    // },
    updateTotal() {
      this.totalPrice = this.cartItems.reduce((total, cartItem) => total + cartItem.item.price * cartItem.quantity, 0).toFixed(2);
    },
    checkout() {
      let orderDetails = [];
      this.cartItems.forEach(item => {
        let orderDetail = {
          menuID: item.item.id,
          quantity: item.quantity,
          ordersID: null,
          price: item.item.price
        }
        
        orderDetails.push(orderDetail);
      });
      let orders = {
        studentID: localStorage.getItem('id'),
        diningHallID: 1,
        employeeID: null,
        totalAmount: this.totalPrice,
        ordersStatus: 'in preperation',
        ordersDetails: orderDetails
      }

      OrderService.placeOrder(orders).then((response) => {
        console.log('placed' + response);  
        localStorage.setItem('cartItems', JSON.stringify([]));
        this.$router.push("/orders").catch(() => { });

      })
    }
  },
  created() {
    this.getCartItems();
    this.updateTotal()
  }
};
</script>
  
<style scoped>
.custom-input {
  max-width: 70px;
}
</style>

  