import Vue from 'vue'
/**
 * 成功通知
 * @param message 内容
 * @param [title] 标题
 */
export function successMsg(message, title) {
  Vue.prototype.$notify.success({
    title: title,
    message: message,
    position: 'top-right'
  });
}

/**
 * 警告通知
 * @param message 内容
 * @param [title] 标题
 */
export function warningMsg(message, title) {
  Vue.prototype.$notify.warning({
    title: title,
    message: message,
    position: 'top-right'
  });
}

/**
 * 消息通知
 * @param message 内容
 * @param [title] 标题
 */
export function infoMsg(message, title) {
  Vue.prototype.$notify.info({
    title: title,
    message: message,
    position: 'top-right'
  });
}

/**
 * 错误通知
 * @param message 内容
 * @param [title] 标题
 */
export function errorMsg(message, title) {
  Vue.prototype.$notify.error({
    title: title,
    message: message,
    position: 'top-right'
  });
}
