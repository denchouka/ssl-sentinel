/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  return str !== null && str.length >= 8 && str.length <= 20 && isNaN(str) && /^[A-Za-z0-9]+$/.test(str)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validPassword(str) {
  return str !== null && str.length >= 8 && str.length <= 20 && isNaN(str)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validCode(str) {
  return str !== null && str.length == 6 && !isNaN(str)
}
