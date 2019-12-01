import vueCookies from 'vue-cookies';

/**
 * 获取用户自定义的皮肤设置
 * @returns {string|*} 设置的皮肤
 */
export function getThemeName() {
  let exist = vueCookies.isKey('theme');
  if (!exist) {
    return 'default';
  }
  let theme = vueCookies.get('theme');
  if (!['black', 'pink', 'orange', 'default'].includes(theme)) {
    return 'default';
  }
  return theme;
}

/**
 * 设置皮肤工具方法
 * @param selectTheme 选中的皮肤
 */
export function setTheme(selectTheme) {
  vueCookies.set('theme', selectTheme, -1);
}
