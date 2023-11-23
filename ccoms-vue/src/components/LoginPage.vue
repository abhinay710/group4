<template>
  <div class="container mt-5">
    <h2 class="text-center mb-4 text-light">Login</h2>

    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card gold-background">
          <form @submit.prevent="submitLogin" class="p-3">
            <div class="mb-3">
              <label for="username" class="form-label text-dark">Email</label>
              <input type="text" class="form-control" id="username" v-model="username" required>
            </div>

            <div class="mb-3">
              <label for="password" class="form-label text-dark">Password</label>
              <input type="password" class="form-control" id="password" v-model="password" required>
            </div>

            <fieldset class="mb-3">
              <div class="form-check">
                <div class="row">
                  <div class="col-auto">
                    <input class="form-check-input" type="radio" name="gridRadios" id="student" v-model="designation" value="student" checked>
                  </div>
                  <div class="col-auto">
                    <label class="form-check-label text-dark" for="student">Student</label>
                  </div>
                </div>
              </div>
              <div class="form-check">
                <div class="row">
                  <div class="col-auto">
                    <input class="form-check-input" type="radio" name="gridRadios" id="employee" v-model="designation" value="employee">
                  </div>
                  <div class="col-auto">
                    <label class="form-check-label text-dark" for="employee">Employee</label>
                  </div>
                </div>
              </div>
            </fieldset>

            <!-- Display error message -->
            <div v-if="loginError" class="alert alert-danger" role="alert">
              {{ loginError }}
            </div>

            <button type="submit" class="btn btn-primary">Sign in</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LoginService from '../services/LoginService';

export default {
  data() {
    return {
      username: '',
      password: '',
      designation: 'student',
      loginError: null, // New property for login error message
    };
  },
  methods: {
    submitLogin() {
      // Reset login error message
      this.loginError = null;

      LoginService.login({
        userName: this.username,
        password: this.password,
        designation: this.designation,
      })
      .then(response => {
        this.$store.commit('login', response.data);
        const route = response.data.designation === 'student' ? '/menu-items' : '/orders';
        this.$router.push(route).catch(() => {});
      })
      .catch(error => {
        console.error('Error during login:', error);

        // Display login error message
        if (error.response && error.response.status === 401) {
          this.loginError = 'Wrong username or password';
        } else {
          this.loginError = 'Wrong username or password';

        }
      });
    },
  },
};
</script>

<style>
body {
  background-color: maroon;
  margin: 0;
  padding: 0;
}

.card.gold-background {
  background-color: gold;
  color: black;
  /* Set the text color to ensure readability */
}
</style>
