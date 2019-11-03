import Vue from 'vue'
/**
 * 成功通知
 * @param message 内容
 * @param [title] 标题
 */
export function success(message, title) {
  Vue.prototype.$notify.success({
    title: title,
    message: message,
    position: 'bottom-right'
  });
}

/**
 * 警告通知
 * @param message 内容
 * @param [title] 标题
 */
export function warning(message, title) {
  Vue.prototype.$notify.warning({
    title: title,
    message: message,
    position: 'bottom-right'
  });
}

/**
 * 消息通知
 * @param message 内容
 * @param [title] 标题
 */
export function info(message, title) {
  Vue.prototype.$notify.info({
    title: title,
    message: message,
    position: 'bottom-right'
  });
}

/**
 * 错误通知
 * @param message 内容
 * @param [title] 标题
 */
export function error(message, title) {
  Vue.prototype.$notify.error({
    title: title,
    message: message,
    position: 'bottom-right'
  });
}
