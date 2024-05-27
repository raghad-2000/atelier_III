import { goto } from '$app/navigation';
import { redirect } from '@sveltejs/kit';

// fonction redirection classique
export function redirectToUrl(url: string) {
	window.location = url;
}
