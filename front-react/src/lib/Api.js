import axios from 'axios';
import Constants from '../util/Constants';

export const Api = axios.create({
    baseURL: Constants.API.BASE_URL
});

Api.interceptors.request.use(
    async request => {
        request.headers = {
            'Content-type': 'application/json'
        };
        //console.log('Request intercepted:', request);
        return request;
    },
    error => {
        return Promise.reject(error);
    }
);

Api.interceptors.response.use(
    async response => {
        //console.log('Response intercepted: ', response);
        return response;
    },
    error => {
        console.log(error)
        return Promise.reject(error);
    }
)
