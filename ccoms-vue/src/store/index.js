import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userName: "",
    designation: ""
  },
  getters: {
    isLoggedIn(state) {
      if (state.userName) {
        return true;
      }

      return false;
    },
    isManagerLoggedIn(state) {
        if (state.designation === 'manager') {
          return true;
        }

        return false;
    },
    isEmployeeLoggedIn(state) {
      if (state.designation === 'employee') {
        return true;
      }

      return false;
  }
  },
  mutations: {
    login(state, loginDetails) {
      state.userName = loginDetails.userName;
      state.designation = loginDetails.designation;
      localStorage.setItem('userName', loginDetails.userName);
      localStorage.setItem('designation', loginDetails.designation);
    },
    logout(state) {
      state.userName = "";
      state.designation = "";
      localStorage.removeItem('userName');
      localStorage.removeItem('designation');
    },
    initialiseStore(state) {
      if (localStorage.getItem('userName')) {
        state.userName = localStorage.getItem('userName');
      }

      if (localStorage.getItem('designation')) {
        state.designation = localStorage.getItem('designation');
      }
    }
  },
  actions: {
  },
  modules: {
  }
})
