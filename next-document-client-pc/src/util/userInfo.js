import vueCookies from 'vue-cookies';

export function getToken() {
  return vueCookies.get('token');
}

export function setToken(token) {
  vueCookies.set('token', token, -1);
}

export function getUserInfo() {
  return vueCookies.get('userInfo');
}

export function setUserInfo(userInfo) {
  vueCookies.set('userInfo', userInfo, -1);
}

export function getRole() {
  return vueCookies.get('role');
}

export function setRole(role) {
  vueCookies.set('role', role, -1);
}

export function getPermission(permission) {
  return vueCookies.get('permission');
}

export function setPermission(permission) {
  vueCookies.set('permission', permission, -1);
}
