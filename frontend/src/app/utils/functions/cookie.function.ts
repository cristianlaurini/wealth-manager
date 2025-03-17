export function getCookie(name: string) {
    return document.cookie
        .split(";")
        .map(cookie => cookie.trim())
        .filter(cookie => cookie.substring(0, name.length) == name)
        .map(cookie => cookie.substring(name.length + 1))
        [0] || null;
}