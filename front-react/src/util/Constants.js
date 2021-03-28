export default (
    Constants =  {
        API: {
            BASE_URL: 'http://192.168.15.16:8080',
            SOCKET_URL: 'https://uobaa.herokuapp.com',
            ACCESS_TOKEN: 'x-user-auth',
            BASIC_AUTH_TOKEN: 'x-basic-auth',
            RESOURCES: {
                USER: {
                    SIGN_UP: '/users/register',
                    ACTIVE_USER: '/users/active',
                    REGISTER_COMPLETE: '/users/register-complete',
                    UPLOAD_PIC_PROFILE: '/users/upload/pic-profile',
                    FIND_CONTACTS: '/users/list-contacts'
                }
            }
        },
        CONTACT_TYPE: {
            PHONE: 1,
            FOLLOWED: 2,
            FOLLWING: 3
        },
        SIGNED: 'signed',
        USER_ID: 'userId',
        USER_PHONE: 'userPhone',
        FRIEND: 'FRIEND',
        OWNER: 'OWNER',
        TIME_FORMAT: 'hh:mm A',
        DATE_TIME_FORMAT: 'DD MMM YYYY hh:mm A'
    }
);