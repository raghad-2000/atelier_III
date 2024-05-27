import { redirectToUrl } from './redirectService';

/**
 * @param: none
 * @returns: true if user is connected, false otherwise
 */
export function isUserConnected(): boolean {
	const token = sessionStorage.getItem('token');
	return token !== null && token !== undefined;
}

export function logOut(): void {
	sessionStorage.clear();
	window.location = '/';
}
