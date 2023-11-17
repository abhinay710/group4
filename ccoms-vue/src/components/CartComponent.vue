<template>
  <div>
    <h2>Your Cart</h2>
    <ul>
      <li v-for="(cartItem, index) in cartItems" :key="index" class="cart-item">
        <div class="card">
          <div class="card-text">
            <div>
              <h5 class="card-title">{{ cartItem.item.itemName }}</h5>
              <span class="item-price">${{ cartItem.item.price.toFixed(2) }}</span>
            </div>
            <div class="card-body">
              <span class="quantity-controls">
                <button @click="updateQuantity(index, 'decrease')" class="btn btn-secondary">-</button>
                <span class="quantity">{{ cartItem.quantity }}</span>
                <button @click="updateQuantity(index, 'increase')" class="btn btn-secondary">+</button>
                <button @click="removeFromCart(index)" class="btn btn-danger">Remove</button>
              </span>
            </div>
          </div>
        </div>
      </li>
    </ul>
    <p>Total: ${{ totalPrice }}</p>
  </div>
</template>

  
<script>
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
    },
    updateQuantity(index, action) {
      let cartItem = this.cartItems[index];

      if (action === 'increase') {
        cartItem.quantity++;
      } else if (action === 'decrease') {
        if (cartItem.quantity > 1) {
          cartItem.quantity--;
        }
      }
      this.updateTotal()
      localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    },
    updateTotal() {
      this.cartItems = JSON.parse(localStorage.getItem('cartItems'));

      this.totalPrice = this.cartItems.reduce((total, cartItem) => total + cartItem.item.price * cartItem.quantity, 0).toFixed(2);
    },
  },
  created() {
    this.getCartItems();
  }
};
</script>
  
<style scoped>
/* Add custom styling here if needed */

.cart-item {
  list-style: none;
  margin-bottom: 20px;
}

.card {
  width: 300px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card-body {
  padding: 15px;
}

.card-title {
  font-size: 1.25rem;
  font-weight: bold;
}

.card-text {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.item-price {
  font-size: 1.1rem;
  font-weight: bold;
}

.quantity-controls {
  display: flex;
  align-items: center;
}

.quantity {
  margin: 0 8px;
}
</style>

  