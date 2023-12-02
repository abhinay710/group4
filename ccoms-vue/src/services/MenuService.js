import axios from 'axios'

const MENU_API = 'http://localhost:8080/menu/'

class MenuService {
  getMenuItems() {
    return axios.get(MENU_API + 'all');
  }


  saveMenu(menu) {
    return axios.post(MENU_API + 'save', menu);
  }
}

export default new MenuService()