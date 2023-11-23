import axios from 'axios'

const DINING_API = 'http://localhost:8080/dining-hall/'

class DiningHallSevice {
    getDiningHalls() {
        return axios.get(DINING_API + 'all');
    }    
}
 
export default new DiningHallSevice()